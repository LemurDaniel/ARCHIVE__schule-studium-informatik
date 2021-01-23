import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.util.Arrays;

public class Hauptfigur
{
    private Bild Figur;                 // Größe 72x95pxl
    private Bild blauesHintergrundBild; // Größe 72x95pxl
    
    private static Dimension Spielfeld = ZEICHENFENSTER.gibFenster().gibMasse(); //holen der Dimension des Zeichenfensters

    private static int Bildbreite = 72; //(Breite der Figur)
    private static int Bildhoehe = 95; //(Hoehe der Figur)

    private static int posx = 50;       // Zeichenfesnteranfang = 0
    private static int posy = Spielfeld.height-Bildhoehe;      // (Zeichenfensterhoehe-hoehe der Figur)
    private static int Boden = Hauptmap.gibKartenboden()-Bildhoehe;   // Boden der Karte beginnt hier (Kartenboden-hoehe der Figur)
    
    private int maxSprunghoehe = Spielfeld.height/4; //Maximale Sprunghoehe
    
    private String Laufrichtung;
    
    private String Knoepfe[] =    {"rechtsLaufen","rechtsLaufenStop","linksLaufen"  ,"linksLaufenStop"  ,"Springen","SpringenStop"}; // Mögliche Knöpfe
    private String Knopfnamen[] = {"Laufen"     ,"LaufenStop"      ,"zurueckLaufen","zurueckLaufenStop","Springen","SpringenStop"}; // Namen der Knöpfe unter jeweilgen Knopf gelistet
    
    private static String SF[] = {"Mario"      , "Mario-2"      , "Mario-3"      , "Mario-4"      , "Luigi"       , "Luigi-2"      , "Luigi-3"      , "Luigi-4"      , "Wario"       , "Wario-2"      , "Wario-3"      , "Wario-4"      , "Waluigi"       , "Waluigi-2"      , "Waluigi-3"      , "Waluigi-4"      ,"Mario mit Yoshi"        ,"Yoshi"        ,"Bowser"        ,"Bowser Jr."           ,"Knochen Bowser"        ,"Koopa"        ,"Knochentrocken"        ,"DonkeyKong"        ,"Donkey mit Diddy"        ,"Cranky Kong"        ,"Big Bob omb"        ,"König Buu Huu"         ,"Kettenhund"}; //array mit Namen aller im Spiel zu verfügung stehenden Spielfiguren
    private String path[]   = {"pic/Mario.png","pic/Mario2.png","pic/Mario3.png","pic/Mario4.png","pic/Luigi.png","pic/Luigi2.png","pic/Luigi3.png","pic/Luigi4.png","pic/Wario.png","pic/Wario2.png","pic/Wario3.png","pic/Wario4.png","pic/WaLuigi.png","pic/WaLuigi2.png","pic/WaLuigi3.png","pic/WaLuigi4.png","pic/Mario_mit_Yoshi.png","pic/Yoshi.png","pic/Bowser.png","pic/Bowser_Junior.png","pic/Knochen_Bowser.png","pic/Koopa.png","pic/Knochentrocken.png","pic/DonkeyKong.png","pic/Donkey_mit_Diddy.png","pic/Cranky_Kong.png","pic/Big_Bob_omb.png","pic/Koenig_Buu_Huu.png","pic/Kettenhund.png"};  //array mit Pfaden zu den jeweiligen Bildern der Spielfiguren
                                   // Hinzufügen von Spielfiguren und zuweisen Namen in dieser Klasse
    private Timer Lauftimer;
    private Timer Springhochtimer;
    private Timer Springruntertimer;
        
    public Hauptfigur()
    {
        this(posx, posy, SF[0]);
        ZEICHENFENSTER.gibFenster().setzeSichtbar(true);
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
             
       if(!Arrays.asList(SF).contains(Spielfigur)){ //prüfen auf gültige Werte mit Fehlerausgabe
           Dialogfenster.gibFehlermeldung("Ungültiger Eingabewert","Konstruktor\nKlasse:  Hauptfigur \nEingabewert:  "+Spielfigur);
           Dialogfenster.gibArrayaus("Eingabewerte Hauptfigur","MöglicheEingabewerte:\n",SF,"Information",7);
       }
       
       posx = x;
       posy = y;
       Boden = posy; //Der Boden entspricht der Startposition auf der y-Achse
       
       blauesHintergrundBild = new Bild("pic/blauesHintergrundBild.png",0,-95); // y=-95 sodass es bei Spielbeginn außerhalb des Zeichenfensters ist und somit nicht gezeichnet wird
      
       for(int i=0;i<SF.length;i++){ 
          if(SF[i]==Spielfigur){Figur = new Bild(path[i] , posx, posy);break;} // zuweisen der Bildpfades, je nachdem welche Spielfigur gewählt wurde
       }  
    }
       
    public void zeichnen(){ // Methode zum zeichnen der objekte
        Figur.positioniereBild(Figur.pic,posx,posy);
    }
    
