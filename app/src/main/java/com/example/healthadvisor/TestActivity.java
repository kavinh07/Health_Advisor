package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    TextView test;
    Button button;
    String id;
    DatabaseReference dR,dr2;

    @Override
    protected void onStart() {
        super.onStart();
        id= getIntent().getStringExtra("docId");
        test = findViewById(R.id.textView);
        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setText(id);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Map<String, String> m= new HashMap<>();


//        test = findViewById(R.id.textView);
//        button= findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////                dR= FirebaseDatabase.getInstance().getReference("Doctor");
////
////
////
////                dR.orderByChild("disease").equalTo("Malaria").addListenerForSingleValueEvent(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        for (DataSnapshot ds: snapshot.getChildren()){
////
////
////                            String id= ds.child("id").getValue().toString();
////                            test.setText(id);
////                        }
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
////                    }
////                });
////
//
//
//
//
//
//            }
//        });
    }
}