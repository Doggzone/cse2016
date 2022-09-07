```
(c)도경구 version 0.9 (2022/09/07)
```

## 2. 식, 타입, 변수

### 2-1. Java 기본타입 값의 종류 및 표현

<img src="https://i.imgur.com/51hFP5e.png" width="700">

- 논리값(`boolean`)은 `true`, `false` 두 값만 있다. 따라서 이 값을 저장하는데 필요한 공간은 1 bit 이지만, Java에서 다루는 데이터의 최소 단위가 1 byte (= 8 bit) 이기 때문에 낭비에도 뷸구하고 1 byte의 공간을 사용한다.
- 문자(`char`)는 '\u0000' \~ '\uFFFF' 범위의 유니코드(Unicode) 문자를 모두 포함하기 위해서 2 byte (= 16 bit)의 공간을 사용한다.
- 정수는 허용 범위에 따라 4가지 타입, `byte`, `short`, `int`, `long` 으로 구분한다. 별도의 언급이 없으면 정수는 모두 `int` 타입으로 인식하여 4 byte (= 32 bit)의 공간을 사용한다. `byte`, `short`은 해당 타입으로 변수를 선언해야 해당 크기의 공간을 할당한다. 거대 정수를 표현하기 위해서 `long` 타입으로 쓰려면 끝에 반드시 `L` 또는 `l`을 붙여야 한다. 
- 실수는 허용 범위에 따라 2가지 타입, `float`, `double` 로 구분한다. 별도의 언급이 없으면 실수는 모두 `double` 타입으로 인식하여 8 byte (= 64 bit)의 공간을 사용한다. 공간 절약을 위해서 `float` 타입으로 쓰려면 끝에 반드시 `F` 또는 `f`를 붙여야 한다.


### 2-2. 식

식(expression)은 계산하여 정상적으로 끝나면 값을 결과로 내주는 프로그램 구조이다. 식을 계산하는 도중 예외상황이 발생하여 결과를 내주지 못하고 비정상적으로 끝날 수도 있다.

**식의 종류**

- 산술식 (arithmetic expressions)
- 논리식 (logical expressions)
- 지정식 (assignment expressions)
- 조건식 (conditional expressions)

#### 산술식

산술식은 계산 결과가 정수 또는 실수가 되는 식 이다. 연산자를 가운데 또는 앞에 두고 식을 세우는데, 연산자는 다음 표와 같이 5가지가 있다.

<img src="https://i.imgur.com/Ga9uJ0k.png" width="400">

#### 논리식

논리식은 계산 결과가 `true` 또는 `false`가 되는 식 이다. 연산자를 가운데 또는 앞에 두고 식을 세우는데, 연산자는 다음 표와 같이 3가지가 있다.

<img src="https://i.imgur.com/zItxedN.png" width="250">

#### 비교 논리식

비교 논리식은 양쪽에 식을 두고 각 식을 계산한 결과의 크기 또는 같거나 다름을 비교하는 식이다. 계산 결과가 `true` 또는 `false`이므로 논리식의 일종이라고 할 수 있다. 가운데 위치한 비교 연산자는 다음과 같이 6가지 있다.

<img src="https://i.imgur.com/fcsjLX2.png" width="250">


#### 조건식

조건식은 `e1 ? e2 : e3`와 같은 형식으로 표현한다. `e1`의 계산 결과가 `true`이면 `e2`를 계산하고, `e1`의 계산 결과가 `false`이면 `e3`를 계산한다.


#### 지정식

지정식은 `x = e`와 같은 형식으로 표현한다. 여기서 `x`는 변수이고, `e`는 임의의 식이다. 이 지정식의 실행 의미는 다음과 같다.
- 식 `x = e`의 계산 결과값은 식 `e`를 계산한 결과값이다.
- 그리고 그 결과값을 변수 `x`가 가리키는 곳에 저장한다.

변수는 미리 선언해두어야 쓸 수 있다. 변수 선언은 다음과 같은 `<타입이름> <변수이름>` 형식으로 나열한다. 예를 들어 정수 변수 `number`를 선언하려면 다음과 같이 쓴다. 

```
int number;
```

그러면 `int` 타입의 값을 저장해둘 수 있는 메모리 공간 4 byte를 할당받고 그 곳을 `number`라는 변수로 접근할 수 있게 해준다. 이를 추상적인 그림으로 형상화해보면 다음과 같다.

<img src="https://i.imgur.com/FIwdVFu.png" width="180">

