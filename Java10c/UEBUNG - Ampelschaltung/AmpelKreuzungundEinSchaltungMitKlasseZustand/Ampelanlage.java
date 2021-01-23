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
    int x= 1 ;
    //int x = 300; int y = 100; // (x,y)-Koordinaten der Kreuzung
    ZUSTAND[] Zustaende = new ZUSTAND[7];
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
        for (int i=0; i<7;i++)
        {
            Zustaende[i]=new ZUSTAND();
        }
        String[] z0 = {"rot","rot","rot","rot"}; Zustaende[0].setze(z0,"1");
        String[] z1 = {"rot","rot-gelb","rot","rot-gelb"}; Zustaende[1].setze(z1,"2");
        String[] z2 = {"rot","gruen","rot","gruen"}; Zustaende[2].setze(z2,"3");
        String[] z3 = {"rot","gelb","rot","gelb"}; Zustaende[3].setze(z3,"4");
        String[] z4 = {"rot-gelb","rot","rot-gelb","rot"}; Zustaende[4].setze(z4,"5");
        String[] z5 = {"gruen","rot","gruen","rot"}; Zustaende[5].setze(z5,"6");
        String[] z6 = {"gelb","rot","gelb","rot"}; Zustaende[6].setze(z6,"7");
    }

    public void schalte_aus()
    {
        for (int i=0; i<4; i++)
        {
            Ampeln[i].schalte_aus();
        }
    }
    
    public void schalte_ein()
    {
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[0].hole(i));
        }
    }
    
    public void schalte_weiter(){
        switch(x){
            
        case 1:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[0].hole(i));
        }x +=1;
        break;
        
        case 2:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[1].hole(i));
        }x +=1;
        break;
        
        case 3:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[2].hole(i));
        }x +=1;
        break;
        
        case 4:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[3].hole(i));
        }x +=1;
        break;
        
        case 5:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[4].hole(i));
        }x +=1;
        break;
        
        case 6:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[5].hole(i));
        }x +=1;
        break;
        
        case 7:    
        for (int i=0;i<4;i++)
        {
            Ampeln[i].schalte_auf(Zustaende[6].hole(i));
        }x -=5;
        break;
        
        }
        }
        
        
}

