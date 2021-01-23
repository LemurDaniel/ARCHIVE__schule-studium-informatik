
public class Hauptblock
{
    
    RECHTECK [] Hauptblock = new RECHTECK [150]; 
    private int breite = 6;
    private int x=30;
    private int y=330;
    private int t=10;
    Hauptblock Block;
        
    public Hauptblock()
    {
        Hauptblock[0]= new RECHTECK(3*x,y,x,x-t,"braun"); // Erdblock 
        Hauptblock[1]= new RECHTECK(3*x,y-10,x,t,"gruen"); // Grasblock     
    }
    
}
