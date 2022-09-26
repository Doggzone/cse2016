```
(c)도경구 version 0.93 (2022/09/06)
```

## 1. 첫 자바 애플리케이션 만들기

### 1-1. 컴퓨터와 프로그램의 실행

#### 1-1-1. 컴퓨터(computer)

- 컴퓨터는 지시/명령(프로그램)에 따라 계산을 수행하는 기계(하드웨어)이다.
- 컴퓨터의 구성 요소
	- 프로세서(processor): 프로그램에 기술한대로 계산 수행
		- 메모리에서 프로그램 또는 데이터 읽어오기
		- 더하기, 빼기 등의 연산을 수행하기
		- 계산한 결과를 메모리에 저장하기
		- 외부에서 데이터를 받아오거나, 외부로 데이터를 내보내기
	- 메모리(Random Access Memory, RAM): 계산 수행하는데 필요한 프로그램과 데이터를 계산하는 동안 보관해 두는 곳
		- 계산에 필요한 프로그램과 데이터를 모두 기록
		- 읽고 쓰는 속도가 빠름, 고비용
		- 계산 작업 종료와 함께 모두 버림
		- 전원이 공급되는 동안만 기억을 유지 (영구 기록 불가)
- 외부저장장치
	- 프로그램과 데이터를 영구히 보관해 두는 곳
	- 전원이 공급되지 않더라도 기억을 유지, 메모리에 비해 상대적으로 저비용
	- 하드디스크, USB, 클라우드, ...
	- 파일 단위로 보관하고, 폴더 단위로 정리 보관 가능
- 입출력장치
	- 사람과 컴퓨터 사이의 소통 창구
	- 입력(input): 사람이 컴퓨터로 정보를 전달
		- 키보드, 마우스, 트랙패드, 디스프레이 패드, 마이크, 카메라, ...
	- 출력(output): 컴퓨터가 사람에게 정보를 전달
		- 모니터, 스피커, 프린터, ...

#### 1-1-2. 프로그램의 실행

- 컴퓨터로 계산을 수행하기 위해서 작성한 지시 또는 명령이 프로그램(소프트웨어)이다.
- 프로그래밍은 프로그램을 작성하는 행위이다. 코딩(coding)이라고 하기도 한다.
- 컴퓨터의 프로그램 실행 절차
	- 작성한 프로그램은 외부저장장치에 파일로 저장
	- 파일에 저장된 프로그램을 메모리에 카피 = 로딩(loading)
	- 프로세서가 로딩된 프로그램을 실행
- 기계수준 언어 (machine language, low-level language)
	- 컴퓨터 프로세서가 이해하고 실행할 수 있는 0과 1의 이진수의 조합으로 표현된 언어
	- 기계수준 언어 프로그램은 이진수로 되어 있어 사람이 이해하고 작성하기 난해
	- 따라서 사람이 기계수준 언어로 프로그램을 작성하기는 비경제적
- 사람수준 언어 (high-level language)
	- 사람이 계산 논리를 기술하기 용이한 언어
	- 컴퓨터 프로세서는 사람수준 언어를 이해하지 못함
	- 사람수준 언어를 컴퓨터로 실행하는 2가지 상이한 방식
		- 인터프리터(interpreter) 실행 방식
		- 컴파일러(compiler) 실행 방식

#### 인터프리터 실행 방식

- 사람수준 언어를 이해하고 실행하는 프로세서를 갖춘 가상머신(virtual machine)을 소프트웨어로 구현하여 설치
- 사람수준 언어 프로그램을 가상머신 프로세서가 직접 실행 (언어 : Python 등)

#### 컴파일러 실행 방식 

- 사람수준 언어로 작성한 프로그램을 프로세서가 이해하는 기계수준 언어로 번역함
- 번역하는 과정을 컴파일(compile)이라고 함
- C 언어 등과 같이 컴퓨터 하드웨어 프로세서가 이해하는 기계수준 언어로 컴파일하여 실행하기도 하고, Java 언어 등과 같이 가상컴퓨터(VM, virtual machine)를 소프트웨어로 만들어 해당 VM의 프로세서가 이해하는 기계수준 언어로 컴파일하여 싫행하기도 함

#### 1-1-3. 자바 프로그램의 실행

