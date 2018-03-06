package com.caigou.admin.service;

import com.caigou.admin.dao.SupplierMapper;
import com.caigou.admin.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierMapper supplierMapper;
    /**
     * 插入一条商品价格记录
     * */
    public Integer add_sup_price(Sup_item_price sup_item_price){
        return supplierMapper.add_supitem_price(sup_item_price);
    }

    /**
     * 查询出指定商品的价格记录
     * */
    public Integer find_sup_price(String sup_item_id){
        return supplierMapper.find_supitem_price(sup_item_id);
    }

    /**
     * 查出指定商品编号的供应商名称
     * */
    public List<Supplier_item> find_supitem_byorder(Supplier_item supplier_item){
        return supplierMapper.findSupitem_byorder(supplier_item);
    }

    /**
     * 查出商品供应编号和价格，根据供应商名称和商品编号
     * */
    public List<Supplier_item> find_supitem_bycondition(Supplier_item supplier_item){
        return supplierMapper.finditem_bycondition(supplier_item);
    }

    /**
     * 查询供应商品最大id
     **/
    public int find_max_supitem_id(){return supplierMapper.find_max_supitem_id();}

    /**
     * 查询供应商最大id
     **/
    public int find_max_supid(){return supplierMapper.find_max_supid();}

    /**
     * 查询供应商
     * */
    public List<Supplier> findSup(Supplier supplier){return supplierMapper.findAll(supplier);}

    /**
     * 根据供应商id查找
     * */
    public Supplier_info findSupbyid(String supplier_id){return supplierMapper.getSupplier(supplier_id);}

    /**
     * 供应商基本信息添加
     * */
    public Integer addSup(Supplier_info supplier_info){return supplierMapper.insertInfo(supplier_info);}

    /**
     * 供应商基本信息编辑
     * */
    public Integer editSup(Supplier_info supplier_info){return supplierMapper.updateInfo(supplier_info);}

    /**
     * 查询供应产品
     * */
    public List<Supplier_item> findSupitem(Supplier_item supplier_item){return supplierMapper.findSupitem(supplier_item);}

    /**
     * 供应产品添加
     * */
    public Integer addSupitem(Supplier_item supplier_item){return supplierMapper.insertItem(supplier_item);}

    /**
     * 供应产品编辑
     * */
    public Integer editSupitem(Supplier_item supplier_item){return supplierMapper.updateItem(supplier_item);}

    /**
     * 供应商评分
     * */
    public Integer evaluateSup(Supplier_evaluate supplier_evaluate){return supplierMapper.evaluateSup(supplier_evaluate);}

    /**
     * 更新供应商评分
     * */
    public Integer update_evaluate(Supplier_evaluate supplier_evaluate){return supplierMapper.update_evaluate_Sup(supplier_evaluate);}
    /**
     * 查询供应商评分
     * */
    public Integer find_evaluate_sup(Supplier_evaluate supplier_evaluate){return supplierMapper.findevaluateSup(supplier_evaluate);}
}
