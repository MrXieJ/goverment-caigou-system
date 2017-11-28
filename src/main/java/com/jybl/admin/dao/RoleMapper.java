package com.jybl.admin.dao;

import com.jybl.admin.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM ROLE WHERE RID=#{rid}")
    Role findByRid(@Param("rid") Integer rid);

}
