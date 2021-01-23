import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;

public class Hauptmap{

   private RECHTECK [] Boden = new RECHTECK [150];
   private int breite = 6;
   private int x=0;
   private int y=0;
   private int t=10;
   private RECHTECK [] Block= new RECHTECK [150];
   private RECHTECK [] Gras= new RECHTECK [150];
   private RECHTECK Mast;
   private RECHTECK Fahne; 
   private KREIS Muster;
   
   private Timer Bewegungstimer;
   
   public Hauptmap(){
       this(300,330,10,"Karte-1");
    }
    
    public Hauptmap(String Karte){
       this(300,330,10,Karte);
    }
    
    public Hauptmap(int x, int y, int t, String Karte)
    {
        Bewegungstimer = new Timer(1, new ActionListener()  // Erstellen des Bewegungstimers
            {
             public void actionPerformed(ActionEvent e)
                   {        
                         bewege(1,4,1300);  // Zugreifen auf die Methode bewege
                     }
            });
        if(Karte=="Karte-1"){   
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
        Mast = new RECHTECK(1540,80,10,240,"weiss", true);
        Fahne = new RECHTECK(1490,80,60,40,"weiss", true);
        Muster = new KREIS(1515,100,15,"blau", true);
        } 
    }

     public void zeichnen(){  //zeichnen der jeweiligen Objekte in Klasse Hauptmap
        for(int i=0;i<=11;i++){
            Block[i].zeichnen();
            Gras[i].zeichnen();
        }
        Mast.zeichnen();
        Fahne.zeichnen();
        Muster.zeichnen();
    }
    
    public void loeschen(){  //loeschen der jeweiligen Objekte in Klasse Hauptmap
        for(int i=0;i<=11;i++){
            Block[i].loeschen();
            Gras[i].loeschen();
        }
        Mast.loeschen();
        Fahne.loeschen();
        Muster.loeschen();
    }
  
    public void BewegenStart(){  // Kartenbewegung wird gestartet
          Bewegungstimer.start();    // Timer wird gestartet            
    }
    
    public void BewegenStop(){  // Kartenbewegung wird gestopt
          Bewegungstimer.stop();     // Timer wird gestopt    
    }
    
   private void bewege(int zeit,int Geschwindigkeit,int Kartenende){  // Methode um Karte zu Bewegen
        int newposx= Geschwindigkeit * zeit;   // Formel um weite Groeße der Verschiebung zu berechen
        x=x+newposx;
        if(x>=Kartenende){BewegenStop();}       // Zugreifen auf die Bewegungsfunktionen der einzelnen Objekte um  
            for(int i=0;i<=11;i++){            // sie um den errechneten Wert "newposx" zu verschieben
            Block[i].bewegex(newposx,"links");
            Gras[i].bewegex(newposx,"links");
        }
        Mast.bewegex(newposx,"links");
        Fahne.bewegex(newposx,"links");
        Muster.bewegex(newposx,"links");
    }
    
    public void erstelleKnopf(String Knopf){  // Methode zum erstellen von ausgewählten Knöpfen im Zeichenfenster
        switch(Knopf){
            
        case "Start":  // erstellen des Start Knopfes mit der Funktion die Bewegung zu Starten
        JButton MapBewegStart=new JButton("Start");
        ZEICHENFENSTER.gibFenster().steuerungSued.add(MapBewegStart);;
        MapBewegStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      BewegenStart();   
                   }
              });
         break;
         
        case "Stop":  // erstellen des Stop Knopfes mit der Funktion die Bewegung zu Stopen
        JButton MapBewegStop=new JButton("Stop");
        ZEICHENFENSTER.gibFenster().steuerungSued.add(MapBewegStop);;
        MapBewegStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      BewegenStop();   
                   }
              });
        break;
       }
    }
}