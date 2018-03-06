package com.caigou.admin.controller;

import com.caigou.admin.dao.RoleMapper;
import com.caigou.admin.entity.Role;
import com.caigou.admin.entity.User;
import com.caigou.admin.service.RoleService;
import com.caigou.admin.service.UserService;
import com.caigou.admin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    /**
     * 查询所有角色，支持分页
     * */
    @RequestMapping("/findall")
    public Map findAll(HttpServletRequest request) {
        Long limit = -1l;
        Long first = -1l;
        if (request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) *limit;
        }
        Map map = new HashMap();
        try {
            List<Role> rlist = roleService.findAll(first, limit);
            map.put("code", 0);
            map.put("data", rlist);
        } catch (Exception e) {
            map.put("errormessage",e.getMessage());
            map.put("code", 1);
        }
        return map;
    }

    /**
     * 通过角色名查找角色，支持分页
     * */
    @RequestMapping("/findbyname")
    public Map findByname(HttpServletRequest request) {
        Long limit = -1l;
        Long first = -1l;
        if (request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) *limit;
        }
        String role_name=request.getParameter("role_name");
        Map map = new HashMap();
        try {
            List<Role> rlist = roleService.getByRname(role_name, first, limit);
            map.put("code", 0);
            map.put("data", rlist);
        } catch (Exception e) {
            map.put("errormessage",e.getMessage());
            map.put("code", 1);
        }
        return map;
    }

    /**
     * 添加角色
     * */
    @Transactional
    @RequestMapping("/add")
    public Map addRole(Role role) {
        Map map = new HashMap();
        try {
            role.setCreate_time(DateUtils.getNowFormatDate(new Date()));
            role.setRole_id("A"+String.valueOf(10001+roleService.find_max_id()));
            User user=userService.getByUsername(role.getEdit_user());
            role.setEdit_user(user.getUser_realname());
            role.setEdit_time(DateUtils.getNowFormatDate(new Date()));
            roleService.addRole(role);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 编辑角色
     * 除了编辑的角色参数，还需要提供role_id
     * */
    @Transactional
    @RequestMapping("/edit")
    public Map editRole(Role role) {
        Map map = new HashMap();
        try {
            User user=userService.getByUsername(role.getEdit_user());
            role.setEdit_user(user.getUser_realname());
            role.setEdit_time(DateUtils.getNowFormatDate(new Date()));
            roleService.editRole(role);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 删除角色
     * 通过角色名删除
     * */
    @Transactional
    @RequestMapping("/delete")
    public Map deleteRole(Role role) {
        Map map = new HashMap();
        try {
            roleService.deleteRole(role);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }
}
