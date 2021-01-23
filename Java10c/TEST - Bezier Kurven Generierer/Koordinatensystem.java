import java.awt.*;
import java.lang.Math.*;

public class Koordinatensystem
{
    static private Dimension Masse = ZEICHENFENSTER.gibFenster().gibMasse();
    
    static private int Rand = 50;
    static private int Hoehe = Masse.height - Rand;
    static private int Breite = Masse.width - Rand;
    
    private Line Y_Achse;
    private Line X_Achse;
    
    private Koordinaten Ursprung;
    
    private Curve Kurve;
    
    private Koordinaten Start;
    private Koordinaten Bezug;
    private Koordinaten Ende;
    
    private Koordinaten[] Punkte;
    
    private Line[][] Gitternetz;
    
    private Line[] Kurve_Gitternetz;
    
    private Line[]  Kurve_Linie;     
    
    public Koordinatensystem(){        
       this(new Koordinaten(Rand,Hoehe));
       Berechne_Kurve(10);
       zeichne_Kurve();
    }
    
    public Koordinatensystem(Koordinaten bezug){  
        
        Ursprung = new Koordinaten(Rand,Hoehe);
        
        Y_Achse = new Line(Rand,Hoehe,   Rand,Rand, "weiss");
        X_Achse = new Line(Rand,Hoehe,  Breite,Hoehe, "weiss");
        
        Start = Y_Achse.P2;
        Bezug = bezug;
        Ende = X_Achse.P2;
        
        Start.add_mouseListener();
        Bezug.add_mouseListener();
        Ende.add_mouseListener();
        
        ZEICHENFENSTER.gibFenster().setzeSichtbar(true);
    }
    
    public void change_Bezug(Koordinaten bezug){
        Bezug = bezug;
    }
    
    public void DoIt(int points){
        Berechne_Kurve(points);
        zeichne_Kurve();
        zeichne_Kurve_Gitternetz();
    }
    
    
    //Berechnen
    public void Berechne_Gitternetz(int Punkte){
        
        if(Gitternetz != null){
            loesche_Gitternetz();          
        }
        
        Gitternetz = new Line[2][Punkte];
               
        float SchrittX = (X_Achse.P2.Xpos - X_Achse.P1.Xpos) / Punkte;
        float SchrittY = (Y_Achse.P2.Ypos - X_Achse.P1.Ypos) / Punkte;
        
        float Sx = Ursprung.Xpos + SchrittX;
        float Sy = Ursprung.Ypos + SchrittY;     
        
            for(int i = 0; i<Punkte; i++){
                
                Gitternetz[0][i] = new Line(Rand,Sy, X_Achse.P2.Xpos,Sy, false);
                Gitternetz[1][i] = new Line(Sx,Rand, Sx,Ursprung.Ypos,  false);
                Sx += SchrittX;
                Sy += SchrittY;
            }
    }
    
    public void Berechne_Kurve(int Punkte_Anzahl){
        
        if(Punkte != null){
            //loesche_Kurve();
            //loesche_Alles();
        }
        
        Kurve_Gitternetz = new Line[Punkte_Anzahl];
               
        Punkte = new Koordinaten[Punkte_Anzahl+2];    
               
        double Schritt = 1/(double)(Punkte_Anzahl+1); 
        double t = Schritt;
        
        Kurve = new Curve(Start, Bezug, Ende);
        
            Punkte[0] = Start;
            Punkte[Punkte.length-1] = Ende; 
        
            for(int i = 1; i<Punkte.length-1; i++){
                
                Kurve.Calculate_QRP(t);
                
                Kurve_Gitternetz[i-1] = new Line(Kurve.Q, Kurve.R, false); 
                
                Punkte[i] = Kurve.P;           
                
                t = (double) Math.round( (t + Schritt)*10000 ) /10000;                
        
            }
            
    }
    
    
    // zeichnen
    public void zeichne_Kurve(){
        
        if(Punkte == null){
            System.out.print("Keine Kurve vorhanden");
            return;
        }       
        
            Start.mark(5, "rot"); 
            Ende.mark(5, "rot"); 
            Bezug.mark(5, "rot");
            
            
             for(int i = 1; i<Punkte.length-1; i++){
                  
                Punkte[i].mark(2, "orange");
                wait(1);
             }
        
        
        Kurve_Linie = new Line[Punkte.length-1];
        
             for(int i = 0; i<Kurve_Linie.length; i++){
                
                Kurve_Linie[i] = new Line(Punkte[i], Punkte[i+1], "hellgruen");
                wait(1);
        
            }
            
            Line.verbinde(Start, Bezug, "hellblau");
            Line.verbinde(Bezug, Ende, "hellblau");
            
                    
        X_Achse.zeichen();
        Y_Achse.zeichen();
        
    }
    
    public void zeichne_Kurve_Gitternetz(){       
        
        if(Kurve_Gitternetz == null){
            System.out.print("Keine Kurve_Gitternetz vorhanden");
            return;
        }        
        
            for(int i = 0; i<Kurve_Gitternetz.length; i++){
                
                Kurve_Gitternetz[i].zeichen("hellgrau");
                wait(10);
        
            }
            
        X_Achse.zeichen();
        Y_Achse.zeichen();
    }
    
    public void zeichne_Gitternetz(){
        
        if(Gitternetz == null){
            System.out.print("Keine Gitternetz vorhanden");
            return;
        }
        
            for(int i = 0; i<Gitternetz[0].length; i++){
                
                Gitternetz[0][i].zeichen("hellgrau");
                //Gitternetz[0][i].mark_Punkte(5, "orange");
                Gitternetz[1][i].zeichen("hellgrau");
                //Gitternetz[1][i].mark_Punkte(5, "orange");
                wait(10);
        
            }
            
        X_Achse.zeichen();
        Y_Achse.zeichen();
        
    }
    
    
    // loeschen
    public void loesche_Kurve(){
        
            for(int i = 0; i<Kurve_Linie.length; i++){              
                Kurve_Linie[i].loesche();       
            }
            
            for(int i = 0; i<Punkte.length; i++){              
                Punkte[i].unmark();       
            }
            
            for(int i = 0; i<Kurve_Gitternetz.length; i++){                
                Kurve_Gitternetz[i].loesche();     
            }
            
            Line.loesche(Start, Bezug);
            Line.loesche(Bezug, Ende);
           Bezug.unmark();
    }
    
    public void loesche_Gitternetz(){
        
            for(int i = 0; i<Gitternetz[0].length; i++){              
                Gitternetz[0][i].loesche(); 
                Gitternetz[1][i].loesche();       
            }

     }
     
     public void loesche_Alles(){
         ZEICHENFENSTER.gibFenster().loescheAlles();
     }
    
    
    private void wait(int zeit){
        ZEICHENFENSTER.gibFenster().warte(zeit);
    }

}
