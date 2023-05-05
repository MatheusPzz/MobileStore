package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText email, password;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailEt);
        password = findViewById(R.id.passwordTv);

        sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

        if(isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, OnBorderActivity.class);
        }
    }


    public void signIn(View view) {

        String userEmail = email.getText().toString().trim();
        String userPass = password.getText().toString().trim();

        if (TextUtils.isEmpty(userEmail)) {

            Toast.makeText(this, "Enter Email Address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPass)) {

            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPass.length() < 6) {
            Toast.makeText(this, "Password too short, minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, OnBorderActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong Login Details..."+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    public void signUp (View view){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}