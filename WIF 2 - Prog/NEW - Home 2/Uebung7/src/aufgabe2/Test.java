package aufgabe2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		
//		Vorhersagen:
//		
//		
//		a) Kaefig<Tier> zwinger = new Kaefig<Hund>();
//		-übersetzt nicht	
//		
//		
//		b) Kaefig<Vogel> voliere = new Kaefig<Tier>();
//		-übersetzt nicht
//		
//		
//		
//		c) Kaefig<?> voliere = new Kaefig<Vogel>();
//		voliere.setTier(new Vogel());
//		-übersetzt nicht
//		
//		
//		
//		
//		d) Kaefig voliere = new Kaefig();
//		voliere.setTier(new Vogel());
//		ja geht, aber Rawtype warnung
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
		
		
		//Kaefig<Tier> zwinger = new Kaefig<Hund>();
		// Kaefig<Vogel> voliere = new Kaefig<Tier>();
//		Kaefig<?> voliere = new Kaefig<Vogel>();
//		voliere.setTier(new Vogel());

//		Kaefig voliere = new Kaefig();
//		voliere.setTier(new Vogel());
		
		
		Kaefig<Tier> test = new Kaefig<>();
		test.setTier(new Vogel());
		test.setTier(new Hund());
		//test.setTier(5);
	}

}
