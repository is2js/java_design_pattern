package builder3;

public class Computer {

    private final String cpu;
    private final String ram;
    private final String storage;

    public Computer(final Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    public static class Builder {

        private String cpu = "default";
        private String ram = "default";
        private String storage = "default";

        public Builder() {
        }

        public Builder cpu(final String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(final String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(final String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
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
