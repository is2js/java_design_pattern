package memento.abc;

public class Memento {
    private final String state;

    protected Memento(final String state) {
        this.state = state;
    }

    protected String getState() {
        return state;
    }
}
