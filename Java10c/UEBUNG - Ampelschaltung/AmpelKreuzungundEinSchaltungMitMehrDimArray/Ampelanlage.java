/**
 * Ampelsystem mit Straﬂenkreuzung.
 * 
 * @author (K. Horlebein) 
 * @version (StrassenampelMitSchaltung_6.3.2015)
 */
class Ampelanlage
{
    Ampel[] Ampeln = new Ampel[4];
    FussgaengerAmpel[] FussgaengerAmpeln = new FussgaengerAmpel[8];
    KASTEN Querstrasse;
    KASTEN Laengsstrasse;
    int Fensterbreite=600;
    int Strassenbreite=40;
    int x = 300; int y = 100; // (x,y)-Koordinaten der Kreuzung
    int z = 0;
    int Zustand = 1;
    String [][] Zustaende = 
    { 
        {"rot","rot","rot","rot"},
        {"rot","rot-gelb","rot","rot-gelb"}, 
        {"rot","gruen","rot","gruen"},
        {"rot","gelb","rot","gelb"},
        {"rot-gelb","rot","rot-gelb","rot"}, 
        {"gruen","rot","gruen","rot"},
        {"gelb","rot","gelb","rot"}
    };
    
    String [][] ZustaendeFussgaengerAmpel = 
    { 
        {"rot","rot","rot","rot","rot","rot","rot","rot"},
        {"rot","rot","rot","rot","rot","rot","rot","rot"}, 
        {"rot","rot","rot","rot","gruen","gruen","gruen","gruen"},
        {"rot","rot","rot","rot","rot","rot","rot","rot"},
        {"rot","rot","rot","rot","rot","rot","rot","rot"}, 
        {"gruen","gruen","gruen","gruen","rot","rot","rot","rot"},
        {"rot","rot","rot","rot","rot","rot","rot","rot"}
    };
    
    Ampelanlage()
    {
        this(300, 200);
    }

    Ampelanlage(int xx, int yy)
    {
        Querstrasse=new KASTEN(0,yy,Fensterbreite,Strassenbreite);
        Laengsstrasse=new KASTEN(xx,0,Strassenbreite,Fensterbreite);
        Querstrasse.zeichne();
        Laengsstrasse.zeichne();
        int Ampelbreite=Strassenbreite/4;
        int Abstand=Strassenbreite/8;
        Ampeln[0]= new Ampel(xx-3*Ampelbreite-Abstand,yy-8*Ampelbreite-Abstand,Ampelbreite,"Norden");
        Ampeln[1]= new Ampel(xx+Strassenbreite+Abstand,yy-3*Ampelbreite-Abstand,Ampelbreite,"Osten");
        Ampeln[2]= new Ampel(xx+Strassenbreite+Abstand,yy+Strassenbreite+Abstand,Ampelbreite,"Sueden");
        Ampeln[3]= new Ampel(xx-8*Ampelbreite-Abstand,yy+Strassenbreite+Abstand,Ampelbreite,"Westen");
        
        FussgaengerAmpeln[0]= new FussgaengerAmpel(xx-12*Ampelbreite,yy-6*Ampelbreite,Ampelbreite,"Sueden");
        FussgaengerAmpeln[1]= new FussgaengerAmpel(xx-12*Ampelbreite,yy+Strassenbreite+Abstand,Ampelbreite,"Norden");
        FussgaengerAmpeln[2]= new FussgaengerAmpel(xx+13*Ampelbreite,yy-6*Ampelbreite,Ampelbreite,"Sueden");
        FussgaengerAmpeln[3]= new FussgaengerAmpel(xx+13*Ampelbreite,yy+Strassenbreite+Abstand,Ampelbreite,"Norden");
        FussgaengerAmpeln[4]= new FussgaengerAmpel(xx+Strassenbreite+Abstand,yy-12*Ampelbreite,Ampelbreite,"Westen");
        FussgaengerAmpeln[5]= new FussgaengerAmpel(xx-6*Ampelbreite,yy-12*Ampelbreite,Ampelbreite,"Osten");
        FussgaengerAmpeln[6]= new FussgaengerAmpel(xx+Strassenbreite+Abstand,yy+13*Ampelbreite,Ampelbreite,"Westen");
        FussgaengerAmpeln[7]= new FussgaengerAmpel(xx-6*Ampelbreite,yy+13*Ampelbreite,Ampelbreite,"Osten");
        
        schalte_aus();
    }

    public void schalte_aus()
    {
        z = 0;
        Zustand = 1;
        for (int i=0; i<4; i++)
        {
            Ampeln[i].schalte_aus();
        }
        for (int i=0; i<8; i++)
        {
            FussgaengerAmpeln[i].schalte_aus();
        }
    }
    
    public void schalte_ein()
    {   
        z = 0;
        Zustand = 2;
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[0][i]);
        }
        for (int i=0;i<8;i++)
        {
            FussgaengerAmpeln[i].schalte_auf(ZustaendeFussgaengerAmpel[0][i]);
        }
    }
    
    public void schalte_auf(){
        switch(Zustand){
        case 1:
        Zustand=Zustand+1;
        z=0;
        break;
        
        case 2:
        Zustand=Zustand+1;
        z=1;
        break;
        
        case 3:
        Zustand=Zustand+1;
        z=2;
        break;
        
        case 4:
        Zustand=Zustand+1;
        z=3;
        break;
        
        case 5:
        Zustand=Zustand+1;
        z=4;
        break;
        
        case 6:
        Zustand=Zustand+1;
        z=5;
        break;
        
        case 7:
        Zustand=Zustand-5;
        z=6;
        break;
        
        
        default:
        System.out.println("Error");
        break;
        }
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[z][i]);
        }
        for (int i=0;i<8;i++)
        {
            FussgaengerAmpeln[i].schalte_auf(ZustaendeFussgaengerAmpel[z][i]);
        }
    }
    
}