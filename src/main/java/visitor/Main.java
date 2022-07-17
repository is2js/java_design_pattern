package visitor;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        final ArrayList<VisitableA> visitableAs = new ArrayList<VisitableA>();
        visitableAs.add(new VisitableA(1));
        visitableAs.add(new VisitableA(2));
        visitableAs.add(new VisitableA(3));
        visitableAs.add(new VisitableA(4));
        visitableAs.add(new VisitableA(5));


        final Visitor visitor = new VisitorA();

        for (final VisitableA visitableA : visitableAs) {
            visitableA.accept(visitor);
        }

        System.out.println("((VisitorA) visitor).getSum() = " + ((VisitorA) visitor).getSum());
    }
}
