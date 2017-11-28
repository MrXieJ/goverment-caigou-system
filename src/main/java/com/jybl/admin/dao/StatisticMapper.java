package com.jybl.admin.dao;

import com.jybl.admin.dao.provider.StatisticProvider;
import com.jybl.admin.entity.OrderStatisticEntity;
import com.jybl.admin.entity.NameValueEntity;
import com.jybl.admin.entity.UserStatisticEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface StatisticMapper {


    @Select("SELECT R.month, ifnull(S.COUNT, 0) as count, ifnull(S.TOTAL, 0) as total FROM PAST_12_MONTH_VIEW R LEFT JOIN (SELECT DATE_FORMAT(T.PURCHASED_TIME,'%Y-%m') AS MONTH, COUNT(T.INDENT_NUMBER) AS COUNT, SUM(T.PRICE) AS TOTAL FROM PURCHASED_SERVICE T WHERE DATE_FORMAT(T.PURCHASED_TIME,'%Y-%m')>DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 month),'%Y-%m') GROUP BY MONTH) S ON R.month = S.MONTH GROUP BY R.month")
    List<OrderStatisticEntity> get12MonthOrderStatistic();

    @Select("SELECT * FROM (SELECT IFNULL(COUNT(*), 0) AS COUNT, IFNULL(SUM(PRICE), 0) AS TOTAL FROM PURCHASED_SERVICE UNION (SELECT IFNULL(COUNT(*), 0) AS COUNT, IFNULL(SUM(PRICE), 0) AS TOTAL FROM PURCHASED_SERVICE WHERE DATE(PURCHASED_TIME) = DATE(CURDATE()))) A")
    List<OrderStatisticEntity> getOverviewOrderStatistic();

    @SelectProvider(type = StatisticProvider.class, method = "selectGroups")
    List<NameValueEntity> getRangeGroup();

    @Select("SELECT R.month, ifnull(S.COUNT, 0) as patient_count, ifnull(T.COUNT, 0) as doctor_count FROM PAST_12_MONTH_VIEW R LEFT JOIN (SELECT DATE_FORMAT(DATETIME,'%Y-%m') AS MONTH, COUNT(*) AS COUNT FROM PATIENT_INFO WHERE DATE_FORMAT(DATETIME,'%Y-%m')>DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 month),'%Y-%m') GROUP BY MONTH) S ON R.month = S.MONTH LEFT JOIN (SELECT DATE_FORMAT(DATETIME,'%Y-%m') AS MONTH, COUNT(*) AS COUNT FROM DOCTOR_INFO WHERE DATE_FORMAT(DATETIME,'%Y-%m')>DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 month),'%Y-%m') GROUP BY MONTH) T ON R.month = T.MONTH GROUP BY R.month")
    List<UserStatisticEntity> get12MonthUserStatistic();

    @Select("SELECT a.time as month, a.count as patient_count, b.count as doctor_count FROM (SELECT DATE(curdate()) AS TIME, COUNT(*) as count FROM PATIENT_INFO WHERE DATE(DATETIME)=DATE(CURDATE())) A LEFT JOIN (SELECT DATE(curdate()) AS TIME, COUNT(*) as count FROM DOCTOR_INFO WHERE DATE(DATETIME)=DATE(CURDATE())) B ON A.TIME = B.TIME UNION (SELECT a.time as month, a.count as patient_count, b.count as doctor_count FROM (SELECT DATE(curdate()) AS TIME, COUNT(*) as count FROM PATIENT_INFO) A LEFT JOIN (SELECT DATE(curdate()) AS TIME, COUNT(*) as count FROM DOCTOR_INFO) B ON A.TIME = B.TIME)")
    List<UserStatisticEntity> getOverviewUserStatistic();

}
