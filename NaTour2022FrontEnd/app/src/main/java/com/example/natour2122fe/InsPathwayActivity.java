package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsPathwayActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnInsPathway;
    EditText etPathwayName, etPathwayDescription, etPathwayCity;
    Spinner spPathwayDuration, spPathwayDifficulty;
    Switch swDisableAccess;
    Pathway newPathway;
    String username, pathwayDuration, pathwayDifficulty;
    String dAccess = "f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_pathway);

        username = getIntent().getStringExtra("username");
        findView();
        setAdapters();

        swDisableAccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (swDisableAccess.isChecked()) {
                    dAccess = "t";
                }
            }
        });

        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        btnInsPathway.setOnClickListener(v -> testAndSetNewPathway(pathwayApi));
    }

    private void findView() {
        swDisableAccess = findViewById(R.id.swDisableAccess);
        etPathwayName = findViewById(R.id.etPathwayName);
        etPathwayDescription = findViewById(R.id.etPathwayDescription);
        etPathwayCity = findViewById(R.id.etPathwayCity);
        btnInsPathway = findViewById(R.id.btnInsPathway);
    }

    private void setAdapters() {
        spPathwayDuration = findViewById(R.id.spPathwayDuration);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.PathwayDuration, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPathwayDuration.setAdapter(adapter);
        spPathwayDuration.setOnItemSelectedListener(this);

        spPathwayDifficulty = findViewById(R.id.spPathwayDifficulty);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.PathwayDifficulty, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPathwayDifficulty.setAdapter(adapter2);
        spPathwayDifficulty.setOnItemSelectedListener(this);
    }

    private void testAndSetNewPathway(PathwayApi pathwayApi) {
        newPathway = (Pathway) getIntent().getSerializableExtra("Pathway");
        boolean mapPoint = getIntent().getBooleanExtra("MapPoint", false);
        if (!mapPoint) {
            Toast.makeText(this, "Non hai inserito i punti sulla mappa: clicca sull'immagine della mappa per proseguire", Toast.LENGTH_SHORT).show();
        } else {
            if (etPathwayName.getText().toString().length() == 0) {
                etPathwayName.setError(getResources().getString(R.string.mandatory));
            } else {
                if (etPathwayDescription.getText().toString().length() == 0) {
                    etPathwayDescription.setError(getResources().getString(R.string.mandatory));
                } else {
                    if (etPathwayCity.getText().toString().length() == 0) {
                        etPathwayCity.setError(getResources().getString(R.string.mandatory));
                    } else {
                        if (pathwayDuration.isEmpty() || pathwayDifficulty.isEmpty()) {
                            Toast.makeText(this, "Non hai inserito uno tra: durata e difficolta nei menu a tendina", Toast.LENGTH_SHORT).show();
                        } else {
                            String pathwayName = String.valueOf(etPathwayName.getText());
                            String pathwayDescription = String.valueOf(etPathwayDescription.getText());
                            String pathwayCity = String.valueOf(etPathwayCity.getText());
                            newPathway.setName(pathwayName);
                            newPathway.setDescription(pathwayDescription);
                            newPathway.setCity(pathwayCity);
                            newPathway.setDifficulty(pathwayDifficulty);
                            newPathway.setDuration(pathwayDuration);
                            newPathway.setAccessibility(dAccess);
                            sendPathway(newPathway, pathwayApi);
                        }
                    }
                }
            }
        }
    }

    private void sendPathway(Pathway newPathway, PathwayApi pathwayApi)
    {
        pathwayApi.addPathway(newPathway)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(InsPathwayActivity.this,"Inserimento del percorso avvenuto con successo", Toast.LENGTH_SHORT).show();
                        showHome(newPathway.getUsername());
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InsPathwayActivity.this,"Inserimento del percorso non avvenuto", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InsPathwayActivity.class.getName()).log(Level.SEVERE,"Errore che occorre:",t);
                    }
                });
    }
    private void showHome(String user) {
        Intent openPage = new Intent(this, HomeActivity.class);
        openPage.putExtra("username",user);
        startActivity(openPage);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(adapterView.getItemAtPosition(0).toString().equals("30min")){
            pathwayDuration = adapterView.getItemAtPosition(position).toString();
       }else{
            pathwayDifficulty = adapterView.getItemAtPosition(position).toString();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}