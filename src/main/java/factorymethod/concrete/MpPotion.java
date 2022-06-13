package factorymethod.concrete;

import factorymethod.framework.Item;

public class MpPotion implements Item {
    @Override
    public void use() {
        System.out.println("마력회복물약 사용!");
    }
}
