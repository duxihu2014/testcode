package new2022;

import java.util.HashMap;
import java.util.Scanner;

public class TTT {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String readStr = in.nextLine();
        HashMap<String,Integer> datamap = new HashMap<>();
        for (int i = readStr.length()-1; i >=0 ; i--) {
            String chartmp = String.valueOf(readStr.charAt(i));
            if(datamap.get(chartmp)==null){
                System.out.print(chartmp);
                datamap.put(chartmp,1);
            }
        }
    }
}
