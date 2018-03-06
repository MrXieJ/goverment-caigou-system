package com.caigou.admin.dao;

import com.caigou.admin.entity.Item;
import com.caigou.admin.dao.provider.ItemProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {
    /**
     * 找出商品最大id
     * */
    @Select("SELECT IFNULL(MAX(ID), 0) FROM ITEM")
    int find_max_id();

    /**
     * 指定条件查询商品
     * */
    @Select("SELECT * FROM ITEM WHERE ITEM_NAME=#{item_name} AND ITEM_TYPE=#{item_type}")
    Item findone(Item item);

    /**
     * 插入商品编号
     * */
    @Update("UPDATE ITEM SET ITEM_ORDER= #{item_order} WHERE ID= #{id}")
    Integer updateorder(Item item);

    /**
     * 查询商品
     * */
    @SelectProvider(type = ItemProvider.class, method = "findItem")
    List<Item> findAll(Item item);

    /**
     * 新增商品
     * */
    @Insert("INSERT INTO ITEM (ITEM_NAME, ITEM_TYPE, ITEM_STATUS, ITEM_LAST_EDIT) VALUES (#{item_name}, #{item_type}, #{item_status}, #{item_last_edit})")
    Integer additem(Item item);

    /**
     * 编辑商品
     * */
    @UpdateProvider(type = ItemProvider.class, method = "updateItem")
    Integer editItem(Item item);

}
