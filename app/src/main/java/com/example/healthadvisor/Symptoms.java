package com.example.healthadvisor;

public class Symptoms {
    private int id;
    private String disease;

    public Symptoms(int id, String disease) {
        this.id = id;
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
