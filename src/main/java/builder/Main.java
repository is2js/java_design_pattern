package builder;

public class Main {
    public static void main(String[] args) {
        final ComputerFactory factory = new ComputerFactory();

        factory.setBluePrint(new LgGramBlueprint());
//        factory.setBluePrint(new MacBlueprint());

        factory.make();
        final Computer computer = factory.getComputer();

        System.out.println("computer = " + computer);
    }
}
