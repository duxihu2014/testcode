package test;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * ��ʽ��ʱ���� DateFormat.FULL = 0 DateFormat.DEFAULT = 2 DateFormat.LONG = 1
 * DateFormat.MEDIUM = 2 DateFormat.SHORT = 3
 */

public class Test5 {
    public static void main(String[] args) {
        Date d = new Date();
        String s;

        
        Calendar c = Calendar.getInstance();     
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
        c.add(Calendar.MONTH, -1);
        System.out.println(sdf.format(c.getTime())); 
        
        
        /** Date��ĸ�ʽ: Sat Apr 16 13:17:29 CST 2006 */
        System.out.println(d);

        System.out.println("******************************************");

        /** getDateInstance() */
        /** �����ʽ: 2006-4-16 */
        s = DateFormat.getDateInstance().format(d);
        System.out.println(s);

        /** �����ʽ: 2006-4-16 */
        s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(d);
        System.out.println(s);

        /** �����ʽ: 2006��4��16�� ������ */
        s = DateFormat.getDateInstance(DateFormat.FULL).format(d);
        System.out.println(s);

        /** �����ʽ: 2006-4-16 */
        s = DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        System.out.println(s);

        /** �����ʽ: 06-4-16 */
        s = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
        System.out.println(s);

        /** �����ʽ: 2006-01-01 00:00:00 */
        DateFormat format1 = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        s = format1.format(new Date());
        System.out.println(s);

        /** �����ʽ: 2006-01-01 01:00:00 */
        System.out.println((new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss")).format(new Date()));

        /** �����ʽ: 2006-01-01 13:00:00 */
        System.out.println((new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss")).format(new Date()));

        /** �����ʽ: 20060101000000 ***/
        DateFormat format2 = new SimpleDateFormat(
                "yyyyMMddhhmmss");
        s = format2.format(new Date());
        System.out.println(s);
    }
}    
