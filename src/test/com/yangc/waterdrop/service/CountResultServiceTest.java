package test.com.yangc.waterdrop.service;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.junit.Ignore;
import org.junit.Test;

import com.yangc.waterdrop.entity.CountResult;
import com.yangc.waterdrop.service.CountResultService;

public class CountResultServiceTest {

	private CountResultService crs = new CountResultService();
	
	@Ignore
	@Test
	public void testGetDefaultList() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetOneDayList() {
		String oneDay = "2019-04-23";
		List<CountResult> list = crs.getOneDayList(oneDay);
		if(list == null) {
			System.out.println("没有数据!");
			return;
		}else {			
			for (CountResult cr : list) {
				System.out.println(cr.getSpeed());
			}
		}
	}
	
	@Ignore
	@Test
	public void testDateAddOneDay() {
		Date today = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(calendar.DATE, 1);
		Date nextDay = calendar.getTime();		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(today));
		System.out.println(sdf.format(nextDay));
		
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(today);
		calendar2.add(calendar2.DATE, -1);
		Date preDate = calendar2.getTime();
		System.out.println(sdf.format(preDate));
	}
	
	@Test
	public void testGetTargetDay() {
		String target = CountResultService.getTargetDay("2019-04-23", 1);
		System.out.println(target);
		target = CountResultService.getTargetDay("2019-04-23", -1);
		System.out.println(target);
	}

}
