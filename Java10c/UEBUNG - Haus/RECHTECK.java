/**
 * Write a description of class RECHECK here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v1)
 */

public class RECHTECK 
{
	int positionX;
	int positionY;
	int breite;
	int hoehe;
	String fuellfarbe;
    boolean sichtbar; 
    int kennung;
 
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

    public void Zeichnen() 
    {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, positionX, 
        		positionY, breite, hoehe, fuellfarbe, sichtbar);      
     }
}
