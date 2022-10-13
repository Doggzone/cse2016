```
(c)도경구 
version 0.5 (2022/10/07) 실습 #6-1, #6-2 답안 코드
version 0.6 (2022/10/09) 실습 #6-3 답안 코드 추가
version 1.0 (2022/10/13) 숙제 답안 코드 추가
```

## 6. 실습 - 답안 코드 

### 상자 속 공 굴리기 애니메이션 (확장)

#### 실습#6-1. 파란 공을 하나 추가

`AnimationWriter` 클래스를 두 개의 공을 그리는 `BallWriter` 객체를 별로로 만들어 운영

```
import javax.swing.*;
import java.awt.*;

public class AnimationWriter extends JPanel {
    
    private BoxWriter box_writer;
    private BallWriter ball_writer1;
    private BallWriter ball_writer2;
    
    public AnimationWriter(BoxWriter box, BallWriter ball1, BallWriter ball2, int size) {
        box_writer = box;
        ball_writer1 = ball1;
        ball_writer2 = ball2;
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setTitle("Bounce");
        f.setSize(size+3, size+30);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void paintComponent(Graphics g) {
        box_writer.paintComponent(g);
        ball_writer1.paintComponent(g);
        ball_writer2.paintComponent(g);
    }

}
```

`BounceController` 클래스를 두 개의 공을 제어하도록 수정 

```
public class BounceController {
    
    private MovingBall ball1;
    private MovingBall ball2;
    private AnimationWriter writer;
    
    public BounceController(MovingBall b1, MovingBall b2, AnimationWriter w) {
        ball1 = b1; 
        ball2 = b2;
        writer = w;
    }
    
    public void runAnimation() {
        while (true) {
            delay(20);
            ball1.move(1);
            ball2.move(1);
            writer.repaint();
        }
    }
    
    /** delay - how_long millisecond 동안 실행 정지  */
    private void delay(int how_long) { 
        try { Thread.sleep(how_long); }
        catch (InterruptedException e) { }
    }

}
```

`BounceTheBall` 클래스 수정 

```
import java.awt.Color;

public class BounceTheBall {

    public static void main(String[] args) {
        int size = 200;
        Box box = new Box(size);
        MovingBall ball1 = new MovingBall(30,30,6,box);
        MovingBall ball2 = new MovingBall(180,120,6,box);
        BoxWriter box_writer = new BoxWriter(size);
        BallWriter ball_writer1 = new BallWriter(ball1,Color.RED);
        BallWriter ball_writer2 = new BallWriter(ball2,Color.BLUE);
        AnimationWriter writer = new AnimationWriter(box_writer,
                                                     ball_writer1,
                                                     ball_writer2,
                                                     size);
        new BounceController(ball1,ball2,writer).runAnimation();
    }

}
```


#### 실습#6-2. 충돌시 진로 수정

`MovingBall` 클래스에 진로 수정 메소드 `reverse()` 추가 

```
    public void reverse() {
        x_velocity = - x_velocity;
        y_velocity = - y_velocity;
    }
```

`BounceController` 클래스의 `runAnimation()` 수정

```
    public void runAnimation() {
        while (true) {
            delay(20);
            ball1.move(1);
            ball2.move(1);
            if (collide(ball1,ball2)) {
                ball1.reverse();
                ball2.reverse();
            }
            writer.repaint();
        }
    }
    
    private boolean collide(MovingBall b1, MovingBall b2) {
        int dist1 = b1.x_pos() - b2.x_pos();
        int dist2 = b1.y_pos() - b2.y_pos();
        double distance = Math.sqrt(Math.pow(dist1,2) + Math.pow(dist2,2));
        return distance <= b1.radius() + b2.radius();
    }
```

#### 실습#6-3. 장애물 설치

- 중앙에 다음과 같은 모양의 적당한 길이의 장애물을 설치한다. (색깔은 자유 선택)

<img src="https://i.imgur.com/M7WbzTJ.png" width="100">

