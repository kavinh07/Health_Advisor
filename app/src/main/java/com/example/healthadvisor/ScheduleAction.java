package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScheduleAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_action);
    }

    public void openCreateSchedule(View view) {
        startActivity(new Intent(ScheduleAction.this, DoctorScheduleActivity.class));
    }

    public void openScheduleAvailability(View view) {
        startActivity(new Intent(ScheduleAction.this, ScheduleAvailabiltyActivity.class));
    }
}