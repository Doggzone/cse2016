---
layout: page

title: 실습
---
## Lab #9-2. GUI and Event-Driven Programming (하) (2020/11/26)

#### 4. 퍼즐 게임 GUI 버전 `slidepuzzle` - 코팅 따라하기 (제출)
#### 5. 스크롤 리스트 `scrollinglist` - 코드 읽기 (제출하지 않음)
#### 6. 텍스트 필드 `tempconv`- 코드 읽기 (제출하지 않음)
#### 7. 텍스트 편집기 `texteditor`- 코드 읽기 (제출하지 않음)

## Lab #9-1. GUI and Event-Driven Programming (상) (2020/11/23)

#### 1. 라벨, 버튼 프레임을 윈도우에 끼우기

#### 2. 카운터 만들기 (최종 버전만 제출)

#### 3. 크기, 색깔 변하는 공 (제출하지 않아도 됨)

## Lab #8-2. Programming to Interface (하) (2020/11/19)

### 5. `Dealer` 클래스 구현 (따라하기)

##### (1) 다음 `CardPlayerBehavior`를 참고하여 아래 명세의 `Dealer` 클래스를 구현하자.

```
public interface CardPlayerBehavior {
	
	/** wantsACard - 카드 한 장을 받겠는지 답한다.
	 * @return 카드를 받고 싶으면 true, 아니면 false */
	public boolean wantsACard();
	
	/** receiveCard - 카드를 한장 받는다. 한도(배열 hand의 크기)를 초과하면 받을 수 없다.
	 * @param c - 카드
	 * @return 성공적으로 받았으면 true, 그렇지 않으면 false */
	public boolean receiveCard(Card c);
}

```

