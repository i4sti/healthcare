package com.example.healthcare;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.UUID;

public class AppointmentViewModel extends AndroidViewModel {
    private AppointmentRepository repository;
    private LiveData<List<Appointment>> appointments;
    private FirebaseFirestore db;
    private CollectionReference appointmentRef;



    public  AppointmentViewModel(Application application){
        super(application);
        this.repository = new AppointmentRepository(application);
        this.appointments = repository.getAllAppointments();
        db = FirebaseFirestore.getInstance();

        // Az "appointments" kollekció referenciájának inicializálása
        appointmentRef = db.collection("appointments");

    }
    public LiveData<List<Appointment>> getAllAppointments(){
        return this.appointments;
    }

    @SuppressLint("StaticFieldLeak")

    public void insert(Appointment appointment){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // Firestore-on mentés
                appointmentRef.add(appointment)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

                return null;
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    public void update(Appointment appointment){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // Firestore-on frissítés
                appointmentRef.document(String.valueOf(appointment.getId()))
                        .set(appointment)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });

                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void delete(Appointment appointment){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // Firestore-on törlés
                appointmentRef.document(String.valueOf(appointment.getId()))
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });


                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void read(Appointment appointment){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // Firestore-on lekérdezés
                appointmentRef.document(String.valueOf(appointment.getId()))
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Appointment updatedAppointment = documentSnapshot.toObject(Appointment.class);
                                    updatedAppointment.setId(documentSnapshot.getId());
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Error getting document:", e);
                            }
                        });
                return null;
            }
        }.execute();
    }




}
