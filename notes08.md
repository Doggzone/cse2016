```
(c)도경구 version 1.0 (2022/10/26)
```

## 8.  상속 : 부품의 재사용


### 사례 학습 - 슬라이드 퍼즐

#### 솔로 구현 버전

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
