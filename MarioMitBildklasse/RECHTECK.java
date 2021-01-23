/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (04.05.2015v1)
 */

public class RECHTECK {

	private int positionX;

	private int positionY;

	private int breite;
	
	private int hoehe;
	
	private boolean fuellung;
	
	private String fuellfarbe;
 
    public RECHTECK() {
        this(60, 50, 60, 30, "rot", false);
    }
    
    public RECHTECK(int posx, int posy, int width, int height, String color, boolean filled)
    {
        positionX = posx;
		positionY = posy;
		breite = width;
		hoehe = height;
		fuellfarbe = color;
		fuellung = filled;
		zeichnen();
    }
    
    public void aendereFarbe(String newcolor){
        loeschen();
        fuellfarbe = newcolor;
        zeichnen();
    }
    
    public void aenderePosition(int newpositionx, int newpositiony){
        loeschen();
        positionX = newpositionx;
        positionY = newpositiony;
        zeichnen();
    }
    
    public void aendereGroesse(int newheight, int newwidth){
        loeschen();
        hoehe = newheight;
        breite = newwidth;
        zeichnen();
    }
    
    public void aendereFuellung(boolean filled){
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
    public void bewegex(int newposx,String direction){
        loeschen();
        
        switch(direction){
        case "rechts":
        positionX = positionX + newposx;
        break;
        
        case "links":
        positionX = positionX - newposx;
        break;
        
        default:
        System.out.print("Ungültige Eingabe");
        break;
        }
        
		zeichnen();
    }
    
    public void bewegey(int newposy, String direction){
        loeschen();
        
        switch(direction){
        case "hoch":
        positionY = positionY - newposy;
        break;
        
        case "runter":
        positionY = positionY + newposy;
        break;
        
        default:
        System.out.print("Ungültige Eingabe");
        break;
        }
        
		zeichnen();
    }
    
    public void zeichnen(){ 
        if (fuellung==true){
        ZEICHENFENSTER.gibFenster().zeichneRechteck(positionX, positionY, breite, hoehe);  
        ZEICHENFENSTER.gibFenster().fuelleRechteck(positionX, positionY, breite, hoehe,fuellfarbe);
        }else{
        ZEICHENFENSTER.gibFenster().zeichneRechteck(positionX, positionY, breite, hoehe);
        }
     }
     
     public void loeschen(){
         ZEICHENFENSTER.gibFenster().loescheRechteck(positionX, positionY, breite+1, hoehe+1); 
     }

}
