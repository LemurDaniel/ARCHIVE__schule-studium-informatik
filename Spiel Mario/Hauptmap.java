import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Hauptmap{
    
    // ZeichenfensterGröße 800x370pxl
    // Kartenboden ist bei y=320!!!
   private static int Kartenboden = 320; //Der Festgelegte Kartenboden
   
   private int breite = 6;
   private int posx;
   private int posy;
   private int t=10;
   
   private RECHTECK [] Boden;
   private RECHTECK [] Block;
   private RECHTECK [] Gras;
   private RECHTECK Mast;
   private RECHTECK Fahne; 
   private KREIS Muster;
   
   private String Bewegungsrichtungen[]={"Start","Stop"};  // Mögliche Knöpfe
   private String Knopfnamen[]         ={"Start","Stop" }; // Namen der Knöpfe unter jeweilgen Knopf gelistet
   
   private static String SK[] = {"Karte-1", "Karte-2"}; // array mit allen im Spiel zu Verfügung stehenden Spielkarten
                                 // Hinzufügen von Spielkartem und zuweisen Namen alleinig in dieser Klasse
                                 
   private int Kartenende; 
   
   private Timer Bewegungstimer;
   
   public Hauptmap(){
       this(300,330,10,SK[0]);
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
                         bewege(1,4);  // Zugreifen auf die Methode bewege
                     }
            });
       if(!Arrays.asList(SK).contains(Karte)){ // prüfen auf gültige Werte mit Fehlerausgabe
                  
                 System.out.println("--------------------------------------------------------------------------");
                 System.out.println("Ungültiger Eingabewert//Klasse: Hauptmap Konstruktor: public Hauptmap(int x, int y, int t, String Karte)");
                 System.out.print("Eingabwert:  ");
                 System.out.println(Karte);
                 gibEingabewerteaus(SK);
                 System.out.println("--------------------------------------------------------------------------");
                 System.exit(0);
                     
        }
            
        
       if(Karte==SK[0]){  
            
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
       }
       
       if(Karte==SK[1]){
       Boden = new RECHTECK [2];
       Block= new RECHTECK [12];
       Gras= new RECHTECK [12];
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
        Mast = new RECHTECK(1540,80,10,240,"weiss", true);
        Fahne = new RECHTECK(1490,80,60,40,"weiss", true);
        Muster = new KREIS(1515,100,15,"blau", true);  
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
        posx=posx+newposx;
        
        if(posx>=Kartenende){BewegenStop();}       // Zugreifen auf die Bewegungsfunktionen der einzelnen Objekte um  sie um den errechneten Wert "newposx" zu verschieben
        for(int i=0;i<Block.length;i++){Block[i].bewegex(newposx,"links");}
        for(int i=0;i<Gras.length;i++){Gras[i].bewegex(newposx,"links");}
        for(int i=0;i<Boden.length;i++){Boden[i].bewegex(newposx,"links");}
        
        Mast.bewegex(newposx,"links");
        Fahne.bewegex(newposx,"links");
        Muster.bewegex(newposx,"links");
    }
    
    private void gibEingabewerteaus(String [] array){
       System.out.print("Mögliche EingabeWerte:  ");
       for(int i=0;i<array.length;i++){
           System.out.print(array[i]);
           System.out.print(",  ");
       } 
       System.out.println(" ");
    }
    
    public static String[] gibSpielkarten(){  // Methode zum Aussgeben des Arrays mit den Spielkarten
        return SK;
    }
    
    public static int gibKartenboden(){ // gibt den Kartenboden aus
        return Kartenboden;
    }
    
    public void erstelleKnopf(String Knopf){  // Methode zum erstellen von ausgewählten Knöpfen im Zeichenfenster
        
        if(!Arrays.asList(Bewegungsrichtungen).contains(Knopf)){ //prüfen auf ungültige Werte
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Ungültiger Eingabewert//Klasse: Hauptfigur, Methode: erstelleKnopf()//");
            System.out.print("Eingabewert:  ");
            System.out.println(Knopf);
            gibEingabewerteaus(Bewegungsrichtungen);
            System.out.println("--------------------------------------------------------------------------");
            System.exit(0);
        }

        if(Knopf==Bewegungsrichtungen[0]){  // erstellen des Start Knopfes mit der Funktion die Bewegung zu Starten
        JButton MapBewegStart=new JButton(Knopfnamen[0]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(MapBewegStart);;
        MapBewegStart.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      BewegenStart();   
                   }
              }); return;
        }
         
        if(Knopf==Bewegungsrichtungen[1]){  // erstellen des Stop Knopfes mit der Funktion die Bewegung zu Stopen
        JButton MapBewegStop=new JButton(Knopfnamen[1]);
        ZEICHENFENSTER.gibFenster().steuerungSued.add(MapBewegStop);;
        MapBewegStop.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      BewegenStop();   
                   }
              }); return;
       }
    }
    
}