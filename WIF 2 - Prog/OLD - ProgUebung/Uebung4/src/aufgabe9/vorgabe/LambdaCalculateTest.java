package aufgabe9.vorgabe;

public class LambdaCalculateTest {
	
	public static void main(String[] args) {
		Rectangle rect = new Rectangle() {
			@Override
			public double getArea(double l, double w) {
				l*=2;
				w*=3;
				return l*w;
			}
		};
		System.out.println("Non Lambda");
		System.out.println("Flaeche: " + rect.getArea(4, 3) );
	}
	
	@FunctionalInterface
	interface Rectangle{
		public double getArea(double lenght, double width);
	}
}
