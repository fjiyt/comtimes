package com.example.comtimes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class ImageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
        ImageView imageView = findViewById(R.id.imageView);
        setImage(imageView);
    }

    private void setImage(ImageView imageView) {
        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");
        imageView.setImageResource(imageID);
    }
}