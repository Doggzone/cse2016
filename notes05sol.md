```
(c)도경구 version 1.0 (2022/10/13) 숙제 답안 코드 
```

## 숙제 답안 코드

```
import java.awt.*;
import javax.swing.*;

public class GameBoard extends JPanel {
	
    private int size = 300;
    private Player player1, player2;
	
    public GameBoard(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        JFrame f = new JFrame();
        f.setTitle("주사위 게임");
        f.setSize(size, size);
        f.getContentPane().add(this);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
	
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.black);
        g.drawString(player1.name(), 80, 100);
        g.drawString(player2.name(), 200, 100);
        g.drawString(Integer.toString(player1.points()), 80, 130);
        g.drawString(Integer.toString(player2.points()), 200, 130);
        if (player1.wins())
            g.drawString("이겼다!", 80, 160);
        else if (player2.wins())
            g.drawString("이겼다!", 200, 160);
        else {
            g.drawString("비겼다!", 140, 160);
        }
        g.drawString(Integer.toString(player1.rolled().face1()), 80, 190);
        g.drawString(Integer.toString(player1.rolled().face2()), 110, 190);
        g.drawString(Integer.toString(player2.rolled().face1()), 200, 190);
        g.drawString(Integer.toString(player2.rolled().face2()), 230, 190);
    }
	
    public void display(Player winner, Player loser) {
        displayDice(winner);
        displayDice(loser);
        System.out.println("승자 = " + winner.name());
        System.out.println(winner.name() + " 누적 점수 = " + winner.points());
        System.out.println(loser.name() + " 누적 점수 = " + loser.points());
    }
	
    public void displayDraw(Player p1, Player p2) {
        displayDice(p1);
        displayDice(p2);
        System.out.println("비겼습니다.");
        System.out.println(p1.name() + " 누적 점수 = " + p1.points());
        System.out.println(p2.name() + " 누적 점수 = " + p2.points());
    }
	
    private void displayDice(Player p) {
        Dice dice_rolled;
        dice_rolled = p.rolled();
        System.out.println(dice_rolled.face1() + ", " + dice_rolled.face2());
    }

}
```

```
import javax.swing.*;

public class Dealer {
	
    public void dealDiceGame(Player p1, Player p2, GameBoard b, Registrar r) {		
        // 차례 정하기 
        // 누적 점수가 낮은 쪽이 선공 
        Player first, second;
        if (p1.points() < p2.points()) {
            first = p1; second = p2;
        }
        else if (p1.points() > p2.points()) {
            first = p2; second = p1;
        }
        // 동점이면 동전 던지기
        else if ((int)(Math.random() * 2) == 0) {
            first = p1; second = p2;
        }
        else {
            first = p2; second = p1;
        }
        // JOptionPane.showMessageDialog(null, first.name()+" 먼저 굴립니다.");
        // 주사위 굴리기 
        first.play(new Dice());
        second.play(new Dice());
        // 승패 결정, 디스프레이 
        Dice d1 = first.rolled();
        Dice d2 = second.rolled();
        if (d1.twin() && d2.twin()) {
            if (d1.sum() > d2.sum())
                beats(first, second, b);
            else if (d1.sum() < d2.sum())
                beats(second, first, b);
            else
                b.repaint();
        }
        else if (d1.twin())
            beats(first, second, b);
        else if (d2.twin())
            beats(second, first, b);
        else {
            if (d1.sum() > d2.sum())
                beats(first, second, b);
            else if (d1.sum() < d2.sum())
                beats(second, first, b);
            else if (d1.difference() < d2.difference())
                beats(first, second, b);
            else if (d1.difference() > d2.difference())
                beats(second, first, b);
            else
                b.repaint();
        }

        if (r.wantToContnue() == 0) {
            p1.reset(); 
            p2.reset();
            this.dealDiceGame(p1, p2, b, r);
        }
        else
            System.exit(0);
    }
	
    private void beats(Player p1, Player p2, GameBoard b)  {
        p1.receivePoint();
        b.repaint();
    }

}
```
