import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.util.Arrays;

public class Hauptmap{
    
    // ZeichenfensterGröße 800x370pxl
    
   private static Dimension Spielfeld = ZEICHENFENSTER.gibFenster().gibMasse();  //holen der Dimension des Zeichenfensters
   
   private static final int Kartenboden = Spielfeld.height-50; //Der Festgelegte Kartenboden
   
   private int breite = 6;
   private int posx;
   private int posy;
   private int t=10;
   
   private int Blockhoehe;
   private int Blockbreite;
   
   private int Kartenende; 
   
   private boolean Zufallskarte;
   
   private RECHTECK [] Boden;
   private RECHTECK [] Block;
   private RECHTECK [] Gras;
   private RECHTECK Mast;
   private RECHTECK Fahne; 
   private KREIS Muster;
   
   private String Knoepfe[] =  {"Start","Stop"};  // Mögliche Knöpfe
   private String Knopfnamen[]={"Start","Stop" }; // Namen der Knöpfe unter jeweilgen Knopf gelistet
   
   private static String SK[] = {"Karte-1", "Karte-2", "Map mit ZufallsBlöcken-Test"}; // array mit allen im Spiel zu Verfügung stehenden Spielkarten
                                 // Hinzufügen von Spielkartem und zuweisen Namen alleinig in dieser Klasse                                 
   int Line=1;//Nur zum Testen
   
   private Timer Bewegungstimer;
   
   public Hauptmap(){
       this(300,330,10,SK[2]);
       ZEICHENFENSTER.gibFenster().setzeSichtbar(true);
    }
    
    public Hauptmap(String Karte){
       this(300,330,10,Karte);
    }
    
    public Hauptmap(int x, int y, int t, String Spielkarte)
    {
        Bewegungstimer = new Timer(10, new ActionListener()  // Erstellen des Bewegungstimers
            {
             public void actionPerformed(ActionEvent e)
                   {        
                         bewege(1,2);  // Zugreifen auf die Methode bewege
                     }
            });
       if(!Arrays.asList(SK).contains(Spielkarte)){ // prüfen auf gültige Werte mit Fehlerausgabew
           Dialogfenster.gibFehlermeldung("Ungültiger Eingabewert","Konstruktor\nKlasse:  Hauptmap \nEingabewert:  "+Spielkarte);
           Dialogfenster.gibArrayaus("Eingabewerte Hautpmap","MöglicheEingabewerte:\n",SK,"Information",7);
        }
                 
       if(Spielkarte==SK[0]){         
       Zufallskarte = false;    
       Boden = new RECHTECK [2];
       Block= new RECHTECK [12];
       Gras= new RECHTECK [12];
            Kartenende = 1300;    // Festlegen des des Kartenendes
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
        
        return; // return um nach gefundener Karte abzubrechen
       }
       
       if(Spielkarte==SK[1]){
       Zufallskarte = false;  
       Boden = new RECHTECK [2];
       Block= new RECHTECK [13];
       Gras= new RECHTECK [13];
            Kartenende = 1100;    // Festlegen des des Kartenendes
        Boden[0]= new RECHTECK(0,Kartenboden+10,800,40,"rot", true);
        Boden[1]= new RECHTECK(0,Kartenboden,800,10,"gelb", true);            
        Block[0]= new RECHTECK(120,290,70,30,"rot", true);
        Block[1]= new RECHTECK(190,260,70,30,"rot", true);
        Block[2]= new RECHTECK(260,230,70,30,"rot", true);
        Block[3]= new RECHTECK(330,200,70,30,"rot", true);
        Block[4]= new RECHTECK(400,170,70,30,"rot", true);
        Block[5]= new RECHTECK(540,170,70,30,"rot", true);
        Block[6]= new RECHTECK(680,200,70,30,"rot", true);
        Block[7]= new RECHTECK(820,170,70,30,"rot", true);
        Block[8]= new RECHTECK(960,230,70,30,"rot", true);
        Block[9]= new RECHTECK(1100,200,70,30,"rot", true);
        Block[10]= new RECHTECK(1240,260,70,30,"rot", true);
        Block[11]= new RECHTECK(1380,215,70,30,"rot", true);
        Block[12]= new RECHTECK(1400,115,70,30,"rot", true);
        Gras[0]= new RECHTECK(120,290,70,10,"gelb", true);
        Gras[1]= new RECHTECK(190,260,70,10,"gelb", true);
        Gras[2]= new RECHTECK(260,230,70,10,"gelb", true);
        Gras[3]= new RECHTECK(330,200,70,10,"gelb", true);
        Gras[4]= new RECHTECK(400,170,70,10,"gelb", true);
        Gras[5]= new RECHTECK(540,170,70,10,"gelb", true);
        Gras[6]= new RECHTECK(680,200,70,10,"gelb", true);
        Gras[7]= new RECHTECK(820,170,70,10,"gelb", true);
        Gras[8]= new RECHTECK(960,230,70,10,"gelb", true);
        Gras[9]= new RECHTECK(1100,200,70,10,"gelb", true);
        Gras[10]= new RECHTECK(1240,260,70,10,"gelb", true);
        Gras[11]= new RECHTECK(1380,215,70,10,"gelb", true);
        Gras[12]= new RECHTECK(1400,115,70,10,"gelb", true);
        Mast = new RECHTECK(1540,80,10,240,"weiss", true);
        Fahne = new RECHTECK(1490,80,60,40,"weiss", true);
        Muster = new KREIS(1515,100,15,"blau", true); 
        
        return; // return um nach gefundener Karte abzubrechen
       } 
       
       if(Spielkarte==SK[2]){
           
            Kartenende = 20000;
            Zufallskarte = true;
            Blockbreite = 70;
            Blockhoehe = 30;
            int Blockanzahl = 10;
            int Divisor = 3;
         
            int ZufallY[] = Mathe.gibArrayausZufallszahlen(Blockanzahl,0,Kartenboden-Blockhoehe);
            Dialogfenster.gibArrayaus("Eingabewerte Hautpmap","Zufallszahlen:\n",ZufallY,"Information",7); //Nur zu zum Testen
            int ZufallX[] = Mathe.gibArrayausZufallszahlen(Blockanzahl,0,Spielfeld.width*4);
            Dialogfenster.gibArrayaus("Eingabewerte Hautpmap","Zufallszahlen:\n",ZufallX,"Information",7); //Nur zu zum Testen            
            
            Boden = new RECHTECK [2];
            Block = new RECHTECK [Blockanzahl];
            Gras = new RECHTECK [Blockanzahl];
                
            Boden[0]= new RECHTECK(0,Kartenboden+10,800,40,"rot", true);
            Boden[1]= new RECHTECK(0,Kartenboden,800,10,"gelb", true);
        
            for(int i=0;i<Blockanzahl;i++){
                Block[i]= new RECHTECK(ZufallX[i],ZufallY[i],Blockbreite,Blockhoehe,"rot", true);

                Gras[i]= new RECHTECK(ZufallX[i],ZufallY[i],Blockbreite,Blockhoehe/Divisor,"gelb", true);
            }
 
            Mast = new RECHTECK(20500,80,10,240,"weiss", true);
            Fahne = new RECHTECK(20450,80,60,40,"weiss", true);
            Muster = new KREIS(20475,100,15,"blau", true);  
            
            return; // return um nach gefundener Karte abzubrechen
       }
    }
    
