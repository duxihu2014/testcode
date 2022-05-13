package lambda;

import java.util.Scanner;

public class TestA {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = in.nextLine();


        if(!(str1.length()<=100||str1.length()<=500000) ||TestA.checklowerCase(str1) ||TestA.checklowerCase(str2)){
            return;
        }
        int num = 0;
        for (int i=0;i<str1.length();i++){
            char char1 = str1.charAt(i);
            for (int j=num;j<str2.length();j++){
                char char2 = str2.charAt(j);
                if(char1==char2){
                    num = j+1;
                    break;

                }
            }
        }
        System.out.println(num-1);
    }

    public static boolean checklowerCase(String str){
        for (int i=0;i<str.length();i++) {
            char char1 = str.charAt(i);
            if(!(char1 >='a' && char1 <='z')){
                return true;
            }

        }
        return false;
    }
}
