class Ampelanlage
{
    Ampel[] Ampeln = new Ampel[4];
    KASTEN Querstrasse;
    KASTEN Laengsstrasse;
    int Fensterbreite=600;
    int Strassenbreite=40;
    int x = 300; int y = 100; // (x,y)-Koordinaten der Kreuzung
    Ampelanlage()
    {
        this(300, 100);
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
    }
	  public void schalte_ein()
    {
       for (int i=0; i<4; i++)
       {
           Ampeln[i].schalte_ein();
       }
    }
	
    public void schalte_aus()
    {
       for (int i=0; i<4; i++)
       {
           Ampeln[i].schalte_aus();
       }
    }
}