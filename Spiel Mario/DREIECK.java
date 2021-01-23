
/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (25.05.2015v2)
 */

public class DREIECK {

    private int [] positionX = new int[3];

    private int [] positionY = new int[3];
    
    private int hoehe;
    
    private int breite;
    
    private String fuellfarbe;
                                           //obereEcke , linkeEcke , rechteEcke
    private static String Ausgangsecke[] = {"obereEcke","linkeEcke","rechteEcke"}; //Array das alle Ausgangsecken beinhaltet
    
    private String StandartEcke; //Standart-Ecke von der aus Koordinaten berrechnet werden
    
    private boolean fuellung;


    public DREIECK(){
        this(0, 100, 50,"rot", false);
    }
    
    public DREIECK(int posx, int posy , int height, String color){ //Konstruktor für Standart-Dreieck. x- und y-Koordinaten von Standart-Ecke ausgehend. 
        this(posx,posy,height,color,Ausgangsecke[1],true);
    }
    
    public DREIECK(int posx, int posy , int height, String color, boolean filled){ //Konstruktor für Standart-Dreieck. x- und y-Koordinaten von Standart-Ecke ausgehend 
        this(posx,posy,height,color,Ausgangsecke[1],filled);
    }
    
    public DREIECK(int posx, int posy , int height, String color, String Ecke, boolean filled){ //Konstruktor für Standart-Dreieck zu erzeugen. x- und y-Koordinaten von einer zu wählenden Ecke ausgehend 
        hoehe=height;
        breite=hoehe*2;
        fuellfarbe = color;   // Farbe mit der das Dreieck gefüllt wird
        fuellung = filled;    // true/false Wert ob das Dreieck gefuellt wird oder nicht
        StandartEcke=Ecke;
     
        if(Ecke==Ausgangsecke[0]){         //Berechnen des Dreiecks von oberer Ecke ausgehend
        positionX[0] = posx;               //Position der 1.Ecke auf x-Achse (obere Ecke)
        positionY[0] = posy;               //Position der 1.Ecke auf y-Achse (obere Ecke)
        positionX[1] = posx-breite/2;      //Position der 2.Ecke auf x-Achse (linke Ecke)
        positionY[1] = posy+height;        //Position der 2.Ecke auf y-Achse (linke Ecke)
        positionX[2] = posx+breite/2;      //Position der 3.Ecke auf x-Achse (rechte Ecke)
        positionY[2] = posy+height;        //Position der 3.Ecke auf y-Achse (rechte Ecke) 
        }else{
        if(Ecke==Ausgangsecke[1]){        //Berechnen des Dreiecks von linker Ecke ausgehend    
        positionX[0] = posx+breite/2;     //Position der 1.Ecke auf x-Achse (obere Ecke)
        positionY[0] = posy-hoehe;        //Position der 1.Ecke auf y-Achse (obere Ecke)
        positionX[1] = posx;              //Position der 2.Ecke auf x-Achse (linke Ecke)
        positionY[1] = posy;              //Position der 2.Ecke auf y-Achse (linke Ecke)
        positionX[2] = posx+breite;       //Position der 3.Ecke auf x-Achse (rechte Ecke)
        positionY[2] = posy;              //Position der 3.Ecke auf y-Achse (rechte Ecke) 
        } else {
       if(Ecke==Ausgangsecke[2]){         //Berechnen des Dreiecks von rechter Ecke ausgehend    
        positionX[0] = posx-breite/2;     //Position der 1.Ecke auf x-Achse (obere Ecke)
        positionY[0] = posy-hoehe;        //Position der 1.Ecke auf y-Achse (obere Ecke)
        positionX[1] = posx-breite;       //Position der 2.Ecke auf x-Achse (linke Ecke)
        positionY[1] = posy;              //Position der 2.Ecke auf y-Achse (linke Ecke)
        positionX[2] = posx;              //Position der 3.Ecke auf x-Achse (rechte Ecke)
        positionY[2] = posy;              //Position der 3.Ecke auf y-Achse (rechte Ecke) 
       } else {
           Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Konstruktor\nKlasse: DREIECK \n ungültige Eingabe der Ausgangsecke \n Eingabe:  "+Ecke);
           gibAusgangsecken();
        }}}
        zeichnen();           // zeichen des Dreiecks
    }
    
