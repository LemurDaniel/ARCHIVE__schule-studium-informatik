/**
 * Write a description of class WUERFEL here.
 * 
 * @author (Daniel Landau) 
 * @version (07.12.2014)
 */

 public class WUERFEL
{
      RECHTECK  Wuerfel;
      KREIS     Augen[] =  new KREIS[7];
      
      
     public WUERFEL()
    {
        Wuerfel = new RECHTECK (200, 100, 240, 240, "grau", true);
        Augen[0] = new KREIS (240, 140, 20, "schwarz", true);
        Augen[1] = new KREIS (240, 220, 20, "schwarz", true);
        Augen[2] = new KREIS (240, 300, 20, "schwarz", true);
        Augen[3] = new KREIS (400, 140, 20, "schwarz", true);
        Augen[4] = new KREIS (400, 220, 20, "schwarz", true);
        Augen[5] = new KREIS (400, 300, 20, "schwarz", true);
        Augen[6] = new KREIS (320, 220, 20, "schwarz", true);   
    }
    
     public WUERFEL(int s, int posx, int posy, String Wuerfelfarbe, String Augenfarbe)
    {   
        int rand=s/6;
        Wuerfel = new RECHTECK (posx, posy, s, s, Wuerfelfarbe, true);
        Augen[0] = new KREIS (posx+rand, posy+rand, s/12, Augenfarbe, true);
        Augen[1] = new KREIS (posx+rand, posy+3*rand, s/12, Augenfarbe, true);
        Augen[2] = new KREIS (posx+rand, posy+5*rand, s/12, Augenfarbe, true);
        Augen[3] = new KREIS (posx+5*rand, posy+rand, s/12, Augenfarbe, true);
        Augen[4] = new KREIS (posx+5*rand, posy+3*rand, s/12, Augenfarbe, true);
        Augen[5] = new KREIS (posx+5*rand, posy+5*rand, s/12, Augenfarbe, true);
        Augen[6] = new KREIS (posx+3*rand, posy+3*rand, s/12, Augenfarbe, true);   
    }
    

       public void Wuerfel_wuerfeln(int Wurfzahl)
    {
        switch(Wurfzahl)
        {
            case 1: 
            Augen[0].SichtbarSetzen(false);
            Augen[1].SichtbarSetzen(false);
            Augen[2].SichtbarSetzen(false);
            Augen[3].SichtbarSetzen(false);
            Augen[4].SichtbarSetzen(false);
            Augen[5].SichtbarSetzen(false);
            Augen[6].SichtbarSetzen(true);
            break;

            case 2:
            Augen[0].SichtbarSetzen(true);
            Augen[1].SichtbarSetzen(false);
            Augen[2].SichtbarSetzen(false);
            Augen[3].SichtbarSetzen(false);
            Augen[4].SichtbarSetzen(false);
            Augen[5].SichtbarSetzen(true);
            Augen[6].SichtbarSetzen(false);
            break;

            case 3:
            Augen[0].SichtbarSetzen(true);
            Augen[1].SichtbarSetzen(false);
            Augen[2].SichtbarSetzen(false);
            Augen[3].SichtbarSetzen(false);
            Augen[4].SichtbarSetzen(false);
            Augen[5].SichtbarSetzen(true);
            Augen[6].SichtbarSetzen(true);
            break;

            case 4:
            Augen[0].SichtbarSetzen(true);
            Augen[1].SichtbarSetzen(false);
            Augen[2].SichtbarSetzen(true);
            Augen[3].SichtbarSetzen(true);
            Augen[4].SichtbarSetzen(false);
            Augen[5].SichtbarSetzen(true);
            Augen[6].SichtbarSetzen(false);
            break;

            case 5:
            Augen[0].SichtbarSetzen(true);
            Augen[1].SichtbarSetzen(false);
            Augen[2].SichtbarSetzen(true);
            Augen[3].SichtbarSetzen(true);
            Augen[4].SichtbarSetzen(false);
            Augen[5].SichtbarSetzen(true);
            Augen[6].SichtbarSetzen(true);
            break;

            case 6:
            Augen[0].SichtbarSetzen(true);
            Augen[1].SichtbarSetzen(true);
            Augen[2].SichtbarSetzen(true);
            Augen[3].SichtbarSetzen(true);
            Augen[4].SichtbarSetzen(true);
            Augen[5].SichtbarSetzen(true);
            Augen[6].SichtbarSetzen(false);
            break;

            default:
            System.out.println("Error");
            break;
        }
    }
    
}


            
            
 
    
	
