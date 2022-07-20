## 메멘토 패턴

- 뜻: 기념품, 유품, 추억거리

- 목적: 객체의 상태를 memento객체에 저장했다가 다시 꺼내서 이전의 상태로 되돌리는 패턴
  - 메멘토패턴을 통해, **객체의 상태를 저장**하고 **이전의 상태로 `복구`**한다.
  - **접근제한자 proteced의 사용을 이해**한다.
- 실목적: 


- 기본 설계

   - 기본적으로 3가지 객체가 필요하다.

   ![image-20220720125740990](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720125740990.png)

   - Originator: 상태값을 가지고 있는 객체
   - Memento : Originator의 상태를 저장하고 있는 객체
   - CareTaker: Memento를 관리해주는 관리자객체
   
   
   
   

### 요구 사항

- Originator의 내부에서 내부state필드로 상태저장객체 memento객체를 만들어 외부에 반환한다.

  - **메멘토 상태객체는 Originator가 내부정보로 발행하는, `내부발행 데이터객체`다. `원래는 CareTaker가 인자로 와서 받아가야한다.`**
  - **내부발행되며 변경해서는 안되는 상태객체는 `같은 패키지 + 발행객체는 생성자까지 protected로 보호`하고, `반드시, 발행자 class내부에서만 생성자를 호출해서 생성` -> `생성완료후 수정불가능한 상태객체를 반환만 getter형식으로 외부로`한다.**
    - 내부 발행 데이터객체지만, **외부에서 사용(CareTaker가 저장)해야하므로 반환해줘야한다.**
      ![image-20220720231821460](../../../../../../AppData/Roaming/Typora/typora-user-images/image-20220720231821460.png)

- CareTaker는 받은 상태객체를 순서대로 저장한다.(stack)

- Originator는 CareTaker에서 역순으로 저장한 상태객체memento를 받아와, 직전 상태로 상태값을 복원한다.

  ![image-20220720232403076](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720232403076.png)

### 적용

1. 일단 abc`패키지`를 하나 만들어놓는다.
   
   ![image-20220720132102110](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720132102110.png)
   
2. 패키지안에 `Originator`객체를 만들어준다. **내 상태필드를 이용해서, 상태객체를 만들어, 제공해주는 기능을 가진다.**

   - **String으로 상태필드**를 가지며, **상태는 어차피 바뀔객체이므로, `not final + setter조합`으로 정의한다.**
   - Originator가 `내 상태필드`를 이용해서 `Memento(상태객체)`를 만들어, getter형식으로 제공해준다. **내부정보로 새로운 객체 생성 -> getter처럼 만든다.**
     - **`Memento객체는, Originator가 내부발행하는 데이터객체`다!!!**
     - 원래는 CareTaker도 만들어서 하지만, 여기선 생략하고 client에서 stack으로 구현할 예정이다.
     - 원래는 내 정보를 이용하기 위해, Memento객체를 인자로 받아와서, 내부에서 가져가라고 한다?!

   ![image-20220720133137531](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720133137531.png)

3. 또한, Originator는 **이전의 memento(상태객체)를 받아 내 상태로 업데이트(저장)**하는 기능이 필요한다.

   - **voidsetter받기기능 = `외부로부터 이전상태정보를 제공받아 -> 그것으로 내상태가 바뀌는` = `이전정보로 복구`하는 기능이 된다.**
   - my) 상태값을 **받기기능**으로 **복구시킨다.** 

   ![image-20220720133533648](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720133533648.png)

   

4. 상태객체의 생성하여 제공/받아업데이트(저장)이외에, **상태 자체의 제공/받아업데이트**기능도 있어야한다.

   - originator의 현재 상태를 **외부에서 확인해서 찍어보기 위해 getter**가 있어야한다.
   - **mement는 상태저장 객체일 뿐, 상태를 바꾸는 역할은 setter가 직접적으로한다.**

   ![image-20220720133758290](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720133758290.png)





5. **memento상태객체**는 **originator의 상태정보를 생성자에서 받아 생성된다.**

   - **`originator의 상태필드가 중요하므로 같은형으로 똑같이 필드에 저장`한다.**
   - originator가 상태를 회복하고 싶을 땐, 상태필드값을 제공해준다.

   ![image-20220720134839204](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720134839204.png)

6. client에서 상황을 만들어준다.

   1. **originator**는 **setter로 상태를 여러번 바꿔 업데이트하는 시나리오**를 만든다.
      ![image-20220720222125775](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720222125775.png)

   2. **originator는 상태를 바꿀때마다, `memento상태객체를 생성`함으로써 `현재 상태를 저장`한다.**
      ![image-20220720222246202](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720222246202.png)

   3. **상태객체를 저장할 `CareTaker`를 `stack`으로 임시 구현한 뒤, `상태객체가 생성될때마다 CareTaker에 저장`한다.**

      ![image-20220720222702282](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720222702282.png)

      - 변수를 재할당으로 업데이트해서 재활용하거나 다른 수정사항이 있는 것이 아니기 때문에 인라인으로 **상태객체 생성과 동시에 careTaker에 저장한다**

        ![image-20220720222812598](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720222812598.png)

   4. **Originator의 상태값이 바뀔때마다 `메멘토생성 -> careTaker에 저장`한다.**

      ![image-20220720222903712](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720222903712.png)

7. **이제 careTaker에 저장된 것을 역순으로 pop해서 상태를 복원시켜보자.**

   ![image-20220720224717024](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720224717024.png)

   ![image-20220720225212590](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720225212590.png)

   ![image-20220720225521277](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720225521277.png)

   

8. **왜 패키지를 만들었을까?**

   - **메멘토 객체는 `생성자 조차도 protected`**

   ![image-20220720230052377](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720230052377.png)

9. **originator의 상태는 외부에서 변경시키는게 정상이지만, `memento라는 상태객체는 originator 내부정보로 생성되며, 저장`되는 귀한 객체이므로 `외부에서 변경은 물론이고 생성도 해선 안되기 때문`에 `생성자에서도 protected접근제한자`를 쓴다.**

   - **외부에서 메멘토의 상태를 변경하거나 `아예 다른 메멘토로 중간에 가로챈다`면, 저장할 상태가 완전히 오염된다.** 

     ![image-20220720230649069](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720230649069.png)

     ![image-20220720230718203](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720230718203.png)



10. **중요한 내부발행객체 `Memento`는 외부인 CareTaker는 자기 속에 저장만 가능하고, `같은 패키지내 Originator만, 생성 + 내부정보 제공받기getter`가 가능해야한다.**

    - **memento의 `생성자를 포함한 모든 메서드를 protected`로 만든다. **

      ![image-20220720231335709](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720231335709.png)

    - **패키지 밖인, client에서 memento를 아예 생성도 못하고 빨간줄이 뜬다.**

      ![image-20220720231408990](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220720231408990.png)

    - protected는 `상속자식 + 같은패키지만 접근가능`하다.