package test;

import java.io.UnsupportedEncodingException;

public class Gbk2Utf8 {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String b = "你好";
		String b1 = new String(b.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(b1);
		
		
		String gbk = "我来了";    
		String iso = new String(gbk.getBytes("UTF-8"),"ISO-8859-1");    
		String utf8=new String(iso.getBytes("ISO-8859-1"),"UTF-8");  
		System.out.println(iso);
		System.out.println(utf8);
		


	}

}
