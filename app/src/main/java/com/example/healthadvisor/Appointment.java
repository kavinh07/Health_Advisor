package com.example.healthadvisor;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private int doctor_id;
    private int patient_id;
    private String disease;
    private Date date;
    private Time time;
    private String type;
    private String location;

    public Appointment(int doctor_id, int patient_id, String disease, Date date, Time time, String type, String location) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.disease = disease;
        this.date = date;
        this.time = time;
        this.type = type;
        this.location = location;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
