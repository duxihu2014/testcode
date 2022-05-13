

public class Test {


    public static void main(String[] args) {


        //Integer [] dataList = {7,1,3,7,5,9,4};   8
        //Integer [] dataList = {1,7,1,3,7,5,9,4};   8
        //Integer [] dataList = {1,7,1,3,7,5,6,4};  6
        //Integer [] dataList = {7,5,1};
        Integer [] dataList = {3,7,2,10};
        Integer headdata = dataList[0];
        Integer taildata = dataList[1];
        Integer result = 0;
        for (int i=0;i<dataList.length-1;i++) {

            if(headdata<dataList[i]){
                continue;
            }
            headdata = dataList[i];
            for (int j=1;j<dataList.length;j++) {
                if(taildata>dataList[j]){
                    continue;
                }
                taildata = dataList[j];
                if (dataList[i] < dataList[j]) {
                    result = dataList[j] -  dataList[i];
                }
            }
        }

        System.out.println(result);
    }
}
