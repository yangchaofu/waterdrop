package com.yangc.waterdrop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yangc.waterdrop.util.MySQLUtil;
import com.yangc.waterdrop.util.DateStrSwitchUtil;

public class MySQLDateRecordDAO {
	/**
	 * 插入一个日期列表中的所有数据到date_record表中
	 * @param dateList 待插入的日期列表
	 * @return 如果全部插入完成返回1
	 */
	public int insertList(List<Date> dateList) {
		String sql = "INSERT INTO date_record(date) values(?)"; 
		// 获取数据库连接(MySQL的驱动)
		Connection c = MySQLUtil.getMySQLConnection();
		// 创建预编译SQL		
		try (PreparedStatement ps = c.prepareStatement(sql);)
		{	
			int count = 0;
			for (Date date : dateList) {
				String strDate = DateStrSwitchUtil.dateToStr(date);
//				System.out.println("日期:" + strDate);
				ps.setString(1, strDate);
				ps.execute();
				count++;
			}
			System.out.println("执行的插入次数为:"+count);
		} catch (SQLException e) {
			System.out.println("插入日期数据时发生异常!");
			e.printStackTrace();
		}finally {
			try {
				System.out.println("成功关闭连接!");
				c.close();
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
		
		return 1;
	}
	
	/**
	 * 查询数据库中的全部数据
	 * @return 全部数据的列表
	 */
	public List<String> selectList() {
		List<String> dateStrList = new ArrayList<>();
		String sql = "SELECT date FROM date_record"; 
		// 获取数据库连接
		Connection c = MySQLUtil.getMySQLConnection();
		// 创建Statement并执行		
		Statement st;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String date = rs.getString(1);
	            dateStrList.add(date);
	        }
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		}
				
		return dateStrList;
	}
	
	
}
