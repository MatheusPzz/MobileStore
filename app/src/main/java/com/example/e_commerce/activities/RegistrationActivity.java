package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText name,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.Uname);
        email = findViewById(R.id.emailEt);
        password = findViewById(R.id.passwordTv);

    }

    public void signUp (View view){

        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPass = password.getText().toString().trim();

        if (TextUtils.isEmpty(userName)){

            Toast.makeText(this, "Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){

            Toast.makeText(this, "Enter Email Address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPass)){

            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPass.length() <6) {
            Toast.makeText(this, "Password too short, minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Creating an account...", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        }else{
                            Toast.makeText(RegistrationActivity.this, "Regristration Failed..."+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signIn(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}