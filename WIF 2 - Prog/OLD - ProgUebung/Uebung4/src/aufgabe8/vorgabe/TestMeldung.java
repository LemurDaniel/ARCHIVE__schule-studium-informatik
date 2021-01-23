package aufgabe8.vorgabe;

public class TestMeldung {

	Melden melden;
	
	public TestMeldung() {
		
		melden = new Melden() {
			@Override
			public void ausgabe(Meldung m) {
				System.out.println(m.getTyp()+ ": "+m.getInhalt());
			}
		};
		
	}
	
	
	Melden getMelden() {
		return melden;
	}
	
	
	public static void main(String[] args) {
		TestMeldung tm = new TestMeldung();
		Meldung m = new Meldung("Status", "gerade gestartet");
		tm.getMelden().ausgabe(m);
	}
}
