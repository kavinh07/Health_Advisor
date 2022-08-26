package com.example.healthadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DocRegistrationActivity extends AppCompatActivity {


    private EditText nameEditTxt;
    private EditText emailEditTxt;
    private EditText passEditTxt;
    private EditText conPassEditTxt;
    private EditText diseaseEditTxt;
    private RadioGroup genderRadio;
    private Button signUpBtn;
    private String gender;
    FirebaseAuth mAuth;
    DatabaseReference dR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_registration);

        mAuth = FirebaseAuth.getInstance();
        dR = FirebaseDatabase.getInstance().getReference("Doctor");


        nameEditTxt = findViewById(R.id.nametxt);
        emailEditTxt = findViewById(R.id.emailTxt);
        passEditTxt = findViewById(R.id.passTxt);
        conPassEditTxt = findViewById(R.id.conPassTxt);
        genderRadio = findViewById(R.id.genderRadioGroup);
        signUpBtn = findViewById(R.id.signUpBtn);
        diseaseEditTxt = findViewById(R.id.diseaseetx);
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

        //Firebase Start
        mAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(view -> {
            createUser();
        });


    }

    private void createUser() {

        String email = emailEditTxt.getText().toString();
        String pass = passEditTxt.getText().toString();
        String conPass = conPassEditTxt.getText().toString();
        String dis= diseaseEditTxt.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditTxt.setError("Email can't be empty!");
            emailEditTxt.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            passEditTxt.setError("Password can't be empty!");
            passEditTxt.requestFocus();
        } else if (!(pass.equals(conPass))) {
            conPassEditTxt.setError("Password didn't match");
            conPassEditTxt.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        saveData();
                        startActivity(new Intent(DocRegistrationActivity.this, DoctorHomePage.class));
                        Toast.makeText(DocRegistrationActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DocRegistrationActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }


    }

    private void saveData() {

        FirebaseUser person = FirebaseAuth.getInstance().getCurrentUser();

        Doctor user = new Doctor(1);
        user.setEmail(emailEditTxt.getText().toString());
        user.setPassword(passEditTxt.getText().toString());
        user.setName(nameEditTxt.getText().toString());
        user.setGender(gender);
        user.setDisease(diseaseEditTxt.getText().toString());
        user.setId(person.getUid());


        String key = user.getId();
        dR.child(key).setValue(user);

        Toast.makeText(this, user.getId(), Toast.LENGTH_SHORT).show();

    }


}