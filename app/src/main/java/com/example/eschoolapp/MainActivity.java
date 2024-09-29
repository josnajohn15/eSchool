package com.example.eschoolapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    AppCompatButton b1;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("Logged",MODE_PRIVATE);
        String n=preferences.getString("user",null);
        if(n!=null)
        {
            Intent i=new Intent(getApplicationContext(), Dash.class);
            startActivity(i);
        }

        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.pass);
        b1=(AppCompatButton) findViewById(R.id.log);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n =ed1.getText().toString();
                String p =ed2.getText().toString();
                if(n.equals("admin")&&p.equals("1234"))
                {
                    SharedPreferences preferences=getSharedPreferences("Logged",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("user","admin");
                    editor.apply();
                    Intent  i=new Intent(getApplicationContext(),Dash.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Entry",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}