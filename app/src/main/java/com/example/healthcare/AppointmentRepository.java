package com.example.healthcare;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AppointmentRepository {
    private AppointmentDAO dao;
    private LiveData<List<Appointment>> appointments;
    private FirebaseFirestore dbfs;
    private CollectionReference appointmentsRef;

    public AppointmentRepository(Application application){
        // Room adatbázis inicializálása
        AppointmentRoomDatabase db = AppointmentRoomDatabase.getInstance(application);
        dao = db.appointmentDAO();
        appointments = dao.getAppointments();

        // Firestore inicializálása
        dbfs = FirebaseFirestore.getInstance();
        appointmentsRef = dbfs.collection("appointments");
    }

    public LiveData<List<Appointment>> getAllAppointments(){
       return dao.getAppointments();
    }

    public void insert(Appointment appointment){
//        new insert(this.dao).execute(appointment);
        new insertAsyncTask(dao, appointmentsRef).execute(appointment);
    }

    public void update(Appointment appointment) {
        new updateAsyncTask(dao, appointment).execute();
    }

    public void delete(Appointment appointment) {
        new deleteAsyncTask(dao, appointment).execute();
    }

    private static class updateAsyncTask extends AsyncTask<Void, Void, Void> {
        private AppointmentDAO mAsyncTaskDao;
        private Appointment mAppointment;

        updateAsyncTask(AppointmentDAO dao, Appointment appointment) {
            mAsyncTaskDao = dao;
            mAppointment = appointment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.update(mAppointment);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private AppointmentDAO mAsyncTaskDao;
        private Appointment mAppointment;

        deleteAsyncTask(AppointmentDAO dao, Appointment appointment) {
            mAsyncTaskDao = dao;
            mAppointment = appointment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.delete(mAppointment);
            return null;
        }
    }




    private static class insertAsyncTask extends AsyncTask<Appointment, Void, Void> {
        private AppointmentDAO mAsyncTaskDao;
        private CollectionReference mFirestoreRef;

        insertAsyncTask(AppointmentDAO dao, CollectionReference ref) {
            mAsyncTaskDao = dao;
            mFirestoreRef = ref;
        }

        @Override
        protected Void doInBackground(final Appointment... params) {
            // Helyi adatbázisba mentés
            mAsyncTaskDao.insert(params[0]);

            // Firestore adatbázisba mentés
            mFirestoreRef.add(params[0]);

            return null;
        }
    }



}
