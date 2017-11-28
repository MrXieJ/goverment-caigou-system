package com.jybl.admin.entity;



import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String alias;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private Role role;

    /**
     * 状态：1-正常，0-封禁
     */
    private Integer status;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 修改时间
     */
    private Long updated;

    /**
     * 创建时间字符串
     */
    private String createdAt;

    /**
     * 修改时间字符串
     */
    private String updatedAt;

    private static final long serialVersionUID = 1L;
}
