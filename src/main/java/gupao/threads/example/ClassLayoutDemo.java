package gupao.threads.example;

import org.openjdk.jol.info.ClassLayout;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/17-20:46
 */
public class ClassLayoutDemo {

    public static void main(String[] args) {
        ClassLayoutDemo classLayoutDemo=new ClassLayoutDemo();
        synchronized (classLayoutDemo){
            System.out.println("locking");
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }

    }
}
