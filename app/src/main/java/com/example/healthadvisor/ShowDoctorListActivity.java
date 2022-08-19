package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowDoctorListActivity extends AppCompatActivity {

    String pId, disease;

    RecyclerView recyclerView;
    DatabaseReference dR;
    ShowDocAdapter myAdapter;
    ArrayList<ShowDoc> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_list);

        pId = getIntent().getStringExtra("patientId");
        disease = getIntent().getStringExtra("patientDisease");

        recyclerView = findViewById(R.id.docList);
        dR = FirebaseDatabase.getInstance().getReference("Doctor");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();
        myAdapter = new ShowDocAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        dR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String key= dataSnapshot.toString();

                    ShowDoc doc= new ShowDoc();
                    doc.setDocName(snapshot.child(key).child("name").toString());
                    doc.setSlot1(snapshot.child(key).child("Schedules").child("sch1").getValue().toString());
                    doc.setSlot2(snapshot.child(key).child("Schedules").child("sch2").getValue().toString());
                    list.add(doc);
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowDoctorListActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

//        Query query = FirebaseDatabase.getInstance().getReference("Doctor").orderByChild("disease").equalTo("Malaria");
//        query.addListenerForSingleValueEvent(valueEventListener);


    }


}