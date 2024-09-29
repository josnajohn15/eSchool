package com.example.eschoolapp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ViewStudent extends AppCompatActivity {
    DatabaseHelper myDB;
    LinearLayout studentDetailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        myDB = new DatabaseHelper(this);
        studentDetailsContainer = findViewById(R.id.studentDetailsContainer);

        displayStudentData();
    }

    private void displayStudentData() {
        Cursor cur = myDB.getAllData();

        if (cur.getCount() == 0) {
            showMessage("Error", "No Data Found");
            return;
        }

        while (cur.moveToNext()) {
            // Create a CardView for each student
            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            cardParams.setMargins(8, 8, 8, 8);
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(8);
            cardView.setCardBackgroundColor(Color.WHITE);

            // Create a new LinearLayout inside the CardView
            LinearLayout cardLayout = new LinearLayout(this);
            cardLayout.setOrientation(LinearLayout.VERTICAL);
            cardLayout.setPadding(16, 16, 16, 16);

            // Create TextView for student details
            String details = "ADMISSION NUMBER: " + cur.getString(0) +
                    "\nFIRST NAME: " + cur.getString(1) +
                    "\nLAST NAME: " + cur.getString(2) +
                    "\nENROLLMENT ID: " + cur.getString(3) +
                    "\nDEPARTMENT ID: " + cur.getString(4) +
                    "\nCOURSE ID: " + cur.getString(5) +
                    "\nFACULTY ID: " + cur.getString(6);

            TextView studentDetails = new TextView(this);
            studentDetails.setText(details);
            studentDetails.setTextSize(18);
            studentDetails.setTextColor(Color.parseColor("#3F51B5"));

            // Add TextView to CardLayout
            cardLayout.addView(studentDetails);

            // Add the cardLayout to cardView
            cardView.addView(cardLayout);

            // Finally, add the CardView to the studentDetailsContainer
            studentDetailsContainer.addView(cardView);
        }
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
