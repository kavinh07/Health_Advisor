package com.example.healthadvisor;

public class Patient extends Person {
    private String userId;
    private String patient_id;
    private String person_id;
    private String patient_name;
    private String patient_age;
    private String patient_gender;
    private String patient_blood_group;
    private String disease;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPatient_phone_no() {
        return patient_phone_no;
    }

    public void setPatient_phone_no(String patient_phone_no) {
        this.patient_phone_no = patient_phone_no;
    }

    private String patient_phone_no;



    public Patient(int userType) {
        super(userType);
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
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

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
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
