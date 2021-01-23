/**
 * Write a description of class DREIECK here.
 * 
 * @author (Daniel Landau) 
 * @version (04.05.2015v1)
 */

public class KREIS {

    private int mittelpunktX;

    private int mittelpunktY;

    private int radius;

    private String fuellfarbe;
    
    private boolean fuellung;           


    public KREIS() {
        this(30, 60, 20, "blau", false);
    }
    
    public KREIS(int posx, int posy, int r,  String color, boolean filled)
    {
        mittelpunktX = posx;
        mittelpunktY = posy;
        radius = r;
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
        mittelpunktX = newpositionx;
        mittelpunktY = newpositiony;
        zeichnen();
    }
    
    public void aendereRadius(int newradius){
        loeschen();
        radius = newradius;
        zeichnen();
    }
    
    public void Fuellung(boolean filled){
        loeschen();
        fuellung = filled;
        zeichnen();
    }
    
     public void bewegex(int newposx,String direction){
        loeschen();
        
        switch(direction){
        case "rechts":
        mittelpunktX = mittelpunktX + newposx;
        break;
        
        case "links":
        mittelpunktX = mittelpunktX - newposx;
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
        mittelpunktY = mittelpunktY - newposy;
        break;
        
        case "runter":
        mittelpunktY = mittelpunktY + newposy;
        break;
        
        default:
        System.out.print("Ungültige Eingabe");
        break;
        }
        
		zeichnen();
    }
    
    public void zeichnen() {
        if(fuellung==true){
        ZEICHENFENSTER.gibFenster().zeichneKreis(mittelpunktX, mittelpunktY, radius);
        ZEICHENFENSTER.gibFenster().fuelleKreis(mittelpunktX, mittelpunktY, radius, fuellfarbe); 
        }else{
        ZEICHENFENSTER.gibFenster().zeichneKreis(mittelpunktX, mittelpunktY, radius);
        }
     }
     
     public void loeschen() {
        ZEICHENFENSTER.gibFenster().loescheKreis(mittelpunktX, mittelpunktY, radius);
     }


}
