```
(c)도경구 version 1.0 (2022/11/17)
```

## 10. 텍스트 및 파일 처리

### 사례 학습 : Payroll

코딩 따라 읽기

##### `PayrollReader.java`

```
import java.io.*;
import java.util.*;

public class PayrollReader {

    private BufferedReader infile;
    private final String EOF = "!";
    private String name;
    private int hours, payrate;

    public PayrollReader(String file_name) {
        try {
            infile = new BufferedReader(new FileReader(file_name));
        }
        catch (Exception e) {
            System.out.println("PayrollReader Error - bad file name: " + file_name);
            throw new RuntimeException(e.toString());
        }
    }

    public String name() { return name; }
    public int hours() { return hours; }
    public int payrate() { return payrate; }

    public void close() {
        try { infile.close(); }
        catch (IOException e) {
            System.out.println("PayrollReader Warning - file close failed");
        }
    }

    public boolean getNextRecord() {
        boolean result = false;
        try {
            if (infile.ready()) {
                String line = infile.readLine();
                StringTokenizer t = new StringTokenizer(line, ",");
                String s = t.nextToken().trim();
                if (! s.equals(EOF))
                    if (t.countTokens() == 2) {
                        name = s;
                        hours = Integer.parseInt((t.nextToken().trim()));
                        payrate = Integer.parseInt((t.nextToken().trim()));
                        result = true;
                    }
                    else {
                        throw new RuntimeException(line);
                    }
            }
        }
        catch (IOException e) {
            System.out.println("PayrollReader Error : " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("PayrollReader Error - bad record format: " + e.getMessage() + " Skipping");
            result = getNextRecord(); // 다음 줄 시도
        }
        return result;
    }

}
```

##### `PayrollWriter.java`

```
import java.io.*;
import java.util.*;

public class PayrollWriter {

    private PrintWriter outfile;

    public PayrollWriter(String file_name) {
        try {
            outfile = new PrintWriter(new FileWriter(file_name));
        }
        catch (Exception e) {
            System.out.println("PayrollWriter Error: " + file_name);
            throw new RuntimeException(e.toString());
        }
    }

    public void close() {
        outfile.close();
    }

    public void printCheck(String name, int payment) {
        outfile.println(name + "," + payment);
    }

    public void printCheck(String s) {
        outfile.println(s);
    }

}
```

##### `PayrollController.java`

```
public class PayrollController {

    public void processPayroll(String in, String out) {
        PayrollReader reader = new PayrollReader(in);
        PayrollWriter writer = new PayrollWriter(out);
        while (reader.getNextRecord()) {
            int payment = reader.hours() * reader.payrate();
            writer.printCheck(reader.name(), payment);
        }
        writer.printCheck("!");
        reader.close();
        writer.close();
    }

}
```

##### `Payroll.java`

```
import javax.swing.*;

public class Payroll {

    public static void main(String[] args) {
        String name_in = JOptionPane.showInputDialog("입력 파일명");
        String name_out = JOptionPane.showInputDialog("출력 파일명");
        if (name_in != null && name_out != null)
            new PayrollController().processPayroll(name_in, name_out);
    }

}
```

##### `in.csv`

```
도경구,24,8000
양준혁,40,12000
모지환,36,10000
!
```
