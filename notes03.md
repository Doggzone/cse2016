```
(c)도경구 
version 1.01 (2022/09/13)
version 1.02 (2022/09/16) 숙제 오타 수정 및 샘플 코드 추가
version 1.03 (2022/09/26) 숙제 모범답안 코드 추가
```

## 3. 클래스와 메소드

### 3-1. 개념 정리

#### 클래스와 객체

Java 애플리케이션 프로그램은 하나 이상의 클래스(class)로 구성한다. 파일별로 클래스를 하나씩 담을 수 있고, 클래스의 이름은 파일이름과 동일해야 한다. 클래스의 이름이 `MyClassName`이면, 그 클래스를 담고 있는 파일의 이름은 `MyClassName.java`으로 지어야 한다. 클래스의 이름은 변수 이름과는 달리 대문자로 시작하고 단어의 후속 문자는 소문자로 쓰는게 관례이다. 두 단어 이상을 이어붙이는 경우 `MyClassName`의 사례에서 볼 수 있듯이 각 단어의 첫 문자는 대문자로 쓴다.

클래스는 다음과 같은 형식으로 작성한다.

```
public class MyClassName {
    // 이 부분에 클래스의 몸체를 이루는 코드를 작성한다.
}
```

`public`은 미리 예약해둔 키워드로서, 작성한 클래스는 공개되므로 누구나 사용할 수 있다는 표시이다.

클래스는 객체(object)를 만드는 일종의 형판(template)이라고 할 수 있다. 메모리 상에 거주하는 실체(instance)로 만들기 위해서는 다음과 같은 형식으로 객체를 만들어야 한다.

```
new MyClassName()
```

이 코드를 실행하면 `MyClassName` 객체를 하나 만들어 메모리에 올려놓고, 그 주소를 결과값으로 내준다. 만든 객체를 추후에 쓰려면 다음과 같이 그 주소를 저장할 `MyClassName` 타입의 변수를 선언하고 그 변수에 저장해두어야 한다. 

```
MyClassName my_object_name;
my_object_name = new MyClassName();
```

#### 메소드

클래스 내부를 구성하는 핵심 부품의 하나인 메소드(method)는 클래스가 생성하는 객체가 지니고 있는 기능(능력)을 정의한다. 일종의 <u>함수 요약(function abstraction)</u> 이라고 할 수 있는데, 다음과 같은 형식으로 작성한다.

```
public class MyClassName {

    public <return_type> myMethodName(<type_1> par_1, ..., <type_n> par_n) {
        // 이 부분에 메소드의 몸체를 이루는 코드를 작성한다.

}
```

메소드 이름은 `myMethodName`과 같이 소문자로 시작하여 모두 소문자로 쓰되, 두 단어 이상을 이어붙이는 경우 둘째 단어부터 각 단어의 첫 문자는 모두 대문자로 쓴다. 메소드 이름을 가운데 두고 왼쪽에는 이 메소드의 계산 결과 타입을 명시하고, 오른쪽에는 괄호 안에 <u>파라미터(parameter)</u> `par_1`, ..., `par_n`을 0개 이상 변수선언과 같은 형식으로 나열한다.

이 메소드를 호출하려면 메소드가 소속되어 있는 객체를 다음과 같은 형식으로 지정해주어야 한다.

```
MyClassName my_object_name = new MyClassName();
<return_type> result = my_object_name.myMethodName(<exp_1>, ..., <exp_n>);
```

객체를 변수에 지정하지 않고 만들자 마자 바로 메소드를 호출할 수도 있다.

```
<return_type> result = new MyClassName().myMethodName(<exp_1>, ..., <exp_n>);
```

나열한 식, `<exp_1>`, ..., `<exp_n>`을 <u>인수(argument)</u>라고 하는데, 이 때 인수의 개수는 해당 메소드의 파라미터의 개수와 같아야 한다. 아울러 메소드를 호출하면 인수 식을 차례로 모두 계산하여, 그 결과를 같은 위치의 파라미터 변수에 지정한 상태에서 해당 메소드의 몸체를 실행한다. 메소드가 내주는 값은 메소드 몸체 내부에 작성해둔 `return <exp>`이 결정하는데, 이 문장을 실행하면 `<exp>`를 계산할 결과를 메소드 호출의 결과로 내주게 된다. 이 때 내주는 결과 값의 타입은 메소드에 명시한 `<return_type>`과 일치해야 한다.

