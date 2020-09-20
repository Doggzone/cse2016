import java.text.*;

public class CelsiusToFahrenheit {

	public static void main(String[] args) {
		int c = Integer.parseInt(args[0]);
		double f = (9.0 / 5.0) * c + 32;
		DecimalFormat formatter = new DecimalFormat("0.0");
		System.out.print("섭씨 " + c + "도는 ");
		System.out.println("화씨로 " + formatter.format(f) + "도 입니다.");
	}
}
