```
(c)도경구 version 0.5 (2022/10/11)
```

## 7. 배열

배열(array) = 동일한 타입의 값을 정해진 개수만큼 나란히 이어 붙여 놓은 구조


### 7.1 사례 학습 1. 투표


<img src="https://i.imgur.com/bS2sLeE.png" width="500">

```
import javax.swing.*;

/** VoteCount 투표 애플리케이션 */
public class VoteCount {

    public static void main(String[] args) {
        int num_candidates = 4;
        int[] votes = new int[num_candidates]; // 0으로 자동 초기화

        boolean processing = true;
    while (processing) {
        /* loop invariant: 기표수는 해당 배열에 후보자별로 누적되었음 */
        String input = JOptionPane.showInputDialog("원하는 후보에 투표하세요: 0, 1, 2, 3");
        if (input == null)
            processing = false;
        else {
            char vote = input.charAt(0);
            if ('0' <= vote && vote <= '3') {
                int voted = vote - '0';
                votes[voted] += 1;
            }
            else
                System.out.println(input + "은 후보자가 아닙니다.");
        }
    }
    for (int i = 0; i != votes.length; i = i + 1)
        System.out.println("후보 " + i + "번이 " + votes[i] + "표를 득표하였습니다.");
    }

}
```


### 7.2 사례 학습 2. 놀이 카드

- 카드 덱 : 카드 배열
- 카드
  - 무늬 : Diamonds, Hearts, Clubs, SPADES
	- 등급 : A, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King

<img src="https://i.imgur.com/Cvx0NI2.png" width="500">

<img src="https://i.imgur.com/idJZUoW.png" width="600">

```
public class Card {

    public static final String SPADES = "spades";
    public static final String HEARTS = "hearts";
    public static final String DIAMONDS = "diamonds";
    public static final String CLUBS = "clubs";

    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    public static final int SIZE_OF_ONE_SUIT = 13;

    private String suit;
    private int rank;

    /** Card의 무의와 등급을 설정
     * @param s - 무늬
     * @param r - 등급  */
    public Card(String s, int r) {
        suit = s;
        rank = r;
    }

    /** 카드의 무늬 리턴 */
    public String suit() {
        return suit;
    }

    /** 카드의 등급 리턴 */
    public int rank() {
        return rank;
    }

    public boolean equals(Card c) {
        return suit.equals(c.suit()) && rank == c.rank();
    }

}
```

```
public class CardDeck {

    private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT]; // 카드 덱
    private int card_count; // 덱에 현재 남아있는 카드의 장수
    // Invariant: deck[0], .., decl[card_count-1] 에 카드가 있다.

    /** CardDeck 카드 한 벌 생성  */
    public CardDeck() {
        createDeck();
    }

    private void createDeck() {
        createSuit(Card.SPADES);
        createSuit(Card.HEARTS);
        createSuit(Card.CLUBS);
        createSuit(Card.DIAMONDS);
    }

    private void createSuit(String which_suit) {
        for(int i = 1; i <= Card.SIZE_OF_ONE_SUIT; i++) {
            deck[card_count] = new Card(which_suit, i);
            card_count = card_count + 1;
        }
    }

    /** 카드 덱에서 무작위로 카드를 한 장 선택하여 내줌
     * @return 카드 덱에서 무작위로 한 장 뽑아줌
     *         없으면 카드 1벌을 새로 만들고 무작위로 한 장 뽑아줌   */
    public Card drawCard() {
        if (card_count == 0) // will never be negative!
            createDeck();
        int the_picked = (int)(Math.random() * card_count);
        Card the_card_drawn = deck[the_picked];
        for (int i = the_picked+1; i < card_count; i++)
            deck[i-1] = deck[i];
        card_count -= card_count;
        return the_card_drawn;
    }

}
```

<img src="https://i.imgur.com/GzTRumR.png" width="600">

