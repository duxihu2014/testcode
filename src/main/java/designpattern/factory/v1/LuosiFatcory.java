package designpattern.factory.v1;

public class LuosiFatcory {
    public static LuosiProduct createLuosiProduct(String productType){
        LuosiProduct luosiProduct = null;
        if(productType.equals("7mm")){
            luosiProduct = new Luosi7mm();
        }else if(productType.equals("8mm")){
            luosiProduct = new Luosi8mm();
        }else if(productType.equals("6mm")){
            luosiProduct = new Luosi6mm();
        }
        return luosiProduct;
    }
}
