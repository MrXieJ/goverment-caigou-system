package com.caigou.admin.service;

import com.caigou.admin.dao.UserMapper;
import com.caigou.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User getByUsername(String username) { return userMapper.findByUsername(username); }

    public List<User> findUser(User user) {return userMapper.findByCondition(user);}

    public Integer updateUserInfo(User user) {
        return userMapper.updateUser(user);
    }

    public Integer addUser(User user){return userMapper.addUser(user);}

    public Integer deleteUser(String username){return userMapper.deleteUser(username);}

    private static final String KEY = "abc123"; // KEY为自定义秘钥



}
