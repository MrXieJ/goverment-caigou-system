package com.caigou.admin.controller;

import com.caigou.admin.entity.Risk_model;
import com.caigou.admin.service.RiskService;
import com.caigou.admin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/riskmodel")
public class RiskController {

    @Autowired
    RiskService riskService;

    /**
     * 根据组合参数查询商品风险设置列表，支持分页
     * */
    @RequestMapping("/find")
    public Map find(Risk_model risk_model){
        Long limit = -1l;
        Long first = -1l;
        if (risk_model.getPage() != null) {
            limit = risk_model.getLimit();
            first = (risk_model.getPage() - 1) *limit;
        }
        risk_model.setPage(first);
        risk_model.setLimit(limit);
        Map map=new HashMap();
        try{
            List<Risk_model> risk_models=riskService.find(risk_model);
            map.put("code",0);
            map.put("data",risk_models);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }


    /**
     * 商品风险模型参数设置编辑
     * */
    @Transactional
    @RequestMapping("/edit")
    public Map edit(Risk_model risk_model){
        risk_model.setLast_edit_time(DateUtils.getNowFormatDate(new Date()));
        Map map=new HashMap();
        try{
            riskService.edit(risk_model);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }
}