    public DREIECK(int posx1, int posy1, int posx2, int posy2, int posx3, int posy3, String color, boolean filled){  // Konstruktor um ein Dreieck mit den jeweiligen Koordinaten jeder Ecke zu zeichen
        positionX[0] = posx1; //Position der 1.Ecke auf x-Achse (obere Ecke)
        positionY[0] = posy1; //Position der 1.Ecke auf y-Achse (obere Ecke)
        positionX[1] = posx2; //Position der 2.Ecke auf x-Achse (linke Ecke)
        positionY[1] = posy2; //Position der 2.Ecke auf y-Achse (linke Ecke)
        positionX[2] = posx3; //Position der 3.Ecke auf x-Achse (rechte Ecke)
        positionY[2] = posy3; //Position der 3.Ecke auf y-Achse (rechte Ecke)
        fuellfarbe = color;   // Farbe mit der das Dreieck gefüllt wird
        fuellung = filled;    // true/false Wert ob das Dreieck gefuellt wird oder nicht
        StandartEcke = Ausgangsecke[0]; //Festlegen der Standartecke
        zeichnen();           // zeichen des Dreiecks
    }
    
     public void aenderePosition(int newposx1, int newposy1, int newposx2, int newposy2, int newposx3, int newposy3){  // Methode zum aendern der Position mit den jeweiligen Koordinaten jeder Ecke
        loeschen();
        positionX[0] = newposx1;
        positionY[0] = newposy1;
        positionX[1] = newposx2;
        positionY[1] = newposy2;
        positionX[2] = newposx3;
        positionY[2] = newposy3;
        zeichnen();
    }
    
    public void aenderePosition(int posx, int posy){
        aenderePosition(posx,posy,StandartEcke);
    }
    
    public void aenderePosition(int posx, int posy, String Ecke){ //Methode zum aendern der Position des Standart Dreiecks. x- und y-Koordinaten von der angegebenen Ecke ausgehend  
        if(StandartEcke==Ausgangsecke[0]){    //Position aendern von oberer Ecke ausgehend
        aenderePosition(posx,posy,posx-breite/2,posy+hoehe,posx+breite/2,posy+hoehe);
        }else{
        if(StandartEcke==Ausgangsecke[1]){    //Position aendern von oberer Ecke ausgehend  
        aenderePosition(posx+breite/2,posy-hoehe,posx,posy,posx+breite,posy);
        }else{       
        if(StandartEcke==Ausgangsecke[2]){   ///Position aendern von oberer Ecke ausgehend  
        aenderePosition(posx-breite/2,posy-hoehe,posx-breite,posy,posx,posy);    
        }else{ 
            Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Klasse: DREIECK\nMethode: aenderePosition \n ungültige Eingabe der Ausgangsecke \n Eingabe:  "+Ecke);
            gibAusgangsecken();
            System.exit(0);
        }}}
    }
    
    public void aendereHoehe(int newheight){
        aendereHoehe(newheight,StandartEcke);
    }
    
    public void aendereHoehe(int newheight, String Ecke){ //Methode zum aendern der Position des Standart Dreiecks. x- und y-Koordinaten von der angegebenen Ecke ausgehend
       loeschen();
       hoehe=newheight;
        if(Ecke==Ausgangsecke[0]){   //Berechnen des Dreiecks von oberer Ecke ausgehend
        positionY[0] = positionY[1]-hoehe;    //Position der 1.Ecke auf y-Achse (obere Ecke)
        }else{ 
        if(Ecke==Ausgangsecke[1]){   //Berechnen des Dreiecks von linker Ecke ausgehend    
        positionY[0] = positionY[1]+hoehe;  //Position der 1.Ecke auf y-Achse (obere Ecke)
        }else{ 
        if(Ecke==Ausgangsecke[2]){  //Berechnen des Dreiecks von rechter Ecke ausgehend    
        positionY[0] = positionY[1]+hoehe;  //Position der 1.Ecke auf y-Achse (obere Ecke)
        }else{ 
            Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Klasse: DREIECK\nMethode: aendereHoehe \n ungültige Eingabe der Ausgangsecke \n Eingabe:  "+Ecke);
            gibAusgangsecken();
            System.exit(0);
       }}}
        zeichnen();
    }
    
     public void aendereBreite(int newwidth){
        aendereBreite(newwidth,StandartEcke);
    }
     
    public void aendereBreite(int newwidth, String Ecke){
        loeschen();
        breite=newwidth;       
        if(Ecke==Ausgangsecke[0]){         //Berechnen des Dreiecks von oberer Ecke ausgehend
        positionX[1] = positionX[0]-breite/2;      //Position der 2.Ecke auf x-Achse (linke Ecke)
        positionX[2] = positionX[0]+breite/2;      //Position der 3.Ecke auf x-Achse (rechte Ecke)
        }else{
        if(Ecke==Ausgangsecke[1]){        //Berechnen des Dreiecks von linker Ecke ausgehend    
        positionX[0] = positionX[1]+breite/2;     //Position der 1.Ecke auf x-Achse (obere Ecke)
        positionX[2] = positionX[1]+breite;       //Position der 3.Ecke auf x-Achse (rechte Ecke)
        } else {
       if(Ecke==Ausgangsecke[2]){         //Berechnen des Dreiecks von rechter Ecke ausgehend    
        positionX[0] = positionX[2]-breite/2;     //Position der 1.Ecke auf x-Achse (obere Ecke)
        positionX[1] = positionX[2]-breite;       //Position der 2.Ecke auf x-Achse (linke Ecke)

       } else {
           Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Klasse: DREIECK\nMethode: aendereBreite \n ungültige Eingabe der Ausgangsecke \n Eingabe:  "+Ecke);
           gibAusgangsecken();
           System.exit(0);
        }}}        
        zeichnen();
    }
    
