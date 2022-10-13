```
(c)도경구 version 1.0 (2022/10/13) 숙제 답안 코드 
```

### 숙제 답안 코드

### 문제 1. 오늘부터 100일 뒤는 몇 년, 몇 월, 며칠?

#### `Model.java`

```
import java.time.*;

public class Model {
	
    public LocalDate hundred_days_from_today() {
        LocalDate today = LocalDate.now();
        return today.plusDays(100);
    }
	
    // ... 다른 메소드 동일

}
```

#### `ViewOut.java`

```
import java.time.*;
import javax.swing.*;

public class ViewOut {
	
    public void showDate(LocalDate d, String message) {
        int year = d.getYear();
        int month = d.getMonthValue();
        int day = d.getDayOfMonth();
        String date = year + "년 " + month + "월 " + day + "일";
        JOptionPane.showMessageDialog(null, message + date);
    }
	
    // ... 다른 메소드 동일

}
```

#### `Controller.java`

```
import java.time.*;

public class Controller {
	
    public void control(Model m, ViewOut out) {
        LocalDate d = m.hundred_days_from_today();
        out.showDate(d,"오늘부터 100일 뒤는 며칠?\n");
    }

}
```

#### 시동 클래스: `Calendar.java`

```
import java.time.*;

public class Calendar {
	
    public static void main(String[] args) {
        Model model = new Model();
        ViewOut out = new ViewOut();
        new Controller().control(model, out);
    }

}
```

#### 문제 2. 특정 년,월,일부터 100일 뒤는 몇 년, 몇 월, 며칠?

#### `Model.java`

```
import java.time.*;

public class Model {
	
    public LocalDate hundred_days_from(LocalDate d) {
        return d.plusDays(100);
    }
	
    // ... 다른 메소드 동일

}
```

#### `ViewIn.java` (그대로 재사용)

```
public class ViewIn {
	
    public LocalDate getDate(String message) {
        String y = JOptionPane.showInputDialog(message + "\n 년?");
        String m = JOptionPane.showInputDialog(message + "\n 월?");
        String d = JOptionPane.showInputDialog(message + "\n 일?");
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        return LocalDate.of(year, month, day);
    }

}
```

#### `ViewOut.java`

```
import java.time.*;
import javax.swing.*;

public class ViewOut {
	
    public void showDate(LocalDate d0, LocalDate d100, String message) {
        int year0 = d0.getYear();
        int month0 = d0.getMonthValue();
        int day0 = d0.getDayOfMonth();
        String date0 = year0 + "년 " + month0 + "월 " + day0 + "일";
        int year100 = d100.getYear();
        int month100 = d100.getMonthValue();
        int day100 = d100.getDayOfMonth();
        String date100 = year100 + "년 " + month100 + "월 " + day100 + "일";
        JOptionPane.showMessageDialog(null, date0 + message + date100);
    }
	
    // ... 다른 메소드 동일

}
```

#### `Controller.java`

```
import java.time.*;

public class Controller {
	
    public void control(Model m, ViewIn in, ViewOut out) {
        LocalDate date0 = in.getDate("년, 월, 일을 차례로 입력해주세요.");
        LocalDate date100 = m.hundred_days_from(date0);
        out.showDate(date0, date100, " 부터 100일 뒤는 며칠?\n");
    }

}
```

#### 시동 클래스: `Calendar.java`

```
import java.time.*;

public class Calendar {
	
    public static void main(String[] args) {
        Model model = new Model();
        ViewIn in = new ViewIn();
        ViewOut out = new ViewOut();
        new Controller().control(model, in, out);
    }

}
```

#### 문제 3.

<img src="https://i.imgur.com/5GsIUOZ.png" width="600">


#### `Model.java`

```
public class Model {
	
    public int calculateInvestmentEarnings(int principal, double interest, int month) {
        int total = (int)(principal * Math.pow(1.0 + interest / 100.0, month));
        return total;
    }

}
```

#### `ViewIn.java`

```
import javax.swing.*;

public class ViewIn {
	
    public int getInteger(String message) {
        String input = JOptionPane.showInputDialog(message);
        return Integer.parseInt(input);
    }
	
    public double getDouble(String message) {
        String input = JOptionPane.showInputDialog(message);
        return Double.parseDouble(input);
    }

}
```

#### `ViewOut.java`

```
import javax.swing.*;

public class ViewOut {
	
    public void showResult(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
```

#### `Controller.java`

```
public class Controller {
	
    public void control(Model m, ViewIn in, ViewOut out) {
        int principal = in.getInteger("투자 원금을 원 단위로 입력해주세요.");
        int month = in.getInteger("투자 기간을 월 단위로 입력해주세요.");
        double interest = in.getDouble("월 수익률을 % 단위로 입력해주세요.");
        int total = m.calculateInvestmentEarnings(principal, interest, month);
        String message = month + "개월 후 받으실 금액은 " + total + "원 입니다.";
        out.showResult(message);
    }

}
```

#### 시동 클래스: `BankService.java`

```
public class BankService {
    public static void main(String[] args) {
        Model m = new Model();
        ViewIn i = new ViewIn();
        ViewOut o = new ViewOut();
        new Controller().control(m,i,o);
    }
}
```
