package com.android.cs.project.eforest_office;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNews extends AppCompatActivity {
    Button btnChooseImg,btnUploadImg;
    ProgressBar progressBar;
    EditText edHeadImg,edDescrImg;
    String imagepath=null;
    NewsClass newsClass;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1){
            if (resultCode==RESULT_OK){
                try {
                    assert data != null;
                    Uri fileUri=data.getData();
                    //Setting reference of storege,.. Child name is the folder name...
                    StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("eForest").child("News");
                    //Creating a timestamp for rename...
                    String timestamp=String.valueOf(System.currentTimeMillis());
                    final StorageReference storageReference1=storageReference.child(timestamp+fileUri.getLastPathSegment());
                    storageReference1.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //Download the uploaded file url from storefe... To store it in database...
                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //uri contain the path of the Uploaded file...
                                    imagepath=String.valueOf(uri);
                                    Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    });
                }catch (NullPointerException ne){
                    Toast.makeText(getApplicationContext(),"Null Pointer exception occured",Toast.LENGTH_LONG).show();
                }

            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        progressBar=(ProgressBar)findViewById(R.id.progress2);
        btnChooseImg=(Button)findViewById(R.id.chooseImgAddNews);
        btnUploadImg=(Button)findViewById(R.id.uploadImgAddNews);
        edHeadImg=(EditText)findViewById(R.id.headingAddNews);
        edDescrImg=(EditText)findViewById(R.id.descriptionAddNews);
        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("eForest").child("News");
        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsClass=new NewsClass();
                if (imagepath.equals(null)){
                    Toast.makeText(getApplicationContext(),"Wait Image is Selecting!..",Toast.LENGTH_LONG).show();
                }else {
                    newsClass.setHeading(edHeadImg.getText().toString().trim());
                    newsClass.setDescription(edDescrImg.getText().toString().trim());
                    newsClass.setImageurl(imagepath);
                }

                newsClass.setHeading(edHeadImg.getText().toString().trim());
                newsClass.setDescription(edDescrImg.getText().toString().trim());
                newsClass.setImageurl(imagepath);
                //getting cuurent date and time...
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                newsClass.setTime(formatter.format(date));

                reference.push().setValue(newsClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
