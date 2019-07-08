package com.yangc.waterdrop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jasonLu
 * @date 2017/6/1 11:33
 * @Description: 计算文件的md5, 分为单个文件和文件夹
 */
public class CalacFileMd5Util {
	/**
	 * 获取单个文件的MD5值！
	 *
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
	
	/**
	 * 获取单个文件的MD5值！
	 *
	 * @param fileNme 文件的名字
	 * @return
	 */
	public static String getFileMD5(String fileName) {
		File file = new File(fileName);
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/**
	 * 获取文件夹中文件的MD5值
	 *
	 * @param file
	 * @param listChild ;true递归子目录中的文件
	 * @return
	 */
	public static Map<String, String> getDirMD5(File file, boolean listChild) {
		if (!file.isDirectory()) {
			return null;
		}
		// <filepath,md5>
		Map<String, String> map = new HashMap<>();
		String md5;
		File files[] = file.listFiles();
		for (File f : files) {
			if (f.isDirectory() && listChild) {
				map.putAll(getDirMD5(f, listChild));
			} else {
				md5 = getFileMD5(f);
				if (md5 != null) {
					map.put(f.getPath(), md5);
				}
			}
		}
		return map;
	}

	
	/**
	 * 将文件的md5值写入到json文件中
	 * @param str 文件的md5值
	 */
	@SuppressWarnings("unused")
	private static void writeToFile(String str)

	{
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream("E:/FileMD5.json"), "UTF-8");
			out.write(str);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}