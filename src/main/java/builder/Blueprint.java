package builder;

public abstract class Blueprint {

    abstract public void setCpu();

    abstract public void setRam();

    abstract public void setStorage();

    abstract Computer getComputer();
}
