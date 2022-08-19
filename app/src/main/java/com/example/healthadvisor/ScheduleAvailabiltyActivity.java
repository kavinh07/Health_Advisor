package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ScheduleAvailabiltyActivity extends AppCompatActivity {
    EditText firstTime, secondTime;
    RadioGroup firstRG, secondRG;
    Button updateBtn, exitBtn;
    DatabaseReference dR, dR2, dR3, dR4;
    FirebaseUser person = FirebaseAuth.getInstance().getCurrentUser();

    String UID = person.getUid();

    String firstAvail, secondAvail;
    String firstAvailOld, secondAvailOld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_availabilty);

        firstTime= findViewById(R.id.firstTimeetx);
        secondTime= findViewById(R.id.secondTimeetx);

        firstRG = findViewById(R.id.firstRadioGroup);
        firstRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.firstAvailableRadioBtn:
                        firstAvail = "0";
                        break;
                    case R.id.firstUnavailableRadioBtn:
                        firstAvail = "1";
                        break;
                }

            }
        });

        secondRG = findViewById(R.id.secondRadioGroup);
        secondRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.secondAvailableRadioBtn:
                        secondAvail = "0";
                        break;
                    case R.id.secondUnavailableRadioBtn:
                        secondAvail = "1";
                        break;
                }

            }
        });
        showData1();
        showData2();

        updateBtn= findViewById(R.id.updateButton);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update1();
                update2();
            }
        });
        exitBtn= findViewById(R.id.exitButton);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScheduleAvailabiltyActivity.this, DoctorHomePage.class));
            }
        });
    }

    private void update2() {
        dR3 = FirebaseDatabase.getInstance().getReference("Person");

        Map<String, Object> map = new HashMap<>();
        map.put("availability", secondAvail);

        dR.child(UID).child("Schedules").child("sch2").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ScheduleAvailabiltyActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update1() {
        dR3 = FirebaseDatabase.getInstance().getReference("Person");

        Map<String, Object> map = new HashMap<>();
        map.put("availability", firstAvail);

        dR.child(UID).child("Schedules").child("sch1").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ScheduleAvailabiltyActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showData2() {
        dR2 = FirebaseDatabase.getInstance().getReference("Doctor");
        dR2.child(UID).child("Schedules").child("sch2").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot snapshot = task.getResult();
                        secondTime.setText(String.valueOf(snapshot.child("time").getValue()));
                        secondAvailOld = String.valueOf(snapshot.child("availability").getValue());
                        if(secondAvailOld.equals("0")){
                            RadioButton avail= findViewById(R.id.secondAvailableRadioBtn);
                            avail.setSelected(true);
                            avail.setChecked(true);
                        }else {
                            RadioButton unavail= findViewById(R.id.secondUnavailableRadioBtn);
                            unavail.setSelected(true);
                            unavail.setChecked(true);
                        }
                    }
                }
            }
        });
    }

    private void showData1() {
        dR = FirebaseDatabase.getInstance().getReference("Doctor");
        dR.child(UID).child("Schedules").child("sch1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot snapshot = task.getResult();
                        firstTime.setText(String.valueOf(snapshot.child("time").getValue()));
                        firstAvailOld = String.valueOf(snapshot.child("availability").getValue());
                        if(firstAvailOld.equals("0")){
                            RadioButton avail= findViewById(R.id.firstAvailableRadioBtn);
                            avail.setSelected(true);
                            avail.setChecked(true);
                        }else {
                            RadioButton unavail= findViewById(R.id.firstUnavailableRadioBtn);
                            unavail.setSelected(true);
                            unavail.setChecked(true);
                        }
                    }
                }
            }
        });
    }
}