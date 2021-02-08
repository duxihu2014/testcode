package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String a1 = "/hhd/jiaohang_ftp/moni_data.txt";
//		String a1 = "aaa.txt";
//		System.out.println(a1.lastIndexOf("/"));
//		System.out.println(a1.substring(a1.lastIndexOf("/") + 1, a1.length()));

//		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = TestString.StringToDate("2013-11-19", "yyyy-MM-dd");
		System.out.println(date.getDate());;
		date.setDate(date.getDate()+1);
		System.out.println(date.getDate());
		String end_date = sdf.format(date);
		System.out.println(end_date);
//		c.add(Calendar.MONTH, +1);
//
//		String start_date = sdf.format(c.getTime());
		//String end_date = sdf.format(new Date());

	}

	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
