package visitor;

public class VisitableA implements Visitable {

    private final int age;

    public VisitableA(final int age) {
        this.age = age;
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    public int getAge() {
        return age;
    }
}
