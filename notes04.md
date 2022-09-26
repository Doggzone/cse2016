```
(c)도경구 version 1.0 (2022/09/19)
version 1.01 (2022/09/20) 숙제 문제 수정
```

## 4. 생성 메소드와 필드 변수

### 4-1. 생성 메소드

객체가 태어나면서 저절로 한번 실행하는 메소드를 <b>생성 메소드(constructor method)</b>라고 한다. 메소드의 이름은 소속 클래스의 이름과 동일하게 다음과 같은 형식으로 만든다.
```
public class ClassName {

    public ClassName(<type_1> par_1, …,<type_n> par_n) {
        // 몸체 코드 블록
    }

}
```
여기서 괄호 안에 나열한 `<type_1> par_1`, ..., `<type_n> par_n` 은 <b>파라미터(parameter)</b> 라고 하는데, (일반 메소드 파라미터와 마찬가지로) 변수 선언 형식과 동일하며, 0개 이상 원하는 만큼 나열할 수 있다.
다음과 같이 객체를 생성할 때 이 메소드의 몸체 코드 블록을 딱 한 번 실행한다.
```
new ClassName(<exp_1>, …, <exp_n>)
```
여기서 괄호 안에 나열한 `<exp_1>`, ..., `<exp_n>` 은 <b>인수(argument)</b>라고 하며, 파라미터의 개수와 같아야 한다. 그리고 인수 식을 계산한 결과 값의 타입은 매칭되는 파라미터에서 명시한 타입에 부합해야 한다. 그렇지 않으면 컴파일러가 불평하면서 컴파일을 거부한다. 생성 메소드를 생략하면 다음과 같이 정의해둔 것과 같다.
```
public class ClassName {

    public ClassName() { }

}
```
따라서 지금까지의 객체 생성은 모두 다음과 같은 형식이었었다.
```
new ClassName()
```

### 4-2. 사례 학습 : 아날로그 시계 만들기

Java Swing 패키지를 활용하여 그래픽 아웃풋 윈도우(graphical output window)에 아날로그 시계를 구현하기 위한 첫걸음으로, 윈도우에 시계 모양의 그래픽 도형을 그리는 `ClockWriter` 클래스를 작성하면서, 생성 메소드와 필드 변수를 실전적으로 공부해보자.  

#### 윈도우 프레임 만들기

우선 다음과 같은 모양의 빈 윈도우 프레임을 화면에 띄우는 클래스를 만들어보자.

<img src="https://i.imgur.com/v7VYA8a.png" width="400">

다음 클래스 다이어그램과 같이 `javax.swing` 패키지가 제공하는 `JFrame` 클래스를 활용하면 된다.

<img src="https://i.imgur.com/F7AcZ8k.png" width="500">

이 다이어그램에서 `uses-a` 라벨이 붙은 화살표는 "활용"하는 관계를 나타낸다. `JFrame` 클래스가 갖고 있는 데이터나 기능을 `ClockWriter` 클래스가 활용한다는 뜻이다. 객체 생성 초기에 빈 윈도우 프레임을 화면에 띄우는 생성 메소드 코드를 이 클래스 다이어그램을 참고하여 다음과 같이 작성할 수 있다.

```
import javax.swing.*;

public class ClockWriter extends JPanel {

    public ClockWriter() {
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // test code
    public static void main(String[] args) {
        new ClockWriter();
    }

}
```

이 클래스는 아웃풋 뷰에 속한다. 클래스를 작성하면서 해당 테스트해보기 위한 방안으로 위와 같이 `main` 메소드를 사용하면 편리하다. `main` 메소드 덕분에 이 클래스는 독립적으로 실행해볼 수 있다. 물론 완성 후에 `main` 메소드는 필요에 따라 제거하거나 주석처리 하면 된다.

`main` 메소드에서 `ClockWriter` 객체를 생성하면서 생성 메소드를 실행한다. 생성 메소드를 실행하면, `JFrame` 객체를 생성하고, 그 객체가 제공하는 메소드를 활용하여 윈도우 타이틀과 프레임 크기를 설정하고 윈도우를 띄운다. 여기서 윈도우 프레임의 크기를 나타내는 단위는 픽셀(pixel) 이며, 명시한 `300`과 `400`은 프레임의 가로와 세로 길이 이다.

윈도우 프레임을 만들면 애플리케이션을 실행하는 프로세스와는 별도의 실행 프로세스가 생기면서 독립적으로 작동한다. 윈도우 프레임 상단에 있는 닫기 버튼을 누르면 윈도우가 사라지고 더 이상 다시 띄울 방법은 없는데, 윈도우를 동작시키던 실행 프로세스는 그대로 남아 있다. `JFrame` 객체에 다음 메소드를 활용하면 윈도우가 닫히면서 실행 프로세스도 동시에 종료한다.   
```
frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
```
나중에 남아 있는 실행 프로세스를 수동으로 하나 하나 제거해야 하는 수고를 하지 않아도 되니 잊지 말고 활용하자.

