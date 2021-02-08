package designpattern.factory.v2;

import designpattern.factory.v1.Luosi6mm;
import designpattern.factory.v1.LuosiProduct;

public class Luosi6mmFactory implements LuosiFactory {
    @Override
    public LuosiProduct createLuosiProduct() {
        return new Luosi6mm();
    }
}
