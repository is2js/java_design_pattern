## 패턴
- 목적: 객체(class)에서 처리(method)를 분리해서 사용
- 실목적: `내부코드 수정이 없길 바라는 객체`class`Visitor`에 대해 method를, `외부에서 정보/기능을 제공해주는 Visitable객체들`을 사용할 수 있게 해주는 패턴

- 기본 설계
	![visitor패턴](https://raw.githubusercontent.com/is3js/screenshots/main/
	.png)
	1. clinet가 visitor와 Visitable을 사용하는데
	2. Visitor는 Visitable을 방문하고, Visitable은 visitor를 받아들인다.
	3. ConcreteVisitor는 visitable이 가진 멤버변수들을 이용해 원하는 처리
	4. ConcreteVisitable은 visitor를 받아들이는 정도로 구현한다.


### 적용
![20220718015900](https://raw.githubusercontent.com/is3js/screenshots/main/20220718015900.png)

1. Visitor와 Visitable 인터페이스 2개를 만든다.
	1. Visitable은 visitor를 메서드인자로 받아들여서, visitor의 visit(this)를 호출한다. 
		- **트랜잭션 관계이며, 결과물을 응답받을 을인 Visitable이 먼저, `내(을) 정보를 받아 결과물을 내어줄 갑 Visitor를 알고서 받아들여, 그 기능을 이용하도록` 순서가 이루어진다.**
			- **호출할 기능을 가지고 결과물을 내어줄 Visitor가 갑이므로, `을이 먼저 갑을 받아온다`.**
		![20220718010425](https://raw.githubusercontent.com/is3js/screenshots/main/20220718010425.png)
		![20220718010747](https://raw.githubusercontent.com/is3js/screenshots/main/20220718010747.png)
	2. Visitor는 (Visitable 내부에서) Visitable을 메서드인자로 받아들여서 방문한다
		- **차후 Visitable내부에서 this로서 해당 인자를 받는다.**
		![20220718010248](https://raw.githubusercontent.com/is3js/screenshots/main/20220718010248.png)
		- visit내부로 들어오는 visitable을 돌면서, 어떤 기능을 할 예정이다.
		![20220718010911](https://raw.githubusercontent.com/is3js/screenshots/main/20220718010911.png)

2. Main에서 Visitor가 방문할, `정보를 Visitable` 구상체를 만들려했더니, VisitableA에 정보가 없어서 정보필드를 만들어준다.
	- 갑인 visitor는 을 visitable에 들어가서, 을 정보를 받아 결과물을 반환해야하는데, 을 vistiableA가 정보가 없는 상황
	![20220718012126](https://raw.githubusercontent.com/is3js/screenshots/main/20220718012126.png)
	- visitable마다 age를 외부에서 받아와 생성자초기화한다.
	![20220718013159](https://raw.githubusercontent.com/is3js/screenshots/main/20220718013159.png)

3. Visitor는 Visitable마다 instanceof를 확인하지만, `Visitable속의 정보를 비롯한 메서드 기능들`을 이용해서 내 필드에 누적하여 저장하는 것과 같은 `로직을 사용`할 수 있다.
	![20220718013019](https://raw.githubusercontent.com/is3js/screenshots/main/20220718013019.png)
	![20220718014959](https://raw.githubusercontent.com/is3js/screenshots/main/20220718014959.png)

4. Main에서, 여러 종류의 age를 가지는 VisitableA들을 생성하여 list로 가진다.
	![20220718013356](https://raw.githubusercontent.com/is3js/screenshots/main/20220718013356.png)

5. Visitor는 **여러 Visitable을 돌면서 방문(돌고 있는 개별 을(정보/메서드를 가진 visitable) visitor를 먼저 받아들여 정보를 주고,  갑Visitor이 그 정보를 바탕으로 기능호출)하도록 코드를 짠다.**
	- 여기서 갑인 Visitor는 결과물을 주지않고, 자기 필드에 visitable의 정보나 메소드기능결과물들을 누적한다.
	![20220718014020](https://raw.githubusercontent.com/is3js/screenshots/main/20220718014020.png)
