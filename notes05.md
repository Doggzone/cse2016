```
(c)도경구 version 1.0 (2022/09/25)
```

## 5. 선택 제어 구조

지금까지 공부한 프로그램은 기술한 순서대로 실행한다. 메소드를 호출하면 해당 메소드 프로그램 블록을 차례대로 실행 완료한 다음, 호출한 곳으로 돌아와서 그 다음 부터 실행을 계속한다. 그런데 특정 조건에 따라서 다른 프로그램 블록을 선택하여 실행하도록 프로그램을 작성해야 하는 경우도 많이 있다. 이 장에서는 조건에 따라서 실행할 코드를 선택하게 해주는 제어 구조를 공부한다.



## 숙제 - 주사위 게임 (제출 마감: 10월 6일 수업 직전)

두 플레이어가 주사위를 한 쌍씩 굴려서 다음 규칙에 따라 우열을 가리는 자바 애플리케이션을 만들자.

- 굴린 주사위 한 쌍의 수가 같으면 쌍둥이 라고 한다. 
- 쌍둥이는 쌍둥이가 아닌 경우를 무조건 이긴다.
- 쌍둥이 끼리는 큰 수가 이기고, 이마저 같으면 비긴다.
- 둘 다 쌍둥이가 아니면, 수의 합이 크면 이긴다.
- 수의 합이 같으면, 두 수의 차이가 작으면 이긴다.
- 두 수의 차이도 같으면 비긴다.

애플리케이션을 시작하면 두 플레이어가 각각 차례로 이름을 등록하고 게임을 진행하며, 굴린 주사위와 누가 이겼는지 보여주면서 원하는 동안 게임을 반복하도록 하고 각 플레이어 별로 이긴 횟수를 누적하여 다음과 같이 보여준다.

<img src="https://i.imgur.com/IcvG6n8.png" width="250"><img src="https://i.imgur.com/xAlQtxC.png" width="250"><img src="https://i.imgur.com/YeI9CMP.png" width="250">

이 게임 보드 윈도우는 최소 정보만 텍스트로 표시한 것이다. 게임 보드 디자인은 각자 자유에 맡긴다.

이 애플리케이션을 MVC 아케텍처 기반으로 설계한 다음 클래스 다이어그램에 맞추어 구현한다. 

<img src="https://i.imgur.com/eBe2mAU.png" width="700">

### 모델 클래스 `Dice`

이 클래스는 게임에 사용할 주사위를 만드는 모델 클래스이다.
주사위의 상태를 나타내는 필드 변수는 두 주사위의 액면 수 `1`\~`6` 을 기억하는 `face1`과 `face2`, 두 수의 합을 기억하는 `sum`, 두 수의 차이를 기억하는 `difference`, 두 수가 같은지를 기억하는 `twin`을 둔다.
이 필드 변수의 값은 같은 이름의 메소드를 통하여 외부에서 읽을 수 있다. 
`rollDice()`는 주사위 한 쌍을 굴려서 필드 변수 값을 모두 새로 지정하는 메소드이다. 
주사위의 액면은 `Math.random()` 함수 메소드를 활용하여 결정한다.
완성한 `Dice` 클래스는 다음과 같다.

```
public class Dice {
	
    private int face1;
    private int face2;
    private int sum;
    private int difference;
    private boolean twin;
	
    public int face1() { return face1; }	
    public int face2() { return face2; }	
    public int sum() { return sum; }
    public int difference() { return difference; }
    public boolean twin() { return twin; }
	
    public void rollDice() {
        face1 = (int)(Math.random() * 6) + 1;
        face2 = (int)(Math.random() * 6) + 1;
        sum = face1 + face2;
        difference = Math.abs(face1 - face2);
        if (face1 == face2) 
            twin = true;
    }
}
```

### 모델 클래스 `Player`

이 클래스는 게임 플레이어를 만드는 모델 클래스이다.
플레이어의 상태를 나타내는 필드 변수는 이름을 기억하는 `name`, 누적 이긴 횟수를 기억하는 `points`, 방금 굴린 주사위를 기억하는 `rolled`, 방금 굴린 주사위로 이겼는지 여부를 기억하는 `wins`을 둔다.
이 필드 변수의 값은 같은 이름의 메소드를 통하여 외부에서 읽을 수 있다. 
각 메소드가 하는 역할은 다음과 같다.

| 메소드 | 역할 |
|:---:|:---:|
| `Player(String n)` | 플레이어의 이름을 인수로 받아 `name`에 기억해둔다. |
| `void play(Dice d)` | 주사위를 인수로 받아 굴려서 결과를 `rolled`에 기억한다. | 
| `void receivePoint()` | `points` 값을 1 증가시키고, `wins`를 `true` 값으로 지정하여 이겼음을 기억한다. | 
| `void reset()` | 다음 게임을 위하여 `wins`를 `false` 값으로 재지정하여 이긴 기록을 삭제한다. | 

완성한 `Player` 클래스는 다음과 같다.

