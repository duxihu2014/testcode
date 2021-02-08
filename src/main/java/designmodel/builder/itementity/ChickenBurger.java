package designmodel.builder.itementity;

import designmodel.builder.item.Burger;

//鸡-汉堡包
public class ChickenBurger extends Burger {
 
   @Override
   public float price() {
      return 50.5f;
   }
 
   @Override
   public String name() {
      return "Chicken Burger";
   }

}