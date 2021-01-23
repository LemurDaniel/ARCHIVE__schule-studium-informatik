/**
 * Beschreiben Sie hier die Klasse Ampel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ampel3
{
    KASTEN Gehaeuse;
    VOLLKREIS [] Signale = new VOLLKREIS[3];
    private int links=100;
    private int oben=80;
    private int breite=20;
    private int rand=breite/2;
    public Ampel3()
    {
        Gehaeuse=new KASTEN(links-breite-rand,oben-breite-rand,
                            2*breite+2*rand,6*breite+4*rand);
        Signale[0]= new VOLLKREIS(links,oben,breite,4);
        Signale[1]= new VOLLKREIS(links,oben+2*breite+rand,breite,6);
        Signale[2]= new VOLLKREIS(links,oben+4*breite+2*rand,breite,2);   
        Gehaeuse.zeichne();
        Signale[0].zeichne();
        Signale[1].zeichne();
        Signale[2].zeichne();
    }
    public Ampel3(int xLinks, int xOben, int xBreite)
    {
        links=xLinks;
        oben=xOben;
        breite=xBreite;
        rand=breite/2;
        Gehaeuse=new KASTEN(links-breite-rand,oben-breite-rand,
                            2*breite+2*rand,6*breite+4*rand);
        Signale[0]= new VOLLKREIS(links,oben,breite,4);
        Signale[1]= new VOLLKREIS(links,oben+2*breite+rand,breite,6);
        Signale[2]= new VOLLKREIS(links,oben+4*breite+2*rand,breite,2);   
        Gehaeuse.zeichne();
        Signale[0].zeichne();
        Signale[1].zeichne();
        Signale[2].zeichne();
    }
    public Ampel3(int xLinks, int xOben, int xBreite, boolean quer)
    {
        
        links=xLinks;
        oben=xOben;
        breite=xBreite;
        rand=breite/2;
        if(quer==false){
        Gehaeuse=new KASTEN(links-breite-rand,oben-breite-rand,
                            2*breite+2*rand,6*breite+4*rand);
        Signale[0]= new VOLLKREIS(links,oben,breite,4);
        Signale[1]= new VOLLKREIS(links,oben+2*breite+rand,breite,6);
        Signale[2]= new VOLLKREIS(links,oben+4*breite+2*rand,breite,2);                       
        }
        else
        {
        Gehaeuse=new KASTEN(links-breite-rand,oben-breite-rand,
                            6*breite+4*rand,2*breite+2*rand);    
        Signale[0]= new VOLLKREIS(links,oben,breite,4);
        Signale[1]= new VOLLKREIS(links+2*breite+rand,oben,breite,6);
        Signale[2]= new VOLLKREIS(links+4*breite+2*rand,oben,breite,2);
        }
        Gehaeuse.zeichne();
        Signale[0].zeichne();
        Signale[1].zeichne();
        Signale[2].zeichne();
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
        zeichne_Ampel();
    }
    
    public void zeichne_Ampel(){
        for (int i=0; i<3; i++)
        {
            Signale[i].zeichne();
        }
    }    
}
