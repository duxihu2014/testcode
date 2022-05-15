package new2022;

import java.util.*;

public class Test1 {


//    将1.java实现代码和2.运行结果截图发送邮件至clarkzx@163.com，邮件标题：您的中文名+您的手机号
//1.从键盘接收1个数字，1打印1次，2打印2次，...... 例子：从键盘接收数字5，打印结果如下 1 22 333 4444 55555 .......
//            2.从键盘接收一个字符串，打印出字符串中重复次数最多的字母和重复的次数。
//            3.从键盘接收5个数字并用stream把这5个数字中的偶数选出来。
//            4.有这样一个数列：1, 2, 3, 5, 8, 13, 21, 34, 55, …,从键盘接收数字n并打印第n项。
public static void main(String[] args) {


//    System.out.println("第一题目============");
//    Test1.test01();
    System.out.println("第二题目========");
    Test1.test02();
//    System.out.println("第3题目========");
//    Test1.test03();
//    System.out.println("第4题目========");
//    Test1.test04();

}

//1.从键盘接收1个数字，1打印1次，2打印2次，...... 例子：从键盘接收数字5，打印结果如下 1 22 333 4444 55555 .......
    public static  void test01(){
        Scanner in = new Scanner(System.in);
        int x=in.nextInt();
//        System.out.println(x);

        long result = 0;
        int i =1;
        while (i <=x){
            result = result * 10 + 1;
            System.out.print(result*i+" ");
            i++;
        }
    }


    public static  void test03(){
        Scanner in = new Scanner(System.in);
        int x1=in.nextInt();
        int x2=in.nextInt();
        int x3=in.nextInt();
        int x4=in.nextInt();
        int x5=in.nextInt();
        List<Integer> numbers = Arrays.asList(x1, x2, x3, x4, x5);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
    }


//    4.有这样一个数列：1, 2, 3, 5, 8, 13, 21, 34, 55, …,从键盘接收数字n并打印第n项。
    public static  void test04(){
        Scanner in = new Scanner(System.in);
        int x=in.nextInt();
        System.out.println(f(x));
    }

    public static int  f(int n){
          if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        else
            return f(n-1) + f(n-2);
    }




//            2.从键盘接收一个字符串，打印出字符串中重复次数最多的字母和重复的次数。
    public static void test02(){
        Scanner in = new Scanner(System.in);
        String x=in.nextLine();
        System.out.println(x);
        SortedMap<String,Integer> sortedMap = new TreeMap<String,Integer>();
        for(char str:x.toCharArray()){
            if(sortedMap.get(String.valueOf(str))!=null){
                sortedMap.put(String.valueOf(str),sortedMap.get(String.valueOf(str)).intValue()+1);
            }else{
                sortedMap.put(String.valueOf(str),new Integer(1));
            }
        }

        Set<Map.Entry<String,Integer>> entry2 = sortedMap.entrySet();
        Integer maxSize = 0;
        String maxSizeStr = "";
        for(Map.Entry<String,Integer> temp : entry2){
            if(temp.getValue()>maxSize){
                maxSize =  temp.getValue();
                maxSizeStr = temp.getKey();
            }
        }
        System.out.println("str=:  "+maxSizeStr+" 值:   "+maxSize);

    }


}
