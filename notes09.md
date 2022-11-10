```
(c)도경구 version 1.0 (2022/11/2)
version 1.1 (2022/11/6) 슬라이딩 퍼즐 게임 추가
```

## 9. GUI와 이벤트 구동 프로그래밍

### 9-1. 사례 학습 : 카운터

코딩 따라하기

####  1단계 : 초기 버전

```
public class Counter {
    private int count;

    /** Counter - 카운터 초기 설정
     * @param start - 카운터의 초기값 */
    public Counter(int start) {
        count = start;
    }

    /** increment - 카운터 값 증가 */
    public void increment() {
        count = count + 1;
    }

    /** count - 카운터 값 리턴
     * @return 카운터 현재 값  */
    public int count() {
        return count;
    }

}
```

```
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CounterFrame extends JFrame implements ActionListener {
    private Counter counter;
    private JLabel label = new JLabel("count = 0");

    public CounterFrame(Counter c) {
        counter = c;
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(label);
        JButton button = new JButton("OK");
        cp.add(button);
        button.addActionListener(this); // 자신을 버튼에 연결 (버튼 이벤트가 발생하면 처리 가능하도록)
        setTitle("Counter");
        setSize(200,70);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** actionPerformed - '버튼 누르기' 액션 이벤트를 처리 */
    public void actionPerformed(ActionEvent e) {
        counter.increment();
        label.setText("count = " + counter.count());
    }

}
```

```
public class CounterStarter {
    public static void main(String[] args) {
        Counter model = new Counter(0);
        new CounterFrame(model);
    }

}
```

#### 2단계 : 컨트롤러와 뷰를 분리

```
import java.awt.event.*;

public class CounterController implements ActionListener {
    private CounterFrame view;
    private Counter model;

    public CounterController(Counter m, CounterFrame v) {
        view = v;
        model = m;
    }

    /** actionPerformed - '버튼 누르기' 액션 이벤트를 처리 */
    public void actionPerformed(ActionEvent e) {
        model.increment();
        view.update();
    }

}
```

```
import java.awt.*;
import javax.swing.*;

public class CounterFrame extends JFrame {
    private Counter counter;
    private JLabel label = new JLabel("count = 0");

    public CounterFrame(Counter c) {
        counter = c;
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(label);
        JButton button = new JButton("OK");
        cp.add(button);
        button.addActionListener(new CounterController(counter, this)); // 자신을 버튼에 연결 (버튼 이벤트가 발생하면 처리 가능하도록)
        setTitle("Counter");
        setSize(200,70);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** update - 뷰 갱신 */
    public void update() {
        label.setText("count = " + counter.count());
    }

}
```

#### 3단계 : 버튼 전용 컨트롤러

```
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CountButton extends JButton implements ActionListener {
    private CounterFrame view;
    private Counter model;

    /** CountButton - 버튼 컨트롤러
     * @param label - 버튼에 붙는 라벨
     * @param m - 협조할 모델
     * @param v - 갱신할 뷰 */
    public CountButton(String label, Counter m, CounterFrame v) {
        super(label);
        view = v;
        model = m;
        addActionListener(this);
    }

    /** actionPerformed - '버튼 누르기' 액션 이벤트를 처리
     * @param e - 이벤트 */
    public void actionPerformed(ActionEvent e) {
        model.increment();
        view.update();
    }

}
```

```
import java.awt.*;
import javax.swing.*;

public class CounterFrame extends JFrame {
    private Counter counter;
    private JLabel label = new JLabel("count = 0");

    public CounterFrame(Counter c) {
        counter = c;
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(label);
        cp.add(new CountButton("OK", counter, this));
        setTitle("Counter");
        setSize(200,70);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** update - 뷰 갱신 */
    public void update() {
        label.setText("count = " + counter.count());
    }

}
```

#### 4단계 : 그래픽 카운터, 구역 정리, 버튼 추가

```
import java.awt.event.*;
import javax.swing.*;

public class ExitButton extends JButton implements ActionListener {

    /** ExitButton - 종료 컨트롤러
     * @param label - 버튼에 붙는 라벨 */
    public ExitButton(String label) {
        super(label);
        addActionListener(this);
    }

    /** actionPerformed - '버튼 누르기' 액션 이벤트를 처리
     * @param e - 이벤트  */
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
```

```
import java.awt.*;
import javax.swing.*;

public class Drawing extends JPanel {

    private Counter counter;

    public Drawing(Counter c) {
        counter = c;
        setSize(200, 200);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 200, 200);
        g.setColor(Color.red);
        int x = 0, y = 0;
        for (int i = 0; i != counter.count(); i++) {
            g.fillOval(x*25, y*25, 20, 20);
            x++;
            if (x > 7) {
                x = 0;
                y++;
            }
        }
    }
}
```

