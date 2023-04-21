package com.example.healthcare;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentDAO {

    @Insert
    void insert(Appointment appointment);

    @Query("DELETE FROM appointment_table")
    void deleteAll();
    //@Delete
    //void delete(Appointment appointment);

    @Query("SELECT * FROM appointment_table ORDER BY user_id ASC")
    LiveData<List<Appointment>> getAppointments();

}
