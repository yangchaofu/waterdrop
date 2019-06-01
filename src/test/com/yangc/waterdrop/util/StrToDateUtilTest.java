package test.com.yangc.waterdrop.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yangc.waterdrop.util.StrDateSwitchUtil;

public class StrToDateUtilTest {

	@Test
	public void testStrToDate() {
		String date = "2019/04/23 19:22:00";
		StrDateSwitchUtil.strToDate(date);
		
	}

}
