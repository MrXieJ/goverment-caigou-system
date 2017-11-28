package com.jybl.admin.dao.provider;

import com.jybl.admin.entity.OrderEntity;

public class OrderProvider {

    public String selectOrder(OrderEntity orderEntity, Long first, Long limit) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM PURCHASED_SERVICE WHERE 1=1");

        if( orderEntity.getIndent_status() != null ) {
            sb.append(" AND INDENT_STATUS=" + orderEntity.getIndent_status());
        }

        if (orderEntity.getName() != null) {
            sb.append(" AND NAME LIKE '%" + orderEntity.getName() + "%'");
        }

        if (orderEntity.getDoctor_name() != null) {
            sb.append(" AND DOCTOR_NAME LIKE '%" + orderEntity.getDoctor_name() + "%'");
        }

        if (orderEntity.getPatient_name() != null) {
            sb.append(" AND PATIENT_NAME LIKE '%" + orderEntity.getPatient_name() + "%'");
        }

        if (orderEntity.getPurchased_time() != null) {
            String[] s = orderEntity.getPurchased_time().split("\\|");
            sb.append(" AND PURCHASED_TIME>='" + s[0] + "'");
            sb.append(" AND PURCHASED_TIME<'" + s[1] + "'");
        }

        sb.append(" LIMIT " + first.toString() + ", " + limit.toString());

        return sb.toString();

    }
}
