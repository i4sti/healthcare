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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edPassword;
    Button btn;
    TextView tv;

    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final int RC_SIGN_IN = 123;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.EditTextEmail);
        edPassword = findViewById(R.id.EditTextPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();

                if(email.length() == 0 || password.length() == 0){
                    Toast.makeText(getApplicationContext(),"Tölstd ki a mezőket.", Toast.LENGTH_SHORT).show();
                } else{
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.d(LOG_TAG,"Sikeres");

                                Toast.makeText(LoginActivity.this, " sikeres bejelentkezés" , Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }else {
                                Log.d(LOG_TAG,"Nem sikeres");
                                Toast.makeText(LoginActivity.this, "Nem sikeres bejelentkezés:" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


    }

    public void loginAsGuest(View view) {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Sikeres anonim bejelentkezés", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                }else {
                    Toast.makeText(LoginActivity.this, "Nem sikeres bejelentkezés:" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}