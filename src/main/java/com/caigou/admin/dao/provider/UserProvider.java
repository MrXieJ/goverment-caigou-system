package com.caigou.admin.dao.provider;

import com.caigou.admin.entity.User;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    /**
     * 更新用户密码
     * */
    public String updateUser (User user) {
        return new SQL(){
            {
                UPDATE("USER");
                if (user.getPassword() != null) {
                    SET("PASSWORD = #{password}");
                }
                WHERE("USERNAME = #{username}");
            }
        }.toString();
    }

    /**
     * 根据组合条件查找用户
     * */
    public String findUser (User user) {
        String sql= new SQL(){
            {
                SELECT("*");
                FROM("USER");
                WHERE("1=1");
                if (user.getUser_realname() != null) {
                    AND().WHERE("USER_REALNAME = #{user_realname}");
                }
                if (user.getRole_id() != null) {
                    AND().WHERE("ROLE_ID = #{role_id}");
                }
                if (user.getUsername()!= null){
                    AND().WHERE("USERNAME = #{username}");
                }
            }
        }.toString();
        if(user.getLimit()!=-1l){
            sql+=" LIMIT #{page},#{limit}";
        }
        return sql;
    }


}
