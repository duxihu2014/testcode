package designpattern.delegate;

public class Boss {
    public static void main(String[] args) {
        Leader leader = new Leader();
        leader.execter("login");
        leader.execter("jiami");
    }

}
