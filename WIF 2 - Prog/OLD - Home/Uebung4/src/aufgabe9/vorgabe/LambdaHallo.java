package aufgabe9.vorgabe;

public class LambdaHallo {

	public static void main(String[] args) {
		Printer p = new Printer() {
			@Override
			public void print() {
				System.out.println("Hallo Welt");
			}
		};
		
		System.out.println("Non Lambda");
		p.print();
		
	}
	
	
	@FunctionalInterface
	interface Printer{
		void print();
	}
}
