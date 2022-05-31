package decorator.concrete;

import decorator.abst.AbstAdding;
import decorator.abst.IBeverage;

public class Milk extends AbstAdding {
    public Milk(final IBeverage iBeverage) {
        super(iBeverage);
    }

    @Override
    public int getTotalPrice() {
        // ConcreteDecorator로서, 추상클래스(Deco)가 물려준 메서드에 추가기능을 더한다.
        // -> 기본 기능 + 50;
        return super.getTotalPrice() + 50;

    }
}
