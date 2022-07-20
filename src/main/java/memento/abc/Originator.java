package memento.abc;

public class Originator {

    private String state;

    //상태객체 생성기능
    public Memento createMemento() {
        return new Memento(state);
    }

    //상태객체로부터 복구기능 from 외부에 저장된 이전상태
    public void restoreMemento(Memento memento){
        this.state = memento.getState();
    }

    //현재 상태만 제공기능
    public String getState(){
        return state;
    }

    //현재 상태만 받아 저장하는 기능
    public void setState(final String state) {
        this.state = state;
    }
}
