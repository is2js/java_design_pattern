## 데코레이터 패턴

- 목적: 동적 책임 추가
- 실목적: 기본 기능에 `2개 이상의 추가 정책(추가 기능)을 내부에서 순서대로 적용`하고 싶을 때


- 사전적 의미:
	- 동적 : 실시간 변화하는
	- 책임 : 할 수 있는 일, 어떤 기능
- 기본 설계
	- Component <--- ConcreteComponent
	- Decorator <--- ConcreteDecorator
	  ![20220531154210](https://raw.githubusercontent.com/is2js/screenshots/main/20220531154210.png)
- 상세 설명
	- `Component`: 실질적 인스턴스 컨트롤하는 역할 -> 데코레이터와 component들을 컨트롤
	- `ConcreteComponent`: 실질적 인스턴스, 실질 책임 구현
	- `Decoreator`: Component와 ConcreteDecorator를 동일시 해주는 역할 -> **ConcreteComponent들을 Component로 생성자 주입 받아서 멤버변수(필드,
	  상태값)으로 갖고 있는 역할**
	- `ConcreteDecoreator`: 실질적인 장식 인스턴스 및 정이, 추가된 책임의 주체 -> Decorator가 지닌 추가된 책임을 구현하는 곳
	- **Component가 있고, 2개의 구현체가 있는데**
		1. 구현체1 ConcreteComponent: Component를 구현
		2. 구현체2 Decorator: 구현체이지만, realizing이 아닌 equaling(동일시)하는 기능 by Component를 생성자주입으로 가져서
			- 즉, Deocator로 Component들을 가져서 -> Component들을 감쌀 수 있게 된다.
		3. 구현체2의 구현체 ConcreteDecorator: 감싸는 책임을 추가함.

- 요구 사항
  ![20220531155453](https://raw.githubusercontent.com/is2js/screenshots/main/20220531155453.png)
	- 에스프레소: 커피의 기본
		- 아메리카노: 에스프레소 + 물
			- 헤이즐넛: 아메리카노 + 헤이즐넛 시럽
		- 카페라떼: 에스프레소 + 스팀밀크
			- 카페모카 : 카페라떼 + 초콜릿
			- 캬라멜 마끼아또 : 카페라떼 + 캬라멜 시럽
	- 우리의 책임은?
		- POS기를 만들어야한다.
		- 그 물건이 얼마냐?
- 예제
  ![20220531155718](https://raw.githubusercontent.com/is2js/screenshots/main/20220531155718.png)
	- `I Beverage`: 커피가격 산출 Component
		- `Base` : 실질적인 책임 구현 ConcreteComponent / 가격 측정, 추가될 책임(재료)에 의해 가격이 변동될 예정
		- `AbstAdding`: Decorator로서 Component(들)를 가짐
			- `Espresso`: ConcreteComponent1
			- `Milk`: ConcreteComponent2


### 생각 정리

- 데코레이터 구조
	1. 인터페이스 추상체는
		1. 명세가 된다.(구현(변수)와 기능을 분리하는 개념도 있음.)
		2. 원클래스를 여러 구현체 중 1개로 취급, 변수로 여러 구현체를 받게 해준다.
	2. 추상클래스 추상체는
		1. 구현체들에게 명세메서드를 구현해놓고 자식들에게 선택적으로 전달할 수 있다.
		2. 생성자주입한 필드가 있다면, 자식들에게 해당 필드를 주입받는 생성자를 물려주어, 안보이는 필드를 가지게 할 수 있다.
	3. 인터페이스 구현 추상클래스 추상체는
		1. 강제 명세를 받아서 -> 강제 명세를 구현한 것들을 자식들에게 선택적으로 전달할 수 있다.
		2. 인터페이스에 없던 필드를 만들거나 주입받아서 -> 자식들이 안보이는 필드를 주입받아 갖다 쓸 수 있다.
	3. **인터페이스를 구현하고, 인터페이스 추상체 변수를 생성자 주입받는 추상클래스(Decorator)**는
		1. 강제 명세를 받은 뒤 -> `강제 명세를 직접 구현한 것`들을 자식들에게 `선택적으로 전달`할 수 있다.
		2. 추상체를 필드로 받아서 -> 자식들이 `해당 구현체들`을  `안보이는 필드로  주입받아` 쓸 수 있다.
	4. 인페 구현 -> 추클 상속 -> 구체 데코레이터는 
		1. 인페 추상체 변수를 생성자 주입받아 작동하지만
		2. 인페 추상체의 자식에 자식이라, `기본기능 생성시 할당받는 인페 추상체 변수를 덮어써서 재할당` 가능해진다.
		![20220531224624](https://raw.githubusercontent.com/is2js/screenshots/main/20220531224624.png)

	5. `Base(ConcreteComponent)도 직전 [구체 데코레이터 객체 취급]`을 한다고 생각하고 like 상태패턴
		1. ConcreteComponent 객체인 `new Base()객체`를 -> 최초 `추상체 변수에 할당`한다.
		2. 특정 추가 기능을 제공하고 싶은 곳에서 -> `new 특정 ConcreteDecorator( Base객체 )`로 객체를 만들어, `최초 추상체 변수에 재할당`한다.
			- 내부에 직전 구체데코레이션객체인 Base객체를 품고 있다.
		3. 현재 구체 데코레이터가 반영된 `추상체 변수`로 `이름 같은 제공기능`을 호출한다.
			- 가장 바깥 객체는 추가기능을
			- 내부 직전 객체는 super라는 이름으로 자기 기능을
				- Base라면 기본 기능
				- 구체데코라면 자기 추가기능 + super(직전 객체).기본기능
		4. 다시 한번 구체 데코레이터 객체를 생성하면서 추상체변수에 재할당하면, 내부에 직전 구체 데코레이터 객체를 품고 있으면서, super로 호출되기 때문에 `재할당 한만큼 기능이 누적`된다.



### 기본 세팅 (ConcreteDecorato 생성 전)

1. ConcreteComponent(Class#method) 생성
	- `기본 기능(ConcreteComponent)`을 하는 `concrete`패키지에 `Base`#method을 구현한다.
	- 0원을 반환하는 메서드(아무것도 구매안한 상황은 0원)
	  ![20220531161124](https://raw.githubusercontent.com/is2js/screenshots/main/20220531161124.png)

2. Component(Interface) 생성
	- ConcreteComponent의 기본 기능으로부터 `abst`패키지에 `IBeverage(Component)` 인터페이스를 추출 구현한다.
	  ![20220531161605](https://raw.githubusercontent.com/is2js/screenshots/main/20220531161605.png)

3. Decorator(abstract class impl Component + 추상체Component 주입까지 구현체로 사용) 생성
	- ConcreComponent처럼 `Component를 구현하여 기본 기능 명세`를 가지는 `추상클래스(Decorator)`이자 `추상체 Component를 생성자 주입받아 필드로` 가지며 && ->
	  Component와 ConcreteDecorator(장식)를 동일시 역할을 할 `abst`패키지 속 `AbstAdding`을 만들어야한다.
		1. Component 인터페이스인 `IBeverage(Component)를 구현하는 추상클래스`를 만들고,하여, Component 명세 속 기본기능 명세를 구현한다.
			- **`이미 생성된 인터페이스 구현`을 쉽게하는 방법: `인터페이스 명칭`에서 `ctrl+.`을 하면 `implement interface`가 뜬다.**
				- **기본 class로 구현하니 `abstract`(or `final`)를 붙여준다.**
				  ![20220531191616](https://raw.githubusercontent.com/is2js/screenshots/main/20220531191616.png)
				  ![20220531191751](https://raw.githubusercontent.com/is2js/screenshots/main/20220531191751.png)
				  ![20220531192049](https://raw.githubusercontent.com/is2js/screenshots/main/20220531192049.png)

		2. 구현 중인 추상체임에도 불구하고, 추가로 **`2. 생성자 주입받아 -> 1. 필드(상태값)으로 가지고 있을 Component(IBeverage)`**의 필드를 만들고 -> 생성자 주입받도록 자동완성한다.
			![20220531164512](https://raw.githubusercontent.com/is2js/screenshots/main/20220531164512.png)
			![20220531164547](https://raw.githubusercontent.com/is2js/screenshots/main/20220531164547.png)
			![20220531164554](https://raw.githubusercontent.com/is2js/screenshots/main/20220531164554.png)

		3. **`추상체 주입`은 항상 외부에서 `기본 기능을 가진 구현체 ConcreteComponent`를 주입받았다고 가정**하고, 주입받은 Component를 이용해서 기본 기능 메서드를 완성한다.
			- **기본 기능의 추상체(인터페이스를)를 만들고**
				1. **`추상클래스로 구현하여, 추상메서드로 채울 수 있는 명세 강제`**
				2. **`주입받아, 기본 기능 구현체로 생각하고, 추상메서드를 기본기능 구현체로 채우기`**
					- 인터페이스와 달리 추상클래스(Decorator)는 필드+생성자를 상속 자식들(ConcreteDecorator)에게 물려줄 수 있다.
				![20220531193248](https://raw.githubusercontent.com/is2js/screenshots/main/20220531193248.png)
			- 의도한 것인지 모르겠는데, Component getter도 구현


### ConcreteDecorator를 생성해서 추가 정책(기능) 더해주기

![20220531210902](https://raw.githubusercontent.com/is2js/screenshots/main/20220531210902.png)

- ConcreteDecorator(Class extends Decorator) - 기본 기능(ConcreteComponent by 주입된 추상체)에 `추가적으로 적용할 정책들 만큼 생성`
	- Decorator가 기본 기능 구현해놓은 추상클래스인데, **이것을 상속함으로써,`super.기본기능메서드()`을 상속 받아서 `기능을 데코(추가)`**할 수 있다.
	- Decorator가 기본 기능 주체의 추상체를 주입받는 생성자를 가지는데, **이것을 상속함으로써 `super( 똑같은 Componet(추상체) )`로 외부에서 구현체(ConcreteComponent)를
	  주입받아 -> 필드(상태값)으로 가져 사용**할 수 있다.
		- **추상클래스는 `인페와 비교시, 메서드구현여부 자율성` + `보이지 않는 필드와 보이는 생성자 상속`을 / `일반class와 비교시, 일반 클래스와 비교시 명세만 정의 가능`의 특징이 있다.**

1. **`추상클래스 구현(상속)`도 IDE를 이용해 편하게 할 수 있다. Decorator를 상속한 ConcreteDecorator 클래스를 `기본 기능에 추가로 적용할 기능(정책) or 재료`명칭으로 만든다.**
	- 추상클래스명칭 -> `ctrl + .` -> `implement astract class`
	  ![20220531195432](https://raw.githubusercontent.com/is2js/screenshots/main/20220531195432.png)
	- 구체클래스들을 위한 package지정(있다면), 구체클래스명 지정
	  ![20220531212304](https://raw.githubusercontent.com/is2js/screenshots/main/20220531212304.png)

2. **`Decorator라는 추상클래스의 (안보이는)필드를 생성자주입받아 쓸 수 있는 상태로 구현`된다.**
	- **외부에서 구현체를 생성후 주입해주면, 안보이는 필드로 가지고 있다가 내부메서드구현시 갖다쓸 것이다.**
	![20220531212419](https://raw.githubusercontent.com/is2js/screenshots/main/20220531212419.png)

3. **`기본 기능(Base, ConcreteComponent)`에서부터 시작해 -> Component -> Decorator를 거쳐 -> `ConcreteDecorator`에서 추가 책임(기능)이 더해진다.**
	- `Decorator`에서도 `ConcreteDecorator`로 가기전에 책임을 추가할 수도 있지만, `여기서는 책임추가 없이 기본 기능만 구현`해서 물려줄 예정이다.
	```java
	@Override
    public int getTotalPrice() {
		// Decorator에서는 기본 기능만 사용해서 물려준다.
        return iBeverage.getTotalPrice();
    }

	```
	1. **추상클래스의 `상속`은 인터페이스와 달리 `메서드 구현이 자유`이므로 `직접 기본 기능 구현된 Decorator가 물려준 메서드를 자식으로서 super.를 붙여 가져`온 뒤 `추가 기능도 같이 구현`한다.**
		![20220531212744](https://raw.githubusercontent.com/is2js/screenshots/main/20220531212744.png)
		![20220531212756](https://raw.githubusercontent.com/is2js/screenshots/main/20220531212756.png)

	2. `Milk`라는 구체 데코레이터는 기본기능(기본 가격) + 50원의 추가기능을 더한다.
		![20220531213004](https://raw.githubusercontent.com/is2js/screenshots/main/20220531213004.png)
3. **또다른 추가 기능(정책)을 구하고 싶다면, `구체 데코레이터 Class를 추가`해서 구현한다.**
	1. 데코레이터(추상클래스) 상속
		- 생성자가 자동구현시 proected -> `바깥에서 쓸라면 public 생성자( 주입 )으로 바꿔야한다.`
	2. 데코레이터 기본 기능 메서드 구현(강제가 아니라 직접)
	3. 내부에서 super.기능기능()에 추가로 기능 구현

4. 좀더 복잡한 추가 정책(ConcreteDecorator) `Espresso`를 추가해보자.
	1. 구체 데코레이터 클래스를 만든다.
	2. 전역변수를 static protected로 만들어서, 마신 espresso를 카운팅한다
	3. 2잔이상부터는 할인된 가격인 100 -> 70원을 추가가격으로 더한다.
		```java
		public class Espresso extends AbstAdding {
			//1. 
			static protected int espressoCount = 0;

			public Espresso(final IBeverage iBeverage) {
				super(iBeverage);
			}

			@Override
			public int getTotalPrice() {
				//추가 기능을 구현하되, 전역변수인 static protected count변수를 만든 전역변수를 활용한다.
				return super.getTotalPrice() + getAddPrice();//2. 추가기능으로 추가 가격을 더한다.
			}

			private int getAddPrice() {
				espressoCount++;
				int addPrice = 100;
				//3. 추가기능 내부에서는, 전역변수 2잔 이상일 때부터는 할인된 가격을 더한다.
				if (espressoCount > 1) {
					addPrice = 70;
				}
				return addPrice;
			}
		}
		```
5. 메인에서 기본기능 구현체 -> 구체 데코레이터의 생성자 주입해서 내부에서 추가기능이 작동되도록 Main함수에서 실행해보기
	```java
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
	```


