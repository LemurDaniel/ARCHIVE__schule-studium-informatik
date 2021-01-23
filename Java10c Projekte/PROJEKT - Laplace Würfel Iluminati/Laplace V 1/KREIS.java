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
    
    public void SichtbarSetzen(boolean visible)
    {
        sichtbar = visible;
        Zeichnen();
    }
 
    public void Zeichnen() {
        ZEICHENFLAECHE.ObjektGeben().zeichnen(kennung, mittelpunktX-radius, 
        		mittelpunktY-radius, 2*radius, 2*radius, fuellfarbe, sichtbar);      
     }


}
