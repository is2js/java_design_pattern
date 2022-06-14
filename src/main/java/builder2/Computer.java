package builder2;

public class Computer {

    private String cpu;
    private String ram;
    private String storage;

    public Computer(final String cpu,
                    final String ram,
                    final String storage) {
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }

    public void setCpu(final String cpu) {
        this.cpu = cpu;
    }

    public void setRam(final String ram) {
        this.ram = ram;
    }

    public void setStorage(final String storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Computer{" +
            "cpu='" + cpu + '\'' +
            ", ram='" + ram + '\'' +
            ", storage='" + storage + '\'' +
            '}';
    }
}
