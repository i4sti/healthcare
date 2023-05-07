package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doktor neve: Dr. Kovács Anna", "Korház címe: Budapest, XI. kerület, Kelenföldi út 27.", "Tapasztalat: 10 év", "Telefonszám: +36 30 123 4567"},
            {"Doktor neve: Dr. Nagy Gábor", "Korház címe: Szeged, Tisza Lajos krt. 55.", "Tapasztalat: 7 év", "Telefonszám: +36 62 123 4567"},
            {"Doktor neve: Dr. Szabó Mária", "Korház címe: Debrecen, Móricz Zsigmond körtér 2.", "Tapasztalat: 12 év", "Telefonszám: +36 52 123 4567"},
            {"Doktor neve: Dr. Horváth István", "Korház címe: Pécs, Rákóczi út 2.", "Tapasztalat: 5 év", "Telefonszám: +36 72 123 4567"},
            {"Doktor neve: Dr. Kiss János", "Korház címe: Győr, Hunyadi u. 4.", "Tapasztalat: 8 év", "Telefonszám: +36 96 123 4567"}
    };
    private String[][] doctor_details2 = {
            {"Doktor neve: Dr. Székely Eszter", "Korház címe: Székesfehérvár, Budai út 2.", "Tapasztalat: 6 év", "Telefonszám: +36 22 123 4567"},
            {"Doktor neve: Dr. Kovács Gergő", "Korház címe: Miskolc, Rákóczi u. 2.", "Tapasztalat: 9 év", "Telefonszám: +36 46 123 4567"},
            {"Doktor neve: Dr. Farkas Péter", "Korház címe: Kecskemét, Budai u. 1.", "Tapasztalat: 11 év", "Telefonszám: +36 76 123 4567"},
            {"Doktor neve: Dr. Szabó Ferenc", "Korház címe: Szolnok, Tiszaligeti sétány 1.", "Tapasztalat: 7 év", "Telefonszám: +36 56 123 4567"},
            {"Doktor neve: Dr. Kovácsné Bognár Katalin", "Korház címe: Békéscsaba, Kossuth tér 1.", "Tapasztalat: 4 év", "Telefonszám: +36 66 123 4567"}
    };
    private String[][] doctor_details3 = {
            {"Doktor neve: Dr. Nagy Kálmán", "Korház címe: Pécs, József Attila u. 1.", "Tapasztalat: 8 év", "Telefonszám: +36 72 123 4567"},
            {"Doktor neve: Dr. Tóth László", "Korház címe: Szeged, Kálvária sgt. 57.", "Tapasztalat: 13 év", "Telefonszám: +36 62 123 4567"},
            {"Doktor neve: Dr. Kissné Kovács Éva", "Korház címe: Debrecen, Egyetem tér 1.", "Tapasztalat: 6 év", "Telefonszám: +36 52 123 4567"},
            {"Doktor neve: Dr. Szabó Gábor", "Korház címe: Miskolc, Szentpáli u. 1.", "Tapasztalat: 9 év", "Telefonszám: +36 46 123 4567"},
            {"Doktor neve: Dr. Kovács Zsuzsanna", "Korház címe: Budapest, XIV. kerület, Rákospatak u. 7.", "Tapasztalat: 5 év", "Telefonszám: +36 1 123 4567"}
    };
    private String[][] doctor_details4 = {
            {"Doktor neve: Dr. Nagy Tibor", "Korház címe: Székesfehérvár, Várkörút 1.", "Tapasztalat: 7 év", "Telefonszám: +36 22 123 4567"},
            {"Doktor neve: Dr. Kovács István", "Korház címe: Kecskemét, Budai u. 2.", "Tapasztalat: 10 év", "Telefonszám: +36 76 123 4567"},
            {"Doktor neve: Dr. Szabóné Nagy Marianna", "Korház címe: Békéscsaba, Jókai u. 1.", "Tapasztalat: 5 év", "Telefonszám: +36 66 123 4567"},
            {"Doktor neve: Dr. Kiss Péter", "Korház címe: Győr, Árpád út 1.", "Tapasztalat: 11 év", "Telefonszám: +36 96 123 4567"},
            {"Doktor neve: Dr. Székely Tamás", "Korház címe: Szolnok, Rákóczi út 1.", "Tapasztalat: 6 év", "Telefonszám: +36 56 123 4567"}
    };
    private String[][] doctor_details5 = {
            {"Doktor neve: Dr. Szabó Mária", "Korház címe: Szeged, Gutenberg u. 12.", "Tapasztalat: 7 év", "Telefonszám: +36 62 123 4567"},
            {"Doktor neve: Dr. Kovács Gergő", "Korház címe: Pécs, Mária u. 6.", "Tapasztalat: 10 év", "Telefonszám: +36 72 123 4567"},
            {"Doktor neve: Dr. Nagyné Kovács Katalin", "Korház címe: Debrecen, Piac u. 1.", "Tapasztalat: 6 év", "Telefonszám: +36 52 123 4567"},
            {"Doktor neve: Dr. Tóth Péter", "Korház címe: Miskolc, Rákóczi u. 1.", "Tapasztalat: 9 év", "Telefonszám: +36 46 123 4567"},
            {"Doktor neve: Dr. Kiss Gábor", "Korház címe: Budapest, VIII. kerület, József krt. 9.", "Tapasztalat: 5 év", "Telefonszám: +36 1 123 4567"}
    };




    TextView tv;
    Button btn;
    String[][] doctor_detail = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        btn = findViewById(R.id.backButton);
        if (title.compareTo("Gyermek orvos") == 0)
            doctor_detail = doctor_details1;
        else
        if (title.compareTo("Dietetikus") == 0)
            doctor_detail = doctor_details2;
        else
        if (title.compareTo("Kardiológus") == 0)
            doctor_detail = doctor_details3;
        else
        if (title.compareTo("Fogorvos") == 0)
            doctor_detail = doctor_details4;
        else
        if (title.compareTo("Sebész") == 0)
            doctor_detail = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
        }

        });

        list = new ArrayList();
        for (int i = 0; i < doctor_detail.length; i++) {
            item = new HashMap<String,String>();
            item.put("line1", doctor_detail[i][0]);
            item.put("line2", doctor_detail[i][1]);
            item.put("line3", doctor_detail[i][2]);
            item.put("line4", doctor_detail[i][3]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d});
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] parts = doctor_detail[i][1].split(",");
                String result = parts[0];
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_detail[i][0]);
                it.putExtra("text3",doctor_detail[i][1]);
                it.putExtra("text4",doctor_detail[i][3]);

                startActivity(it);
//                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_in_down);

            }
        });
    }

}