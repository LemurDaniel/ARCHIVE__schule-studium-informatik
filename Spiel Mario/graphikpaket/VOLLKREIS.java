package graphikpaket;


public class VOLLKREIS implements I
{
    private int xMitte, radius, farbnr;
    private int yMitte;
    private ZEICHENFENSTER Fenster;
    
    VOLLKREIS()
    {
        this(50,50,20,FarbKonverter.farbeZuFarbNr($StandardZeichenFarbe));
    }
    public VOLLKREIS(int xStart, int yStart, int radiusStart, int farbnrStart)
    {
        this(xStart, yStart, radiusStart, farbnrStart, $StandardZEICHENFENSTER);
    }
    public VOLLKREIS(int xStart, int yStart, int radiusStart, int farbnrStart, ZEICHENFENSTER neuesFenster)
    {
        xMitte = xStart;
        yMitte = yStart;
        radius = radiusStart;
        farbnr = farbnrStart;
        Fenster = neuesFenster;
    }
    public VOLLKREIS(boolean AufpunktLinksOben, int xStart, int yStart, int radiusStart)
    {
            this(xStart+radiusStart,yStart+radiusStart,radiusStart,FarbKonverter.farbeZuFarbNr($StandardZeichenFarbe));
    } 
    public VOLLKREIS(boolean AufpunktLinksOben, int xStart, int yStart, int radiusStart, int farbnrStart)
    {
            this(xStart+radiusStart,yStart+radiusStart,radiusStart,farbnrStart);
    }
    public VOLLKREIS(boolean AufpunktLinksOben, int xStart, int yStart, int radiusStart, int farbnrStart, ZEICHENFENSTER neuesFenster)
    {
        this(xStart+radiusStart,yStart+radiusStart,radiusStart,farbnrStart,neuesFenster);
    }
    public void setzeMitte(int xNeu, int yNeu)
    {
        xMitte = xNeu;
        yMitte = yNeu;
    }
        
    public void zeichne()
    {
        Fenster.fuelleKreis(xMitte,yMitte,radius,farbnr);
    }   

    public void setzeFarbe(int farbnrNeu)
    {
        farbnr = farbnrNeu;
    }
    
    public void verschiebe(int nachRechts, int nachUnten)
    {
       xMitte = xMitte + nachRechts;
       yMitte = yMitte + nachUnten;
    }
}
