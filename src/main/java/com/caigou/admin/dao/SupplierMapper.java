package com.caigou.admin.dao;


import com.caigou.admin.dao.provider.SupplierProvider;
import com.caigou.admin.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplierMapper {

    /**
     * 添加供应商品价格记录
     * */
    @Insert("INSERT INTO SUP_ITEM_PRICE (SUP_ITEM_ID,PRICE,TIME) VALUES (#{sup_item_id},#{price},#{time})")
    Integer add_supitem_price(Sup_item_price sup_item_price);

    /**
     * 查询指定供应编号的商品价格变化
     * */
    @Select("SELECT * FROM SUP_ITEM_PRICE WHERE SUP_ITEM_ID = #{sup_item_id}")
    Integer find_supitem_price(@Param("sup_item_id") String sup_item_id);

    /**
     * 查询最大供应商品id
     * */
    @Select("SELECT IFNULL(MAX(ID), 0) FROM SUPPLIER_ITEM")
    int find_max_supitem_id();

    /**
     * 查询最大供应商id
     * */
    @Select("SELECT IFNULL(MAX(ID), 0) FROM SUPPLIER_INFO")
    int find_max_supid();

    /**
     * 供应商查询
     * */
    @SelectProvider(type = SupplierProvider.class, method = "findSupplier")
    List<Supplier> findAll(Supplier sup);

    /**
     * 供应商基本信息查询
     * 通过供应商编号查询
     * */
    @SelectProvider(type = SupplierProvider.class, method = "getSupplier")
    Supplier_info  getSupplier(String supplier_id);

    /**
     * 供应商基本信息更新
     * */
    @UpdateProvider(type = SupplierProvider.class, method = "updateInfo")
    Integer updateInfo(Supplier_info supplier_info);

    /**
     * 供应商基本信息插入
     * */
    @InsertProvider(type = SupplierProvider.class, method = "insertInfo")
    Integer insertInfo(Supplier_info supplier_info);

    /**
     * 供应产品查询
     * */
    @SelectProvider(type = SupplierProvider.class, method = "findSupitem")
    List<Supplier_item>  findSupitem(Supplier_item supplier_item);

    /**
     * 新增供应产品
     * 需要提供供应商id,确定是哪个供应商插入的商品
     * */
    @InsertProvider(type = SupplierProvider.class, method = "insertItem")
    Integer insertItem(Supplier_item supplier_item);

    /**
     * 编辑供应产品
     * */
    @UpdateProvider(type = SupplierProvider.class, method = "updateItem")
    Integer updateItem(Supplier_item supplier_item);

    /**
     * 供应商评分
     * */
    @InsertProvider(type = SupplierProvider.class, method = "evaluateSup")
    Integer evaluateSup(Supplier_evaluate supplier_evaluate);

    /**
     * 查询是否有供应商评分
     * */
    @Select("SELECT COUNT(*) FROM SUPPLIER_EVALUATE WHERE SUP_ID =#{sup_id}")
    Integer findevaluateSup(Supplier_evaluate supplier_evaluate);

    /**
     * 更新供应商评分表
     * */
    @Update("UPDATE SUPPLIER_EVALUATE SET SUP_SERVICE= #{sup_service},SUP_TIME =#{sup_time},SUP_QUALITY= #{sup_quality},SUP_REMARK= #{sup_remark},SUP_SERVICE_SCORE= #{sup_service_score},SUP_QUALITY_SCORE= #{sup_quality_score} WHERE SUP_ID =#{sup_id}")
    Integer update_evaluate_Sup(Supplier_evaluate supplier_evaluate);

    /**
     * 通过商品编号查询所有供应商品信息
     * */
    @Select("SELECT * FORM SUPPLIER_ITEM WHERE ITEM_ORDER =#{item_order}")
    List<Supplier_item>  findSupitem_byorder(Supplier_item supplier_item);

    /**
     * 通过供应商名称和商品编号查出所有商品供应编号和商品单价
     * */
    @Select("SELECT ITEM_SUPID,ITEM,PRICE FORM SUPPLIER_ITEM WHERE ITEM_ORDER =#{item_order} AND SUP_NAME =#{sup_name}")
    List<Supplier_item>  finditem_bycondition(Supplier_item supplier_item);


}
