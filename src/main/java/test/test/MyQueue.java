package test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MyQueue<E> implements DelayedQueue<E>{

    public MyQueue(int size){
        this.size = size;
    }
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    public int size;
    transient Object[] queue;
    int i = 0;
    @Override
    public boolean enqueue(E entity) {
        if (entity == null)
            throw new NullPointerException();
        if(queue == null){
            initElementsFromCollection(size);
        }


        if (i >= queue.length)
            grow(i + 1);
        i = i + 1;
        if (i == 0)
            queue[0] = entity;
        //交换数组元素

        return true;
    }

    @Override
    public E dequeue() {
        if(i<size){
            System.out.println("return value is null");
            return null;
        }
        else{
            System.out.println("return "+queue[size-1]);
            return (E)queue[size-1];
        }

    }

    private void initElementsFromCollection(int size) {
        this.queue = new Object[size-1];
    }

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
}
