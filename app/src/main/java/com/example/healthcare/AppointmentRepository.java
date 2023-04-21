package com.example.healthcare;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppointmentRepository {
    private AppointmentDAO dao;
    private LiveData<List<Appointment>> appointments;

    AppointmentRepository(Application application){
        AppointmentRoomDatabase db = AppointmentRoomDatabase.getInstance(application);
        dao = db.appointmentDAO();
        appointments = dao.getAppointments();
    }

    public LiveData<List<Appointment>> getAllAppointments(){
        return appointments;
    }

    public void insert(Appointment appointment){
        new insert(this.dao).execute(appointment);
    }

    private static class  insert extends AsyncTask<Appointment,Void,Void> {
        private AppointmentDAO mDAO;

        public insert(AppointmentDAO dao) {
            this.mDAO = dao;
        }

        @Override
        protected Void doInBackground(Appointment... appointments) {
            mDAO.insert(appointments[0]);
            return null;
        }
    }

}
