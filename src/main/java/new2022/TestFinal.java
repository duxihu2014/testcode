package new2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFinal {


    //当使用final关键字声明类成员变量或局部变量后，其值不能被再次修改；也经常和static关键字一起，作为类常量使用。很多时候会容易把static和final关键字混淆，static作用于成员变量用来表示只保存一份副本，而final的作用是用来保证变量不可变。
    //如果final变量是引用，这意味着该变量不能重新绑定到引用另一个对象，但是可以更改该引用变量指向的对象的内部状态，即可以从final数组或final集合中添加或删除元素。
    static final List list1 = new ArrayList(Arrays.asList("apple"));


    public static void main(String[] args) {


    }

//    public static void update (){
//        list1 = null;
//    }

}
