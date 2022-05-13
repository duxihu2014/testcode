package arithmetic.testsap.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//一、什么是JAVA的反射
//https://www.cnblogs.com/dongguacai/articles/6535417.html
public class TestReflection {

  public static void main(String[] args) throws Exception {
    Class<?> cls = Class.forName("arithmetic.testsap.reflection.DemoTest");
    System.out.println(cls.getName());
    System.out.println(cls.newInstance());

    System.out.println("-------------------------------------------");
    //获取该类实现的接口
    Class<?>[] interfaces = cls.getInterfaces();
    System.out.println(interfaces[0].getName());

    System.out.println("-------------------------------------------");
    //获取有参构造函数
    Constructor<?> con =  cls.getConstructor(String.class,int.class);
    DemoTest demoTest = (DemoTest) con.newInstance("xiaoming",12);
    System.out.println(demoTest.getAge());

    //获取类的成员变量
    Field field1 = cls.getField("age");
    System.out.println(field1);
    System.out.println(field1.get(demoTest));

    System.out.println("-------------------------------------------");

    Method m = cls.getMethod("sayHello", String.class);
    m.invoke(demoTest,"hangzhou");


  }

}
