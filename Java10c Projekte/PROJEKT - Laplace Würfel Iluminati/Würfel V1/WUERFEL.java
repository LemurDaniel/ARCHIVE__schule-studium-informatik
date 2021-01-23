
/**
 * Write a description of class WUERFEL here.
 * 
 * @author (Daniel Landau) 
 * @version (20141207v1)
 */

public class WUERFEL
{
    RECHTECK  Wuerfel;
    KREIS     Punkt1;
    KREIS     Punkt2;
    KREIS     Punkt3;
    KREIS     Punkt4;
    KREIS     Punkt5;
    KREIS     Punkt6;
    KREIS     Punkt7;
  
    public WUERFEL()
    {
        Wuerfel = new RECHTECK (200, 100, 240, 240, "grau", true);
        Punkt1  = new KREIS (240, 140, 20, "schwarz", false);
        Punkt2  = new KREIS (240, 220, 20, "schwarz", false);
        Punkt3  = new KREIS (240, 300, 20, "schwarz", false);
        Punkt4  = new KREIS (400, 140, 20, "schwarz", false);
        Punkt5  = new KREIS (400, 220, 20, "schwarz", false);
        Punkt6  = new KREIS (400, 300, 20, "schwarz", false);
        Punkt7  = new KREIS (320, 220, 20, "schwarz", true);
    }
    

    public void Umschalten(int Wuerfelzahl)
    {
        switch(Wuerfelzahl)
        {
        case 1: 
        Punkt1.SichtbarSetzen(false);
        Punkt2.SichtbarSetzen(false);
        Punkt3.SichtbarSetzen(false);
        Punkt4.SichtbarSetzen(false);
        Punkt5.SichtbarSetzen(false);
        Punkt6.SichtbarSetzen(false);
        Punkt7.SichtbarSetzen(true); 
        break;
        
        case 2:
        Punkt1.SichtbarSetzen(true);
        Punkt2.SichtbarSetzen(false);
        Punkt3.SichtbarSetzen(false);
        Punkt4.SichtbarSetzen(false);
        Punkt5.SichtbarSetzen(false);
        Punkt6.SichtbarSetzen(true);
        Punkt7.SichtbarSetzen(false);
        break;
        
        case 3:
        Punkt1.SichtbarSetzen(true);
        Punkt2.SichtbarSetzen(false);
        Punkt3.SichtbarSetzen(false);
        Punkt4.SichtbarSetzen(false);
        Punkt5.SichtbarSetzen(false);
        Punkt6.SichtbarSetzen(true);
        Punkt7.SichtbarSetzen(true);
        break;
        
        case 4:
        Punkt1.SichtbarSetzen(true);
        Punkt2.SichtbarSetzen(false);
        Punkt3.SichtbarSetzen(true);
        Punkt4.SichtbarSetzen(true);
        Punkt5.SichtbarSetzen(false);
        Punkt6.SichtbarSetzen(true);
        Punkt7.SichtbarSetzen(false);
        break;
        
        case 5:
        Punkt1.SichtbarSetzen(true);
        Punkt2.SichtbarSetzen(false);
        Punkt3.SichtbarSetzen(true);
        Punkt4.SichtbarSetzen(true);
        Punkt5.SichtbarSetzen(false);
        Punkt6.SichtbarSetzen(true);
        Punkt7.SichtbarSetzen(true);
        break;
        
        case 6:
        Punkt1.SichtbarSetzen(true);
        Punkt2.SichtbarSetzen(true);
        Punkt3.SichtbarSetzen(true);
        Punkt4.SichtbarSetzen(true);
        Punkt5.SichtbarSetzen(true);
        Punkt6.SichtbarSetzen(true);
        Punkt7.SichtbarSetzen(false);
        break;
        
        default:
        System.out.println("Fehler");
        break;
        }
    }
    
    public void Farbeaender (String Augenfarbe, String Wuerfelfarbe)
    {   
        Punkt1.FarbeSetzen(Augenfarbe);
        Punkt2.FarbeSetzen(Augenfarbe);
        Punkt3.FarbeSetzen(Augenfarbe);
        Punkt4.FarbeSetzen(Augenfarbe);
        Punkt5.FarbeSetzen(Augenfarbe);
        Punkt6.FarbeSetzen(Augenfarbe);
        Punkt7.FarbeSetzen(Augenfarbe);
        Wuerfel.FarbeSetzen(Wuerfelfarbe);
    }
    
    
    
}