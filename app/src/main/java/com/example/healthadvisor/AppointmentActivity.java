package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentActivity extends AppCompatActivity implements DeleteItemInterface {

    RecyclerView recyclerView;
    DatabaseReference dR, dR2, dR3;
    ShowAppAdapter adapter2;
    ArrayList<ShowApp> list;
    String docId, appId, currentPId, slot, slotName;
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
        adapter2 = new ShowAppAdapter(this, list, this, this);
        recyclerView.setAdapter(adapter2);

        Query check= dR.orderByChild("personId").equalTo(currentPId);

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
    public void onDeleteItem(int position) {
        dR2= FirebaseDatabase.getInstance().getReference("Appointment");


        appId= list.get(position).getAppId();
        slot= list.get(position).getSlot();
        adapter2.applist.remove(position);
        adapter2.notifyItemRemoved(position);
        dR2.child(appId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                if(task.getResult().exists()){
                    DataSnapshot s= task.getResult();
                    docId= s.child("docId").getValue().toString();
                    slotName= s.child("slotName").getValue().toString();
                    updateSchedule(docId, slotName);
                }
            }
        });


        dR2.child(appId).removeValue();








    }

    private void updateSchedule(String id, String s) {
        dR3= FirebaseDatabase.getInstance().getReference("Doctor");
        Map<String, Object> map= new HashMap<>();
        map.put("availability", "0");

        if(s.equals("sch1")){
            dR3.child(id).child("Schedules").child("sch1").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AppointmentActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            dR3.child(id).child("Schedules").child("sch2").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AppointmentActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


    }



}