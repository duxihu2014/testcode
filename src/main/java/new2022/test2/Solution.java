package new2022.test2;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'fizzBuzz' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void fizzBuzz(int n) {
     System.out.println(IntStream.range(1, n+1).mapToObj(Result::tag).collect(Collectors.joining("\n")));
    }
    
    private static  String tag(int turn) {
        if(turn % 3 ==0 && turn % 5 == 0) return "FizzBuzz";
        if(turn % 3 ==0) return "Fizz";
        if (turn % 5 == 0) return "Buzz";
        return String.valueOf(turn);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.fizzBuzz(n);

        bufferedReader.close();
    }
}