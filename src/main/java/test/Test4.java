package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test4 {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = df.parse("2013-09".concat("-01"));
		Calendar c =  Calendar.getInstance();
		c.setTime(startDate);
		
		c.add(Calendar.MONTH, 1);
		Date endDate = c.getTime();
		String date1 = df.format(startDate);
		String date2 = df.format(endDate);
		
		System.out.println(date1+"  =============="+date2);

	}

}
