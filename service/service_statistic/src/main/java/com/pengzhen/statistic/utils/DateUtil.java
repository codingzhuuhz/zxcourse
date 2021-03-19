package com.pengzhen.statistic.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
    public static String getBeforeDate(){
        Calendar day=Calendar.getInstance();
        day.add(Calendar.DATE,-1);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(day);
    }
}
