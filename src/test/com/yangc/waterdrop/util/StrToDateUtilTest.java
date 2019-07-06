package test.com.yangc.waterdrop.util;

import org.junit.Test;

import com.yangc.waterdrop.util.DateStrSwitchUtil;

public class StrToDateUtilTest {

	@Test
	public void testStrToDate() {
		String date = "2019/04/23 19:22:00";
		DateStrSwitchUtil.strToDate(date);		
	}

}
