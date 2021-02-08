package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test
{
    public static void main(String[] args) throws Exception
    {
      //参考链接：https://www.cnblogs.com/dongguacai/articles/6535417.html

        //获取类DemoTest的Class对象
        Class<?> c = Class.forName("com.test.reflection.DemoTest");
        //打印该Class对象对表示的类的名称
        System.out.println(c.getName());
        //获取该类的实例
        System.out.println(c.newInstance());

        System.out.println("-------------------------------------------");
        //获取该类实现的接口
        Class<?>[] interfaces = c.getInterfaces();
        System.out.println(interfaces[0].getName());

        System.out.println("-------------------------------------------");
        //获取有参构造函数
        Constructor<?> con = c.getConstructor(String.class,int.class);
        DemoTest dt = (DemoTest)con.newInstance("xiaoming",12);
        System.out.println(dt.getAge());

        System.out.println("-------------------------------------------");
        //获取类的成员变量
        Field f2 = c.getField("age");
        System.out.println("age--->"+f2);
        //获取指定对象上该字段表示的值
        System.out.println(f2.get(dt));

        System.out.println("-------------------------------------------");
        //获取指定的方法
        Method m = c.getMethod("sayHello", String.class);
        //反射调用方法，非常重要
        m.invoke(dt, "hangzhou");
    }
}