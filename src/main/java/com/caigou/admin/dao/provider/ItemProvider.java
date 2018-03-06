package com.caigou.admin.dao.provider;

import com.caigou.admin.entity.Item;
import org.apache.ibatis.jdbc.SQL;


public class ItemProvider {

    /**
     * 查找商品
     * */
    public String findItem (Item item) {
        String sql=new SQL(){
            {
                SELECT("*");
                FROM("ITEM");
                WHERE("1=1");
                if (item.getItem_name() != null) {
                    AND().WHERE("ITEM_NAME = #{item_name}");
                }
                if (item.getItem_type() != null) {
                    AND().WHERE("ITEM_TYPE = #{item_type}");
                }
                if (item.getItem_order() != null) {
                    AND().WHERE("ITEM_ORDER = #{item_order}");
                }
            }
        }.toString();
        if(item.getPage()!=-1l){
            sql+=" LIMIT "+item.getPage()+","+item.getLimit();
        }
        return sql;
    }

    /**
     * 更新商品
     * */
    public String updateItem (Item item) {
        return new SQL(){
            {
                UPDATE("ITEM");
                if (item.getItem_name() != null) {
                    SET("ITEM_NAME = #{item_name}");
                }
                if (item.getItem_type() != null) {
                    SET("ITEM_TYPE = #{item_type}");
                }
                if (item.getItem_status() != null) {
                    SET("ITEM_STATUS = #{item_status}");
                }
                if (item.getItem_last_edit() != null) {
                    SET("ITEM_LAST_EDIT = #{item_last_edit}");
                }
                WHERE("ID = #{id}");
            }
        }.toString();
    }
}
