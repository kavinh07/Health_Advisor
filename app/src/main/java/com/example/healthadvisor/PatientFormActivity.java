package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientFormActivity extends AppCompatActivity {
    private EditText enterName, enterAddress, enterPhoneNo, enterAge, enterBloodGroup;
    private RadioGroup genderRadio;
    private Button next;
    String gender;
    FirebaseAuth mAuth;
    DatabaseReference dR;

    String pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
        enterName = findViewById(R.id.nametxt);
        enterAddress = findViewById(R.id.addressTxt);
        enterPhoneNo = findViewById(R.id.phoneTxt);
        enterAge = findViewById(R.id.agetxt);
        enterBloodGroup = findViewById(R.id.bloodtxt);
        genderRadio = findViewById(R.id.genderRadioGroup);
        next = findViewById(R.id.nextBtn);

        genderRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maleRadioBtn:
                        gender = "Male";
                        break;
                    case R.id.femaleRadioBtn:
                        gender = "Female";
                        break;
                    case R.id.othersRadioBtn:
                        gender = "Others";
                        break;
                }

            }
        });

        next.setOnClickListener(view -> {
            addInfo();
            Intent i= new Intent(PatientFormActivity.this, SymptomAnalyzerActivity.class);
            i.putExtra("patientId", pId);
            startActivity(i);
        });

    }

    private void addInfo(){

        FirebaseUser person = FirebaseAuth.getInstance().getCurrentUser();
        dR = FirebaseDatabase.getInstance().getReference("Person");

        String key = person.getUid();
        pId= dR.child(key).child("Patient").push().getKey();

        String patientName = enterName.getText().toString();
        String patientAddress = enterAddress.getText().toString();
        String patientAge = enterAge.getText().toString();
        String patientBloodGroup = enterBloodGroup.getText().toString();
        String patientPhoneNo = enterPhoneNo.getText().toString();

        Patient user = new Patient(0);
        user.setName(patientName);
        user.setAddress(patientAddress);
        user.setPatient_age(patientAge);
        user.setPatient_blood_group(patientBloodGroup);
        user.setPatient_phone_no(patientPhoneNo);
        user.setDisease("null");


        dR.child(key).child("Patient").child(pId).setValue(user);


    }

    public void openPatientHome(View view) {
        startActivity(new Intent(PatientFormActivity.this, PatientHomePage.class));
    }

}