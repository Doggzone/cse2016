```
(c)도경구 version 1.0 (2022/10/26)
```

## 8.  인터페이스와 클래스 계층 구조


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

### 사례 학습 - 카드 게임

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

    public static final int SIZE_OF_ONE_SUIT = 12;

    private String suit;
    private int rank;

    public Card(String s, int r) {
        suit = s;
        rank = r;
    }

    public String suit() {
        return suit;
    }

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

    private int card_count; // 남은 카드 수
    private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT];
    // Invariant: deck[0], .., decl[card_count-1] 에 카드가 있다.

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

    /** newCard - 임의의 새 카드 한 장을 뽑아 줌
    * @return 카드 덱에서 임의로 한 장을 뽑아 리턴
    *         없으면 카드 1벌을 새로 만들고 한 장을 뽑아 리턴   */
    public Card newCard() {
        if (! this.moreCards())
            createDeck();
        int index = (int)(Math.random() * card_count);
        Card card_to_deal = deck[index];
        for (int i = index+1; i < card_count; i++)
            deck[i-1] = deck[i];
        card_count = card_count - 1;
        return card_to_deal;
    }

    /** moreCards - 카드 덱에 카드가 남아있는지 알려줌  
     * @return 있으면 true, 없으면 false */
    public boolean moreCards() {
        return card_count > 0;
    }

}
```

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


```
public class Dealer {

    CardDeck deck;

    public Dealer() {
        deck = new CardDeck();
    }

    public void dealTo(CardPlayerBehavior p) {
        while (p.wantsACard()) {
            Card c = deck.newCard();
            p.receiveCard(c);
        }

    }

    public static void main(String[] args) {
        Dealer d = new Dealer();
        HumanPlayer p = new HumanPlayer(3);
        d.dealTo(p);
        Card[] hand = p.showCards();
        for (int i = 0; i < hand.length; i++) {
            System.out.println(hand[i].getSuit() + " " + hand[i].getRank());
        }
    }
}
```


```
public abstract class CardPlayer implements CardPlayerBehavior {

    private Card[] hand; // 갖고 있는 카드
    private int card_count; // 갖고 있는 카드의 장 수

    /** Constructor CardPlayer - max_cards 카드를 수용가능한 Card 배열 객체를 만들어 CardPlayer 생성
     * @param max_cards - 들고 있을 수 있는 카드의 최대 장수 */
    public CardPlayer(int max_cards) {
        hand = new Card[max_cards];
        card_count = 0;
    }

    /** wantsACard - 카드 한 장을 받겠는지 답한다.
     * @return 카드를 받고 싶으면 true, 아니면 false */
    public abstract boolean wantsACard();

    /** receiveCard - 카드를 한장 받는다. 한도(배열 hand의 크기)를 초과하면 받을 수 없다.
     * @param c - 카드
     * @return 성공적으로 받았으면 true, 그렇지 않으면 false */
    public boolean receiveCard(Card c) {
        if (card_count < hand.length) {
            hand[card_count] = c;
            card_count += 1;
            return true;
        }
        else
            return false;
    }

    /** showCards - 들고 있는 카드를 내준다.
     * @return 들고 있는 카드 전체  */
    public Card[] showCards() {
        Card[] card = new Card[card_count];
        for (int i = 0; i < card_count; i++)
            card[i] = hand[i];
        return card;
    }

}
```

```
import javax.swing.*;

public class HumanPlayer extends CardPlayer {

    public HumanPlayer(int max_cards) {
        super(max_cards);
    }

    public boolean wantsACard() {
        String response = JOptionPane.showInputDialog("한장 더 드릴까요? (Y/N)");
        return response.equals("Y");
    }
}
```

```
import javax.swing.JOptionPane;

public class ComputerPlayer extends CardPlayer {

    public ComputerPlayer(int max_cards) {
        super(max_cards);
    }

    public boolean wantsACard() {
        boolean decision;
        Card[] cards = showCards();
        // 게임에 따라 다른 전략을 세움
        return true;
    }

}
```