####  윈도우에 글쓰기

윈도우 프레임을 띄웠으니 이제 윈도우 프레임에 다음과 같은 위치에 문자열 텍스트 `TIME IS GOLD`를 디스플레이하는 클래스를 만들어 보자.

<img src="https://i.imgur.com/q3K7ppl.png" width="400">

윈도우 프레임에 뭔가를 디스플레이 하려면 그래픽을 그릴 능력을 갖추어야 한다. 그런데 다행히도 `javax.swing` 패키지에 그런 능력을 갖춘 클래스인 `JPanel` 클래스가 준비되어 있다. 이 클래스를 `ClockWriter` 클래스의 목적에 맞게 활용하기 위해서는 `JPanel` 클래스가 갖고 있는 모든 것을 물려받아 쓰면 된다. 이 관계를 클래스 다이어그램을 그려서 표현하면 다음과 같다.

<img src="https://i.imgur.com/ZtEqxv6.png" width="700">

이 다이어그램에서 `has-a` 라벨이 붙은 화살표는 "상속"하는 관계를 나타낸다. `JPanel` 클래스가 갖고 있는 데이터와 기능을 모두 `ClockWriter` 클래스가 갖는다. 즉, 모두 상속(물려)받는다는 뜻이다. `JPanel` 클래스는 윈도우를 화면에 띄워서 활성화 할 때마다 저절로 실행되는 `paintComponent` 메소드를 갖고 있는데, 이를 물려받는다.
물려 받았지만, 화면에 디스플레이할 그래픽 도형을 `paintComponent` 메소드에 새로 구현할 수 있다. 상속받은 메소드와 새로 만든 메소드가 사양이 똑같으므로 충돌이 생기는데, 이 경우를 메소드가 중첩(override, 덧씌우기) 선언되었다고 한다. 메소드가 중첩되면 상속받은 메소드는 무시하고, 새로 만든 메소드가 사용된다. 따라서 `paintComponent` 메소드에 그래픽 도형을 디스플레이하는 코드를 작성해두면, `ClockWriter` 객체가 만든 윈도우가 새로 띄워질 때마다 이 메소드가 저절로 실행된다.  

`javax.swing` 패키지의 `JPanel` 클래스를 상속받아 윈도우 프레임에 다음과 같은 위치에 문자열 텍스트 `Java!`를 디스플레이하는 클래스 `ClockWriter`를 구현하면 다음과 같다.

```
import javax.swing.*;
import java.awt.*;

public class ClockWriter extends JPanel {

    public ClockWriter() {
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(300, 400);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("TIME IS GOLD", 105, 50);
    }

    // test code
    public static void main(String[] args) {
        new ClockWriter();
    }

}
```

먼저 `JPanel` 클래스를 상속받음을 클래스의 헤더에 다음과 같은 형식으로 기술한다.
```
public class Canvas extends JPanel
```
그리고 윈도우 프레임에 그래픽을 디스플레이하려면 도화지 역할을 하는 `ClockWriter`(`JPanel`) 객체를 담아서 프레임에 끼울 `Container` 객체가 필요하다. 위 프로그램에서 `frame.getContentPane()`와 같은 형식으로 `JFrame` 객체 `frame`에 요청하면 `Container` 객체를 하나 만들어 내준다. 이 `Container` 객체에 `ClockWriter`(`JPanel`) 객체를 담는 작업은 연이어 기술한 `.add(this)`가 해준다. `this`를 `Container` 객체에 담아 달라는 요청이다. 여기서 `this`는 미리 정해둔 키워드로 객체 자신을 나타낸다. 생성된 `ClockWriter` 객체 스스로를(자신을) 가리키는 이름이다. 이 시점이 되면 이제 도화지(`JPanel`)를 `Container`에 담아 프레임에 끼워 넣었으니 그래픽 도형을 그릴 준비가 완료된다.

도화지에 어떤 도형을 그릴지는 `paintComponent` 메소드에 구현한다. 여기 인수로 받는 `Graphics`  `g`는 다음 그림과 같이 `java.awt` 팩키지에 준비되어 바로 활용할 수 있는데, 도형을 그리는 펜 역할을 한다고 생각하고 활용하면 된다.

<img src="https://i.imgur.com/Hgu62BU.png" width="700">

