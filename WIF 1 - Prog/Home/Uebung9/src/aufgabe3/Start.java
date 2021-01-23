package aufgabe3;

public class Start {
	
	//	
	
	
	public static void main(String[] args) {
		int z1 = 10;
		int z2 = 20;
		System.out.println("Zahl1: "+z1);
		System.out.println("Zahl2: "+z2);
		System.out.println("-----------------");
		tausche(z1, z2);
		System.out.println("Zahl1: "+z1);
		System.out.println("Zahl2: "+z2);
	}
	
	public static void tausche(int a, int b) {
		int x;
		x = a;
		a = b;
		b = x;
	}

}