- Java 프로그램은 가상컴퓨터인 Java Virtual Machine(JVM)이 이해하는 기계수준 언어 Java Bytecode로 컴파일하여 실행한다.
- 따라서 Java 프로그램을 실행하려면 JVM 소프트웨어를 컴퓨터에 설치해야 한다.
- Java 프로그램 실행 절차
	- 프로그램을 Java로 작성
	- Java 프로그램을 Java Bytecode 프로그램으로 컴파일하여 로딩
	- 로딩된 Java Bytecode 프로그램을 JVM이 실행
- 왜, JVM 가상컴퓨터를 사용하는가?
	- 컴퓨터 하드웨어 기종에 따라 프로세서가 다르고, 프로세서마다 이해하는 기계수준 언어가 다르다.
	- 따라서 컴퓨터 기종별로 전용 컴파일러를 따로 준비해야 해서 불편하다.
	- 그런데 컴퓨터 하드웨어 기종별로 JVM 소프트웨어를 준비해두면, Java Bytecode로 컴파일하는 컴파일러 하나만 준비해두면 된다.
	- 우수한 이식성(portability): 하드웨어 또는 운영체제와 상관없이 어디에서나 실행가능하고 항상 일정한 실행 결과 보장

#### 1-1-4. Java 프로그램의 작동 개념

- Java는 객체지향 패러다임으로 프로그래밍이 가능한 언어이다.
- 클래스(class)
  - Java 프로그램은 클래스(class)의 집합이다.
  - 파일 하나에 클래스 하나씩 담는다.
  - 클래스는 객체를 만들어내는(찍어내는) 형판(template) 이다.
- 객체(object)
  - 객체는 프로그램 실행 중에 생성되어 메모리에 거주하는 실체(instance)이다.
  - 각 객체는 고유한 상태(field)과 수행가능한 기능(method)을 보유하고 있다.
  - 예를 들어 Java에서 문자열 `String`은 객체로 취급한다.
  - 다음 그림은 `String` 객체 `"ERICA"`를 형상화 한 것이다.

<img src="https://i.imgur.com/T5TTMKQ.png" width="400">

  - 이 `String` 객체는 `"ERICA"` 문자열을 갖고 있고, 다양한 기능을 할 수 있도록 준비되어 있다. 그 기능 중 하나가 `length()` 라는 메소드이다.
  - 즉, 이 객체가 갖고 있는 문자열의 길이을 알고 싶으면 `length()` 메소드 호출 메시지를 이 객체에 보낸다. 그러면 이 객체는 해당 기능을 수행하여 결과인 `5`를 내준다. 

- 자바 세상에서 계산이란 프로그램에 짜놓은 각본대로 객체들이 만들어져 메모리에 거주하면서 객체들끼리 서로 메시지를 주고받는 일련의 소통 과정으로 볼 수 있다.
- 프로그램이 이와 같은 방식으로 작동하는 패러다임을 객체지향 프로그래밍, 영어로 Object-Oriented Programming, 또는 줄여서 OOP, 패러다임이라고 한다.

#### 1-1-5. Java 프로그래밍 준비

- 프로그램을 작성하고 실행하는 작업을 한 군데에서 모두 할 수 있게 해주는 소프트웨어를 통합개발환경, 영어로는 IDE(Integrated Development Environment)라고 한다.
- 다양한 상용 IDE가 시판되고 있으니 어떤 것이든지 취향에 맞는 걸 골라서 사용하면 된다.
- 많이 사용하는 IDE를 몇 개만 나열해보면, Eclipse, intelliJ IDEA 등이 있다.
- 이 수업에서는 Eclipse를 사용한다.
- 실습 또는 숙제를 하면서 이미 친숙한 다른 IDE가 있다면 굳이 Eclipse를 사용하지 않아도 상관없지만, 소통의 원활함을 원한다면 Eclipse 사용을 권한다.
- 대부분의 상용 IDE는 회사에서 업무용으로 쓰는 경우 유료이지만, 학교에서 교육용으로 쓰는 경우 Community Edition 이라고 하며 무료이다.

### 1-2. `Hello, World!` 애플리케이션

- 애플리케이션(Application)은 컴퓨터 운영체제로 실행가능한 모든 소프트웨어를 말한다. 우리말로는 응용프로그램이라고도 한다.
- 일부 시스템소프트웨어를 제외한 거의 대부분 소프트웨어를 자바로 작성할 수 있으므로 자바로 작성한 프로그램을 보통 자바 애플리케이션 (Java Application) 이라고 많이 부른다.
- 이제 자바 애플리케이션을 작성하고 실행하는 과정을 간단한 `Hello, World!` 애플리케이션 사례를 가지고 경험해보자.

#### 1-2-1. 표준 출력 버전

#### 요구사항

