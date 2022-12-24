package juc;

public class TestCHM {
    public static void main(String[] args) {
        int n = 16;
        System.out.println(n - (n >>> 1));;
        System.out.println(n - (n >>> 2));;

        int n1 = 2;
        System.out.println(n1<<1);;
        System.out.println(n1<<2);;


    }

//    static final int spread(int h) {
//        return (h ^ (h >>> 16)) & HASH_BITS;
//    }
}