```
import java.awt.*;
import javax.swing.*;

public class CounterFrame extends JFrame {
    private Counter counter;
    private JLabel label = new JLabel("count = 0");
    private Drawing panel;

    public CounterFrame(Counter c, Drawing p) {
        counter = c;
        panel = p;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(label);
        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(new CountButton("OK", counter, this));
        p2.add(new ExitButton("Exit"));
        cp.add(p1, BorderLayout.NORTH);
        cp.add(panel, BorderLayout.CENTER);
        cp.add(p2,BorderLayout.SOUTH);
        setTitle("Counter");
        setSize(200,280);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** update - 뷰 갱신 */
    public void update() {
        label.setText("count = " + counter.count());
        panel.repaint();
    }

}
```

```
public class CounterStarter {

    public static void main(String[] args) {
        Counter model = new Counter(0);
        Drawing panel = new Drawing(model);
        new CounterFrame(model,panel);
    }

}
```

### 9-2. 실습 : 슬라이드 퍼즐 게임 (GUI 버전)

지난 실습 시간에 작성한 퍼즐 게임을 다음의 요구사항와 맟추어 개선하자.

- 애플리케이션을 실행하면 다음과 같은 퍼즐 보드 창을 띄운다.

<img src="https://i.imgur.com/OyBBBB0.png" width="270">

- 상단에 위치한 `Start` 버튼을 누르면 아래와 같이 우하단 구석에 있는 한 칸을 비운 채로 퍼즐 조각을 무작위로 섞은 퍼즐 보드가 생기며 퍼즐 게임을 시작한다.
- 퍼즐 조각을 움직여 퍼즐 보드를 위와 똑같이 만들면 게임이 끝난다.

<img src="https://i.imgur.com/XcoAmNy.png" width="270">

- 퍼즐 보드의 재배치를 완료하면 다음과 같이 우하단 빈 칸에 `Done` 이라고 표시되면서 게임이 끝난다.

<img src="https://i.imgur.com/nL11nXI.png" width="270">

- 게임이 끝나면 어떤 버튼을 누르더라도 퍼즐 조각이 움직이지 않아야 한다.
- 언제든지 `Start` 버튼을 누르면 다시 처음부터 퍼즐 게임을 할 수 있어야 한다.

#### 설계도

<img src="https://i.imgur.com/KcXJLLH.png" width="800">



```
/** PuzzlePiece - 슬라이드 퍼즐 게임 조각  */
public class PuzzlePiece {
    private int face;

    /** Constructor - PuzzlePiece 퍼즐 조각을 만듬
     * @param value - 퍼즐 조각 위에 표시되는 값  */
    public PuzzlePiece(int value) {
        face = value;
    }

    /** face - 조각의 액면 값을 리턴  */
    public int face() {
        return face;
    }
}
```

```
public class SlidePuzzleBoard {

    private PuzzlePiece[][] board;
    // 빈칸의 좌표
    private int empty_row;
    private int empty_col;
    // representation invariant: board[empty_row][empty_col] == null

    /** Constructor - SlidePuzzleBoard 초기 퍼즐 보드 설정 - 감소하는 순으로 나열
     *  */
    public SlidePuzzleBoard() {
        // 4 x 4 보드 만들기
        board = new PuzzlePiece[4][4];
        // 퍼즐 조각 1~15를 보드에 역순으로 끼우기
        int number = 15;
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++) {
                board[row][col] = new PuzzlePiece(number);
                number -= 1;
            }
        board[3][3] = null;
        empty_row = 3;
        empty_col = 3;
    }

    /** getPuzzlePiece - 퍼즐 조각을 리턴
     * @param row - 가로줄 인덱스
     * @param col - 세로줄 인덱스
     * @return 퍼즐 조각  */
    public PuzzlePiece getPuzzlePiece(int row, int col) {
        return board[row][col];
    }

    /** 이동이 가능하면, 퍼즐 조각을 빈칸으로 이동
     * @param w - 이동하기 원하는 퍼즐 조각의 번호
     * @return 이동 성공하면 true를 리턴하고, 이동이 불가능하면 false를 리턴 */
    public boolean move(int w) {
        int row, col; // w의 위치
        // 빈칸에 주변에서 w의 위치를 찾음
        if (found(w, empty_row - 1, empty_col)) {
            row = empty_row - 1;
            col = empty_col;
        }
        else if (found(w, empty_row + 1, empty_col)) {
            row = empty_row + 1;
            col = empty_col;
        }
        else if (found(w, empty_row, empty_col - 1)) {
            row = empty_row;
            col = empty_col - 1;
        }
        else if (found(w, empty_row, empty_col + 1)) {
            row = empty_row;
            col = empty_col + 1;
        }
        else
            return false;
        // w를 빈칸에 복사
        board[empty_row][empty_col] = board[row][col];
        // 빈칸 위치를 새로 설정하고, w를 제거
        empty_row = row;
        empty_col = col;
        board[empty_row][empty_col] = null;
        return true;
    }

    /** found - board[row][col]에 퍼즐 조각 v가 있는지 확인  */
    private boolean found(int v, int row, int col) {
        if (row >= 0 && row <= 3 && col >= 0 && col <= 3)
            return board[row][col].face() == v;
        else
            return false;
    }
}
```


