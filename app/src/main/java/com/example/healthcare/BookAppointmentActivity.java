package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.UUID;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Button dateButton, timeButton, bookAppointmentButton, backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.EditTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.EditTextAppPhoneNumber);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        bookAppointmentButton = findViewById(R.id.buttonBookAppointment);
        backButton = findViewById(R.id.buttonBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String[] parts = address.split(",");
        String result = parts[0];
        String contact = it.getStringExtra("text4");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(result);
        ed3.setText(contact);


        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
//      timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        bookAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // beállítjuk az appointment adatait a formon megadott értékekre
                Appointment appointment = new Appointment();
                appointment.setId(UUID.randomUUID().toString());

                appointment.setFullName(ed1.getText().toString());
                appointment.setAddress(ed2.getText().toString());
                appointment.setPhoneNumber(ed3.getText().toString());
                appointment.setDate(dateButton.getText().toString());
                appointment.setTime(timeButton.getText().toString());


                // hozzáférés az adatbázishoz az AppointmentViewModel segítségével
                AppointmentViewModel appointmentViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AppointmentViewModel.class);


//

                if (currentUser != null) {
                    String email = currentUser.getEmail();
                    if (email != null) {
                        // a felhasználó be van jelentkezve, és van érvényes email címe
                        String userId = currentUser.getUid();
                        // Az userId értékét beállítjuk az "appointment_table" tábla "user_id" mezőjére
                        appointment.setUserId(userId);
                        // beszúrás az adatbázisba
                        appointmentViewModel.insert(appointment);
// NotificationManager inicializálása
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// NotificationChannel létrehozása a NotificationManager-ben
                        String channelId = "my_channel_id";
                        CharSequence channelName = "My Channel";
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
                        notificationManager.createNotificationChannel(notificationChannel);

// Notification létrehozása
                        String notificationTitle = "Sikeres időpont foglalás";
                        String notificationText = "Az időpont foglalás sikeres volt";
                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(BookAppointmentActivity.this, channelId)
                                .setSmallIcon(R.drawable.info)
                                .setContentTitle(notificationTitle)
                                .setContentText(notificationText)
                                .setAutoCancel(true)
                                .setPriority(NotificationCompat.PRIORITY_HIGH);

// Notification elküldése
                        notificationManager.notify(0, notificationBuilder.build());
                        // visszalépés az előző képernyőre
                        startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
                    } else {
                        Toast.makeText(BookAppointmentActivity.this, "Nem lehet időpontot foglalni vendégként, vagy valami nem sikerült", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookAppointmentActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    // Az időpontfoglalás nem engedélyezett, mert a felhasználó nincs bejelentkezve
                    Toast.makeText(BookAppointmentActivity.this, "A foglaláshoz be kell jelentkezni!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookAppointmentActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }



            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_in_up);

            }
        });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                dateButton.setText(i+"/"+i1+"/"+i2);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);


    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);


        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);



    }
}