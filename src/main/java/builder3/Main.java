package builder3;

import builder3.Computer.Builder;

public class Main {
    public static void main(String[] args) {
        // 기존 방식
//        Computer computer = ComputerBuilder
//            .start()
//            .setCpu("i7")
//            .setRam("16g")
//            .setStorage("256g ssd")
//            .build();

        // 객체 내부에 Builder static class를 넣고,
        // builder객체를 생성자호출로 받아온 상태가 되어
        // default값을 가진 객체 필드들을 하나씩 setter 초기화하는 방식
        final Computer computer = new Computer.Builder()
            .cpu("i7")
            .ram("16g")
            .storage("256ssd")
            .build();

        System.out.println(computer);

        final Computer computer1 = new Builder()
            .storage("156g ssd")
            .build();

        System.out.println(computer1);

    }
}
