package com.jybl.admin.service;

import com.jybl.admin.dao.RoleMapper;
import com.jybl.admin.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public Role getByRid(Integer rid) {
        return roleMapper.findByRid(rid);
    }

}
