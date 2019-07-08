package com.yangc.waterdrop.service;

import java.util.Date;
import java.util.List;

import com.yangc.waterdrop.dao.DateRecordDAO;
import com.yangc.waterdrop.dao.FileRecordDAO;
import com.yangc.waterdrop.util.BinToTimestampUtil;
import com.yangc.waterdrop.util.CalacFileMd5Util;
import com.yangc.waterdrop.util.DateUtil;

public class ImportFileService {
	private FileRecordDAO fileRecordDAO = new FileRecordDAO();	
	private DateRecordDAO dateRecordDAO = new DateRecordDAO();
	private String filePath = null;
	
	public ImportFileService(String path) {
		this.filePath = path;
	}		
	
	//将日期数据插入数据库
	public String insertToData() {
		int count = 0;
		//计算导入文件的md5值
		String importFileMd5 = CalacFileMd5Util.getFileMD5(filePath);
		//从数据库中查询该文件是否存在,通过md5唯一标识,如果存在返回该文件在数据库中的名字
		String existFileName = fileRecordDAO.select(importFileMd5);
		// 如果存在,返回该文件的名字
		if(existFileName != null) {
			return "该文件已经导入过, 原导入文件名为: "+existFileName;
		}else {//如果不存在,将该文件中的数据读取出来,读取之后判断是否为日期数据
			List<String> dateStrList = BinToTimestampUtil.readBinTimeStamp(filePath);
			List<Date> dateList = DateUtil.strlistToDatelist(dateStrList);			
			//将转换后的日期列表插入数据库中
			count = dateRecordDAO.insertList(dateList);
		}
		
		return "数据导入成功, 共计"+count+"数据!";
	}
}
