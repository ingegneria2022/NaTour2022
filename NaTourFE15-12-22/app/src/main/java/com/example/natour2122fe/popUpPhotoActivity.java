package com.example.natour2122fe;


        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.natour2122fe.Model.Photo;
        import com.example.natour2122fe.R;

public class popUpPhotoActivity extends AppCompatActivity {

    ImageView popUpImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_photo);

        /*settare la metrica cosi che sebri un pop up anche se è una activity*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        /*testo errore a video*/
        popUpImg = findViewById(R.id.ivPathwayPhoto);
        Intent intent = getIntent();
        String name = intent.getStringExtra("namePhoto");

        String link= "https://pathway-image.s3.amazonaws.com/"+ name;
        Glide.with(this)
                .load(link)
                .into(popUpImg);
    }
}
