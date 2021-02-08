package designmodel.builder.item;

import designmodel.builder.pack.Packing;
import designmodel.builder.pack.Wrapper;

//汉堡包
public abstract class Burger implements Item {
 
   @Override
   public Packing packing() {
      return new Wrapper();
   }
 
   @Override
   public abstract float price();
}