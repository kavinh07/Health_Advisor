package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DoctorScheduleActivity extends AppCompatActivity {
    EditText firstStartTimetx, firstEndTimetx, secondStartTimetx, secondEndTimetx;
    RadioGroup firstStartRG, firstEndRG, secondStartRG, secondEndRG;

    Button updatebtn, exitbtn;

    String firstStartAMPM, firstEndAMPM, secondStartAMPM, secondEndAMPM;
    String firstSchedule, secondSchedule;

    DatabaseReference dR, dR2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_schedule);

        updatebtn= findViewById(R.id.updateButton);
        exitbtn= findViewById(R.id.exitButton);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstStartTimetx= findViewById(R.id.firstStartTimeetx);
                firstEndTimetx= findViewById(R.id.firstEndTimeetx);
                firstSchedule= firstStartTimetx.getText().toString()+firstStartAMPM+"-"+firstEndTimetx.getText().toString()+firstEndAMPM;

                secondStartTimetx= findViewById(R.id.secondStartTimeetx);
                secondEndTimetx= findViewById(R.id.secondEndTimeetx);
                secondSchedule= secondStartTimetx.getText().toString()+secondStartAMPM+"-"+secondEndTimetx.getText().toString()+secondEndAMPM;

                saveSchedule();
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorScheduleActivity.this, DoctorHomePage.class));

            }
        });


        firstStartRG = findViewById(R.id.firstStartTimeRadioGroup);
        firstStartRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.firstStartAmRadioBtn:
                        firstStartAMPM = "AM";
                        break;
                    case R.id.firstStartPmRadioBtn:
                        firstStartAMPM = "PM";
                        break;
                }

            }
        });


        firstEndRG = findViewById(R.id.firstEndTimeRadioGroup);
        firstEndRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.firstEndAmRadioBtn:
                        firstEndAMPM = "AM";
                        break;
                    case R.id.firstEndPmRadioBtn:
                        firstEndAMPM = "PM";
                        break;
                }

            }
        });



        secondStartRG = findViewById(R.id.secondStartTimeRadioGroup);
        secondStartRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.secondStartAmRadioBtn:
                        secondStartAMPM = "AM";
                        break;
                    case R.id.secondStartPmRadioBtn:
                        secondStartAMPM = "PM";
                        break;
                }

            }
        });


        secondEndRG = findViewById(R.id.secondEndTimeRadioGroup);
        secondEndRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.secondEndAmRadioBtn:
                        secondEndAMPM = "AM";
                        break;
                    case R.id.secondEndPmRadioBtn:
                        secondEndAMPM = "PM";
                        break;
                }

            }
        });


    }

    private void saveSchedule() {
        FirebaseUser person = FirebaseAuth.getInstance().getCurrentUser();
        dR = FirebaseDatabase.getInstance().getReference("Doctor");
        dR2 = FirebaseDatabase.getInstance().getReference("Doctor");

        String key = person.getUid();

        Map<String, Object> map1= new HashMap<>();
        map1.put("time", firstSchedule);
        map1.put("availability", "0");
        dR.child(key).child("Schedules").child("sch1").setValue(map1);


        Map<String, Object> map2= new HashMap<>();
        map2.put("time", secondSchedule);
        map2.put("availability", "0");
        dR2.child(key).child("Schedules").child("sch2").setValue(map2);

        Toast.makeText(this, "Slots updated", Toast.LENGTH_SHORT).show();


    }
}