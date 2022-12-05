package com.example.natour2122fe.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.natour2122fe.InsPathwayActivity;
import com.example.natour2122fe.Model.User;
import com.example.natour2122fe.R;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class SignUpActivity extends AppCompatActivity {

    private static final long MAX_BYTE = 5242880;
    AmplifyCognito amplifyCognito;
    EditText etUsername,etEmail,etEmailBis,etPassword,etPasswordBis,etName,etSurname;
    Button btnSignup,btnSignIn,btnLoadPhoto;
    ImageView ivPersonPhoto;
    Uri selectedImage;
    String fileName;
    private boolean onResult=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        findView();
        btnLoadPhoto.setOnClickListener(v -> {
          Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(intent, 3);
        });
        btnSignup.setOnClickListener(v -> {
            loadTestAndSafeUser();

        });
        btnSignIn = findViewById(R.id.btnSignin);
        btnSignIn.setOnClickListener(v -> amplifyCognito.loadLogin());
    }

    private void findView() {
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etEmailBis = findViewById(R.id.etEmailBis);
        etPassword = findViewById(R.id.etPassword);
        etPasswordBis = findViewById(R.id.etPasswordBis);
        btnSignup = findViewById(R.id.btnSignup);
        btnLoadPhoto = findViewById(R.id.btnLoadPhoto);
        ivPersonPhoto=findViewById(R.id.ivPersonPhoto);
    }

    private void loadTestAndSafeUser() {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String emailBis = etEmailBis.getText().toString();
        String password = etPassword.getText().toString();
        String passwordBis = etPasswordBis.getText().toString();
        if((email.equals(emailBis))&&(password.equals(passwordBis))){
            if(name.isEmpty()){
                etName.setError(getResources().getString(R.string.mandatory));
            }else{
                if(surname.isEmpty()){
                    etSurname.setError(getResources().getString(R.string.mandatory));
                }else{
                    if(username.isEmpty()){
                        etUsername.setError(getResources().getString(R.string.mandatory));
                    }else{
                        if (email.isEmpty()){
                            etEmail.setError(getResources().getString(R.string.mandatory));
                        }else{
                            if(password.isEmpty()){
                                etPassword.setError(getResources().getString(R.string.mandatory));
                            }else{
                                loadPopUpLoading();
                                User user = new User();
                                user.setName(name);
                                user.setUsername(username);
                                user.setEmail(email);
                                user.setSurname(surname);
                                if(onResult){
                                    user.setPhoto(fileName);
                                    Log.i("loadTestAndSafeUser: ", String.valueOf(fileName));
                                    saveUser(user);
                                }else{
                                    user.setPhoto("noPhoto");
                                    saveUser(user);
                                }
                                amplifyCognito.signUp(name, surname, email, username, password);
                            }
                        }
                    }
                }
            }
        }else{
            if(email.equals(emailBis)){
                loadPopUpSignError("le due password non corrispondono");
            }else{
                loadPopUpSignError("Le due email non corrispondono");
            }
        }
    }

    private void saveImg(Uri selectedImage) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        try {
            // Make sure the Pictures directory exists.
            path.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(selectedImage.getLastPathSegment());
        // creates RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("photo"), file);
        // MultipartBody.Part is used to send also the actual filename
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);
        String keyStr = "photo";
        // executes the request
            pathwayApi.update(keyStr,body)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if( response.body() != null) {
                                try {
                                    String body = response.body().string();
                                    fileName = body.replace("File uploaded : ", "");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i("TAG", "onResponse: no" + t);
                        }
                        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            selectedImage = data.getData();
            File file = new File(selectedImage.getLastPathSegment());
            Log.i( "saveImg: "+file.length(),selectedImage.getPath());
            if(file.length()<MAX_BYTE){
                saveImg(selectedImage);
                ivPersonPhoto.setImageURI(selectedImage);
                onResult=true;
            }else{
                Toast.makeText(this, "Immagine troppo grande", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveUser(User user) {
        //retrofit
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addUser(user)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
    }

    private void loadPopUpSignError(String error) {
        Intent intent = new Intent(SignUpActivity.this, popUpErrorActivity.class);
        intent.putExtra("errorSign",error);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loadPopUpLoading() {
        setContentView(R.layout.activity_pop_up_loading);
        /*settare la metrica cosi che sebri un pop up anche se è una activity*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}