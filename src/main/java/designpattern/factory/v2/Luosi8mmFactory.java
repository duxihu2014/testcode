package designpattern.factory.v2;

import designpattern.factory.v1.Luosi8mm;
import designpattern.factory.v1.LuosiProduct;

public class Luosi8mmFactory implements LuosiFactory {
    @Override
    public LuosiProduct createLuosiProduct() {
        return new Luosi8mm();
    }
}