- Eclipse에서 `Hello, World!`를 콘솔창에 프린트하는 애플리케이션을 만들어 실행해보자.

#### Eclipse로 프로그래밍 준비

- Eclipse를 연다.
- 먼저 프로그램을 만들어 저장할 장소를 마련해야 한다.
- 메뉴에서 `Java Project` 클릭하여 프로젝트 만들기
	- 프로젝트 이름은 `Project name` 칸에 `hello`(모두 소문자)로 적고 `Finish` 클릭
	- 다음 창에서 모듈은 (당분간) 만들지 않을 것이므로 `Don't Create` 클릭
	- `hello` 이름의 폴더가 만들어짐. 앞으로 작성하는 프로그램은 이 폴더에 저장됨
	- `hello` 폴더 안에 `bin`과 `src` 폴더가 생성됨
	- `src` 폴더에는 작성하는 소스 코드가 보관되고, `bin` 폴더에는 컴파일된 바이트 코드가 보관됨

- 메뉴에서 `Class` 클릭하여 클래스 만들기
	- `Package` 이름은 (당분간) 빈칸으로 둠
	- 클래스 이름은 `Name` 칸에 `HelloWorld`(각 단어의 첫 문자만 대문자)로 정함
	- `public static void main(String[] args)` 체크박스 클릭하여 선택
	- `Finish` 클릭
	- `HelloWorld.java` 파일이 `src` 폴더 안에 생김
	- 자바 프로그램이라는 표시를 해주기 위해 확장자 `.java`가 붙음
	- 확장자를 제외하면 파일 이름과 클래스 이름이 동일함을 주목
	- 자바 프로그램 파일마다 클래스가 하나씩 있음
- 자동 생성된 `HelloWorld.java` 파일 살펴보기

<img src="https://i.imgur.com/bLSKt8Q.png" width="400">

- 라인 2에서 `public`는 누구나 쓸 수 있도록 공개된 클래스임을 나타내는 키워드이고,
- 이어서 나오는 `HelloWorld`는 클래스의 이름을 나타낸다.
- 라인 4-7은 `main` 메소드(method)라고 하며, 애플리케이션 실행과 동시에 저절로 바로 실행되므로 시동을 거는 역할을 한다고 볼 수 있다.
- `main` 메소드 앞에 `static` 이라는 키워드는 이 메소드가 클래스소속임을 나타낸다. 클래스소속은 객체로 만들지 않아도 호출(사용)이 가능하다.
- 메인 메소드의 몸체를 이루는 중괄호 사이에 코드를 작성한다.

#### 구현

- 콘솔창에 `Hello, World!`를 프린트하는 코드는 다음과 같다.

```
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

- 작성한 코드 한 줄의 의미를 파악해보자.
	- `System`은 `java.lang` 패키지(package) 소속의 클래스이다.
	- `java.lang` 패키지는 프로그램에서 따로 언급하지 않아도 쓸 수 있도록 준비되어 있다.
	- `System` 클래스가 보유하고 있는 클래스소속 필드 `out`에 콘솔창에 프린트하는 기능을 가진 `PrintStream` 객체가 연결되어 있다.
	- `PrintStream` 객체가 보유하고 있는  `println("...")`를 호출하면 콘솔창에 `...`을 프린트한다.

#### 컴파일

- 프로그램을 실행하려면 먼저 컴파일을 해야한다.
- IDE에서는 프로그램을 완성하고 저장하면 저절로 컴파일이 되어 바이트코드가 생성된다.
- 생성된 바이트코드는 `bin` 폴더에서 확인할 수 있다.
- 바이트코드는 소스코드와 파일 이름이 같으며, 확장자만 `.class`로 다르다.

#### 실행

- 메뉴에서 다음과 같은 모양의 `Run` 버튼을 찾아서 클릭한다. ![Run](https://i.imgur.com/BqLc6Cm.png)
- 콘솔창에서 실행 결과 `Hello, World!` 확인한다.


#### 실행 추적을 통한 실행 의미의 이해

1. `Run` 버튼을 누름과 동시에 `HelloWorld` 클래스의 `main` 메소드가 저절로 호출되어 실행된다.
2. `System` 클래스의 `out` 필드에 연결되어 있는 `PrintStream` 객체에 `println("Hello, World!")` 메시지를 보낸다.
3. `PrintStream` 객체는 `"Hello, World!"`를 콘솔창에 프린트 한다.

<img src="https://i.imgur.com/ktYceOk.png" width="600">



#### 1-2-2. Swing 패키지 활용 버전

#### 요구사항

- `Hello, World!`를 Swing 패키지에서 제공하는 `Messsage` 출력창에 프린트하는 애플리케이션을 만들어보자.

#### 구현

- `Messsage` 출력창에 `Hello, World!`를 프린트하는 코드는 다음과 같다.

```
import javax.swing.*;