    public void loeschen(){  // Methode zum "loeschen" des Bildes (Figur wird von einem Bild der gleichen Größe und mit der selben Farbe wie der Spielntergrund übermalt)
        blauesHintergrundBild.positioniereBild(blauesHintergrundBild.pic,posx,posy);
    }
  
    public void LaufenStart(String Richtung){  // Methode um Hauptfigur laufen zu lassen
          if(Richtung!="rechts"&&Richtung!="links"){Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Eigabefehler: \nKlasse: Hauptfigur\nMethode: LaufenStart");System.exit(0);}
          Laufrichtung=Richtung;
          Lauftimer.start();// (Laufen = Bewegung über x-Achse)
    }

    public void LaufenStop(){  // Methode um laufen der Hauptfigur zu stopen
          Lauftimer.stop();    // (Laufen = Bewegung über x-Achse)
    }
    
    private void lauf(int zeit, int Geschwindigkeit){  //Methode um dei Figur auf der x-Achse zu bewegen
        loeschen();        
        if(Laufrichtung=="rechts"){posx += Geschwindigkeit * zeit;  // Formel um Verschiebung auf x-Achse nach rechts zu berechen  
            if(posx>Spielfeld.width){posx = 0-Bildbreite;}} // Falls Figur Zeichenfensterende erreicht wird sie an den Anfang des Zeichenfensters gesetzt
        if(Laufrichtung=="links"){posx -= Geschwindigkeit * zeit;  // Formel um Verschiebung auf x-Achse nach rechts zu berechen
            if(posx<0-Bildbreite){posx = Spielfeld.width;}} // Falls Figur Zeichenfensteranfang erreicht wird sie an das Ende des Zeichenfensters gesetzt
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
        
        posy -= Geschwindigkeit * zeit; //Formel um weite Groeße der Verschiebung zu berechen
        
        if(posy<=maxSprunghoehe){
                Springhochtimer.stop();
                Springruntertimer.start();        
        } 
        
        zeichnen();
   }
   
   private void springrunter(int zeit, int Geschwindigkeit){  //Methode um dei Figur auf der y-Achse zu bewegen
       loeschen();
       
        int newposy = Geschwindigkeit * zeit;  //Formel um weite Groeße der Verschiebung zu berechen
        posy+=newposy;    
                
        if(posy>=Boden){
         Springruntertimer.stop();
        }
        
        zeichnen();
   }   
   
    public static String[] gibSpielfiguren(){ // Methode zum Aussgeben des Arrays mit den Spielfigure
        return SF;
    }
    
    public void erstelleKnopf(String Knopf){  // Methode zum erstellen von ausgewählten Knöpfen im Zeichenfenster
        
        if(!Arrays.asList(Knoepfe).contains(Knopf)){ //prüfen auf ungültige Werte
            Dialogfenster.gibFehlermeldung("Ungültiger Eingabewert","Klasse: Hauptfigur\nMethode: erstelleKnopf\nEingabewert:  "+Knopf);
            Dialogfenster.gibArrayaus("Eingabewerte Hautpfigur.erstellteKnopf","MöglicheEingabewerte:\n",Knoepfe,"Information",6);
        }
   
        if(Knopf==Knoepfe[0]){           // erstellen des Laufen Knopfes mit der Funktion die LaufenBewegung zu Starten
        JButton FigurBewegrechtsStart=new JButton(Knopfnamen[0]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBewegrechtsStart);;
        FigurBewegrechtsStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){     
                      LaufenStart("rechts");   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
        }   
         
        if(Knopf==Knoepfe[1]){     // erstellen des LaufenStop Knopfes mit der Funktion die LaufenBewegung zu Stopen
        JButton FigurBewegrechtsStop=new JButton(Knopfnamen[1]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBewegrechtsStop);;
        FigurBewegrechtsStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      LaufenStop();   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
        }
         
        if(Knopf==Knoepfe[2]){           // erstellen des Laufen Knopfes mit der Funktion die LaufenBewegung zu Starten
        JButton FigurBeweglinksStart=new JButton(Knopfnamen[2]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBeweglinksStart);;
        FigurBeweglinksStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){    
                      LaufenStart("links");   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
        }   
         
        if(Knopf==Knoepfe[3]){     // erstellen des LaufenStop Knopfes mit der Funktion die LaufenBewegung zu Stopen
        JButton FigurBeweglinksStop=new JButton(Knopfnamen[3]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurBeweglinksStop);;
        FigurBeweglinksStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      LaufenStop();   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
        } 
         
        if(Knopf==Knoepfe[4]){          // erstellen des Springen Knopfes mit der Funktion die SpringBewegung zu Starten
        JButton FigurSpringStart=new JButton(Knopfnamen[4]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurSpringStart);;
        FigurSpringStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      SpringenStart();   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
        } 
              
        if(Knopf==Knoepfe[5]){    // erstellen des SpringenStop Knopfes mit der Funktion die SpringBewegung zu Stopen
        JButton FigurSpringStop=new JButton(Knopfnamen[5]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(FigurSpringStop);;
        FigurSpringStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      SpringenStop();   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
       }
    }
}
