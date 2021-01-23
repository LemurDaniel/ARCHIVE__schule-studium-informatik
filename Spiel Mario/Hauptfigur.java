import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.*;

public class Hauptfigur
{
    private Bild Figur;                 // Größe 72x95pxl
    private Bild blauesHintergrundBild; // Größe 72x95pxl
                                        // ZeichenfensterGröße 800x370pxl
    private static int posx = 50;        // Zeichenfesnteranfang = 0
    private static int posy = 275;      // 370-95=275 (Zeichenfensterhoehe-Bildhoehe)
    private static int Boden = 225;     // Boden der Karte beginnt hier
    
    private int maxSprunghoehe = 112;
    
    private String Laufrichtung;
    
    private String Bewegungsrichtungen[]={"rechtsLaufen","rechtsLaufenStop","linksLaufen"  ,"linksLaufenStop"  ,"Springen","SpringenStop"}; // Mögliche Knöpfe
    private String Knopfnamen[]         ={"Laufen"     ,"LaufenStop"      ,"zurueckLaufen","zurueckLaufenStop","Springen","SpringenStop"}; // Namen der Knöpfe unter jeweilgen Knopf gelistet
     
    private Timer Lauftimer;
    private Timer Springhochtimer;
    private Timer Springruntertimer;
        
    public Hauptfigur()
    {
        this(posx, posy, "Mario");
    }
    
    public Hauptfigur(String Spielfigur)
    {
        this(posx, Boden, Spielfigur);
    }
    
    public Hauptfigur(int x, int y, String Spielfigur)
    {
           Lauftimer = new Timer(1, new ActionListener() //erstellen des Lauftimers
            {
             public void actionPerformed(ActionEvent e)
                   {
                       lauf(1,2);  //Zugreifen auf die Methode lauf
                                }
           });
           
         Springhochtimer = new Timer(1, new ActionListener()  //erstellen des Springochtimers
            {
             public void actionPerformed(ActionEvent e)
                   {
                       springhoch(1,2);  //Zugreifen auf die Methode springhoch
                                }
           });
          Springruntertimer = new Timer(1, new ActionListener()  //erstellen des Springruntertimers
            {
             public void actionPerformed(ActionEvent e)
                   {
                       springrunter(1,2);  //Zugreifen auf die Methode springrunter
                                }
           });
       
      blauesHintergrundBild = new Bild("pic/blauesHintergrundBild.png",0,-95); 
      
      posx = x;
      posy = y;
      Boden=posy;
      
      switch(Spielfigur){
      case "Mario":     Figur = new Bild("pic/Mario.png"   ,posx,posy);break;
      case "Mario-2":   Figur = new Bild("pic/Mario2.png"  ,posx,posy);break;
      case "Mario-3":   Figur = new Bild("pic/Mario3.png"  ,posx,posy);break; 
      case "Mariotest":   Figur = new Bild("pic/Mariotest.png"  ,posx,posy);break;
      case "Luigi":     Figur = new Bild("pic/Luigi.png"   ,posx,posy);break;
      case "Luigi-2":   Figur = new Bild("pic/Luigi2.png"  ,posx,posy);break;
      case "Luigi-3":   Figur = new Bild("pic/Luigi3.png"  ,posx,posy);break;
      case "Wario":     Figur = new Bild("pic/Wario.png"   ,posx,posy);break;
      case "Wario-2":   Figur = new Bild("pic/Wario2.png"  ,posx,posy);break;
      case "Waluigi":   Figur = new Bild("pic/Waluigi.png" ,posx,posy);break;
      case "Waluigi-2": Figur = new Bild("pic/Waluigi2.png",posx,posy);break;
      
      default: System.out.println("--------------------------------------------------------------------------");
               System.out.println("Ungültiger Eingabewert//Klasse: Hauptmap Konstruktor: public Hauptfigur(int x, int y, String Spielfigur)");
               System.out.print("Eingabwert:  ");
               System.out.println(Spielfigur);
               System.out.println("--------------------------------------------------------------------------");
      }
    }
             
    public void zeichnen(){ // Methode zum zeichnen der objekte
        Figur.positioniereBild(Figur.pic,posx,posy);
    }
    
    public void loeschen(){  // Methode zum "loeschen" des Bildes (Figur wird von einem Bild der gleichen Größe und mit der selben Farbe wie der Spiehntergrund übermalt)
        blauesHintergrundBild.positioniereBild(blauesHintergrundBild.pic,posx,posy);
    }
  
    public void LaufenStart(String Richtung){  // Methode um Hauptfigur laufen zu lassen
          Laufrichtung=Richtung;
          Lauftimer.start();// (Laufen = Bewegung über x-Achse)
    }

    public void LaufenStop(){  // Methode um laufen der Hauptfigur zu stopen
          Lauftimer.stop();    // (Laufen = Bewegung über x-Achse)
    }
    
