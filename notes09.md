```
(c)도경구 version 1.0 (2022/11/2)
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
