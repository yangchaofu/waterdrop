package com.yangc.waterdrop.service;

import java.util.List;

import com.yangc.waterdrop.dao.DateRecordDAO;
import com.yangc.waterdrop.entity.CountResult;

public class CountResultService {
	private DateRecordDAO drDAO = new DateRecordDAO();
	private int timeGap = 10;
	
	public int getTimeGap() {
		return timeGap;
	}

	public void setTimeGap(int timeGap) {
		this.timeGap = timeGap;
	}

	public List<CountResult> getDefaultList() {
		String oneDay = drDAO.selectLast();
		oneDay = oneDay.substring(0, 10) + " 00:00:00";
		String oneDayFirst = drDAO.selectOneDayFirst(oneDay);
		String ondDayLast = drDAO.selectOneDayLast(oneDay);
		
		return drDAO.getCountResult(oneDayFirst, ondDayLast, timeGap);		
	}
}
