
public class Line
{
    Koordinaten P1;
    Koordinaten P2;
    
    String Farbe;
    
    public Line(Koordinaten p1, Koordinaten p2, String farbe){
        this(p1, p2, farbe, true);
    }
    
    public Line(Koordinaten p1, Koordinaten p2, boolean zeichne){
        this(p1, p2, "s", zeichne);
    }
    
    public Line(Koordinaten p1, Koordinaten p2, String farbe, boolean zeichne){
        P1 = p1;
        P2 = p2;
        Farbe = farbe;
        
        if(zeichne){zeichen();}
    }
    
    public Line(double p1x,double p1y, double p2x,double p2y, boolean zeichne){  
        this(p1x, p1y, p2x, p2y, "s", zeichne);
    }
    
    public Line(double p1x,double p1y, double p2x,double p2y, String farbe){  
        this(p1x, p1y, p2x, p2y, farbe, true);
    }
    
    public Line(double p1x,double p1y, double p2x,double p2y, String farbe, boolean zeichne){         
        float P1x = (float) p1x;
        float P1y = (float) p1y;
        
        float P2x = (float) p2x;
        float P2y = (float) p2y;
        
        P1 = new Koordinaten(P1x, P1y);
        P2 = new Koordinaten(P2x, P2y);
        Farbe = farbe;
        
        if(zeichne){zeichen();}
    }
    
    public void mark_Punkte(int rad, String farbe){
        P1.mark(rad,farbe);
        P2.mark(rad,farbe);
    }
    
    public void unmark_Punkte(int rad, String farbe){
        P1.unmark();
        P2.unmark();
    }
    
    public void zeichen(String farbe){
        Farbe = farbe;
        zeichen();
    }    

    public void zeichen(){
        ZEICHENFENSTER.gibFenster().zeichneStrecke((int)P1.Xpos, (int)P1.Ypos, (int)P2.Xpos, (int)P2.Ypos, Farbe);
    }
    
    public void loesche(){
        ZEICHENFENSTER.gibFenster().loescheStrecke((int)P1.Xpos, (int)P1.Ypos, (int)P2.Xpos, (int)P2.Ypos);
    }
    
    
    static Koordinaten gib_Mitte(Koordinaten p1, Koordinaten p2){
        Koordinaten retKoo = new Koordinaten();
        retKoo.Xpos = (p1.Xpos + p2.Xpos) / 2;
        retKoo.Ypos = (p1.Ypos + p2.Ypos) / 2;
        return retKoo;
    }
    
    static void loesche(Koordinaten p1, Koordinaten p2){
        ZEICHENFENSTER.gibFenster().loescheStrecke((int)p1.Xpos, (int)p1.Ypos, (int)p2.Xpos, (int)p2.Ypos);
    }
    
    static void verbinde(Koordinaten p1, Koordinaten p2, String farbe){
        verbinde(p1, p2, 1, farbe);
    }
    
    static void verbinde(Koordinaten p1, Koordinaten p2, int t){
        verbinde(p1, p2, t, "schwarz");
    }
    
    static void verbinde(Koordinaten p1, Koordinaten p2, int t, String farbe){
        ZEICHENFENSTER.gibFenster().zeichneStrecke((int)(p1.Xpos*(float)t), (int)(p1.Ypos*(float)t), (int)(p2.Xpos*(float)t), (int)(p2.Ypos*(float)t), farbe);
    }
 
}
