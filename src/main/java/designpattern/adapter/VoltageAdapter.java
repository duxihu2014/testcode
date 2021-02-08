package designpattern.adapter;

public class VoltageAdapter implements Voltage5V {

    private Voltage220V voltage220V;
    public VoltageAdapter(Voltage220V voltage220V){
        this.voltage220V = voltage220V;
    }
    @Override
    public int output5V() {

        int distV = 0;
        if(null != voltage220V){
            int src  = voltage220V.output220V();
            distV = src/4;
        }
        return distV;
    }
}