메소드 맨앞에 붙이는 `public`은 외부에 공개한다는 의미로, 해당 메소드는 객체의 외부에서 호출하여 사용할 수 있다. 반대로 `private`이라고 쓰면 클래스 내부용이라는 의미로, 객체의 외부에 노출이 되지 않아 내부에서만 호출하여 사용할 수 있다.

메소드 몸체에 `return <exp>`이 없으면 내주는 값이 없게 되는데, 이 경우 `<return_type>`은 `void`로 쓴다.

이제 클래스와 메소드의 작성 방법과 의미를 간단히 살펴보았으니, 간단한 Java 애플리케이션을 설계하고 구현할 준비가 되었다.
지난 시간에 공부한 간단한 애플리케이션을 가져다, MVC 아키텍처를 기반으로 설계도를 작성하고, 그 설계도에 기반하여 애플리케이션을 재작성하면서 설계와 구현의 개념을 이해해보자.


### 3-2. MVC 아키터처 기반 객체지향 애플리케이션 설계

애플리케이션은 클래스를 모아놓은 것이다. 클래스를 구현하기 전에 설계도부터 작성한다. 설계도를 작성하는 방법은 다양하겠지만, 이 수업에서는 가장 널리 쓰고 있는 MVC(Model-View-Controller) 아키텍처를 기반으로 설계도를 작성하도록 한다.

설계도를 <u>클래스 다이어그램(class diagram)</u>이라고 하는데, 필요한 부품인 클래스를 정하고 클래스 사이의 사용 관계를 표시하여 작성한다. 설계도에서 클래스는 다음과 같은 형식으로 작성한다.

<img src="https://i.imgur.com/BB2koOX.png" width="500">

상단에 클래스의 이름을 적고, 하단에 클래스에 정의할 메소드의 헤더(header)를 나열한다.

MVC 아키텍처에 기반한 설계도에서 클래스는 다음과 같이 4 종류로 구분한다.

<img src="https://i.imgur.com/f040wTm.png" width="400">

각 클래스는 다음과 같은 방식으로 연결하여 클래스 다이어그램을 그릴 수 있다.

<img src="https://i.imgur.com/BwMkxcK.png" width="500">

화살표는 사용 관계를 나타낸다. `Starter` 클래스는 애플리케이션 실행을 위하여 시동을 거는 역할을 하는 클래스로, 실행을 위해 필요한 객체를 만들어 메모리에 등장시키고 실행의 매니저 역할을 `Controller` 클래스에 넘긴다. `Controller`는 애플리케이션의 실행을 지휘하는 역할을 담당한다. 따라서 `Model`과 `View` 클래스를 사용하여 실행 업무를 수행하도록 기획한다. `Model` 클래스는 핵심 계산을 수행하는 두뇌 역할을 담당하는데, 실제 계산을 수행하는 선수라고 할 수 있다. 당연히 필요에 따라 여러 개를 만들 수도 있다. `View` 클래스는 외부와의 소통 창구 역할을 담당한다. 필요하면 입력 클래스와 출력 클래스를 따로 둘 수 있다.

MVC의 각 역할을 대략 이해했으니 이제 사례를 통해서 객체제향 애플리케이션을 설계하는 방법을 습득해보자.

### 3-3. 사례 학습: (뒤늦은) 설계

#### 사례 #1 : 인삿말 보여주기 - `HelloWorld`

#### 콘솔 버전

```
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

}
```

#### Swing 버전

```
import javax.swing.*;

public class HelloWorld {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Hello, World!");
    }

}
```

#### 클래스 다이어그램

<img src="https://i.imgur.com/jYUxvnf.png" width="500">


#### 구현

```
public class Model {

    public String createMessage() {
        return "Hello, World!";
    }

}
```

