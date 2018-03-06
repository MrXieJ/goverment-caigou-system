package com.caigou.admin.entity;



import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    /**
     * 自增ID
     */
    private int id;

    /**
     * 用户账号名
     */
    private String username;

    /**
     * 用户名
     * */
    private String user_realname;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色
     */
    private String role_id;

    /**
     * 用户部门
     */
    private String department;

    /**
     * 用户是否被删除
     */
    private int delete;

    /**
     * 用户是否被冻结
     */
    private int freeze;

    /**
     * 用户手机号码
     */
    private String telphone;

    /**
     * 用户最后修改时间
     */
    private String last_update;


    private Long page;

    private Long limit;

    private static final long serialVersionUID = 1L;
}
