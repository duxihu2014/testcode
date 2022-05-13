package arithmetic.testsap;

import java.util.Stack;

import javajuc.day2.arrayblockingqueue.CopyOnWriteArrayListDemo;

////155. 最小栈
//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
//  push(x) —— 将元素 x 推入栈中。
//  pop() —— 删除栈顶的元素。
//  top() —— 获取栈顶元素。
//  getMin() —— 检索栈中的最小元素。
//
//  输入：
//  ["MinStack","push","push","push","getMin","pop","top","getMin"]
//  [[],[-2],[0],[-3],[],[],[],[]]
//
//  输出：
//  [null,null,null,null,-3,null,0,-2]
//
//  解释：
//  MinStack minStack = new MinStack();
//  minStack.push(-2);
//  minStack.push(0);
//  minStack.push(-3);
//  minStack.getMin();   --> 返回 -3.
//  minStack.pop();
//  minStack.top();      --> 返回 0.
//  minStack.getMin();   --> 返回 -2.


class MinStack {

     /** initialize your data structure here. */
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x < minStack.peek()) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
    }
    
    public void pop() {
        minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }


  public static void main(String[] args) {


  MinStack obj = new MinStack();
 obj.push(-2);
    obj.push(0);
    obj.push(-4);
    obj.push(-5);
 obj.pop();
 int param_3 = obj.top();
 int param_4 = obj.getMin();
    System.out.println();
  }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */