package com.caigou.admin.service;

import com.caigou.admin.entity.Item;
import com.caigou.admin.entity.Supplier_item;
import com.caigou.admin.util.ExcelImportUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ExcelService {

    @Autowired
    ItemService itemService;
    @Autowired
    SupplierService supplierService;
    /**
     * 上传excel文件到临时目录后并开始解析
     * @param fileName
     * @param mfile
     * @return
     */
    public Map batchImport(String fileName, MultipartFile mfile,String fileType){
        Map map=new HashMap();
        File uploadDir = new  File("E:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //新建一个文件
        File tempFile = new File("E:\\fileupload\\" + new Date().getTime() + ".xlsx");
        //初始化输入流
        InputStream is = null;
        try{
            //将上传的文件写入新建的文件中
            mfile.transferTo(tempFile);

            //根据新建的文件实例化输入流
            is = new FileInputStream(tempFile);

            //根据版本选择创建Workbook的方式
            Workbook wb = null;
            //根据文件名判断文件是2003版本还是2007版本
            if(ExcelImportUtils.isExcel2007(fileName)){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }
            //根据excel里面的内容读取知识库信息
            return readExcelValue(wb,tempFile,fileType);
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        /*数据格式有问题*/
        map.put("code",3);
        return map;
    }


    /**
     * 解析Excel里面的数据
     * @param wb
     * @return
     */
    private Map readExcelValue(Workbook wb, File tempFile,String fileType){
        Map map=new HashMap();
        //错误信息接收器
        String errorMsg = "";
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows=sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if(totalRows>=2 && sheet.getRow(1) != null){
            totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
        }
        String br = "<br/>";
        if(fileType.equals("item")) {
            //循环Excel行数,从第二行开始。标题不入库
            for (int r = 1; r < totalRows; r++) {
                String rowMessage = "";
                Row row = sheet.getRow(r);
                if (row == null) {
                    errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
                    continue;
                }
                List<Item> itemList=new ArrayList<Item>();
                Item item = new Item();
                String item_name = "";
                String item_type = "";

                //循环Excel的列
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (null != cell) {
                        if (c == 0) {
                            item_name = cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_name)) {
                                rowMessage += "商品名不能为空；";
                            }
                            item.setItem_name(item_name);
                        } else if (c == 1) {
                            item_type = cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_type)) {
                                rowMessage += "商品类型不能为空；";
                            }
                            item.setItem_type(item_type);
                        }
                    } else {
                        rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
                    }
                }
                //拼接每行的错误提示
                if (!StringUtils.isEmpty(rowMessage)) {
                    errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
                } else {
                    itemList.add(item);
                }
                //没导入的商品列表
                List<Item> errorlist=new ArrayList<>();
                //全部验证通过才导入到数据库
                if(StringUtils.isEmpty(errorMsg)){
                    for(Item item1 : itemList){
                        if(itemService.editItem(item1)==0){
                            errorlist.add(item1);
                        }
                    }
                    if(errorlist!=null){
                        map.put("code",4);
                        map.put("data",errorlist);
                    }else {
                        map.put("code",0);
                    }
                }
            }
        }else if(fileType.equals("sup_item")) {
            //循环Excel行数,从第二行开始。标题不入库
            for (int r = 1; r < totalRows; r++) {
                String rowMessage = "";
                Row row = sheet.getRow(r);
                if (row == null) {
                    errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
                    continue;
                }
                List<Supplier_item> supplier_items=new ArrayList<>();
                Supplier_item supplier_item=new Supplier_item();
                String item_name="";
                String item_type="";
                String item_brand="";
                String item_spec="";
                String item_produce_address="";
                String item_count_unit;
                String present_state;
                String item_price;
                //循环Excel的列
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (null != cell) {
                        if (c == 0) {
                            item_name=cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_name)) {
                                rowMessage += "供应商品名不能为空；";
                            }
                            supplier_item.setItem_name(item_name);
                        } else if (c == 1) {
                            item_type = cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_type)) {
                                rowMessage += "供应商品类型不能为空；";
                            }
                            supplier_item.setItem_type(item_type);
                        }
                        else if (c == 2) {
                            item_brand = cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_brand)) {
                                rowMessage += "供应商品品牌不能为空；";
                            }
                            supplier_item.setItem_brand(item_brand);
                        }
                        else if (c == 3) {
                            item_spec = cell.getStringCellValue();
                            supplier_item.setItem_spec(item_spec);
                        }
                        else if (c == 4) {
                            item_produce_address = cell.getStringCellValue();
                            supplier_item.setItem_produce_address(item_produce_address);
                        }
                        else if (c == 5) {
                            item_count_unit = cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_count_unit)) {
                                rowMessage += "供应商品计量单位不能为空；";
                            }
                            supplier_item.setItem_count_unit(item_count_unit);
                        }
                        else if (c == 5) {
                            present_state = cell.getStringCellValue();
                            if (StringUtils.isEmpty(present_state)) {
                                rowMessage += "供应商品状态不能为空；";
                            }
                            supplier_item.setPresent_state(present_state);
                        }
                        else if (c == 6) {
                            item_price = cell.getStringCellValue();
                            if (StringUtils.isEmpty(item_price)) {
                                rowMessage += "供应商品价格不能为空；";
                            }
                            supplier_item.setItem_price(item_price);
                        }
                    } else {
                        rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
                    }
                }
                //拼接每行的错误提示
                if (!StringUtils.isEmpty(rowMessage)) {
                    errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
                } else {
                    Item item=new Item();
                    item.setItem_name(supplier_item.getItem_name());
                    item.setItem_type(supplier_item.getItem_type());
                    supplier_item.setItem_order(itemService.findone(item).getItem_order());
                    supplier_items.add(supplier_item);
                }
                //没导入的供应商品列表
                List<Supplier_item> errorlist=new ArrayList<>();
                //全部验证通过才导入到数据库
                if(StringUtils.isEmpty(errorMsg)){
                    for(Supplier_item supplier_item1 : supplier_items){
                        if(supplierService.addSupitem(supplier_item1)==0){
                            errorlist.add(supplier_item1);
                        }
                    }
                    if(errorlist!=null){
                        map.put("code",4);
                        map.put("data",errorlist);
                    }else {
                        map.put("code",0);
                    }
                }
        }
        }
        //删除上传的临时文件
        if(tempFile.exists()){
            tempFile.delete();
        }
        return map;
    }

}
