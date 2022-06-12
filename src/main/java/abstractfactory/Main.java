package abstractfactory;

import abstractfactory.abst.BikeFactory;
import abstractfactory.abst.Body;
import abstractfactory.abst.Wheel;
import abstractfactory.gt.GtFactory;

public class Main {
    public static void main(String[] args) {
//        final BikeFactory factory = new SamFactory();
        final BikeFactory factory = new GtFactory();

        final Body body = factory.createBody();
        final Wheel wheel = factory.createWheel();

        System.out.println("body.getClass() = " + body.getClass());
        System.out.println("wheel.getClass() = " + wheel.getClass());

    }
}
