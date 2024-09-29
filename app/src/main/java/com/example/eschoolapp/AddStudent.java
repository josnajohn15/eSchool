package com.example.eschoolapp;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudent extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    AppCompatButton b1,b2;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ed1 = (EditText) findViewById(R.id.admno);
        ed2 = (EditText) findViewById(R.id.fname);
        ed3 = (EditText) findViewById(R.id.lname);
        ed4 = (EditText) findViewById(R.id.enroll);
        ed5 = (EditText) findViewById(R.id.dep);
        ed6 = (EditText) findViewById(R.id.course);
        ed7 = (EditText) findViewById(R.id.fac);
        b1 = (AppCompatButton) findViewById(R.id.addbtn);
        b2 = (AppCompatButton) findViewById(R.id.back);
        // created object of DatabaseHelper class
        //myDB.getWritableDatabase(); // for checking db is created or not.

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new DatabaseHelper(AddStudent.this);

                boolean isInserted = myDB.insertData(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), ed4.getText().toString(), ed5.getText().toString(), ed6.getText().toString(), ed7.getText().toString());

                // Show toast when data inserted successfully
                if (isInserted) {
                    Toast.makeText(AddStudent.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddStudent.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
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

