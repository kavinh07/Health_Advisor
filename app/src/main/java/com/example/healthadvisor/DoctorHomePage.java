package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorHomePage extends AppCompatActivity {
    FirebaseUser person;
    DatabaseReference dR;
    TextView tvDocName;
    TextView tvDocEmail;
    private String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        tvDocName = findViewById(R.id.docName);
        tvDocEmail = findViewById(R.id.docEmail);

        person = FirebaseAuth.getInstance().getCurrentUser();
        UID = person.getUid();
        showData();

    }

    private void showData() {
        dR = FirebaseDatabase.getInstance().getReference("Doctor");
        dR.child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String id = UID;
                        String name = String.valueOf(dataSnapshot.child("name").getValue());
                        String email = String.valueOf(dataSnapshot.child("email").getValue());

                        Doctor user = new Doctor(1);
                        user.setId(id);
                        user.setName(name);
                        user.setEmail(email);

                        tvDocEmail.setText(user.getEmail());
                        tvDocName.setText(user.getName());
                    }
                }
            }
        });
    }

    public void openEditProfileDoc(View view) {
        startActivity(new Intent(DoctorHomePage.this, DoctorEditProfileActivity.class));
    }

    public void openDocSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(DoctorHomePage.this, MainActivity.class));
    }

    public void openSchedule(View view) {
        startActivity(new Intent(DoctorHomePage.this, ScheduleAction.class));
    }
}