package com.example.healthadvisor;

public class Patient_Symptoms extends Patient{
    private int presId;

    public Patient_Symptoms(int userType) {
        super(userType);
    }

    public int getPresId() {
        return presId;
    }

    public void setPresId(int presId) {
        this.presId = presId;
    }
}
