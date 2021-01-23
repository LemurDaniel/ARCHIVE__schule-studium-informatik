public class Hauptmap1
{
    
    RECHTECK [] Hauptblock = new RECHTECK [150]; 
    private int breite = 6;
    private int x=30;
    private int y=330;
    private int t=10;
    Hauptblock Block;
    Hauptmap1 Map1;
   
    
    public Hauptmap1()
    {
        
        Hauptblock[0]= new RECHTECK(x-x,y,60*x,x-t,"braun"); // 1 Erdblock 
        Hauptblock[1]= new RECHTECK(x-x,y-t,60*x,t,"gruen"); // 1 Grasblock               
        
    }
}