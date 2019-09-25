package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageCapture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);

        Bitmap bitmap = this.getIntent().getParcelableExtra("bitmap");
        ImageView photo = findViewById(R.id.photo);

        photo.setImageBitmap(bitmap);
    }
}
