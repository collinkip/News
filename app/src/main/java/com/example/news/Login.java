package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();


    }
    public void login(View view) {
        Intent intent= new Intent(this,Login.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent= new Intent(this,Register.class);
        startActivity(intent);
    }

    public void Landing(View view) {
        Intent intent= new Intent(this,Landing_page.class);
        startActivity(intent);
    }
}