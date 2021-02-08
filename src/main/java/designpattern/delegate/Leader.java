package designpattern.delegate;

import java.util.HashMap;
import java.util.Map;

public class Leader implements IExecuter{

static Map<String,IExecuter> datamap = new HashMap<>();
static {
    datamap.put("jiami",new ExecuterA());
    datamap.put("login",new ExecuterB());
}

    @Override
    public void execter(String msg) {
        datamap.get(msg).execter(msg);
    }
}
