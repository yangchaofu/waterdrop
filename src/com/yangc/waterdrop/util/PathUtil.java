package com.yangc.waterdrop.util;

import java.io.File;

public class PathUtil {
	public static String getDBPath() {
		File f = new File(HsqldbUtil.class.getResource("/").getPath());
		String parentPath = f.getParent();
		System.out.println("获取父节点路径: " + parentPath);
		String append = File.separator + "hsqldb" + File.separator + "database" + File.separator + "waterdrop";
		StringBuffer dbPath = new StringBuffer(parentPath + append);
		System.out.println(dbPath);
        System.out.println(f);
        String dbPathStr = dbPath.toString().replaceAll("\\\\", "/");
        return dbPathStr;
	}
}
