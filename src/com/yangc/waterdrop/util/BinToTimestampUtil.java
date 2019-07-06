package com.yangc.waterdrop.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BinToTimestampUtil {

	public static List<String> readBinTimeStamp(String fileName) {	
		List<String> dateList = new ArrayList<String>();
		try (				
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			){
			byte[] byteArr = new byte[8];
			while(in.read(byteArr) != -1) {
//				System.out.println(Arrays.toString(byteArr));
				for (int i = 4; i < byteArr.length; i++) {
					byteArr[i] = 0;
				}
				byteArr = changeBytes(byteArr);
				long tmp = bytesToLong(byteArr);
//				System.out.println("转换后的长整型为"+tmp);
				dateList.add(timeStamp2Date(tmp));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
	// 转换为小端序
	public static byte[] changeBytes(byte[] a){
	    byte[] b = new byte[a.length];
	    for (int i = 0; i < b.length; i++) {
	        b[i] = a[b.length - i - 1];
	    }
	    return b;
	}

	private static ByteBuffer buffer = ByteBuffer.allocate(8);

	// byte 数组与 long 的相互转换
	public static byte[] longToBytes(long x) {
		buffer.putLong(0, x);
		return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
		buffer.clear();
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();// need flip
		
		return buffer.getLong();
	}  
	
	/**
	 * 时间戳转时间
	 * @param s 长整型日期
	 * @return 日期字符串
	 */
    public static String stampToDate(Long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
//        System.out.println("转换后的日期字符串为;" + res);
        return res;
    }
    
  /**
   * 将Unix的时间戳转换为正常的时间格式并返回字符串  
   * @param timestampLong 带待转换的Unix时间戳
   * @return 转换完成的时间字符串
   */
    public static String timeStamp2Date(Long timestampLong){  
        Long timestamp = timestampLong * 1000;  
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
//        System.out.println("转换后的日期字符串为;" + date);
        return date;  
    } 

}