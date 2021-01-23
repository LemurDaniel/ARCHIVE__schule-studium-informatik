/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v1)
 */

public class DREIECK
{
    int positionX;
	int positionY;
	int breite;
	int hoehe;
	String fuellfarbe;
    boolean sichtbar; 
    int kennung;
     
   public DREIECK(int x, int y,int width,int height, String farbe, boolean visible) 
   {
		positionX  = x;
		positionY  = y;
		breite     = width;
		hoehe      = height;
		fuellfarbe = farbe;
		sichtbar   = visible;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artDreieck);
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, positionX, positionY, breite, hoehe, fuellfarbe, sichtbar);
    }
}
