package builder;

public class Main {
    public static void main(String[] args) {
        final ComputerFactory factory = new ComputerFactory(new LgGramBlueprint());

        final Computer computer = factory.make();

        System.out.println("computer = " + computer);
    }
}
