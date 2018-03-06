package com.caigou.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Item implements Serializable{

    /**
     * 自增id
     * */
    private int id;

    /**
     * 商品编号
     * */
    private String item_order;

    /**
     * 商品类型
     * */
    private String item_type;

    /**
     * 商品名字
     * */
    private String item_name;

    /**
     * 商品最后编辑时间
     * */
    private String item_last_edit;

    /**
     * 商品状态
     * */
    private String item_status;

    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;

}
