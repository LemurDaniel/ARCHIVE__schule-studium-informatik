
/**
 * Klasse f�r eine Rechteck-Figur
 * 
 * Ein Objekt dieser Klasse benutzt zur Darstellung
 * seines Grafiksymbols ein Objekt der Klasse ZEICHENFLAECHE
 * 
 * @author U.Freiberger
 * @version 1.0
 */

public class RECHTECK {

	private int positionX;

	private int positionY;

	private int breite;
	
	private int hoehe;
	
    private String fuellfarbe;
    
    private boolean sichtbar;
    
    private int kennung;


    public RECHTECK(int x, int y, int width, int height, String farbe, boolean visible)
    {
        positionX  = x;
		positionY  = y;
		breite     = width;
		hoehe      = height;
		fuellfarbe = farbe;
		sichtbar   = visible;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artViereck);
        Zeichnen();
    }

	public void PositionSetzen(int NeuesX, int NeuesY) {
		positionX = NeuesX;
		positionY = NeuesY;
		Zeichnen();
    }

	public void GroesseSetzen(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
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
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, positionX, 
        		positionY, breite, hoehe, fuellfarbe, sichtbar);      
     }


}
