package designmodel.builder.itementity;

import designmodel.builder.item.ColdDrink;

// 百事可乐
public class Pepsi extends ColdDrink {
 
   @Override
   public float price() {
      return 35.0f;
   }
 
   @Override
   public String name() {
      return "Pepsi";
   }
}