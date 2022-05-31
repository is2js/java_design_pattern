package decorator.abst;

public class Espresso extends AbstAdding {
    //1. 
    protected static int espressoCount = 0;

    public Espresso(final IBeverage iBeverage) {
        super(iBeverage);
    }

    @Override
    public int getTotalPrice() {
        //추가 기능을 구현하되, 전역변수인 static protected count변수를 만든 전역변수를 활용한다.
        return super.getTotalPrice() + getAddPrice();//2. 추가기능으로 추가 가격을 더한다.
    }

    private int getAddPrice() {
        espressoCount++;
        int addPrice = 100;
        //3. 추가기능 내부에서는, 전역변수 2잔 이상일 때부터는 할인된 가격을 더한다.
        if (espressoCount > 1) {
            addPrice = 70;
        }
        return addPrice;
    }
}
