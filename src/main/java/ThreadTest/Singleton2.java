package ThreadTest;

public class Singleton2 {

    private static volatile Singleton2 singleton2;
    private Singleton2(){

    }

    public static Singleton2 getSingleton2(){
        if (singleton2 == null){
            synchronized (Singleton2.class){
                if (singleton2 == null){
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}

//另外，需要注意 uniqueInstance 采用 volatile 关键字修饰也是很有必要。
//        uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分
//        为三步执行：
//        1. 为 uniqueInstance 分配内存空间
//        2. 初始化 uniqueInstance
//        3. 将 uniqueInstance 指向分配的内存地址
//        但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出先问题，但是在
//        多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用
//        getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被
//        初始化。
//        使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行
