package com.example.healthadvisor;

public class Disease_analyzer {
    private int psId;
    private int sId;
    private String disease;

    public Disease_analyzer(int psId, int sId, String disease) {
        this.psId = psId;
        this.sId = sId;
        this.disease = disease;
    }

    public int getPsId() {
        return psId;
    }

    public void setPsId(int psId) {
        this.psId = psId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
