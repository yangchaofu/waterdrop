package com.yangc.waterdrop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yangc.waterdrop.entity.CountResult;
import com.yangc.waterdrop.util.DateUtil;
import com.yangc.waterdrop.util.HsqldbUtil;
import com.yangc.waterdrop.util.StrDateSwitchUtil;

public class DateRecordDAO {
	/**
	 * 插入一个日期列表中的所有数据到date_record表中
	 * @param dateList 待插入的日期列表
	 * @return 如果全部插入完成返回1
	 */
	public int insertList(List<Date> dateList) {
		String sql = "INSERT INTO date_record(date) values(?)"; 
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建预编译SQL		
		try (PreparedStatement ps = c.prepareStatement(sql);)
		{	
			int count = 0;
			for (Date date : dateList) {
				String strDate = StrDateSwitchUtil.dateToStr(date);
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
				c.close();
				System.out.println("成功关闭连接!");
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
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String date = rs.getString(1);
	            date.replaceAll(".000000", "");
	            dateStrList.add(date);
	        }
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
				
		return dateStrList;
	}
	
	/**
	 * 查询数据库中的最早一条数据的日期, 用于计算统计
	 * @return 最早一条数据
	 */
	public String selectFirst() {		
		String sql = "SELECT date FROM date_record ORDER BY date LIMIT 1"; 		  
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		String date = null;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	           date = rs.getString(1);
	           date.replaceAll(".000000", "");
	        }
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
		date = date.replaceAll("\\\\", "/");
		System.out.println("数据库中的第一个日期为:"+date);
		return date;
	}
	
	/**
	 * 查询数据库中的最新一条数据的日期, 用于计算统计
	 * @return 最新一条数据
	 */
	public String selectLast() {		
		String sql = "SELECT date FROM date_record ORDER BY date DESC LIMIT 1"; 		  
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		String date = null;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	           date = rs.getString(1);
	           date.replaceAll(".000000", "");
	        }
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
		System.out.println("数据库中的最近一个日期为:"+date);
		
		return date;
	}
	
	
	/**
	 * 需要绘制统计图的默认数据列表, 就是最近一天的数据列表
	 * @param 指定的某一天的日期
	 * @deprecated 无需为此单独写一个方法,使用选择指定某一天的数据即可, 返回值也不能使用List<String>, 应该使用CountResult
	 * @return 最近一天的数据列表
	 */
	public List<String> selectDefaultList(String date) {
		List<String> defaultDateList = new ArrayList<>();
		String sql = "SELECT date FROM date_record WHERE date >= '" + date + "' ORDER BY date"; 		
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String dateTmp = rs.getString(1);
	            dateTmp.replaceAll(".000000", "");
	            defaultDateList.add(dateTmp);
	        }
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
				
		return defaultDateList;
	}
	
	
	/**
	 * 选择指定日期的第一条数据, 使用SQL的limit 1来选出
	 * @param date 指定的日期
	 * @return 指定日期的第一条数据
	 */
	public String selectOneDayFirst(String date) {
		String dateTmp = null;
		String sql = "SELECT date FROM date_record WHERE date >= '" + date + "' ORDER BY date LIMIT 1"; 
		System.out.println("selectOneDayFirst的Sql为:" + sql);
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				dateTmp = rs.getString(1);
				dateTmp.replaceAll(".000000", "");				
			}
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
		
		return dateTmp;
	}
	
	/**
	 * 获取指定日期的最后一条数据, 使用DESC(倒序排序)和Limit 1实现
	 * @param date 指定的日期
	 * @return 指定日期的最后一条数据, 返回字符串
	 */
	public String selectOneDayLast(String date) {
		String dateTmp = null;
		String sql = "SELECT date FROM date_record WHERE date >= '" + date + "' ORDER BY date DESC LIMIT 1"; 
		System.out.println("selectOneDayLast的Sql为:" + sql);
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		try {
			st = c.createStatement();
			// 获取执行结果
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				dateTmp = rs.getString(1);
				dateTmp.replaceAll(".000000", "");				
			}
		} catch (SQLException e) {
			System.out.println("从数据库中查询全部数据失败");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
		
		return dateTmp;
	}
		
	/**
	 * 获取需要绘制统计图的数据, 根据
	 * @param firstData
	 * @param lastData
	 * @param timeGap
	 * @return
	 */
	public List<CountResult> getCountResult(String firstData, String lastData, double timeGap) {
		// 存放查询结果
		List<CountResult> countResList = new ArrayList<>();
		// 定义一个接收统计数据的对象
		CountResult countResult = null;
		// 定义临时日期字符串,该对象是一个标记对象, 会随着一轮查询结束之后被重新赋值
		String tmpDate = firstData;
		tmpDate = tmpDate.replaceAll(".000000", "");
		// 数据中x轴上的数据
		String categoryDate = null;
		
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建Statement并执行		
		Statement st;
		try {
			st = c.createStatement();
			do {			
				categoryDate = DateUtil.getGapDate(tmpDate, lastData, timeGap);
				//编写SQL语句, 该SQL语句查询出date_record表中, date字段大于 tmpDate,并且小于categoryDate的统计数
				// 并根据时间间隔计算出速度(速度 = 单位时间(分钟)内的水滴数 / 时间间隔)
				String sql = "SELECT count(*) FROM date_record WHERE date >= '" + tmpDate + "' and date < '" + categoryDate + "'";
				if(categoryDate.equals("越界")) {
					break;
				}
				System.out.println("当前的sql语句为:"+sql);
				// 获取执行结果	
				ResultSet rs = st.executeQuery(sql);		
		        while (rs.next()) {
		        	if(rs.getInt(1) == 0) {
		        		continue;
		        	}
		        	countResult = new CountResult();
		        	countResult.setTickDate(tmpDate);		        	
		        	countResult.setCount(rs.getInt(1));
		        	countResult.setSpeed(countResult.getCount() / timeGap);
		        	
		            countResList.add(countResult);
		        }
		        tmpDate = categoryDate;
			} while(!categoryDate.equals("越界"));
			
		} catch (SQLException e) {
			System.out.println("执行查询统计数据出错!");
			e.printStackTrace();
		} finally {
			try {
				c.close();
				System.out.println("成功关闭连接!");
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败!");
				e.printStackTrace();
			}
		}
		
		return countResList;
	}
	
}