변수를 선언한다고 하여 그 공간에 값이 저절로 저장되지 않는다. 값은 다음과 같이 지정식의 형식으로 저장할 수 있다.

```
number = 55;
```

이 지정식을 실행하면 우변의 식을 계산한 결과값을 다음 그림과 같이 변수 `number`로 지정된 공간에 저장한다.

<img src="https://i.imgur.com/s3FNf4d.png" width="180">

이후에 필요에 따라 지정식으로 이 곳에 저장되어 있는 값을 변경할 수 있다.

그리고 언제든지 다음과 같이 호출하여 저장되어 있는 값을 가져다 쓸 수 있다.

```
System.out.println(number);
```

선언한 변수에 값을 지정하지 않고 호출하여 사용하면 의도하지 않은 결과를 초래할 수 있다. 왜냐하면 할당받은 공간에 어떤 값이 들어있을지 알 수 없기 때문이다. 따라서 선언한 변수의 값은 반드시 바로 지정하는 습관을 들여야 하며, 다음과 같이 선언과 동시에 초기값을 지정해두면 간편하다.

```
int number = 55;
```

변수 이름은 영문 알파벳과 숫자, 밑줄문자(`_`), 달러기호(`$`)의 조합으로 만들 수 있다. 하지만 숫자는 맨앞에 둘 수 없다. 일반 변수 이름은 명사 사용을 추천한다. 그리고 일반 변수 이름은 소문자만 사용는 것이 관례이다. 예를 들어 `number`. 두개 이상의 단어가 이어지는 경우, 밑줄을 사이에 두어야 한다. 예를 들어 `my_number`. `myNumber`와 같이 일반 변수 이름에 대문자를 쓰는 것은 추천하지 않는다. 언급한 이 관례는 개발자들끼리의 약속이므로 반드시 지키는 것이 좋다.

변수 이름은 그 변수가 갖고 있는 값을 연상할 수 있도록 짓는게 좋다. 

#### 계산 우선순위, 결합순서

이항 연산자가 2개 이상 다음과 같이 이어서 나열되어 있는 경우, 

```
e1 op1 e2 op2 e3
```

`op1`과 `op2`의 어떤 연산자를 먼저 적용하여 계산하느냐에 따라 계산 결과가 달라질 수 있다. 혼동을 방지하기 위하여 다음 표와 같이 우선순위와 결합순서를 정해놓았다.

<img src="https://i.imgur.com/AQeg8YB.png" width="600">

계산 우선순위에 반해서 우선 계산하고 싶은 쌍은 괄호로 둘러싸아 먼저 계산할 수밖에 없도록 하면 된다.

#### 사례 1. 정사각형 면적 구하기

변의 길이가 7인 정사각형의 면적

```
public class Geometry {
    public static void main(String[] args) {
        System.out.println(7 * 7);
    }
}
```

변의 길이가 7인 정사각형의 면적 (변수 활용 버전)

```
public class Geometry {
    public static void main(String[] args) {
        int side, area;
        side = 7;
        area = side * side;
        System.out.println(area);
    }
}
```

#### 사례 2. 원 면적 구하기

원의 면적을 실수로 구하는 버전

```
public class Geometry {
    public static void main(String[] args) {
        int radius;
        double area;
        radius = 7;
        area = 3.14 * radius * radius;
        System.out.println(area);
    }
}
```

- 묵시적 타입 변환 (implicit type casting) : `int` => `double`

원의 면적을 정수로 구하는 버전 (컴파일 오류 - 타입 불일치)

```
public class Geometry {
    public static void main(String[] args) {
        int radius;
        int area;
        radius = 7;
        area = 3.14 * radius * radius;
        System.out.println(area);
    }
}
```

원의 면적을 정수로 구하는 버전 (소수점 이하 버리기)

```
public class Geometry {
    public static void main(String[] args) {
        int radius;
        int area;
        radius = 7;
        area = (int)(3.14 * radius * radius);
        System.out.println(area);
    }
}
```

- 명시적 타입 변환 (explicit type casting) : `double` => `int`

원의 면적을 정수로 구하는 버전 (`java.lang.Math` 클래스 활용 버전)

```
public class Geometry {
    public static void main(String[] args) {
        int radius;
        int area;
        radius = 7;
        area = (int)(Math.PI * Math.pow(radius,2));
        System.out.println(area);
    }
}
```

<img src="https://i.imgur.com/SFuWJmr.png" width="320">

#### 사례 3. 논리 연산

```
public class Demo {
    public static void main(String[] args) {
        boolean tag = false;
        tag = tag && true || ! tag;
        System.out.println(tag);
    }
}
```