    private void lauf(int zeit, int Geschwindigkeit){  //Methode um dei Figur auf der x-Achse zu bewegen
        loeschen();
        
        if(Laufrichtung=="rechts"){posx = posx + Geschwindigkeit * zeit;}  // Formel um Verschiebung auf x-Achse nach rechts zu berechen  
        if(Laufrichtung=="links"){posx = posx - Geschwindigkeit * zeit;}  // Formel um Verschiebung auf x-Achse nach rechts zu berechen
        
        zeichnen();
    }
    
    public void SpringenStart(){  // Methode um Hauptfigur springen zu lassen
        Springhochtimer.start();  // (Springen = Bewegung über y-Achse)
    }
    
    public void SpringenStop(){  // Methode um springen der Hauptfigur zu stopen
        Springhochtimer.stop();  // (Springen = Bewegung über y-Achse)
        Springruntertimer.stop();
    }
    
   private void springhoch(int zeit, int Geschwindigkeit){  //Methode um dei Figur auf der y-Achse zu bewegen
        loeschen();
        
        posy = posy - Geschwindigkeit * zeit; //Formel um weite Groeße der Verschiebung zu berechen
        
        if(posy<=maxSprunghoehe){
                Springhochtimer.stop();
                Springruntertimer.start();        
        } 
        
        zeichnen();
   }
   
   private void springrunter(int zeit, int Geschwindigkeit){  //Methode um dei Figur auf der y-Achse zu bewegen
       loeschen();
       
        int newposy = Geschwindigkeit * zeit;  //Formel um weite Groeße der Verschiebung zu berechen
        posy=posy+newposy;    
                
        if(posy>=Boden){
         Springruntertimer.stop();
        }
        
        zeichnen();
   }   
   
   private void gibBewegungsrichungsEingabewerteaus(){
       System.out.print("Mögliche EingabeWerte:  ");
       for(int i=0;i<Bewegungsrichtungen.length;i++){
           System.out.print(Bewegungsrichtungen[i]);
           System.out.print(",  ");
       } 
       System.out.println(" ");
    }
    
    public void erstelleKnopf(String Knopf){  // Methode zum erstellen von ausgewählten Knöpfen im Zeichenfenster
        
        if(Knopf!=Bewegungsrichtungen[0]&&Knopf!=Bewegungsrichtungen[1]&&Knopf!=Bewegungsrichtungen[2]&&Knopf!=Bewegungsrichtungen[3]&&Knopf!=Bewegungsrichtungen[4]&&Knopf!=Bewegungsrichtungen[5]){ //prüfen auf ungültige Werte
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Ungültiger Eingabewert//Klasse: Hauptfigur, Methode: erstelleKnopf//");
            System.out.print("Eingabewert:  ");
            System.out.println(Knopf);
            gibBewegungsrichungsEingabewerteaus();
            System.out.println("--------------------------------------------------------------------------");    
        }
        
        
        if(Knopf==Bewegungsrichtungen[0]){           // erstellen des Laufen Knopfes mit der Funktion die LaufenBewegung zu Starten
        JButton FigurBewegrechtsStart=new JButton(Knopfnamen[0]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBewegrechtsStart);;
        FigurBewegrechtsStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){     
                      LaufenStart("rechts");   
                   }
              });
        }   
         
        if(Knopf==Bewegungsrichtungen[1]){     // erstellen des LaufenStop Knopfes mit der Funktion die LaufenBewegung zu Stopen
        JButton FigurBewegrechtsStop=new JButton(Knopfnamen[1]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBewegrechtsStop);;
        FigurBewegrechtsStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      LaufenStop();   
                   }
              });
        }
         
        if(Knopf==Bewegungsrichtungen[2]){           // erstellen des Laufen Knopfes mit der Funktion die LaufenBewegung zu Starten
        JButton FigurBeweglinksStart=new JButton(Knopfnamen[2]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBeweglinksStart);;
        FigurBeweglinksStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){    
                      LaufenStart("links");   
                   }
              });
        }   
         
        if(Knopf==Bewegungsrichtungen[3]){     // erstellen des LaufenStop Knopfes mit der Funktion die LaufenBewegung zu Stopen
        JButton FigurBeweglinksStop=new JButton(Knopfnamen[3]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBeweglinksStop);;
        FigurBeweglinksStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      LaufenStop();   
                   }
              });
        } 
         
        if(Knopf==Bewegungsrichtungen[4]){          // erstellen des Springen Knopfes mit der Funktion die SpringBewegung zu Starten
        JButton FigurSpringStart=new JButton(Knopfnamen[4]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurSpringStart);;
        FigurSpringStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      SpringenStart();   
                   }
              });
        } 
              
        if(Knopf==Bewegungsrichtungen[5]){    // erstellen des SpringenStop Knopfes mit der Funktion die SpringBewegung zu Stopen
        JButton FigurSpringStop=new JButton(Knopfnamen[5]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurSpringStop);;
        FigurSpringStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      SpringenStop();   
                   }
              });
       }
    }
}
