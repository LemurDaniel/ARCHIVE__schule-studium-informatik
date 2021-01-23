
/**
 * Write a description of class ADVENTSKRANZ here.
 * 
 * @author (Daniel Landau, Dominic Zimonich) 
 * @version ()
 */
public class ADVENTSKRANZ
{
    KREIS Innenkreis;
    KREIS Außenkreis;
    KREIS Kerze[] =new KREIS[4];
    KREIS Licht[] =new KREIS[4];
    int Zustand = 1;
  
    public ADVENTSKRANZ()
    {
        Außenkreis =new KREIS(300, 250, 120, "dunkelgruen", true);
        Innenkreis =new KREIS(300, 250, 80, "weiss", true);
        Kerze[0]   =new KREIS(230, 180, 20, "rot", true);
        Kerze[1]   =new KREIS(370, 180, 20, "rot", true);
        Kerze[2]   =new KREIS(230, 320, 20, "rot", true);
        Kerze[3]   =new KREIS(370, 320, 20, "rot", true);
        Licht[0]   =new KREIS(230, 180, 5, "rot", true);
        Licht[1]   =new KREIS(370, 180, 5, "rot", true);
        Licht[2]   =new KREIS(230, 320, 5, "rot", true);
        Licht[3]   =new KREIS(370, 320, 5, "rot", true);
     }

    public void Lichtanzuenden ()
    {
     switch (Zustand)
        {
            case 1:
            Licht[0].FarbeSetzen ("gelb");
            Licht[1].FarbeSetzen ("rot");
            Licht[2].FarbeSetzen ("rot");
            Licht[3].FarbeSetzen ("rot");
            System.out.print("Advent, Advent, ein Lichtlein brennt. Erst eins, ");
            Zustand = Zustand + 1;
            break;
            
            case 2:
            Licht[0].FarbeSetzen ("gelb");
            Licht[1].FarbeSetzen ("gelb");
            Licht[2].FarbeSetzen ("rot");
            Licht[3].FarbeSetzen ("rot");
            System.out.print("dann zwei, ");
            Zustand = Zustand + 1;
            break;
            
            case 3:
            Licht[0].FarbeSetzen ("gelb");
            Licht[1].FarbeSetzen ("gelb");
            Licht[2].FarbeSetzen ("gelb");
            Licht[3].FarbeSetzen ("rot");
            System.out.print("dann drei, ");
            Zustand = Zustand + 1;
            break;
            
            case 4:
            Licht[0].FarbeSetzen ("gelb");
            Licht[1].FarbeSetzen ("gelb");
            Licht[2].FarbeSetzen ("gelb");
            Licht[3].FarbeSetzen ("gelb");
            System.out.println("dann vier, dann steht das Christkind vor der Tür.");
            Zustand = Zustand + 1;
            break;       
            
            default:
            Licht[0].FarbeSetzen ("rot");
            Licht[1].FarbeSetzen ("rot");
            Licht[2].FarbeSetzen ("rot");
            Licht[3].FarbeSetzen ("rot");
            Zustand = Zustand - 4;
            break;
        }
    }

}

