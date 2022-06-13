## 컴포짓 패턴

- 목적: 컨테이너와 내용물을 동일시한다.
- 실목적:


- 기본 설계
	![20220613202603](https://raw.githubusercontent.com/is2js/screenshots/main/20220613202603.png)
	- Component는 내용물의 추상체로서, interface로 구현해도 되지만, 구현체들이 모두 공통된 field(상태값)을 가진다면 abstract class로 추상체를 구현한다.
	- Leaf는 내용물 중 1개, Component 구현체
		- 내용물Leaf과 Component는 공통적인 기능(opertation)이 있다면 가진다.
	- Composite는 내용물Leaf를 담을 때, `추상체인 Component`변수 List로 받아 보관하는 컨테이너
		- Composite는 컨테이너로서 내용물을 더하거나 빼거나 getter(getChild) 기능을 가져야한다.

- 요구 사항
	- 파일시스템으로 composite패턴을 많이 구현한다.
	![20220613203003](https://raw.githubusercontent.com/is2js/screenshots/main/20220613203003.png)
	- Component = Component
	- Leaf(내용물) = File
	- Composite(컨테이너) = Folder
		- **희한하게 내용물과 컨테이너가 같은 class Component를 구현한다는 것이 특이점**

### 적용
1. 파일이름 name을 상태값으로 가지는 추상클래스 `Component`를 만든다.
	- 어떤 구현체든지 name필드를 공통적으로 가지게 하기 위해서임.
		- **my) 추상체를 만들 때, 필드도 가지는 추상체는 abstract class를 생각하자.**
	- 필드에 따른 getter와 setter도 만들어준다.
		- **setter를 만들거면, 생성자 초기화되는 final로 필드를 만들면 안된다.**
	![84e584c2-43b2-40f1-bc33-0f199eeef1f9](https://raw.githubusercontent.com/is2js/screenshots/main/84e584c2-43b2-40f1-bc33-0f199eeef1f9.gif)

2. Component를 구현하는 `내용물Leaf`를 `File`이라는 이름으로 만든다.
	- 이 때, 부모field인 name이외에 `Obejct data`도 가지고 있어야한다.
		- 파일이름 + 진짜 파일
		- 이 data는 사용하진 않을 것이다.
	![0284973e-a216-4d5c-9cbf-e9fc54df561a](https://raw.githubusercontent.com/is2js/screenshots/main/0284973e-a216-4d5c-9cbf-e9fc54df561a.gif)


3. Composite(컨테이너)를 `Folder`라는 이름의 class를 만드는데, 
	- **특이하게도 내용물(Leaf)인 `File`과 동일하게 `Component`를 구현해서 만들지만**
	- **또한 일급컬렉션처럼  `List<Component> children`상태값과 `add/remove/getChild` 등의 메서드를 만든다.**
		- **구현한 `추상체`를 `List 상태값`으로 가지게 되는 구조**
	- 상태값 초기화를 `final + 생성자`에서 안하고 바로 `변수에서 빈 ArrayList로` 해준다.
	![059cf476-344c-462a-907d-c5c82f2c2659](https://raw.githubusercontent.com/is2js/screenshots/main/059cf476-344c-462a-907d-c5c82f2c2659.gif)


4. 이제 기본 composite패턴 틀은 완성되었다. 구현할 파일시스템을 Main에서 File 생성 -> 컨테이너에 add하기 위해서 구조를 주석화한다.
	- **cf) 일급컬렉션까지 같은 추상체를 가진다면, add한 일급컬렉션을 다시 다른 Component와 같이 add할 수 있는 장점이 있을 것 같다.**
	![20220613205426](https://raw.githubusercontent.com/is2js/screenshots/main/20220613205426.png)

5. **누락된 Component의 `field -> 생성자 주입으로 초기화` 추가해주기**
	- 생성자로 받아놔야 `외부에서 받아서 상태값name 바로 지정`할 수 있다.
		- 예를 들어, 파일명을 지정해서 File객체 생성 -> `new File("name")`
	- Component를 구현하는 File, Folder 둘다 수정해준다.
	![78615ad8-5db9-4279-91d6-02f0c4bdfffb](https://raw.githubusercontent.com/is2js/screenshots/main/78615ad8-5db9-4279-91d6-02f0c4bdfffb.gif)


5. Main(Client)에서 composite패턴의 leaf(File)와 compiste(Folder) 활용해보기
	1. 각 Folder와 File의 객체들을 name을 넣어 생성해준다.
