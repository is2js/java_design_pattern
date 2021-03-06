## 템플릿 메소드 패턴

- 목적: `일정한 (단계를 가진) 프로세스` 묶어주기
- 실목적: 알고리즘의 **구조를 메서드에 정의**하고, 하위 클래스에서 알고리즘 구조의 변경없이 알고리즘을 **재정의**하는 패턴
	- 언제?
		1. 구현하려는 알고리즘이 **일정한 프로세스(여러 단계)**가 있다.
		2. 구현하려는 알고리즘이 **변경 가능성**이 있다.
	- 사용 단계
		1. 알고리즘은 **여러 단계**로 나눈다.(여러 step)
		2. 나눠진 알고리즘의 **단계를 (추상)메소드로 선언**한다.
		3. 알고리즘을 수행할 **(step순으로 만든 메서드들을 순서대로 전체 호출하는) 템플릿 메소드 = 알고리즘 메서드**를 만들고, 단계 메서드들을 순서대로 호출한다.
		4. 하위 클래스에서 **나눠진 메소드들을 구현**한다.


- 사전적 의미: 모양자 + step
	![20220607150459](https://raw.githubusercontent.com/is2js/screenshots/main/20220607150459.png)

- 기본 설계
	![20220607150854](https://raw.githubusercontent.com/is2js/screenshots/main/20220607150854.png)
	1. 요구사항에 따른 알고리즘을 여러step으로 메서드를 상위 클래스에서 선언해둔다.
		- operation1(), operation2(), operation3()
	2. 상위클래스에서 템플릿메소드를 선언하고 내부에서 순서대로 호출한다.
	3. 하위 클래스에서 step별로 선언된 메서드들을 구현해준다.

- 요구 사항
	![20220607151141](https://raw.githubusercontent.com/is2js/screenshots/main/20220607151141.png)
	- 요구사항에서부터 `접속`이라는 알고리즘에서, `여러 step`이 나타나있음.
- 추가 요구사항
	![20220607161138](https://raw.githubusercontent.com/is2js/screenshots/main/20220607161138.png)
### 적용

1. 추상클래스(단계별 추상메서드 -> 전체 호출 템플릿 메서드) 구현
	1. 요구사항에서 명시된 `step을 가지는 알고리즘`을 `추상`클래스를 만들고, `step별로 명세를 추상메서드로 정의`해준다.
		- 보안 => 인증 => 권한 => 접속의 step이 존재한다.
		- 추상 메서드의 이름은 `Abst` + 알고리즘 + `Helper`로 명명하자.

	2. 구현은 하위클래스에서 할 예정이므로 알고리즘을 수행할 `step 메서드들`은 `abstract`로 추상메서드를 선언하고, **알고리즘 내부 노출을 방지하기 위해 ~~private~~ 구현할 하위클래스들을 위한 `protected`로 접근제어자를 만들어준다.**

	3. 이제 알고리즘 메서드인 템플릿 메서드에서 `아직 구현은 안됬지만, 순서를 가지는 하위 단계메서드들을 호출하도록 구현`한다.

	```java
	public abstract class AbstConnectHelper {
		protected abstract String doSecurity(String info);

		protected abstract boolean authentication(String id, String password);

		protected abstract int authorization(String userName);

		protected abstract String connection(String info);

		// 템플릿 메서드
		public String requestConnection(String encodedInfo) {
			//단계1: 보안 -> 암호화된 문자열을 받아, 디코드 -> id와 password를 할당한다.
			final String decodedInfo = doSecurity(encodedInfo);
			final String id = "aaa";
			final String password = "bbb";

			//단계2: 인증 -> 보내준 id와 password를 검증한다.
			// -> 인증 실패시 에러내서 다음진행못하게
			// my) true시 if문 내부에서 진행(?) -> false시 에러내서 못 진행 + 자동true시 if문 없이 아래에서 이어서 진행
			//     early return = early continue/break(반복문) = early thr!!!
			final boolean isAuthenticated = authentication(id, password);
			if (!isAuthenticated) {
				throw new Error("아이디 암호 불일치");
			}

			//단계3: 권한 -> 정액제 등록한 사람만 접속가능
			// -> 권한은 int로 여러 종류를 가질 수 있다 t/f가 아님.
			final String userName = "userName";
			final int authorization = authorization(userName);
			switch (authorization) {
				case 0: // GM
					break;
				case 1: // 유료 회원 -> 아템 확를 높음. 경험치 많음.
					break;
				case 2: // 무료 회원 -> 아템 확률 낮음. 경험치 적음.
					break;
				case 3: // 권한 없음
					break;
				default: // 기타 for 확장성
					break;

			}
			//
			return connection(decodedInfo);
		}
	}
	```

2. 추상클래스를 구현한 (자식)클래스 구현
	1. 주로 Abst를 떼고 -> `Default`를 붙여 이름 짓는다.
		![a214fe44-cd8e-44ea-873c-e3ebe4696c5d](https://raw.githubusercontent.com/is2js/screenshots/main/a214fe44-cd8e-44ea-873c-e3ebe4696c5d.gif)
	2. 내부 `단계별메서드 구현`은 실제로직 대신 sout로 대신한다.
		- **템플릿 메서드는 구현안한다. 전체 단계별 메서드 호출해주 메서드로서 Abst에서 구현했음.**
3. 내부 알고리즘 노출 방지를 위해 `알고리즘 정의` <-> `Main 실행`의 패키지 분리
	1. `protected`는 같은 패키지내에서만 접근 허용이다.
	2. `protected`는 상속한 자식의 경우메나 다른 패키지에서 접근 가능이다.
	- **client들은 못보도록 패키지를 따로 분리해야한다.**
		- client(실행 함수 클래스)를 같은 패키지에 넣을 경우, protected라도 내부 알고리즘이 다 노출된다.
			- 패키지를 분리하여 proteced 단계별 메서드들을 외부로부터 감추자.
		![3ef69c50-510d-4106-8202-ad9a8e30ebcf](https://raw.githubusercontent.com/is2js/screenshots/main/3ef69c50-510d-4106-8202-ad9a8e30ebcf.gif)



3. Main클래스에서 한번 호출해보자.
	- 추상체 = new 단계별메서드_구현체();
	- 추상체.구현체에선_구현안한_템플릿메서드();
	![cd050256-7665-4764-bd23-686025b534b7](https://raw.githubusercontent.com/is2js/screenshots/main/cd050256-7665-4764-bd23-686025b534b7.gif)

4. 추가 요구사항 반영
	- 템플릿 메소드가 있는 추상체가 아니라 **구현체에서 반영해주면 된다.**
	- `10시 제한` 정책 -> `권한`파트에서 -> 서버에서 나이 확인 && 현재 시간 확인 -> `권한을 없는 것으로 변경` or `새로운 권한 (-1) 생성해서 case문에서 thr Error`한다.