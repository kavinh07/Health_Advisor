package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegTypeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_type);
    }

    public void openRegDoc(View view) {

        startActivity(new Intent(this, DocRegistrationActivity.class));

    }
    public void openRegPat(View view) {

        startActivity(new Intent(this, RegistrationActivity.class));

    }

}
