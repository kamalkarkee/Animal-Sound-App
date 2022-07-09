package com.example.animalapp.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.animalapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLoginButton, buttonSignupButton, buttonForgotButton;
    private ImageButton buttonBackButton;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonBackButton = findViewById(R.id.login_back_button);
        buttonLoginButton = findViewById(R.id.login_login_button);
        buttonSignupButton = findViewById(R.id.login_signup_button);
        buttonForgotButton = findViewById(R.id.login_forgot_button);
        inputLayoutEmail = findViewById(R.id.login_email);
        inputLayoutPassword = findViewById(R.id.login_password);

        buttonBackButton.setOnClickListener(this);
        buttonLoginButton.setOnClickListener(this);
        buttonSignupButton.setOnClickListener(this);
        buttonForgotButton.setOnClickListener(this);
    }

    private void signInUser() {
        String email = inputLayoutEmail.getEditText().getText().toString().trim();
        String password = inputLayoutPassword.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        } else {
                            progressDialog.dismiss();
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast.makeText(Login.this, "Login Failed: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonBackButton) {
            startActivity(new Intent(getApplicationContext(), SplashScreen.class));
        }

        if (v == buttonSignupButton) {
            startActivity(new Intent(getApplicationContext(), Signup.class));
        }

        if (v == buttonForgotButton) {
            startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
        }

        if (v == buttonLoginButton) {
            signInUser();
        }
    }
}