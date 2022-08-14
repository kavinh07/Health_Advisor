package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientHomePage extends AppCompatActivity {

    FirebaseUser person;
    DatabaseReference dR;
    TextView tvPatientName;
    TextView tvPatientEmail;
    private String UID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);


        tvPatientName=findViewById(R.id.patientName);
        tvPatientEmail=findViewById(R.id.patientEmail);


        person = FirebaseAuth.getInstance().getCurrentUser();
        UID= person.getUid();
        showData();

    }

    private void showData() {
        dR = FirebaseDatabase.getInstance().getReference("Person");
        dR.child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
                        String id= UID;
                        String name= String.valueOf(dataSnapshot.child("name").getValue());
                        String email= String.valueOf(dataSnapshot.child("email").getValue());

                        Person user= new Person(0);
                        user.setId(id);
                        user.setName(name);
                        user.setEmail(email);

                        tvPatientEmail.setText(user.getEmail());
                        tvPatientName.setText(user.getName());
                    }else{
                        Toast.makeText(PatientHomePage.this, "Doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PatientHomePage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void openEditProfile(View view) {
        startActivity(new Intent(PatientHomePage.this, PersonEditProfileActivity.class));
    }

    public void openPatSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(PatientHomePage.this, MainActivity.class));
    }

    public void openPatientForm(View view) {
        startActivity(new Intent(PatientHomePage.this, PatientFormActivity.class));

    }
}