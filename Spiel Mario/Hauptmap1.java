import javax.swing.Timer;
import java.awt.event.*;
public class Hauptmap1
{
    
   RECHTECK [] Boden = new RECHTECK [150];
   private int breite = 6;
   private int x=30;
   private int y=330;
   private int t=10;
   Hauptmap1 Map1;
   RECHTECK [] Block= new RECHTECK [150];
   RECHTECK [] Gras= new RECHTECK [150];
   RECHTECK Mast;
   RECHTECK Fahne;
   KREIS Muster;
   
   Timer timer;
   
   public Hauptmap1(){
       this(30,330,10);
    }
    
    public Hauptmap1(int x, int y, int t)
    {
        timer = new Timer(1, new ActionListener()
            {
             public void actionPerformed(ActionEvent e)
                   {
                       beweg(1,1,1300);
                                }
           });
           
        Boden[0]= new RECHTECK(x,y,2*x,x-t,"braun", true);// 1 Erdblock 
        Boden[0]= new RECHTECK(x-x,y,60*x,x-t,"braun", true);// 1 Erdblock 
        Boden[1]= new RECHTECK(x-x,y-t,60*x,t,"gruen", true); // 1 Grasblock               
        Block[0]= new RECHTECK(120,290,70,30,"braun", true);
        Block[1]= new RECHTECK(190,260,70,30,"braun", true);
        Block[2]= new RECHTECK(260,230,70,30,"braun", true);
        Block[3]= new RECHTECK(330,200,70,30,"braun", true);
        Block[4]= new RECHTECK(400,170,70,30,"braun", true);
        Block[5]= new RECHTECK(540,170,70,30,"braun", true);
        Block[6]= new RECHTECK(680,200,70,30,"braun", true);
        Block[7]= new RECHTECK(820,170,70,30,"braun", true);
        Block[8]= new RECHTECK(960,230,70,30,"braun", true);
        Block[9]= new RECHTECK(1100,200,70,30,"braun", true);
        Block[10]= new RECHTECK(1240,260,70,30,"braun", true);
        Block[11]= new RECHTECK(1380,215,70,30,"braun", true);
        Gras[0]= new RECHTECK(120,290,70,10,"gruen", true);
        Gras[1]= new RECHTECK(190,260,70,10,"gruen", true);
        Gras[2]= new RECHTECK(260,230,70,10,"gruen", true);
        Gras[3]= new RECHTECK(330,200,70,10,"gruen", true);
        Gras[4]= new RECHTECK(400,170,70,10,"gruen", true);
        Gras[5]= new RECHTECK(540,170,70,10,"gruen", true);
        Gras[6]= new RECHTECK(680,200,70,10,"gruen", true);
        Gras[7]= new RECHTECK(820,170,70,10,"gruen", true);
        Gras[8]= new RECHTECK(960,230,70,10,"gruen", true);
        Gras[9]= new RECHTECK(1100,200,70,10,"gruen", true);
        Gras[10]= new RECHTECK(1240,260,70,10,"gruen", true);
        Gras[11]= new RECHTECK(1380,215,70,10,"gruen", true);
        Mast = new RECHTECK(1540,90,10,240,"weiss", true);
        Fahne = new RECHTECK(1490,90,60,40,"weiss", true);
        Muster = new KREIS(1515,110,15,"blau", true);
    
}

     public void zeichnen(){
        for(int i=0;i<=11;i++){
            Block[i].zeichnen();
            Gras[i].zeichnen();
        }
        Mast.zeichnen();
        Fahne.zeichnen();
        Muster.zeichnen();
    }
    
    public void loeschen(){
        for(int i=0;i<=11;i++){
            Block[i].loeschen();
            Gras[i].loeschen();
        }
        Mast.loeschen();
        Fahne.loeschen();
        Muster.loeschen();
    }
  
    public void Bewegenstart(){
          timer.start();
    }
    
    public void Bewegenstop(){
          timer.stop();
    }
    
     public void beweg(int zeit, int Geschwindigkeit,int Kartenende){
        int vx=Geschwindigkeit;
        
        int newposx= vx * zeit;   
        x=x+newposx;
        System.out.print(x);
        System.out.print(", ");
        if(x==Kartenende){Bewegenstop();}
            for(int i=0;i<=11;i++){
            Block[i].bewegex(newposx,"links");
            Gras[i].bewegex(newposx,"links");
        }
        Mast.bewegex(newposx,"links");
        Fahne.bewegex(newposx,"links");
        Muster.bewegex(newposx,"links");
    }
}
