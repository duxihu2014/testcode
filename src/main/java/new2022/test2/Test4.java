package new2022.test2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test4 {
    public static void main(String[] args) {


        Test4.gemstones(Arrays.asList("abcddea","baccd","eeabg"));

//        System.out.println(Test4.filter("abcddea"));

    }

    public static int gemstones(List<String> rocks) {
        for (int i = 0; i < rocks.size(); i++) {
            //2.字符串数组转化成整数数组
            String[] intArray= new String[rocks.get(i).length()];
            Set<String> set = new HashSet<String>();
            for (int j = 0; j < rocks.get(i).length(); j++) {
                intArray[j]=String.valueOf(rocks.get(i).charAt(j));
            }
            //3.整数数组排序,Arrays工具类自带快排
            Arrays.sort(intArray);
            //4.排完顺序转化成字符串,单线程使用StringBuider好一点
            StringBuilder returnString=new StringBuilder();
            for (int j = 0; j < intArray.length; j++) {
                returnString.append(intArray[j]);
            }
            rocks.set(i,Test4.filter(returnString.toString()));
        }






        int count = rocks.size();
        int maxsize = 0;
        int totalsize =  rocks.get(0).length();
        for (int i = 0; i < count; i++) {

            int que = rocks.get(i).length();
            if(que<=totalsize){
                totalsize = que;
            }
        }


        for (int i = 0; i < totalsize; i++) {

            boolean flag = true;
            for (int j = 0; j < rocks.size(); j++) {
                String bb = String.valueOf(rocks.get(j).charAt(i));

                for (int jj = 0; jj < rocks.size(); jj++) {
                    String aa = String.valueOf(rocks.get(jj).charAt(i));
                    if(!bb.equals(aa)){
                        flag = false;
                    }

                }

            }
            if(flag)
                maxsize = maxsize+1;
        }

        return maxsize;

    }
    public static String filter(String s) {
        //非空判断
        if (s == null) {
            return null;
        }
        StringBuilder sbd = new StringBuilder();//创建一个空的StringBuilder
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c) == s.lastIndexOf(c)) {//第一次出现的下标跟最后一次相等,表示当前字符没有出现重复,直接添加进StringBuilder中
                sbd.append(c);
            } else {//出现重复
                if (s.indexOf(c) == i) {//如果重复出现的字符的位置等于当先字符的索引值,即表示当前字符为重复字符出现的第一次,将其加入StringBuilder中
                    sbd.append(c);
                }
            }
        }
        return sbd.toString();
    }


//    public static String removeRepeatChar(String str)
//
//    {
//        StringBuffer sb = new StringBuffer();
//
//        for (int i = 0; i<sb.length();i++)
//
//        {
//            char charWord = str.charAt(i);
//
//            int firstPosition = str.indexOf(charWord);
//
//            int lastPosition = str.lastIndexOf(charWord);
//
//            if (firstPosition == lastPosition || firstPosition == i)
//
//            {
//                sb.append(charWord);
//
//            }
//
//        }
//
//        return sb.toString();
//
//    }

//    public static String removeRepeat(String str)
//
//    {
//        StringBuffer sb = new StringBuffer(str);
//
//        String rs = sb.reverse()
//                .toString()
//                .replaceAll("(.)(?=.*\1)", "");
//
//        StringBuffer out = new StringBuffer(rs);
//        return out.reverse().toString();
//
//    }

//    private static String sortNumberString(String stringTest) {
//
//
//
//
//        //4.排完顺序转化成字符串,单线程使用StringBuider好一点
//        StringBuilder returnString=new StringBuilder();
//        for (int i = 0; i < intArray.length; i++) {
//            returnString.append(intArray[i]);
//            if(i!=intArray.length-1)
//                returnString.append(",");
//        }
//        return returnString.toString();
//    }
}
