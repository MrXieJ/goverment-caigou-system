package com.caigou.admin.controller;

import com.caigou.admin.entity.*;
import com.caigou.admin.service.RiskService;
import com.caigou.admin.service.SupplierService;
import com.caigou.admin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    RiskService riskService;

    /**
     * 供应商和供应商品连表查询，支持分页
     * */
    @RequestMapping("/find")
    public Map findsup(HttpServletRequest request){
        Long limit = -1l;
        Long first = -1l;
        if (request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) *limit;
        }
        Map map=new HashMap();
        Supplier supplier=new Supplier();
        supplier.setSupplier_id(request.getParameter("supplier_id"));
        supplier.setSupplier_name(request.getParameter("supplier_name"));
        supplier.setSup_item_name(request.getParameter("item_name"));
        supplier.setSup_item_type(request.getParameter("item_type"));
        supplier.setPage(first);
        supplier.setLimit(limit);
        try{
            List<Supplier> supplierList=supplierService.findSup(supplier);
            map.put("code",0);
            map.put("data",supplierList);
        }catch(Exception e){
            map.put("errormessage",e.getMessage());
            map.put("code",1);
        }
        return map;
    }

    /**
     * 供应商品查询，支持分页
     * */
    @RequestMapping("/item/find")
    public Map findSupitem(Supplier_item supplier_item){
        Long limit = -1l;
        Long first = -1l;
        if (supplier_item.getPage() != null) {
            limit = supplier_item.getLimit();
            first = (supplier_item.getPage() - 1) *limit;
        }
        supplier_item.setPage(first);
        supplier_item.setLimit(limit);
        Map map=new HashMap();
        try{
            List<Supplier_item> supplier_itemresult=supplierService.findSupitem(supplier_item);
            map.put("code",0);
            map.put("data",supplier_itemresult);
        }catch (Exception e){
            map.put("code",0);
            map.put("errormessage",e.getMessage());
        }

        return map;
    }

    /**
     * 添加供应商信息,供应商编号自动生成
     * */
    @Transactional
    @RequestMapping("/info/add")
    public Map supInfoadd(Supplier_info supplier_info){
        Map map=new HashMap();
        supplier_info.setSupplier_id("GYS"+ DateUtils.get_sup_FormatDate(new Date())+String.valueOf(1000+supplierService.find_max_supid()));
        supplier_info.setCreate_time(DateUtils.getNowFormatDate(new Date()));
        try {
            supplierService.addSup(supplier_info);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 添加供应商品信息
     * 需要提供供应商id,商品编号自动生成
     * */
    @Transactional
    @RequestMapping("/item/add")
    public Map supItemadd(Supplier_item supplier_item){
        Map map=new HashMap();
        try{
            supplier_item.setItem_supid("SPGY"+String.valueOf(1000000000+supplierService.find_max_supitem_id()));
            supplier_item.setItem_addtime(DateUtils.getNowFormatDate(new Date()));
            /*查找出商品供应商*/
            Supplier_info supplier_info=supplierService.findSupbyid(supplier_item.getSup_id());
            /*插入供应商名字*/
            supplier_item.setSup_name(supplier_info.getSupplier_name());
            if(supplier_info==null){
                map.put("code",1);
                map.put("errormessage","no supplier exist");
                return map;
            }
            /*如果供应商还没有提供商品，则将新添加的商品加上*/
            if(supplier_info.getSup_item_type().equals("无")){
                supplier_info.setSup_item_name(supplier_item.getItem_name());
                supplier_info.setSup_item_type(supplier_item.getItem_type());
                supplierService.editSup(supplier_info);
            }
            else {
            /*查看供应商是否含有该商品*/
                if (!supplier_info.getSup_item_name().contains(supplier_item.getItem_name())) {
                    StringBuffer namesb = new StringBuffer();
                    namesb.append(supplier_info.getSup_item_name() + "," + supplier_item.getItem_name());
                    supplier_info.setSup_item_name(namesb.toString());
                    StringBuffer typesb = new StringBuffer();
                    typesb.append(supplier_info.getSup_item_type() + "," + supplier_item.getItem_type());
                    supplier_info.setSup_item_type(typesb.toString());
                    /*更新入供应商数据库*/
                    supplierService.editSup(supplier_info);
                }
            }
            /*插入一个供应商品*/
            supplierService.addSupitem(supplier_item);
            /*插入商品风险模型设置表*/
            Risk_model risk_model=new Risk_model();
            risk_model.setItem_name(supplier_item.getItem_name());
            risk_model.setItem_type(supplier_item.getItem_type());
            risk_model.setItem_order(supplier_item.getItem_order());
            risk_model.setLast_edit_time(DateUtils.getNowFormatDate(new Date()));
            riskService.add(risk_model);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 更新供应商信息
     * 需要提供供应商id
     * */
    @Transactional
    @RequestMapping("/info/update")
    public Map supInfoupdate(Supplier_info supplier_info){
        Map map=new HashMap();
        try{
            supplierService.editSup(supplier_info);
            map.put("code",0);
        }catch (Exception e)
        {
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 更新供应商品信息
     * */
    @Transactional
    @RequestMapping("/item/update")
    public Map supItemupdate(Supplier_item supplier_item){
        Map map=new HashMap();
        try{
            supplierService.editSupitem(supplier_item);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 评价供应商
     * */
    @Transactional
    @RequestMapping("/evaluate")
    public Map evaluateSup(Supplier_evaluate supplier_evaluate){
        Map map=new HashMap();
        supplierService.find_evaluate_sup(supplier_evaluate);
        try{
            if(supplierService.find_evaluate_sup(supplier_evaluate)==null) {
            /*产生一条评价*/
                supplierService.evaluateSup(supplier_evaluate);
            }else{
                /*更新一条评价*/
                supplierService.update_evaluate(supplier_evaluate);
            }
            /*将评价分数更新到供应商基本信息表中*/
            Supplier_info supplier_info=new Supplier_info();
            supplier_info.setEvaluate_score(supplier_evaluate.getSup_score());
            supplier_info.setSupplier_id(supplier_evaluate.getSup_id());
            supplierService.editSup(supplier_info);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 根据商品编号查询供应商和供应商风险信息
     * 根据供应商名字去重
     * */
    @RequestMapping("name/find/byorder")
    public Map find_name_byorder(Supplier_item supplier_item){
        Map map=new HashMap();
        try{
            /*商品编号*/
            String item_order=supplier_item.getItem_order();

            /*商品最高价格*/
            double max_price=-1;

            /*商品最低价格*/
            double min_price=1000000000;

            /*最低价商品供应编号*/
            String low_supitem_price_order=null;

            /*最高价商品供应编号*/
            String high_supitem_price_order=null;

            /*高于平均差的商品供应编号*/
            List<String> deviation_supitem_warn_order=new ArrayList<>();

            /*供应商名称列表*/
            List<String> sup_list=new ArrayList<>();

            /*供应商品列表安全价格范围外*/
            List<String> item_outof_range=new ArrayList<>();

            /*供应商品列表超出标准差*/
            List<String> deviation_supitem_hide_order=new ArrayList<>();

            /*根据供应商品编号查询出所有供应商品*/
            List<Supplier_item> supplier_items=supplierService.find_supitem_byorder(supplier_item);

            /*是否设置风险模型*/
            Risk_model risk_model=riskService.find_by_order(item_order);
            int status=risk_model.getStatus();
            String secure_price=risk_model.getSecure_price_range();

            /*如果不为空*/
            if(supplier_items!=null) {
                if (status == 0) {
                    /*循环遍历添加供应商名称*/
                    for (Supplier_item supplier_item1 : supplier_items) {
                        String sup_name = supplier_item1.getSup_name();
                        if (!sup_list.contains(sup_name)) {
                            sup_list.add(sup_name);
                        }
                    }
                } else
                /*如果设置风险模型*/
                {
                    for (Supplier_item supplier_item1 : supplier_items) {
                        String sup_name = supplier_item1.getSup_name();
                        String item_supid = supplier_item1.getItem_supid();
                        double price = Double.valueOf(supplier_item1.getItem_price());
                        /*如果设置了安全价格范围*/
                        if (secure_price != null) {
                            float left_price = Float.valueOf(secure_price.split(",")[0]);
                            float right_price = Float.valueOf(secure_price.split(",")[1]);
                            /*如果超出安全范围*/
                            if (left_price >= price || price >= right_price) {
                                item_outof_range.add(item_supid);
                            }
                        }
                        /*如果没有设置标准差检验*/
                        if (risk_model.getDeviation_check() == 0) {
                            /*添加供应商名称*/
                            if (!sup_list.contains(sup_name)) {
                                sup_list.add(sup_name);
                            }
                        }
                        /*如果设置了标准差检验*/
                        if (risk_model.getDeviation_check() == 1) {
                            double price_hide = risk_model.getDeviation_price_hide();
                            double price_warn = risk_model.getDeviation_price_warn();
                            /*计算标准差*/
                            double dvar=0;
                            double sum_price = 0;
                            int count = supplier_items.size();
                            double[] price_list = new double[count];
                            int i=0;
                            for (Supplier_item supplier_item2 : supplier_items) {
                                price_list[i]=Double.valueOf(supplier_item2.getItem_price()).doubleValue();
                                sum_price += Double.valueOf(supplier_item2.getItem_price());
                                i++;
                            }
                            double average=sum_price/count;
                            for(int j=0;j<count;j++){
                                double temp=price_list[j];
                                dvar+=(temp-average)*(temp-average);
                            }
                            double biaozhuntemp=dvar/count;
                            double biaozhuncha=Math.sqrt(biaozhuntemp);
                            /*有设置标准差隐藏*/
                            if (price_hide != -10000) {
                               /*如果大于设置值*/
                               if(biaozhuncha>=price_hide) {
                                   if (!deviation_supitem_hide_order.contains(sup_name)) {
                                       deviation_supitem_hide_order.add(sup_name);
                                   }
                               }
                            } else if (price_warn != -10000) {
                                if(biaozhuncha>price_warn){
                                    if(!deviation_supitem_warn_order.contains(item_supid)){
                                        deviation_supitem_warn_order.add(item_supid);
                                    }
                                }
                            }
                        }
                    /*如果设置最高价提醒*/
                        if (risk_model.getPrice_set_high() == 1) {
                            if (price > max_price) {
                                max_price = price;
                                high_supitem_price_order = item_supid;
                            }
                        }
                    /*如果设置最低价提醒*/
                        if (risk_model.getPrice_set_low() == 1) {
                            if (price < min_price) {
                                min_price = price;
                                low_supitem_price_order = item_supid;
                            }
                        }
                    }
                }
            }
            map.put("code",0);
            map.put("sup_name",sup_list);
            map.put("low_supitem_price_order",low_supitem_price_order);
            map.put("high_supitem_price_order",high_supitem_price_order);
            map.put("deviation_supitem_warn_order",deviation_supitem_warn_order);
            map.put("out_of_secure_range",item_outof_range);
            map.put("deviation_supitem_warn_order",deviation_supitem_warn_order);
        }catch (Exception e){
            map.put("code",1);
            map.put("error_message",e.getMessage());
        }
        return map;
    }

    /**
     * 根据商品编号，供应商名称查询商品价格，商品供应编号
     * */
    @RequestMapping("item/supid/find")
    public Map item_supid_find(Supplier_item supplier_item){
        Map map=new HashMap();
        try{
            List<Supplier_item> supplier_items=supplierService.find_supitem_bycondition(supplier_item);
            map.put("code",0);
            map.put("data",supplier_items);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }
}