public class HelloWorld {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Hello, World!");
    }
}

```

- `javax.swing`은 Java Standard API Library에서 제공하는 Graphic User Interface(GUI) 라이브러리 패키지 이다. (Java API Documentation 사이트 방문하여 확인)
- 이 패키지를 사용하려면 `import` 키워드를 붙여 위와 같이 프로그램 앞부분에 선언해야 한다.
- 작성한 코드 한 줄의 의미를 파악해보자.
	- `JOptionPane`은 `javax.swing` 패키지가 제공하는 클래스이다.
	- `JOptionPane`은 `showMessageDialog`  클래스소속 메소드를 보유하고 있다.
	- `showMessageDialog(null,"...")` 메소드를 호출하면 메시지 출력창을 띄워 `...`을 프린트하여 보여준다.

#### 실행

-	메뉴에서 `Run` 버튼을 찾아서 클릭하여 실행하여, 다음과 같은 출력창이 생기는지 확인

<img src="https://i.imgur.com/sEIA0I6.png" width="200">


#### 실행 추적을 통한 실행 의미의 이해

1. `Run` 버튼을 누름과 동시에 `HelloWorld` 클래스의 `main` 메소드가 저절로 호출되어 실행된다.
2. `JOptionPane` 클래스에 `showMessageDialog(null, "Hello, World!")` 메시지를 보낸다.
3. `JOptionPane` 클래스는 `"Hello, World!"`를 새긴 메시지 출력창을 띄운다.

<img src="https://i.imgur.com/tmRbp7g.png">

### 1-3. 실습 : IDE 준비

- IDE를 개인 컴퓨터에 설치한다.
- 강의 시간에 시연한 `Hello, World!` 애플리케이션을 강의 노트에 기술한대로 똑같이 따라서 작성하고 실행하여 문제없는지 확인한다.

### 1-4. 실습 : 현재 시각 출력 애플리케이션

- 실행하는 시점의 시각을 출력하는 애플리케이션을 공부하자.

#### 1-4-1. 표준 출력 버전

#### 요구사항

- 현재 시각을 콘솔창에 프린트하는 다음 애플리케이션을 실행하여 콘솔창에서 현재 시각을 확인하고, 실행 추적해보자.

```
import java.time.*;

public class Clock {
    public static void main(String[] args) {
        System.out.println(LocalTime.now());
    }
}
```

#### 실행 추적을 통한 실행 의미의 이해

1. `Run` 버튼을 누름과 동시에 `Clock` 클래스의 `main` 메소드가 저절로 호출되어 실행된다.
2. `java.time` 패키지에 준비되어 있는 `LocalTime` 클래스의 클래스소속 메소드인 `now()`를 호출하여 현재 시각을 받아온다.
3. 전달받은 현재 시각을 콘솔창에 출력해달라는 `println` 메시지를 `PrintStream` 객체에 보낸다.
4. `PrintStream` 객체는 전달받은 현재 시각을 콘솔창에 프린트 한다.

<img src="https://i.imgur.com/keu2ovV.png" width="600">



#### 1-4-2. Swing 패키지 활용 버전

#### 요구사항

- 현재 시각을 Swing 패키지에서 제공하는 `Messsage` 출력창에 프린트하는 다음 애플리케이션을 실행하여 결과를 확인하고, 실행 추적해보자.


```
import java.time.*;
import javax.swing.*;

public class Clock {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, LocalTime.now());
    }
}
```


#### 실행 추적을 통한 실행 의미의 이해

1. `Run` 버튼을 누름과 동시에 `Clock` 클래스의 `main` 메소드가 저절로 호출되어 실행된다.
2. `java.time` 패키지에 준비되어 있는 `LocalTime` 클래스의 클래스소속 메소드인 `now()`를 호출하여 현재 시각을 받아온다.
3. 전달받은 현재 시각을 `Messsage` 출력창에 프린트해달라는 `showMessageDialog` 메시지를 `JOptionPane` 클래스에 보낸다.
4. `JOptionPane` 클래스는 전달받은 현재 시각을 새긴 메시지 출력창을 띄운다.

<img src="https://i.imgur.com/pygbvpg.png" width="600">











