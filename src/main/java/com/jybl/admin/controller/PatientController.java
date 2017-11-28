package com.jybl.admin.controller;

import com.jybl.admin.entity.BloodPressureEntity;
import com.jybl.admin.entity.HealthEntity;
import com.jybl.admin.entity.PatientEntity;
import com.jybl.admin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping("/listAll")
    public Map findAllPatient(HttpServletRequest request) {

        Long first = 1L, limit = 30L;

        if (request.getParameter("page") != null && request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) * limit;
        }

        PatientEntity pe = new PatientEntity();

        if (request.getParameter("sex") != null && request.getParameter("sex").length() > 0){
            pe.setSex(request.getParameter("sex"));
        }

        if (request.getParameter("kind") != null && request.getParameter("kind").length() > 0){
            pe.setKind(request.getParameter("kind"));
        }

        if (request.getParameter("age") != null && request.getParameter("age").length() > 0){
            pe.setAge(Integer.valueOf(request.getParameter("age")));
        }

        if (request.getParameter("count") != null && request.getParameter("count").length() > 0){
            pe.setCount(Integer.valueOf(request.getParameter("count")));
        }

        List<PatientEntity> list = patientService.findAll(pe, first, limit);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", patientService.getCount());
        map.put("data", list);
        return map;
    }

    @RequestMapping("/listDetail")
    public Map findPatientDetail(HttpServletRequest request) {

        String wechatId = "";

        if (request.getParameter("wechatId") != null && request.getParameter("wechatId") != null) {
            wechatId = request.getParameter("wechatId");
        }

        Map map = new HashMap();

        PatientEntity pe = patientService.findByWechatId(wechatId);

        HealthEntity he = patientService.getHealthByWechatId(wechatId);

        List<BloodPressureEntity> bpe = patientService.getBloodPressureByWechatId(wechatId);

        map.put("code", 0);
        map.put("patientInfo", pe);
        map.put("healthInfo", he);
        map.put("bloodPressure", bpe);

        return map;
    }

}
