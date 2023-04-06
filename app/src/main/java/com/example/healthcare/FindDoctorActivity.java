package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit = findViewById(R.id.cardBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });

        CardView childDoctor = findViewById(R.id.cardChildDoctor);
        childDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent it =  new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
               it.putExtra("title", "Gyermek orvos");
               startActivity(it);
            }
        });
        CardView dietecian = findViewById(R.id.cardDietician);
        dietecian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it =  new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Dietetikus");
                startActivity(it);
            }
        });
        CardView cardiologist = findViewById(R.id.cardCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it =  new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Kardiológus");
                startActivity(it);
            }
        });
        CardView dentist = findViewById(R.id.cardDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it =  new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Fogorvos");
                startActivity(it);
            }
        });
        CardView surgeon = findViewById(R.id.cardSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it =  new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Sebész");
                startActivity(it);
            }
        });
    }
}