```
public class Hand {
    // 카드 놀이 하는 참여자의 손에 들고 있는 카드의 모델

    private Card[] hand;
    private int number_of_cards;

    /** Card 생성 메소드
     * @param max - 들고 있을 수 있는 카드의 최대 장수 */
    public Hand(int max) {
        hand = new Card[max];
    }

    /** 카드를 한장 받는다. 한도를 초과하면 받을 수 없다.
     * @param c - 카드
     * @return 성공적으로 받았으면 true, 그렇지 않으면 false
     */
    public boolean receiveCard(Card c) {
        if (number_of_cards < hand.length) {
            hand[number_of_cards] = c;
            number_of_cards += 1;
            return true;
        }
        else
            return false;
    }

    /** 지정한 카드를 낸다. 없으면 낼 수 없다.
     * @param c - 카드
     * @return 카드가 있으면 손에서 제거하고 true를 리턴, 없으면 false를 리턴
     */
    public boolean playCard(Card c) {
        boolean found = false;
        int index = 0;
        while (!found && index < number_of_cards) {
            if (hand[index].equals(c))
                found = true;
            else
                index += 1;
        }
        if (found) {
            for (int i = index+1; i < number_of_cards; i++)
                hand[i-1] = hand[i];
            number_of_cards -= 1;
            hand[number_of_cards] = null;
            return true;
        }
        else
            return false;
    }

    /** 들고 있는 카드를 모두 실행창에 보여 준다.
     *  카드가 없으면 없음을 알려 준다. */
    public void showHand() {
        if (number_of_cards == 0)
            System.out.println("카드가 없습니다.");
        else {
            Card card;
            System.out.println(number_of_cards + "장 있습니다.");
            for (int i = 0; i < number_of_cards; i++) {
                card = hand[i];
                System.out.println("#" + (i + 1) + " " + card.suit() + " " + card.rank());
            }
        }
    }

}
```

### 7.3 2차원 배열 - 실습. 슬라이드 퍼즐 게임

<img src="https://i.imgur.com/3JXF3YS.png" width="600">


## 숙제 -  스도쿠 퍼즐 게임 (제출 마감: 10월 20일 수업시작 직전)

스도쿠(Sudoku) 퍼즐은 보드게임으로 가로 9칸 세로 9칸의 9×9 격자 보드에 1부터 9까지의 숫자를 정해놓은 규칙에 맞게 채워 넣는 퍼즐 게임이다. 스도쿠 퍼즐 게임 애플리케이션을 만들어보자.

시작 시점의 스도쿠 보드는 다음 그림과 같은 모양이며, 칸의 일부는 1에서 9까지의 숫자로 채워져 있지만, 일부는 비어 있다.

<img src="https://i.imgur.com/cV0yl8c.png" width="370">

빈칸을 다음 그림과 같이 모두 채우면 퍼즐 게임이 끝난다.

<img src="https://i.imgur.com/qgVL8Ak.png" width="400">

빈칸에 숫자를 채우는 규칙은 다음과 같다.

- 9개의 세로줄과 9개의 가로줄에 1에서 9까지의 숫자를 하나씩만 넣어서 겹치지 않아야 하고,
- 3×3의 단위로 9등분한 격자 보드에도 1에서 9까지의 숫자를 하나씩만 넣어서 겹치지 않아야 한다.

위 그림에 채워진 숫자를 확인하여 이 규칙을 준수했는지 확인해보자.

### 난이도 옵션

스도쿠 퍼즐게임은 빈칸이 많을수록 퍼즐 풀기가 어려워진다. 따라서 게임을 시작하면서 플레이어가 난이도를 스스로 선택하도록 한다. 다음 그림과 같이 창을 띄워 난이도에 따라 1,2,3 중에서 선택하도록 한다.

<img src="https://i.imgur.com/pyHRrbY.png" width="400">

다음 그림은 플레이어가 1을 선택한 그림이다.

<img src="https://i.imgur.com/GaYrchT.png" width="400">

난이도에 따른 빈칸의 개수는 다음과 같이 정한다.
- 초급 = 27
- 중급 = 36
- 고급 = 45

### 퍼즐 게임의 진행

스도쿠 보드를 보면 왼쪽과 위쪽에 줄 번호가 있다.

<img src="https://i.imgur.com/FLlGp9P.png" width="400">

왼쪽은 가로줄 번호를 나타내고, 위쪽은 세로줄 번호를 나타낸다. 먼저 다음과 같은 창을 띄워 가로줄 번호를 받는다.

<img src="https://i.imgur.com/LISrUwT.png" width="400">

플레이어는 5를 넣고 OK 버튼을 누른다.

<img src="https://i.imgur.com/3xaoSHw.png" width="400">

그리고 나서 다음과 같은 창을 띄워 세로줄 번호를 받는다.

<img src="https://i.imgur.com/7WdGk86.png" width="400">

플레이어는 7을 넣고 OK 버튼을 누른다.

<img src="https://i.imgur.com/kLLC2l8.png" width="400">

그리고 나서 다음과 같은 창을 띄워 해당 빈칸에 채울 숫자를 받는다.

<img src="https://i.imgur.com/0n5XuHX.png" width="400">

플레이어는 6을 넣고 OK 버튼을 누른다.

<img src="https://i.imgur.com/0Gl7NEC.png" width="400">

가로줄 5와 세로줄 7에 해당하는 칸이 빈칸이 맞고, 넣은 숫자 6이 스도쿠 규칙을 만족하므로 다음과 같이 빈칸에 해당 숫자가 채워진다.

