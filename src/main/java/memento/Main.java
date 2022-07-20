package memento;

import java.util.Stack;
import memento.abc.Memento;
import memento.abc.Originator;

public class Main {
    public static void main(String[] args) {

        final Stack<Memento> mementos = new Stack<>();

        final Originator originator = new Originator();

        originator.setState("state 1");
        // 메멘토는 setter가 없지만, 생성자를 통해서 상태를 바꾼 memento가
        // 외부에서 생성될 수 있다.
//        Memento memento = originator.createMemento();
//        memento = new Memento("state 11111");
        mementos.push(originator.createMemento());

        originator.setState("state 2");
        mementos.push(originator.createMemento());

        originator.setState("state 3");
        mementos.push(originator.createMemento());

        originator.setState("state final");
        mementos.push(originator.createMemento());

        // careTaker(stakc-mementos)에 저장된 상태를 순서대로 불러와
        // -> originator의 상태를 복원시킨다.
        // --> setter로 상태 업데이트가 아니라, 상태객체를 받아들여 복원하는 것
        originator.restoreMemento(mementos.pop());
        System.out.println(originator.getState()); // final
        originator.restoreMemento(mementos.pop());
        System.out.println(originator.getState()); // state 3
        originator.restoreMemento(mementos.pop());
        System.out.println(originator.getState()); // state 2
        originator.restoreMemento(mementos.pop());
        System.out.println(originator.getState()); // state 1

    }
}
