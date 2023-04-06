package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edRegUserName, edRegEmail, edRegPassword, edRegRePassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edRegUserName = findViewById(R.id.EditTextRegUsername);
        edRegEmail = findViewById(R.id.editTextRegEmail);
        edRegPassword = findViewById(R.id.EditTextRegPassword);
        edRegRePassword = findViewById(R.id.editTextRegRePassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edRegUserName.getText().toString();
                String email = edRegEmail.getText().toString();
                String password = edRegPassword.getText().toString();
                String rePassword = edRegRePassword.getText().toString();

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || rePassword.length() == 0){
                    Toast.makeText(getApplicationContext(),"Tölstd ki a mezőket!", Toast.LENGTH_SHORT).show();
                } else if (password.compareTo(rePassword) == 0){
                    Toast.makeText(getApplicationContext(),"Nem egyezik meg a két jelszó.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),"Sikeres regisztráció.", Toast.LENGTH_SHORT).show();

                }


            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}