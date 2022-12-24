package lambda;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class New2022 {
    public static void main(String[] args) {
//        // TODO 将数字字符串转换为整数类型
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted.getClass()); //class java.lang.Integer
//
//
//        Converter<String, Integer> converter2 = Integer::valueOf;
//        Integer converted2 = converter2.convert("123");
//        System.out.println(converted2.getClass());   //class java.lang.Integer


        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");

//        stringList.stream().sorted().filter(s ->s.startsWith("a")).forEach(System.out::println);

//        stringList
//                .stream()
//                .map(String::toUpperCase)
//                .sorted((a, b) -> a.compareTo(b))
//                .forEach(System.out::println);// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "BBB1", "AAA2", "AAA1"

        boolean anyStartsWithA =
                stringList.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        anyStartsWithA =
                stringList.stream().allMatch(s -> s.startsWith("a"));

        System.out.println(anyStartsWithA);

        boolean noneStartsWithZ =
                stringList.stream().noneMatch(s -> s.startsWith("Z"));
        System.out.println(noneStartsWithZ);


        long startWithB =
                stringList.stream().filter(s -> s.startsWith("b"))
                        .count();
        System.out.println(startWithB);


//        Reduce(规约)
//        这是一个 最终操作 ，允许通过指定的函数来将stream中的多个元素规约为一个元素，规约后的结果是通过Optional 接口表示的：
        Optional<String> reduced =
                stringList.stream().sorted().reduce((s, s2) -> s + "#" + s2);

        reduced.ifPresent(System.out::println);

//        Integer sum = integers.reduce(0, (a, b) -> a+b);


        String concat = Stream.of("A", "B", "C").reduce("",String::concat);

        System.out.println(concat);

        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE,Double::min);
        System.out.println(minValue);

        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);

        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();

        System.out.println(sumValue);

        sumValue = Stream.of(1, 2, 3, 4).mapToInt(Integer::intValue).sum();



        List<Integer> integerList = new ArrayList<>();
        integerList.add(15);
        integerList.add(32);
        integerList.add(5);
        integerList.add(232);
        integerList.add(56);
        List<Integer> after = integerList.stream()
                .filter(i->i>50)
                .collect(Collectors.toList());
        System.out.println(after);//232，56



        List<Integer> myTestList = new ArrayList<>();
        myTestList.add(39);
        myTestList.add(78);
        myTestList.add(10);
        myTestList.add(22);
        myTestList.add(56);
        List<Integer> sortList = myTestList.stream()
                .sorted(Integer::compareTo).collect(Collectors.toList());

        List<Integer> distinctList = myTestList.stream().distinct().collect(Collectors.toList());

        System.out.println("distinctList:"+distinctList);
        System.out.println("sortList："+sortList);






        List<String> words = new ArrayList<String>();
        words.add("your");
        words.add("name");




        Stream<Stream<Character>> result = words.stream().map(s -> characterStream(s));

        System.out.println(result.collect(Collectors.toList()));   //  TODO:
//[['y', 'o', 'u', 'r'], ['n', 'a', 'm', 'e']]
        Stream<Character> letters = words.stream().flatMap(w -> characterStream(w));
        System.out.println(letters.collect(Collectors.toList()));

//        将一个Room对象集合按照高度分组。
//
//        List<Room> roomList = Lists.newArrayList(
//                new Room(11,23,56),
//                new Room(11,84,48),
//                new Room(22,46,112),
//                new Room(22,75,62),
//                new Room(22,56,75),
//                new Room(33,92,224));
//
//        Map<Integer,List<Room>> groupMap = roomList.stream().collect(Collectors.groupingBy(Room::getHigh));
//        System.out.println("groupMap："+groupMap);


    }

    public static Stream<Character> characterStream(String s){
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray())
            result.add(c);
        return result.stream();
    }
}
