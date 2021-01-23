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
    
    public void hoehe_aendern(int newheight)
    {
       hoehe = newheight;
       Zeichnen();
    }

	public void Zeichnen() {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, positionX, 
        		positionY, breite, hoehe, fuellfarbe, sichtbar);      
     }


}
