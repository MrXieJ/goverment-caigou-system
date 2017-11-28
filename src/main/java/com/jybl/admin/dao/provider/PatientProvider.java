package com.jybl.admin.dao.provider;

import com.jybl.admin.entity.PatientEntity;

public class PatientProvider {

    public String selectPatient(PatientEntity patientEntity, Long first, Long limit) {

        StringBuffer sb = new StringBuffer();
        if (patientEntity.getCount() != -1) {
            sb.append("SELECT * FROM (");
        }
        sb.append("SELECT A.*, COUNT(B.INDENT_NUMBER) AS COUNT FROM PATIENT_INFO A LEFT JOIN PURCHASED_SERVICE B ON A.WECHAT_ID = B.WECHAT_ID WHERE 1=1");
        if (patientEntity.getSex() != null && patientEntity.getSex().length() > 0) {
            sb.append(" AND A.SEX='" + patientEntity.getSex() + "'");
        }
        if (patientEntity.getKind() != null && patientEntity.getKind().length() > 0) {
            sb.append(" AND A.KIND='" + patientEntity.getKind() + "'");
        }
        if (patientEntity.getAge() != null) {
            sb.append(" AND A.AGE BETWEEN");
            switch(patientEntity.getAge()) {
                case 18:
                    sb.append(" 0 AND 18");
                    break;
                case 25:
                    sb.append(" 25 AND 30");
                    break;
                case 30:
                    sb.append(" 30 AND 40");
                    break;
                case 40:
                    sb.append(" 40 AND 50");
                    break;
                case 50:
                    sb.append(" 50 AND 60");
                    break;
                case 60:
                    sb.append(" 60 AND 80");
                    break;
                case 80:
                    sb.append(" 80 AND 200");
                    break;
                default:
                    break;
            }
        }
        sb.append(" GROUP BY A.WECHAT_ID");
        if (patientEntity.getCount() != -1) {
            System.out.print(patientEntity.getCount().toString());
            if (patientEntity.getCount() == 0) {
                sb.append(") C WHERE COUNT=0");
            } else {
                sb.append(") C WHERE COUNT>0");
            }
        }
        sb.append(" LIMIT " + first.toString() + ", " + limit.toString());
        return sb.toString();

    }

}
