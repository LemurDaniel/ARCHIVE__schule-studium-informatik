package aufgabe1.a1;

public enum Fach {
	MATHEMATIKSTUDIUM, INFORMATIKSTUDIUM, ARCHITEKTURSTUDIUM, WIRTSCHAFTLICHESSTUDIUM, BIOLOGIESTUDIUM, GESCHICHTSSTUDIUM, GERMANISTIKSTUDIUM, POLITOLOGIESTUDIUM, PHYSIKSTUDIUM;

	public int regelstudienzeit() {			
		switch( Fach.values()[this.ordinal()] ) {
		case POLITOLOGIESTUDIUM: case ARCHITEKTURSTUDIUM: return 8;
		default: return 7;
		}
	}
}