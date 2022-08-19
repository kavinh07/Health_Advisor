package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DiseaseShowActivity extends AppCompatActivity {
    String pId, disease;
    TextView diseasetxt;
    Button showbtn, homebtn, appbtn;
    DatabaseReference dR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_show);

        pId = getIntent().getStringExtra("patientId");
        disease= getIntent().getStringExtra("patientDisease");

        showbtn= findViewById(R.id.showBtn);
        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diseasetxt= findViewById(R.id.diseaseShowTxt);
                diseasetxt.setText(disease);
                saveDisease();
            }


        });
        homebtn= findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiseaseShowActivity.this, PatientHomePage.class));
            }
        });

        appbtn= findViewById(R.id.appBtn);
        appbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(DiseaseShowActivity.this, ShowDoctorListActivity.class);
                i.putExtra("patientId", pId);
                i.putExtra("patientDisease", disease);
                startActivity(i);
            }
        });


    }

    private void saveDisease() {
        FirebaseUser person = FirebaseAuth.getInstance().getCurrentUser();
        dR = FirebaseDatabase.getInstance().getReference("Person");

        String key = person.getUid();
        Map<String, Object> map = new HashMap<>();
        map.put("disease", disease);
        dR.child(key).child("Patient").child(pId).updateChildren(map);
    }
}