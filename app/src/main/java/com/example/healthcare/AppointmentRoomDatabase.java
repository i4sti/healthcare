package com.example.healthcare;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Appointment.class}, version = 2, exportSchema = false)
public abstract class AppointmentRoomDatabase extends RoomDatabase {

    public abstract AppointmentDAO appointmentDAO();

    private static volatile AppointmentRoomDatabase INSTANCE;

    public static AppointmentRoomDatabase getInstance( final Context context){
        if (INSTANCE == null){
            synchronized (AppointmentRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), AppointmentRoomDatabase.class,
                            "appointmnet_database").fallbackToDestructiveMigration().
                            addCallback(populationCallback).build();
                }
            }
        }

        return  INSTANCE;
    }

    private static RoomDatabase.Callback populationCallback = new Callback() {
        public void onOpen(@NonNull SupportSQLiteDatabase db){

        }
    };

//    private static class initDb extends AsyncTask<Void,Void,Void>{
//        private AppointmentDAO dao;
//        Appointment appointment1 = new Appointment("John Smith", "123 Main St", "555-1234", "2023-05-01", "10:00 AM", "user123");
//        Appointment appointment2 = new Appointment("Jane Doe", "456 Oak Ave", "555-5678", "2023-05-02", "2:30 PM", "user456");
//
//        Appointment[] appointments = {appointment1,appointment2};
//
//        initDb(AppointmentRoomDatabase db){
//            this.dao = db.appointmentDAO();
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            this.dao.deleteAll();
//
//            for (int i = 0; i < appointments.length; i++) {
//                this.dao.insert(new Appointment(appointments[i]));
//            }
//            return null;
//        }
//    }

}