- 공이 이 장애물 위면 또는 아래 면을 만나면 y축 진행 방향을 바꾼다.

`Box` 클래스에 장애물 정보를 담은 필드 변수 추가

```
    private final int BOX_SIZE;
    private final int OBSTACLE_WIDTH;
    private final int OBSTACLE_X0;
    
    public Box(int n) {
        BOX_SIZE = n;
        int width = n / 4;
        OBSTACLE_WIDTH = width;
        OBSTACLE_X0 = (n - width) / 2;
    }
    
    public int box_size() { return BOX_SIZE; }
    public int obstacleWidth() { return OBSTACLE_WIDTH; }
    public int obstacleX0() { return OBSTACLE_X0; } 
```

`Box` 클래스에 장애물 접촉 여부를 알려주는 메소드 추가

```
    public boolean inObstacleContact(int x, int y) {
        return OBSTACLE_X0 <= x && x <= OBSTACLE_X0 + OBSTACLE_WIDTH && y == BOX_SIZE / 2;
    }
```

`BoxWriter` 클래스의 `paintComponent` 메소드에 장애물 그리는 코드 추가

```
    public void paintComponent(Graphics g) {
        int size = box.box_size();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, size, size);
        g.setColor(Color.GRAY);
        int x = box.obstacleX0();
        int y = size / 2 - size / 80;
        int width = box.obstacleWidth();
        int height = size / 40;
        g.fillRect(x, y, width, height);
    }
```

`MovingBall` 클래스의 `move` 메소드에 장애물을 만났을 때 y축의 진행 방향을 바꾸는 코드 추가

```
    public void move(int time_units) {
        x_pos += x_velocity;
        if (container.inHorizontalContact(x_pos)) 
            x_velocity = - x_velocity;
        y_pos += y_velocity;
        if (container.inVerticalContact(y_pos)) 
            y_velocity = - y_velocity;
        if (container.inObstacleContact(x_pos, y_pos))
            y_velocity = - y_velocity;
    }
```


## 6. 숙제 - 답안 코드 

```
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class ClockWriter extends JPanel {
    
    private final int SIZE;
    
    public ClockWriter(int size) {
        SIZE = size;
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setSize(SIZE+50, SIZE+110);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        // 현재시간 알아보기 
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond(); 
        // 디지털 시계
        g.setColor(Color.BLACK);
        String clock = format2(hour) + ":" + format2(minute) + ":" + format2(second);
        g.drawString(clock, 116, 35);
        // 아날로그 시계 
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(25, 60, SIZE, SIZE);
        // 시계 중심 
        int radius = SIZE / 2;
        int x1 = 25 + radius;
        int y1 = 60 + radius;
        // 초침 그리기
        int diameter = (second == 0) ? SIZE : SIZE / 60 * second;
        int base = (SIZE - diameter) / 2;
        g.setColor(Color.PINK);
        g.fillOval(25+base, 60+base, diameter, diameter);
        // 분침 그리기 
        radius -= 30;
        double minute_angle = (minute - 15) * Math.PI / 30;
        int x2 = x1 + (int)(radius * Math.cos(minute_angle));
        int y2 = y1 + (int)(radius * Math.sin(minute_angle));
        g.setColor(Color.RED);
        g.drawLine(x1, y1, x2, y2);
        // 시침 그리기
        radius -= 30;
        double hour_angle = (hour - 3) * Math.PI / 6 + minute_angle / 12;
        x2 = x1 + (int)(radius * Math.cos(hour_angle));
        y2 = y1 + (int)(radius * Math.sin(hour_angle));
        g.setColor(Color.YELLOW);
        g.drawLine(x1, y1, x2, y2);
    }
    
    /** format2 - 1자리 정수이면 앞에 0을 붙여 문자열로 만들어주는 메소  */
    private String format2(int number) {
        if (number < 10)
            return "0" + number;
        else
            return "" + number;
    }
    
}
```