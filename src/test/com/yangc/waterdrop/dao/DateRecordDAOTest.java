package test.com.yangc.waterdrop.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yangc.waterdrop.dao.DateRecordDAO;
import com.yangc.waterdrop.dao.FileDAO;

public class DateRecordDAOTest {
	private FileDAO fd = new FileDAO();
	private DateRecordDAO drDAO = new DateRecordDAO();
	private final String path = "C:/Users/yangc/Desktop/main/数据可视化/ref data/1904水滴数据已转换.txt";
	
	@Ignore
	@Test
	public void testInsertList() {
		File f = new File(path);		
		int result = drDAO.insertList(fd.readFile(f));
		assertEquals(1, result);
	}
	

	@Test
	public void testSelectList() {
//		testInsertList();
		List<String> dateStrList;
		dateStrList = drDAO.selectList();
		System.out.println("数据库中的size为"+dateStrList.size());
		for (String string : dateStrList) {
			System.out.println(string.replaceAll(".000000", ""));
		}
	}

}
