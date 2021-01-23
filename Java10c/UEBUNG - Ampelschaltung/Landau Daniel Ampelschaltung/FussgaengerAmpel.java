public class FussgaengerAmpel
{
    KASTEN Gehaeuse;
    VOLLKREIS [] Signale = new VOLLKREIS[2];
    private int links=100;
    private int oben=80;
    private int breite=20;
    private int rand=breite/2;
    private int Zustand = 0;
    
    public FussgaengerAmpel()
    {
      erstelleAmpel("Norden");
      zeichneAmpel();
    }
    
    public FussgaengerAmpel(String position)
    {
        erstelleAmpel(position);
      zeichneAmpel();
    }
    
    public FussgaengerAmpel(int xLinks, int yOben, int zBreite, String position)
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
                            2*breite+2*rand,4*breite+3*rand); //Kasten - stehend;
            Signale[0] = new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,2); // Lichter alle mit gleichem Links-Wert 
            Signale[1] = new VOLLKREIS(links+breite+rand,oben+3*breite+2*rand,breite,4);
            break;
            
            case "Osten":
            Gehaeuse=new KASTEN(links,oben,
                            4*breite+3*rand,2*breite+2*rand); //Kasten - liegend // Lichter alle mit gleichem Oben-Wert
            Signale[0]= new VOLLKREIS(links+3*breite+2*rand,oben+breite+rand,breite,2);
            Signale[1]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,4);
            break;
            
            case "Sueden":
            Gehaeuse=new KASTEN(links,oben,
                            2*breite+2*rand,4*breite+3*rand); //Kasten - stehend; Lichter alle mit gleichem Links-Wert  // Lichter: rot unten
            Signale[0]= new VOLLKREIS(links+breite+rand,oben+3*breite+2*rand,breite,2);
            Signale[1]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,4);
            break;
            
            case "Westen":
            Gehaeuse=new KASTEN(links,oben,
                            4*breite+3*rand,2*breite+2*rand); //Kasten - liegend; Lichter alle mit gleichem Oben-Wert
            Signale[0]= new VOLLKREIS(links+breite+rand,oben+breite+rand,breite,2); // Lichter: rot unten
            Signale[1]= new VOLLKREIS(links+3*breite+2*rand,oben+breite+rand,breite,4);
            break;
            
            default:
            System.out.println("Es ist ein Fehler aufgetreten. Die Ampel kann nicht erstellt werden");
            break;
        }
    }
        
    public void zeichneAmpel()
    {
        Gehaeuse.zeichne();
        for (int i=0; i<2; i++)
        {
            Signale[i].zeichne();
        }
    }    

    public void schalte_aus()
    {
        for (int i=0; i<2;i++)
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
            Signale[0].setzeFarbe(0);
            Signale[1].setzeFarbe(4);
            break;
            
            case "gruen":
            Signale[0].setzeFarbe(2);
            Signale[1].setzeFarbe(0);
            break; 
           
            default:
            System.out.println("Was soll das denn für eine Ampelfarbe sein?");
            break;
        }
        zeichneAmpel();
    }
    
    public void loescheAmpel()
    {
        Gehaeuse.loescheRechteck();
        for (int i=0;i<=1;i++){
        Signale[i].loescheKreis();
        }
    }
}
