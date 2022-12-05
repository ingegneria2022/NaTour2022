package com.example.natour2122fe;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.login.popUpErrorActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPointsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    double latStart,lngStart,latFinish, lngFinish;
    Button btnInsPoint;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_points);

        username = getIntent().getStringExtra("username");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady( GoogleMap googleMap) {
        map= googleMap;
        LatLng Napoli = new LatLng(40.841872, 14.316996);
        Marker startPath = map.addMarker(new MarkerOptions().position(Napoli).title("PuntoInizio").draggable(true));
        Marker finishPath = map.addMarker(new MarkerOptions().position(Napoli).title("PuntoFine").draggable(true));
        CameraPosition posizioneNapoli = new CameraPosition.Builder().target(Napoli).zoom(12).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(posizioneNapoli));
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {  }
            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                latStart  = startPath.getPosition().latitude;
                lngStart  = startPath.getPosition().longitude;
                latFinish = finishPath.getPosition().latitude;
                lngFinish = finishPath.getPosition().longitude;
            }
            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {  }
        });
        btnInsPoint = findViewById(R.id.btnInsPoint);
        btnInsPoint.setOnClickListener(v -> {
            LatLng start = new LatLng(latStart, lngStart);
            LatLng finish = new LatLng(latFinish, lngFinish);
            if(start.latitude == 0 || start.longitude == 0 || finish.longitude == 0 || finish.latitude == 0 || start.latitude == Napoli.latitude|| finish.longitude==Napoli.longitude){
                Toast.makeText(this, "Non hai spostato i marker rossi", Toast.LENGTH_SHORT).show();
            }else{
                loadPoints(latStart, lngStart, latFinish, lngFinish, username);
            }
        });
    }

    private void loadPoints(double latStart, double lngStart, double latFinish, double lngFinish, String username){
        Pathway newPathway= new Pathway();
        newPathway.setLatStart(latStart);
        newPathway.setLatFinish(latFinish);
        newPathway.setLngStart(lngStart);
        newPathway.setLngFinish(lngFinish);
        newPathway.setUsername(username);
        Intent intent = new Intent(this, InsPathwayActivity.class);
        intent.putExtra("Pathway", newPathway);
        intent.putExtra("MapPoint", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}