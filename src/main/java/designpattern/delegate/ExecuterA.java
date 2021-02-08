package designpattern.delegate;

public class ExecuterA implements IExecuter {
    @Override
    public void execter(String msg) {
        System.out.println(" executer "+msg);
    }
}
