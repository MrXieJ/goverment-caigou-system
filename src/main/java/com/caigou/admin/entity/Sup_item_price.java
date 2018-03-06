package com.caigou.admin.entity;

import lombok.Data;

@Data
public class Sup_item_price {
    private int id;
    private String time;
    private double price;
    private String sup_item_id;
    private static final long serialVersionUID = 1L;
}
