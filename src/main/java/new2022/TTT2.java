package new2022;

import java.util.Scanner;

public class TTT2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String readStr = in.nextLine().toUpperCase();
        String readStr2 = in.nextLine().toUpperCase();
        int j = 0;
        for (int i = 0; i < readStr.length() ; i++) {
            if(String.valueOf(readStr.charAt(i)).equals(readStr2)){
                j++;
            }
        }
        System.out.print(j);
    }
}


//编译器信息
//版本：OpenJDK 1.8。支持Java8的最新特性，比如stream操作和lambda表达式。
//输入输出处理
//1. 核心代码模式处理
//不需要处理任何输入输出，直接返回值即可。
//2. ACM 模式
//你的代码需要处理输入输出，请使用如下样例代码读取输入和打印输出：
//import java.util.Scanner;
//
//// 注意类名必须为 Main, 不要有任何 package xxx 信息
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
//    }
//}