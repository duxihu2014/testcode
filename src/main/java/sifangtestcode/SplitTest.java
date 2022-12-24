package sifangtestcode;

public class SplitTest {

    public static void main(String[] args) {
//        对于特殊字符的分隔|  ^   $  *   .    (    )   \   /  + *等都是正则表达式的一部分，
//        只能通过前面加上\\进行转义。注意\要用三个\\\，也就是split（“\\\\”）;
//        不转义字符：a：,号分割　　b：空格分割

//        String[] aa = "aaa|bbb|ccc".split("|");//error
        //String[] aa = "aaa|bbb|ccc".split("\\|"); 这样才能得到正确的结果
//        String[] aa = "aaa\\bbb\\bccc".split("\\\\");//如果想在串中使用"\"字符,则也需要转义.
        // 首先要表达"aaaa\bbbb"这个串就应该用"aaaa\\bbbb",如果要分隔就应该这样才能得到正确结果,



        String[] aa = "/root/test/abc/defg".split("\\/");//如果想在串中使用"\"字符,则也需要转义.

        for (int i = 0 ; i<aa.length;i++){
        System.out.println(aa[i]);
    }



//        test2();
//        main2();
    }

    public static void test2(){
        System.out.println("分隔后字符串数组长度为");
        String ss = "abcabcdefg";
        String[] split = ss.split("[bc]",3);//b和c都当作分割的字符。
        for(String st:split){
            System.out.println(st);
        }
        System.out.println("分隔后字符串数组长度为");
        System.out.println(split.length);
    }


    public static void main2() {

//        1. \n unix,linux系统，好像新的mac也是这样的。
//
//        2. \r 有的mac系统
//
//        3. \r\n window系统。
//
//        自己观察，你会发现规律，其实用一个正则表达式就可以满足： \r?\n
        StringBuilder sb = new StringBuilder("");

        sb.append(" aaa \n");

        sb.append(" bbb \n");

        sb.append("ccc \n");

        sb.append("\n");

        sb.append("ddd\r\n");

        sb.append("\r\n");

        sb.append("eee\n");

        String text = sb.toString();

        System.out.println("---Original---");

        System.out.println(text);

        System.out.println("---Split---");

        int count = 1;

        String[] lines = text.split("\\r?\\n");

        for (String line : lines) {
            System.out.println("line " + count++ + " : " + line);
        }


    }
}
