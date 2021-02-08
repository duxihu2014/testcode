package mianshi;

public class GetOffer {
    public static void main(String[] args) {
      int num =123;
        int reNum=reverse(num);
        System.out.println(reNum);
    }

    private static int reverse(int num) {
        if(num==0){
            return 0;
        }
        if(num>0){
            return reverseNum(num);
        }else{
            return 0-reverseNum(Math.abs(num));
        }
    }

    private static int reverseNum(int abs) {
        int result=0;
        while(true){
            int n=abs%10;//取出最后一个数
            result=result*10+n;
            System.out.println("result---->"+result);
             abs = abs/10;//降位
            System.out.println("abs---->"+abs);
             if(abs==0){
                 return result;
             }
        }
    }
}