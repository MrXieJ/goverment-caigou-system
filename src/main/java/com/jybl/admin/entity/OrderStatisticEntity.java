package com.jybl.admin.entity;

import java.io.Serializable;

public class OrderStatisticEntity implements Serializable {

    private String month;
    private Long count;
    private Double total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
