package designmodel.builder.itementity;

import designmodel.builder.item.Burger;

//蔬菜-汉堡包
public class VegBurger extends Burger {
 
   @Override
   public float price() {
      return 25.0f;
   }
 
   @Override
   public String name() {
      return "Veg Burger";
   }
}