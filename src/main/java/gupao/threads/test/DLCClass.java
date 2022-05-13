package gupao.threads.test;

public class DLCClass {

  //Double lock Check
  //不完整对象

  public static volatile DLCClass dlcClass;

  private DLCClass(){}

  public static DLCClass getInstance(){

    if(dlcClass==null){
      synchronized (dlcClass){
        if (dlcClass == null){
          //三个指令  ---》重排序
          dlcClass = new DLCClass();
        }
      }
    }
    return  dlcClass;
  }

}