<img src="https://i.imgur.com/8kbRF5s.png" width="400">

지정한 칸이 빈칸이 아니거나 넣으려는 숫자가 스도쿠 규칙을 만족하지 않으면, 플레이어의 시도는 무시하고 게임을 계속 진행하도록 한다.

빈칸을 모두 채우면 게임이 끝난다.

### 설계도

다음 클래스 다이어그램과 같이 이 애플리케이션을 설계했다.

<img src="https://i.imgur.com/LDXF8vd.png" width="700">

View와  Starter클래스는 모두 다른 팀이 다음과 같이 구현을 완성하였다.

```
import javax.swing.*;

public class PlayerInput {

    /** 플레이어에게 난이도를 선택하게 하여 난이도에 따라 빈칸의 개수를 정하여 리턴한다.
     *
     * @return 빈칸의 개수
     */
    public int selectLevel() {
        String message = "난이도 숫자 입력  = 초급 1, 중급 2, 고급 3";
        String input = JOptionPane.showInputDialog(message);
        while (! (input.equals("1") || input.equals("2") || input.equals("3")))
            input = JOptionPane.showInputDialog(message);
        int level = Integer.parseInt(input);
        if (level == 1)
            return 27;
        else if (level == 2)
            return 36;
        else // level must be 3
            return 45;
    }

    /** 1~9 범위의 수를 플레이어에게서 입력받아서 정수로 리턴한다.
     *
     * @param message - 인풋 메시지 윈도우에 보여줄 메시지 문자열   
     * @return 인풋 받은 1~9 범위의 정수
     */
    public int selectNumber(String message) {
        String input = JOptionPane.showInputDialog(message);
        while (! (input.equals("1") || input.equals("2") || input.equals("3") ||
                  input.equals("4") || input.equals("5") || input.equals("6") ||
                  input.equals("7") || input.equals("8") || input.equals("9")))
            input = JOptionPane.showInputDialog(message);
        return Integer.parseInt(input);
    }
}

```

```
import java.awt.*;
import javax.swing.*;

public class SudokuWriter extends JPanel {

    private Sudoku sudoku;
    private final int SIZE = 40;
    private final int PANEL_SIZE = SIZE * 11;

    /** JPanel 객체 초기화 메소드
     *    
     * @param s - 윈도우에 그릴 Sudoku 객체
     */
    public SudokuWriter(Sudoku s) {
        sudoku = s;
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setLocation(160, 200);
        f.setTitle("Sudoku");
        f.setSize(PANEL_SIZE, PANEL_SIZE+28);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, PANEL_SIZE, PANEL_SIZE);
        int digit;
        int x = SIZE;
        int y = SIZE;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) { 				
                g.setColor(Color.gray);
                g.drawRect(x, y, SIZE, SIZE);
                g.setColor(Color.black);
                digit = sudoku.getPuzzleBoard()[i][j];
                if (digit != 0)
                    g.drawString(digit + "", x+15, y+25);
                x += SIZE;
            }
            x = SIZE;
            y += SIZE;
        }
        x = SIZE;
        y = SIZE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g.setColor(Color.black);
                g.drawRect(x, y, SIZE*3, SIZE*3);
                x += SIZE * 3;
            }
            x = SIZE;
            y += SIZE * 3;
        }
        x = SIZE - 25;
        y = SIZE;
        for (int i = 1; i <= 9; i++) {
            g.setColor(Color.blue);
            g.drawString(i + "", x, y+25);
            y += SIZE;
        }
        x = SIZE;
        y = SIZE - 10;
        for (int i = 1; i <= 9; i++) {
            g.setColor(Color.blue);
            g.drawString(i + "", x+15, y);
            x += SIZE;
        }
    }
}

```

```
public class SudokuPuzzle {

    public static void main(String[] args) {
        new SudokuController().playSudokuPuzzle();
    }
}

```

그런데 모델 클래스 `Sudoku`와 컨트롤러 클래스 `SudokuController` 클래스만 아직 미완성이다. 다음 `Sudoku` 클래스의 명세서를 참고하여, 미완성인 아래 두 클래스의 빈 부분을 채워 구현을 완성하자.

<img src="https://i.imgur.com/wkwAgQm.png" width="700">


