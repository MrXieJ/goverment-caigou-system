package com.caigou.admin.dao.provider;

import com.caigou.admin.entity.Risk_model;
import org.apache.ibatis.jdbc.SQL;

public class RiskProvider {

    /**
     * 查找商品参数设置模型
     * */
    public String find (Risk_model risk_model) {
        String sql=new SQL(){
            {
                SELECT("*");
                FROM("RISK_MODEL_SET");
                WHERE("1=1");
                if (risk_model.getItem_name() != null) {
                    AND().WHERE("ITEM_NAME = #{item_name}");
                }
                if (risk_model.getItem_type() != null) {
                    AND().WHERE("ITEM_TYPE = #{item_type}");
                }
                if (risk_model.getItem_order() != null) {
                    AND().WHERE("ITEM_ORDER = #{item_order}");
                }
            }
        }.toString();
        if(risk_model.getPage()!=-1l){
            sql+=" LIMIT "+risk_model.getPage()+","+risk_model.getLimit();
        }
        return sql;
    }

    /**
     * 风险模型参数编辑
     * */
    public String risk_model_update (Risk_model risk_model) {
        return new SQL(){
            {
                UPDATE("RISK_MODEL_SET");
                if(risk_model.getDeviation_check()==1){
                    if(risk_model.getDeviation_price_hide()!=-10000){
                        SET("DEVIATION_PRICE_HIDE = #{deviation_price_hide}");
                    }else if(risk_model.getDeviation_price_warn()!=-10000){
                        SET("DEVIATION_PRICE_WARN = #{deviation_price_warn}");
                    }
                }
                    SET("PRICE_SET_HIGH = #{price_set_high}");
                    SET("PRICE_SET_LOW = #{price_set_low}");
                    SET("DEVIATION_CHECK = #{deviation_check}");
                    SET("SECURE_PRICE_RANGE = #{secure_price_range}");
                    SET("LAST_EDIT_TIME = #{last_edit_time}");
                    SET("STATUS = #{status}");
                WHERE("ITEM_ORDER = #{item_order}");
            }
        }.toString();
    }
}
