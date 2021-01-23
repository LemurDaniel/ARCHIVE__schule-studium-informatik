package aufgabe5;

public class Test {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		A x = new A();
		B y = new B();
		B z = new A();
		C c = new A();

		
		//////////////////////
		
		//1.
		//x = y;
		// Type Mismatch --> Kompilierfehler
		
		//2.
		//x = (A)y;
		// B cannot be Cast to A
		
		//3.
		//x = (C)y;
		// Type Mismatch --> Kompilierfehler
		
		//4.
		//y = x;
		
		//5.
		//y = (B)x;
		
		//6.
		//y = (C)x;
		// Type Mismatch --> Kompilierfehler
		
		//7.
		//c = x;
		
		// 8.
		//c = y;
		
		// 9.
		//c = z;
			
		// 10.
		//c = (C)y;
		
		// 11.
		//x = c;
		// Type Mismatch --> Kompilierfehler
		
		// 12.
		//x = (A)c;
		
		// 13.
		//y = (A)c;
		
		// 14.
		//y = (B)c;
		
		// 15.
		//c =(C)z;
	}

}
