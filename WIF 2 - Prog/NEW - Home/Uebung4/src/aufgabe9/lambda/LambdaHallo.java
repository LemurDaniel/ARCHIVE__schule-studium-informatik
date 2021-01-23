package aufgabe9.lambda;

public class LambdaHallo {

	public static void main(String[] args) {
		Printer p = () -> System.out.println("Hallo Welt");		
		
		System.out.println("Lambda");
		p.print();
	}
		
	@FunctionalInterface
	interface Printer{
		void print();
	}
}
