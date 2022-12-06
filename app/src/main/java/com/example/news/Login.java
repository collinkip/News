package com.example.news;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText mail,password;
    Button login;
    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail =findViewById(R.id.editTextTextEmailAddress);
        password =findViewById(R.id.editTextTextPassword);
        login=findViewById(R.id.button);
        progressBar=findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();

        FirebaseFirestore db=FirebaseFirestore.getInstance();

        login.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String Email = mail.getText().toString().trim();
            String Password = password.getText().toString().trim();
            if (Email.isEmpty()) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                return;
            }
            if (Password.isEmpty()) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "Log in success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Landing_page.class);
                    startActivity(intent);
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "Error ! \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

    }


    public void register(View view) {
        Intent intent= new Intent(this,Register.class);
        startActivity(intent);
    }
}