package lambda;

import java.util.Scanner;
import java.util.Stack;

public class TestB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();// 3[k]2[mn]   3[k2[mn]]
        if(!(str1.length()<=1000)){
            return;
        }
        Stack<String> stack = new Stack<>();
        int tmp =0;
        for (int i=0;i<str1.length();i++) {
            i=tmp;
            char char1 = str1.charAt(i);
            int num =0;

            if(char1!='['&&char1!=']'&&!(char1 >='a' && char1 <='z')){//
                num = Integer.parseInt(String.valueOf(char1));
                System.out.println(num);
            }

            if(char1=='['){//入栈  进入
                for (int k=i;k<str1.length();k++) {
                    if(char1==']'){//出栈
                        tmp=k;
                        break;
                    }
                    char char2 = str1.charAt(k);
                    stack.push(String.valueOf(char2));

                }
            }
            if((char1 >='a' && char1 <='z')){
                 for (int j=0;j<str1.length();j++) {
               // stack.push(String.valueOf(char1));
                }

            }


        }

    }


}
