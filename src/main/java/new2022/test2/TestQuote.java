package new2022.test2;

public class TestQuote {

    public static void main(String[] args) {
        Car car = new Car();
        car.name = "aa";
        f(car);
        System.out.println(car.name);
    }

    private static void f(Car car) {
        car.name = null;
    }


}
class Car{
    public String name;
    public Car(){}
}