package designpattern.factory.v1;

public class Client {
    public static void main(String[] args) {
        LuosiProduct luosiProduct = LuosiFatcory.createLuosiProduct("7mm");
        luosiProduct.getLuosiProduct();

        luosiProduct = LuosiFatcory.createLuosiProduct("8mm");
        luosiProduct.getLuosiProduct();

        luosiProduct = LuosiFatcory.createLuosiProduct("6mm");
        luosiProduct.getLuosiProduct();
    }
}
