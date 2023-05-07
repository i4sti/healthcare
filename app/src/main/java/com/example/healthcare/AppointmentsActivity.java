package com.example.healthcare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private  AppointmentViewModel viewModel;
    private FirebaseFirestore db;
    private ListenerRegistration listenerRegistration;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String userId = currentUser.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        db = FirebaseFirestore.getInstance();

        viewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);
        viewModel.getAllAppointments().observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {

            }
        });
        db.collection("appointments")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Appointment> appointments = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Appointment appointment = document.toObject(Appointment.class);
                            appointments.add(appointment);
                        }
                        // Get a reference to the ListView in the XML layout
                        ListView listView = findViewById(R.id.listViewAppointments);

                        // Create an ArrayAdapter to populate the ListView with the appointment data
                        ArrayAdapter<Appointment> adapter = new ArrayAdapter<Appointment>(AppointmentsActivity.this, R.layout.activity_appointments, appointments) {
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                if (convertView == null) {
                                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.appointment_item, parent, false);
                                }

                                TextView textViewDateTime = convertView.findViewById(R.id.textViewDateTime);
                                TextView textViewName = convertView.findViewById(R.id.textViewName);
                                TextView textViewLocation = convertView.findViewById(R.id.textViewLocation);
                                TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
                                ImageButton btnDelete = convertView.findViewById(R.id.btnDelete);


                                Appointment appointment = getItem(position);

                                textViewDateTime.setText(appointment.getDate());
                                String input = appointment.getFullName();
                                String[] parts = input.split(":");
                                String name = parts[1].trim();
                                textViewName.setText(name);
                                input = appointment.getAddress();
                                parts = input.split(":");
                                String address = parts[1].trim();
                                textViewLocation.setText(address);
                                input = appointment.getPhoneNumber();
                                parts = input.split(":");
                                String phone = parts[1].trim();
                                textViewPhone.setText(phone);

                                // Set OnClickListener for the delete button
                                btnDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        onDeleteAppointment(appointment);
                                    }
                                });

                                return convertView;
                            }
                        };

                        // Set the adapter for the ListView
// Get a reference to the ListView in the XML layout


// Set the adapter for the ListView
                        listView.setAdapter(adapter);

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });




    }




    public void onDeleteAppointment(Appointment appointment) {
        viewModel.delete(appointment);
    }


    protected void onPause() {
        super.onPause();

        // Detach listener from Firestore database to save resources
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reattach listener to Firestore database
        listenerRegistration = db.collection("appointments")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot querySnapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, "Error querying Firestore", e);
                            return;
                        }

                        // Handle data from Firestore snapshot
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                    }
                });
    }
}