    public void bewegex(int newposx, String direction){ // alle Ecken werden um den gleichen Betrag verschoben
        bewegex(newposx,newposx,newposx,direction);
    }
    
    public void bewegex(int newposx1, int newposx2, int newposx3, String direction){  // bewegen einzelner Ecken auf der x-Achse um angegebenen Werte
        loeschen();                                                                   // entscheiden über Bewegunsrichtung auf x-Achse mit "direction"        
        switch(direction){
        case "rechts":  // Bewegen nach rechts auf x-Achse um den Wert newposx
        positionX[0] += newposx1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionX[1] += newposx2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionX[2] += newposx3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        case "links":  // Bewegen nach links auf x-Achse um den Wert newposx
        positionX[0] -= newposx1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionX[1] -= newposx2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionX[2] -= newposx3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        default:
        Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Ungültige Richtungsangabe bei Bewegung auf der x-Achse \nKlasse: DREIECK\nMethode: bewegex \nEingabe:  "+direction);// Fehlermeldung bei ungültiger Eingabe der "direction"
        System.exit(0);
        }        
        zeichnen();
    }
    
    public void bewegey(int newposy, String direction){ // alle Ecken werden um den gleichen Betrag verschoben
        bewegey(newposy,newposy,newposy,direction);
    }
    
    public void bewegey(int newposy1, int newposy2, int newposy3, String direction){  // bewegen einzelner Ecken auf der y-Achse um angegebenen Werte
        loeschen();                                                                  // entscheiden über Bewegunsrichtung auf y-Achse mit "direction"      
        switch(direction){
        case "hoch":  // Bewegen nach oben auf y-Achse um den Wert newposy
        positionY[0] -= newposy1;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionY[1] -= newposy2;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionY[2] -= newposy3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        case "runter":  // Bewegen nach unten auf y-Achse um den Wert newposy
        positionY[0] += newposy3;  // Formel um neue  Position der 1.Ecke zu errechenen
        positionY[1] += newposy3;  // Formel um neue  Position der 2.Ecke zu errechenen
        positionY[2] += newposy3;  // Formel um neue  Position der 3.Ecke zu errechenen
        break;
        
        default:
        Dialogfenster.gibFehlermeldung("Ungültige Eingabe","Ungültige Richtungsangabe bei Bewegung auf der y-Achse \nKlasse: DREIECK\nMethode: bewegey \nEingabe:  "+direction);;  // Fehlermeldung bei ungültiger Eingabe der "direction"
        System.exit(0);
        }  
        zeichnen();
    }
    
       public void aendereFarbe(String newcolor){  // aendern der Füllfarbe
        loeschen();
        fuellfarbe = newcolor;
        zeichnen();
    }
    
    public void Fuellung(boolean filled){  // aendern ob mit Farbe geüllt oder nicht
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
    public void gibAusgangsecken(){  // Methode zur Ausgabe der aktuellen Ausgangsecken
        Dialogfenster.gibInformation("Ausgangsecken DREIECK","Mögliche Ausgangsecken:  "+Ausgangsecke[0]+", "+Ausgangsecke[1]+", "+Ausgangsecke[2]);
    }
    
    public void gibStandartEcke(){
        Dialogfenster.gibInformation("Standart-Ecke DREIECK","Standart-Ecke:  "+StandartEcke);
    }
    
    public void zeichnen(){  // Methode zum zeichnen des Dreiecks
        if (fuellung==true){  // prüfen ob es mit oder ohne Füllung gezeichnet werden soll
            ZEICHENFENSTER.gibFenster().fuelleDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2],fuellfarbe);       // zeichnen mit Füllung
        }else{
            ZEICHENFENSTER.gibFenster().zeichneDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2]);                 // Zeichen ohne Füllung
        }
   }
   
   public void loeschen(){ // Methode zum loeschen des Dreiecks
       if (fuellung==true){  // prüfen ob es mit oder ohne Füllung gezeichnet werden soll
            ZEICHENFENSTER.gibFenster().fuelleDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2],fuellfarbe); 
        }else{
            ZEICHENFENSTER.gibFenster().loescheDreieck(positionX[0],positionY[0]-1,positionX[1]-1,positionY[1]+1,positionX[2]+1,positionY[2]+1);  
       }  
   }
   
   }
