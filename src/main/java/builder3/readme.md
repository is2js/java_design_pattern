## 빌더 패턴3
- 목적: 요즘 트렌드에 맞게 설계된 빌더패턴을 사용하기 

- 설계:
    - **정적메소드로 최초 Builder객체를 만드는 class를 `해당 객체 내부 public static class Builder`로 만든다.**
        - **이름은 Builder로 고정하여, `new` 객체Class`.Builder()` 호출시 생성자가 호출되게 한다.**
        - `new 객체Class.builder()`의 `정적(static) 메서드로 최초 객체를 생성`하는 것은 동일하지만, `별도의 Class가 아니라 해당 객체Class로 내부의 정적클래스를 사용`하는 것이 차별점이다.
            - **Builder의 생성자를 호출하려 객체를 생성하는 경우, inner의 public static class라도 `new를 맨앞에 달아야`한다**
        - 같은 class에 정의되어있다면 객체 생성자에서는 builder.인자1, 인자2로 꺼내서 객체 필드를 초기화하는 것이 가능하다.
        - 이 때, `new 객체Class.builder()`는  static메서드 같아보이지만, **유틸메서드가 아니라 응답값 없이 정의되어  `public static class Builder`의 객체 생성자를 호출하는 것이므로 앞에 `new`가 붙인체로 호출된다.**
        - **my) `내부 static 클래스 Builer의 객체 생성`은 `new`외부클래스`.클래스명의 생성자()`의 static 과 유사하게 호출된다.**
    - builder3는 각 field를 default + setter를 이용하는 방식이다. 
        - builder2에서는 객체를 default로 초기화하고 setter로 객체.setter를 호출하여 필드값을 넣었지만,**builder3에서는 객체의 field들을 builder class에서도 그대로 가진다.**
    - builder는 setter방식이기 때문에 선언시 default값을 넣어두자.
        - 선택적인 인자는, builder로 체이닝안할 때 default값이 들어가도록
        - builder속 setter메서드명은 field값과 동일하게 준다.
    - **마지막 build()를 통해 모아둔 field들을 객체인자로 전달하는 방식은**
        1. builder들을 객체 생성자에 건네준다.
        2. 객체 생성자에서는 builder.인자1, 인자2로 꺼내서 객체 필드를 초기화해준다.
            - **같은 class내에 있기 때문에 가능하다.**
        


### 적용

1. **bulider패턴을 적용할 객체 및 객체 field들을 아래와 같이 미리 `메서드 체이닝`한다.**
    - static메서드 같이 보이는 생성자 `new` 객체클래스`.Builder()`를 호출해 내부 public static Builder의 객체가 생성되게 한다.
        - **이 때, static 같아 메서드지만, 내부 public static class의 `class명이 Builder`로 고정되어, Builder클래스 객체를 생성하는 생성자역할을 한다.**
            - builder는 default값 필드 + settter 방식을 사용하기 때문에 **field에 final을 달지 말아야한다.**
        - **builder class가 가질 필드값중에 선택적매개변수가 아니라면 이 builder에서 생성자에서 받으면 된다.**
    ![20220614171044](https://raw.githubusercontent.com/is2js/screenshots/main/20220614171044.png)

2. 체이닝한 객체 및 내부 Builder class 만들어가기
    1. 객체class를 만든 뒤, `객체의 field`들은 직접 작성해줘야한다.
    2. 체이닝된 new 객체Class.Builder()의 빨간줄은 자동으로 inner `public static class Builder`의 생성을 유도한다.
        - 객체와 동일한 field들을 선언해주되 `with default값`을 가지게 만들어주어 `default값 field+setter`형식을 따르게 한다.
        - 만일 필수로 받아야하는 파라미터가 있다면, Builder생성자에서 받도록 해주고 초기화없이 final로 필드를 선언한다.
    ![d0ecc1fe-c660-47d2-90a9-8f76dbbe1567](https://raw.githubusercontent.com/is2js/screenshots/main/d0ecc1fe-c660-47d2-90a9-8f76dbbe1567.gif)

3. 이제 먼저 생성된 builder객체에 체이닝된 (setter)메서드들을 만들어준다.
    - **마지막 객체반환(.build())전까지는 `setter후 계속해서 같은 builder객체(return this)를 반환`하도록 해주어, 같은 class에 정의된 메서드들을 변화가 반영된 builder객체(this)로 이어나간다.**
    ![7241aaa7-454b-4f6b-a216-b3356d83fc98](https://raw.githubusercontent.com/is2js/screenshots/main/7241aaa7-454b-4f6b-a216-b3356d83fc98.gif)


4. **마지막 .build()에서는 `computer객체를 생성자로 만들어서 반환`해줘야하는데, 객체는 builder객체를 받아서 뽑아서 필드들을초기화해주기로 했으니, `this`를 넘겨줘 생성자를 생성해준다.**
    ![e941751a-90a5-42a5-a0fa-72399fff77b6](https://raw.githubusercontent.com/is2js/screenshots/main/e941751a-90a5-42a5-a0fa-72399fff77b6.gif)

5. **builder패턴의 장점으로 default값에 setter하는 것인데, `특정 필드만 setter를 호출하면 다른 필드들은 default값으로 들어오는지 확인해보자.`**
    - **builder패턴을 적용한 객체 생성은 `new 객체Class.Builder() ~ .build()`를 통해 해야하는 것만 익숙해지면 될 것 같다.**
    ![6a776be9-a690-46ea-acc7-b2323c7aee9c](https://raw.githubusercontent.com/is2js/screenshots/main/6a776be9-a690-46ea-acc7-b2323c7aee9c.gif)