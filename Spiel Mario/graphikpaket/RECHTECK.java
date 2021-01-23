package graphikpaket;


public class RECHTECK implements I
{
    private int links;
    private int oben;
    private int breite;
    private int hoehe;
    private int farbnr;
    private ZEICHENFENSTER Fenster;
    public RECHTECK()
    {
       this(10,10,10,10,FarbKonverter.farbeZuFarbNr($StandardZeichenFarbe));        
    }
    
    public RECHTECK(int linksStart, int obenStart, int breiteStart, int hoeheStart,int farbnrStart)
    {
        this(linksStart, obenStart, breiteStart, hoeheStart, farbnrStart, $StandardZEICHENFENSTER); 
    }
    public RECHTECK(int linksStart, int obenStart, int breiteStart, int hoeheStart,int farbnrStart, ZEICHENFENSTER neuesFenster)
     {
        links = linksStart;
        oben = obenStart;
        breite = breiteStart;
        hoehe = hoeheStart;
        farbnr = farbnrStart;
        Fenster = neuesFenster;
    }
    
    public void setzeFarbe(int Farbe){farbnr=Farbe;}
    public void zeichne()
    {
        Fenster.zeichneRechteck(links,oben,breite,hoehe);
    }

    public void fuelle()
    {
        Fenster.fuelleRechteck(links,oben,breite,hoehe,farbnr);
    }
    
    public void fuelle(int farbnrx)
    {
        farbnr=farbnrx;
        Fenster.fuelleRechteck(links,oben,breite,hoehe,farbnr);
    }
    
    public void loesche()
    {
        Fenster.loescheRechteck(links,oben,breite,hoehe);
    }
    
    public void loescheKomplett()
    {
        Fenster.zeichneRechteck(links,oben,breite,hoehe);
        Fenster.fuelleRechteck(links,oben,breite,hoehe,8);
        Fenster.loescheRechteck(links,oben,breite,hoehe);
        zeichne();
    }

    public void setzeLinks(int linksNeu)
    {
        links = linksNeu;
    }
    
    public void setzeRechts(int rechtsNeu)
    {
        links = rechtsNeu-breite;
    }
    
    public void setzeGroesse(int breiteNeu, int hoeheNeu)
    {
        breite = breiteNeu;
        hoehe = hoeheNeu;
    }
    
    public int gibUmfang()
    {
        return 2*(breite+hoehe);
    }
    
    public int gibInhalt()
    {
        return breite*hoehe;
    }
    
    public void verschiebe(int nachRechts, int nachUnten)
    {
        loescheKomplett();
        links = links + nachRechts;
        oben = oben + nachUnten;
        zeichne();
    }
}
