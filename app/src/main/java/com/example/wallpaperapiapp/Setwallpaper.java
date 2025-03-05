package com.example.wallpaperapiapp;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class Setwallpaper extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    Button set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setwallpaper);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        set=findViewById(R.id.set);
        imageView=findViewById(R.id.finalimage);
        intent=getIntent();

        String url=intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(Setwallpaper.this, "Done", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }
}