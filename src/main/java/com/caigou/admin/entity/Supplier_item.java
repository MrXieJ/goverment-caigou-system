package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Supplier_item implements Serializable{

    private String item_order;

    private int id;

    private String item_type;

    private String item_name;

    private String item_supid;

    private String sup_id;

    private String sup_name;

    private String item_addtime;

    private String present_state;

    private String item_price;

    private String item_brand;

    private String item_spec;

    private String item_produce_address;

    private String item_count_unit;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
