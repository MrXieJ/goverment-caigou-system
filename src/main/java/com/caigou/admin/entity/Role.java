package com.caigou.admin.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {

    /**
     * 角色唯一标识符
     */
    private String role_id;

    /**
     * 角色名称
     */
    private String role_name;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 修改人
     */
    private String edit_user;

    /**
     * 修改时间
     * */
    private String edit_time;

    /**
     * 权限列表
     * */
    private String permissions;

    /**
     * 角色状态
     * */
    private int freeze;

    private static final long serialVersionUID = 1L;


}
