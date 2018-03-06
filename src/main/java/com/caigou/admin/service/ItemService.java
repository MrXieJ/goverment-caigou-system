package com.caigou.admin.service;

import com.caigou.admin.dao.ItemMapper;
import com.caigou.admin.entity.Item;
import com.caigou.admin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemMapper itemMapper;

    /**
     * Excel单个导入
     * */
    public Integer excel_import(Item item){
        item.setItem_last_edit(DateUtils.getNowFormatDate(new Date()));
        item.setItem_status("0");
        item.setItem_order("SP"+String.valueOf(100000+itemMapper.find_max_id()));
        return itemMapper.additem(item);
    }

    /**
     * 找出商品最大id
     * */
    public int find_max_id(){
        return itemMapper.find_max_id();
    }
    /**
     * 查询商品
     * */
    public List<Item> findAll(Item item){return itemMapper.findAll(item);}

    /**
     * 查找特定商品
     * */
    public Item findone(Item item){return itemMapper.findone(item);}

    /**
     * 更新商品编号
     * */
    public Integer updateorder(Item item){return itemMapper.updateorder(item);}

    /**
     * 新增商品
     * */
    public Integer addItem(Item item){return itemMapper.additem(item);}

    /**
     * 编辑商品
     * */
    public Integer editItem(Item item){return itemMapper.editItem(item);}

}
