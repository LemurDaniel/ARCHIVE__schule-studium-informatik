/**
 * Write a description of class KREIS here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v1)
 */

public class KREIS 
{
    int positionX;
	int positionY;
	int radius;
	String fuellfarbe;
    boolean sichtbar;
    int kennung;
    
      public KREIS(int x, int y, int rad, String farbe, boolean visible) 
    {
        positionX  = x;
        positionY  = y;
        radius     = rad;
        fuellfarbe = farbe;
        sichtbar   = visible;
        kennung = ZEICHENFLAECHE.ObjektGeben().anmelden(ZEICHENFLAECHE.SymbolArt.artKreis);
        Zeichnen();
    }
    
    public void Zeichnen() 
   {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, positionX-radius, 
        		positionY-radius, 2*radius, 2*radius, fuellfarbe, sichtbar);      
   }
}