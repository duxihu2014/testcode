package new2022.test2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Test5 {

    public static void main(String[] args) {


        List<String> pangram = Arrays.asList("pack my box with five dozen liquor jugs","this is not a pangram");
        isPangram(pangram);

//        extracted();
    }

//    private static void extracted() {
//        int x =1;
//        int y =1;
//        int z = 0;
//        while (x<25){
//            z = x;
//            x = x +y;
//            y =z;
//        }
//        System.out.println(x);
//    }

//    pangram = ['pack my box with five dozen liquor jugs', 'this is not a pangram']
    public static String isPangram(List<String> pangram) {
        char a = 'a';
        char z = 'z';
        StringBuilder sb = new StringBuilder();
        for (String str : pangram) {
            HashSet<Character> charSet = new HashSet<>();
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                if (a <= aChar && aChar <= z) {
                    charSet.add(aChar);
                }
            }
            if (charSet.size() == 26) {
                sb.append(1);
            } else {
                sb.append(0);
            }
        }
        return sb.toString();
    }
}
