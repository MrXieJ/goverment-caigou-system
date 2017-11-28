package com.jybl.admin.service;

import com.jybl.admin.dao.PatientMapper;
import com.jybl.admin.entity.BloodPressureEntity;
import com.jybl.admin.entity.HealthEntity;
import com.jybl.admin.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientMapper patientMapper;

    public Integer getCount() {
        return patientMapper.getCount();
    }


    public List<PatientEntity> findAll(PatientEntity patientEntity, Long first, Long limit) {
        List<PatientEntity> list = patientMapper.findAll(patientEntity, first, limit);

        int i = 1;
        for (PatientEntity pe : list) {
            pe.setId(Integer.toUnsignedLong(i));
            i++;
        }

        return list;
    }

    public PatientEntity findByWechatId(String wechat_id) {
        return patientMapper.findByWechatId(wechat_id);
    }


    public HealthEntity getHealthByWechatId(String wechat_id) {
        return patientMapper.getHealthByWechatId(wechat_id);
    }


    public List<BloodPressureEntity> getBloodPressureByWechatId(String wechat_id) {
        return patientMapper.getBloodPressureByWechatId(wechat_id);
    }

}
