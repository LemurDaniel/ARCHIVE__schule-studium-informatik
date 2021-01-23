
/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (05.05.2015v1)
 */

public class DREIECK {

	private int [] positionX = new int[3];

	private int [] positionY = new int[3];
	
	private String fuellfarbe;
	
	private boolean fuellung;


    public DREIECK(){
        this(100, 100, 80, 80, 120, 120, "rot", false);
    }
    
    public DREIECK(int posx1, int posy1, int posx2, int posy2, int posx3, int posy3, String color, boolean filled){
        positionX[0] = posx1;
        positionY[0] = posy1;
        positionX[1] = posx2;
        positionY[1] = posy2;
        positionX[2] = posx3;
        positionY[2] = posy3;
        fuellfarbe = color;
        fuellung = filled;
        zeichnen();   
    }
    
    public void aenderePosition(int newposx1, int newposy1, int newposx2, int newposy2, int newposx3, int newposy3){
        loeschen();
        positionX[0] = newposx1;
        positionY[0] = newposy1;
        positionX[1] = newposx2;
        positionY[1] = newposy2;
        positionX[2] = newposx3;
        positionY[2] = newposy3;
        zeichnen();
    }
    
    public void aendereFarbe(String newcolor){
        loeschen();
        fuellfarbe = newcolor;
        zeichnen();
    }
    
    public void Fuellung(boolean filled){
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
    public void bewegex(int newposx1, int newposx2, int newposx3, String direction){
        loeschen();
        
        switch(direction){
        case "rechts":
        positionX[0] = positionX[0] + newposx1;
        positionX[1] = positionX[1] + newposx2;
        positionX[2] = positionX[2] + newposx3;
        break;
        
        case "links":
        positionX[0] = positionX[0] - newposx1;
        positionX[1] = positionX[1] - newposx2;
        positionX[2] = positionX[2] - newposx3;
        break;
        
        default:
        System.out.print("Ungültige Eingabe");
        break;
        }
        
		zeichnen();
    }
    
    public void bewegey(int newposy1, int newposy2, int newposy3, String direction){
        loeschen();
        
        switch(direction){
        case "hoch":
        positionY[0] = positionY[0] - newposy1;
        positionY[1] = positionY[1] - newposy2;
        positionY[2] = positionY[2] - newposy3;
        break;
        
        case "runter":
        positionY[0] = positionY[0] + newposy1;
        positionY[1] = positionY[1] + newposy2;
        positionY[2] = positionY[2] + newposy3;
        break;
        
        default:
        System.out.print("Ungültige Eingabe");
        break;
        }
        
		zeichnen();
    }
    
    public void zeichnen(){
        if (fuellung==true){
            ZEICHENFENSTER.gibFenster().zeichneDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2]);
            ZEICHENFENSTER.gibFenster().fuelleDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2],fuellfarbe);
        }else{
            ZEICHENFENSTER.gibFenster().zeichneDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2]);
        }
   }
   
   public void loeschen(){
       ZEICHENFENSTER.gibFenster().loescheDreieck(positionX[0],positionY[0],positionX[1],positionY[1],positionX[2],positionY[2]);
    }
    
    public void test(){
        ZEICHENFENSTER.gibFenster().zeichneDreieck(100, 100, 80, 80, 120, 120);
    }
}
