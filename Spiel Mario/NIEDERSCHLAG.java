public class NIEDERSCHLAG
{
    protected double x, y, vx, vy;

    public NIEDERSCHLAG()
    {
        x = 10;
        y = 315;
        vx = 12;
        vy = 1;
    }

    public void zeichne()
    {
        Hauptmap1 Map1  = new Hauptmap1();
    }
    
    public void bewege(double zeit)
    {
        x = x - vx * zeit;
        y = y - vy * zeit;
    }
    
}
