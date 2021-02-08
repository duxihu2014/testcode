package designpattern.adapter;

public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Voltage5V voltage5V = new VoltageAdapter(new Voltage220V());
        phone.getPower(voltage5V);
    }

}
