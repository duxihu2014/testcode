package designpattern.adapter;

public class Phone {
    public void getPower(Voltage5V voltage5V){
        if(voltage5V.output5V()==220){
            System.out.println("do not use");
        }else if(voltage5V.output5V()!=220) {
            System.out.println("can use");
        }
    }
}
