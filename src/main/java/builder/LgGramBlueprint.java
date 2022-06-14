package builder;

public class LgGramBlueprint extends Blueprint {

    private String cpu = "i7";
    private String ram = "16g";
    private String storage = "256g ssd";

    @Override
    Computer getComputer() {
        return new Computer(cpu, ram, storage);
    }
}
