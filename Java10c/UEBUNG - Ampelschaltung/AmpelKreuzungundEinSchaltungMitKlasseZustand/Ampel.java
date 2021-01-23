/**
 * Ampelsystem mit Straßenkreuzung.
 * 
 * @author (K. Horlebein) 
 * @version (Strassenampel_5.3.2015)
 */
public class Ampel
{
    KASTEN Gehaeuse;
    VOLLKREIS [] Signale = new VOLLKREIS[3];
    private int links=100;
    private int oben=80;
    private int breite=20;
    private int rand=breite/2;
    private int Zustand = 0;
    
    public Ampel()
    {
       this("Norden"); // ruft den Konstruktor von Ampel mit dem Parameter Ampel("Norden") auf
    }
    public Ampel(String position)
    {
        erstelleAmpel(position);
        zeichneAmpel();
    }
    public Ampel(int xLinks, int yOben, int zBreite)
    {
        this(xLinks, yOben, zBreite, "Norden"); // ruft den Konstruktor Ampel mit -> Ampel(xLinks, yOben, zBreite, "Norden") auf
    }
    public Ampel(int xLinks, int yOben, int zBreite, String position)
    {        
        links=xLinks;
        oben=yOben;
        breite=zBreite;
        rand=breite/2;
        erstelleAmpel(position);
        zeichneAmpel();
    }

    private void erstelleAmpel(String position)
    {        
        switch (position)
        {
            case "Norden":
            Gehaeuse=new KASTEN(links,oben,
                            2*breite+2*rand,6*breite+4*rand); //Kasten - stehend;
            Signale[0] = new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,2); // Lichter alle mit gleichem Links-Wert 
            Signale[1] = new VOLLKREIS(links+breite+rand,oben+3*breite+2*rand,breite,6);
            Signale[2] = new VOLLKREIS(links+breite+rand,oben+5*breite+3*rand,breite,4); 
            break;
            
            case "Osten":
            Gehaeuse=new KASTEN(links,oben,
                            6*breite+4*rand,2*breite+2*rand); //Kasten - liegend
            Signale[0]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,4); // Lichter alle mit gleichem Oben-Wert
            Signale[1]= new VOLLKREIS(links+3*breite+2*rand,oben+breite+rand,breite,6);
            Signale[2]= new VOLLKREIS(links+5*breite+3*rand,oben+breite+rand,breite,2); 
            break;
            
            case "Sueden":
            Gehaeuse=new KASTEN(links,oben,
                            2*breite+2*rand,6*breite+4*rand); //Kasten - stehend; Lichter alle mit gleichem Links-Wert 
            Signale[0]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,4); // Lichter: rot unten
            Signale[1]= new VOLLKREIS(links+breite+rand,oben+3*breite+2*rand,breite,6);
            Signale[2]= new VOLLKREIS(links+breite+rand,oben+5*breite+3*rand,breite,2); 
            break;
            
            case "Westen":
            Gehaeuse=new KASTEN(links,oben,
                            6*breite+4*rand,2*breite+2*rand); //Kasten - liegend; Lichter alle mit gleichem Oben-Wert
            Signale[0]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,2); // Lichter: rot unten
            Signale[1]= new VOLLKREIS(links+3*breite+2*rand,oben+breite+rand,breite,6);
            Signale[2]= new VOLLKREIS(links+5*breite+3*rand,oben+breite+rand,breite,4);
            break;
            
            default:
            System.out.println("Es ist ein Fehler aufgetreten. Die Ampel kann nicht erstellt werden");
            break;
        }
    }
        
    public void zeichneAmpel()
    {
        Gehaeuse.zeichne();
        for (int i=0; i<3; i++)
        {
            Signale[i].zeichne();
        }
    }    

    public void schalte_aus()
    {
        for (int i=0; i<3;i++)
        {
           Signale[i].setzeFarbe(0);
           Signale[i].zeichne();
        }
    }
            
    public void schalte_auf(String farbe){
        switch(farbe){
            case "aus":
            schalte_aus();
            break;
            
            case "rot":
            Signale[0].setzeFarbe(4);
            Signale[1].setzeFarbe(0);
            Signale[2].setzeFarbe(0);
            break;
            
            case "rot-gelb":
            Signale[0].setzeFarbe(4);
            Signale[1].setzeFarbe(6);
            Signale[2].setzeFarbe(0);
            break; 
             
            case "gruen":
            Signale[0].setzeFarbe(0);
            Signale[1].setzeFarbe(0);
            Signale[2].setzeFarbe(2);
            break;
                       
            case "gelb":
            Signale[0].setzeFarbe(0);
            Signale[1].setzeFarbe(6);
            Signale[2].setzeFarbe(0);
            break;

            default:
            System.out.println("Was soll das denn für eine Ampelfarbe sein?");
            break;
        }
        zeichneAmpel();
    }
    
}
