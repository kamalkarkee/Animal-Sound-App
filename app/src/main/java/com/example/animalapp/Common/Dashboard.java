package com.example.animalapp.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.animalapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    // Getting user info for profile page
    private void getUserInfo() {
        // Get user's email for database reference
        String email = currentUser.getEmail();

        // get database reference for current user
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("email").equalTo(email);

        /* THIS IS WHERE WE GET DATABASE INFO */
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // create a new variable and use similar calls to get all info in database
                /* Already have email */
                /* TODO: figure out how to get these variables to the profile page and display them */
                String firstName = snapshot.child(email).child("firstName").getValue(String.class);
                String lastName = snapshot.child(email).child("lastName").getValue(String.class);
                String phoneNumber = snapshot.child(email).child("phoneNumber").getValue(String.class);
                String utaID = snapshot.child(email).child("utaID").getValue(String.class);
                String birthday = snapshot.child(email).child("birthday").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.dashboard_stats_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Statistics.class));
            }
        });

        findViewById(R.id.dashboard_model_info_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ModelInfo.class));
            }
        });

        findViewById(R.id.dashboard_profile_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });

        findViewById(R.id.dashboard_classify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Classification_Modules.class));
            }
        });

        findViewById(R.id.dashboard_logout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Dashboard.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SplashScreen.class));
            }
        });
    }


}