package designpattern.adapter;

public class Voltage220V {

    public int output220V(){
        int src =220;
        System.out.println("电压"+220);
        return src;
    }

}
