package com.jybl.admin.dao.provider;

import com.jybl.admin.entity.User;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String updateUser (User user) {

        StringBuffer sb = new StringBuffer();

        return new SQL(){
            {
                UPDATE("USER");

                if (user.getAlias() != null) {
                    SET("ALIAS = #{alias}");
                }

                if (user.getPassword() != null) {
                    SET("PASSWORD = #{password}");
                }

                if (user.getAvatar() != null) {
                    SET("AVATAR = #{avatar}");
                }

                WHERE("USERNAME = #{username}");

            }
        }.toString();
    }

}
