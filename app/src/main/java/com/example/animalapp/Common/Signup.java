package com.example.animalapp.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.time.format.DateTimeFormatter;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignupButton;
    private ImageButton imageButtonBackButton;
    private TextInputLayout inputLayoutFirstName, inputLayoutLastName, inputLayoutUsername, inputLayoutEmail,
            inputLayoutPhoneNumber, inputLayoutUTAID, inputLayoutPassword1, inputLayoutPassword2, inputLayoutBirthday;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    String firstName, lastName, username, email, date,
            phoneNumber, utaID, password1, password2, UID, birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonSignupButton = findViewById(R.id.signup_signup_button);
        imageButtonBackButton = findViewById(R.id.signup_back_button);

        inputLayoutFirstName = findViewById(R.id.signup_first_name);
        inputLayoutLastName = findViewById(R.id.signup_last_name);
        inputLayoutUsername = findViewById(R.id.signup_username);
        inputLayoutEmail = findViewById(R.id.signup_email);
        inputLayoutPhoneNumber = findViewById(R.id.signup_phone_number);
        inputLayoutUTAID = findViewById(R.id.signup_uta_id);
        inputLayoutBirthday = findViewById(R.id.signup_birthday);
        inputLayoutPassword1 = findViewById(R.id.signup_password_1);
        inputLayoutPassword2 = findViewById(R.id.signup_password_2);

        buttonSignupButton.setOnClickListener(this);
        imageButtonBackButton.setOnClickListener(this);

    }

    private void registerUser() {
        firstName = inputLayoutFirstName.getEditText().getText().toString().trim();
        lastName = inputLayoutLastName.getEditText().getText().toString().trim();
        username = inputLayoutUsername.getEditText().getText().toString().trim();
        email = inputLayoutEmail.getEditText().getText().toString().trim();
        birthday = inputLayoutBirthday.getEditText().getText().toString().trim();
        phoneNumber = inputLayoutPhoneNumber.getEditText().getText().toString().trim();
        utaID = inputLayoutUTAID.getEditText().getText().toString().trim();
        password1 = inputLayoutPassword1.getEditText().getText().toString().trim();
        password2 = inputLayoutPassword2.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(username) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(utaID) || TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "Please fill out all information", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password1.equals(password2)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()) {
                           progressDialog.dismiss();

                           storeNewUserData();

                           Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(), Dashboard.class));
                       } else {
                           progressDialog.dismiss();
                           FirebaseAuthException e = (FirebaseAuthException )task.getException();
                           Toast.makeText(Signup.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                           return;
                       }
                    }
                });
    }

    private void storeNewUserData() {
        DatabaseReference reference = firebaseDatabase.getReference("Users");

        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        UserHelperClass addNewUser = new UserHelperClass(firstName, lastName, username,
                email, date, phoneNumber, utaID, password1, UID, birthday);

        reference.child(username).setValue(addNewUser);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSignupButton) {
            registerUser();
        }

        if (v == imageButtonBackButton) {
            startActivity(new Intent(getApplicationContext(), SplashScreen.class));
        }
    }
}