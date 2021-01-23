/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (04.05.2015v1)
 */

public class RECHTECK {

	private int positionX;

	private int positionY;

	private int breite;
	
	private int hoehe;
	
	private boolean fuellung;
	
	private String fuellfarbe;
 
    public RECHTECK() {
        this(60, 50, 60, 30, "rot", false);
    }
    
    public RECHTECK(int posx, int posy, int width, int height, String color, boolean filled) // Methode um ein Rechteck mit den jeweiligen Werten zu zeichen
    {
        positionX = posx;    // position auf x-Achse
		positionY = posy;    // position auf y-Achse
		breite = width;      // Breite des Rechtecks
		hoehe = height;      // hoehe des Rechtecks
		fuellfarbe = color;  // Farbe mit der das Recheckt gefüllt wird
		fuellung = filled;   // true/false Wert ob das Rechteck gefuellt wird oder nicht
		zeichnen();          // zeichnen des Rechtecks
    }
    
    public void aendereFarbe(String newcolor){ // aendern der Füllfarbe
        loeschen();
        fuellfarbe = newcolor;
        zeichnen();
    }
    
    public void aenderePosition(int newpositionx, int newpositiony){ // aendern der Position
        loeschen();
        positionX = newpositionx;
        positionY = newpositiony;
        zeichnen();
    }
    
    public void aendereGroesse(int newheight, int newwidth){  // aendern der Groesse
        loeschen();
        hoehe = newheight;
        breite = newwidth;
        zeichnen();
    }
    
    public void aendereFuellung(boolean filled){  // aendern ob mit Farbe geüllt oder nicht
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
    public void bewegex(int newposx,String direction){  // bewegen auf der x-Achse um angegebenen Werte "newposx"
        loeschen();                                     // entscheiden über Bewegunsrichtung auf x-Achse mit "direction"
        
        switch(direction){
        case "rechts":  // Bewegen nach rechts auf x-Achse um den Wert newposx
        positionX = positionX + newposx;  // Formel um neue Position zu errechenen
        break;
        
        case "links":  // Bewegen nach links auf x-Achse um den Wert newposx
        positionX = positionX - newposx;  // Formel um neue Position zu errechenen
        break;
        
        default:
        System.out.print("Ungültige Eingabe");  // Fehlermeldung bei ungültiger Eingabe der "direction" 
        break;
        }
        
		zeichnen();
    }
    
    public void bewegey(int newposy, String direction){  // bewegen auf der y-Achse um angegebenen Werte "newposy"
        loeschen();                                      // entscheiden über Bewegunsrichtung auf y-Achse mit "direction"
        
        switch(direction){
        case "hoch":  // Bewegen nach oben auf y-Achse um den Wert newposy
        positionY = positionY - newposy;  // Formel um neue Position zu errechenen
        break;
        
        case "runter":  // Bewegen nach unten auf y-Achse um den Wert newposy
        positionY = positionY + newposy;  // Formel um neue Position zu errechenen
        break;
        
        default:
        System.out.print("Ungültige Eingabe");  // Fehlermeldung bei ungültiger Eingabe der "direction" 
        break;
        }
        
		zeichnen();
    }
    
    public void zeichnen(){ // Methode zum zeichnen des Rechtecks
        if (fuellung==true){  // prüfen ob es mit oder ohne Füllung gezeichnet werden soll
        ZEICHENFENSTER.gibFenster().fuelleRechteck(positionX, positionY, breite, hoehe,fuellfarbe);        // zeichnen mit Füllung
        }else{
        ZEICHENFENSTER.gibFenster().zeichneRechteck(positionX, positionY, breite, hoehe);                  // zeichen ohne Füllung
        }
     }
     
     public void loeschen(){ // Methode zum loeschen des Rechtecks
       if (fuellung==true){
       ZEICHENFENSTER.gibFenster().loescheRechteck(positionX, positionY, breite, hoehe);  //loeschen der Rechtecks
       }else{
       ZEICHENFENSTER.gibFenster().loescheRechteck(positionX, positionY, breite+1, hoehe+1);  // Mit breite+1 und hohe+1 da das Rechteck sonst nicht komplett gelöscht wird
       }                                                                                        
    }
}
