package decorator;

import decorator.abst.Espresso;
import decorator.abst.IBeverage;
import decorator.concrete.Base;
import decorator.concrete.Milk;
import java.util.Scanner;

/**
 * <h2><b>Role : </b>Component</h2>
 * <p>
 * 컴포넌트의 역할은 실질적인 컴포넌트들을 다루는, 컨트롤하는 역할을 합니다.
 * </p>
 * <h2><b>Coffee Hoses Menu</b></h2>
 * </br>
 * <ul>
 * <li>에스프레소 : 커피의 기본</li>
 * <li>아메리카노 : 에스프레소 + 물</li>
 * <li>카페라떼 : 에스프레소 + 스팀밀크</li>
 * <li>헤이즐넛 : 아메리카노 + 헤이즐넛 시럽</li>
 * <li>카페모카 : 에스프레소 + 스팀밀크 + 초콜릿</li>
 * <li>캬라멜 마끼아또 : 카페라떼 + 캬라멜 시럽</li>
 * </ul>
 *
 * @author garam park
 */
public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        //1. 기본 기능 객체를 만들어, 인페 추상체 변수로 받는다.
        IBeverage iBeverage = new Base();

        // 반복문 내 if를 종료시킬 flag변수
        boolean isDone = false;
        while (!isDone) {
            // 1.5 - 반복되는 input전 안내메세지에서, [기본 기능 구현체의 기본기능]만 호출하는 것 같지만,
            //     - 변수에 구체 데코레이터가 재할당되어 업데이트된 [데코 적용 구현체].추가기능()이 와서 호출된다.
            System.out.printf("음료 현재 가격: %s%n", iBeverage.getTotalPrice());
            System.out.println("선택(1:샷 추가/2:우유 추가): ");
            //2. 기본 기능 추상체변수를 주입하여, 원하는 추가기능을 갖춘 구체 데코레이터의 객체를 만들어 재할당한다
            switch (Integer.parseInt(scanner.nextLine())) {
                // 0입력시 early return처럼  flag로 종료
                case 0:
                    isDone = true;
                    break; // switch문을 종료한다는 것임. while종료 아님.
                case 1:
                    //3. 기본 기능 추상체변수를 주입받는 구체 데코레이터를 생성해서 추상체변수를 덮어쓴다.
                    // - 구체 데코레이터 자체가 [기본 기능 인페 추상체의 구현 추상클래스의 구현체라서, 인페 추상체의 2단계 하위]라 덮어써서 할당된다.
                    // - 구체 데코레이터는 객체생성만으로도, 내부에서 추가된 기능을 호출한 뒤,
                    iBeverage = new Espresso(iBeverage);
                    break;
                case 2:
                    iBeverage = new Milk(iBeverage);
                    break;
            }
        }
        //4. 구체 데코레이터가 생성될 때마다 같은 추상체를 쓰는 변수가 생성자 주입된다면,
        //   -> 같은 (추상체=) 기능을 할 수 있는 직전 변수 super를 품은 체, 새 객체가 생성되며
        //   -> 직전객체인 super.직전기능() + 현재 객체는 추가기능 제공 형태로 누적된다.
        //   -> 가장 마지막인 놈이 직전 객체(super)의 기능에 + 추가기능 제공해서 끝나게 된다.

        //5. 기본기능 구현체 -> 구체 데코레이터 구현체 중 1개로 재할당된 변수를 이용해서, 추가 기능 구현된 기능을 호출한다.
        System.out.printf("음료 가격: %d%n", iBeverage.getTotalPrice());

        scanner.close();
    }
}
