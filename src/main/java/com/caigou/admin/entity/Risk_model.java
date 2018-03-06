package com.caigou.admin.entity;

import lombok.Data;

@Data
public class Risk_model {

    private int id;

    private String item_order;

    private String item_type;

    private String item_name;

    private int price_set_high;

    private int price_set_low;

    private int deviation_check;

    private double deviation_price_warn;

    private double deviation_price_hide;

    private String last_edit_time;

    private int status;

    private String secure_price_range;

    private Long page;

    private Long limit;

}
