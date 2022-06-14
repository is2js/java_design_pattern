package builder;

public class LgGramBlueprint extends Blueprint {

    private Computer computer;

    public LgGramBlueprint() {
        this.computer = new Computer("default", "default", "default");
    }

    @Override
    public void setCpu() {
        computer.setCpu("i7");
    }

    @Override
    public void setRam() {
        computer.setCpu("8g");
    }

    @Override
    public void setStorage() {
        computer.setCpu("256g ssd");
    }

    @Override
    Computer getComputer() {
        return computer;
    }
}
