package arithmetic.testsap.javase;

public class TestSwitch {


  public void testString(String i){
    switch(i){
    case "5":
      System.out.println("5");
      break;
    case "6":
      System.out.println("6");
      break;
    case "7":
      System.out.println("7");
      break;
    default:
      System.out.println("default");
    }
  }


  public static void main(String[] args) {
    TestSwitch testSwitch = new TestSwitch();
    testSwitch.testString("5");
  }

}
