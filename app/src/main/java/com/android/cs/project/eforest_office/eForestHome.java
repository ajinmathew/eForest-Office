package com.android.cs.project.eforest_office;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class eForestHome extends AppCompatActivity {
EditText edUserName,edPassword;
Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_forest_home);

        btnLogin=(Button)findViewById(R.id.btnLoginHome);
        edUserName=(EditText)findViewById(R.id.userNameHome);
        edPassword=(EditText)findViewById(R.id.passwordHome);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname,pword;
                uname=edUserName.getText().toString().trim();
                pword=edPassword.getText().toString().trim();
                if ((uname.equals("admin"))&&(pword.equals("12345"))){
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
