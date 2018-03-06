package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Purchase_check implements Serializable{

    private int id;

    private String check_date;

    private String check_user;

    private String deny_reason;

    private String apply_order;

    private String check_user_account;

    private int check_status;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
