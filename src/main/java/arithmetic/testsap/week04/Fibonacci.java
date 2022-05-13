package arithmetic.testsap.week04;


//Fibonacci数列

public class Fibonacci {

  int fib(int n){
    if(n<=0){
      return 0;
    }else if(n == 1){
      return 1;
    }else {
      return fib(n-1) +fib(n-2);
    }
  }

//  int fib2(int n,int[] memo){
//    if(n<=0){
//      return 0;
//    }else if(n == 1){
//      return 1;
//    }else if(memo[n] ==0){
//      memo[n] =  fib2(n-1) +fib2(n-2);
//    }
//    return memo[n];
//  }

  public static void main(String[] args) {
    Fibonacci solution11 = new Fibonacci();
    int n =6;

    System.out.println(solution11.fib(n));
    int[] memo = new int[n+1];
//    System.out.println(solution11.fib2(n,memo));
  }


}
