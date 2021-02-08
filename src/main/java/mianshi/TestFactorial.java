package mianshi;

public class TestFactorial {

    public static void main(String[] args) {
        TestFactorial test = new TestFactorial();
       int a =  test.factorial(5);
        System.out.println(a);
    }



    public int factorial(int n){
        if(n<2){
            return 1;
        }
        return n*factorial(n-1);
    }

}
