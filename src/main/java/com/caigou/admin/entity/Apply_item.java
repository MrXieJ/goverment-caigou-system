package com.caigou.admin.entity;

import lombok.Data;

@Data
public class Apply_item {

    private String item_order;

    private String item_find_order;

    private String apply_order;

    private String apply_reason;

    private String item_name;

    private String item_sup_name;

    private String item_supid;

    private String item_sup_price;

    private String item_type;

    private int steady_fund;

    private int item_count;
}
