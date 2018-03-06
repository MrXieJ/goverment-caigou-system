package com.caigou.admin.dao;

import com.caigou.admin.dao.provider.RoleProvider;
import com.caigou.admin.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 查找出角色表最大id
     * */
    @Select("SELECT IFNULL(MAX(ID), 0) FROM ROLE")
    int find_max_id();

    /**
     * 根据角色名字查询
     * */
    @SelectProvider(type = RoleProvider.class, method = "findByname")
    List<Role> findByRolename(String role_name, Long first,Long limit);

    /**
     * 查询所有角色
     * */
    @SelectProvider(type = RoleProvider.class, method = "findAll")
    List<Role> findAll(Long first,Long limit);

    /**
     * 新增角色
     * */
    @Insert("INSERT INTO ROLE (ROLE_ID, ROLE_NAME, FREEZE, PERMISSIONS, CREATE_TIME, EDIT_USER, EDIT_TIME) VALUES (#{role_id}, #{role_name}, #{freeze}, #{permissions}, #{create_time}, #{edit_user}, #{edit_time})")
    Integer addRole(Role role);

    /**
     * 编辑角色
     * */
    @Update("UPDATE ROLE SET ROLE_NAME = #{role_name}, FREEZE = #{freeze}, EDIT_USER = #{edit_user}, EDIT_TIME = #{edit_time}, PERMISSIONS= #{permissions} WHERE ROLE_ID = #{role_id}")
    Integer editRole(Role role);

    /**
     * 删除角色
     * */
    @Delete("DELETE FROM ROLE WHERE ROLE_ID = #{role_id}")
    Integer deleteRole(Role role);

}
