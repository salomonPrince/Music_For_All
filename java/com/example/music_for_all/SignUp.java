package com.example.music_for_all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextconfirmPassword, editTextFullname, editTextAge ;
    Button signUp;
    TextView signIn;
    FirebaseFirestore fStore;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initializes Firebase
        FirebaseApp.initializeApp(this);
        fStore = FirebaseFirestore.getInstance();

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextconfirmPassword = findViewById(R.id.cpassword);
        editTextFullname = findViewById(R.id.fullname);
        editTextAge = findViewById(R.id.age);
        signIn = findViewById(R.id.Sign_in);
        signUp = findViewById(R.id.Sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        });


      signUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String email, password, fullname, cpassword, age ;
              email = String.valueOf(editTextEmail.getText());
              password = String.valueOf(editTextPassword.getText());
              fullname = String.valueOf(editTextFullname.getText());
              cpassword = String.valueOf(editTextconfirmPassword.getText());
              age = String.valueOf(editTextAge.getText());

              if(TextUtils.isEmpty(email)){
                  Toast.makeText(SignUp.this,"Enter Email", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(TextUtils.isEmpty(password)){
                  Toast.makeText(SignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(TextUtils.isEmpty(cpassword)){
                  Toast.makeText(SignUp.this, "Enter Confirmation Password", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(TextUtils.isEmpty(fullname)){
                  Toast.makeText(SignUp.this, "Enter Fullname", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(TextUtils.isEmpty(age)){
                  Toast.makeText(SignUp.this, "Enter Age", Toast.LENGTH_SHORT).show();
                  return;
              }

              if (password.length()<8){
                  editTextPassword.setError("Password should be more than 8 char");
                  editTextPassword.requestFocus();
                  return;
              }

              if(!password.equals(cpassword)){
                  editTextconfirmPassword.setError("Password and Confirmation is not match");
                  editTextconfirmPassword.requestFocus();
                  return;
              }

              if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                  editTextEmail.setError("Please Put the valid Email address");
                  editTextEmail.requestFocus();
                  return;
              }

              firebaseAuth.createUserWithEmailAndPassword(email,password)
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful()){
                                  FirebaseUser user = firebaseAuth.getCurrentUser();
                                  Toast.makeText(SignUp.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                  DocumentReference df = fStore.collection("Users").document(user.getUid());
                                  Map<String,Object> userInfo = new HashMap<>();
                                  userInfo.put("FullName",fullname);
                                  userInfo.put("UserEmail",email);
                                  userInfo.put("Age",age);
                                  // specify if the user is admin
                                  userInfo.put("isUser","1");

                                  df.set(userInfo);

                                  Intent intent = new Intent(SignUp.this, SignIn.class);
                                  intent.putExtra("name",fullname);
                                  intent.putExtra("email",email);

                                  startActivity(intent);
                                  finish();
                              }
                              else {
                                  Toast.makeText(SignUp.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
          }
      });
    }
}