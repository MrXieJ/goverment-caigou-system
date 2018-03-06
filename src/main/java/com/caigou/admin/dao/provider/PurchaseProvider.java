package com.caigou.admin.dao.provider;

import com.caigou.admin.entity.Apply_item;
import com.caigou.admin.entity.Purchase_apply;
import com.caigou.admin.entity.Purchase_check;
import org.apache.ibatis.jdbc.SQL;


public class PurchaseProvider {
    /**
     * 个人申请查询
     * */
    public String findApply(Purchase_apply purchase_apply){
        /*获取时间参数*/
        String[] date_params=purchase_apply.getApply_date().split(",");
        String begin_date=date_params[0];
        String end_date=date_params[1];
        String sql= new SQL(){
           {
                SELECT("*");
                FROM("PURCHASE_APPLY");
                WHERE("APPLY_USER_ACCOUNT= #{apply_user_account}");
                if (purchase_apply.getApply_order() != null) {
                    AND().WHERE("APPLY_ORDER = #{apply_order}");
                }
                if (purchase_apply.getApply_date() != null) {
                   AND().WHERE("APPLY_DATE BETWEEN '"+begin_date+"'"+" AND '"+end_date+"'");
                }
                if (purchase_apply.getApply_state() != null) {
                   AND().WHERE("APPLY_STATE = #{apply_state}");
                }
            }
        }.toString();
       if(purchase_apply.getPage()!=-1l){
           sql+=" LIMIT #{page},#{limit}";
       }
       return sql;
    }

    /**
     * 根据组合条件查询采购申请单
     * */
    public String findBycondition(Purchase_apply purchase_apply){
        /*获取时间参数*/
        String[] date_params=purchase_apply.getApply_date().split(",");
        String begin_date=date_params[0];
        String end_date=date_params[1];
        String sql= new SQL(){
            {
                SELECT("*");
                FROM("PURCHASE_APPLY");
                WHERE("1=1");
                if (purchase_apply.getApply_order() != null) {
                    AND().WHERE("APPLY_ORDER = #{apply_order}");
                }
                if (purchase_apply.getApply_date() != null) {
                    AND().WHERE("APPLY_DATE BETWEEN '"+begin_date+"'"+" AND '"+end_date+"'");
                }
                if (purchase_apply.getApply_state() != null) {
                    AND().WHERE("APPLY_STATE = #{apply_state}");
                }
                if (purchase_apply.getApply_department() != null) {
                    AND().WHERE("APPLY_DEPARTMENT = #{apply_department}");
                }
                if (purchase_apply.getApply_user() != null) {
                    AND().WHERE("APPLY_USER = #{apply_user}");
                }
            }
        }.toString();
        if(purchase_apply.getPage()!=-1l){
            sql+=" LIMIT #{page},#{limit}";
        }
        return sql;
    }

    /**
     * 更新采购商品表中的商品
     * */
    public String updateApplyitem (Apply_item apply_item) {
        String sql= new SQL(){
            {
                UPDATE("APPLY_ITEMS");
                    if(apply_item.getApply_reason()!=null) {
                        SET("APPLY_REASON = #{apply_reason}");
                    }
                    if(apply_item.getSteady_fund()!=0){
                        SET("STEADY_FUND = #{steady_fund}");
                    }
                    if(apply_item.getItem_order()!=null) {
                        SET("ITEM_ORDER = #{item_order}");
                    }
                    if(apply_item.getItem_name()!=null) {
                        SET("ITEM_NAME = #{item_name}");
                    }
                    if(apply_item.getItem_type()!=null) {
                        SET("ITEM_TYPE = #{item_type}");
                    }
                    if(apply_item.getItem_count()!=0){
                        SET("ITEM_COUNT = #{item_count}");
                    }
                    if(apply_item.getItem_supid()!=null){
                        SET("ITEM_SUPID = #{item_supid}");
                    }
                    if(apply_item.getItem_sup_name()!=null){
                        SET("ITEM_SUP_NAME = #{item_sup_name}");
                    }
                    if(apply_item.getItem_sup_price()!=null){
                        SET("ITEM_SUP_PRICE = #{item_sup_price}");
                    }
                WHERE("APPLY_ORDER = #{apply_order}");
            }
        }.toString();
        if(apply_item.getItem_find_order()!=null){
            sql+=" AND ITEM_ORDER = #{item_find_order}";
        }
        return sql;
    }
    /**
     * 采购申请更新
     * */
    public String updateApply (Purchase_apply purchase_apply) {
        return new SQL(){
            {
                UPDATE("PURCHASE_APPLY");
                if(purchase_apply.getApply_check_user()!=null){
                    SET("APPLY_CHECK_USER = #{apply_check_user}");
                }
                if (purchase_apply.getApply_date() != null) {
                    SET("APPLY_DATE = #{apply_date}");
                }
                if (purchase_apply.getApply_state()!=null){
                    SET("APPLY_STATE = #{apply_state}");
                }
                if (purchase_apply.getPurchase_complete_time()!=null){
                    SET("PURCHASE_COMPLETE_TIME = #{purchase_complete_time}");
                }
                if (purchase_apply.getPurchase_item_accept_time()!=null){
                    SET("PURCHASE_ITEM_ACCEPT_TIME = #{purchase_item_accept_time}");
                }
                WHERE("APPLY_ORDER = #{apply_order}");
            }
        }.toString();
    }

    /**
     * 采购申请审核
     * */
    public String checkApply(Purchase_check purchase_check){
        return new SQL(){
            {
                INSERT_INTO("PURCHASE_CHECK");
                VALUES("CHECK_USER","'"+purchase_check.getCheck_user()+"'");
                VALUES("CHECK_DATE","'"+purchase_check.getCheck_date()+"'");
                VALUES("DENY_REASON","'"+purchase_check.getDeny_reason()+"'");
                VALUES("APPLY_ORDER","'"+purchase_check.getApply_order()+"'");
                VALUES("CHECK_USER_ACCOUNT","'"+purchase_check.getCheck_user_account()+"'");
            }}.toString();
    }
}
