package com.jybl.admin.controller;


import com.jybl.admin.entity.DoctorEntity;
import com.jybl.admin.dao.DoctorMapper;
import com.jybl.admin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/listAll")
    public Map findAllDoctor(HttpServletRequest request) {

        Long first = 1L, limit = 30L;

        if (request.getParameter("page") != null && request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) * limit;
        }

        DoctorEntity de = new DoctorEntity();

        if (request.getParameter("sex") != null && request.getParameter("sex").length() > 0) {
            de.setSex(request.getParameter("sex"));
        }

        if (request.getParameter("title") != null && request.getParameter("title").length() > 0) {
            de.setTitle(request.getParameter("title"));
        }

        if (request.getParameter("verify") != null && request.getParameter("verify").length() > 0) {
            de.setVerify(request.getParameter("verify"));
        }

        if (request.getParameter("name") != null && request.getParameter("name").length() > 0) {
            de.setName(request.getParameter("name"));
        }

        if (request.getParameter("department") != null && request.getParameter("department").length() > 0) {
            de.setDepartment(request.getParameter("department"));
        }

        List<DoctorEntity> list = doctorService.findAll(de, first, limit);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", doctorService.getCount());
        map.put("data", list);
        return map;
    }

    @RequestMapping("/info")
    public Map findDoctorInfo(HttpServletRequest request) {

        String phone = "";
        if (request.getParameter("phone") != null && request.getParameter("phone").length() > 0) {
            phone = request.getParameter("phone");
        }

        DoctorEntity de = doctorService.findByPhone(phone);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("doctor", de);
        return map;
    }

    @RequestMapping("/add")
    public Map addNewDoctor(HttpServletRequest request) {

        DoctorEntity de = new DoctorEntity();

        if (request.getParameter("phone") != null ) {
            de.setPhone(request.getParameter("phone"));
        }

        if (request.getParameter("password") != null ) {
            de.setPassword(request.getParameter("password"));
        }

        if (request.getParameter("name") != null ) {
            de.setName(request.getParameter("name"));
        }

        if (request.getParameter("sex") != null) {
            de.setSex(request.getParameter("sex"));
        }

        if (request.getParameter("practiceCode") != null) {
            de.setPractice_code(request.getParameter("practiceCode"));
        }

        if (request.getParameter("hospital") != null) {
            de.setHospital(request.getParameter("hospital"));
        }

        if (request.getParameter("title") != null) {
            de.setTitle(request.getParameter("title"));
        }

        if (request.getParameter("department") != null) {
            de.setDepartment(request.getParameter("department"));
        }

        if (request.getParameter("headPic") != null) {
            de.setHead_pic(request.getParameter("headPic"));
        }

        if (request.getParameter("practicePic") != null) {
            de.setPractice_pic(request.getParameter("practicePic"));
        }

        if (request.getParameter("adept") != null) {
            de.setAdept(request.getParameter("adept"));
        }

        if (request.getParameter("experience") != null) {
            de.setExperience(request.getParameter("experience"));
        }

        if (request.getParameter("verify") != null) {
            de.setVerify(request.getParameter("verify"));
        }

        doctorService.addDoctor(de);

        Map map = new HashMap();
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/edit")
    public Map editDoctorInfo (HttpServletRequest request) {

        DoctorEntity de = new DoctorEntity();

        de.setPhone(request.getParameter("phone"));

        if (request.getParameter("password") != null ) {
            de.setPassword(request.getParameter("password"));
        }

        if (request.getParameter("name") != null ) {
            de.setName(request.getParameter("name"));
        }

        if (request.getParameter("sex") != null) {
            de.setSex(request.getParameter("sex"));
        }

        if (request.getParameter("practiceCode") != null) {
            de.setPractice_code(request.getParameter("practiceCode"));
        }

        if (request.getParameter("hospital") != null) {
            de.setHospital(request.getParameter("hospital"));
        }

        if (request.getParameter("title") != null) {
            de.setTitle(request.getParameter("title"));
        }

        if (request.getParameter("department") != null) {
            de.setDepartment(request.getParameter("department"));
        }

        if (request.getParameter("headPic") != null) {
            de.setHead_pic(request.getParameter("headPic"));
        }

        if (request.getParameter("practicePic") != null) {
            de.setPractice_pic(request.getParameter("practicePic"));
        }

        if (request.getParameter("adept") != null) {
            de.setAdept(request.getParameter("adept"));
        }

        if (request.getParameter("experience") != null) {
            de.setExperience(request.getParameter("experience"));
        }

        doctorService.editDoctor(de);

        Map map = new HashMap();
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/verify")
    public Map verifyDoctor (HttpServletRequest request) {

        doctorService.verifyDoctor(request.getParameter("phone"), "已认证");

        Map map = new HashMap();
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/del")
    public Map deleteDoctor (HttpServletRequest request) {

        doctorService.deleteDoctor(request.getParameter("phone"));

        Map map = new HashMap();
        map.put("code", 0);
        return map;
    }

}
