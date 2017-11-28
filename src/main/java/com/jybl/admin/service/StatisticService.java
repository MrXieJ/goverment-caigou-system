package com.jybl.admin.service;

import com.jybl.admin.dao.StatisticMapper;
import com.jybl.admin.entity.NameValueEntity;
import com.jybl.admin.entity.OrderStatisticEntity;
import com.jybl.admin.entity.UserStatisticEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    public List<OrderStatisticEntity> get12MonthOrderStatistic() {
        return statisticMapper.get12MonthOrderStatistic();
    }

    public List<OrderStatisticEntity> getOverviewOrderStatistic() {
        return statisticMapper.getOverviewOrderStatistic();
    }

    public List<NameValueEntity> getRangeGroup() {
        return statisticMapper.getRangeGroup();
    }

    public List<UserStatisticEntity> get12MonthUserStatistic() {
        return statisticMapper.get12MonthUserStatistic();
    }

    public List<UserStatisticEntity> getOverviewUserStatistic() {
        return statisticMapper.getOverviewUserStatistic();
    }

}
