package javajuc.day2.priorityqueue;

import java.util.Comparator;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 * 比较存款多少的类
 */
public class HumanComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o2.getMoney() - o1.getMoney();
    }
}
