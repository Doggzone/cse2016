
## 실습

### 준비

- `Eclipse`를 개인 컴퓨터에 설치한다.
- 강의 시간에 시연한 `Hello, World!` 애플리케이션을 강의 노트에 기술한대로 똑같이 따라서 작성하고 실행하여 문제없는지 확인한다.


### [문제] 현재 시각 출력 애플리케이션 

- 아래 두 애플리케이션은 현재 시각을 출력하는 애플리케이션이다.
각각 실행하여 어떤 결과가 나오는지 확인해보자. 

#### 실행창 출력 버전

```
import java.time.*;

public class Clock {
	public static void main(String[] args) {
		System.out.println(LocalTime.now());
	}
}
```

- 이 애플리케이션을 실행하는데 등장한 객체를 찾아내고, 어떤 객체가 어떤 객체에게 어떤 메시지를 전달하며 실행했는지 그림을 그려서 실행 추적을 해보자.

#### Swing package 활용 버전

```
import javax.swing.*;
import java.time.*;

public class Clock {
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, LocalTime.now());
	}
}
```

<img src="https://i.imgur.com/m3LHtJU.png" width="300">

- 이 애플리케이션을 실행하는데 등장한 객체를 찾아내고, 어떤 객체가 어떤 객체에게 어떤 메시지를 전달하며 실행했는지 그림을 그려서 실행 추적을 해보자.