```
import java.awt.*;
import javax.swing.*;

public class PuzzleFrame extends JFrame {

    private SlidePuzzleBoard board;
    private PuzzleButton[][] button_board;

    public PuzzleFrame(SlidePuzzleBoard b) {
        board = b;
        button_board = new PuzzleButton[4][4];
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(4,4));
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++) {
                button_board[row][col] = new PuzzleButton(board,this);
                cp.add(button_board[row][col]);
            }
        update();
        setTitle("Slide Puzzle");
        setSize(250,250);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void update() {
        // 코드 채우기
    }

}
```

```
public class PuzzleStarter {

    public static void main(String[] args) {
        new PuzzleFrame(new SlidePuzzleBoard());
    }
}
```


### 9-3. 사례 학습 : 슬라이드 퍼즐 (GUI 버전 2.0)

코딩 따라하기


```
import java.util.*;

public class SlidePuzzleBoard {

    private PuzzlePiece[][] board;
    // 빈칸의 좌표
    private int empty_row;
    private int empty_col;
    // representation invariant: board[empty_row][empty_col] == null

    private boolean on = false;

    public SlidePuzzleBoard() {
        board = new PuzzlePiece[4][4];
        // 퍼즐 조각 1~15를 보드에 순서대로 끼우기
        int number = 1;
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++) {
                if (col != 3 || row != 3) {
                    board[row][col] = new PuzzlePiece(number);
                    number += 1;
                } else {
                    board[3][3] = null;
                    empty_row = 3;
                    empty_col = 3;
                }
            }
    }

    /** getPuzzlePiece - 퍼즐 조각을 리턴
     * @param row - 가로줄 인덱스
     * @param col - 세로줄 인덱스
     * @return 퍼즐 조각  */
    public PuzzlePiece getPuzzlePiece(int row, int col) {
        return board[row][col];
    }

    /** on - 게임이 진행중인지 점검하는 함수
     * @return 게임이 진행중이면 true, 아니면 false  */
    public boolean on() {
        return on;
    }

    /** 이동이 가능하면, 퍼즐 조각을 빈칸으로 이동
     * @param w - 이동하기 원하는 퍼즐 조각
     * @return 이동 성공하면 true를 리턴하고, 이동이 불가능하면 false를 리턴 */
    public boolean move(int w) {
        int row, col; // w의 위치
        // 빈칸에 주변에서 w의 위치를 찾음
        if (found(w, empty_row - 1, empty_col)) {
            row = empty_row - 1;
            col = empty_col;
        }
        else if (found(w, empty_row + 1, empty_col)) {
            row = empty_row + 1;
            col = empty_col;
        }
        else if (found(w, empty_row, empty_col - 1)) {
            row = empty_row;
            col = empty_col - 1;
        }
        else if (found(w, empty_row, empty_col + 1)) {
            row = empty_row;
            col = empty_col + 1;
        }
        else
            return false;
        // w를 빈칸에 복사
        board[empty_row][empty_col] = board[row][col];
        // 빈칸 위치를 새로 설정하고, w를 제거
        empty_row = row;
        empty_col = col;
        board[empty_row][empty_col] = null;
        return true;
    }

    /** found - board[row][col]에 퍼즐 조각 v가 있는지 확인  
     * @param v - 확인할 수
     * @param row - 보드의 가로줄 인덱스
     * @param col - 보드의 세로줄 인덱스
     * @return 있으면 true, 없으면 false */
    private boolean found(int v, int row, int col) {
        if (row >= 0 && row <= 3 && col >= 0 && col <= 3)
            return board[row][col].face() == v;
        else
            return false;
    }

    /** createPuzzleBoard - 퍼즐 게임 초기 보드 생성 */
    public void createPuzzleBoard() {
        int[] numbers = generateRandomPermutation(15);
        int i = 0;
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++) {
                // PuzzlePiece 만들어 채우기
            }
        on = true;
    }

    /** generateRandomPermutation - 0~n-1 범위의 정수 수열을 무작위로 섞은 배열을 리턴 한다.
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

    /** gameOver - 퍼즐 게임이 끝났는지를 확인  
     * @return 목표를 달성했으면 true, 아직 더 진행해야 하면 false
     */
    public boolean gameOver() {
        // 코드 채우기

    }

}
```
