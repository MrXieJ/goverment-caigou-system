package com.jybl.admin.service;

import com.jybl.admin.dao.DoctorMapper;
import com.jybl.admin.entity.DoctorEntity;
import com.jybl.admin.entity.DoctorServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    public Integer getCount() {
        return doctorMapper.getCount();
    }

    public List<DoctorEntity> findAll(DoctorEntity doctorEntity, Long first, Long limit) {

        List<DoctorEntity> list = doctorMapper.findAll(doctorEntity, first, limit);
        int i = 1;
        for (DoctorEntity de : list) {
            de.setId(Integer.toUnsignedLong(i));
            String time = de.getDatetime();
            de.setDatetime(time.substring(0, time.length() - 2));
            i++;
        }

        return list;
    }

    public DoctorEntity findByPhone(String phone) {
        return doctorMapper.findByPhone(phone);
    }


    public Integer addDoctor(DoctorEntity doctorEntity) {
        return doctorMapper.addDoctor(doctorEntity);
    }

    public Integer editDoctor(DoctorEntity doctorEntity){
        return doctorMapper.editDoctor(doctorEntity);
    }

    public Integer verifyDoctor(String phone, String verify){
        doctorMapper.verifyDoctor(phone, verify);
        doctorMapper.sendMsg(phone, "审核状态", "您的个人资料已通过认证");
        return 0;
    };

    public Integer deleteDoctor(String phone) {
        return doctorMapper.deleteDoctor(phone);
    };

    public Integer sendRemind(String phone, String title, String content) {
        return doctorMapper.sendMsg(phone, title, content);
    }

    public Integer sendServiceDeleteMsg(Long serviceId) {
        for(DoctorServiceEntity ds : doctorMapper.getDoctorServiceByServiceId(serviceId)) {
            doctorMapper.sendMsg(ds.getDoctor_phone(), "服务状态", "您的添加的服务\"" + ds.getService_name() + "\"已被管理员删除");
        }
        return 0;
    }


}
