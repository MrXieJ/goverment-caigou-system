package com.jybl.admin.dao;

import com.jybl.admin.dao.provider.DoctorProvider;
import com.jybl.admin.entity.DoctorEntity;
import com.jybl.admin.entity.DoctorServiceEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorMapper {

    @Select("SELECT COUNT(*) FROM DOCTOR_INFO")
    Integer getCount();

    @SelectProvider(type = DoctorProvider.class, method = "selectDoctor")
    List<DoctorEntity> findAll(DoctorEntity doctorEntity, Long first, Long limit);

    @Select("SELECT * FROM DOCTOR_INFO WHERE PHONE = #{phone}")
    DoctorEntity findByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO DOCTOR_INFO (PHONE, PASSWORD, NAME, SEX, PRACTICE_CODE, HOSPITAL, TITLE, DEPARTMENT, HEAD_PIC, PRACTICE_PIC, ADEPT, EXPERIENCE, VERIFY) VALUES (#{phone}, #{password}, #{name}, #{sex}, #{practice_code}, #{hospital}, #{title}, #{department}, #{head_pic}, #{practice_pic}, #{adept}, #{experience}, #{verify})")
    Integer addDoctor(DoctorEntity doctorEntity);

    @UpdateProvider(type = DoctorProvider.class, method = "updateDoctor")
    Integer editDoctor(DoctorEntity doctorEntity);

    @Update("UPDATE DOCTOR_INFO SET VERIFY = #{verify} WHERE PHONE = #{phone}")
    Integer verifyDoctor(@Param("phone") String phone, @Param("verify") String verify);

    @Delete("DELETE FROM DOCTOR_INFO WHERE PHONE = #{phone}")
    Integer deleteDoctor(@Param("phone") String phone);

    @Insert("INSERT INTO REMINDERS (PHONE, TITLE, CONTENT) VALUES (#{phone}, #{title}, #{content})")
    Integer sendMsg(@Param("phone") String phone, @Param("title") String title, @Param("content") String content);

    @Select("SELECT * FROM DOCTOR_SERVICE WHERE SERVICE_ID = #{service_id}")
    List<DoctorServiceEntity> getDoctorServiceByServiceId(@Param("service_id") Long service_id);

}
