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

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonBackButton;
    private Button buttonNextButton;
    private TextInputLayout inputLayoutEmail;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        inputLayoutEmail = findViewById(R.id.forgot_email);
        buttonBackButton = findViewById(R.id.forgot_back_button);
        buttonNextButton = findViewById(R.id.forgot_next_button);

        buttonNextButton.setOnClickListener(this);
        buttonBackButton.setOnClickListener(this);
    }

    private void sendPasswordReset() {
        String email = inputLayoutEmail.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Sending password reset email...");
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(email).
                addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(ForgotPassword.this,
                            "Password reset email sent!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(ForgotPassword.this,
                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonBackButton){
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        if (v == buttonNextButton) {
            sendPasswordReset();
        }
    }
}