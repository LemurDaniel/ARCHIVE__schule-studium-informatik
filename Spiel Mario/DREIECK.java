
/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (05.05.2015v1)
 */

public class DREIECK {

	private int [] positionX = new int[3];

	private int [] positionY = new int[3];
	
	private String fuellfarbe;
	
	private boolean fuellung;


    public DREIECK(){
        this(100, 100, 80, 80, 120, 120, "rot", true);
    }
    
    public DREIECK(int posx1, int posy1, int posx2, int posy2, int posx3, int posy3, String color, boolean filled){  // Methode um ein Dreieck mit den jeweiligen Werten zu zeichen
        positionX[0] = posx1; //Position der 1.Ecke auf x-Achse
        positionY[0] = posy1; //Position der 1.Ecke auf y-Achse
        positionX[1] = posx2; //Position der 2.Ecke auf x-Achse
        positionY[1] = posy2; //Position der 2.Ecke auf y-Achse
        positionX[2] = posx3; //Position der 3.Ecke auf x-Achse
        positionY[2] = posy3; //Position der 3.Ecke auf y-Achse
        fuellfarbe = color;   // Farbe mit der das Dreieck gefüllt wird
        fuellung = filled;    // true/false Wert ob das Dreieck gefuellt wird oder nicht
        zeichnen();           // zeichen des Dreiecks
    }
    
    public void aenderePosition(int newposx1, int newposy1, int newposx2, int newposy2, int newposx3, int newposy3){  // aendern der Position
        loeschen();
        positionX[0] = newposx1;
        positionY[0] = newposy1;
        positionX[1] = newposx2;
        positionY[1] = newposy2;
        positionX[2] = newposx3;
        positionY[2] = newposy3;
        zeichnen();
    }
    
    public void aendereFarbe(String newcolor){  // aendern der Füllfarbe
        loeschen();
        fuellfarbe = newcolor;
        zeichnen();
    }
    
    public void Fuellung(boolean filled){  // aendern ob mit Farbe geüllt oder nicht
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
    public void bewegex(int newposx1, int newposx2, int newposx3, String direction){  // bewegen auf der x-Achse um angegebenen Werte "newposx"
        loeschen();                                                                   // entscheiden über Bewegunsrichtung auf x-Achse mit "direction"
        
        switch(direction){
        case "rechts":  // Bewegen nach rechts auf x-Achse um den Wert newposx
        positionX[0] = positionX[0] + newposx1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionX[1] = positionX[1] + newposx2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionX[2] = positionX[2] + newposx3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        case "links":  // Bewegen nach links auf x-Achse um den Wert newposx
        positionX[0] = positionX[0] - newposx1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionX[1] = positionX[1] - newposx2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionX[2] = positionX[2] - newposx3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        default:
        System.out.print("Ungültige Eingabe");  // Fehlermeldung bei ungültiger Eingabe der "direction"
        break;
        }
        
		zeichnen();
    }
    
    public void bewegey(int newposy1, int newposy2, int newposy3, String direction){  // bewegen auf der y-Achse um angegebenen Werte "newposy"
        loeschen();                                                                  // entscheiden über Bewegunsrichtung auf y-Achse mit "direction"
        
        switch(direction){
        case "hoch":  // Bewegen nach oben auf y-Achse um den Wert newposy
        positionY[0] = positionY[0] - newposy1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionY[1] = positionY[1] - newposy2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionY[2] = positionY[2] - newposy3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        case "runter":  // Bewegen nach unten auf y-Achse um den Wert newposy
        positionY[0] = positionY[0] + newposy1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionY[1] = positionY[1] + newposy2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionY[2] = positionY[2] + newposy3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        default:
        System.out.print("Ungültige Eingabe");  // Fehlermeldung bei ungültiger Eingabe der "direction"
        break;
        }
        
		zeichnen();
    }
    
    public void zeichnen(){  // Methode zum zeichnen des Dreiecks
        if (fuellung==true){  // prüfen ob es mit oder ohne Füllung gezeichnet werden soll
            ZEICHENFENSTER.gibFenster().fuelleDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2],fuellfarbe);       // zeichnen mit Füllung
        }else{
            ZEICHENFENSTER.gibFenster().zeichneDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2]);                 // Zeichen ohne Füllung
        }
   }
   
   public void loeschen(){ // Methode zum loeschen des Dreiecks
       ZEICHENFENSTER.gibFenster().loescheDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2]);
    }
    
}
