
public class Spiel
{
    Hauptfigur Figur;
    //Hauptfigur2 Figur2;
    String Zustand;
    int breite; 
    int x;
    int y;
    
    Spiel()
    {
        Figur = new Hauptfigur();
        //Figur2 = new Hauptfigur2();
        
    
    
    }
    public void  umschalten(String Zustand)
    {
    
    switch (Zustand)
    {
        case("normal"):
        breite = 6;
        x=2;
        y=345;
        break;
        
        case("rechts"):
        breite = 6;
        x=40;
        y=345;
        break;
        
        case("oben"):
        breite = 6;
        x=2;
        y=341;
        break;
        
        case("oben-rechts"):
        int breite = 6;
        int x=2;
        int y=341;
        break;
        default:
    }
}
        
    
    
 }
