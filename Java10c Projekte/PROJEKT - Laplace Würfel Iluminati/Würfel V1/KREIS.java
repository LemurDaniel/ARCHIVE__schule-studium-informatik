
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

    public KREIS(int x, int y, int rad, String farbe, boolean visible) 
    {
        mittelpunktX = x;
        mittelpunktY = y;
        radius       = rad;
        fuellfarbe   = farbe;
        sichtbar     = visible;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artKreis);
        Zeichnen();
    }

    public void MittelpunktSetzen(int neuesX, int neuesY) {
        mittelpunktX = neuesX;
        mittelpunktY = neuesY;
        Zeichnen();
    }

    public void RadiusSetzen(int neuerRadius) {
        radius = neuerRadius;
        Zeichnen();
    }

    public void FarbeSetzen(String neueFarbe) {
        fuellfarbe = neueFarbe;
        Zeichnen();
    }

    public void SichtbarSetzen(boolean neueSichtbarkeit) {
        sichtbar = neueSichtbarkeit;
        Zeichnen();
       }
    
    public void Zeichnen() {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, mittelpunktX-radius, 
        		mittelpunktY-radius, 2*radius, 2*radius, fuellfarbe, sichtbar);      
     }


}
