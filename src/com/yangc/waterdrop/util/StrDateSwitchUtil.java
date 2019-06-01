package com.yangc.waterdrop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrDateSwitchUtil {
	/**
	 * 将传入的str转换为Date并返回
	 * @param str 待转换的字符串
	 * @return 转换后的日期
	 */
	public static Date strToDate(String str) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
        Date d = null;
               
        try {
            d = sdf.parse(str);
//            System.out.printf("字符串 %s 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: %s",str,d.toString());
        } catch (ParseException e) {
        	System.out.println("字符串转换日期失败!");
            e.printStackTrace();
        }
		return d;
	}
	
	/**
	 * 日期转为有格式的字符串
	 * @param date 需要转换的日期对象
	 * @return 转换后的日期字符串
	 */
	public static String dateToStr(Date date) {
		 //y 代表年
        //M 代表月
        //d 代表日
        //H 代表24进制的小时
        //h 代表12进制的小时
        //m 代表分钟
        //s 代表秒
        //S 代表毫秒
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );        
        String str = sdf.format(date);
//        System.out.println("当前时间通过 yyyy/MM/dd HH:mm:ss格式化后的输出: " + str);
        
        return str;
   	}
}
