package com.example.music_for_all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment extends Fragment {

    private ImageView profilePhoto;
    private EditText nameEditText, emailEditText, ageEditText;
    private Button saveButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflates the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Initializes views
        profilePhoto = view.findViewById(R.id.profile_photo);
        nameEditText = view.findViewById(R.id.name_edittext);
        emailEditText = view.findViewById(R.id.email_edittext);
        ageEditText = view.findViewById(R.id.age_edittext);
        saveButton = view.findViewById(R.id.save_button);

        // Populates EditText fields with user's current information
        populateUserInfo();

        // Sets click listener for save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        return view;
    }

    // Method to populate EditText fields with user current information
    private void populateUserInfo() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            // Retrieve user data from Firestore
            mFirestore.collection("Users").document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String currentName = documentSnapshot.getString("FullName");
                            String currentEmail = documentSnapshot.getString("UserEmail");
                            String currentAge = documentSnapshot.getString("Age");

                            // Populates EditText fields with current information
                            nameEditText.setText(currentName);
                            emailEditText.setText(currentEmail);
                            ageEditText.setText(currentAge);
                        } else {
                            // Handles the case where user data doesn't exist
                            Toast.makeText(getActivity(), "User data not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Handles errors
                        Toast.makeText(getActivity(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private void saveProfile() {
        // Gets the modified profile data
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();

        // Validates if any modification has been made
        if (!name.isEmpty() || !email.isEmpty() || !age.isEmpty()) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                String userId = currentUser.getUid();
                DocumentReference userRef = mFirestore.collection("Users").document(userId);

                // Creates a map to store the updated data
                Map<String, Object> updatedData = new HashMap<>();
                if (!name.isEmpty()) {
                    updatedData.put("FullName", name);
                }
                if (!email.isEmpty()) {
                    updatedData.put("UserEmail", email);
                }
                if (!age.isEmpty()) {
                    updatedData.put("Age", age);
                }

                // Performs the update operation
                userRef.update(updatedData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getActivity(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                        });
            }
        } else {
            // No modification made

            // Displays a message to the user
            Toast.makeText(getActivity(), "Edit your profile first", Toast.LENGTH_SHORT).show();
        }
    }

}
