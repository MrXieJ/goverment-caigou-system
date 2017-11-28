package com.jybl.admin.entity;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    /**
     * 角色唯一标识符
     */
    private Integer rId;

    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String rName;

    /**
     * 角色别名
     */
    @NotNull(message = "角色别名不能为空")
    private String rAlias;

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

    private List<Permission> permissions;

    private static final long serialVersionUID = 1L;


}
