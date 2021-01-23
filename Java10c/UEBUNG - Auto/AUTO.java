
/**
 * Write a description of class Auto here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v1)
 */
public class AUTO
{
    KREIS     Reifen;
    RECHTECK  Karosserie;
    RECHTECK  Dach;
    DREIECK   Frontscheibe;
    
    public AUTO()
    {
        Karosserie   = new RECHTECK (200, 250, 240, 40, "dunkelrot", true);
        Dach         = new RECHTECK (240, 210, 120, 40, "dunkelrot", true);
        Frontscheibe = new DREIECK  (360, 210, 80, 40, "dunkelrot", true);
        Reifen       = new KREIS    (260, 290, 20, "schwarz", true);
                       new KREIS    (380, 290, 20, "schwarz", true);
    }
}

