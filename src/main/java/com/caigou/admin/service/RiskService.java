package com.caigou.admin.service;

import com.caigou.admin.dao.RiskMapper;
import com.caigou.admin.entity.Risk_model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskService {
    @Autowired
    RiskMapper riskMapper;

    public List<Risk_model> find(Risk_model risk_model){
        return riskMapper.findAll(risk_model);
    }

    public Integer add(Risk_model risk_model)
    {
        return riskMapper.risk_model_add(risk_model);
    }
    public Integer edit(Risk_model risk_model)
    {
        return riskMapper.risk_model_update(risk_model);
    }

    public Risk_model find_by_order(String item_order){
        return riskMapper.find_by_order(item_order);
    }
}
