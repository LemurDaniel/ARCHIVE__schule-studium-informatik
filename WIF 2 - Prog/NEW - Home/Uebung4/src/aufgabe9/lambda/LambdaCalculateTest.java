package aufgabe9.lambda;

public class LambdaCalculateTest {
	
	public static void main(String[] args) {
		Rectangle rect = (l, w) -> {return (2*l)*(3*w);};
				
		System.out.println("Lambda");
		System.out.println("Flaeche: " + rect.getArea(4, 3) );
	}
	
	@FunctionalInterface
	interface Rectangle{
		public double getArea(double lenght, double width);
	}
}
