package com.caigou.admin.dao;

import com.caigou.admin.dao.provider.UserProvider;
import com.caigou.admin.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据用户账号查找用户
     */
    @Select("SELECT * FROM USER WHERE USERNAME=#{username}")
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户姓名，用户账号，用户角色查找用户
     */
    @SelectProvider(type = UserProvider.class, method = "findUser")
    List<User> findByCondition(User user);

    /**
     * 新增用户
     * */
    @Insert("INSERT INTO USER (USERNAME, PASSWORD, ROLE_ID, DEPARTMENT, TELPHONE, USER_REALNAME, LAST_UPDATE) VALUES (#{username}, #{password}, #{role_id}, #{department}, #{telphone}, #{user_realname}, #{last_update})")
    Integer addUser(User user);

    /**
     * 删除用户
     * */
    @Delete("DELETE FROM USER WHERE USERNAME = #{username}")
    Integer deleteUser(@Param("username") String  username);

    /**
     * 更新用户密码
     * */
    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    Integer updateUser(User user);

}
