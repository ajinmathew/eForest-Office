package com.android.cs.project.eforest_office;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewImage extends AppCompatActivity {
ImageView imageView;
TextView txtHeading,txtTime,txtDesc;
Picasso picasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        Intent i=getIntent();
        //String imgheading=i.getStringExtra( "TAGforIMG" );


        imageView=(ImageView)findViewById(R.id.imageviewViewImg);
        txtHeading=(TextView)findViewById(R.id.headingViewImg);
        txtTime=(TextView)findViewById(R.id.timeViewImg);
        txtDesc=(TextView)findViewById(R.id.descriptionViewImg);
        txtHeading.setText(i.getStringExtra( "ImgHead" ));
        txtDesc.setText(i.getStringExtra( "ImgDesc" ));
        txtTime.setText(i.getStringExtra( "ImgTime" ));
        picasso.with(getApplicationContext()).load(i.getStringExtra( "ImgUrl" )).into(imageView);

        //Toast.makeText(getApplicationContext(),imgheading,Toast.LENGTH_LONG).show();
    }
}
