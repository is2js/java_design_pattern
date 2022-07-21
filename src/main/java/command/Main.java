package command;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<Command> commandPriorityQueue = new PriorityQueue<>();

        // 길이가 우선순위를 결정하므로, 서로 다른 길이로 커맨드 작성
        commandPriorityQueue.add(new StringPrintCommand("a"));
        commandPriorityQueue.add(new StringPrintCommand("ab"));
        commandPriorityQueue.add(new StringPrintCommand("abc"));
        commandPriorityQueue.add(new StringPrintCommand("abcd"));

        for (final Command command : commandPriorityQueue) {
            command.execute();
        }
    }
}
