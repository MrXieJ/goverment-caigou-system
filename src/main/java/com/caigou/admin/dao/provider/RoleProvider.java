package com.caigou.admin.dao.provider;

import org.apache.ibatis.jdbc.SQL;

public class RoleProvider {


    /**
     * 查找所有角色
     * */
    public String findAll (Long first,Long limit) {
        StringBuffer sb = new StringBuffer();
        sb.append(new SQL(){
            {
                SELECT("*");
                FROM("ROLE");
            }
        }.toString());
        if(first!=-1l){
            sb.append(" LIMIT "+first+","+limit);
        }
        return sb.toString();
    }

    /**
     * 根据角色名查找角色
     * */
    public String findByname ( String role_name, Long first, Long limit) {
        String sql=new SQL(){
            {
                SELECT("*");
                FROM("ROLE");
                WHERE("ROLE_NAME = '"+role_name+"'");
            }
        }.toString();
        if(first!=-1l){
            sql+=" LIMIT "+first+","+limit;
        }
        return sql;
    }
}
