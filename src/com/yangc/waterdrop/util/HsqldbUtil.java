package com.yangc.waterdrop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hsqldb相关的工具类
 * 包括获取连接 getConnection()
 * @author yangc
 *
 */
public class HsqldbUtil {
	/**
	 * 获取数据库连接
	 * @return 成功获取的数据库连接
	 */
	public static Connection getConnection() {
		try {
			// 加载驱动
			Class.forName("org.hsqldb.jdbcDriver");
			// 数据库url
			System.out.println(PathUtil.getDBPath());
			String dbPath = PathUtil.getDBPath();
			
			System.out.println(dbPath);
			String url = "jdbc:hsqldb:file:"+ dbPath +";shutdown=true";
			// 获取连接, url, user, password
			Connection c = DriverManager.getConnection(url, "root", "admin");
			
			return c;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("获取数据库连接失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	
}