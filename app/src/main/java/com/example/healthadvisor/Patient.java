package com.example.healthadvisor;

public class Patient extends Person {
    private String patient_id;
    private String patient_name;
    private int patient_age;
    private String patient_gender;
    private String patient_blood_group;

    public Patient(int userType) {
        super(userType);
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public String getPatient_blood_group() {
        return patient_blood_group;
    }

    public void setPatient_blood_group(String patient_blood_group) {
        this.patient_blood_group = patient_blood_group;
    }
}
