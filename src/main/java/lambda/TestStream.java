package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestStream {
    public static void main(String[] args) {

        //惰性求值  不打印内容
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream().filter(one ->{
            System.out.println(one);
            return one.contains("aa");
        });

        //及早求值 打印内容
        long count = list.stream().filter(one ->{
            System.out.println(one);
            return one.contains("aa");
        }).count();
        System.out.println(count);

        list.stream().map(string -> string.toUpperCase(Locale.ROOT)).collect(Collectors.toList());

        Runnable noArguments = () -> System.out.println("aaa");
        Thread th = new Thread(noArguments);
        th.start();

        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8");
            }
        }).start();
        new Thread( () -> System.out.println("In Java8, Lambda expression") ).start();

        BinaryOperator<Long> add = (x, y) -> x+y;


        Predicate<Integer> atLest = x -> x>5;
        System.out.println(atLest);

    }
}
