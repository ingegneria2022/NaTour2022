package com.example.natour2122fe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.natour2122fe.Model.InterestPoints;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsInterestPointsActivity extends AppCompatActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    Integer idPathway;
    String username, typeInterestPoint;
    GoogleMap map;
    Pathway pathway;
    Double latPoint,lngPoint;
    Spinner spTypeInterestPoint;
    EditText etInterestPointName;
    Button btnInsInterestPoint;
    InterestPoints point = new InterestPoints();
    boolean drag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_interest_points);

        pathway = (Pathway) getIntent().getSerializableExtra("Pathway");
        username = getIntent().getStringExtra("username");

        findView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.TypeInterestPoint, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypeInterestPoint.setAdapter(adapter);
        spTypeInterestPoint.setOnItemSelectedListener(this);

        btnInsInterestPoint.setOnClickListener(v -> {
            testInterestPoint();
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapInsInterestPoint);
        mapFragment.getMapAsync(this);
    }

    private void findView() {
        etInterestPointName = findViewById(R.id.etInterestPointName);
        spTypeInterestPoint = findViewById(R.id.spTypeInterestPoint);
        btnInsInterestPoint = findViewById(R.id.btnInsInterestPoint);
    }

    private void testInterestPoint() {
        if (etInterestPointName.getText().toString().length() == 0) {
            etInterestPointName.setError(getResources().getString(R.string.mandatory));
        } else {
            if (typeInterestPoint.isEmpty()) {
                Toast.makeText(this, "Non hai inserito il tipo di punto di interesse nel menu a tendina", Toast.LENGTH_SHORT).show();
            } else {
                if (!drag) {
                    Toast.makeText(this, "Non hai inserito il nuovo punto di interesse sulla mappa (si deve muovere il marker rosso, dalla posizione di defoult impostata sul punto di inizio del percorso)", Toast.LENGTH_SHORT).show();
                } else {
                    String pointName = String.valueOf(etInterestPointName.getText());
                    point.setName(pointName);
                    point.setIdPathway(pathway.getId());
                    point.setUsername(username);
                    point.setLatitude(latPoint);
                    point.setLongitude(lngPoint);
                    point.setType(typeInterestPoint);
                    sendInterestPoint(point);
                }
            }
        }
    }

    private void sendInterestPoint(InterestPoints point) {
        Log.i("sendInterestPoint:" , point.toString());
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addInterestPoint(point)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(InsInterestPointsActivity.this,"Inserimento del Punto Di Interesse avvenuto con successo", Toast.LENGTH_SHORT).show();
                        showInterestPoint(point.getUsername());
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InsInterestPointsActivity.this,"Inserimento del Punto Di Interesse non avvenuto", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InsInterestPointsActivity.class.getName()).log(Level.SEVERE,"Errore che occorre:",t);
                    }
                });
    }

    private void showInterestPoint(String username) {
        Intent openPage = new Intent(this, InterestPointsActivity.class);
        openPage.putExtra("username",username);
        openPage.putExtra("Pathway",pathway);
        startActivity(openPage);
    }

    @Override
    public void onMapReady( GoogleMap googleMap) {
        map = googleMap;
        LatLng startPositionPathway = new LatLng(pathway.getLatStart(), pathway.getLngStart());
        LatLng finishPositionPathway = new LatLng(pathway.getLatFinish(), pathway.getLngFinish());
        LatLng positionInterestPoint = new LatLng(pathway.getLatStart(), pathway.getLngStart());
        Marker startPathway = map.addMarker(new MarkerOptions().position(startPositionPathway).title("Punto Inizio Percorso").draggable(false));
        Marker finishPathway = map.addMarker(new MarkerOptions().position(finishPositionPathway).title("Punto Fine Percorso").draggable(false));
        Marker newInterestPoint = map.addMarker(new MarkerOptions().position(positionInterestPoint).title("Nuovo Punto Di Interesse").draggable(true));
        CameraPosition position = new CameraPosition.Builder().target(positionInterestPoint).zoom(12).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) { }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                latPoint = newInterestPoint.getPosition().latitude;
                lngPoint = newInterestPoint.getPosition().longitude;
                drag=true;
            }
            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        typeInterestPoint = adapterView.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}