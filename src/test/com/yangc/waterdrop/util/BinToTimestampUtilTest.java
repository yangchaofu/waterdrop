package test.com.yangc.waterdrop.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.yangc.waterdrop.util.BinToTimestampUtil;

public class BinToTimestampUtilTest {	
	
	@Test
	public void testReadBinTimeStamp() {
		String fileName = "C:/Users/yangc/Desktop/main/数据可视化/ref data/1904水滴数据.txt";
		List<String> dateList = BinToTimestampUtil.readBinTimeStamp(fileName);
		for (String date : dateList) {
			System.out.println(date);
		}
	}

}
