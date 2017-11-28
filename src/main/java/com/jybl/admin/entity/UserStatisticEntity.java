package com.jybl.admin.entity;

import java.io.Serializable;

public class UserStatisticEntity implements Serializable {

    private String month;
    private Long patient_count;
    private Long doctor_count;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getPatient_count() {
        return patient_count;
    }

    public void setPatient_count(Long patient_count) {
        this.patient_count = patient_count;
    }

    public Long getDoctor_count() {
        return doctor_count;
    }

    public void setDoctor_count(Long doctor_count) {
        this.doctor_count = doctor_count;
    }
}
