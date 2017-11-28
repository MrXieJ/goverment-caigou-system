package com.jybl.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

    public final static Long getNowTimestamp(){
        return System.currentTimeMillis();
    }

    public final static String getNowFormatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = null;
        if (date == null) {
            dateStr = sdf.format(new Date());
        } else {
            dateStr = sdf.format(date);
        }
        return dateStr;
    }

}
