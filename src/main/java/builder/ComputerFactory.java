package builder;

public class ComputerFactory {

    private Blueprint blueprint;

    public ComputerFactory(final Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    public Computer make() {
        return blueprint.getComputer();
    }
}
