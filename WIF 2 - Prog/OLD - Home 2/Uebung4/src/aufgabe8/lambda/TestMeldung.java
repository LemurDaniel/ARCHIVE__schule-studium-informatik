package aufgabe8.lambda;

import aufgabe8.vorgabe.Meldung;;

public class TestMeldung {
	
	Melden melden;
	
	public TestMeldung() {
		melden = m -> System.out.println(m.getTyp()+ ": "+m.getInhalt());
	}
	
	public Melden getMelden() {
		return melden;
	}
	
	public static void main(String[] args) {
		TestMeldung tm = new TestMeldung();
		Meldung m = new Meldung("Status", "gerade gestartet");
		
		tm.getMelden().ausgabe(m);
	}
	
		
	@FunctionalInterface
	interface Melden {
		void ausgabe(Meldung m);
	}
}
