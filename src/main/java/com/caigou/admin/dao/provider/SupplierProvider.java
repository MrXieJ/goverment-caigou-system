package com.caigou.admin.dao.provider;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.caigou.admin.entity.Supplier;
import com.caigou.admin.entity.Supplier_evaluate;
import com.caigou.admin.entity.Supplier_info;
import com.caigou.admin.entity.Supplier_item;
import com.sun.media.sound.SF2GlobalRegion;
import org.apache.ibatis.jdbc.SQL;

public class SupplierProvider {

    /**
     * 查询供应商
     * */
    public String findSupplier (Supplier sup) {
        String sql= new SQL(){
            {
                SELECT("*");
                FROM("SUPPLIER_INFO A LEFT JOIN SUPPLIER_ITEM B ON A.SUPPLIER_ID = B.SUP_ID");
                WHERE("1=1");
                if (sup.getSupplier_id() != null) {
                    AND().WHERE("A.SUPPLIER_ID = #{supplier_id}");
                }
                if (sup.getSupplier_name() != null) {
                    AND().WHERE("A.SUPPLIER_NAME = #{supplier_name}");
                }
                if (sup.getSup_item_type() != null) {
                    AND().WHERE("B.ITEM_TYPE = #{item_type}");
                }
                if (sup.getSup_item_name() != null) {
                    AND().WHERE("B.ITEM_NAME = #{item_name}");
                }
            }
        }.toString();
        if(sup.getPage()!=-1){
            sql+="LIMIT #{page},#{first}";
        }
        return sql;
    }

    /**
     * 查询供应商品
     * */
    public String findSupitem (Supplier_item supplier_item) {
        String sql= new SQL(){
            {
                SELECT("*");
                FROM("SUPPLIER_ITEM");
                WHERE("1=1");
                if (supplier_item.getItem_name()!= null) {
                    AND().WHERE("ITEM_NAME =#{item_name}");
                }
                if (supplier_item.getItem_order() != null) {
                    AND().WHERE("ITEM_ORDER = #{item_order}");
                }
                if (supplier_item.getItem_type() != null) {
                    AND().WHERE("ITEM_TYPE = #{item_type}");
                }
                if (supplier_item.getSup_id()!=null){
                    AND().WHERE("SUP_ID = #{sup_id}");
                }
                if (supplier_item.getItem_supid()!=null){
                    AND().WHERE("ITEM_SUPID= #{item_supid}");
                }
            }
        }.toString();
        if(supplier_item.getPage()!=-1l){
            sql+=" LIMIT #{page},#{limit}";
        }
        System.out.println(sql);
        return sql;
    }

    /**
     * 取得供应商基本信息
     * */
    public String getSupplier (String supid) {
        return new SQL(){
            {
                SELECT("*");
                FROM("SUPPLIER_INFO");
                WHERE("SUPPLIER_ID = #{supid}");
            }
        }.toString();
    }

    /**
     * 供应商信息更新
     * */
    public String updateInfo (Supplier_info supplier_info) {
        return new SQL(){
            {
                UPDATE("SUPPLIER_INFO");

                if (supplier_info.getBusiness_address() != null) {
                    SET("BUSINESS_ADDRESS= #{business_address}");
                }
                if (supplier_info.getBusiness_idcard() != null) {
                    SET("BUSINESS_IDCARD = #{business_idcard}");
                }
                if (supplier_info.getBusiness_mail() != null) {
                    SET("BUSINESS_MAIL = #{business_mail}");
                }
                if (supplier_info.getBusiness_owner() != null) {
                    SET("BUSINESS_OWNER = #{business_owner}");
                }
                if (supplier_info.getBusiness_telphone() != null) {
                    SET("BUSINESS_TELPHONE = #{business_telphone}");
                }
                if (supplier_info.getCooperration_amount() != null) {
                    SET("COOPERRATION_AMOUNT = #{cooperration_amount}");
                }
                if (supplier_info.getCorporate_card() != null) {
                    SET("CORPORATE_CARD = #{corporate_card}");
                }
                if (supplier_info.getDeposit_bank() != null) {
                    SET("DEPOSIT_BANK = #{deposit_bank}");
                }
                if (supplier_info.getGurantee_rate() != null) {
                    SET("GURANTEE_RATE = #{gurantee_rate}");
                }
                if (supplier_info.getInstitution_name() != null) {
                    SET("INSTITUTION_NAME= #{institution_name}");
                }
                if (supplier_info.getInstitution_owner() != null) {
                    SET("INSTITUTION_OWNER = #{institution_owner}");
                }
                if (supplier_info.getRegist_address() != null) {
                    SET("REGIST_ADDRESS = #{regist_address}");
                }
                if (supplier_info.getRegist_person() != null) {
                    SET("REGIST_PERSON = #{regist_person}");
                }
                if (supplier_info.getSocial_trust_code() != null) {
                    SET("SOCIAL_TRUST_CODE = #{social_trust_code}");
                }
                if (supplier_info.getSupplier_name() != null) {
                    SET("SUPPLIER_NAME = #{supplier_name}");
                }
                if (supplier_info.getSupplier_type() != null) {
                    SET("SUPPLIER_TYPE = #{supplier_type}");
                }
                if (supplier_info.getTax_number() != null) {
                    SET("TAX_NUMBER = #{tax_number}");
                }
                if( supplier_info.getSup_item_name()!=null){
                    SET("SUP_ITEM_NAME = #{sup_item_name}");
                }
                if( supplier_info.getEvaluate_score()!=0) {
                    SET("EVALUATE_SCORE = #{evaluate_score}");
                }
                if( supplier_info.getSup_item_type()!=null){
                    SET("SUP_ITEM_TYPE = #{sup_item_type}");
                }
                WHERE("SUPPLIER_ID = #{supplier_id}");
            }
        }.toString();
    }

