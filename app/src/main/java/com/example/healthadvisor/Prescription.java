package com.example.healthadvisor;

public class Prescription extends Patient{
    private int pres_id;
    private int doctor_id;

    public Prescription(int userType) {
        super(userType);
    }

    public int getPres_id() {
        return pres_id;
    }

    public void setPres_id(int pres_id) {
        this.pres_id = pres_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
}
