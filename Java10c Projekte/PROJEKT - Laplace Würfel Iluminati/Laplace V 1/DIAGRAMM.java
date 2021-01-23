/**
 * Write a description of class test here.
 * 
 * @author (Patric, Dominc Zimonich & Daniel Landau) 
 * @version (24.01.2015)
 */

public class DIAGRAMM
{
    RECHTECK  Saeulen[] = new RECHTECK[6];
    RECHTECK  Wuerfel[] = new RECHTECK[6];
    
    KREIS AugenWuerfel[] = new KREIS[6];
    
    public DIAGRAMM()
    {
        Saeulen[0] = new RECHTECK (500, 130, 90, 0, "hellgrau", true);
        Saeulen[1] = new RECHTECK (600, 130, 90, 0, "hellgrau", true);
        Saeulen[2] = new RECHTECK (700, 130, 90, 0, "hellgrau", true);
        Saeulen[3] = new RECHTECK (800, 130, 90, 0, "hellgrau", true);
        Saeulen[4] = new RECHTECK (900, 130, 90, 0, "hellgrau", true);
        Saeulen[5] = new RECHTECK (1000, 130, 90, 0, "hellgrau", true);
        
        Wuerfel[0] = new RECHTECK (510, 55, 70, 70, "grau", true);
        Wuerfel[1] = new RECHTECK (610, 55, 70, 70, "grau", true);
        Wuerfel[2] = new RECHTECK (710, 55, 70, 70, "grau", true);
        Wuerfel[3] = new RECHTECK (810, 55, 70, 70, "grau", true);
        Wuerfel[4] = new RECHTECK (910, 55, 70, 70, "grau", true);
        Wuerfel[5] = new RECHTECK (1010,55, 70, 70, "grau", true); 
        
        AugenWuerfel[0] = new KREIS  (545, 90, 10, "schwarz", true);       
        AugenWuerfel[1] = new KREIS  (665, 110, 10, "schwarz", true);
                       new KREIS  (625, 70, 10, "schwarz", true);
        AugenWuerfel[2] = new KREIS  (765, 110, 10, "schwarz", true);
                       new KREIS  (745, 90, 10, "schwarz", true);
                       new KREIS  (725, 70, 10, "schwarz", true);
        AugenWuerfel[3] = new KREIS  (825, 110, 10, "schwarz", true);
                       new KREIS  (865, 70, 10, "schwarz", true);
                       new KREIS  (825, 70, 10, "schwarz", true);
                       new KREIS  (865, 110, 10, "schwarz", true);   
        AugenWuerfel[4] = new KREIS  (925, 110, 10, "schwarz", true);
                       new KREIS  (965, 70, 10, "schwarz", true);
                       new KREIS  (925, 70, 10, "schwarz", true);
                       new KREIS  (965, 110, 10, "schwarz", true);
                       new KREIS  (945, 90, 10, "schwarz", true);
        AugenWuerfel[5] = new KREIS  (1025, 110, 10, "schwarz", true);
                       new KREIS  (1065, 70, 10, "schwarz", true);
                       new KREIS  (1025, 70, 10, "schwarz", true);
                       new KREIS  (1065, 110, 10, "schwarz", true);
                       new KREIS  (1025, 90, 10, "schwarz", true);
                       new KREIS  (1065, 90, 10, "schwarz", true);
                       
        
    }
    
    public DIAGRAMM(int posx, int posy, int breite, String Saeulenfarbe, String Wuerfelfarbe, String Augenfarbe)
    {
        Saeulen[0] = new RECHTECK (posx, posy, breite, 0, Saeulenfarbe, true);
        Saeulen[1] = new RECHTECK (posx+(breite+10), posy, breite, 0, Saeulenfarbe, true);
        Saeulen[2] = new RECHTECK (posx+2*(breite+10), posy, breite, 0, Saeulenfarbe, true);
        Saeulen[3] = new RECHTECK (posx+3*(breite+10), posy, breite, 0, Saeulenfarbe, true);
        Saeulen[4] = new RECHTECK (posx+4*(breite+10), posy, breite, 0, Saeulenfarbe, true);
        Saeulen[5] = new RECHTECK (posx+5*(breite+10), posy, breite, 0, Saeulenfarbe, true);
        
        int Ws = breite-20; /**Ws = Wuerfelseite */
        int AbWs =breite/10;       /**Abstand Wuerfel zu Diagramm */
        Wuerfel[0] = new RECHTECK (posx+10, posy-Ws-AbWs, Ws, Ws, Wuerfelfarbe, true);
        Wuerfel[1] = new RECHTECK (posx+(breite+10)+10, posy-Ws-AbWs, Ws, Ws,  Wuerfelfarbe, true);
        Wuerfel[2] = new RECHTECK (posx+2*(breite+10)+10, posy-Ws-AbWs, Ws, Ws, Wuerfelfarbe, true);
        Wuerfel[3] = new RECHTECK (posx+3*(breite+10)+10, posy-Ws-AbWs, Ws, Ws, Wuerfelfarbe, true);
        Wuerfel[4] = new RECHTECK (posx+4*(breite+10)+10, posy-Ws-AbWs, Ws, Ws, Wuerfelfarbe, true);
        Wuerfel[5] = new RECHTECK (posx+5*(breite+10)+10, posy-Ws-AbWs, Ws, Ws, Wuerfelfarbe, true); 
        
        int rand = Ws/6;
        int rP = Ws/8; /**rp = radiusPunkt */
        AugenWuerfel[0] = new KREIS  (posx+10+3*rand, posy-AbWs-3*rand, rP, Augenfarbe, true);
        
        AugenWuerfel[1] = new KREIS  (posx+(breite+10)+10+5*rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+(breite+10)+10+rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          
        AugenWuerfel[2] = new KREIS  (posx+2*(breite+10)+10+5*rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+2*(breite+10)+10+3*rand, posy-AbWs-3*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+2*(breite+10)+10+rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          
        AugenWuerfel[3] = new KREIS  (posx+3*(breite+10)+10+rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+3*(breite+10)+10+5*rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+3*(breite+10)+10+rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+3*(breite+10)+10+5*rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          
        AugenWuerfel[4] = new KREIS  (posx+4*(breite+10)+10+rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+4*(breite+10)+10+5*rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+4*(breite+10)+10+rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+4*(breite+10)+10+5*rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+4*(breite+10)+10+3*rand, posy-AbWs-3*rand, rP, Augenfarbe, true);

        AugenWuerfel[5] = new KREIS  (posx+5*(breite+10)+10+rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+5*(breite+10)+10+5*rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+5*(breite+10)+10+rand, posy-AbWs-5*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+5*(breite+10)+10+5*rand, posy-AbWs-rand, rP, Augenfarbe, true);
                          new KREIS  (posx+5*(breite+10)+10+rand, posy-AbWs-3*rand, rP, Augenfarbe, true);
                          new KREIS  (posx+5*(breite+10)+10+5*rand, posy-AbWs-3*rand, rP, Augenfarbe, true);
                       
        
    }
}   


