package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;

public class RegisterActivity extends AppCompatActivity {

    EditText edRegUserName, edRegEmail, edRegPassword, edRegRePassword;
    Button btn;
    TextView tv;

    private static final String LOG_TAG = RegisterActivity.class.getName();
    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edRegUserName.getText().toString();
                String email = edRegEmail.getText().toString();
                String password = edRegPassword.getText().toString();
                String rePassword = edRegRePassword.getText().toString();

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || rePassword.length() == 0){
                    Toast.makeText(getApplicationContext(),"Tölstd ki a mezőket!", Toast.LENGTH_SHORT).show();
                } else if (password.compareTo(rePassword) != 0 && password.length()>=6){
                    Toast.makeText(getApplicationContext(),"Nem egyezik meg a két jelszó vagy nem elég hosszú", Toast.LENGTH_SHORT).show();

                } else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.d(LOG_TAG, "Sikeres regisztráció");
                                //átlépés a dologra
                            } else {
                                Log.d(LOG_TAG,"Nem sikeres");
                                Toast.makeText(RegisterActivity.this, "Nem sikeres regisztráció:" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

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