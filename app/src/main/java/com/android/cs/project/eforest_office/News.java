package com.android.cs.project.eforest_office;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {
    Button btnUploadNews;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    DatabaseReference reference;
    List<NewsClass> newslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        btnUploadNews=(Button)findViewById(R.id.uploadNewsNews);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerviewNews);
        recyclerView.setHasFixedSize(true);

        newslist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        reference= FirebaseDatabase.getInstance().getReference().child("eForest").child("News");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataNews:dataSnapshot.getChildren()){
                    NewsClass newsClass=dataNews.getValue(NewsClass.class);
                    newslist.add(newsClass);
                }

                adapter=new CustomAdapterNews(newslist,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnUploadNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddNews.class));
            }
        });
    }
}
