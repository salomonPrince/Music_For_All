package com.example.music_for_all;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Admin extends AppCompatActivity {

    private TextView emailTextView, fullnameTextView;
    private FirebaseFirestore fStore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        emailTextView = findViewById(R.id.textViewEmail);
        fullnameTextView = findViewById(R.id.textViewFullName);

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Retrieves user full name from Firestore
            DocumentReference userRef = fStore.collection("Users").document(userId);
            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String fullname = documentSnapshot.getString("FullName");
                    fullnameTextView.setText(getString(R.string.fullname_text, fullname));
                } else {
                    // Handle the case where user data doesn't exist
                    fullnameTextView.setText(getString(R.string.fullname_text, "Unknown"));
                }
            }).addOnFailureListener(e -> {
                // Handle errors
                fullnameTextView.setText(getString(R.string.fullname_text, "Unknown"));
            });
        }

        if (currentUser != null) {
            String email = currentUser.getEmail();
            emailTextView.setText(getString(R.string.email_text, email));
        }

        // Sets OnClickListener for admin dashboard button
        Button adminDashboardButton = findViewById(R.id.admin_dashboard);
        adminDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts Admin Dashboard activity
                startActivity(new Intent(Admin.this, AdminDashboard.class));
            }
        });

        // Sets OnClickListener for client dashboard button
        Button dashboardButton = findViewById(R.id.dashboard);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts Client Dashboard activity
                startActivity(new Intent(Admin.this, Dashboard.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        View rootView = findViewById(android.R.id.content);
        rootView.findViewById(R.id.logout_button).setOnClickListener(v -> showLogoutConfirmationDialog());
    }

    // Method to show the logout confirmation dialog
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you really want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {

                    // Performs logout action
                    Intent intent = new Intent(Admin.this, SignIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // Finish the current activity
                })
                .setNegativeButton("No", (dialog, which) -> {

                });
        // Creates and shows the AlertDialog
        builder.create().show();
    }
}
