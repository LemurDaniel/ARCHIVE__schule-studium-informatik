
/**
 * Write a description of class WUERFEL here.
 * 
 * @author (Daniel Landau) 
 * @version (20141210v1)
 */

public class WUERFEL
{
    RECHTECK  Wuerfel;
    KREIS     Augen[] =  new KREIS[7];

    public WUERFEL()
    {
        Wuerfel = new RECHTECK (200, 100, 240, 240, "grau", true);
        Augen[0] = new KREIS (240, 140, 20, "schwarz", false);
        Augen[1] = new KREIS (240, 220, 20, "schwarz", false);
        Augen[2] = new KREIS (240, 300, 20, "schwarz", false);
        Augen[3] = new KREIS (400, 140, 20, "schwarz", false);
        Augen[4] = new KREIS (400, 220, 20, "schwarz", false);
        Augen[5] = new KREIS (400, 300, 20, "schwarz", false);
        Augen[6] = new KREIS (320, 220, 20, "schwarz", false);
    }

    public void Umschalten(int Wuerfelzahl)
    {
        switch(Wuerfelzahl)
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
            System.out.println("Fehler");
            break;
        }
    }
   
}