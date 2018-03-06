package com.caigou.admin.controller;

import com.caigou.admin.entity.Item;
import com.caigou.admin.service.ExcelService;
import com.caigou.admin.service.ItemService;
import com.caigou.admin.util.DateUtils;
import com.caigou.admin.util.ExcelImportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ExcelService excelService;

    /**
     * Excel导入
     * */
    @RequestMapping("/excel/import")
    public Map batchImport(@RequestParam(value="filename") MultipartFile file) throws IOException {

        Map map=new HashMap();
        //判断文件是否为空
        if(file==null){
            map.put("code",1);
            return map;
        }

        //获取文件名
        String fileName=file.getOriginalFilename();

        //验证文件名是否合格
        if(!ExcelImportUtils.validateExcel(fileName)){
            map.put("code",2);
            return map;
        }

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
            map.put("code",1);
            return map;
        }

        //批量导入
        map = excelService.batchImport(fileName,file);
        return map;
    }

    /**
     * 通过组合条件查找商品，支持分页
     * */
    @RequestMapping("/find")
    public Map findItem(HttpServletRequest request){
        Long limit = -1l;
        Long first = -1l;
        if (request.getParameter("limit") != null) {
            limit = Long.valueOf(request.getParameter("limit"));
            first = (Long.valueOf(request.getParameter("page")) - 1) *limit;
        }
        Item item=new Item();
        if(request.getParameter("item_name")!=null){
            item.setItem_name(request.getParameter("item_name"));
        }
        if(request.getParameter("item_order")!=null){
            item.setItem_order(request.getParameter("item_order"));
        }
        if(request.getParameter("item_type")!=null){
            item.setItem_type(request.getParameter("item_type"));
        }
        item.setPage(first);
        item.setLimit(limit);
        Map map=new HashMap();
        try{
            List<Item> itemList=itemService.findAll(item);
            map.put("code",0);
            map.put("data",itemList);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 添加商品
     * */
    @Transactional
    @RequestMapping("/add")
    public Map addItem(HttpServletRequest request){
        Item item=new Item();
        item.setItem_last_edit(DateUtils.getNowFormatDate(new Date()));
        Item temp=new Item();
        if(request.getParameter("item_name")!=null){
            item.setItem_name(request.getParameter("item_name"));
        }
        if(request.getParameter("item_status")!=null){
            item.setItem_status(request.getParameter("item_status"));
        }
        if(request.getParameter("item_type")!=null){
            item.setItem_type(request.getParameter("item_type"));
        }
        Map map=new HashMap();
        try{
            itemService.addItem(item);
            temp.setItem_order("SP"+String.valueOf(100000+itemService.find_max_id()));
            itemService.updateorder(temp);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

    /**
     * 编辑商品
     * */
    @Transactional
    @RequestMapping("/edit")
    public Map editItem(HttpServletRequest request){
        Item item=new Item();
        if(request.getParameter("id")!=null){
            item.setId(Integer.valueOf(request.getParameter("id")).intValue());
        }
        if(request.getParameter("item_name")!=null){
            item.setItem_name(request.getParameter("item_name"));
        }
        if(request.getParameter("item_status")!=null){
            item.setItem_status(request.getParameter("item_status"));
        }
        if(request.getParameter("item_type")!=null){
            item.setItem_type(request.getParameter("item_type"));
        }
        Map map=new HashMap();
        try{
            item.setItem_last_edit(DateUtils.getNowFormatDate(new Date()));
            itemService.editItem(item);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            map.put("errormessage",e.getMessage());
        }
        return map;
    }

}