    /**
     * 供应商信息插入
     * */
    public String insertInfo (Supplier_info supplier_info) {
        return new SQL(){
            {
                INSERT_INTO("SUPPLIER_INFO");
                if (supplier_info.getBusiness_address() != null) {
                    VALUES("BUSINESS_ADDRESS","'"+supplier_info.getBusiness_address()+"'");
                }
                if (supplier_info.getBusiness_idcard() != null) {
                    VALUES("BUSINESS_IDCARD","'"+supplier_info.getBusiness_idcard()+"'");
                }
                if (supplier_info.getBusiness_mail() != null) {
                    VALUES("BUSINESS_MAIL","'"+supplier_info.getBusiness_mail()+"'");
                }
                if (supplier_info.getBusiness_owner() != null) {
                    VALUES("BUSINESS_OWNER","'"+supplier_info.getBusiness_owner()+"'");
                }
                if (supplier_info.getBusiness_telphone() != null) {
                    VALUES("BUSINESS_TELPHONE","'"+supplier_info.getBusiness_telphone()+"'");
                }
                if (supplier_info.getCooperration_amount() != null) {
                    VALUES("COOPERRATION_AMOUNT",String.valueOf(supplier_info.getCooperration_amount()));
                }
                if (supplier_info.getCorporate_card() != null) {
                    VALUES("CORPORATE_CARD","'"+supplier_info.getCorporate_card()+"'");
                }
                if (supplier_info.getDeposit_bank() != null) {
                    VALUES("DEPOSIT_BANK","'"+supplier_info.getDeposit_bank()+"'");
                }
                if (supplier_info.getGurantee_rate() != null) {
                    VALUES("GURANTEE_RATE","'"+supplier_info.getGurantee_rate()+"'");
                }
                if (supplier_info.getInstitution_name() != null) {
                    VALUES("INSTITUTION_NAME","'"+supplier_info.getInstitution_name()+"'");
                }
                if (supplier_info.getInstitution_owner() != null) {
                    VALUES("INSTITUTION_OWNER","'"+supplier_info.getInstitution_owner()+"'");
                }
                if (supplier_info.getRegist_address() != null) {
                    VALUES("REGIST_ADDRESS","'"+supplier_info.getRegist_address()+"'");
                }
                if (supplier_info.getRegist_person() != null) {
                    VALUES("REGIST_PERSON","'"+supplier_info.getRegist_person()+"'");
                }
                if (supplier_info.getSocial_trust_code() != null) {
                    VALUES("SOCIAL_TRUST_CODE","'"+supplier_info.getSocial_trust_code()+"'");
                }
                if (supplier_info.getSupplier_name() != null) {
                    VALUES("SUPPLIER_NAME","'"+supplier_info.getSupplier_name()+"'");
                }
                if (supplier_info.getSupplier_type() != null) {
                    VALUES("SUPPLIER_TYPE","'"+supplier_info.getSupplier_type()+"'");
                }
                if (supplier_info.getTax_number() != null) {
                    VALUES("TAX_NUMBER","'"+supplier_info.getTax_number()+"'");
                }
                if (supplier_info.getSupplier_id() != null) {
                    VALUES("SUPPLIER_ID","'"+supplier_info.getSupplier_id()+"'");
                }
                if (supplier_info.getCreate_time()!=null){
                    VALUES("CREATE_TIME","'"+supplier_info.getCreate_time()+"'");
                }
            }
        }.toString();
    }

