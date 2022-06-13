## 추상 팩토리 패턴

- 목적: 관련 있는 객체들의 생성을 가상화 할 수 있다.
- 실목적: 객체 생성시 필요한 여러 부품객체들을 생성하는 메서드의 명세(인터페이스) 인터페이스로 가상화된 Factory에 몰아넣어놓고, 각 구현체Factory들을 부품객체들+Factory를 구현해서 만들어놓으면, 어느 구현체든지 Client에서 구상Factory객체만 바꿔주면 생성과정일 동일하게 바로바로 생성된다.
    - 추상팩토리 -> 각 구상팩토리만 바꿔끼워넣는 전략패턴의 업그레이드 버전
    - **언제든 갈아끼울 수 있는 추상팩토리 변수 -> 부품 객체들도 생성시 자동으로 갈아끼어지도록 추상부품체 생성**하는 것이 핵심인 것 같다.
    - **my) `팩토리메서드 패턴 with 템플릿메소드`과 다르게, factory 및 생성될 여러부품객체들이 존재하며, 그것드 전부 인터페이스로 추상화**
        - 템플릿메소드(내부step들 다 호출해주는 public)이 없으니 추상클래스로 정의X 인터페이스로 명세만 정의 
- 기본 설계
    ![20220612230241](https://raw.githubusercontent.com/is2js/screenshots/main/20220612230241.png)
    - 생성하는 Factory부분을 `가상화`해서  `구체적인 생성 Factory를 가려주고`, Client는 가상화된 부분으로 `가상화된 Product`를 생성해서 활용한다.
    - AbstractProudctA와 B가 관련된 객체들이다.
        - 예를 들어, 자전거 생성이 필요한 Body와 Wheel을 예시로 든다.

- 요구 사항

- 추가 요구사항

### 문제상황

### 적용
1. `abst`ract 패키지를 먼저 만들고,객체 생성에 필요한 `부품 객체들`을 `abst`패키지 내 `interface(가상화)`를 만든다.
	- 기능을 가지는 부품들이므로 interface로 생성한다.
	1. Body
	2. Wheel
		- 여기선 구현안하지만, 구름() 등의 기능 명세를 정의할 수 도 있다.
2. **이제 부품객체들을 전부 만드는 기능을 해주는 객체역할의**  `Factory`를 `인터페이스(가상화)`로 만든다
	1. BikeFactory
	![20220612232023](https://raw.githubusercontent.com/is2js/screenshots/main/20220612232023.png)

3. BikeFactory는 내부에서 `부품객체들(Body, Wheel)을 만들어서 반환`해주는 기능을 가진다.
	- 핸들, 의자 등의 부품객체들도 있지만 예제이므로 쉽게 간다.
	- 응답형은 좌항의 변수와 같아서 받을 때 추상형(interface)으로 받는다.
	![20220612232616](https://raw.githubusercontent.com/is2js/screenshots/main/20220612232616.png)

4. `concrete한 자전거 중 1개` 패키지를 만들고, 만들고 싶은 `자전거를 구체화 중 1개 Facotry`를 만들어준다.
	- Bike의 여러 구체화된 구현체 중 `Sam`천리 자전거를 만들 계획이다.
	1. `sam`패키지를 만들고
	2. `BikeFactory`를 구현한 `SamFactory`를 만든다. 
		![7d855a2d-3771-4da5-9f3b-08627f8aba4d](https://raw.githubusercontent.com/is2js/screenshots/main/7d855a2d-3771-4da5-9f3b-08627f8aba4d.gif)


5. 가상화된 BikeFactory내부 부품객체들을 구현한 `-Body`, `-Wheel`도 구현해준다.
	![0ae7a80d-34f0-4c28-9f20-750876bdabc2](https://raw.githubusercontent.com/is2js/screenshots/main/0ae7a80d-34f0-4c28-9f20-750876bdabc2.gif)

6. 구체화된 SamFactory를 각각의 구현부품객체들을 반환하도록 해준다.
	![e5051329-19d1-4190-9295-32be1fe06eb5](https://raw.githubusercontent.com/is2js/screenshots/main/e5051329-19d1-4190-9295-32be1fe06eb5.gif)

7. Main(Client)에서
	1. 구체Factory를 추상Factory변수에 받아주고
	2. factory.create각부품(); 해준다.
	3. 현재 각 부품객체마다 명세 -> 기능구현이 없으므로 `.getClass()`로 클래스만 찍어본다.
	![d836aacd-2352-4f18-8376-940ef901d593](https://raw.githubusercontent.com/is2js/screenshots/main/d836aacd-2352-4f18-8376-940ef901d593.gif)


8. **이제 추가적으로 Sam천리 자전거외에 `추가 자전거 Gt`를 만들고 싶다면?**
	1. `GtBody`, `GtWheel` -> `GtFactory`를 **`asbt`패키지내 Body, Wheel, BikeFactory를 구현**해서 만들어준다. 
	2. Client인 Main에서는 **추상체인 BikeFactory에 할당될 `구상체Factory`만 `GtFactory`로 바꿔주면, `생성과정은 명세대로 동일하여 쉽게 생성`할 수 있어 `관련된 부품객체들을 일괄생성`이 바로 가능해진다.**
	![7bb38a5c-3d84-45cf-ac70-600dae198434](https://raw.githubusercontent.com/is2js/screenshots/main/7bb38a5c-3d84-45cf-ac70-600dae198434.gif)
