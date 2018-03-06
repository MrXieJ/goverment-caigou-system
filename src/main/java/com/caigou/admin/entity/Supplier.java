package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Supplier implements Serializable{

    private int id;

    private String supplier_id;

    private String supplier_name;

    private String create_time;

    private String sup_item_type;

    private String sup_item_name;

    private int evaluate_score;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
