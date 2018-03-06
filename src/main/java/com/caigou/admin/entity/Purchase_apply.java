package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Purchase_apply implements Serializable {

    private int id;

    private String apply_user;

    private String apply_user_account;

    private String apply_department;

    private String apply_date;

    private String apply_state;

    private String apply_order;

    private String apply_check_user;

    private String purchase_complete_time;

    private String purchase_item_accept_time;

    private String deny_reason;

    private List<Apply_item> Apply_item;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
