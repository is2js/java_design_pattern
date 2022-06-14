## 패턴
- 목적: `복잡한 단계`가 필요한 `인스턴스 생성`을 main(client)에서 하지말고 `서브클래스`에게 넘겨주는 빌더 패턴으로 구현한다.
	- 책에서 소개하는 빌더패턴1: 템플릿메소드 패턴이랑 비슷하다
	- 실무에서 쓰는 빌더패턴2
- 실목적: 


- 사전적 의미
- 
- 기본 설계
	![20220614105018](https://raw.githubusercontent.com/is2js/screenshots/main/20220614105018.png)
	- Director가 `Factory`객체이다.
		- 통합으로 1개를 가진다.
	- Builder가 설계도 `Blueprint`객체이다.
		- 각 구현 설계도는 같은 Computer객체지만 인자만 다르게 생성한다.
	- 구조 자체는 중요하지 않다. 다양한 방식으로 만들어질 수 있다.
- 요구 사항
	- 복잡한 단계가 필요한 인스턴스를 Computer로 본다.
		- cpu, ram, storage의 필드를 가진다.

### 문제상황

### 적용
1. 생성하고자하는 객체를 `Computer`로 만들고, cpu, ram, storage필드를 가지며, getter/setter를 만들어준다.
	![b1aeca0b-8027-49c4-8bfc-9b641dc8e71f](https://raw.githubusercontent.com/is2js/screenshots/main/b1aeca0b-8027-49c4-8bfc-9b641dc8e71f.gif)

2. main에서 Computer를 생성하고, `toString()`을 재정의해준 뒤, sout해보자.
	![c534be86-d0a8-41d0-8c45-bed6c8b87898](https://raw.githubusercontent.com/is2js/screenshots/main/c534be86-d0a8-41d0-8c45-bed6c8b87898.gif)

3. **이제 Computer생성자에 `요구되는 인자들이 복잡하게 많아지는 경우`에는?**
	- 원래 컴퓨터는 굉장히 복잡한 과정을 가진다.
	- **Main에서 생성의 복잡한 과정을 짜지말고, `다른 객체에 복잡한 생성로직을 넘겨`주자.**
		1. 만드는 역할을 주체할 객체 `ComputerFactory` 클래스를 만든다.
		2. ComputerFactory는 `만들 때 필요한 설계도 역시 (생성된) 객체`로 받는다.
			- 설계도도 종류가 여러가지다. -> `추상화한 설계도(BluePrint)`를 만든다.
		3. 특정 설계도(구현 설계도) 객체를 받아온다.
			- factory.setBlueprint(`new LgGramBlueprint()`)
		4. 설계도에 따라 Computer도 여러종류가 되니, 추상화 -> 구현체로 만든다.

		5. ComputerFactory는 설계도 객체를 바탕으로 객체를 만든다.
			- `factory.make();`
		6. ComputerFactory는 설계도를 바탕으로 만든 객체를 get으로 건네준다.
			- `factory.getComputer();`
	![20220614112010](https://raw.githubusercontent.com/is2js/screenshots/main/20220614112010.png)


4. factory가 받을 `Blueprint`부터 만들어줘야한다.
	- 설계도는 Builder로서 `추상체 설계도에는 buildPart()`의 기능 명세가 있어야한다.
		- 여기서는 3개의 field를 채우는 것이 buildPart()이므로, 3개의 setter를 명세한다.
		![20220614115857](https://raw.githubusercontent.com/is2js/screenshots/main/20220614115857.png)
	- 여기서 생성할 객체의 종류는 Computer로 1개이지만, `인자 종류에 따라 서로 다른 Computer로 인식`된다.
	- 넣어주는 인자에 따라 각기 다른 Computer가 되는데, 인자를 삽입해주는 설계도는 컴퓨터 종류마다 서로 다르므로 가지므로 추상화 -> 구현체 형태로 구현한다.
		- **컴퓨터 인자의 종류 = 설계도의 종류 -> `여러개라서 추상화`**
	- **Computer마다 field로 가질 cpu,ram,storage 역시 Blueprint의 필드로 가져야하니 설계도를 `abstract`로 구현한다.**


5. Blueprint(AbstractBuilder)를 만든다.
	- **각 구현 설계도가 가질 객체생성에 필요한 `부품생성기능의 명세`를 가지고 있다.**
		- **my) 추상체는 `각 구현체들의 기능을 미리 생각해보고 명세를 추상메서드나 전략메서드로 올리면 된다. 쉽게가려면 구현체 -> intellij @Override을 이용하는 방법`을 택해왔다.**
		- 구현 설계도(Builder)는 객체의 각 부품들을 만들어줘야한다.
		- 추상 설계도는 그 명세를 추상메서드로 가지고 있어야한다.
	![20220614120144](https://raw.githubusercontent.com/is2js/screenshots/main/20220614120144.png)

6. 추상설계도를 구현(상속)하여 `구상 설계도(ConcreteBuilder)`를 만든다.
	- **최종적으로 factory.make()로 만들어질 computer는 `factory에서 최종생성`하거나 `각 구현설계도에서 최종생성하여 factory내에서 반환`해주거나 2가지 방법을 가질 수 있다.**
		- 정답은 없으나, 여기서는 각 구현설계도가 `Computer를 필드로 가지면서 setter로 최종생성해주는 것`으로 한다.
			- **이럴 경우, 최종 Computer를 반환할 `Factory는 완성된 Computer를 field로 가지고 있는 구현설계도를 ~~생성자 주입~~ -> field만 선언해놓고, setter로 설계도를 주입받아` 최종 반환해줄 수 있다.**
		- 만약, 구현설계도에서 각 인자로 최종Computer를 만드는 것이 아니라면,
			- 구현설계도 -> factory로 각 인자들을 반환해줘야하는데, 묶어서 반환해주려면 새로운 객체생성이 또 필요해지니 복잡해질 수 있다.
	- computer를 각 구현설계도에서 생성해줄 때, 생성자주입으로 바로 생성해도 되지만, **여기서는 default인자를 받은 Computer를 구현설계도 생성자에서 초기화해주는 `field Computer computer`를 가지게 한 뒤, setter로 각 구현설계도에 맞는 인자를 주입하는 방식을 쓴다.**
	![c8e6206d-1858-4f54-a7b7-50b86bcf7502](https://raw.githubusercontent.com/is2js/screenshots/main/c8e6206d-1858-4f54-a7b7-50b86bcf7502.gif)

	- 별개로 생각해볼 거리는
		- Computer를 field로 안가지고, 구상 설계도에서 Computer가 각 인자들을 setter로 채워놓고 맨 마지막에 getter함수만 new Computer(채워진, 컴퓨타, 인자들)로 가지게 할 수 도 있다.
		![20220614122458](https://raw.githubusercontent.com/is2js/screenshots/main/20220614122458.png)
		
7. 여러 구상설계도를 받을 수 있는 `Factory`를 만든다.
	- 구상설계도를 생성자 주입받는 것이 아니라 빈 field로 가지고 있다가 setter로 주입받는다.
		- **이렇게 객체 생성과 동시에 초기화 안되고, setter -> make -> getter의 과정을 가지면 문제점이...  make하기전에 getter를 호출하면 안되기 때문에 `makeAndGet()`함수로 통합해서 만들어 줄 수도 있다.**
	- 이 과정에서 blueprint의 getter도 생성한다.
	![f7d0e4cd-c290-46a6-bda4-b4519cb6e923](https://raw.githubusercontent.com/is2js/screenshots/main/f7d0e4cd-c290-46a6-bda4-b4519cb6e923.gif)


	