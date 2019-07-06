package com.yangc.waterdrop.util;

import java.util.Date;

public class DateUtil {
	/**
	 * 根据传入的日期, 时间间隔(单位分钟)和结束日期,获取新的日期并返回, 此方法的目的是x轴的下标日期
	 * @param oldDate 基准日期
	 * @param endDate 结束日期, 获取的日期不能超过该日期
	 * @param timeGap 时间间隔,单位分钟
	 * @return 获取到的新日期
	 */
	public static String getGapDate(String oldDate, String endDate, double timeGap) {		
		String newDate = null;
		// 只获取到分钟即可,此处将时间字符串进行截取
		oldDate = oldDate.substring(0, 16) + ":00";
		endDate = endDate.substring(0, 16) + ":00";
		System.out.println("截取后的时间为:" + oldDate);
		Date dateTmp = DateStrSwitchUtil.strToDateUnderLine(oldDate);
		long newDateTimestamp = dateTmp.getTime() + ((int)timeGap * 60 * 1000);
		newDate = DateStrSwitchUtil.dateToStr(new Date(newDateTimestamp));
		
		//由于字符串不能进行直接的大小比较, 这里需要把endDate转换为long型才能进行比较
		long endDateTimeStamp = DateStrSwitchUtil.strToDateUnderLine(endDate).getTime();
		//如果新的日期大于了结束日期, 则返回结束标识,获取一天的数据结束
		if(newDateTimestamp > endDateTimeStamp) {
			return "越界";
		}
		
		return newDate;
	}
}
