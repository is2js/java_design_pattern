package abstractfactory.gt;

import abstractfactory.abst.BikeFactory;
import abstractfactory.abst.Body;
import abstractfactory.abst.Wheel;

public class GtFactory implements BikeFactory {
    @Override
    public Body createBody() {
        return new GtBody();
    }

    @Override
    public Wheel createWheel() {
        return new GtWheel();
    }
}
