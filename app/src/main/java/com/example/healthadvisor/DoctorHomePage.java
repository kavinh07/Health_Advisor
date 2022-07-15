package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    TextView tvDocId;
    TextView tvDocName;
    TextView tvDocEmail;
    private String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        tvDocId = findViewById(R.id.docId);
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
                        tvDocId.setText(user.getId());
                        tvDocName.setText(user.getName());
                    } else {
                        Toast.makeText(DoctorHomePage.this, "Doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoctorHomePage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}