package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Supplier_info implements Serializable{

    private int id;

    private String supplier_id;

    private String supplier_name;

    private String institution_name;

    private String supplier_type;

    private String social_trust_code;

    private String regist_address;

    private String regist_person;

    private String corporate_card;

    private String tax_number;

    private String deposit_bank;

    private String gurantee_rate;

    private String business_owner;

    private String institution_owner;

    private String business_idcard;

    private String business_telphone;

    private Float  cooperration_amount;

    private String business_mail;

    private String business_address;

    private String create_time;

    private String sup_item_type;

    private String sup_item_name;

    private int evaluate_score;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
