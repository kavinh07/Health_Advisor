package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

public class ShowDoctorListActivity extends AppCompatActivity implements RecyclerViewInterface {

    String pId, disease;

    FirebaseUser person;

    RecyclerView recyclerView;
    DatabaseReference dR;
    ShowDocAdapter myAdapter;
    ArrayList<ShowDoc> list;

    @Override
    protected void onStart() {
        super.onStart();

        pId = getIntent().getStringExtra("patientId");
        disease = getIntent().getStringExtra("patientDisease");

        recyclerView = findViewById(R.id.docList);
        dR = FirebaseDatabase.getInstance().getReference("Doctor");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new ShowDocAdapter(this, list, this);
        recyclerView.setAdapter(myAdapter);

        Query check= dR.orderByChild("disease").equalTo(disease);

        check.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    String id = ds.child("id").getValue().toString();

                    ShowDoc doc = new ShowDoc();
                    doc.setDocName(ds.child("name").getValue().toString());
                    doc.setSlot1(ds.child("Schedules").child("sch1").child("time").getValue().toString());
                    doc.setSlot2(ds.child("Schedules").child("sch2").child("time").getValue().toString());
                    doc.setDocId(id);
                    list.add(doc);
                }
                myAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_list);

    }


    @Override
    public void onClickItem(int position) {

        person= FirebaseAuth.getInstance().getCurrentUser();

        Intent i= new Intent(ShowDoctorListActivity.this, SelectSlotActivity.class);
        i.putExtra("docId",list.get(position).getDocId());
        i.putExtra("pId", pId);
        i.putExtra("personId", person.getUid());
        i.putExtra("disease", disease);

        startActivity(i);


    }
}