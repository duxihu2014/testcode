package designmodel.builder.item;

import designmodel.builder.pack.Packing;

public interface Item {
   public String name();
   public Packing packing();
   public float price();    
}