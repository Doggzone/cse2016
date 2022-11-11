```
(c)도경구 version 1.0 (2022/11/11)
```

## 9. GUI와 이벤트 구동 프로그래밍

### 9-3. 사례 학습 : Scrolling List

코딩 따라 읽기

```
public class Counter {

    private int count;

    /** Counter - 카운터 초기 설정
     * @param n - 카운터의 초기값 */
    public Counter(int n) {
        count = n;
    }

    /** increment - 카운터 값 증가 */
    public void increment() {
        count += 1;
    }

    /** count - 카운터 값 리턴
     * @return 카운터 현재 값  */
    public int count() {
        return count;
    }

}
```

```
public class Counter2 extends Counter {

    private int index;

    public Counter2(int n, int i) {
        super(n);
        index = i;
    }

    public String toString() {
        return "Counter " + index + " has " + count();
    }
}
```

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
import java.awt.event.*;
import javax.swing.*;

public class ListButton extends JButton implements ActionListener {
    private Counter2[] counters;
    private ListFrame frame;

    public ListButton(String label, Counter2[] c, ListFrame v) {
        super(label);
        counters = c;
        frame = v;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        int choice = frame.getSelection();
        if (choice != -1) {
            counters[choice].increment();
            frame.update();
        }
    }
}
```

```
import java.awt.*;
import javax.swing.*;

public class ListFrame extends JFrame {
    private Counter2[] counters;
    private JList items;

    public ListFrame(Counter2[] c) {
        counters = c;
        items = new JList(counters);  
        JScrollPane sp = new JScrollPane(items);  
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(2,1));
        cp.add(sp);
        JPanel p = new JPanel(new GridLayout(2,1));
        p.add(new ListButton("Go", counters, this));
        p.add(new ExitButton("Quit"));
        cp.add(p); 	
        update();
        setTitle("ListExample");
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public int getSelection() {
        return items.getSelectedIndex();
    }

    public void update() {
        items.clearSelection();
    }
}
```

```
public class Starter {

    public static void main(String[] args) {
        int how_many_counters = 8;
        Counter2[] counters = new Counter2[how_many_counters];
        for (int i = 0; i < how_many_counters; i++)
            counters[i] = new Counter2(0,i);
        new ListFrame(counters);
    }

}
```
