package com.yangc.waterdrop.util;

import java.io.File;

public class PathUtil {
	public static String getDBPath() {
//		File f = new File(HsqldbUtil.class.getResource("/").getPath());
		String path = HsqldbUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		File file = new File(path);
		String absPath = file.getAbsolutePath();
		System.out.println("jar 文件绝对路径"+file.getAbsolutePath());
		absPath.replaceAll("\\.", "");
		System.out.println("去掉点之后的效果"+absPath);
//		String jvmPath = System.getProperty("java.class.path");
		System.out.println("jar 路径:"+path);
//		System.out.println("jvmPath:"+jvmPath);
//		String parentPath = f.getParent();
//		System.out.println("获取父节点路径: " + parentPath);
		
		
		
		String append = File.separator + "hsqldb" + File.separator + "database" + File.separator + "waterdrop";
		StringBuffer dbPath = new StringBuffer(absPath + append);
		System.out.println(dbPath);
//        System.out.println(f);
        String dbPathStr = dbPath.toString().replaceAll("\\\\", "/");
        return dbPathStr;
	}
}
