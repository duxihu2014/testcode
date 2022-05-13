package lambda;

import java.util.Arrays;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        //Test1.test01();
        Test1.test();
    }


    
    public static void test01(){
        String [] test = {"Lambdas", "Default Method", "Stream API",
                "Date and Time API"};
        List features2 = Arrays.asList(test);
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API",
                "Date and Time API");
        features.forEach(n -> System.out.println(n));
        features.forEach(System.out::println);
    }
    public static void test(){
//        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//        double total = 0;
//        for (Integer cost : costBeforeTax) {
//            double price = cost + .12*cost;
//            System.out.println(price);
//            total = total + price;
//        }
//        System.out.println("Total : " + total);

// 新方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }



}
