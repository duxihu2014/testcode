package test.test;

public interface DelayedQueue<E> {

    boolean enqueue(E entity);
    E dequeue();

}
