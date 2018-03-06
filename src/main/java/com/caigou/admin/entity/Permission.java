package com.caigou.admin.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Permission implements Serializable {

    /**
     * 权限菜单名称
     */
    private String menu_name;

    /**
     * 权限菜单id
     * */
    private int menu_id;


    private static final long serialVersionUID = 1L;
}
