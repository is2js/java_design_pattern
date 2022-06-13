## 팩토리 메서드 패턴
- 목적: `생성`의 템플릿 메소드 패턴
	- 템플릿 메소드 패턴: 어떤 알고리즘을 작성할 때, 여러 단계 step으로 나눠서, step별로 메소드를 추상클래스내 선언하고 하위클래스에서 step별 메소드들을 구현하는 패턴
		- main(client)에서 사용하기 위한 템플릿메서드는 추상클래스에서만 존재하여 외부에서 사용됨. 여기서 create()메서드가 템플릿메소드()의 역할을 한다.
		- **템플릿 메소드는 `내부step들을 다 호출하도록 구현`되어야하므로 interface가 아닌 `추상클래스로 추상화`했었다.**

- 실목적: my) 객체 생성시 `순서를 가지는 여러 step`을 가질 경우, 생성자 클래스에 템플릿메소드 패턴을 적용해서, step들을 하위클래스에서 구현, 추상체내 템플릿메소드로 객체 최종 생성
	- 여러step을 가지는 객체 item을 바로 생성하지 않고
	- `객체 생성자 추상체` + `객체 추상체`를 미리 만들어놓되
	- 구현 `객체1`을 만들 때마다 `객체생성자 구현1` + `객체 구현1`의 set로 만들어나간다.
		- 각 구현마다 step들만 바꿔준다.
		- **`step이 서로 다른 생성메서드`와 함께 `생성될 객체마저 추상화`해놓아야한다.**


- 학습목표: 팩토리 메소드 패턴에서의 `구조와 구현의 분리`를 이해하고 구조와 구현의 분리 장점(추상화로 유연성 확보/ 구조=기능만 client에서 사용)을 이해한다.
	- 내부에 `템플릿 메소드 패턴이 사용`됨
	- `구조와 구현의 분리`는 모든 패턴의 기본 구조다.



