package com.jybl.admin.service;

import com.jybl.admin.dao.OrderMapper;
import com.jybl.admin.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public Integer getCount() {
        return orderMapper.getCount();
    }


    public List<OrderEntity> findAll(OrderEntity orderEntity, Long first, Long limit) {
        List<OrderEntity> list = orderMapper.findAll(orderEntity, first, limit);
        int i = 1;
        for (OrderEntity oe : list) {
            oe.setId(Integer.toUnsignedLong(i));
            String time = oe.getPurchased_time();
            oe.setPurchased_time(time.substring(0, time.length() - 2));
            switch(Integer.valueOf(oe.getIndent_status())) {
                case 0:
                    oe.setIndent_status("未付款");
                    break;
                case 1:
                    oe.setIndent_status("已支付");
                    break;
                case 2:
                    oe.setIndent_status("已完成");
                    break;
                case 99:
                    oe.setIndent_status("已过期");
                    break;
            }
            i++;
        }
        return list;
    }


    public Integer getCountByWechatId(String wechat_id) {
        return orderMapper.getCountByWechatId(wechat_id);
    }


    public List<OrderEntity> findByWechatId(String wechat_id, Long first, Long limit) {
        List<OrderEntity> list = orderMapper.findByWechatId(wechat_id, first, limit);

        int i = 1;
        for (OrderEntity orderEntity : list) {
            orderEntity.setId(Integer.toUnsignedLong(i)) ;
            String time = orderEntity.getPurchased_time();
            orderEntity.setPurchased_time(time.substring(0, time.length() - 2));
            switch(Integer.valueOf(orderEntity.getIndent_status())) {
                case 0:
                    orderEntity.setIndent_status("未付款");
                    break;
                case 1:
                    orderEntity.setIndent_status("已支付");
                    break;
                case 2:
                    orderEntity.setIndent_status("已完成");
                    break;
                case 99:
                    orderEntity.setIndent_status("已过期");
                    break;
            }
            i++;
        }

        return list;
    }


    public Integer getCountByPhone(String doc_phone) {
        return orderMapper.getCountByPhone(doc_phone);
    }


    public List<OrderEntity> findByPhone(String doc_phone, Long first, Long limit) {
        List<OrderEntity> list = orderMapper.findByPhone(doc_phone, first, limit);
        int i = 1;
        for (OrderEntity orderEntity : list) {
            orderEntity.setId(Integer.toUnsignedLong(i));
            String time = orderEntity.getPurchased_time();
            orderEntity.setPurchased_time(time.substring(0, time.length() - 2));
            switch (Integer.valueOf(orderEntity.getIndent_status())) {
                case 0:
                    orderEntity.setIndent_status("未付款");
                    break;
                case 1:
                    orderEntity.setIndent_status("已支付");
                    break;
                case 2:
                    orderEntity.setIndent_status("已完成");
                    break;
                case 99:
                    orderEntity.setIndent_status("已过期");
                    break;
            }
            i++;
        }
        return list;
    }

}
