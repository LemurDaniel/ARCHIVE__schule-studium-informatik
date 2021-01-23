import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

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
    private static String SF[] = {"Mario", "Mario-2", "Mario-3", "Luigi", "Luigi-2", "Luigi-3", "Wario", "Wario-2", "Waluigi", "Waluigi-2", "Mariotest", "Luigitest", "Wariotest", "Waluigitest"}; //Array mit allen im Spiel zu verfügung stehenden Spielfiguren
                                   // Hinzufügen von Spielfiguren und zuweisen Namen alleinig in dieser Klasse
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
             
      if(Arrays.asList(SF).contains(Spielfigur)==false){ //prüfen auf gültige Werte mit Fehlerausgabe
               System.out.println("--------------------------------------------------------------------------");
               System.out.println("Ungültiger Eingabewert//Klasse: Hauptmap Konstruktor: public Hauptfigur()");
               System.out.print("Eingabwert:  ");
               System.out.println(Spielfigur);
               gibEingabewerteaus(SF);
               System.out.println("--------------------------------------------------------------------------");
               System.exit(0);
              }
                   
      posx = x;
      posy = y;
      Boden=posy; 
      blauesHintergrundBild = new Bild("pic/blauesHintergrundBild.png",0,-95); 
              
      if(SF[0] ==Spielfigur){Figur = new Bild("pic/Mario.png"      ,posx,posy);} 
      if(SF[1] ==Spielfigur){Figur = new Bild("pic/Mario2.png"     ,posx,posy);} 
      if(SF[2] ==Spielfigur){Figur = new Bild("pic/Mario3.png"     ,posx,posy);} 
      
      if(SF[3] ==Spielfigur){Figur = new Bild("pic/Luigi.png"      ,posx,posy);} 
      if(SF[4] ==Spielfigur){Figur = new Bild("pic/Luigi2.png"     ,posx,posy);} 
      if(SF[5] ==Spielfigur){Figur = new Bild("pic/Luigi3.png"     ,posx,posy);}
      
      if(SF[6] ==Spielfigur){Figur = new Bild("pic/Wario.png"      ,posx,posy);} 
      if(SF[7] ==Spielfigur){Figur = new Bild("pic/Wario2.png"     ,posx,posy);} 
      
      if(SF[8]==Spielfigur){Figur = new Bild("pic/Waluigi.png"    ,posx,posy);}      
      if(SF[9]==Spielfigur){Figur = new Bild("pic/Waluigi2.png"   ,posx,posy);}
      
      if(SF[10]==Spielfigur){Figur = new Bild("pic/Mariotest.png"  ,posx,posy);}
      if(SF[11]==Spielfigur){Figur = new Bild("pic/Luigitest.png"  ,posx,posy);} 
      if(SF[12]==Spielfigur){Figur = new Bild("pic/Wariotest.png"  ,posx,posy);} 
      if(SF[13]==Spielfigur){Figur = new Bild("pic/Waluigitest.png",posx,posy);} 
  
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
   
    public static String[] gibSpielfiguren(){ // Methode zum Aussgeben des Arrays mit den Spielfigure
        return SF;
    }
    
    private void gibEingabewerteaus(String [] array){
       System.out.print("Mögliche EingabeWerte:  ");
       for(int i=0;i<array.length;i++){
           System.out.print(array[i]);
           System.out.print(",  ");
       } 
       System.out.println(" ");
    }
    
    public void erstelleKnopf(String Knopf){  // Methode zum erstellen von ausgewählten Knöpfen im Zeichenfenster
        
        if(Arrays.asList(Bewegungsrichtungen).contains(Knopf)==false){ //prüfen auf ungültige Werte
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Ungültiger Eingabewert//Klasse: Hauptfigur, Methode: erstelleKnopf//");
            System.out.print("Eingabewert:  ");
            System.out.println(Knopf);
            gibEingabewerteaus(Bewegungsrichtungen);
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
