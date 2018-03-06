package com.caigou.admin.dao;

import com.caigou.admin.dao.provider.RiskProvider;
import com.caigou.admin.entity.Risk_model;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RiskMapper {

    /**
     * 根据商品编号查询商品风险设置模型
     * */
    @Select("SELECT * FORM RISK_MODEL_SET WHERE ITEM_ORDER = #{item_order}")
    Risk_model find_by_order(@Param("item_order") String item_order);

    /**
     * 商品模型查询
     * */
    @SelectProvider(type = RiskProvider.class, method = "find")
    List<Risk_model> findAll(Risk_model risk_model);

    /**
     * 商品模型参数添加
     * */
    @Insert("INSERT INTO RISK_MODEL_SET (ITEM_ORDER, ITEM_NAME, ITEM_TYPE, LAST_EDIT_TIME) VALUES (#{item_order}, #{item_name}, #{item_type}, #{last_edit_time})")
    Integer risk_model_add(Risk_model risk_model);

    /**
     * 商品模型参数编辑
     * */
    @UpdateProvider(type = RiskProvider.class, method = "risk_model_update")
    Integer risk_model_update(Risk_model risk_model);
}
