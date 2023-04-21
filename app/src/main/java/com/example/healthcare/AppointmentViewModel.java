package com.example.healthcare;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppointmentViewModel extends AndroidViewModel {
    private AppointmentRepository repository;
    private LiveData<List<Appointment>> appointments;

    public  AppointmentViewModel(Application application){
        super(application);
        this.repository = new AppointmentRepository(application);
        this.appointments = repository.getAllAppointments();

    }
    public LiveData<List<Appointment>> getAllAppointments(){
        return this.appointments;
    }

    public void insert(Appointment appointment){
        this.repository.insert(appointment);
    }



}
