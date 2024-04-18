package com.example.music_for_all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomeScreen extends AppCompatActivity {

    private TextView emailTextView, fullnameTextView;
    FirebaseFirestore fStore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        TextView emailTextView = findViewById(R.id.textViewEmail);
        TextView fullnameTextView = findViewById(R.id.textViewFullName);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            email = currentUser.getEmail();
            String userId = currentUser.getUid();

            // Retrieve user's full name from Firestore
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

        emailTextView.setText(getString(R.string.email_text, email));

        // Navigate to Dashboard activity after 5 seconds
        new Handler().postDelayed(() -> {
            startActivity(new Intent(WelcomeScreen.this, Dashboard.class));
            finish();
        }, 5000);
    }
}