#### 문자

```
public class Demo {
    public static void main(String[] args) {
        char c = 'A';
        char n = '0';
        char s = '#';
        char backspace = '\b';
        char tab = '\t';
        char newline = '\n';
        char return_character = '\r';
        char double_quote = '\"';
        char single_quote = '\'';
        char backslash = '\\';
        char hangul = '한';
        System.out.println(hangul);
        System.out.println((int)hangul);
        System.out.println((char)(hangul+1));
    }
}
```

#### 문자열

문자열은 `java.lang` 패키지에 `class String`로 붙박이로 정의되어 있으며, 객체 이다.

```
public class Demo {
    public static void main(String[] args) {
        String slogan = "자유는 공짜가 아니다";
        System.out.println(slogan);
        System.out.println(slogan.length());
        String slogan2 = "  " + slogan + "  ";
        System.out.println(slogan2 + "!");
        System.out.println(slogan2.length());
        String slogan3 = slogan2.trim();
        System.out.println(slogan3 + "!");
        System.out.println(slogan);
        System.out.println(slogan3);
        System.out.println(slogan == slogan3);
        System.out.println(slogan.equals(slogan3));
        System.out.println(slogan.charAt(5));
        System.out.println(slogan.substring(4,6));
        String slogan_eng = "Freedom is not free";
        System.out.println(slogan_eng);
        System.out.println(slogan_eng.toUpperCase());
        System.out.println(slogan_eng.toLowerCase());
        System.out.println(slogan_eng.indexOf('o'));
        System.out.println(slogan_eng.indexOf('o',6));
        System.out.println("a".compareTo("d"));
        System.out.println("D".compareTo("A"));
    }
}
```

#### 타입 검사 (type checking)

타입 검사는 식의 타입이 일치하는지 점검하는 작업으로 컴파일러가 담당한다. 따라서 타입 검사를 통과하지 않으면 컴파일에 실패한다.

<img src="https://i.imgur.com/fOfRuGt.png" width="300">


#### 오류 (error)

컴파일(compile-time) 오류 

- 파싱(parsing) 오류 
- 타입(type) 오류
- 선언(declaration) 오류

실행 오류 (run-time error)

- 예외발생(exception) 오류 
- 논리(logic) 오류

오류 사례

<img src="https://i.imgur.com/1flIykw.png" width="350">


### 2-3. 사용자 입력 받기

#### 방법 1. 프로그램 인수로 전달

사례 1. 문자열 인수 2개

```
public class Demo {
    public static void main(String[] args) {
        String who = args[0];
        String what = args[1];
        System.out.println(who + " likes " + what + ".");
    }
}
```

사례 2. 정수 인수 2개

```
public class Demo {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        System.out.println(m + " + " + n + " = " + (m + n));
    }
}
```

사례 3. 정수 인수 1개 (출력 포맷 하기) - 포맷 전

```
public class Geometry {
    public static void main(String[] args) {
        int radius;
        double area;
        radius = Integer.parseInt(args[0]);
        area = Math.PI * Math.pow(radius,2);
        System.out.println("반지름이 " + radius + "인 원의 면적은 " + area);
    }
}
```

사례 4. 정수 인수 1개 (출력 포맷 하기) - 포맷 후

```
import java.text.*;

public class Geometry {
    public static void main(String[] args) {
        int radius;
        double area;
        radius = Integer.parseInt(args[0]);
        area = Math.PI * Math.pow(radius,2);
        DecimalFormat f = new DecimalFormat("0.00");
        System.out.println("반지름이 " + radius + "인 원의 면적은 " + f.format(area));
    }
}
```


#### 방법 2. `Swing` 입력 메시지 창에서 전달

```
import java.text.*;
import javax.swing.*;

public class Geometry {
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

입력전

<img src="https://i.imgur.com/NZcnWYb.png" width="400">

입력후

<img src="https://i.imgur.com/V8yAbtU.png" width="400">


## 실습

- `javax.swing` 패키지의 `JOptionPane`이 제공해주는 `showInputDialog` 메소드를 활용하여 입력창으로 사용자 입력을 받고, 계산 결과는 `showMessageDialog` 메소드를 활용하여 메시지 창에 출력하는 애플리케이션을 만드는 연습입니다.
- 스스로 해보는 실습 문제입니다. 제출할 필요 없습니다.

### 실습 2-1. 마일을 킬로미터로 환산

영미권에서는 세계 표준과 다른 계량 단위를 사용한다. 거리는 `km` 대신 `mile`을 사용하는데, 1 mile은 1.60934 km 이다.

`mile` 단위 거리를 정수로 입력받아 `km` 단위로 환산하여 다음과 같은 형식으로 메시지 창에 보여주는 애플리케이션을 아래 뼈대 코드 형식에 맞추어 작성하자. 환산한 거리는 소수점 첫째자리 까지만 표현한다.

<img src="https://i.imgur.com/cV24ByG.png" width="400">

<img src="(https://i.imgur.com/h7WPnze.png" width="400">

```
import java.text.*;
import javax.swing.*;

