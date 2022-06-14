package builder2;

public class Main {
    public static void main(String[] args) {
        Computer computer = ComputerBuilder // 처음에는 정적으로 start를 호출
            .start() // 여기서 비로소 new ComputerBulider() 객체가 최초 생성
            .setCpu("i7")
            .setRam("16g")
            .setStorage("256g ssd")
            .build();

        System.out.println(computer.toString());
    }
}