    public int gibKartenende(){
        return Kartenende;
    }

    public void zeichnen(){  //zeichnen der jeweiligen Objekte in Klasse Hauptmap
        for(int i=0;i<Block.length;i++){Block[i].zeichnen();}
        for(int i=0;i<Gras.length;i++){Gras[i].zeichnen();}
        for(int i=0;i<Boden.length;i++){Boden[i].zeichnen();}
        Mast.zeichnen();
        Fahne.zeichnen();
        Muster.zeichnen();
    }
    
    public void loeschen(){  //loeschen der jeweiligen Objekte in Klasse Hauptmap
        for(int i=0;i<Block.length;i++){Block[i].loeschen();}
        for(int i=0;i<Gras.length;i++){Gras[i].loeschen();}
        for(int i=0;i<Boden.length;i++){Boden[i].loeschen();}
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
    
   private void bewege(int zeit,int Geschwindigkeit){  // Methode um Karte zu Bewegen
        int newposx= Geschwindigkeit * zeit;   // Formel um weite Groeße der Verschiebung zu berechen
        posx+=newposx;               
        
        if(posx>=Kartenende){BewegenStop();}       // Zugreifen auf die Bewegungsfunktionen der einzelnen Objekte um  sie um den errechneten Wert "newposx" zu verschieben
        for(int i=0;i<Block.length;i++){
            Block[i].bewegex(newposx,"links");
            Gras[i].bewegex(newposx,"links");
          
            if(Zufallskarte==true){
                if(Block[i].gibPositionX()<0 - Block[i].gibbreite() && Gras[i].gibPositionX()<0 - Gras[i].gibbreite()){
                    
                    int ZufallY = Mathe.gibZufallszahl(0,Kartenboden-Blockhoehe);
                    int ZufallX = Mathe.gibZufallszahl(Spielfeld.width,Spielfeld.width*4);
                                        
                    Block[i].aenderePosition(ZufallX,ZufallY);
                    Gras[i].aenderePosition(ZufallX,ZufallY);
                    
                    
                    System.out.print(Line++ + ".Zeile : " + " ZufallX:  " + ZufallX + ":  :" + "ZufallY:  " + ZufallY + "\n"); //Nur zum Testen
                }
            }
        }
        //for(int i=0;i<Boden.length;i++){Boden[i].bewegex(newposx,"links");}
        
        Mast.bewegex(newposx,"links");
        Fahne.bewegex(newposx,"links");
        Muster.bewegex(newposx,"links");
    }
    
    public static String[] gibSpielkarten(){  // Methode zum Aussgeben des Arrays mit den Spielkarten
        return SK;
    }
    
    public static int gibKartenboden(){ // gibt den Kartenboden aus
        return Kartenboden;
    }
    
    public void erstelleKnopf(String Knopf){  // Methode zum erstellen von ausgewählten Knöpfen im Zeichenfenster
        
        if(!Arrays.asList(Knoepfe).contains(Knopf)){ //prüfen auf ungültige Werte
            Dialogfenster.gibFehlermeldung("Ungültiger Eingabewert","Klasse: Hauptmap\nMethode: erstelleKnopf\nEingabewert:  "+Knopf);
            Dialogfenster.gibArrayaus("Eingabewerte Hautpmap.erstellteKnopf","MöglicheEingabewerte:\n",Knoepfe,"Information",6);
        }

        if(Knopf==Knoepfe[0]){  // erstellen des Stop Knopfes mit der Funktion die Bewegung zu Starten
        JButton MapBewegStop=new JButton(Knopfnamen[0]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(MapBewegStop);;
        MapBewegStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      BewegenStart();   
                   }
               }); return; // return um Methode nach gefundener Karte abzubrechen
        }
         
        if(Knopf==Knoepfe[1]){  // erstellen des Stop Knopfes mit der Funktion die Bewegung zu Stopen
        JButton MapBewegStop=new JButton(Knopfnamen[1]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(MapBewegStop);;
        MapBewegStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      BewegenStop();   
                   }
              }); return; // return um Methode nach gefundenen Knopf abzubrechen
       }
    }
    
}