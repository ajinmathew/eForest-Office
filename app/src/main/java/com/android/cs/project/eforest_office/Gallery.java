package com.android.cs.project.eforest_office;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {
Button btnUploadImg;
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
DatabaseReference reference;
List<Image> imagelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        btnUploadImg=(Button)findViewById(R.id.uploadImgGallery);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerviewGallery);
        recyclerView.setHasFixedSize(true);

        imagelist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        reference= FirebaseDatabase.getInstance().getReference().child("eForest").child("Gallery");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataImg:dataSnapshot.getChildren()){
                    Image image=dataImg.getValue(Image.class);
                    imagelist.add(image);
                }

                adapter=new CustomAdapterImage(imagelist,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddGallery.class));
            }
        });
    }
}
