package test.test;

public class Test {

    public static void main(String[] args) {
        DelayedQueue<String> s = new MyQueue<String>(4);
        s.enqueue("first element1");
        s.enqueue("first element2");
        s.dequeue();

        s.enqueue("first element3");
        s.enqueue("first element4");
        s.dequeue();

//        s.enqueue("first element5");
//        s.enqueue("first element6");



    }
}
