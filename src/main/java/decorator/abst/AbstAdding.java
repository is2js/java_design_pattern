package decorator.abst;

public abstract class AbstAdding implements IBeverage {

    private final IBeverage iBeverage;

    protected AbstAdding(final IBeverage iBeverage) {
        this.iBeverage = iBeverage;
    }

    @Override
    public int getTotalPrice() {
        // Decorator에서는 기본 기능만 사용해서 물려준다.
        return iBeverage.getTotalPrice();
    }

    public IBeverage getIBeverage() {
        return iBeverage;
    }
}