```
public class Player {
	
	private String name;
	private int points;
	private Dice rolled;
	private boolean wins;
	
	public String name() { return name; }
	public int points() { return points; }
	public Dice rolled() { return rolled; }
	public boolean wins() { return wins; }
	
	public Player(String n) {
		name = n;
	}
	
	public void play(Dice d) {
		d.rollDice();
		rolled = d;
	}
	
	public void receivePoint() {
		points += 1;
		wins = true;
	}
	
	public void reset() {
		wins = false;
	}

}
```

### 인풋 뷰 클래스 `Registrar`

이 클래스는 외부 사용자에게서 정보를 받아서 전달하는 기능을 갖고 있는 인풋 뷰 클래스이다.
`javax.swing` 패키지에서 제공하는 `JOptionPane`을 활용한다.
준비한 두 가지 기능은 다음과 같다.

| 메소드 | 역할 |
|:---:|:---:|
| `String invitePlayer()` | `showInputDialog`로 플레이어 이름을 받아서 전달한다. |
| `int wantToContinue()` | `showConfirmDialog`로 게임을 계속할지 의사를 받아 전달한다. 사용자가 `Yes` 버튼을 누르면 `0`을 전달한다. `No` 버튼을 누르면 다른 수를 전달한다. | 

<img src="https://i.imgur.com/LJjwhrg.png" width="400"><img src="https://i.imgur.com/VDyGpHr.png" width="350">


완성한 `Registrar` 클래스는 다음과 같다.

```
import javax.swing.*;

public class Registrar {
	
    public String invitePlayer() {
        return JOptionPane.showInputDialog("이름을 등록해 주세요.");	
    }
	
    public int wantToContnue() {
        return JOptionPane.showConfirmDialog(null, "더 하시겠어요?");
    }

}
```

### 아웃풋 뷰 클래스 `GameBoard`

(이 클래스는 숙제로 각자 완성한다.)

이 클래스는 게임이 진행되는 상황을 외부 사용자에게 보여주는 기능을 갖고 있는 아웃풋 뷰 클래스이다.
`javax.swing` 패키지에서 제공하는 `JPanel`을 상속받아서 구현하며, 플레이어의 이름, 누적 이긴 횟수, 주사위 한 쌍을 굴린 결과와 누가 이겼는지 게임 보드 윈도우에 표시한다. 윈도우에 어떻게 보여줄지는 `paintComponent` 메소드에 구현한다.


### 컨트롤러 클래스 `Dealer`

(이 클래스는 숙제로 각자 완성한다.)

이 클래스는 주사위 게임을 총괄하여 진행하는 `dealDiceGame(Player p1, Player p2, GameBoard b, Registrar r)` 메소드를 구현하면 된다. 
이 메소드가 인수로 전달 받는 객체는 두 개의 `Player`와 `GameBoard`, `Registrar` 객체이다.

게임 진행은 다음 알고리즘을 참고하여 구현한다.

1. 두 플레이어가 주사위를 던지는 순서는 고정해두지 않고, 누적 점수가 작은 플레이어가 항상 먼저 주사위를 던지도록 한다. (처음에는 등록한 순서, 이후에는 누적 점수가 역전이 되는 경우 던지는 순서가 바뀌도록 한다.)
2. 차례로 주사위를 굴려서 결과는 각 `Player` 객체가 기억하도록 한다.
3. 결과를 검사하여 승패를 결정하고, 이긴 `Player` 객체는 이긴 횟수를 1 증가시킨다.
4. 게임 결과를 반영하여 갱신한 게임보드 원도우를 보여준다. `GameBoard`(`JPanel`) 객체의 `repaint()` 메소드를 호출하면 `paintComponent` 메소드가 실행되어 윈도우를 다시 그려준다.
5. 게임을 계속할지 여부는 `Registrar` 객체의 `wantToContinue()` 메소드를 호출하여 결정한다. 
   - 게임을 계속 진행하려면 `this.dealDiceGame(p1, p2, b, r);`와 같이 자신을 다시 호출하게 하면 된다. 호출하기 전에 해당 `Player` 객체의 `reset()` 메소드를 호출하여 방금 이긴 기록을 삭제해야 함을 명심하자. 
   - 게임을 그만하려면 `System.exit(0)`을 실행하여 애플리케이션을 강제 종료하면 된다.

### 스타터 클래스 `DiceGame`

`main` 메소드를 갖고 있는 스타터 클래스는 애플리케이션을 실행하는데 필요한 객체를 준비하고, `Dealer` 객체를 만들어 `dealDiceGame` 메소드를 호출하여 게임을 시작하게 한다.

완성한 `DiceGame` 클래스는 다음과 같다.

```
public class DiceGame {

    public static void main(String[] args) {
        Registrar r = new Registrar();
        Player p1 = new Player(r.invitePlayer());
        Player p2 = new Player(r.invitePlayer());
        GameBoard b = new GameBoard(p1, p2);
        new Dealer().dealDiceGame(p1,p2,b,r);
    }
}
```


소스코드 파일이 들어 있는 폴더를 zip으로 압축하여 제출한다.
