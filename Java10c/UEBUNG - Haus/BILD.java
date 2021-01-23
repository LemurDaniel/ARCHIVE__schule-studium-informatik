
/**
 * Write a description of class HAUS here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v2)
 */
 public class BILD
{
    RECHTECK  Garage;
    RECHTECK  Garagentor;
    RECHTECK  Hausfront;
    RECHTECK  Tür;
    RECHTECK  Fenster;
    RECHTECK  Schornstein;
    DREIECK   Dach;
    KREIS     Dachfenster;
    KREIS     Sonne;
   
    public BILD()
    {
        Garage         = new RECHTECK (260, 340, 160, 80, "grau", true);
        Garagentor     = new RECHTECK (280, 360, 120,60, "hellgrau", true);
        Hausfront      = new RECHTECK (100, 300, 160, 120, "grau", true);
        Tür            = new RECHTECK (165, 380, 30, 40, "braun", true);
        Fenster        = new RECHTECK (120, 340, 40, 20, "blau", true);
                         new RECHTECK (200, 340, 40, 20, "blau", true);
        Schornstein    = new RECHTECK (140, 220, 20, 40, "schwarz", true);
        Dach           = new DREIECK  (180, 220, 160, 80, "dunkelrot", true);
        Dachfenster    = new KREIS    (180, 270, 10, "blau", true);
        Sonne          = new KREIS    (440, 140, 60, "gelb", true);
    }
    
    public BILD(int posx, int posy, int breite)
    {   
        int br=breite/16;
        int hoehe=br*6;
        int h=hoehe/6;
        Garage         = new RECHTECK (posx+breite/2, posy+2*h, breite/2, 4*h, "grau", true);
        Garagentor     = new RECHTECK (posx+breite/2+h, posy+3*h, breite/2-2*br, 3*h, "hellgrau", true);
        Hausfront      = new RECHTECK (posx, posy, breite/2, hoehe, "grau", true);
        Tür            = new RECHTECK (posx+3*br, posy+4*h, 2*br, 2*h, "braun", true);
        Fenster        = new RECHTECK (posx+br, posy+2*h, 2*br, h, "blau", true);
                         new RECHTECK (posx+5*br, posy+2*h, 2*br, h, "blau", true);
        Schornstein    = new RECHTECK (posx+2*br, posy-4*h, br, 2*h, "schwarz", true);
        Dach           = new DREIECK  (posx+4*br, posy-4*h, breite/2, 4*h, "dunkelrot", true);
        Dachfenster    = new KREIS    (posx+4*br, posy-2*h, h/2, "blau", true);
        Sonne          = new KREIS    (posx+17*br, posy-8*h, h*3, "gelb", true);
    }

}
