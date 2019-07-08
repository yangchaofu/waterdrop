package com.yangc.waterdrop.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yangc.waterdrop.util.DateStrSwitchUtil;

/**
 * 对于文件中数据的操作, 读取文件中的数据
 * @author yangc
 */
public class FileDAO {
	/**
	 * 按行读取文件中的数据
	 * @return 返回一个日期列表
	 */
	public List<Date> readFile(File dateFile) {
		List<Date> dateList = new ArrayList<Date>();
		try (FileReader fr = new FileReader(dateFile);
			 BufferedReader br = new BufferedReader(fr);) {					
			String oneLine;
			while(!(oneLine = br.readLine()).equals("")) {
				System.out.println(oneLine);
				dateList.add(DateStrSwitchUtil.strToDateSlash(oneLine));
			}
		} catch (IOException e) {
			System.out.println("读取日期文件出错!");
			e.printStackTrace();
		}
		System.out.println("读取到的日期文件的大小为:"+dateList.size());	
		return dateList;
	}
}
