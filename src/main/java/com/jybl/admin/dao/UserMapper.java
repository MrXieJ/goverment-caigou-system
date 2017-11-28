package com.jybl.admin.dao;

import com.jybl.admin.dao.provider.UserProvider;
import com.jybl.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE USERNAME=#{username}")
    User findByUsername(@Param("username") String username);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    Integer updateUser(User user);

}