- 기본 설계

	![20220613161443](https://raw.githubusercontent.com/is2js/screenshots/main/20220613161443.png)
	- 참고: 템플릿 메소드 설계(step들을 구현하고, 외부에서는 템플릿 메소드로 사용)
		![템플릿 메소드 설계](https://raw.githubusercontent.com/is2js/screenshots/main/20220607150854.png)

	- Product(객체)를 생성하는 생성자(Creator)가 있다. 각각은 기본 골격을 가진다.
		- 기본 골격은 framework라는 package를 만들어 넣어서 사용할 수 있다.
	- 생성자(Creator)에는 create()가 객체에서는 use()메서드가 있는데, 이 때, **create()시 여러step이 있을 수 있다.**
		- 예를 들어, DB 데이터 읽고 -> 객체 생성 -> 생성된 객체를 log남기기의 step을 가진다. **템플릿 메소드 방식으로 각 step을 하위class에서 구현한다.**
		- create()
			- op1()
			- createProduct()
			- op2()

- 요구 사항
	![20220613161926](https://raw.githubusercontent.com/is2js/screenshots/main/20220613161926.png)

### 적용

1. 아이템-아이템생성자 기본골격을 가질 `framework` package를 만들고
	1. 생성자 ItemCreator는 `추상클래스`로
		- 템플릿메소드 create()내에서 `step들을 다 호출`시키려면 인터페이스가 아닌 `추상클래스`이면서 외부에서 사용될 `public Item반환`으로 만들어야한다.
	2. 생성될 아이템 Item은 `인터페이스`로 구현한다.
		- `생성될 아이템의 종류도 여러가지(구현체)`이므로 `추상화`(= 유연성 확보 및 외부에서 사용되는 변수)하되 `요구사항대로 interface`로 추상화한다.
		- **외부에서는 itemCreator.create()로 생성될 `여러 구현체 Item`들을 추상체인 `Item item`으로 받을 것이기 때문에, 객체 생성 메서드는 `응답형도 인터페이스`로 해준다**.
			- **응답형이 inteface일 때, 임시 return값을 new Item()처럼 객체생성못하니, 좌항변수로만 null할당되도록 선언해서 받아준다.**
				![20220613165311](https://raw.githubusercontent.com/is2js/screenshots/main/20220613165311.png)
		![01ae8433-add2-4a76-a15c-6ae83cafeb83](https://raw.githubusercontent.com/is2js/screenshots/main/01ae8433-add2-4a76-a15c-6ae83cafeb83.gif)
	
2. `팩토리 메서드`인 추상클래스 ItemCreator에 step들을 템플릿메소드패턴의 step들처럼 `abstract proteced`로 생성해준다.
	1. DB에 아이템 정보 요청: requestItemsInfo()
	2. item생성: createItem()
	3. item생성 로그: createItemLog()
	![595fb88c-6c64-480c-99cf-01a31f54a8cb](https://raw.githubusercontent.com/is2js/screenshots/main/595fb88c-6c64-480c-99cf-01a31f54a8cb.gif)

3. 이제 외부사용 public + not abstract 템플릿 메서드 create()에 step명세들을 호출해주도록 정의한다.
	![f6896481-4f4b-4816-bd18-bcb2c746203c](https://raw.githubusercontent.com/is2js/screenshots/main/f6896481-4f4b-4816-bd18-bcb2c746203c.gif)

4. **이제 `concrete` package를 만들고 구현Item마다 `framework` package 속 `1. Item` + `2.ItemCreator`**을 구현해서 -> `구현Item1`을 생성을 `구현Item1` + `구현ItemCreator1`의 set로 구현한다.
	1. `체력회복물약`을 `Item구현` + `ItemCreator구현`의 과정으로 만들어보자.
		1. Item 구현 HpPotion
		2. ItemCreator 구현 HpPotionCreator
			- 이 때, 객체 생성시 인터페이스가 아닌 구체class이므로 new HpPotion()으로 생성해서 반환 가능함.
			- 생성 로그는 메세지 + new Date()를 찍어서 현재 날짜정보가 나오도록 한다.
		![40676dd7-6f9c-4fdb-b8ab-5056e7dbedd3](https://raw.githubusercontent.com/is2js/screenshots/main/40676dd7-6f9c-4fdb-b8ab-5056e7dbedd3.gif)
	2. 마력회복물약 관련 class2개는 복사해서 생성해주자.


5. Main(client)에서 각각의 Creator 객체를 만들고, 템플릿메소드인 create()를 통해 각각의 Item을 생성해보자.
	- protected(같은 pacakge)로 step메서드들을 정의해놨기 때문에, main에서는 public인 step메소드들이 아닌 템플릿메소드만 접근 가능하다.
	![1b8ab9c1-f02e-4f9b-be20-54b6a7e2736b](https://raw.githubusercontent.com/is2js/screenshots/main/1b8ab9c1-f02e-4f9b-be20-54b6a7e2736b.gif)

6. 장점: 다른Item을 **추가할 때, `기존 코드변경이 없다.` = 유연하다**
	- 모든 패턴이 `구조(추상체, 선언)` <-> `구현(구현체, 생성할당)`를 분리해서 유연해지는 것이 목표다.

7. 느낀점
	- **`객체` + step을 가진` 객체 생성자` 2개를 추상화해놨으니, 각 구현Item마다 2개의 class를 가지게 된다.**
	![20220613174115](https://raw.githubusercontent.com/is2js/screenshots/main/20220613174115.png)
	- **순서를 가진(step) 메서드들을 호출을 템플릿메소드가 순서대로 호출하도록 감춰둔다.**
		- 템플릿메소드는 `구현 + 외부`에서 호출되어야하므로 `추상클래스 + public 메서드`로 만든다.
		- 각 Item은 직접 구현될일이 없으니 `기능 명세`만 필요하므로 `인터페이스`로 만든다.
	- **그 순서들도 각Item마다 다르게 구현되므로 `추상메서드로 명세 -> 상속한 구현 생성자 class`로 구현해야하므로, `item + itemCreator` 2개의 class가 set가 된다.**
		- 템플릿메소드패턴에서는 객체 생성이 없이 알고리즘만 호출하였기 때문에, Item 인터페이스 -> 구현Item들이 없어도 됬었다.