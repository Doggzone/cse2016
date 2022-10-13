```
(c)도경구 version 1.0 (2022/10/13) 숙제 답안 코드 
```

### 답안 코드

#### `BackService.java`

```
import java.awt.*;
import javax.swing.*;

public class BankService {
   public static void main(String[] args) {
      String input = JOptionPane.showInputDialog("투자 원금을 원 단위로 입력해주세요.");
      int principal = Integer.parseInt(input);
      input = JOptionPane.showInputDialog("투자 기간을 월 단위로 입력해주세요.");
      int month = Integer.parseInt(input);
      input = JOptionPane.showInputDialog("월 수익률을 % 단위로 입력해주세요.");
      double interest = Double.parseDouble(input);
      int total = (int)(principal * Math.pow(1.0 + interest / 100.0, month));
      String message = month + "개월 후 받으실 금액은 " + total + "원 입니다.";
      JOptionPane.showMessageDialog(null, message);
   }
}
```