    /**
     * 供应商品添加
     * */
    public String insertItem (Supplier_item supplier_item) {
        return new SQL(){
            {
                INSERT_INTO("SUPPLIER_ITEM");
                if (supplier_item.getSup_id() != null) {
                    VALUES("SUP_ID","'"+supplier_item.getSup_id()+"'");
                }
                if (supplier_item.getItem_name()!=null){
                    VALUES("ITEM_NAME","'"+supplier_item.getItem_name()+"'");
                }
                if (supplier_item.getItem_type()!=null){
                    VALUES("ITEM_TYPE","'"+supplier_item.getItem_type()+"'");
                }
                if (supplier_item.getItem_supid()!=null){
                    VALUES("ITEM_SUPID","'"+supplier_item.getItem_supid()+"'");
                }
                if (supplier_item.getItem_order() != null) {
                    VALUES("ITEM_ORDER","'"+supplier_item.getItem_order()+"'");
                }
                if (supplier_item.getItem_brand() != null) {
                    VALUES("ITEM_BRAND","'"+supplier_item.getItem_brand()+"'");
                }
                if (supplier_item.getItem_spec() != null) {
                    VALUES("ITEM_SPEC","'"+supplier_item.getItem_spec()+"'");
                }
                if (supplier_item.getItem_produce_address() != null) {
                    VALUES("ITEM_PRODUCE_ADDRESS","'"+supplier_item.getItem_produce_address()+"'");
                }
                if (supplier_item.getItem_count_unit() != null) {
                    VALUES("ITEM_COUNT_UNIT","'"+supplier_item.getItem_count_unit()+"'");
                }
                if (supplier_item.getPresent_state() != null) {
                    VALUES("PRESENT_STATE",supplier_item.getPresent_state());
                }
                if (supplier_item.getItem_price() != null) {
                    VALUES("ITEM_PRICE",String.valueOf(supplier_item.getItem_price()));
                }
                if  (supplier_item.getItem_addtime()!=null) {
                    VALUES("ITEM_ADDTIME","'"+supplier_item.getItem_addtime()+"'");
                }
                if(supplier_item.getSup_name()!=null){
                    VALUES("SUP_NAME","'"+supplier_item.getSup_name()+"'");
                }
            }
        }.toString();
    }

    /**
     * 供应商品编辑
     * */
    public String updateItem (Supplier_item supplier_item) {
        return new SQL(){
            {
                UPDATE("SUPPLIER_ITEM");
                if (supplier_item.getItem_order() != null) {
                    SET("ITEM_ORDER= #{item_order}");
                }
                if (supplier_item.getItem_brand() != null) {
                    SET("ITEM_BRAND= #{item_brand}");
                }
                if (supplier_item.getItem_spec() != null) {
                    SET("ITEM_SPEC= #{item_spec}");
                }
                if (supplier_item.getItem_produce_address() != null) {
                    SET("ITEM_PRODUCE_ADDRESS= #{item_produce_address}");
                }
                if (supplier_item.getItem_count_unit() != null) {
                    SET("ITEM_COUNT_UNIT= #{item_count_unit}");
                }
                if (supplier_item.getPresent_state() != null) {
                    SET("PRESENT_STATE= #{present_state}");
                }
                if (supplier_item.getItem_price() != null) {
                    SET("ITEM_PRICE= #{item_price}");
                }
                WHERE("ITEM_SUPID= #{item_supid}");
            }
        }.toString();
    }

    /**
     * 供应商评价
     * */
    public  String evaluateSup(Supplier_evaluate supplier_evaluate){
        return new SQL(){
            {
                INSERT_INTO("SUPPLIER_EVALUATE");
                VALUES("SUP_SERVICE","'"+supplier_evaluate.getSup_service()+"'");
                VALUES("SUP_TIME","'"+supplier_evaluate.getSup_time()+"'");
                VALUES("SUP_QUALITY","'"+supplier_evaluate.getSup_quality()+"'");
                VALUES("SUP_REMARK","'"+supplier_evaluate.getSup_remark()+"'");
                VALUES("SUP_SERVICE_SCORE",String.valueOf(supplier_evaluate.getSup_service_score()));
                VALUES("SUP_QUALITY_SCORE",String.valueOf(supplier_evaluate.getSup_quality_score()));
                VALUES("SUP_ID","'"+supplier_evaluate.getSup_id()+"'");
                VALUES("SUP_SCORE",String.valueOf(supplier_evaluate.getSup_score()));
        }}.toString();
    }

}