그리고 `java.awt` 팩키지에는 색깔 정보를 갖고 있는 `Color` 클래스도 준비되어 있으니 그대로 다음과 같이 가져다 활용할 수 있다. `Color.BLUE`는 파란색을 나타내고, 다음과 같이 호출하여 펜 `g`의 색깔을 파란색으로 설정한다.
```
g.setColor(Color.BLUE);
```
이어서 `drawString` 메소드를 다음과 같이 호출하여 문자열 "TIME IS GOLD"를 디스플레이 한다.
```
g.drawString("TIME IS GOLD", 105, 50);
```
여기서 `105`과 `50`은 위치 좌표를 나타내는데, 패널에서 원점 `(0, 0)`은 다음 그림에서 표시한대로 패널의 왼쪽 위 꼭지점이다. 즉, 문자열을 새길 위치는 원점에서 x축으로(가로로) 105 픽셀, y축으로(세로로) 50 픽셀 지점으로, 문자열의 좌상단을 가리킨다.

<img src="https://i.imgur.com/AnIjm0f.png" width="400">


#### 도형 그리기 - 직사각형, 타원(원)

이번에는 윈도우에 다음과 같이 직사각형을 그려보자.

<img src="https://i.imgur.com/NwQFPqm.png" width="400">

이를 구현하는 코드는 다음과 같다.

```
g.setColor(Color.CYAN);
g.fillRect(25, 100, 250, 250);
```

먼저 펜의 색깔을 `Color.CYAN`로 바꾸고, `fillRect` 메소드를 호출하여 직사각형을 그리고 전체를 그 색깔로 채운다. 첫 두 인수는 사각형 좌상단의 좌표를 나타내고, 남은 두 인수는 직사각형의 가로, 세로 길이를 나타낸다.

이어서 다음과 같이 같은 크기의 파란색 직사각형을 그려서 테두리를 그려보자.

<img src="https://i.imgur.com/EUFXeXJ.png" width="400">

다시 펜의 색깔을 바꾸고, 이번에는 같은 인수로 `drawRect` 메소드를 다음과 같이 호출하여 직사각형을 그릴 수 있다.

```
g.setColor(Color.BLUE);
g.drawRect(25, 100, 250, 250);
```

이번에는 원을 그리고 내부를 다음과 같이 회색으로 채워보자.

<img src="https://i.imgur.com/14WHdIn.png" width="400">

위의 원은 타원을 그려주는 `fillOval` 메소드를 호출하여 그릴 수 있다.

```
g.setColor(Color.LIGHT_GRAY);
g.fillOval(25, 100, 250, 250);
```

각 메소드 호출에서 인수가 어떤 의미를 가지는지 하나씩 알아보자. 도형을 그리는 `fillRect`, `drawRect`, `fillOval` 메소드의 첫 두 인수는 도형의 위치 x와 y 좌표, 다음 두 인수는 도형의 크기를 나타낸다. 메소드 이름 앞 부분의 `fill`은 도형을 펜 색깔로 채우라는 뜻이고, `draw`는 도형의 테두리를 펜 색깔로 그리라는 뜻으로 이해하면 된다. 메소드 이름 뒷 부분의 `Rect`는 직사각형, `Oval`은 타원을 의미한다. 도형을 그릴 위치는 원칙적으로 도형의 왼쪽 위 꼭지점을 가리키는데 도형의 모양에 따라 방식이 다르다. 사각형의 경우, 첫 두 인수는 사각형의 좌상단 꼭지점의 x 좌표, y 좌표이다. 그리고 다음 두 인수는 각각 사각형의 가로, 세로의 길이이다. 타원의 경우 첫 두 인수는 타원을 최대한 맞게 둘러싼 사각형의 좌상단 꼭지점의 x 좌표, y 좌표이다. 그리고 다음 두 인수는 각각 타원의의 가로 지름, 세로 지름의 길이이다. 자연히 가로 지름과 세로 지름이 같으면 원이 된다.


####  선 그리기

선도 그릴 수 있다. 양쪽 끝의 좌표를 다음과 같이 주면,
두 좌표를 잇는 선을 그린다.

```
g.setColor(Color.RED);
g.drawLine(150, 225, 150, 100);
```
두 좌표, `(150,225)`와 `(150,100)`을 잇는 선을 다음과 같이 그린다.
<img src="https://i.imgur.com/bxA30Yx.png" width="400">

이 빨간선은 원의 중심에서 원주까지를 이은 선이다. 원주율을 활용하여 원의 중심에서 원주의 원하는 지점까지 잇는 선을 다음 코드로 그릴 수 있다.

```
int x1 = 150;
int y1 = 225;
int x2 = x1 + (int)(125 * Math.cos(Math.PI / 6));
int y2 = y1 + (int)(125 * Math.sin(Math.PI / 6));
g.drawLine(x1, y1, x2, y2);
```

<img src="https://i.imgur.com/JxLYIXB.png" width="400">


이와 같은 방식으로 시계의 침을 그릴 수 있다.



#### 사례 학습 : 아날로그 시계 그리기 (시계판 그리기)

지금까지 공부한 내용을 바탕으로 현재 시각을 표시하는 아날로그 시계를 만들어보자. 일단 지금까지 배운 지식으로 시계 바늘이 없는 시계를 그리는 코드를 작성하면 다음과 같다.

