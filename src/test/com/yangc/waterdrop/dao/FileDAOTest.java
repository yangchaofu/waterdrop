package test.com.yangc.waterdrop.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.yangc.waterdrop.dao.FileDAO;

public class FileDAOTest {
	private FileDAO fd = new FileDAO();
	private final String path = "C:/Users/yangc/Desktop/main/数据可视化/ref data/1904水滴数据已转换.txt";
	
	// 以下用例通过了测试, 可以确定系统可以按行读取文件的数据信息
	@Test
	public void testReadFile() {
		File f = new File(path);
		List<Date> dateList = fd.readFile(f);
		System.out.println(dateList.size());
		assertEquals(500, dateList.size());
	}

}
