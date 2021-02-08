package javajuc.day2.arrayblockingqueue;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> c = new CopyOnWriteArrayList<>();
        c.add("yuandengta");
        c.remove("");

    }
}
