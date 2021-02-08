package innercls.partcls;

public class People{
    public People() {
         
    }
}
 
class Man{
    public Man(){
         
    }
     
    public People getWoman(){
        class Woman extends People{   //局部内部类
             int age =0;
        }
        return new Woman();
    }

  public static void main(String[] args) {

  }
}