public class Mi2Km {
    public static void main(String[] args) {
        // TO DO
    }
}
```

### 실습 2-2. 9로 나누어 떨어지는 수인지 쉽게 확인하기

`-99999`과 `99999` 사이의 정수를 입력받아 그 수가 9로 나누어 떨어지는지 다음과 같은 형식으로 메시지 창에 보여주는 애플리케이션을 아래 뼈대 코드 형식에 맞추어 작성하자.

<img src="https://i.imgur.com/j9bYWeG.png" width="400">

<img src="https://i.imgur.com/ADCYxos.png" width="400">

```
import javax.swing.JOptionPane;

public class DivisibleBy9 {
    public static void main(String[] args) {
        // TO DO
    }
}
```

#### 힌트

어떤 수를 9로 나눈 나머지가 `0`과 같으면, 그 수는 9로 나누어 떨어진다.

### 실습 2-3. 9로 나누어 떨어지는 수인지 힘들게 확인하기

정수는 다음 성질을 만족한다.

> 자리별 숫자의 합이 9로 나누어 떨어지면 그 수는 9로 나누어진다.

예를 들어 9로 나누어 떨어지는 `423`, `-4725`, `19287`은 모두 자리별 숫자의 합이 각각 `9`, `18`, `27`로 9로 나누어 떨어진다.

`-99999`와 `+99999` 사이의 정수를 프로그램 인수로 받아, 자리별 숫자의 합이 9로 나누어 떨어지는지 다음과 같은 형식으로 메시지 창에 보여주는 애플리케이션을 위의 성질을 이용하여 아래 뼈대 코드 형식에 맞추어 작성하자.

<img src="https://i.imgur.com/tCZ8OU1.png" width="400">

<img src="https://i.imgur.com/PCGDK6L.png" width="400">

```
import javax.swing.JOptionPane;

public class DivisibleBy9Hard {
    public static void main(String[] args) {
        // TO DO
    }
}
```

#### 힌트 1

정수의 자리수별 숫자를 구하는 방법은 다음과 같다.

1.	정수 `n`의 가장 낮은자리수는 `n`의 절대값을 10으로 나눈 나머지이다.
2.	정수 `n`의 다음 둘째자리수는 `n`의 절대값을 10으로 나눈 몫을 10으로 나눈 나머지이다.
3.	정수 `n`의 다음 셋째자리수는 `n`의 절대값을 100으로 나눈 몫을 10으로 나눈 나머지이다.
4.	정수 `n`의 다음 넷째자리수는 `n`의 절대값을 1000으로 나눈 몫을 10으로 나눈 나머지이다.
5.	정수 `n`의 다음 다섯째자리수는 `n`의 절대값을 10000으로 나눈 몫을 10으로 나눈 나머지이다.

#### 힌트 2

`n`의 절대값은 `Math.abs(n)`으로 계산한다.


## 숙제. 월복리 계산기 애플리케이션

투자 원금이 `p`원이고 목표 수익률이 월 `i`% 일때 복리로 계산하여`n`달 후에 투자 원금이 얼마로 불어 있을까? 계산 공식은 다음과 같다.

![CodeCogsEqn (52)](https://i.imgur.com/dKppU6k.gif)

이 궁금증을 풀어주는 자바 애플리케이션 `BankService`를 아래 뼈대 코드 형식에 맞추어 만들어보자. 투자 원금, 투자 개월수, 수익률은 정수, 정수, 실수로 메시지 창으로 차례로 하나씩 입력받고,

<img src="https://i.imgur.com/m5wnCgK.png" width="450">

<img src="https://i.imgur.com/Sni3sYI.png" width="450">

<img src="https://i.imgur.com/HOE3gUa.png" width="450">

계산 결과는 다음과 같은 형식으로 메시지 창에 보여준다.

<img src="https://i.imgur.com/r5Ovc1d.png" width="450">

애플리케이션 뻐대 코드

```
import javax.swing.JOptionPane;

public class BankService {
    public static void main(String[] args) {
        // TO DO
   }
}
```

