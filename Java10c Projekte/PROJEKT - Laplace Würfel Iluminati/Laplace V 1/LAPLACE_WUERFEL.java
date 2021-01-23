/**
 * Write a description of class test here.
 * 
 * @author (Daniel Landau) 
 * @version (25.01.2015)
*/

public class LAPLACE_WUERFEL
{  
    int Zufallszahl;
    int Wurfanzahl;
    int Wuerfelzahl[] = new int[6];
    int Prozentanteil[] = new int [6];
    WUERFEL LaplaceWuerfel = new WUERFEL(300, 200, 100, "grau", "schwarz");
    DIAGRAMM Diagramm = new DIAGRAMM(600, 150, 100, "hellgrau", "grau", "schwarz");
    
    public void Wuerfle(int Versuchszahl){
        Wuerfle(Versuchszahl, true);
    }
    
    public void Wuerfle(int Versuchszahl, boolean tf)
    {
        Wurfanzahl = Versuchszahl;
        
        for(int i=0;i<6;i++){
            Wuerfelzahl[i]=0;
        }
        
        for (int i = 1; i <= Versuchszahl; i++)
        {
           Zufallszahl = gib_Zufallszahl();
           zaehle_mit(tf);
          
           if(i == Versuchszahl)
            {
                zeichne_Diagramm();
                gib_Werte_aus();
            }
        }
    }
    
    private void zaehle_mit(boolean tf)
    {
        switch(Zufallszahl)
        {
        case 1:
        Wuerfelzahl[0] = Wuerfelzahl[0]+ 1;
        if(tf==true){
        LaplaceWuerfel.Augen[0].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[1].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[2].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[3].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[4].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[5].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[6].SichtbarSetzen(true);
        }
        break;
        
        case 2:
        Wuerfelzahl[1] = Wuerfelzahl[1]+ 1;
        if(tf==true){
        LaplaceWuerfel.Augen[0].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[1].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[2].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[3].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[4].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[5].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[6].SichtbarSetzen(false);
        }
        break;
        
        case 3:
        Wuerfelzahl[2] = Wuerfelzahl[2]+ 1;
        if(tf==true){
        LaplaceWuerfel.Augen[0].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[1].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[2].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[3].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[4].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[5].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[6].SichtbarSetzen(true);
        }
        break;
        
        case 4:
        Wuerfelzahl[3] = Wuerfelzahl[3]+ 1;
        if(tf==true){
        LaplaceWuerfel.Augen[0].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[1].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[2].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[3].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[4].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[5].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[6].SichtbarSetzen(false);
        }
        break;
        
        case 5:
        Wuerfelzahl[4] = Wuerfelzahl[4]+ 1;
        if(tf==true){
        LaplaceWuerfel.Augen[0].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[1].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[2].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[3].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[4].SichtbarSetzen(false);
        LaplaceWuerfel.Augen[5].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[6].SichtbarSetzen(true);
        }
        break;
        
        case 6:
        Wuerfelzahl[5] = Wuerfelzahl[5]+ 1;
        if(tf==true){
        LaplaceWuerfel.Augen[0].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[1].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[2].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[3].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[4].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[5].SichtbarSetzen(true);
        LaplaceWuerfel.Augen[6].SichtbarSetzen(false);
        }
        break;
        
        default:
        System.out.println("Error");
        break;
        }
    }
    
    private void gib_Werte_aus()
    {
        System.out.println("------------------------");
        System.out.print("Wurfanzahl:  ");
        System.out.println(Wurfanzahl);
        System.out.println("-----------");
       
        int ProzentanteilSumme=0;
        
       for(int ii=0;ii<Prozentanteil.length;ii++){
            ProzentanteilSumme+=Prozentanteil[ii];
       }
        
       for (int i=0; i<=5; i++)
       {
                    
           if(i==0){
                System.out.print(i+1);
                System.out.print(" Auge : ");
            } else {
                System.out.print(i+1);
                System.out.print(" Augen: ");
            }
            
            System.out.print(Wuerfelzahl[i]);
            
            System.out.print("   Anteil: " + Prozentanteil[i] + "/" + ProzentanteilSumme + "\n");
           
       }
       
    }
    
    private void zeichne_Diagramm()
    {
       for (int i=0; i<=5; i++)
       {
        if(Wuerfelzahl[i] == 0){
        }else {
            Prozentanteil[i]=Wurfanzahl/Wuerfelzahl[i]; 
            Diagramm.Saeulen[i].hoehe_aendern(50*Prozentanteil[i]);
        }
       }
    }
    
    private int gib_Zufallszahl()
   { 
    return (int)((Math.random()*(6-0))+0+0.99999999999999999999999);
   }
   
   public void test()
   {
        int Versuchszahl = 1000000;
        Wurfanzahl = Versuchszahl;
        
        for(int i=0;i<6;i++){
            Wuerfelzahl[i]=0;
        }
        
        for (int i = 1; i <= Versuchszahl; i++)
        {
           Zufallszahl = gib_Zufallszahl();
           zaehle_mit(false);
            if(i%50==0){
                System.out.print(i);
                System.out.print("; ");
            }
          
           if(i == Versuchszahl)
            {
                System.out.println();
                zeichne_Diagramm();
                gib_Werte_aus();
            }
            
        }
   }
   
}