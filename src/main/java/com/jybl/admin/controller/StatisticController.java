package com.jybl.admin.controller;

import com.jybl.admin.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping("/listAll")
    public Map getStatistic(HttpServletRequest request) {

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("orderStatistic", statisticService.get12MonthOrderStatistic());
        map.put("orderOverview", statisticService.getOverviewOrderStatistic());
        map.put("groupStatistic", statisticService.getRangeGroup());
        map.put("userStatistic", statisticService.get12MonthUserStatistic());
        map.put("userOverview", statisticService.getOverviewUserStatistic());

        return map;

    }

}
