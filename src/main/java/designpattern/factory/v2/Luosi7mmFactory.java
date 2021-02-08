package designpattern.factory.v2;

import designpattern.factory.v1.Luosi7mm;
import designpattern.factory.v1.LuosiProduct;

public class Luosi7mmFactory implements LuosiFactory {
    @Override
    public LuosiProduct createLuosiProduct() {
        return new Luosi7mm();
    }
}
