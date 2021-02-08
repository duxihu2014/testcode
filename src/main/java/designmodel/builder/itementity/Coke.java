package designmodel.builder.itementity;

import designmodel.builder.item.ColdDrink;

//可口可乐
public class Coke extends ColdDrink {
 
   @Override
   public float price() {
      return 30.0f;
   }
 
   @Override
   public String name() {
      return "Coke";
   }
}