## 빌더 패턴2

- 목적: `많은 인자를 가진 객체 생성`을 다른 객체 도움으로 생성하는 패턴
- 실목적: 생성자에 많은 인자를 순서를 기억하며 넣지말고, Builder라는 static + instance method를 활용해서 setter한 객체를 반환해준다.
	- **미리 Builder.start() -> .set인자1(값1) -> .set인자2(값2)로 만들어놓고 intellij로 만들어나가자.**
	- Builder는 만들고 싶은 setter전 객체의 변수를 field -> 생성자에 default값으로 초기화해놓고 setteer한다
	- **Builder.start()라는 메서드로 static -> builder객체 instance를 처음 만든다.**
	- **매 체이닝마다 한 곳에 있는 메서드를 활용하기 위해 해당객체`this`를 반환해줘야한다.**
	- 제일 마지막에는 setter작업기 끝난 객체를 반환한다.

### 적용

1. 만들고 싶은 `인자가 많은 객체` Computer class를 만든다.
	![20220614153729](https://raw.githubusercontent.com/is2js/screenshots/main/20220614153729.png)

2. 인자가 아주 많다고 가정하고 **`builder`**를 만든다.
	- null상태로 초기화한 `field Computer computer` + `생성자에서 default인자로 초기화`를 가지고 있고, 이것을 builder속 setter메서드로 완성해나갈 것이다.
	![e308add0-130e-4749-a7e8-7a27d116ffc5](https://raw.githubusercontent.com/is2js/screenshots/main/e308add0-130e-4749-a7e8-7a27d116ffc5.gif)

3. **일단 builder -> 메서드 체이닝을 통해 -> 최종 객체 반환하는 과정을 `Main(client)`에서 사용해서 빨간줄로 만들어나가보자.**
	- builder패턴은 인자를 잘못 입력(순서 등)하는 것을 막아준다.
	- 구조를 먼저 파악하고 **input과 output을 intellij에 의존해서 입력한 method생성이 가능해진다.**
		- **메서드 체이닝마다 필요한 인자들을 예시로서 넣어주고 만들어보자.**
		![20220614154746](https://raw.githubusercontent.com/is2js/screenshots/main/20220614154746.png)
		![20220614155120](https://raw.githubusercontent.com/is2js/screenshots/main/20220614155120.png)

	- **기본적으로 한 class내에 존재하는 메서드들을 연속해서 부르려면 `각 메서드의 return값이 해당 동일한 객체`여야한다. `대신 메서드적용시마다 변화하는 상태값을 field로 가져야한다`**
		- 첫번째 메서드(start)를 만들 때, 그다음 메서드가 완성되지 않는 상태 -> intellij가 메서드의 주체객체를 모르는 상황이 -> 첫번째 메서드의 응답값으로 `현재 호출하는 객체인 builder`가 반환되도록 `new Builder()`를 처음으로 만들어줘야한다.??
			- **왜냐면 Builder는 처음에는 Util class처럼 classmethod로 start()하기 때문에 `start()하는 순간 실제 객체`가 만들어진다. `그전까지는 정적 메소드로 호출하는 것`이다.**
	![c6efcdd7-5ea8-4464-b952-eef609a8adf6](https://raw.githubusercontent.com/is2js/screenshots/main/c6efcdd7-5ea8-4464-b952-eef609a8adf6.gif)



4. print해서 확인해보자.
	![4e693c22-ee12-4c1a-b9fa-0cb4eb9f3c69](https://raw.githubusercontent.com/is2js/screenshots/main/4e693c22-ee12-4c1a-b9fa-0cb4eb9f3c69.gif)