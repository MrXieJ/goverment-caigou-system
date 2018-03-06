package com.caigou.admin.service;

import com.caigou.admin.dao.PurchaseMapper;
import com.caigou.admin.entity.Apply_item;
import com.caigou.admin.entity.Purchase_apply;
import com.caigou.admin.entity.Purchase_check;
import com.caigou.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurchaseMapper purchaseMapper;
    public List<Apply_item> find_items_by_order(String apply_order){return purchaseMapper.find_item_by_order(apply_order);}
    public Purchase_apply find_by_order(String apply_order){return purchaseMapper.find_by_order(apply_order);}

    public int find_max_id(){return purchaseMapper.find_max_id();}

    public List<Purchase_apply> findBycondition(Purchase_apply purchase_apply) { return purchaseMapper.findBycondition(purchase_apply); }

    public List<Purchase_apply> findApply(Purchase_apply purchase_apply) { return purchaseMapper.findApply(purchase_apply); }

    public void addApply(Purchase_apply purchase_apply)
    {
        List<Apply_item> apply_items=purchase_apply.getApply_item();
        purchaseMapper.addApply(purchase_apply);
        for(Apply_item apply_item:apply_items){
            purchaseMapper.add_apply_items(apply_item);
        }
    }

    public Integer checkApply(Purchase_check purchase_check)
    {
        return purchaseMapper.checkApply(purchase_check);
    }

    public Integer deleteApply(Apply_item apply_item)
    {
        return purchaseMapper.deleteApply(apply_item);
    }

    public Integer editApplyitem(Apply_item apply_item)
    {
        return purchaseMapper.editApplyitem(apply_item);
    }

    public Integer editApply(Purchase_apply purchase_apply)
    {
        return purchaseMapper.editApply(purchase_apply);
    }


    public Integer update_order(Purchase_apply purchase_apply) { return purchaseMapper.update_order(purchase_apply); }

    public Purchase_apply getApply(String apply_user_account,String apply_date){return purchaseMapper.getApply(apply_user_account,apply_date);}

}
