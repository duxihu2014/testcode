package designpattern.delegate;

public class ExecuterB implements IExecuter {
    @Override
    public void execter(String msg) {
        System.out.println(" executer "+msg);
    }
}
