package com.caigou.admin.dao;

import com.caigou.admin.dao.provider.PurchaseProvider;
import com.caigou.admin.entity.Apply_item;
import com.caigou.admin.entity.Purchase_apply;
import com.caigou.admin.entity.Purchase_check;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PurchaseMapper {

    /**
     * 查找申请单最大id
     * */
    @Select("SELECT IFNULL(MAX(ID), 0) FROM PURCHASE_APPLY")
    int find_max_id();

    /**
     * 个人申请查询
     * */
    @SelectProvider(type = PurchaseProvider.class, method = "findApply")
    List<Purchase_apply> findApply(Purchase_apply purchase_apply);

    /**
     * 根据编号查询出所有商品
     * */
    @Select("SELECT * FROM APPLY_ITEMS WHERE APPLY_ORDER= #{apply_order}")
    List<Apply_item> find_item_by_order(@Param("apply_order")String apply_order);

    /**
     * 根据申请编号查询申请
     * */
    @Select("SELECT * FROM PURCHASE_APPLY WHERE APPLY_ORDER= #{apply_order}")
    Purchase_apply find_by_order(@Param("apply_order") String apply_order);

    /**
     * 更新指定id申请编号
     * */
    @Update("UPDATE PURCHASE_APPLY SET APPLY_ORDER =#{apply_order} WHERE ID =#{id}")
    Integer update_order(Purchase_apply purchase_apply);

    /**
     * 根据组合条件查询所有申请
     * */
    @SelectProvider(type = PurchaseProvider.class, method = "findBycondition")
    List<Purchase_apply> findBycondition(Purchase_apply purchase_apply);

    /**
     * 根据用户和申请日期获取申请
     * */
    @Select("SELECT * FROM PURCHASE_APPLY WHERE APPLY_USER_ACCOUNT =#{apply_user_account} AND APPLY_DATE =#{apply_date}")
    Purchase_apply getApply(@Param("apply_user_account")String apply_user_account,@Param("apply_date")String apply_date);

    /**
     * 申请提交
     * */
    @Insert("INSERT INTO PURCHASE_APPLY (APPLY_USER, APPLY_DEPARTMENT, APPLY_DATE, APPLY_STATE, DENY_REASON, APPLY_USER_ACCOUNT) VALUES (#{apply_user}, #{apply_department}, #{apply_date}, #{apply_state}, #{deny_reason}, #{apply_user_account})")
    Integer addApply(Purchase_apply purchase_apply);

    /**
     * 申请更新
     * */
    @UpdateProvider(type = PurchaseProvider.class, method = "updateApply")
    Integer editApply(Purchase_apply purchase_apply);

    /**
     * 插入申请表中申请的商品
     * */
    @Insert("INSERT INTO APPLY_ITEMS (APPLY_REASON, APPLY_ORDER, ITEM_ORDER, ITEM_NAME, ITEM_TYPE, ITEM_COUNT) VALUES (#{apply_reason}, #{apply_order}, #{item_order}, #{item_name}, #{item_type}}, #{item_count}})")
    Integer add_apply_items(Apply_item apply_item);

    /**
     * 编辑申请表中申请的商品
     * */
    @UpdateProvider(type = PurchaseProvider.class, method = "updateApplyitem")
    Integer editApplyitem(Apply_item apply_item);

    /**
     * 申请删除
     * */
    @Delete("DELETE FROM APPLY_ITEMS WHERE APPLY_ORDER = #{apply_order} AND ITEM_ORDER =#{item_order}")
    Integer deleteApply(Apply_item apply_item);

    /**
     * 申请审核
     * */
    @InsertProvider(type = PurchaseProvider.class, method = "checkApply")
    Integer checkApply(Purchase_check purchase_check);

}
