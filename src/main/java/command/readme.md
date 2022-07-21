## XXX 패턴

- 목적: 커맨드 패턴을 통해 **명령을 객체화** 할 수 있다.
- 실목적: 


- 기본 설계

   - command 인터페이스만으로 구현할 수 있다.
  ![image-20220721115442796](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721115442796.png)
   - 그외 인보크, 리시버들도 있을 수 있다.

   - **전략패턴과 비슷해 보이지만, `명령자체가 객체화`되어서**
     - 객체를 다른 객체에 넘겨줄 수 있고
     - 대기를 한 후에 실행
     - 순서나 우선순위 높은 것부터 실행하는 모델을 만들 수 있다.
   
   ![image-20220721130104796](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721130104796.png)

### 적용

#### 전략패턴과 차이점이 없는 경우 (전략객체 대신 익클로 분신술 실시간 구현)

1. command 인터페이스와 execute 오페레이터를 만든다.
   ![image-20220721120625521](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721120625521.png)

2. **clinet에서** command 인테페이스를 구현한 **구상체들을 담아놓을 linkedlist를 만든다. queue에 담아도 된다.**

   - 추상체변수가 제네릭에 들어간 `List<Command> list`에 담아야한다.

    ![image-20220721120757421](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721120757421.png)

3. list에 **add할 구상체를, 인터페이스를 추상체로 간주하여 실시간 익명클래스로 구현**해도 된다.

   - 전략메서드(오퍼레이터)를 소유한 인터페이스도, 훅메서드-추상클래스처럼 익클 분신술 실시간 구현이 가능하다.

   ![fdaa70b3-e081-4950-9e22-f204ff233d15](https://raw.githubusercontent.com/is3js/screenshots/main/fdaa70b3-e081-4950-9e22-f204ff233d15.gif)

   ![image-20220721120948964](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721120948964.png)

4. 익명클래스로 구현하여 저장한 구상체들을 반복문을 돌면서 실행(print)해본다.

   ![image-20220721121035469](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721121035469.png)





#### 전략객체와 다르게, 상태(필드)를 가지는 객체로서, 상태를 이용한 comparable 구현으로 우선순위 큐에 넣을 수 있는 커맨드 객체

1. command를 우선순위 큐에 넣어볼 것이다.
   ![image-20220721122516385](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721122516385.png)

   - **우선순위큐에 들어갈 객체는, `추상체Command가 Comparable<Command>를 extends로 구현하고, 자식 구상체들에서 compareTo를 구현`하도록 해야한다.**

     - 구상클래스의 impl로 구현시, 구상클래스에서 바로 compareTo를 구현해야했지만

     - **추상체에서는 `Comparable<추상체>`를 extends로 구현하고 -> 실제 `compareTo 오버라이딩 구현`은 구상체들에게 물려진다.**

       ![7488cf04-2a73-4567-b1c0-df06f1da3bc6](https://raw.githubusercontent.com/is3js/screenshots/main/7488cf04-2a73-4567-b1c0-df06f1da3bc6.gif)

       ![image-20220721123517698](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721123517698.png)

       ![image-20220721123534617](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721123534617.png)

   

2. Comparable & 커맨드인터페이스 구상체들은 **우선, string으로 command를 입력받아 생성되도록 할 것이다. 또한 compareTo할만한 정보를 필드로 가지고 있어야한다. String필드라면, 비교할 것이 .length()밖에 없다.**

   1. 구상체들은 외부에서 **String을 받아서 필드로 저장해놓고 상태**를 가지게 된다.**이 상태를 가지고 있어야 비교를 하든 뭘 하든 한다.**

      - 즉, **compareTo할 상태가 미리 존재해야하므로 먼저 필드부터 정의**한다.

      ![7476bc14-9793-4fc0-ba42-203c83cc54d9](https://raw.githubusercontent.com/is3js/screenshots/main/7476bc14-9793-4fc0-ba42-203c83cc54d9.gif)

      ![image-20220721123817792](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721123817792.png)

   2. **문제는, 추상체에 comparable이 달리는 바람에 `구상체끼리 비교가 아니게 되서, 파라미터에는 추상체가 날아온다` -> `구상체의 필드에 접근못한다` -> `필드를 이용한 비교가 안된다` **

      - 그래서 다운캐스팅을 먼저 해주고, 비교하도록 함.
      - **그냥 다음에는, 비교대상인 `구상체에 comparable을 달아주는 방식`으로 할 것 같음.**

      ![image-20220721124715621](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721124715621.png)

3. execute도 정의해준다.

   ![image-20220721125322349](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721125322349.png)

4. 우선순위 queue에 길이가 서로 다르게 커맨드 객체를 만들어서 넣어준다.
   ![image-20220721124850879](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721124850879.png)

5. 막 넣었지만, 넣은 순서가 중요한 것이 아니므로, 출력해보면, **전략객체와 달리, 상태값을 이용한 우선순위대로 커맨드가 실행된다.**

   - **우선순위는 compareTo의 작은 것부터 실행된다.**

   ![image-20220721125520349](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721125520349.png)

   - 만약,**역순으로 출력하고 싶으면, compareTo의 비교인자들의 순서를 바꿔준다.**

   ![image-20220721125601586](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220721125601586.png)

   