package command;

public class StringPrintCommand implements Command {

    private final String command;

    public StringPrintCommand(final String command) {
        this.command = command;
    }

    @Override
    public void execute() {
        System.out.printf("%s가 실행되었습니다%n",command);
    }

    @Override
    public int compareTo(final Command o) {
        final StringPrintCommand other = (StringPrintCommand) o;
//        return Integer.compare(command.length(), other.command.length());
        return Integer.compare(other.command.length(), command.length());
    }
}
