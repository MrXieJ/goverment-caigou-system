package com.jybl.admin.dao.provider;

public class StatisticProvider {

    public String selectGroups(){

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.NAME, COUNT(WECHAT_ID) AS VALUE FROM (");
        sb.append("\nSELECT WECHAT_ID, CASE");
        sb.append("\nWHEN (PROB = 0) THEN '健康人群'");
        sb.append("\nWHEN (PROB>0 AND PROB<1) THEN '低危人群'");
        sb.append("\nWHEN (PROB>=1 AND PROB<5) THEN '中危人群'");
        sb.append("\nWHEN (PROB>=5 AND PROB<10) THEN '高危人群'");
        sb.append("\nWHEN (PROB>=10 AND PROB<=100) THEN '极高危人群'");
        sb.append("\nELSE '未评估'");
        sb.append("\nEND AS NAME");
        sb.append("\nFROM PATIENT_INFO");
        sb.append("\n) A GROUP BY A.NAME");

        return sb.toString();
    }






}