```
import javax.swing.*;
import java.awt.*;

public class ClockWriter extends JPanel {

    public ClockWriter() {
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(300, 400);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("TIME IS GOLD", 105, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(25, 100, 250, 250);
    }

    // test code
    public static void main(String[] args) {
        new ClockWriter();
    }

}
```

이 애플리케이션을 실행하면 다음과 같은 모양의 윈도우가 생긴다.

<img src="https://i.imgur.com/A6RN57c.png" width="400">

#### 필드 변수 추가

위 프로그램에서 시계의 크기는 고정되어 있다. 시계의 크기를 조정하고 싶으면, 길이, 너비 등 관련 값들을 모두 찾아서 다시 고쳐야 한다. 크기의 기준 값을 변수로 기억해두고 시계를 그 변수 크기를 기준으로 비율에 맞게 그리도록 하면, 시계 크기를 조정하고 싶을 때 기준값만 변경하면 된다. 이러한 기준 값 역할을 하는 변수는 객체가 영구히 기억하고 있어서 필요할 때마다 수정 또는 참조할 수 있어야 한다. 이런 역할을 하는 변수를 따로 두고 지정해야 하는데 이를 필드(field) 변수라고 하며 메소드의 외부에 다음 코드와 같이 선언하여 사용한다. 필드 변수는 객체의 상태를 기억하고 있는 변수라고 하여, 상태 변수라고도 한다. 이 애플리케이션에서 크기의 기준을 기억하고 있는 필드 변수 `SIZE`를 두면, 이 클래스를 사용하는 측에서 객체를 만들면서 원하는 크기를 지정하도록 할 수 있다. 이 값은 객체를 생성할 때 `new ClockWriter(250)`과 같이 생성 메소드에 인수로 전달하여 설정한다.

```
import javax.swing.*;
import java.awt.*;

public class ClockWriter extends JPanel {

    private final int SIZE;

    public ClockWriter(int n) {
    	SIZE = n;
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(SIZE+50, SIZE+150);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("TIME IS GOLD", 105, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(25, 100, SIZE, SIZE);
    }

    // test code
    public static void main(String[] args) {
        new ClockWriter(250);
    }

}
```

#### `final` 변수

- 값 지정 후 변경 불가
- 이름은 모두 대문자로 씀


#### 필드 변수와 지역 변수의 유효 범위 (scope)

<img src="https://i.imgur.com/6XLZFeq.png" width="600">


#### 사례 학습 : 아날로그 시계 그리기 (시침, 분침 그리기)

<img src="https://i.imgur.com/93UxbGL.png" width="400">

```
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class ClockWriter extends JPanel {

    private final int SIZE;

    public ClockWriter(int n) {
        SIZE = n;
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(SIZE+50, SIZE+150);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("TIME IS GOLD", 105, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(25, 100, SIZE, SIZE);
        // 현재시간 알아보기
        LocalTime now = LocalTime.now();
        // 시계 중심
        int radius = SIZE / 2;
     	int x1 = 25 + radius;
     	int y1 = 100 + radius;
     	// 분침 그리기
     	radius -= 30;
     	double minute_angle = (now.getMinute() - 15) * Math.PI / 30;
     	int x2 = x1 + (int)(radius * Math.cos(minute_angle));
     	int y2 = y1 + (int)(radius * Math.sin(minute_angle));
     	g.setColor(Color.RED);
     	g.drawLine(x1, y1, x2, y2);
     	// 시침 그리기
     	radius -= 30;
     	double hour_angle = (now.getHour() - 3) * Math.PI / 6 + minute_angle / 12;
     	x2 = x1 + (int)(radius * Math.cos(hour_angle));
     	y2 = y1 + (int)(radius * Math.sin(hour_angle));
     	g.setColor(Color.YELLOW);
     	g.drawLine(x1, y1, x2, y2);
    }

    // test code
    public static void main(String[] args) {
        new ClockWriter(250);
    }
    
}
```



### 실습 : 아날로그 시계 - 자라는 동심원

- 창을 최소화 했다가 다시 띄울때마다 핑크색 원이 일정 비율로 커짐
- 전체를 채운 후에는 다시 처음으로 돌아가 같은 패턴을 반복

<img src="https://i.imgur.com/zyspdEP.png" width="600">


### 숙제 : 아날로그 시계 - 기능 추가

1. 아날로그 시계의 초를 동심원의 크기로 나타내자.
   - 시계판의 반지름이 `r`일때, `t`초에서 동심원의 반지름은 `r*t/60`이다.
2. 시계에 눈금을 추가한다.
   - 디자인은 자유이다.
   - 도형을 사용하여 눈금을 표시해도 좋고, 숫자를 넣어도 좋다.
