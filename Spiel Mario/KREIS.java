
/**
 * Klasse f�r eine Kreis-Figur
 * 
 * Ein Objekt dieser Klasse benutzt zur Darstellung
 * seines Grafiksymbols ein Objekt der Klasse ZEICHENFLAECHE
 * 
 * @author U.Freiberger
 * @version 1.0
 */

public class KREIS {

    private int mittelpunktX;

    private int mittelpunktY;

    private int radius;

    private String fuellfarbe;
    
    private boolean sichtbar;
    
    private int kennung;

    
    /**
     * Erzeugt einen neuen Kreis mit einer Standardposition und einer
     * Standardf�llfarbe. Das zugeh�rige Symbol wird sofort angezeigt. 
     */
    public KREIS() {
        mittelpunktX = 30;
        mittelpunktY = 60;
        radius = 20;
        fuellfarbe = "blau";
        sichtbar = true;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artKreis);
        Zeichnen();
    }
  public KREIS(int x, int y, int r,  String farbe)
    {
        mittelpunktX = x;
        mittelpunktY = y;
        radius = r;
        fuellfarbe = farbe;
        sichtbar = true;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artKreis);
        Zeichnen();
		
    }
    /**
     * Den Mittelpunkt dieses Kreises auf die neuen Werte
     * setzen.
     */
    public void MittelpunktSetzen(int neuesX, int neuesY) {
        mittelpunktX = neuesX;
        mittelpunktY = neuesY;
        Zeichnen();
    }

    /**
     * Den Radius dieses Kreises auf den neuen Wert setzen.
     */
    public void RadiusSetzen(int neuerRadius) {
        radius = neuerRadius;
        Zeichnen();
    }

    /**
     * Die F�llfarbe dieses Kreises auf 'neueFarbe' setzen. 
     * Erlaubte Parameterwerte sind:
     * "rot", "gruen", "blau", "gelb", "cyan", "magenta", "schwarz", "weiss"
     * "hellgelb", "hellgruen", "orange", "braun"
     */
    public void FarbeSetzen(String neueFarbe) {
        fuellfarbe = neueFarbe;
        Zeichnen();
    }
    
    /**
     * Die Sichtbarkeit dieses Kreises ein- oder ausschalten.
     * Erlaubte Parameterwerte: true, false
     */
    public void SichtbarSetzen(boolean neueSichtbarkeit) {
        sichtbar = neueSichtbarkeit;
        Zeichnen();
       }
       
    /**
     * Zeichne f�r diesen Kreis ein entsprechendes Grafiksymbol 
     * in dem Grafikfenster.
     * Nach jeder �nderung muss der Kreis diese �nderung seinem 
     * Grafiksymbol mitteilen.
     */
    public void Zeichnen() {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, mittelpunktX-radius, 
        		mittelpunktY-radius, 2*radius, 2*radius, fuellfarbe, sichtbar);      
     }


}
