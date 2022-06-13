package factorymethod.concrete;

import factorymethod.framework.Item;

public class HpPotion implements Item {
    @Override
    public void use() {
        System.out.println("체력회복물약 사용!");
    }
}
