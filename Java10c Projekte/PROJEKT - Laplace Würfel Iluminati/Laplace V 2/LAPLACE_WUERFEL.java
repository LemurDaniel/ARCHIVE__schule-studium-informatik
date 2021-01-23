/**
 * Write a description of class test here.
 * 
 * @author (Daniel Landau) 
 * @version (25.01.2015)
*/

public class LAPLACE_WUERFEL
{  
    private int Zufallszahl;
    private int Wurfanzahl;
    private int Method;
    private int Wuerfelzahl[];
    private float Prozentanteil[];
    private WUERFEL LaplaceWuerfel;
    private DIAGRAMM Diagramm;
    
    public LAPLACE_WUERFEL(){ 
        this(1);
    }
    
    public LAPLACE_WUERFEL(int method){
        Method = method;
        Wuerfelzahl = new int[6];
        Prozentanteil = new float [6];
        LaplaceWuerfel = new WUERFEL(300, 200, 100, "grau", "schwarz");
        Diagramm = new DIAGRAMM(600, 150, 100, "hellgrau", "grau", "schwarz");
    }
    
    public LAPLACE_WUERFEL(int Versuchszahl, int method){
        this(method);
        Wuerfle(Versuchszahl);
    }
    
    public void Wuerfle(int Versuchszahl){
        Wuerfle(Versuchszahl, true);
    }
    
    private void Wuerfle(int Versuchszahl, boolean tf)
    {
        Wurfanzahl = Versuchszahl;
        
        for(int i=0;i<6;i++){
            Wuerfelzahl[i]=0;
        }
        
        for (int i = 1; i <= Versuchszahl; i++)
        {
           Zufallszahl = Mathe.gibZufallszahl(1,6,Method);
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
       
        float Genauigkeit=0;
        
       for(int i=0;i<Prozentanteil.length;i++){
            Genauigkeit += Prozentanteil[i];
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
            
            System.out.print("   Anteil: " + Prozentanteil[i]*100 + "%" + "\n");
            
       } 
        System.out.println();
        System.out.println("Genauigkeit: " +  Genauigkeit);
        System.out.println();
        System.out.println("Methode Nummer: " +  Method);
       
    }
    
    private void zeichne_Diagramm()
    {
       for (int i=0; i<=5; i++)
       {
        if(Wuerfelzahl[i] == 0){
        }else {
            Prozentanteil[i]= ((float)Wuerfelzahl[i])/((float)Wurfanzahl); 
            Diagramm.Saeulen[i].hoehe_aendern((int) (1000*Prozentanteil[i]) );
        }
       }
    }
    
   public void test(int Versuchszahl)
   {
        Wurfanzahl = Versuchszahl;
        
        for(int i=0;i<6;i++){
            Wuerfelzahl[i]=0;
        }
        
        for (int i = 1; i <= Versuchszahl; i++)
        {
           Zufallszahl = Mathe.gibZufallszahl(1,6,Method);
           //System.out.print("Zufallszahl:  " + Zufallszahl);
           zaehle_mit(false);
            if(Versuchszahl < 10000 || i%(Versuchszahl/1000)==0){
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
   
   public void ChangeMethod(){
       if(Method<3){Method++;}else { if(Method==3){Method=1;}}
    }
   
   public void test(){
       test(10000000);  
       ChangeMethod();
       test(10000000);  
       ChangeMethod();
       test(10000000);  
    }
   
   
}