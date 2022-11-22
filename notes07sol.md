```
(c)도경구 1.0 (2022/10/14) 실습 완성코드
(2022/11/22) 숙제 완성코드
```

## 7. 실습 - 완성 코드

### 슬라이딩 퍼즐 게임

```
public class PuzzlePiece {

    private int face;

    public int face() {
        return face;
    }

    public PuzzlePiece(int n) {
        face = n;
    }

}
```

```
public class SlidePuzzleBoard {

    private PuzzlePiece[][] board;
    private int empty_row;
    private int empty_col;

    public PuzzlePiece[][] board() { return board; }

    public SlidePuzzleBoard() {
        board = new PuzzlePiece[4][4];
        int number = 15;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                board[i][j] = new PuzzlePiece(number);
                number -= 1;
            }
        board[3][3] = null;
        empty_row = 3;
        empty_col = 3;
    }

    public boolean move(int n) {
        int row, col;
        if (found(n,empty_row-1, empty_col)) {
            row = empty_row-1;
            col = empty_col;
        }
        else if (found(n,empty_row+1, empty_col)) {
            row = empty_row+1;
            col = empty_col;
        }
        else if (found(n,empty_row, empty_col-1)) {
            row = empty_row;
            col = empty_col-1;
        }
        else if (found(n,empty_row, empty_col+1)) {
            row = empty_row;
            col = empty_col+1;
        }
        else
            return false;
        board[empty_row][empty_col] = board[row][col];
        board[row][col] = null;
        empty_row = row;
        empty_col = col;
        return true;
    }

    private boolean found(int n, int r, int c) {
        if (r >= 0 && r <= 3 && c >= 0 && c <= 3)
            return board[r][c].face() == n;
        else
            return false;
    }

}
```

```
import java.awt.*;
import javax.swing.*;

public class PuzzleWriter extends JPanel {
    private SlidePuzzleBoard puzzle;
    private final int SIZE; // the size of a puzzle piece in pixel

    public PuzzleWriter(SlidePuzzleBoard p) {
        puzzle = p;
        SIZE = 30;
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setLocation(550,100);
        f.setTitle("Slide Puzzle");
        f.setSize(SIZE*6, SIZE*6+28);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, SIZE*6, SIZE*6);
        PuzzlePiece[][] r = puzzle.board();
        for (int i = 0; i < 4; i = i + 1) {
            for (int j = 0; j < 4; j = j + 1) {
                paintPiece(g, r[i][j], i, j);
            }
        }
    }

    private void paintPiece(Graphics g, PuzzlePiece p, int i, int j) {
        int x = SIZE + (SIZE * j);
        int y = SIZE + (SIZE * i);
        if (p != null) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, SIZE, SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, SIZE, SIZE);
            int face_value = p.face();
            if (face_value < 10)
                g.drawString(face_value + "", x+11, y+20);
            else
                g.drawString(face_value + "", x+7, y+20);
        }
        else {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, SIZE, SIZE);
        }
    }

    public void displayPuzzleBoard() {
        this.repaint();
    }

    public void showNoMove(String s) {
        JOptionPane.showMessageDialog(null, "Error: " + s );
    }

}
```


```
import javax.swing.*;

public class PuzzleController {

    private SlidePuzzleBoard puzzle;
    private PuzzleWriter writer;

    public PuzzleController(SlidePuzzleBoard p, PuzzleWriter w) {
        puzzle = p;
        writer = w;
    }

    public void play() {
        while (true) {
            writer.displayPuzzleBoard();
            String input = JOptionPane.showInputDialog("Your move:");
            int n = Integer.parseInt(input);
            if (! puzzle.move(n))
                writer.showNoMove("Cannot move.");              
        }
    }

}
```

```
public class PuzzleStarter {

    public static void main(String[] args) {
        SlidePuzzleBoard puzzle = new SlidePuzzleBoard();
        PuzzleWriter writer = new PuzzleWriter(puzzle);
        new PuzzleController(puzzle,writer).play();
    }

}
```


## 7. 숙제 - 완성 코드

### 스도쿠 퍼즐 게임

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

    /** 해답 보드를 만든다. */
    private void createSolutionBoard() {
        int[] random_sequence = generateRandomPermutation(9);
        for (int i = 0; i < 9; i++)
            random_sequence[i] += 1;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                solution[i][j] = random_sequence[(j+3*i+i/3)%9];
        for (int k = 0; k < 2; k++)
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 9; j++)
                    if (j % 3 != 2)
                        solution[i+3*(k+1)][j] = solution[i+3*k][j+1];
                    else
                        solution[i+3*(k+1)][j] = solution[i+3*k][j-2];
        shuffleRibbons();
        transpose();
        shuffleRibbons();
        transpose();
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

    /** 스도쿠 퍼즐 보드를 망가트리지 않는 한도 내에서 solution 배열의 가로줄을 무작위로 섞는다. */
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

    /** 2차원 배열 b를 콘솔 윈도우에 보여준다.
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

    /** solution 배열을 puzzle_board 배열에 카피한 뒤, count 개수만큼 무작위로 빈칸(0)으로 만든다.
     *
     * @param count - 비울 빈칸의 개수
     */
    private void createPuzzleBoard(int count) {
        // copy solution board to puzzle board
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                puzzle_board[i][j] = solution[i][j];
        // make holes randomly
        Random random = new Random();
        int count_down = count;
        while (count_down > 0) {
            int i = random.nextInt(9);
            int j = random.nextInt(9);
            if (puzzle_board[i][j] != 0) {
                puzzle_board[i][j] = 0;
                count_down -= 1;
            }
        }
    }

    /** row번 가로줄, col번 세로줄에 digit을 채울 수 있는지 검사하여,
     *  가능하면 채우고 true를 리턴하고, 불가능하면 false를 리턴 한다.
     *
     * @param digit - 빈칸에 채울 수 (1~9 중 하나)
     * @param row - 가로줄 번호
     * @param col - 세로줄 번호
     * @return 퍼즐 보드 조건에 만족하여 빈칸을 채웠으면 true, 만족하지 않으면 false
     */
    public boolean check(int digit, int row, int col) {
        if (puzzle_board[row][col] == 0 && solution[row][col] == digit) {
            puzzle_board[row][col] = digit;
            hole_count -= 1;
            return true;
        }
        else
            return false;
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

    /** 스도쿠 퍼즐 게임을 진행한다. */
    public void playSudokuPuzzle() {
        int i, j, d;
        while (sudoku.countHoles() > 0) {
            i = reader.selectNumber("가로줄 번호를 넣어주세요.") - 1;
            j = reader.selectNumber("세로줄 번호를 넣어주세요.") - 1;
            d = reader.selectNumber("숫자를 넣어주세요.");
            if (sudoku.check(d,i,j))
                writer.repaint();
        }
    }
}
```
