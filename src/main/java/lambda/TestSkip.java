package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestSkip {

    public static void main(String[] args) {
        TestSkip.testSkip();
        TestSkip.testPeek();

        TestSkip.testReduce1();
        TestSkip.testReduce2();
        TestSkip.testReduce3();
    }

    static  void testSkip(){
        //    本文主要介绍通过List的Stream()方法，过滤获取指定元素，获取不到就取最后一个元素的方法。
        List<String> list = Arrays.asList("node", "java", "c++", "react", "javascript");

        // get last element from a list
        String result = list.get(list.size() - 1);

        System.out.println(result);

        // get last element from a stream, via skip
        String result2 = list.stream().skip(list.size() - 1).findFirst().orElse("no last element");

        System.out.println(result2);
    }

    static  void testPeek(){
        //此方法主要是为了支持调试，您希望在元素经过管道中的某一点时查看元素：
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }


    static void testReduce1(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);
        Optional<Integer> result = numList.stream().reduce((a, b) -> a + b );
        System.out.println(result.get());
    }

    static void testReduce2(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);
        Integer result = numList.stream().reduce(100, (a,b) -> a + b );
        System.out.println(result);

    }


    static void testReduce3(){
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        String result = numList.stream().reduce("__", (a, b) -> a += String.valueOf(b), (x, t) -> null);
        System.out.println(result);
    }
}
