package com.yangc.waterdrop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {
	/**
	 * 此处的代码用于测试使用, 获取MySQL的连接
	 * @return
	 */
	public static Connection getMySQLConnection() {
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 数据库url
			String url = "jdbc:mysql://127.0.0.1:3306/waterdrop?characterEncoding=UTF-8";
			Connection c = DriverManager.getConnection(url,"root", "admin");
			//返回数据库连接
			return c;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("获取数据库连接失败!");
			e.printStackTrace();
		}
		return null;
	}
}
