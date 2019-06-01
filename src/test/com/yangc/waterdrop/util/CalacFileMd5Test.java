package test.com.yangc.waterdrop.util;

import java.io.File;

import org.junit.Test;

import com.yangc.waterdrop.util.CalacFileMd5Util;

public class CalacFileMd5Test {	
	private final String path = "C:/Users/yangc/Desktop/main/数据可视化/ref data/1904水滴数据已转换1 - 副本.txt";
	
	//1904水滴数据已转换 - D00CFC760E918FA80859EAFD002B621A
	//1904水滴数据已转换1 - d00cfc760e918fa80859eafd002b621a
	//1904水滴数据已转换1 - 副本.txt - d00cfc760e918fa80859eafd002b621a
	// 经过以上测试,可以说明文件名的改变不会影响文件的md5值,通过计算md5来保证文件的唯一性
	@Test
	public void testGetFileMD5() {
		File f = new File(path);
		String md5 = CalacFileMd5Util.getFileMD5(f);
		System.out.println(md5);
		
	}

}
