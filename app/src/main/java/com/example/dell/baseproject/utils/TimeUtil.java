package com.example.dell.baseproject.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class TimeUtil {

    // 不可用
    public static long getDifftime(String expiryDate) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date startDate = sf.parse(expiryDate);
        Date endTime = new Date(System.currentTimeMillis());
        long diffTime = endTime.getSeconds() - startDate.getSeconds();
        Log.e("timeUtil", "timer = "+diffTime);
        return diffTime;
    }

    // 不可用
    public static String getStartToEndTime(String startTime, String endTime) throws Exception{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        startTime = String.format(Locale.CHINA, "yyyy/MM/dd HH:mm:ss", startTime);
//        endTime = String.format(Locale.CHINA, "yyyy/MM/dd HH:mm:ss", endTime);
        Date startDate = sf.parse(startTime);
        Date endDate = sf.parse(endTime);
        long diffTime = (endDate.getTime() - startDate.getTime())/1000;
        long hour = diffTime / 3600;
        long min = diffTime % 3600 / 60;
        long second = diffTime % 60;
        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
    }

    public static String getFormatString(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sf.format(date);

    }

    public static  String getStringTime(int cnt) {
        int hour = cnt / 3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
    }
}
