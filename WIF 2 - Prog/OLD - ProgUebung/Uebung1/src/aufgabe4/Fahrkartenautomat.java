package aufgabe4;

public class Fahrkartenautomat {
	
	//
	private static final int[][] muenzen =	{ 	{ 2, 1},
												{ 50, 20, 10, 5}
											};
	
	private int euro, cent;
	private int[] eingabe = new int[2];
	//
	Fahrkartenautomat(){
		euro = cent = 5;
		eingabe[0] = eingabe[1] = 0;
	}
	
	//
	public boolean fahrpreisSetzen(double preis) {

		if( eingabePruefung(preis) ) {
			String[] p = Double.toString(preis).split(".");
			p[1] += p[1].length()==1 ? "0":"";
			euro = Integer.valueOf(p[0]);
			cent = Integer.valueOf(p[1]);
			return true;
		}
		return false;		
	}
	
	public void geldEingeben(double e) {
		
		System.out.println(e);
		
		if (eingabePruefung(e)) {
			String[] p = Double.toString(e).split("\\.");
			p[1] += p[1].length()==1 ? "0":"";
			eingabe[0] += Integer.valueOf(p[0]);
			eingabe[1] += Integer.valueOf(p[1]);
			
			if (eingabe[1]>100) {
				eingabe[0] += eingabe[1]/100;
				eingabe[1] %= 100;
			}
			
			p[1] += p[1].length()==1 ? "0":"";
			System.out.printf("	Es wurden %s.%s€ eingezahlt\n", p[0], p[1]);
			fehlendesGeld();
		}
		else
			System.out.println("ungültige Eingabe");
		
	}
	
	public void rueckgabe() {	
		System.out.print("	Wechselgeld: ");
		for(int i=0, i2=0 ; i2<2 ; i++) {	
			
			System.out.printf("%d*%d%s", eingabe[i2]/muenzen[i2][i], muenzen[i2][i], i2==0 ? "€":"c");
			System.out.print( i==muenzen[i2].length-1 && i2==1 ? "\n":"; ");
			eingabe[i2] %= muenzen[i2][i];
			
			if(i==muenzen[i2].length-1) {
				i = 0;
				i2++;
			}
				
		}
		
		
	}
	
	public void fehlendesGeld() {
		if(status()) {
			System.out.println("	Es fehlt kein Geld mehr");
			return;
		}
		
		
		String cent, euro;
		if(this.cent-eingabe[1] < 0) {
			cent = 100 - eingabe[1] + this.cent +"";
			cent = (cent.length()==1 ? "0":"") + cent;
			euro = this.euro - eingabe[0]-1 +"";
		}else {	
			cent = (this.cent-eingabe[1] > 9 ? "":"0") + (this.cent-eingabe[1]);
			euro = this.euro - eingabe[0]+"";
		}
		System.out.printf("	Es fehlen noch: %s.%s€ \n", euro, cent);
	}
	
	public void eingezahlt() {
		String cent = (eingabe[1] > 9 ? "":"0") + eingabe[1];
		System.out.printf("	Es befinden sich: %d.%s€ im Automaten. \n", eingabe[0], cent);
	}
	
	public void fahrkarteLoesen() {
		if (status()) {
			eingabe[0] -= euro;
			eingabe[1] -= cent;
			System.out.println("*** Fahrkarte gelöst ***");
			rueckgabe();
		}else
			fehlendesGeld();
	}	
	
	public boolean status() {
		if (eingabe[0] >= euro && eingabe[1] >= cent)
			return true;
		return false;
	}
	
	private boolean eingabePruefung(double preis) {
		String[] p = Double.toString(preis).split("\\.");
		p[1] += p[1].length()==1 ? "0":"";
		
		if (p[1].length() > 2)
			return false;	
		else if ( Integer.valueOf(p[1])%5 != 0 )
			return false;
		return true;
	}
	
	
	//
	public void getPreis() {
		String cent = (this.cent>9 ? "":"0") +this.cent;
		System.out.printf("	Fahrkartenpreis: %d.%s€ \n", euro, cent);
	}

}
