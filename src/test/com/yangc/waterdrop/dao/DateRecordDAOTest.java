package test.com.yangc.waterdrop.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yangc.waterdrop.dao.DateRecordDAO;
import com.yangc.waterdrop.dao.FileDAO;
import com.yangc.waterdrop.entity.CountResult;

public class DateRecordDAOTest {
	private FileDAO fd = new FileDAO();
	private DateRecordDAO drDAO = new DateRecordDAO();
//	private MySQLDateRecordDAO mysqlDrDAO = new MySQLDateRecordDAO();
	private final String path = "C:/Users/yangc/Desktop/main/数据可视化/ref data/1904水滴数据已转换.txt";
	
	@Ignore
	@Test
	public void testInsertList() {
		File f = new File(path);		
		int result = drDAO.insertList(fd.readFile(f));
		assertEquals(1, result);
	}
	
	@Ignore
	@Test
	public void testSelectList() {
		List<String> dateStrList;
		dateStrList = drDAO.selectList();
		System.out.println("数据库中的size为"+dateStrList.size());
		for (String string : dateStrList) {
			System.out.println(string.replaceAll(".000000", ""));
		}
	}
	@Ignore
	@Test
	public void testSelectFirst() {
		String firstDate = drDAO.selectFirst();
		System.out.println("第一条数据为:"+firstDate);
	}
	@Ignore
	@Test
	public void testSelectLast() {
		String lastDate = drDAO.selectLast();
		System.out.println("最后一条数据为:"+lastDate);
	}
	
	@Ignore
	@Test
	public void testSelectOneDayFirst() {
		String oneDay = "2019-04-23 00:00:00";
		String oneDayFirst = drDAO.selectOneDayFirst(oneDay);
		System.out.println(oneDay + "的第一条数据为:" + oneDayFirst);
	}
	
	@Ignore
	@Test
	public void testSelectOneDayLast() {
		String oneDay = "2019-04-23 00:00:00";
		String oneDayLast = drDAO.selectOneDayLast(oneDay);
		System.out.println(oneDay + "的最后一条数据为:" + oneDayLast);
	}
	
	@Test
	public void testGetCountResult() {
		int timeGap = 5;
		String oneDay = "2019-04-23 00:00:00";
		String oneDayFirst = drDAO.selectOneDayFirst(oneDay);
		String oneDayLast = drDAO.selectOneDayLast(oneDay);
		List<CountResult> cs = drDAO.getCountResult(oneDayFirst, oneDayLast, timeGap);
		for (CountResult countResult : cs) {
			System.out.println("tick:"+ countResult.getTickDate()+ " count:" +countResult.getCount() +" speed:" + countResult.getSpeed());
		}
	}

}
