package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorEditProfileActivity extends AppCompatActivity {

    private EditText editName, editEmail, setNewPass, conPass;
    private Button updateButton, exitButton;
    private RadioGroup genderRadio;
    String gender;
    FirebaseAuth mAuth;
    DatabaseReference dR, dR2;
    FirebaseUser person = FirebaseAuth.getInstance().getCurrentUser();
    String name, email, pass, confirmPass;
    String UID = person.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_edit_profile);
        editName = findViewById(R.id.editNameText);
        editEmail = findViewById(R.id.editEmailText);
        setNewPass = findViewById(R.id.newPassText);
        conPass = findViewById(R.id.conPassText);
        genderRadio = findViewById(R.id.genderRadioGroup);

        genderRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maleRadioBtn:
                        gender = "Male";
                        break;
                    case R.id.femaleRadioBtn:
                        gender = "Female";
                        break;
                    case R.id.othersRadioBtn:
                        gender = "Others";
                        break;
                }

            }
        });
        showData();
        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });
        exitButton= findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorEditProfileActivity.this, DoctorHomePage.class));
            }
        });
    }

    private void showData() {
        dR2 = FirebaseDatabase.getInstance().getReference("Doctor");
        dR2.child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot snapshot = task.getResult();
                        editName.setText(String.valueOf(snapshot.child("name").getValue()));
                        editEmail.setText(String.valueOf(snapshot.child("email").getValue()));
                        String oldGender = String.valueOf(snapshot.child("gender").getValue());
                        if(oldGender.equals("Male")){
                            RadioButton male= findViewById(R.id.maleRadioBtn);
                            male.setSelected(true);
                            male.setChecked(true);
                        }else if(oldGender.equals("Female")){
                            RadioButton female= findViewById(R.id.femaleRadioBtn);
                            female.setSelected(true);
                            female.setChecked(true);
                        }else{
                            RadioButton others= findViewById(R.id.othersRadioBtn);
                            others.setSelected(true);
                            others.setChecked(true);
                        }
                        setNewPass.setText(String.valueOf(snapshot.child("pass").getValue()));
                    }
                }
            }
        });
    }


    public void updatePerson() {

        name = editName.getText().toString();
        email = editEmail.getText().toString();
        pass = setNewPass.getText().toString();
        confirmPass = conPass.getText().toString();
        if(!(pass.equals(confirmPass))){
            conPass.setError("Password didn't match");
            conPass.requestFocus();
        }else{
            person.updatePassword(pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(DoctorEditProfileActivity.this, "Pass reset", Toast.LENGTH_SHORT).show();
                }
            });
        }
        dR = FirebaseDatabase.getInstance().getReference("Doctor");

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        if(pass.equals(confirmPass)){
            map.put("pass", pass);
        }
        map.put("gender", gender);

        dR.child(UID).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(DoctorEditProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });


    }
}