package sifangtestcode;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Test2 {



    public static void test(){
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("3");
        list1.add("5");


//        List<String> list2 = new ArrayList<String>();
//        list2.add("3");
//        list2.add("5");
//        list2.add("7");
        List<String> list2 =null;


        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集---");
        intersection.parallelStream().forEach(System.out::println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集(list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集---");
        listAll.parallelStream().forEachOrdered(System.out::println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集---");
        listAllDistinct.parallelStream().forEachOrdered(System.out::println);
    }


    public static void main(String[] args) {
        Test2.test();
    }

}
