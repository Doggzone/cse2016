```
(c)도경구 version 1.0 (2022/10/13) 숙제 완성 코드 
```

## 숙제 완성 코드

```
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class ClockWriter extends JPanel {

	private final int SIZE;

    public ClockWriter(int n) {
    	SIZE = n;
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(SIZE+50, SIZE+150);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("TIME IS GOLD", 105, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(25, 100, SIZE, SIZE);
        System.out.println("Hello");
        // 현재 시간 
        LocalTime time = LocalTime.now();
        int radius = SIZE / 2;
        int x1 = 25 + radius;
        int y1 = 100 + radius;
        int diff = radius / 5;
        // 초침  
        int second = time.getSecond() * radius / 60;
        int x0 = 25 + radius - second;
        int y0 = 100 + radius - second;
        g.setColor(Color.PINK);
        g.fillOval(x0, y0, second * 2, second * 2);
        // 분침 
        radius -= diff;
        double minute_angle = (time.getMinute() - 15) * Math.PI / 30;
        int x2 = (int)(x1 + radius * Math.cos(minute_angle));
        int y2 = (int)(y1 + radius * Math.sin(minute_angle));
        g.setColor(Color.RED);
        g.drawLine(x1, y1, x2, y2);
        // 시침 
        radius -= diff;
        double hour_angle = (time.getHour() - 3) * Math.PI / 6 + minute_angle / 12;
        x2 = (int)(x1 + radius * Math.cos(hour_angle));
        y2 = (int)(y1 + radius * Math.sin(hour_angle));
        g.setColor(Color.YELLOW);
        g.drawLine(x1, y1, x2, y2);
    }

    // test code
    public static void main(String[] args) {
        new ClockWriter(250);
    }
}
```