package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private static final String LOG_TAG = HomeActivity.class.getName();

    private FirebaseUser user;

    private FirebaseAuth mAuth;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        // mAuth.signOut();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }
        CardView exit = findViewById(R.id.cardLogout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });
        CardView findDoctor = findViewById(R.id.findDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });

        CardView appointments = findViewById(R.id.cardAppointment);

        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AppointmentsActivity.class));
            }
        });
    }
}