```
import javax.swing.*;

public class View {

    public void show(String message) {
        System.out.println(message);
    }

    public void showWindow(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
```

```
public class Controller {

    public void control(Model m, View v) {
        String message = m.createMessage();
        v.show(message);
        v.showWindow(message);
    }

}
```

```
public class HelloWorld {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        new Controller().control(model, view);
    }

}
```




#### 사례 #2 : 현재 시각 알려주기 - `Clock`

#### 콘솔 버전

```
import java.time.*;

public class Clock {

    public static void main(String[] args) {
        System.out.println(LocalTime.now());
    }

}
```

#### Swing 버전

```
import java.time.*;
import javax.swing.*;

public class Clock {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, LocalTime.now());
    }

}
```

#### 클래스 다이어그램

<img src="https://i.imgur.com/BGcO0Zq.png" width="500">

#### 구현

```
import java.time.*;

public class Model {

    public LocalTime whatTimeIsIt() {
        return LocalTime.now();
    }

}
```

```
import java.time.*;
import javax.swing.*;

public class View {

    public void show(LocalTime t) {
        System.out.println(t);
    }

    public void showSwing(LocalTime t) {
        JOptionPane.showMessageDialog(null, t);
    }

}
```

```
import java.time.LocalTime;

public class Controller {

    public void control(Model m, View v) {
        LocalTime t = m.whatTimeIsIt();
        v.show(t);
        v.showSwing(t);
    }

}
```

```
public class Clock {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        new Controller().control(model, view);
    }

}
```



#### 사례 #3 : 원의 면적 구하기 - `Circle`

#### GUI 버전

```
import java.text.*;
import javax.swing.*;

public class Circle {
    public static void main(String[] args) {
        String message = "반지름을 주시면 원의 면적을 계산해드립니다.";
        String input = JOptionPane.showInputDialog(message);
        int radius = Integer.parseInt(input);
        double area = Math.PI * Math.pow(radius,2);
        DecimalFormat f = new DecimalFormat("0.00");
        System.out.print("반지름이 " + radius + "인 원의 면적은 ");
        System.out.println(f.format(area) + " 입니다.");
    }
}
```

#### 클래스 다이어그램

<img src="https://i.imgur.com/E7ehIQ2.png" width="500">



#### 클래스 다이어그램 (개선)

<img src="https://i.imgur.com/HMg7uZQ.png" width="500">



#### 구현

```
public class Model {

    double areaCircle(double radius) {
        return Math.PI * Math.pow(radius, radius);
    }

}
```

```
import javax.swing.*;

public class ViewIn {

    public int getNumber(String message) {
        String input = JOptionPane.showInputDialog(message);
        return Integer.parseInt(input);
    }

}
```

```
import java.text.*;
import javax.swing.*;

public class ViewOut {

    public void show(int radius, double area) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        System.out.print("반지름이 " + radius + "인 원의 면적은 ");
        System.out.println(formatter.format(area) + " 입니다.");
    }

}
```

```
public class Controller {

    public void control(Model m, ViewIn i, ViewOut o) {
        int radius = i.getNumber("반지름을 주시면 원의 면적을 계산해드립니다.");
        double area = m.areaCircle(radius);
        o.show(radius, area);
    }

}
```

```
public class Circle{

    public static void main(String[] args) {
        Model model = new Model();
        ViewIn input = new ViewIn();
        ViewOut output = new ViewOut();
        new Controller().control(model, input, output);
    }

}
```


### 실습


#### 실습 1. 크리스마스까지 몇달 며칠 남았을까?

오늘부터 크리스마스까지 몇달 며칠이 남았는지 다음과 같은 형식으로 메시지 대화창에 프린트하는 자바 애플리케이션 `Calandar`를 아래 MVC 아키텍처 기반 설계도에 맞추어 구현하자.

```
금년 크리스마스까지 3달하고 15일 남았다!
```

#### 설계도: 클래스 다이어그램

<img src="https://i.imgur.com/4Q1kgfJ.png" width="600">

#### 구현 가이드

