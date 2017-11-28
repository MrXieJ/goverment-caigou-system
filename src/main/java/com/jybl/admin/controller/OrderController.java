package com.jybl.admin.controller;

import com.jybl.admin.entity.OrderEntity;
import com.jybl.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/listAll")
    public Map findAllOrder(HttpServletRequest request) {

        Long first = 1L, limit = 30L;

        if (request.getParameter("page") != null && request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) * limit;
        }

        OrderEntity oe = new OrderEntity();

        if (request.getParameter("status") != null && request.getParameter("status").length() > 0) {
            oe.setIndent_status(request.getParameter("status"));
        }

        if (request.getParameter("name") != null && request.getParameter("name").length() > 0) {
            oe.setName(request.getParameter("name"));
        }

        if (request.getParameter("docName") != null && request.getParameter("docName").length() > 0) {
            oe.setDoctor_name(request.getParameter("docName"));
        }

        if (request.getParameter("patName") != null && request.getParameter("patName").length() > 0) {
            oe.setPatient_name(request.getParameter("patName"));
        }

        if (request.getParameter("startTime") != null && request.getParameter("startTime").length() > 0) {
            if (request.getParameter("endTime") != null && request.getParameter("endTime").length() > 0){
                oe.setPurchased_time(request.getParameter("startTime") + "|" + request.getParameter("endTime"));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                oe.setPurchased_time(request.getParameter("startTime") + "|" + sdf.format(new Date()));
            }
        } else {
            if (request.getParameter("endTime") != null && request.getParameter("endTime").length() > 0){
                oe.setPurchased_time("2000-01-01 00:00:00|" + request.getParameter("endTime"));
            }
        }

        List<OrderEntity> list = orderService.findAll(oe, first, limit);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", orderService.getCount());
        map.put("data", list);
        return map;
    }

    @RequestMapping("/listPatient")
    public Map findOrderWithPatient(HttpServletRequest request) {
        Long first = 1L, limit = 30L;

        if (request.getParameter("page") != null && request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) * limit;
        }

        String wechatId = "";
        if (request.getParameter("wechatId") != null && request.getParameter("wechatId").length() > 0) {
            wechatId = request.getParameter("wechatId");
        }

        List<OrderEntity> list = orderService.findByWechatId(wechatId, first, limit);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", orderService.getCountByWechatId(wechatId));
        map.put("data", list);
        return map;
    }

    @RequestMapping("/listDoctor")
    public Map findOrderWithDoctor(HttpServletRequest request) {
        Long first = 1L, limit = 30L;

        if (request.getParameter("page") != null && request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) * limit;
        }

        String phone = "";
        if (request.getParameter("phone") != null && request.getParameter("phone").length() > 0) {
            phone = request.getParameter("phone");
        }

        List<OrderEntity> list = orderService.findByPhone(phone, first, limit);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", orderService.getCountByPhone(phone));
        map.put("data", list);
        return map;
    }

}
