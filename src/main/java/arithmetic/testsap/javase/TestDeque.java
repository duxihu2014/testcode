package arithmetic.testsap.javase;

import java.util.Deque;
import java.util.LinkedList;



public class TestDeque {

  public static void main(String[] args) {
    Deque<String> queue = new LinkedList<String>();
    queue.offer("data1");    //队列尾部加入元素
    queue.offer("data2");
    queue.offer("data3");
    System.out.println(queue.poll());    //取得队首元素，并从队列中删去

    Deque<String> stack = new LinkedList<String>();
    stack.push("element1");    //向栈顶压入元素
    stack.push("element2");
    stack.push("element3");
    System.out.println(stack.pop());    //取得栈顶元素，并从栈顶删去
    System.out.println();
  }

}
