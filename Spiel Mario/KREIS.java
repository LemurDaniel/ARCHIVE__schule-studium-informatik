/**
 * Write a description of class KREIS here.
 * 
 * @author (Daniel Landau) 
 * @version (04.05.2015v1)
 */

public class KREIS {

    private int mittelpunktX;

    private int mittelpunktY;

    private int radius;

    private String fuellfarbe;
    
    private boolean fuellung;           


    public KREIS() {
        this(30, 60, 20, "blau", false);
        ZEICHENFENSTER.gibFenster().setzeSichtbar(true);
    }
    
    public KREIS(int posx, int posy, int r,  String color, boolean filled) // Methode um einen Kreis mit den jeweiligen Werten zu zeichen
    {
        mittelpunktX = posx;  // Position des Mittelpunktes auf x-Achse
        mittelpunktY = posy;  // Position des Mittelpunktes auf y-Achse
        radius = r;           // radius des Kreises
        fuellfarbe = color;   // Farbe mit der der Kreis gefüllt wird
        fuellung = filled;    // true/false Wert ob der Kreis gefuellt wird oder nicht
        zeichnen();           // zeichen des Kreises
    }

    public void aendereFarbe(String newcolor){  // aendern der Füllfarbe
        loeschen();
        fuellfarbe = newcolor;
        zeichnen();
    }
    
    public void aenderePosition(int newpositionx, int newpositiony){ // aendern der Position
        loeschen();
        mittelpunktX = newpositionx;
        mittelpunktY = newpositiony;
        zeichnen();
    }
    
    public void aendereRadius(int newradius){  // aender des Radiuses
        loeschen();
        radius = newradius;
        zeichnen();
    }
    
    public void Fuellung(boolean filled){  // aendern ob mit Farbe geüllt oder nicht
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
     public void bewegex(int newposx,String direction){   // bewegen auf der x-Achse um angegebenen Werte "newposx"
        loeschen();                                      // entscheiden über Bewegunsrichtung auf x-Achse mit "direction"
        
        switch(direction){
        case "rechts":  // Bewegen nach rechts auf x-Achse um den Wert newposx
        mittelpunktX += newposx;  // Formel um neue  Position des Mittelpunktes zu errechenen
        break;
        
        case "links":  // Bewegen nach links auf x-Achse um den Wert newposx
        mittelpunktX -= newposx;  // Formel um neue  Position des Mittelpunktes zu errechenen
        break;
        
        default:
        Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Ungültige Richtungsangabe bei Bewegung auf der x-Achse \nKlasse: KREIS\nMethode: bewegex");  // Fehlermeldung bei ungültiger Eingabe der "direction" 
        System.exit(0);
        }
        
		zeichnen();
    }
    
    public void bewegey(int newposy, String direction){  // bewegen auf der y-Achse um angegebenen Werte "newposy"
        loeschen();                                      // entscheiden über Bewegunsrichtung auf y-Achse mit "direction"
        
        switch(direction){
        case "hoch":  // Bewegen nach oben auf y-Achse um den Wert newposy
        mittelpunktY -= newposy;  // Formel um neue  Position des Mittelpunktes zu errechenen
        break;
        
        case "runter":  // Bewegen nach unten auf y-Achse um den Wert newposy
        mittelpunktY += newposy;  // Formel um neue  Position des Mittelpunktes zu errechenen
        break;
        
        default:
        Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Ungültige Richtungsangabe bei Bewegung auf der y-Achse \nKlasse: RECHTECK\nMethode: bewegey");  // Fehlermeldung bei ungültiger Eingabe der "direction" 
        System.exit(0);
        }
        
		zeichnen();
    }
    
    public void zeichnen() {  // Methode zum zeichnen des Kreises
        if(fuellung==true){  // prüfen ob es mit oder ohne Füllung gezeichnet werden soll
        ZEICHENFENSTER.gibFenster().fuelleKreis(mittelpunktX, mittelpunktY, radius, fuellfarbe);       // zeichnen mit Füllung        
        }else{ 
        ZEICHENFENSTER.gibFenster().zeichneKreis(mittelpunktX, mittelpunktY, radius);                  // eichen ohne Füllung
        }
     }
     
     public void loeschen() {  // Methode zum loeschen des Kreises
        if(fuellung==true){  // prüfen ob es mit oder ohne Füllung gezeichnet werden soll
        ZEICHENFENSTER.gibFenster().loescheKreis(mittelpunktX, mittelpunktY, radius);  // loeschen des Kreises       
        }else{ 
        ZEICHENFENSTER.gibFenster().loescheKreis(mittelpunktX, mittelpunktY, radius+1);  // Mit radius+1 da der Kreis sonst nicht komplett gelöscht wird
        }
     }


}
