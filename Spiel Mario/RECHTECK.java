
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

    
    /**
     * Erzeuge ein neues Rechteck mit einer Standardposition und einer
     * Standardf�llfarbe. Das zugeh�rige Symbol wird sofort angezeigt.
     */
    public RECHTECK() {
		positionX = 60;
		positionY = 50;
		breite = 60;
		hoehe = 30;
		fuellfarbe = "rot";
		sichtbar = true;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artViereck);
        Zeichnen();
    }
    public RECHTECK(int x, int y, int br, int hoe, String farbe)
    {
        positionX = x;
		positionY = y;
		breite = br;
		hoehe = hoe;
		fuellfarbe = farbe;
		sichtbar = true;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artViereck);
        Zeichnen();
    }

    /**
     * Die Position (linke, obere Ecke) dieses Rechtecks
     * auf die neuen Werte setzen.
     */
	public void PositionSetzen(int NeuesX, int NeuesY) {
		positionX = NeuesX;
		positionY = NeuesY;
		Zeichnen();
    }

	/**
	 * Die Gr��e dieses Rechteckes auf die neue Breite 
	 * und die neue H�he setzen. 
	 */
	public void GroesseSetzen(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		Zeichnen();
	}

    /**
     * Die F�llfarbe dieses Rechtecks auf 'neueFarbe' setzen. 
     * Erlaubte Parameterwerte sind:
     * "rot", "gruen", "blau", "gelb", "cyan", "magenta", "schwarz", "weiss"
     * "hellgelb", "hellgruen", "orange", "braun"
     */
    public void FarbeSetzen(String neueFarbe) {
        fuellfarbe = neueFarbe;
        Zeichnen();
    }
    
    /**
     * Die Sichtbarkeit dieses Rechtecks ein- oder ausschalten.
     * Erlaubte Parameterwerte: true, false
     */
    public void SichtbarSetzen(boolean neueSichtbarkeit) {
        sichtbar = neueSichtbarkeit;
        Zeichnen();
       }
       
    /**
     * Zeichne f�r dieses Rechteck ein entsprechendes Grafiksymbol
     * in dem Grafikfenster.
     * Nach jeder �nderung muss das Rechteck diese �nderung seinem 
     * Grafiksymbol mitteilen.
     */
    public void Zeichnen() {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, positionX, 
        		positionY, breite, hoehe, fuellfarbe, sichtbar);      
     }


}
