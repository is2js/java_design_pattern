## 추상 팩토리 패턴2

- `gui생성시 여러부품 객체들을 생성`하는 과정을 Factory구현체만 바꿔주면 되는 예제를 다룬다.
	- 생성할 부품은 Button과 TextArea 2개로 한다.
- **추가적으로 각 구현체Factory를 잘못 사용할 수 도 있기 때문에, `외부에 구현체 생성을 감추고, 사용자는 인터페이스(변수)만 사용`할 수 있게  해줘야한다.**
	- 각 구현체package들을 모두 정적팩토리메서드class내부로 Move시켜서 거기서 생성되고, 구현하도록 변경함.

### 적용
1. `abst`패키지에 생성할 부품인 `Button`, `TextArea` -> `GuiFactory`를 인터페이스로 선언하고, Factory내부를 부품객체들을 생성해서 반환해주도록 public 명세를 만든다.
	- 각 부품들도 기능을 가지도록 구현한다
		1. Button - void click()
		2. TextArea - String getText()
	![b8d6ff91-b52e-4816-afa8-108e67fdeb4b](https://raw.githubusercontent.com/is2js/screenshots/main/b8d6ff91-b52e-4816-afa8-108e67fdeb4b.gif)

2. gui를 가지는 구상체들을 `linux`, `win`, `mac`패키지를 미리 만들어놓자.
	![20220613001537](https://raw.githubusercontent.com/is2js/screenshots/main/20220613001537.png)

3. 각 구현패키지마다 부품객체들(Button, TextArea)를 구현한 `-Button, -TextArea`를 구현하고, 기능도 만들어넣고(print), `-Factory`를 구현해서 생성객체를 구현부품객체들로 채워놓자.
	![cfdbc537-db62-4fb2-acef-eb1d90ff222a](https://raw.githubusercontent.com/is2js/screenshots/main/cfdbc537-db62-4fb2-acef-eb1d90ff222a.gif)


4. Main(client) 언제든 갈아끼울 수 있는 추상팩토리를 변수로 받아서, 내부에서는 각 구현부품객체들이 생성되도록 한다.
	- **언제든 갈아끼울 수 있는 추상팩토리 변수 -> 부품 객체들도 생성시 자동으로 갈아끼어지도록 추상부품체 생성**하는 것이 핵심인 것 같다.
	![aeac8431-649a-41a2-b8f5-0c322514461b](https://raw.githubusercontent.com/is2js/screenshots/main/aeac8431-649a-41a2-b8f5-0c322514461b.gif)

5. mac, win 도 똑같은 패턴으로 Factory까지 구현한 뒤, Main(Client)에서는 `구현체Factory만 바꿔주면`, 명세에 맞게 부품객체들이 알아서 생성된다.
	![20220613105010](https://raw.githubusercontent.com/is2js/screenshots/main/20220613105010.png)


6. **이제, 구현체Factory를 `Clinet에서 직접 사용는 것을 막아`보자.**
	- **`Client의` 운영체제에 따라 `프로그램이 알아서 Factory가 선택`되도록 client에서 구현체 생성을 피한다.**
	1. **`concrete`패키지를 만들고, `FactoryInstance`클래스를 만든다.**
		- 내부에 `여러 구현체Factory들 중 택1해`서 만들어서 반환하도록 `public static`의 정적메서드 .getGuiFac()을 만들 것이다.
		- 외부에서는 이 메서드 호출을 통해 `GuiFactory guiFactory = `형태로 오로지 인스턴스만 사용하게 할 것이다.
		![a04fe4bd-5370-4668-8bca-c3136267c4a7](https://raw.githubusercontent.com/is2js/screenshots/main/a04fe4bd-5370-4668-8bca-c3136267c4a7.gif)

	2. **구현체Factory를 주석으로 막고 FactoryInstance.getGuiFact()의 `정적 팩토리 메서드`를 통해 `내부에서 알아서 구현체Factory가 선택되어 생성`되도록 감싼다.**
		- 외부Client가 구현체를 선택하는 일이 없게 한다. (만약 외부에서 종류를 받아야할 때는 정팩매의 인자로 `종류 상수`로 받는다?!)
		![20220613112633](https://raw.githubusercontent.com/is2js/screenshots/main/20220613112633.png)

	3. 외부에 `구현체 생성`관련 메서드들은 `public으로 외부에 노출되지 않아야` 한다. 
		- **그러기 위해서, 구현체package들의 모든 구현class들(button, textarea, factory)을 `public을 제외`하고 `정적 팩토리 메서드(FactoryInstance) 안`으로 옮겨준 뒤, package들은 삭제한다.**
		- **move(`alt+7` + `F6`)을 활용하되, `정적메서드를 가지는 곳에서 호출될 Factory들을 static으로 가질 필요없다. 내부에 가지면 일반 inner class로 옮긴`다.**
			- F6으로 옮기면, public static이 붙을 가능성이 높다. -> **하지만 외부에 공개하지 않기 위해 옮기는 것이기 때문에 `class를 제외한 다른 접근제한자는 move후 삭제`해준다.**
				- 정팩매는 static이라 내부에서 사용되는 구현체Factory생성도 static -> 구현체Factory내에서 생성되는 구현부품생성도 static으로 해야한다.
				- 결론적으로 `public`만 떼주면 된다.
		![97e0a487-cf6a-4b1c-8a06-2f0113e2e78d](https://raw.githubusercontent.com/is2js/screenshots/main/97e0a487-cf6a-4b1c-8a06-2f0113e2e78d.gif)

	

7. 이제 switch문에 들어갈 0,1,2의 값을 java API로 자동으로 선택되도록 바로 위에서 메서드를 걸어주자.
	- 현재 switch문 작성시 default 0값이 들어가게 해놨다.
	- `System.getProperty("os.name")`API를 이용하면, Client의 운영체제가 알아서 나온다. **원래는 정규표현식으로 Mac, Windwows, Linux 등만 추출하여 확인해야한다. 버전이 다를 수 있기 때문**
		```
		System.getProperty("os.name") = Windows 10
		```
	![20220613122458](https://raw.githubusercontent.com/is2js/screenshots/main/20220613122458.png)
	![20220613122557](https://raw.githubusercontent.com/is2js/screenshots/main/20220613122557.png)


	