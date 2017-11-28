package com.jybl.admin.service;

import com.jybl.admin.dao.ServiceMapper;
import com.jybl.admin.entity.DoctorServiceEntity;
import com.jybl.admin.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    ServiceMapper serviceMapper;

    public Integer getCount(){
        return serviceMapper.getCount();
    }


    public List<ServiceEntity> findAll(ServiceEntity serviceEntity, Long first, Long limit) {
        List<ServiceEntity> list = serviceMapper.findAll(serviceEntity, first, limit);

        int i = 1;
        for (ServiceEntity se : list) {
            if (se.getStatus().equals("1")) {
                se.setStatus("已激活");
            } else {
                se.setStatus("冻结");
            }
            se.setRid(Integer.toUnsignedLong(i));
            i++;
        }
        return list;
    }


    public Integer addService(ServiceEntity serviceEntity) {
        return serviceMapper.addService(serviceEntity);
    }


    public Integer updateService(ServiceEntity serviceEntity) {
        return serviceMapper.updateService(serviceEntity);
    }


    public Integer updateServiceStatus(Long id, Integer status) {

        serviceMapper.updateServiceStatus(id, status);

        if (status == 0) {
            serviceMapper.updateDocServiceStatusByServiceId(id, status);
        }
        return 0;
    }


    public Integer deleteService(Long id) {
        serviceMapper.deleteService(id);
        serviceMapper.deleteDoctorService(id);
        return 0;
    }


    public List<DoctorServiceEntity> findDoctorService(String phone, Long first, Long limit) {
        List<DoctorServiceEntity> list = serviceMapper.findDoctorService(phone, first, limit);

        int i = 1;
        for (DoctorServiceEntity doctorServiceEntity : list) {
            if (doctorServiceEntity.getAdded_status() == 0) {
                doctorServiceEntity.setAdded_time("已下架");
            }
            doctorServiceEntity.setRid(Integer.toUnsignedLong(i));
            i++;
        }

        return list;

    }

    public Integer getDocServiceCount(String phone) {
        return serviceMapper.getDocServiceCount(phone);
    }


    public Integer updateDocServiceStatusWithId(Long id, Integer status, String time) {
        return serviceMapper.updateDocServiceStatusById(id, status, time);
    }

    public List<ServiceEntity> findAllAvailable(String phone, Long first, Long limit) {
        List<ServiceEntity> list = serviceMapper.findAllAvailable(phone, first, limit);

        int i = 1;
        for (ServiceEntity serviceEntity : list) {
            serviceEntity.setRid(Integer.toUnsignedLong(i));
            i++;
        }

        return list;
    }


    public Integer getAvailableCount(String phone) {
        return serviceMapper.getAvailableCount(phone);
    }

    public Integer addDocService(DoctorServiceEntity doctorServiceEntity) {
        return serviceMapper.addDocService(doctorServiceEntity);
    }

}
