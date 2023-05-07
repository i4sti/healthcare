package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private  AppointmentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

//        List<Doctor> doctors = MyApp.db.doctorDao().getAll();
//        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
//        for (Doctor doctor : doctors) {
//            Map<String, String> item = new HashMap<String, String>();
//            item.put("line1", doctor.fullName);
//            item.put("line2", doctor.address);
//            item.put("line3", doctor.phoneNumber);
//            item.put("line4", doctor.specialization);
//            data.add(item);
//        }
//
//        // Adapter inicializálása
//        sa = new SimpleAdapter(this, data, R.layout.multi_lines,
//                new String[]{"line1", "line2", "line3", "line4

        viewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);
        viewModel.getAllAppointments().observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {

            }
        });
    }
}