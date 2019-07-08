package com.yangc.waterdrop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yangc.waterdrop.util.HsqldbUtil;

public class FileRecordDAO {
	/**
	 * 插入一个文件的md5和文件名到file_record表中
	 * @param md5 文件的md5值
	 * @param fileName 文件名
	 * @return 插入状态标识
	 */
	public int insert(String md5, String fileName) {
		String sql = "INSERT INTO file_record(md5, file_name) values(?,?)"; 
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建预编译SQL		
		try (PreparedStatement ps = c.prepareStatement(sql);)
		{	
			ps.setString(1, md5);
			ps.setString(2, fileName);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("插入文件数据时发生异常!");
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
	 * 从file_record表中查询传入的md5值是否已经存在, 如果已经存在则返回该md5值的文件名
	 * @param md5 待查询文件名的md5码
	 * @return 返回该md5在数据库中存在的文件名
	 */
	public String select(String md5) {
		String existMd5 = null;
		
		String sql = "SELECT file_name FROM file_record WHERE md5 = ?"; 
		// 获取数据库连接
		Connection c = HsqldbUtil.getConnection();
		// 创建预编译SQL		
		try (PreparedStatement ps = c.prepareStatement(sql);)
		{	
			ps.setString(1, md5);		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				existMd5 = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("查询文件数据时发生异常!");
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
		
		return existMd5;
	}
}
