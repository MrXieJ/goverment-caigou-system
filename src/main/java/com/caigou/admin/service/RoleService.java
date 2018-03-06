package com.caigou.admin.service;

import com.caigou.admin.entity.Role;
import com.caigou.admin.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * 找出最大id
     * */
    public int find_max_id(){return roleMapper.find_max_id();}

    /**
     * 根据角色名查询角色
     * */
    public List<Role> getByRname(String role_name,Long first,Long limit) { return roleMapper.findByRolename(role_name,first,limit); }

    /**
     * 查询所有角色
     * */
    public List<Role> findAll(Long first,Long limit){return roleMapper.findAll(first,limit);}

    /**
     * 新增角色
     * */
    public Integer addRole(Role role){return roleMapper.addRole(role);}

    /**
     * 删除角色
     * */
    public Integer deleteRole(Role role){return roleMapper.deleteRole(role);}


    /**
     * 编辑角色
     * */
    public Integer editRole(Role role){return roleMapper.editRole(role);}

}
