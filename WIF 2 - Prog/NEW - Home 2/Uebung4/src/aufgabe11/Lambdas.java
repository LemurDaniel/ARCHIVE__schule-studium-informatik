package aufgabe11;

public class Lambdas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		empty e = () -> System.out.println("lambda!");
		ausgabe a = () -> "lambda";
		ausgabe2 a2 = s -> s.toLowerCase();
		ausgabe3 a3 = s -> s.contains("=");
		ausgabe4 a4 = (s,t) -> s.contains(t);
		ausgabe5 a5 = (s,t) -> s.concat(t);
		ausgabe6 a6 = (s, ch) -> s.indexOf(ch);
		ausgabe7 a7 = (s,ch,fromIndex) -> s.indexOf(ch, fromIndex);
		
		ausgabeB b = s -> System.out.println(s);		
		ausgabeB b2 = System.out::println;
		
		
		e.print();
		System.out.println(a.gibAus());
		System.out.println(a2.gibAus("AAAAAA2"));
		System.out.println(a3.gibAus("="));
		System.out.println(a4.gibAus("aaa", "addd"));
		System.out.println(a5.gibAus("aaa", "bbb"));
		System.out.println(a6.gibAus("aaa", 'a'));
		System.out.println(a7.gibAus("aaa", 'a', 2));
		
		b.gibAus("\n B-Test");
		b2.gibAus(" B2-Test");
	}

	
	@FunctionalInterface
	interface empty{
		void print();
	}
	
	@FunctionalInterface
	interface ausgabe{
		String gibAus();
	}
	
	@FunctionalInterface
	interface ausgabe2{
		String gibAus(String s);
	}
	
	@FunctionalInterface
	interface ausgabe3{
		boolean gibAus(String s);
	}
	
	@FunctionalInterface
	interface ausgabe4{
		boolean gibAus(String s, String t);
	}
	
	@FunctionalInterface
	interface ausgabe5{
		String gibAus(String s, String t);
	}
	
	@FunctionalInterface
	interface ausgabe6{
		int gibAus(String s, char ch);
	}
	
	@FunctionalInterface
	interface ausgabe7{
		int gibAus(String s, char ch, int fromIndex);
	}
	
	@FunctionalInterface
	interface ausgabeB{
		void gibAus(String s);
	}
}
