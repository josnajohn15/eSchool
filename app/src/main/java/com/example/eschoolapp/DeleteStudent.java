package com.example.eschoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class DeleteStudent extends AppCompatActivity {
    EditText ed1;
    AppCompatButton b1, b2;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_student);

        ed1 = (EditText) findViewById(R.id.admno);
        b1 = (AppCompatButton) findViewById(R.id.delbtn);
        b2 = (AppCompatButton) findViewById(R.id.back);
        myDB = new DatabaseHelper(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer isDeleted = myDB.deleteData(ed1.getText().toString());
                if (isDeleted > 0) {
                    Toast.makeText(DeleteStudent.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteStudent.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dash.class);
                startActivity(i);
            }
        });

    }

    // Method to show database table
    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

