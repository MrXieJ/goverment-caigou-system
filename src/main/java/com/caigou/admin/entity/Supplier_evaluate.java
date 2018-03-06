package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Supplier_evaluate implements Serializable{

    private String sup_id;

    private String sup_service;

    private int sup_service_score;

    private String sup_time;

    private String sup_quality;

    private int sup_quality_score;

    private String sup_remark;

    private int sup_score;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