![Dealer](https://i.imgur.com/YbaR1OC.png)

##### (2) 위의 명세에서 `CardPlayerBehavior`를 `CardPlayer`로 
모두 고쳤다고 하고, `class Dealer`를 수정해보자.
이 두 버전이 차이점을 이해하고, 장단점을 생각해보자.


### 6. `Card Player` 구현 (따라하기)

![CardPlayer](https://i.imgur.com/tT5VUkA.png)


## Lab #8-1. Programming to Interface (상) (2020/11/16)

### 1. Database Application 구현 따라하기

다음 메인 메소드를 `Database` 클래스에 추가하여
실행하여 어떤 결과가 나오는지 확인하자. 

```
public static void main(String[] args) {
	Database db = new Database(4);
		
	BankAccount c1 = new BankAccount(50000, new IntegerKey(55));
	boolean register1 = db.insert(c1);
		
	IntegerKey k = new IntegerKey(1979);
	BankAccount c2 = new BankAccount(10000, k);
	boolean register2 = db.insert(c2);
		
	Record r = db.find(k);
	System.out.println(((BankAccount)r).getBalance());
}
```

### 2. Inheritance 이해하기

```
public class Person {
    private String name;

    public Person(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public boolean sameName(Person other) {
        return getName().equals(other.getName();
    }
}
```

```
public class PersonFrom extends Person {
    private String city;

    public PersonFrom(String n, String c) {
        super(n);
        city = c;
    }

    public String getCity() {
        return city;
    }

    public boolean same(PersonFrom other) {
        return sameName(other) && 
               city.equals(other.getCity());
    }
}
```

```
Person p = new Person(“마음”);
Person q = new PersonFrom(“소리”,”서울”);
```

다음 각 문장을 이해하고, Java 컴파일러를 통과하는 문장을 고르고, 그 문장이 무엇을 프린트할지 예측해보자.

- `System.out.println(p.getCity());`
- `System.out.println(q.getName());`
- `System.out.println(p.sameName(p));`
- `System.out.println(q.sameName(p));`
- `System.out.println(q.same(p));`

### 3. 서브 타입 이해하기

다음 각 문장을 이해하고, Java 컴파일러를 통과하는 문장을 고르고, 그 문장이 무엇을 프린트할지 예측해보자.

- `System.out.println(p.sameName(q));`
- `Person x = q; System.out.println(x.getName());`
- `PersonFrom x = p; System.out.println(x.getCity());`
- `Person x = q; System.out.println(x.getCity());`
- `System.out.println(q.same(p));`

### 4. `instanceof`

1. 앞에서 구현한 StringKey와 같은 요령으로 IntegerKey를 구현하자.
2. 다음 코드를 실행하면 어떤 결과가 실행창에 프린트될까?

```
Database db = new Database(4);

BankAccount a1 = new BankAccount(50000, new IntegerKey(55));
IntegerKey k = new StringKey(“열려라”);
BankAccount a2 = new BankAccount(10000, k);
boolean transaction1 = db.insert(a1);
boolean transaction2 = db.insert(a2);

Record p = db.find(k);
BankAccount q = (BankAccount)p;
System.out.println(q.getBalance());

Key k = q.getKey();
if (k instanceof IntegerKey)
    System.out.println(((IntegerKey)k).getInt());
else if (k instanceof StringKey)
    System.out.println(((StringKey)k).getString());
else
    System.out.println(“모르는 Key 출현 오류”);
```


## Lab #7. Arrays (2020/11/2,5,9)

### 1. 공부한 CardDeck, Card 클래스를 활용하여 
다음에 주석으로 기술한 명세를 만족하는 Hand 클래스를 완성하자. 

```
public class Hand {
	// 카드 놀이 하는 참여자의 손에 들고 있는 카드의 모델

	private Card[] hand; // 손에 들고 있는 카드
	private int number_of_cards; // 손에 들고 있는 카드의 장수

	/** Constructor Hand - how_many 카드를 수용가능한 Card 배열 객체를 만들어 hand 초기화 
	 * @param how_many - 들고 있을 수 있는 카드의 최대 장수 */
	public Hand(int how_many) {
		hand = new Card[how_many];
	}
	
	/** showHand - 들고 있는 카드를 모두 실행창에 프린트한다. 
	 *             카드가 없으면 카드가 없다고 프린트한다. */ 
	public void showHand() {


	}
	
	/** receiveCard - 카드를 한장 받는다. 한도(배열 hand의 크기)를 초과하면 받을 수 없다.
	 * @param c - 카드
	 * @return 성공적으로 받았으면 true, 그렇지 않으면 false */
	public boolean receiveCard(Card c) {


	}

	/** removeCard - 들고 있는 카드 중에서 하나를 버린다. 
	 *  게임에 따라 버릴 카드를 선택하는 기준이 다를 수 있음. 여기서는 무작위로 선택 
	 * @return 선택한 카드, 손에 들고 있는 카드가 없으면 null */
	public Card removeCard() {
		if (number_of_cards > 0) {
			int picked = (int)(Math.random() * number_of_cards);
			Card card_to_remove = hand[picked];
			


		}
		else
			return null;
	}
}

```

### 2. Slide Puzzle Game (따라 하기)


## Lab #6. Patterns of Repetition (2020/10/22)

### Bouncing Ball Animation

#### 1. 파란 공을 하나 추가

- 두 공은 다른 장소에서 다른 방향으로 출발한다.
- 두 공이 움직이는 속도는 같다.
- 공이 충돌해도 그대로 통과한다.

#### 2. 충돌시 진로 수정

- 두 공이 충돌하면, 둘 다 진행 방향을 역방향으로 바꾸도록 한다.

#### 3. 장애물 설치

- 중앙에 다음과 같은 모양의 적당한 길이의 장애물을 설치한다.

![Bar](https://i.imgur.com/Cf8GiTq.png)

- 공이 이 장애물 위면 또는 아래면을 만나면 y축 진행 방향을 바꾸도록 한다.


## Lab #5. Control Structure: Conditional Statements and Software Architecture (2020/10/12,15)

### `BankAccountManager2` 애플리케이션 - 계좌 이체 기능 추가

- 다음 사양의 계좌이체 기능 추가

![Lab5](https://i.imgur.com/rhwNn3G.png)

- `BankReader` 클래스에 계좌이체 서비스 입력 메뉴 추가
  - `> 금액` - send
  - `< 금액` - receive

```
/* 시동 클래스 
 * 입력 포맷 
 *   P     - 주계좌
 *   S     - 보조계
 *   D 금액 - 입금 
 *   W 금액 - 출금 
 *   Q     - 종료 
 * 출력 
 *   거래 결과 */
public class AccountManager2 {

	public static void main(String[] args) {
		BankReader reader = new BankReader();
		BankAccount primary_account = new BankAccount(0);
		BankAccount secondary_account = new BankAccount(0);
		BankWriter primary_writer = new BankWriter("자유은행 계좌#1", account);
		BankWriter secondary_writer = new BankWriter("자유은행 계좌#2", account);
		AccountController2 controller = new AccountController2(reader, primary_writer, secondary_account, 
						                                               primary_writer, secondary_account);
		controller.processTransactions();
	}
}
```

```
public class AccountController2 {
	
	private BankReader reader; // input-view
	private BankWriter primary_writer; // output-view
	private BankWriter secondary_writer;
	private BankAccount primary_account; // model
	private BankAccount secondary_account;
	private BankAccount account; // remembers active account
	private BankWriter writer; // remembers active writer
	
	/* AccountController 객체 초기 
	 * @param r - input-view 객체 
	 * @param w - output-view 객체
	 * @param a - model 객체 */
	public AccountController2(BankReader r, BankWriter w1, BankAccount a1,
			                                BankWriter w2, BankAccount a2) {
		reader = r;
		primary_writer = w1;
		secondary_writer = w2;
		primary_account = a1;
		secondary_account = a2;
		// 기본은 주계좌
		account = primary_account;
		writer = primary_writer;
	}
	
	/* resetAccount - 계좌 변경 
	 * @param new_account - 변경할 계좌 
	 * @param new_writer - 변경할 출력기 */
	private void resetAccount(BankAccount new_account BankWriter new_writer) {
		writer.showTransactions("비활성");
		account = new_account;
		writer = new_writer;
		writer.showTransactions("활성");
	}
	
	/* processTransactions - Q 요청을 할 때까지 고객의 요청을 처리 */
	public void processTransactions() {
		char command = reader.readCommand("계좌#1 P, 계좌#2 S, 입금 D금액, 출금 W금액, 종료 Q);");
		if (command == 'Q') {
			writer.showTransaction("서비스를 마칩니다.");
			return;
		}
		else if (command == 'D') {
			int amount = reader.readAmount();
			if (account.deposit(amount))
				writer.showTransaction(amount, "입금");
			else
				writer.showTransaction("입금 실패");
		}
		else if (command == 'W') {
			int amount = reader.readAmount();
			if (account.withdraw(amount))
				writer.showTransaction(amount, "출금");
			else
				writer.showTransaction("출금 실패");
		}
		else if (command == 'P') 
			resetAccount(primary_account, primary_writer);
		else if (command == 'S')
			resetAccount(secondary_account, secondary_writer);
		else
			writer.showTransaction("요청 오류");
		this.processTransactions();
	}
}
```

```
import javax.swing.*;

public class BankAccount {
	
	private int balance; // invariant: balance >= 0
	
	/* BankAccount - 계좌 개설
	 * @param initial_amount - 초기 입금 금액 (0 이상 양수) */
	public BankAccount(int initial_amount) {
		if (initial_amount >= 0)
			balance = initial_amount;
		else
			balance = 0;
	}
	
	/* deposit - 입금 
	 * @param amount - 입금액 (0 이상 양수) 
	 * @return 입금 성공하면 true, 실패하면 false */
	public boolean deposit(int amount) {
		boolean result = false;
		if (amount >= 0) {
			balance = balance + amount;
			result = true;
		}
		else 
			JOptionPane.showMessageDialog(null, "입금액에 문제가 있어서 입금이 취소되었습니다.");
		return result;
	}
	
	/* withdraw - 출금 (잔고가 있는 경우 한)
	 * @param amount - 출금액 (0 이상 양수) 
	 * @return 출금 성공하면 true, 실패하면 false */
	public boolean withdraw(int amount) {
		boolean result = false;
		if (amount < 0) {
			JOptionPane.showMessageDialog(null, "출금액에 문제가 있어서 입금이 취소되었습니다.");
		}
		else if (amount > balance) 
			JOptionPane.showMessageDialog(null, "출금액이 잔고액보다 많아서 입금이 취소되었습니다.");
		else {
			balance = balance - amount;
			result = true;
		}
		return result;
	}
	
	/* getBalance - 잔액 확인 
	 * @return 잔액 */
	public int getBalance() {
		return balance;
	}
	
	public static void main(String[] args) {
		BankAccount tester = new BankAccount(0);
		System.out.println("잔액 = " + tester.getBalance());
		int five = 50000;
		int three = 30000;
		if (tester.deposit(five))
			System.out.println(five + "원 입금 성공 : 잔액 = " + tester.getBalance());
		else
			System.out.println(five + "원 입금 실패 : 잔액 = " + tester.getBalance());
		if (tester.withdraw(three))
			System.out.println(three + "원 출금 성공 : 잔액 = " + tester.getBalance());
		else 
			System.out.println(three + "원 출금 실패 : 잔액 = " + tester.getBalance());
		if (tester.withdraw(three))
			System.out.println(three + "원 출금 성공 : 잔액 = " + tester.getBalance());
		else 
			System.out.println(three + "원 출금 실패 : 잔액 = " + tester.getBalance());
	}
}
```

```
import javax.swing.*;

public class BankReader {
	
	private String input_line;
	
	/* BankReader - 입력기 초기화 */
	public BankReader() {
		input_line = "";
	}
	
	/* readCommand - 요청 서비스 파악 
	 * @param message - 안내 메시지
	 * @return 요청 문자열의 첫 문자 */
	public char readCommand(String message) {
		input_line = JOptionPane.showInputDialog(message).trim().toUpperCase();
		return input_line.charAt(0);
	}
	
	/* readAmount - 요청 액수 파악 
	 * @return 금액, 문제가 발생하면 알리고 0을 리턴 */
	public int readAmount() {
		int amount = 0;
		String s = input_line.substring(1, input_line.length());
		s = s.trim();
		if (s.length() > 0)
			amount = Integer.parseInt(s);
		else
			JOptionPane.showMessageDialog(null, "금액 입력 오류");
		return amount;
	}
}
```

```
import java.awt.*;
import javax.swing.*;

public class BankWriter extends JPanel {
	private int WIDTH = 300;
	private int HEIGHT = 200;
	private BankAccount bank;
	private String last_transaction = "";
	
	/* BankWriter - 출력기 초기화 
	 * @param title - 창의 제목 
	 * @param b - BankAccount 객체 */
	public BankWriter(String title, BankAccount b) {
		bank = b;
		JFrame f = new JFrame();
		f.getContentPane().add(this);
		f.setTitle(title);
		f.setSize(WIDTH, HEIGHT);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT+22);
		g.setColor(Color.black);
		int text_margin = 50;
		int text_baseline = 50;
		g.drawString(last_transaction, text_margin, text_baseline);
		g.drawString("잔액 = " + bank.getBalance() + "원", text_margin, text_baseline + 20);
	}
	
	/* showTransaction - 거래 결과 표시 
	 * @param message - 거래 메시지
	 * @param amount - 거래 금액 */
	public void showTransaction(int amount, String message) {
		last_transaction = amount + "원 " + message;
		this.repaint();
	}
	
	/* showTransaction - 거래 결과 표시 
	 * @param message - 거래 메시지 */
	public void showTransaction(String message) {
		last_transaction = message;
		this.repaint();
	}
}
```






## Lab #4. Component Structure: Method and Class Building (2020/10/5,8)

### 1. MathOperations 클래스에 역수 계산 메소드 장착하기

다음의 명세를 참고하여 `MathOperations` 클래스에 4개의 메소드를 구현하자.

![inverse](https://i.imgur.com/gvZ81AP.png)

다음의 구동 클래스를 실행하여 아래와 같이 실행 결과가 실행창에 나타나는지 확인하자.

```
import java.text.DecimalFormat;

public class TestMathOperations {

	public static void main(String[] args) {
		MathOperations calculator = new MathOperations();
		
		System.out.println(calculator.inverse(3));
		System.out.println(calculator.inverse(7));
		
		calculator.printInverse(3);
		calculator.printInverse(7);
		
		calculator.printInverse(3, "0.0");
		calculator.printInverse(7, "0.00000");
		
		DecimalFormat one_place = new DecimalFormat("0.0");
		calculator.printInverse(3, one_place);
		DecimalFormat five_places = new DecimalFormat("0.00000");
		calculator.printInverse(7, five_places);
	}
}
```

```
0.3333333333333333
0.14285714285714285
0.333
0.143
0.3
0.14286
0.3
0.14286
```


### 2. `MyWriter` 클래스 단장하기

![MyWriter](https://i.imgur.com/qgX2tb5.png)

수업 시간에 공부한 `MyWriter` 클래스는 배경이 흰색이다.
위와 같이 적절히 테두리를 둘러 보기 좋게 하려고 한다.
색깔과 두께는 디자이너의 자유 선택에 맡긴다.
테두리를 칠하는 역할을 하는 다음 사양의 내부 전용 메소드를 
추가하여 활용해야 한다.
```
private void makeBorder(Graphics pen)
```

수업 시간에 공부한 다음 클래스를 활용하여 확장한다.

```
import java.awt.*; 
import javax.swing.*;

/* MyWriter - 문장를 디스플레이할 그래픽스 윈도를 만듬 */
public class MyWriter extends JPanel {
	private int width; // 프레임 너비 
	private int height; // 프레임 높이 
	private String sentence = ""; // 인쇄할 문장
	private int x_position; // 문장 위치 x 좌표 
	private int y_position; // 문장 위치 y 좌표 
	
	/* MyWriter - constructor 메소드 
	 * @param w - 창의 너비 
	 * @param h - 창의 높이 */
	public MyWriter(int w, int h) {
		width = w;
		height = h;
		x_position = width / 5;
		y_position = height / 2;
		JFrame my_frame = new JFrame();
		my_frame.getContentPane().add(this);
		my_frame.setTitle("MyWriter");
		my_frame.setSize(width, height + 22);
		my_frame.setVisible(true);
		my_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/* paintComponent - 화가 메소 
	 * @param g - 그래픽 펜 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.drawString(sentence, x_position, y_position);
	}
	
	/* writeSentence - 창에 문자열 인쇄 
	 * @param s - 인쇄할 문자열 */
	public void writeSentence(String s) {
		sentence = s;
		this.repaint();
	}
	
	/* repositionSentence - 위치를 이동하여 창에 문자열 인쇄 
	 * @param x - x 좌표 
	 * @param y - y 좌표 */
	public void repositionSentence(int x, int y) {
		x_position = x;
		y_position = y;
		this.writeSentence(sentence); // this 생략 가능 
	}
}
```

```
import javax.swing.*;

public class MyExample {

	public static void main(String[] args) {
		int width = 300;
		int height = 200;
		MyWriter writer = new MyWriter(width, height);
		String s = JOptionPane.showInputDialog("보고 싶은 문장 넣어주세요.");
		writer.writeSentence(s);
	}
}
```



## Lab #3. Input, Output & State (2020/9/24,28)


### 1. 온도 변환 프로그램 요구 사항 변경

```
import javax.swing.*;
import java.text.*;

public class CelsiusToFahrenheit {

	public static void main(String[] args) {
		String message = "화씨 온도를 입력해주세요.";
		String input = JOptionPane.showInputDialog(message);
		double f = Double.parseDouble(input);
		double c = (f - 32) * 5.0 / 9.0;
		DecimalFormat formatter = new DecimalFormat("0.00");
		String output = "화씨 " + f + "도는 ";
		output += "섭씨로 " + formatter.format(c) + "도 입니다.";
		JOptionPane.showMessageDialog(null, output);
	}
}
```

### 2. 시계 초침 그리기


```
import java.awt.*;
import javax.swing.*;
import java.time.*;

public class ClockWriter extends JPanel {
	
	private int width = 200;

	public ClockWriter() {
		// 프레임 생성 
		JFrame clock_frame = new JFrame();
		// 자신(패널)을 프레임에 끼우기 
		clock_frame.getContentPane().add(this);
		// 프레임 만들어 보여주기 
		clock_frame.setTitle("Clock");
		clock_frame.setSize(width,width);
		clock_frame.setVisible(true);
		clock_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g) {
		// 바탕은 흰색으로 
		g.setColor(Color.white);
		g.fillRect(0, 0, width, width);
		// 현재 시간 + 시침, 분침, 초 각도 계산
		LocalTime now = LocalTime.now();
		int seconds_angle = 90 - now.getSecond() * 6; 
		int minutes_angle = 90 - now.getMinute() * 6;
		int hours_angle = 90 - now.getHour() * 30;
		// 시계 판 그리기
		int x = 50;
		int y = 50;
		int diameter = 100;
		g.setColor(Color.black);
		g.drawOval(x, y, diameter, diameter);
		// 초침 그리기
		g.fillArc(x+10, x+10, diameter-20, diameter-20, seconds_angle, -3);
		// 분침 그리기
		g.setColor(Color.red);
		g.fillArc(x+5, x+5, diameter-10, diameter-10, minutes_angle, 5);
		// 시침 그리기 
		g.setColor(Color.blue);
		g.fillArc(x+25, x+25, diameter-50, diameter-50, hours_angle, -8);
	}

	public static void main(String[] args) {
		new ClockWriter();
	}
}
```


### 3. 작아지는 계란


```
import java.awt.*;
import javax.swing.*;

public class EggWriter extends JPanel {
	
	private int SIZE = 100;
	private int X = 3;
	private int Y = 2;
	private int WIDTH = X * SIZE;
	private int HEIGHT = Y * SIZE;
	private int width = WIDTH;
	private int height = HEIGHT;
	private int x = 0;
	private int y = 0;

	public EggWriter() {
		JFrame f = new JFrame();
		f.getContentPane().add(this);
		f.setTitle("Egg");
		f.setSize(WIDTH, HEIGHT+22);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
		x = x + X * 5;
		y = y + Y * 5;
		width = width - X * 10;
		height = height - Y * 10;
	}

	public static void main(String[] args) {
		new EggWriter();
	}
}
```


## Lab #2. Arithmetic and Variables (2020/9/21,월)

### 1. 마일을 킬로미터로 환산 (예상소요시간 20분)

영미권에서는 세계 표준과 다른 계량 단위를 사용한다. 
거리는 `km` 대신 `mile`을 사용하는데, 1 mile은 1.60934 km 이다. 

`mile` 단위 거리(실수)를 프로그램 인수로 받아,
`km` 단위로 환산하여 다음과 같은 형식으로 실행창에 프린트해주는 애플리케이션을 작성하자.
환산한 거리는 소수점 첫째자리 까지만 표현한다.

```
> javac Main.java
> java Main 500
500.0 마일은 804.7 킬로미터 입니다.
> java Main 55
55.0 마일은 88.5 킬로미터 입니다.
> java Main 65
65.0 마일은 104.6 킬로미터 입니다.
> java Main 70
70.0 마일은 112.7 킬로미터 입니다.
```

### 2. 9로 나누어 떨어지는 수인지 쉽게 확인하기 (예상소요시간 10분)

`-99999`과 `99999` 사이의 정수를 프로그램 인수로 받아서
그 수가 9로 나누어 떨어지는지 
아래의 실행 사례와 같은 형식으로 알려주는 애플리케이션을 작성하자.

힌트: 어떤 수를 9로 나눈 나머지가 `0`과 같으면, 그 수는 9로 나누어 떨어진다.

```
> javac Main.java
> java Main 0
정수 = 0
9로 나누어지나? true
> java Main 7
정수 = 7
9로 나누어지나? false
> java Main 9
정수 = 9
9로 나누어지나? true
> java Main -9
정수 = -9
9로 나누어지나? true
> java Main 38
정수 = 38
9로 나누어지나? false
> java Main 666
정수 = 666
9로 나누어지나? true
> java Main 423
정수 = 423
9로 나누어지나? true
> java Main -4725
정수 = -4725
9로 나누어지나? true
> java Main 19287
정수 = 19287
9로 나누어지나? true
```

### 3. 9로 나누어 떨어지는 수인지 힘들게 확인하기 (예상소요시간 40분)

정수는 다음 성질을 만족한다.

> 자리별 숫자의 합이 9로 나누어 떨어지면 그 수는 9로 나누어진다.

예를 들어 9로 나누어 떨어지는 `423`, `-4725`, `19287`9는 
모두 자리별 숫자의 합이 각각 `9`, `18`, `27`로 9로 나누어 떨어진다.

`-99999`와 `+99999` 사이의 정수를 프로그램 인수로 받아,
자리별 숫자의 합이 9로 나누어 떨어지는지 알려주는 애플리케이션을 작성하자.

정수의 자리수별 숫자를 구하는 방법은 다음과 같다.

1. 정수 `n`의 가장 낮은자리수는 `n`의 절대값을 10으로 나눈 나머지이다.
2. 정수 `n`의 다음 둘째자리수는 `n`의 절대값을 10으로 나눈 몫을 10으로 나눈 나머지이다.
3. 정수 `n`의 다음 셋째자리수는 `n`의 절대값을 100으로 나눈 몫을 10으로 나눈 나머지이다.
4. 정수 `n`의 다음 넷째자리수는 `n`의 절대값을 1000으로 나눈 몫을 10으로 나눈 나머지이다.
5. 정수 `n`의 다음 다섯째자리수는 `n`의 절대값을 10000으로 나눈 몫을 10으로 나눈 나머지이다.

`n`의 절대값은 `Math.abs(n)`으로 계산한다.

실행 결과는 다음과 같이 실행창에 프린트해야 한다.

```
> java Main 0
정수 = 0
숫자의 합 = 0
9로 나누어지나? true
> java Main 5
정수 = 5
숫자의 합 = 5
9로 나누어지나? false
> java Main 9
정수 = 9
숫자의 합 = 9
9로 나누어지나? true
> java Main -7
정수 = -7
숫자의 합 = 7
9로 나누어지나? false
> java Main 18
정수 = 18
숫자의 합 = 9
9로 나누어지나? true
> java Main 21
정수 = 21
숫자의 합 = 3
9로 나누어지나? false
> java Main 423
정수 = 423
숫자의 합 = 9
9로 나누어지나? true
> java Main -4725
정수 = -4725
숫자의 합 = 18
9로 나누어지나? true
> java Main 19287
정수 = 19287
숫자의 합 = 27
9로 나누어지나? true
```


### 4. 월복리 계산기 애플리케이션 (예상소요시간 30분)

투자 원금이 `p`원이고 목표 수익률이 월 `i`% 일때 복리로 계산하여
`n`달 후에 투자 원금이 얼마로 불어 있을까?
계산 공식은 다음과 같다.

![CodeCogsEqn (52)](https://i.imgur.com/dKppU6k.gif)

이 궁금증을 풀어주는 자바 애플리케이션을 만들어보자.
투자 원금, 투자 개월수, 수익률은 프로그램 인수로 제공하고,
각각 정수, 정수, 실수로 입력받는다.
실행 결과는 다음과 같이 실행창에 프린트해야 한다.

```
> javac Main.java
> java Main 1000000 12 0
투자 원금 = 1000000원
월 수익률 = 0.0%
12개월 후 총액 = 1000000원
> java Main 1000000 12 1
투자 원금 = 1000000원
월 수익률 = 1.0%
12개월 후 총액 = 1126825원
> java Main 1000000 12 1.5
투자 원금 = 1000000원
월 수익률 = 1.5%
12개월 후 총액 = 1195618원
> java Main 1000000 12 2.9
투자 원금 = 1000000원
월 수익률 = 2.9%
12개월 후 총액 = 1409238원
> java Main 1000000 12 5
투자 원금 = 1000000원
월 수익률 = 5.0%
12개월 후 총액 = 1795856원
```





## Lab #1. Simple Java Applications (2020/9/10)

### [실습 준비] 웹브라우저 기반 IDE repl.it 등록 및 준비

- 크롬 브라우저 설치
- 크롬 브라우저를 열고, https://repl.it/ 로 이동
- 오른쪽 위 `Sign up` 버튼을 누르고 등록
- 설문 조사 ... 언어 Java 선택 ... 이메일 계정 인증 확인
- `Java` 버튼 클릭
- 상단에 있는 `Run` 버튼을 클릭하여 `Hello world!` 프로그램 실행하고 결과 확인

### 1. 100일 전과 100일 후

**문제**

오늘 날짜와 100일 전의 날짜, 100일 후의 날짜를 각각 다음과 같은 형식으로 실행창에 프린트하는 자바 애플리케이션을 설계하고 구현하자. 

```
100 days ago: 2020-06-02
Today: 2020-09-10
100 days later: 2020-12-19
```

**코딩 가이드**

- 이 문제는 강의 슬라이드 25쪽의 코드를 변형, 확장하여 완성할 수 있다.

- 오늘 날짜는 `java.time` 패키지에서 제공하는 `LocalDate` 객체가 알고 있다. 이 객체는 저절로 생긴다.
[Java API Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/index.html#)에서 `LocalDate`를 검색하여 관련 문서를 확인해보자.

- 오늘 날짜는 `now`메소드를 사용하여 알아낼 수 있다. 문서에서 이 메소드의 구문과 의미를 찾아서 이해하자.

- `LocalDate` 객체의 날짜에 주어진 수만큼 날짜를 더하거나 뺀 `LocalDate` 객체를 새로 만들고 싶으면 `plusDays` 또는 `minusDays` 메소드를 사용하면 된다. 문서에서 이 메소드의 구문과 의미를 찾아서 이해하자.

- 코드를 작성하기 전에 클래스 다이어그램을 그려서 설계도부터 작성하자. 설계도는 강의슬라이드 24쪽의 클래스 다이어그램과 매우 유사하다.

- 설계도를 참고하여 구현을 완성하자.


### 2. 몇달 며칠 기다리면 크리스마스?

**문제**

오늘 부터 크리스마스까지 몇달 며칠이 남았는지 다음과 같은 형식으로 실행창에 프린트하는 자바 애플리케이션을 설계하고 구현하자.

```
3 Months 15 Days to Christmas!
```

**코딩 가이드**

- 이 문제는 앞 문제의 코드를 변형, 확장하여 완성할 수 있다.

- 년/월/일을 구체적으로 명시하여 그 날짜에 해당하는 `LocalDate` 객체를 만들 수 있다. `of`메소드를 사용하면 되는데, 문서에서 이 메소드의 호출 구문과 의미를 찾아서 이해하자. 예를 들어 크리스마스 `LocalDate` 객체는 `LocalDate.of(2020,12,25)`로 만들 수 있다.

- 기간을 년/월/일 단위로 구분하여 기억할 수 있는 객체가 있다. `java.time` 패키지의 `Period` 객체가 바로 그 것이다. 이 객체도 저절로 생긴다. 초기값은 `ZERO` 이다. [Java API Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/index.html#)에서 `Period`를 검색하여 관련 문서를 확인해보자.

- 시작 `LocalDate`와 종료 `LocalDate`가 주어지면, `Period` 객체의 기간을 세팅해주는 메소드는 `between` 이다.  `Period` 클래스 문서에서 이 메소드의 구문과 의미를 찾아서 이해하자.

- `Period` 객체가 가지고 있는 년/월/일 정보는 각각 `getYears`, `getMonths`, `getDays` 메소드로 추출할 수 있다. 문서에서 이 메소드의 호출 구문과 의미를 찾아서 이해하자

- 코드를 작성하기 전에 클래스 다이어그램을 그려서 설계도부터 작성하자. 앞 문제의 설계도를 확장하여 완성할 수 있다.

- 설계도를 참고하여 구현을 완성하자.


