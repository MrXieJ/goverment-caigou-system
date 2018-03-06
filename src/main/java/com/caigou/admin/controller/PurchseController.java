package com.caigou.admin.controller;


import com.caigou.admin.entity.*;
import com.caigou.admin.service.ItemService;
import com.caigou.admin.service.PurchaseService;
import com.caigou.admin.service.UserService;
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
@RequestMapping("/purchase")
public class PurchseController {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;

    /**
     * 个人采购申请查询,支持分页，支持分时间段
     * 时间参数用apply_date,分为开始时间和结束时间，两个时间用,分隔
     * 开始时间为2017-12-11 11:11:11
     * 结束时间为2017—12—30 11:11:11
     * 则应该提供请求参数apply_date=2017-12-11 11:11:11,2017—12—30 11:11:11
     * */
    @Transactional
    @RequestMapping("/apply/findbyuser")
    public Map findbyuser(Purchase_apply purchase_apply){
        Long limit = -1l;
        Long first = -1l;
        if (purchase_apply.getPage() != null) {
            limit = purchase_apply.getLimit();
            first = (purchase_apply.getPage() - 1) *limit;
        }
             purchase_apply.setPage(first);
             purchase_apply.setLimit(limit);
             Map map=new HashMap();
        try {
            List<Purchase_apply> purchase_applies =purchaseService.findApply(purchase_apply);
            map.put("code",0);
            map.put("data",purchase_applies);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
            return map;
    }

    /**
     * 全部采购申请查询,支持分页，支持分时间段
     * 时间参数用apply_date,分为开始时间和结束时间，两个时间用,分隔
     * 开始时间为2017-12-11 11:11:11
     * 结束时间为2017—12—30 11:11:11
     * 则应该提供请求参数apply_date=2017-12-11 11:11:11,2017—12—30 11:11:11
     * */
    @Transactional
    @RequestMapping("/apply/findall")
    public Map findAll(Purchase_apply purchase_apply){
        Long limit = -1l;
        Long first = -1l;
        if (purchase_apply.getPage() != null) {
            limit = purchase_apply.getLimit();
            first = (purchase_apply.getPage() - 1) *limit;
        }
        purchase_apply.setPage(first);
        purchase_apply.setLimit(limit);
        Map map=new HashMap();
        try {
            List<Purchase_apply> purchase_applies =purchaseService.findBycondition(purchase_apply);
            map.put("code",0);
            map.put("data",purchase_applies);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 暂存采购申请，需要提交用户账号，和一些页面基本参数
     * */
    @Transactional
    @RequestMapping("/apply/add")
    public Map applyadd(Purchase_apply purchase_apply){
        Map map=new HashMap();
        try {
            User user=userService.getByUsername(purchase_apply.getApply_user_account());
            /*设置申请人基本信息*/
            purchase_apply.setApply_department(user.getDepartment());
            purchase_apply.setApply_user(user.getUser_realname());
            purchase_apply.setApply_state("未提交");
            purchase_apply.setDeny_reason("无");
            /*设置申请编号*/
            purchase_apply.setApply_order("SQCG"+String.valueOf(1000000000+purchaseService.find_max_id()));
            /*添加申请*/
            purchaseService.addApply(purchase_apply);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 提交采购申请
     * */
    @Transactional
    @RequestMapping("/apply/commit")
    public Map applycommit(Purchase_apply purchase_apply){
        Map map=new HashMap();
        try {
            /*设置申请提交时间*/
            purchase_apply.setApply_date(DateUtils.getNowFormatDate(new Date()));
            purchase_apply.setApply_state("已提交");
            /*添加申请*/
            purchaseService.editApply(purchase_apply);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 根据采购申请的编号查询出所有申请商品
     * */
    @Transactional
    @RequestMapping("/apply/items/get")
    public Map apply_items_get(Apply_item apply_item){
        Map map=new HashMap();
        try {
            List<Apply_item> apply_items=purchaseService.find_items_by_order(apply_item.getApply_order());
            map.put("code",0);
            map.put("data",apply_items);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 删除采购商品里面的商品,根据apply_order和item_order删除
     * */
    @Transactional
    @RequestMapping("/apply/item/delete")
    public Map applydelete(Apply_item apply_item){
        Map map=new HashMap();
        try {
            purchaseService.deleteApply(apply_item);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 1：编辑采购商品里面的商品，需要提供apply_order和商品的编号item_find_order
     * 编辑商品后商品的item_order发生变化，item_order字段代表编辑后的商品id
     * item_find_order代表编辑前商品的编号
     * 需要编辑前的编号去更新特定的商品
     * */
    @Transactional
    @RequestMapping("/apply/item/edit")
    public Map applyedit(Apply_item apply_item){
        Map map=new HashMap();
        try {
            purchaseService.editApplyitem(apply_item);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 将采购单申请状态设置为审核中,需要提供采购单编号
     * */
    @Transactional
    @RequestMapping("apply/check")
    public Map applycheck(Purchase_apply purchase_apply){
    Map map=new HashMap();
    purchase_apply.setApply_state("审核中");
    try{
        purchaseService.editApply(purchase_apply);
        map.put("code",0);
    }catch (Exception e){
        map.put("code",1);
        map.put("message",e.getMessage());
    }
    return map;
    }

    /**
     * 审核采购单申请
     * */
    @Transactional
    @RequestMapping("/apply/check/commit")
    public Map applycheckcommit(Purchase_check purchase_check){
        Map map=new HashMap();
        Purchase_apply purchase_apply=new Purchase_apply();
        purchase_apply.setApply_order(purchase_apply.getApply_order());
        try {
            /*设置审核日期*/
            purchase_check.setCheck_date(DateUtils.getNowFormatDate(new Date()));

            /*设置审核人信息*/
            User user=userService.getByUsername(purchase_check.getCheck_user_account());
            purchase_check.setCheck_user(user.getUser_realname());

            /*添加审核人信息*/
            purchase_apply.setApply_check_user(purchase_check.getCheck_user());

            /*更新申请表*/
            if(purchase_check.getCheck_status()==0){
                purchase_apply.setApply_state("审核通过");
                purchaseService.editApply(purchase_apply);
            }else{
                purchase_apply.setApply_state("审核未通过");
                purchase_apply.setDeny_reason(purchase_check.getDeny_reason());
                purchaseService.editApply(purchase_apply);
            }
            /*添加审核记录*/
            purchaseService.checkApply(purchase_check);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 采购单确认领取
     * 参数：采购单编号
     * */
    @Transactional
    @RequestMapping("/apply/accept")
    public Map applyaccept(Purchase_apply purchase_apply){
        /*设置领取时间*/
        purchase_apply.setPurchase_item_accept_time(DateUtils.getNowFormatDate(new Date()));
        /*设置状态为已领取*/
        purchase_apply.setApply_state("已领取");
        Map map=new HashMap();
        try{
            purchaseService.editApply(purchase_apply);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 采购单完善提交
     * 参数：采购单编号,所有申请的商品数组
     * 采购商品数组需要提供字段item_find_order（商品编号）来和申请编号组合查询更新商品的供应渠道信息
     * */
    @Transactional
    @RequestMapping("/apply/consummate")
    public Map apply_consummate(Purchase_apply purchase_apply){
        Map map=new HashMap();
        try{
            purchase_apply.setApply_state("采购中");
            for(Apply_item apply_item:purchase_apply.getApply_item()) {
                purchaseService.editApplyitem(apply_item);
            }
            purchaseService.editApply(purchase_apply);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 采购完毕
     * 参数：采购单编号
     * */
    @Transactional
    @RequestMapping("/apply/complete")
    public Map applycomplete(Purchase_apply purchase_apply){
        /*设置采购完毕时间*/
        purchase_apply.setPurchase_complete_time(DateUtils.getNowFormatDate(new Date()));
        /*设置采购单状态为采购完毕*/
        purchase_apply.setApply_state("采购完毕");
        Map map=new HashMap();
        try{
            purchaseService.update_order(purchase_apply);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("error_message",e.getMessage());
        }
        return map;
    }


}
