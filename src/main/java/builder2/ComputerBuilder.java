package builder2;

public class ComputerBuilder {
    private Computer computer;

    public ComputerBuilder() {
        this.computer = new Computer("deafult", "default", "default");
    }

    public static ComputerBuilder start() {
        return new ComputerBuilder();
    }

    public ComputerBuilder setCpu(final String cpu) {
        computer.setCpu(cpu);
        return this;
    }

    public ComputerBuilder setRam(final String ram) {
        computer.setRam(ram);
        return this;
    }

    public ComputerBuilder setStorage(final String storage) {
        computer.setStorage(storage);
        return this;
    }

    public Computer build() {
        return computer;
    }
}
