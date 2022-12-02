package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Register extends AppCompatActivity {
    EditText mail,password,confirmPassword;
    Button register;
    FirebaseAuth mAuth;
    ProgressBar progressbar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        register=findViewById(R.id.button);
        progressbar=findViewById(R.id.progressBar2);
        confirmPassword=findViewById(R.id.editTextTextPassword2);

        mAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                String Email=mail.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String secondPass=confirmPassword.getText().toString().trim();

                if (!Password.equals(secondPass)){
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Email.isEmpty()) {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                    return;
                }
                if (Password.isEmpty()) {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(getApplicationContext(),Landing_page.class);
                            startActivity(intent);
                            progressbar.setVisibility(View.GONE);
                        }
                        else
                        {
                            String error= Objects.requireNonNull(task.getException()).getMessage();
                            Toast.makeText(Register.this, "Signup Failed "+error, Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }


    public void register(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}