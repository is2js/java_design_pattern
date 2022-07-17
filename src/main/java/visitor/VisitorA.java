package visitor;

public class VisitorA implements Visitor {

    private int sum = 0;

    @Override
    public void visit(final Visitable visitable) {
        if (visitable instanceof VisitableA) {
            final int age = ((VisitableA) visitable).getAge();
            sum += age;
        }
        //...
    }

    public int getSum() {
        return sum;
    }
}
