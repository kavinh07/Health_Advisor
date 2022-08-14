package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
    }

    public void openPatientHome(View view) {
        startActivity(new Intent(PatientFormActivity.this, PatientHomePage.class));
    }
}