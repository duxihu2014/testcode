package lambda;




import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringUtil2 {

    public static void main(String[] args) {
//        方法一:
//// 字符串转数组 java.lang.String
        String str = "0,1,2,3,4,5";
        String[] arr = str.split(","); // 用,分割
        System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4, 5]


//
//        方法二: 使用StringUtils的join方法


//数组转字符串 org.apache.commons.lang3.StringUtils
        String[] arr2 = {"1","2"};
        String str3 = StringUtils.join(arr2); // 数组转字符串,其实使用的也是遍历
        System.out.println(str3); // 012345
        String str4 = StringUtils.join(arr2, ","); // 数组转字符串(逗号分隔)(推荐)
        System.out.println(str4); // 0,1,2,3,4,5
//        方法三: 使用ArrayUtils的toString方法


// 数组转字符串 org.apache.commons.lang3.ArrayUtils
        String str2 = ArrayUtils.toString(arr, ","); // 数组转字符串(逗号分隔,首尾加大括号)
        System.out.println(str2); // {0,1,2,3,4,5}
    }

}
