package new2022.test2;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {

    public static  String print(int num){
        return IntStream.range(1, num).mapToObj(FizzBuzz::tag).collect(Collectors.joining("\n"));
    }

    private static  String tag(int turn) {
        if(turn % 3 ==0 && turn % 5 == 0) return "FizzBuzz";
        if(turn % 3 ==0) return "Fizz";
        if (turn % 5 == 0) return "Buzz";
        return String.valueOf(turn);
    }

    public static void main(String[] args) {
        System.out.println(FizzBuzz.print(100));
    }
}
