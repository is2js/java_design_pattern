## 어댑터 패턴
- 목적: 연관성 없는 2 객체를 묶어 사용하기
- 실목적: `미리 주어진 기능(class), 외부라이브러리`를 **Client(호출부) 변경없이, Adapter인터페이스도 변경없이** `요구사항(다른 인자/응답/메서드명)에 맞게 + 기능 추가해서 사용`하기 위해 Adapter(Impl)에 주입해서 내부에서 사용


- 사전적 의미: 기계 기구 등을 다목적으로 사용하기 위한 `부가기구`
- 기본 설계
	![20220531142848](https://raw.githubusercontent.com/is2js/screenshots/main/20220531142848.png)
- 요구 사항
	![20220531142902](https://raw.githubusercontent.com/is2js/screenshots/main/20220531142902.png)
- 추가 요구사항
	![20220531150427](https://raw.githubusercontent.com/is2js/screenshots/main/20220531150427.png)

### 문제상황
- `이미 구현되어 수정불가한` 메서드의 인자와 응답형을 double 아닌 `더 추상화한 인자/응답형` Float로 사용하고 싶다.
- `이미 구현되어 수정불가한` 메서드명도 twoTime이 아닌 `더 추상화를 의미하는 메서드명` twiceOf로 사용하고 싶다.
- 이미 구현된 메서드들(twoTime(double ...))는 `내부에서 바꿔 사용하면서, 다른 것도 받을 수 있는 class`를 만들고 싶다.

- 기능(메서드들)과 구현(사용)을 분리하기 위해 인터페이스를 만들어야한다.
	- 이 때, 인자/메서드명의 명세가 다르므로 intellij @Override interface extract를 쓰는 상황은 아니다. 
		- 추출시 static 메서드는 완전히 이동되니 참고
	- 인자와 메서드명이 다른 껍데기 인터페이스 -> 그 구현체를 만들고 -> 거기서 이미구현된수정불가 메서드를 사용

### 적용
1. `이미 구현된, 수정불가한` 현재 메서드들을 다른 방식으로 사용하기 위한 class를 만들기 위해
	1. `이미 구현된, 수정불가한` 현재 메서드들을 복붙해서 `AdapterImpl` class를 만든다
	2. `원하는 메서드명/인자`로 바꾸고,  `Adapter` interface를 추출해서 명세를 만든다.
2. `AdapterImpl`에서 `이미 구현된, 수정불가한` 현재 메서드들의 `class를 객체를 생성자 주입`받고
3. 복사해온 기존 메서드로직을 -> `객체를 통해 구현하도록 수정`한다.
	![20220531145432](https://raw.githubusercontent.com/is2js/screenshots/main/20220531145432.png)
	![20220531145747](https://raw.githubusercontent.com/is2js/screenshots/main/20220531145747.png)

4. main에서 사용은 기존메서드 class객체 -> AdapterImpl에 주입 -> Adapter추상체 변수로 받아 사용
	![20220531150237](https://raw.githubusercontent.com/is2js/screenshots/main/20220531150237.png)

5. Adapter패턴의 장점 실습해보기
	1. `수정 불가한` 외부라이브러리의 새로운 기능으로 수정 -> `AdapterImpl의 해당메서드`에서 주입받은 라이브러리 로직만 바꿔주면 **Main(Client, 호출부) 변경없이, Adapter인터페이스도 변경없이 그대로 사용가능**
		- 새로운 버전의 기능으로 변경될 수 있음.
	2. 외부라이브러리 사용시 `부가기능` 더하기 -> `AdapterImpl의 해당메서드`에서 log찍는 기능등을 더해도 **Main(Client, 호출부) 변경없이, Adapter인터페이스도 변경없이 그대로 사용가능**
		- 외부라이브러리는 로그찍는 기능을 추가할 수 없음.
	![20220531152356](https://raw.githubusercontent.com/is2js/screenshots/main/20220531152356.png)

6. 또다른 예
	- **엄청 복잡한 알고리즘은 직접 다시 짜는 것보다, 인자-> 외부라이브러리에 맞게 변경 -> 응답만 다시 편하게**응답하는 식으로 Adapter패턴을 이용할 수 있다.
		- 버블소트 기능은 arr를 인자로 받는다.
		- 하지만 우리는 List를 인자로 많이 쓴다.
			1. 인자를 List로 받는 Adapter -> AdapterImpl을 구현하고
			2. AdapterImpl에서 list -> arr 변환후 버블소트 -> arr -> list변환하여 응답