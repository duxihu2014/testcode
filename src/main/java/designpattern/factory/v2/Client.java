package designpattern.factory.v2;

public class Client {
    public static void main(String[] args) {
        LuosiFactory luosiProduct = new Luosi6mmFactory();
        luosiProduct.createLuosiProduct().getLuosiProduct();
        luosiProduct = new Luosi7mmFactory();
        luosiProduct.createLuosiProduct().getLuosiProduct();
        luosiProduct = new Luosi8mmFactory();
        luosiProduct.createLuosiProduct().getLuosiProduct();

    }
}
