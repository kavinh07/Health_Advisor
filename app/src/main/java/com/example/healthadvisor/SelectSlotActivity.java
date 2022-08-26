package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SelectSlotActivity extends AppCompatActivity {
    Button slot1, slot2, homeBtn, backBtn;
    DatabaseReference dR,dR2, dR3;
    String docId, docName, pId, personId, slot1V, slot2V, slot1T, slot2T, disease, patientName;


    @Override
    protected void onStart() {
        super.onStart();

        docId= getIntent().getStringExtra("docId");
        pId= getIntent().getStringExtra("pId");
        personId= getIntent().getStringExtra("personId");
        disease= getIntent().getStringExtra("disease");

        dR= FirebaseDatabase.getInstance().getReference("Doctor");
        dR2= FirebaseDatabase.getInstance().getReference("Person");
        dR3= FirebaseDatabase.getInstance().getReference("Appointment");

        dR2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.getResult().exists()){
                    DataSnapshot dataSnapshot= task.getResult();
                    patientName= dataSnapshot.child(personId).child("Patient").child(pId).child("name").getValue().toString();
                }

            }
        });

        slot1= findViewById(R.id.slot1Btn);
        slot2= findViewById(R.id.slot2Btn);
        homeBtn= findViewById(R.id.homeBtn);
        backBtn= findViewById(R.id.backBtn);



        slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dR.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.getResult().exists()){
                            DataSnapshot snapshot= task.getResult();
                            slot1V= snapshot.child(docId).child("Schedules").child("sch1").child("availability").getValue().toString();
                            slot1T= snapshot.child(docId).child("Schedules").child("sch1").child("time").getValue().toString();
                            docName= snapshot.child(docId).child("name").getValue().toString();
                            if(slot1V.equals("0")){

                                dR3= FirebaseDatabase.getInstance().getReference("Appointment");

                                String appId= dR3.push().getKey();

                                Map<String, String> m= new HashMap<>();
                                m.put("id", appId);
                                m.put("patientName", patientName);
                                m.put("patientId", pId);
                                m.put("personId", personId);
                                m.put("docId", docId);
                                m.put("time", slot1T);
                                m.put("docName", docName);
                                m.put("slotName", "sch1");

                                dR3.child(appId).setValue(m);

                                updateAvailabilitySlot1();
                                startActivity(new Intent(SelectSlotActivity.this, PatientHomePage.class));

//                                Intent i= new Intent(SelectSlotActivity.this, TestActivity.class);
//                                i.putExtra("docId", appId);
//                                startActivity(i);


                            }else{
                                Toast.makeText(SelectSlotActivity.this, "The slot is already booked", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
            }
        });
        slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dR.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.getResult().exists()){
                            DataSnapshot snapshot= task.getResult();
                            slot2V= snapshot.child(docId).child("Schedules").child("sch2").child("availability").getValue().toString();
                            slot2T= snapshot.child(docId).child("Schedules").child("sch2").child("time").getValue().toString();
                            docName= snapshot.child(docId).child("name").getValue().toString();
                            if(slot2V.equals("0")){


                                String appId= dR3.push().getKey();

                                Map<String, String> m= new HashMap<>();
                                m.put("id", appId);
                                m.put("patientName", patientName);
                                m.put("patientId", pId);
                                m.put("personId", personId);
                                m.put("docId", docId);
                                m.put("time", slot2T);
                                m.put("docName", docName);
                                m.put("slotName", "sch2");

                                dR3.child(appId).setValue(m);

                                updateAvailabilitySlot2();
                                startActivity(new Intent(SelectSlotActivity.this, PatientHomePage.class));

//                                Intent i= new Intent(SelectSlotActivity.this, TestActivity.class);
//                                i.putExtra("docId", appId);
//                                startActivity(i);


                            }else{
                                Toast.makeText(SelectSlotActivity.this, "The slot is already booked", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectSlotActivity.this, PatientHomePage.class));
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SelectSlotActivity.this, ShowDoctorListActivity.class);
                i.putExtra("patientId", pId);
                i.putExtra("patientDisease", disease);
                startActivity(i);
            }
        });

    }

    private void updateAvailabilitySlot2() {
        Map<String, Object> map= new HashMap<>();
        map.put("availability", "1");

        dR.child(docId).child("Schedules").child("sch2").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    private void updateAvailabilitySlot1() {
        Map<String, Object> map= new HashMap<>();
        map.put("availability", "1");

        dR.child(docId).child("Schedules").child("sch1").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_slot);

    }
}