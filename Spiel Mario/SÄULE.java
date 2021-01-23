

public class SÄULE {

	private int positionX;

	private int positionY;

	private int breite;
	
	private int hoehe;
	
	private String fuellfarbe;
    
    private boolean sichtbar;
    
    private int kennung;

    
    public SÄULE() {
		positionX = 60;
		positionY = 50;
		breite = 60;
		hoehe = 30;
		fuellfarbe = "rot";
		sichtbar = true;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artViereck);
        Zeichnen();
    }
    public SÄULE(int x, int y, int br, int hoe, String farbe)
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
