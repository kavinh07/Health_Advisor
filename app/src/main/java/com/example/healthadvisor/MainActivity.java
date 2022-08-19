package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passEditText;
    private Button logInBtn;
    private TextView forgetPass, createAccount;
    FirebaseAuth mAuth;
    DatabaseReference dR;

    FirebaseUser person;
    int userType;

    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEditText = findViewById(R.id.username);
        passEditText = findViewById(R.id.password);
        logInBtn = findViewById(R.id.logInBtn);
        forgetPass = findViewById(R.id.forgotPass);
        createAccount = findViewById(R.id.signUp);
        proBar = findViewById(R.id.proBar);
        mAuth = FirebaseAuth.getInstance();

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Set New Password", Toast.LENGTH_SHORT).show();

            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Please Sign Up", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, RegTypeActivity.class));
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInUser();
                proBar.setVisibility(View.VISIBLE);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        proBar.setVisibility(View.INVISIBLE);
                    }
                },4000);
            }
        });
    }

    private void LogInUser() {
        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!password.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                checkUserType();
                                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                passEditText.setError("Error");
                                passEditText.requestFocus();
                            }
                        });

            } else {
                passEditText.setError("Empty Fields Not Allowed");
            }
        } else if (email.isEmpty()) {
            emailEditText.setError("Empty Fields Are Not Allowed");
        } else {
            emailEditText.setError("Please Enter Correct Email");
        }
    }

    private void checkUserType() {
        person = FirebaseAuth.getInstance().getCurrentUser();
        String UID= person.getUid();

        dR = FirebaseDatabase.getInstance().getReference("Person");
        dR.child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        startActivity(new Intent(MainActivity.this, PatientHomePage.class));
                    }
                }
            }
        });
        dR = FirebaseDatabase.getInstance().getReference("Doctor");
        dR.child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        startActivity(new Intent(MainActivity.this, DoctorHomePage.class));
                    }
                }
            }
        });
    }


}