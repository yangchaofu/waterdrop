package com.yangc.waterdrop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.yangc.waterdrop.dao.DateRecordDAO;
import com.yangc.waterdrop.entity.CountResult;
import com.yangc.waterdrop.util.DateStrSwitchUtil;

public class CountResultService {
	private DateRecordDAO drDAO = new DateRecordDAO();
	private int timeGap = 30;
	
	public int getTimeGap() {
		return timeGap;
	}

	public void setTimeGap(int timeGap) {
		this.timeGap = timeGap;
	}

	public List<CountResult> getDefaultList() {
		String oneDay = drDAO.selectLast();
		if(oneDay == null) {
			return null;
		}
		oneDay = oneDay.substring(0, 10) + " 00:00:00";
		String oneDayFirst = drDAO.selectOneDayFirst(oneDay);
		String oneDayLast = drDAO.selectOneDayLast(oneDay);
		if(oneDayFirst == null || oneDayLast == null) {
			return null;
		}
		
		return drDAO.getCountResult(oneDayFirst, oneDayLast, timeGap);		
	}
	
	public List<CountResult> getOneDayList(String oneDay) {
		if(oneDay == null) {
			return null;
		}
		System.out.println("待查询数据的日期为:" + oneDay);
		oneDay = oneDay.substring(0, 10) + " 00:00:00";
		String oneDayFirst = drDAO.selectOneDayFirst(oneDay);
		String oneDayLast = drDAO.selectOneDayLast(oneDay);
		if(oneDayFirst == null || oneDayLast == null) {
			return null;
		}
		// 当天仅一条数据
		if(oneDayFirst.equals(oneDayLast)) {
			List<CountResult> crList = new ArrayList<>();
			CountResult cr = new CountResult();
			cr.setCount(1);
			cr.setTickDate(oneDayLast.replaceAll(".000000", ""));
			cr.setSpeed(1.0);
			crList.add(cr);
			return crList;
		}
		
		return drDAO.getCountResult(oneDayFirst, oneDayLast, timeGap);		
	}
	
	/**
	 * 获取前一天或后一天
	 * @param srcDateStr 原来日期的字符串表达
	 * @param flag 1 后一天 -1 前一天
	 * @return
	 */
	public static String getTargetDay(String srcDateStr, int flag) {		
		Date srcDate = DateStrSwitchUtil.strToDateUnderLineNoTime(srcDateStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date targetDate = null;
//		System.out.println(sdf.format(srcDate));		
			
		Calendar calendar = new GregorianCalendar();
		if(flag == 1) {			
			calendar.setTime(srcDate);
			calendar.add(calendar.DATE, 1);
			targetDate = calendar.getTime();						
		} else if(flag == -1){
			calendar.setTime(srcDate);
			calendar.add(calendar.DATE, -1);
			targetDate = calendar.getTime();					
		}
		
		return sdf.format(targetDate);		
	}
}
