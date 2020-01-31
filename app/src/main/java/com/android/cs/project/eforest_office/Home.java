package com.android.cs.project.eforest_office;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
Button btnApplivationHome,btnNewsHome,btnGalleryHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnApplivationHome=(Button)findViewById(R.id.applicationsHome);
        btnGalleryHome=(Button)findViewById(R.id.galleryHome);
        btnNewsHome=(Button)findViewById(R.id.newsHome);
        btnNewsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),News.class));
            }
        });
        btnGalleryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Gallery.class));
            }
        });
    }
}
