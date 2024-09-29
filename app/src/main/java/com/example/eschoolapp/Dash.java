package com.example.eschoolapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dash extends AppCompatActivity {
    AppCompatButton b1,b2,b3,b4,b5,b6;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash);
        b1=(AppCompatButton) findViewById(R.id.addstud);
        b2=(AppCompatButton) findViewById(R.id.upstud);
        b3=(AppCompatButton) findViewById(R.id.serstud);
        b4=(AppCompatButton) findViewById(R.id.delstud);
        b5=(AppCompatButton) findViewById(R.id.vistud);
        b6=(AppCompatButton) findViewById(R.id.logbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddStudent.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),UpdateStudent.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SearchStudent.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DeleteStudent.class);
                startActivity(i);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewStudent.class);
                startActivity(i);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    SharedPreferences preferences=getSharedPreferences("Logged",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }

        });

    }
}