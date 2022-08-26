package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorAppointmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference dR;
    ShowAppAdapter adapter2;
    ArrayList<ShowApp> list;
    String docId, docName, currentPId;
    FirebaseUser person;

    @Override
    protected void onStart() {
        super.onStart();
        dR= FirebaseDatabase.getInstance().getReference("Appointment");
        recyclerView = findViewById(R.id.appList);

        person= FirebaseAuth.getInstance().getCurrentUser();
        currentPId= person.getUid();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter2 = new ShowAppAdapter(this, list, this);
        recyclerView.setAdapter(adapter2);

        Query check= dR.orderByChild("docId").equalTo(currentPId);

        check.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){

                    ShowApp app= new ShowApp();

                    app.setPatName(ds.child("patientName").getValue().toString());
                    app.setDocName(ds.child("docName").getValue().toString());
                    app.setSlot(ds.child("time").getValue().toString());
                    app.setAppId(ds.child("id").getValue().toString());
                    list.add(app);
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
    }
}