```
import java.util.*;

public class Sudoku {

    private int[][] solution = new int[9][9];
    private int hole_count;
    private int[][] puzzle_board = new int[9][9];

    /** 객체 초기화 메소드
     *
     * @param count - 빈칸의 개수
     */
    public Sudoku(int count) {
        createSolutionBoard();
        hole_count = count;
        createPuzzleBoard(count);
    }

    /** 퍼즐 보드 배열을 리턴 한다.
     *
     * @return 퍼즐 보드 배열
     */
    public int[][] getPuzzleBoard() {
        return puzzle_board;
    }

    /** 빈칸의 개수를 리턴 한다.
     *
     * @return 빈칸의 개수
     */
    public int countHoles() {
        return hole_count;
    }

    // [배점 = 0.5/2.0]
    /** 해답 스도쿠 보드인 solution 배열을 무작위로 섞어서 만든다. */
    private void createSolutionBoard() {
        // 1~9 범위의 무작위 시퀀스 {n1,n2,n3,n4,n5,n6,n7,n8,n9}를 만들고,
        // 이를 문서에 첨부한 그림 1과 같이 solution 배열에 배치 한다.







        // 문서에 첨부한 그림 2와 같이 가로줄 바꾸기와 세로줄 바꾸기를 무작위로 한다.
        // 무작위로 줄 바꾸기를 한다는 말은 바꿀지 말지를 무작위로 결정한다는 의미이다.
        // 가로줄 바꾸기
        shuffleRibbons();
        // 세로줄 바꾸기
        transpose();
        shuffleRibbons();
        transpose();
        // 테스트용 메소드
        showBoard(solution);
    }

    /** 0~n-1 범위의 정수 수열을 무작위로 섞은 배열을 리턴 한다.
     *
     * @param n - 수열의 길이
     * @return 0~n-1 범위의 정수를 무작위로 섞어 만든 배열
     */
    private int[] generateRandomPermutation(int n) {
        Random random = new Random();
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            int d = random.nextInt(i+1);
            permutation[i] = permutation[d];
            permutation[d] = i;
        }
        return permutation;
    }

    /** 문서에 첨부한 그림 2와 같은 전략으로 solution 배열의 가로줄을 무작위로 섞는다. */
    private void shuffleRibbons() {
        int[][] shuffled = new int[9][9];
        int[] random_index;
        for (int i = 0; i < 3; i++) {
            random_index = generateRandomPermutation(3);
            for (int j = 0; j < 3; j++)
                shuffled[i*3+random_index[j]] = solution[i*3+j];
        }
        solution = shuffled;
    }

    /** solution 배열의 행과 열을 바꾼다. */
    private void transpose() {
        int[][] transposed = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                transposed[i][j] = solution[j][i];
        solution = transposed;
    }

    /** 2차원 배열 b를 콘솔 윈도우에 보여준다. (테스트용 메소드)
     *
     * @param b - 2차원 배열
     */
    private void showBoard(int[][] b) {
        System.out.println("스도쿠 보드");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }

    // [배점 = 0.5/2.0]
    /** solution 배열에서 count 만큼 무작위로 빈칸을 채워 puzzle_board 배열을 만들어 리턴한다.
     *  
     * @param count - 빈칸의 개수
     */
    private void createPuzzleBoard(int count) {
        // solution 보드를 그대로 puzzle_board에 복제한다.



        // 무작위로 빈칸을 선정한다. 빈칸은 구별을 위해서 0으로 채운다.
        // new Random().nextInt(n) 메소드를 호출하면
        // 0~n-1 범위의 정수 중에서 무작위로 하나를 고를 수 있다.










    }

    // [배점 0.5/2.0]
    /** row번 가로줄, col번 세로줄에 digit을 채울 수 있는지 검사하여,
     *  가능하면 채우고 true를 리턴하고, 불가능하면 false를 리턴 한다.
     *
     * @param digit - 빈칸에 채울 수 (1~9 중 하나)
     * @param row - 가로줄 번호
     * @param col - 세로줄 번호
     * @return 퍼즐 보드 조건에 만족하여 빈칸을 채웠으면 true, 만족하지 않으면 false
     */
    public boolean check(int digit, int row, int col) {







    }
}
```

```
public class SudokuController {

    private Sudoku sudoku;
    private PlayerInput reader;
    private SudokuWriter writer;

    /** Sudoku, PlayerInput, SudokuWriter 객체를 생성하여 필드 변수에 지정한다. */
    public SudokuController() {
        reader = new PlayerInput();
        int hole_count = reader.selectLevel();
        sudoku = new Sudoku(hole_count);
        writer = new SudokuWriter(sudoku);
    }

    // [배점 0.5/2.0]
    /** 스도쿠 퍼즐 게임을 진행한다. */
    public void playSudokuPuzzle() {







    }
}
```


### 첨부

#### 그림 1

<img src="https://i.imgur.com/uCcs3mF.png" width="500">

#### 그림 2

<img src="https://i.imgur.com/FqfY3qY.png" width="500">
