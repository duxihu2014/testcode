package arithmetic.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TestStack {

  public static void main(String[] args) {
    TestStack testStack = new TestStack();
//    testStack.test01();
//    testStack.test02();
    testStack.test03();
  }


  //Stack
  public void test01() {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    System.out.println(stack);
    System.out.println("4 的位置" +stack.search(2));

    stack.pop();
    stack.pop();

    Integer topElement = stack.peek();
    System.out.println(topElement);
    System.out.println("3 的位置" +stack.search(3));
  }

  //Queue
  public void test02() {
    Queue<String> queue = new LinkedList<>();
    queue.offer("one");
    queue.offer("two");
    queue.offer("theree");
    queue.offer("four");
    System.out.println(queue);

    String polledElement = queue.poll();
    System.out.println(polledElement);
    System.out.println(queue);

    String peekedElement = queue.peek();
    System.out.println(peekedElement);
    System.out.println(queue);

    while (queue.size()>0){
      System.out.println(queue.poll());
    }
  }

  //Deque
  public void test03() {
    Deque<String> deque = new LinkedList<>();
    deque.push("a");
    deque.push("b");
    deque.push("c");
    System.out.println(deque);

    String str = deque.peek();
    System.out.println(str);
    System.out.println(deque);

    while (deque.size()>0){
      System.out.println(deque.pop());
    }

    System.out.println(deque);

  }



}
