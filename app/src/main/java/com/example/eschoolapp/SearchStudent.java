package com.example.eschoolapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SearchStudent extends AppCompatActivity {
    private EditText ed1;
    private AppCompatButton searchBtn, backBtn;
    private TextView studentDetails;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_student);

        // Initialize views
        ed1 = findViewById(R.id.admno);
        searchBtn = findViewById(R.id.searchbtn);
        backBtn = findViewById(R.id.back);
        studentDetails = findViewById(R.id.studentDetails);

        // Initialize DatabaseHelper
        myDB = new DatabaseHelper(this);

        // Set up the search button click listener
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchStudent();
            }
        });

        // Set up the back button click listener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dash.class);
                startActivity(intent);
            }
        });
    }

    private void searchStudent() {
        String admissionNumber = ed1.getText().toString().trim();
        if (admissionNumber.isEmpty()) {
            Toast.makeText(SearchStudent.this, "Please enter an admission number", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = myDB.getStudentData(admissionNumber);
        if (cursor != null && cursor.moveToFirst()) {
            String details = "Admission_Number: " + cursor.getString(0) +
                    "\nFname: " + cursor.getString(1) +
                    "\nLname: " + cursor.getString(2) +
                    "\nEnrollment_id: " + cursor.getString(3) +
                    "\nDepartment_id: " + cursor.getString(4) +
                    "\nCourse_id: " + cursor.getString(5) +
                    "\nFaculty_id: " + cursor.getString(6);
            studentDetails.setText(details);
            highlightResult(studentDetails);
        } else {
            Toast.makeText(SearchStudent.this, "No data found for this admission number", Toast.LENGTH_SHORT).show();
            studentDetails.setText(""); // Clear previous details
        }
    }

    private void highlightResult(TextView textview) {
        textview.setBackgroundColor(Color.parseColor("#F0F0F0"));
    }

    // Method to show database table
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
