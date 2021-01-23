/**
 * Beschreiben Sie hier die Klasse Ampel.
 * 
 * @author (K. Horlebein) 
 * @version (einfache Ampel - auch quer - Orientierungspunkt links/oben-Koordinate des Gehäuses)
 */
public class Ampel
{
    KASTEN Gehaeuse;
    VOLLKREIS [] Signale = new VOLLKREIS[3];
    private int links=100;
    private int oben=80;
    private int breite=20;
    private int rand=breite/2;
    public Ampel()
    {
       erstelleAmpel();
       zeichneAmpel();
    }
    public Ampel(boolean quer)
    {
        erstelleAmpel(quer);
        zeichneAmpel();
    }
    public Ampel(int xLinks, int xOben, int xBreite)
    {
        links=xLinks;
        oben=xOben;
        breite=xBreite;
        rand=breite/2;
        erstelleAmpel();
        zeichneAmpel();
    }
    public Ampel(int xLinks, int xOben, int xBreite, boolean quer)
    {        
        links=xLinks;
        oben=xOben;
        breite=xBreite;
        rand=breite/2;
        if(quer==false)
        {
           erstelleAmpel();          
        }
        else
        {
           erstelleAmpel(quer);
        }
        zeichneAmpel();
    }
    
    private void erstelleAmpel()
    {
        Gehaeuse=new KASTEN(links,oben,
                            2*breite+2*rand,6*breite+4*rand);
        Signale[0]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,4);
        Signale[1]= new VOLLKREIS(links+breite+rand,oben+3*breite+2*rand,breite,6);
        Signale[2]= new VOLLKREIS(links+breite+rand,oben+5*breite+3*rand,breite,2); 
    }
    private void erstelleAmpel(boolean quer)
    {
        Gehaeuse=new KASTEN(links,oben,
                            6*breite+4*rand,2*breite+2*rand);    
        Signale[0]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,4);
        Signale[1]= new VOLLKREIS(links+3*breite+2*rand,oben+breite+rand,breite,6);
        Signale[2]= new VOLLKREIS(links+5*breite+3*rand,oben+breite+rand,breite,2);
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
            
            case "gelb":
            Signale[0].setzeFarbe(0);
            Signale[1].setzeFarbe(6);
            Signale[2].setzeFarbe(0);
            break;
            
            case "gruen":
            Signale[0].setzeFarbe(0);
            Signale[1].setzeFarbe(0);
            Signale[2].setzeFarbe(2);
            break;
            
            case "rot-gelb":
            Signale[0].setzeFarbe(4);
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
