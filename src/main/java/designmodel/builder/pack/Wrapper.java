package designmodel.builder.pack;

import designmodel.builder.pack.Packing;

//纸盒/包装纸
public class Wrapper implements Packing {
 
   @Override
   public String pack() {
      return "Wrapper";
   }
}