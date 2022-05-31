package adapter;

public class Main {
    public static void main(String[] args) {

        final Adapter adapter = new AdapterImpl(new Math());

        System.out.println("adapter.twiceOf(100F) = " + adapter.twiceOf(100F));
        System.out.println("adapter.halfOf(88F) = " + adapter.halfOf(88F));
    }
}