```
import java.time.*;

public class Calendar {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        LocalDate xmas = LocalDate.of(year,12,25);
        Period p = Period.between(today,xmas);
        int months = p.getMonths();
        int days = p.getDays();
        String message = "크리스마스까지 " + months + "달 " + days + "일 남았다.";
        System.out.println(message);
	}

}
```

-	`java.time` 패키지에서 제공하는 `LocalDate`는 특정 날짜를 년,월,일로 구분하여 처리할 수 있는 다양한 기능을 갖춘 클래스이다.
	-	오늘 날짜 갖고 있는 `LocalDate` 객체는 클래스소속 메소드 `LocalDate.now()`를 호출하여 만들 수 있다.
	- `LocalDate` 객체 `d`에서 년도는 메소드 `d.getYear()`와 같이 호출하여 `int` 값으로 가져올 수 있다.
	-	특정 날짜를 년,월,일을 정수로 제공하여 `LocalDate` 객체를 만들 수 있다. 클래스소속 메소드 `LocalDate.of(int year, int month, int day)`를 형식에 맞추어 호출하면 된다.
-	`java.time` 패키지에서 제공하는 `Period`는 기간을 년,월,일 단위로 구분하여 처리할 수 있는 다양한 기능을 갖춘 클래스이다.
	-	시작 `LocalDate` 객체 `from`과 종료 `LocalDate` 객체 `to`를 가지고, `LocalDate.between(from,to)`를 호출하여 두 날짜 사이의 기간 정보를 갖고 있는  `Period` 객체를 만들 수 있다.
	-	`Period` 객체 `p`가 가지고 있는 년,월,일 정보는 `p.getYears()`, `p.getMonths()`, `p.getDays()` 메소드로 각각 `int` 값으로 추출할 수 있다.
- 참고: [Java API Documentatio 링크](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/time/package-summary.html)


#### 실습 2. 몇월 며칠까지 몇달 며칠 남았는지 계산 기능 추가

이번에는 년,월,일 정보를 사용자로부터 받아서, 오늘부터 그날까지 몇년 몇달 며칠이 남았는지 다음과 같은 형식으로 메시지 대화창에 프린트하는 기능을 `Calandar` 애플리케이션에 추가하자.

```
2023년 2월 28일까지 0년 5달 16일 남았습니다.
```

#### 설계도: 클래스 다이어그램

<img src="https://i.imgur.com/e3e0Ziv.png" width="600">


### 숙제 (마감: 2022년 9월 22일 수업시간 직전)

아래 문제를 각각 주어진 설계도에 맞추어 구현하자.

#### 문제 1. 오늘부터 100일 뒤는 몇 년, 몇 월, 며칠?

오늘부터 100일 뒤는 몇 년, 몇 월, 며칠인지 알려주는 기능을 위에서 작성한 `Calandar` 애플리케이션에 추가하자.

<img src="https://i.imgur.com/2sAGnJy.png" width="600">

#### 문제 2. 특정 년,월,일부터 100일 뒤는 몇 년, 몇 월, 며칠?

이번에는 년,월,일 정보를 사용자로부터 받아서, 그날부터 100일 뒤는 몇 년, 몇 월, 며칠인지 알려주는 기능을 추가하자.

<img src="https://i.imgur.com/FChRw4j.png" width="600">

#### 문제 1번과 문제 2번을 통합한 `Controller.java` 사례

```
import java.time.*;

public class Controller {
	
    public void control(Model m, ViewIn in, ViewOut out) {
        // 문제 1
        LocalDate d = m.hundredDaysFromToday();
        out.showDate(d,"오늘부터 100일 뒤는 며칠?\n");
        // 문제 2
        LocalDate date0 = in.getDate("년, 월, 일을 차례로 입력해주세요.");
        LocalDate date100 = m.hundredDaysFrom(date0);
        out.showDate(date0, date100, " 부터 100일 뒤는 며칠?\n");
    }

}
```



#### 문제 3. 숙제#1로 완성한 애플리케이션을 MVC 아키텍처 기반으로 설계도를 작성하고 이를 바탕으로 애플리케이션을 재작성

제출 요령: LMS에서 업로드.


