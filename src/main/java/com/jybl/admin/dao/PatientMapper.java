package com.jybl.admin.dao;

import com.jybl.admin.dao.provider.PatientProvider;
import com.jybl.admin.entity.BloodPressureEntity;
import com.jybl.admin.entity.HealthEntity;
import com.jybl.admin.entity.PatientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface PatientMapper {

    @Select("SELECT COUNT(*) FROM PATIENT_INFO")
    Integer getCount();

    @SelectProvider(type = PatientProvider.class, method = "selectPatient")
    List<PatientEntity> findAll(PatientEntity patientEntity, Long first, Long limit);

    @Select("SELECT * FROM PATIENT_INFO WHERE WECHAT_ID = #{wechat_id}")
    PatientEntity findByWechatId(@Param("wechat_id") String wechat_id);

    @Select("SELECT * FROM HEALTH_INFO WHERE WECHAT_ID = #{wechat_id}")
    HealthEntity getHealthByWechatId(@Param("wechat_id") String wechat_id);

    @Select("SELECT * FROM BLOOD_PRESSURE WHERE WECHAT_ID = #{wechat_id}")
    List<BloodPressureEntity> getBloodPressureByWechatId(@Param("wechat_id") String wechat_id);


}
