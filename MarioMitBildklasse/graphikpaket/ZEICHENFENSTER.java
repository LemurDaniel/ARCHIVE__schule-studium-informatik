package graphikpaket;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Class ZEICHENFLAECHE - Eine Klasse, die einfache grafische Zeichnungen 
 * in einem Programmfenster ermoeglicht.
 * 
 * @author Michael Kolling (mik)
 * @author Bruce Quig
 * @author Christian Heidrich
 * @author Kurt Horlebein
 *
 * @version V6_20130330
 */

public class ZEICHENFENSTER extends FENSTER
{
    private Graphics2D graphic; // Klasse zur bequemen Handhabung von Graphikelementen wie Kreis etc.
    private ZEICHENFLAECHE ZeichenFlaeche;
    private int ZeichenFlaechenBreite, ZeichenFlaechenHoehe;
    private Image GemaltesAufDerZeichenFlaeche; // ein Bild der Klasse Image, das den Inhalt der Zeichenflaeche darstellt
    /**
     * Erzeugt eine Zeichenfenster mit Standardmassen Breite * Hoehe und Hintergrundfarbe weiss 
     * @param titel  Titel des Fensters     
     */
    
    /**
     * Erzeugt eine Zeichenflaeche.
     * @param neuesFenster - ZEICHENFENSTER -> braucht man, da sonst Fehlermeldungen bei graphic-Aufrufen unvermeidlich sind 
     * @param neueBreite  Breite der Flaeche - int
     * @param neueHoehe  Hoehe der Flaeche - int
     * @param neueHintergrundColor  Hintergrundfarbe der Zeichenflaeche - Color (Javainterne FarbenKlasse
     */
    ZEICHENFENSTER(){this(Titel);}
    ZEICHENFENSTER(String neuerTitel){this(neuerTitel,Breite,Hoehe);}
    ZEICHENFENSTER(String neuerTitel,int neueBreite, int neueHoehe){this(neuerTitel,neueBreite,neueHoehe,HintergrundFarbe);}
    ZEICHENFENSTER(String neuerTitel,int neueBreite, int neueHoehe, String neueHintergrundFarbe)
    {
        this(neuerTitel,neueBreite,neueHoehe,neueHintergrundFarbe,true);
    }
    ZEICHENFENSTER(String neuerTitel,int neueBreite, int neueHoehe, String neueHintergrundFarbe,boolean standardfenster)
    {
        super(neuerTitel,neueBreite,neueHoehe, neueHintergrundFarbe,standardfenster);
    }
    public FENSTER gibFenster(){return this;}
    public String holeTitel(String neuerTitel){if (neuerTitel==null){neuerTitel="StandardZeichenFenster";};return neuerTitel;}
    public void ergaenzeFensterElemente()
    {
        ZeichenFlaeche=new ZEICHENFLAECHE();
        ZeichenFlaechenBreite=Breite;
        ZeichenFlaechenHoehe=Hoehe;
        setzeZeichenFlaecheGroesse(ZeichenFlaechenBreite,ZeichenFlaechenHoehe);
        getContentPane().add(ZeichenFlaeche,BorderLayout.CENTER); // Die Zeichenflaeche wird der Fensterinnenflaeche
                                                                  // (-> ContentPane) in die Fenstrgrenzen zentriert hinzugefuegt
        setVisible(true);
        fuelleZeichenFlaeche(HintergrundFarbe);
    }
    public void setzeZeichenFlaecheGroesse(int neueBreite, int neueHoehe)
    {
        ZeichenFlaechenBreite = neueBreite;
        ZeichenFlaechenHoehe = neueHoehe;
        ZeichenFlaeche.setPreferredSize(new Dimension(neueBreite, neueHoehe));
        GemaltesAufDerZeichenFlaeche = createImage(neueBreite, neueHoehe);
    }
    public void auffrischenZeichenFlaeche()
    {
        setVisible(true);
        graphic = (Graphics2D)GemaltesAufDerZeichenFlaeche.getGraphics();
    }
    /**
     * Aendert die Abmessungen der Zeichenflaeche.
     * @param  neueBreite    neue Breite 
     * @param  neueHoehe     neue Hoehe 
     */
    public void anpassenZeichenFlaecheGroesse(int neueBreite, int neueHoehe)
    {
        Image altesBild = GemaltesAufDerZeichenFlaeche;
        setzeZeichenFlaecheGroesse(neueBreite, neueHoehe);
        auffrischenZeichenFlaeche();
        graphic.drawImage(altesBild, 0, 0, null);
    }
    public void setzeZeichenFlaecheBreite(int neueBreite)
    {
        ZeichenFlaechenBreite=neueBreite;
        setzeZeichenFlaecheGroesse(Breite, ZeichenFlaechenHoehe);
    }
    public void setzeZeichenFlaecheHoehe(int neueHoehe)
    {
        ZeichenFlaechenHoehe=neueHoehe;
        setzeZeichenFlaecheGroesse(ZeichenFlaechenBreite,Hoehe);
    }
    /**
     * Gibt die Abmessungen des Zeichenfensters zurueck.
     * @return     die aktuellen Abmessungen des Zeichenfensters
     */

    public Dimension gibZeichenFlaecheGroesse()
    {
        return ZeichenFlaeche.getSize();
    }
    public int gibZeichenFlaecheBreite()
    {
        return ZeichenFlaeche.getSize().width;
    }
    public int gibZeichenFlaecheHoehe()
    {
        return ZeichenFlaeche.getSize().height;
    }
    /**
     * Macht die Zeichenflaeche sichtbar bzw. setzt es in den Vordergrund
     */
    public void zeige()
    {
        if(graphic == null) {
           fuelleZeichenFlaeche();
        }
        else
        {
            repaint();
        }
    }
    public void fuelleZeichenFlaeche()
    {
        fuelleZeichenFlaeche(HintergrundColor);
    }
    public void fuelleZeichenFlaeche(String neueFarbe)
    {
        fuelleZeichenFlaeche(FarbKonverter.farbeZuColor(neueFarbe));
    }
    public void fuelleZeichenFlaeche(Color neueColor)
    {
        HintergrundColor=neueColor;
        Dimension groesse = new Dimension(ZeichenFlaechenBreite, ZeichenFlaechenHoehe); // Die Abmessungen der Zeichenflaeche werden festgelegt
        ZeichenFlaeche.setPreferredSize(groesse);
        GemaltesAufDerZeichenFlaeche = createImage(ZeichenFlaechenBreite, ZeichenFlaechenHoehe); // Ein Bild dieser
                                                                   // Groesse wird erzeugt
        setVisible(true);                                                                         
        graphic = (Graphics2D)GemaltesAufDerZeichenFlaeche.getGraphics(); // Umwandlung in eine komplexere 2DGraphik
        graphic.setColor(HintergrundColor); // die Hintergrundfarbe wird als Zeichenfarbe gesetzt
        graphic.fillRect(0, 0, groesse.width, groesse.height); // und ein gefuelltes Rechteck mit den Abmessungen 
                                                         // wird gezeichnet
        graphic.setColor(Color.black); // die Zeichenfarbe wird auf schwarz gesetzt
        repaint();
    }
    public void erweitereZeichenFlaeche(int neueBreite, int neueHoehe)
    {
        if (Breite<neueBreite || Hoehe < neueHoehe)
        {
            setVisible(true);
            if(Breite<neueBreite){Breite=neueBreite;}
            if(Hoehe<neueHoehe){Hoehe=neueHoehe;}
            Image alteZeichnung = GemaltesAufDerZeichenFlaeche;
            fuelleZeichenFlaeche();
            graphic.drawImage(alteZeichnung, 0, 0, null);
        } 
    }
    
    /**
     * Gibt Information ueber die Sichtbarkeit.
     * @return  true falls das Fenster sichtbar ist.
     */
    public boolean istSichtbar()
    {
        return isVisible();
    }
    public void auffrischen()
    {
        repaint();
    }
    public void macheSichtbar(boolean ja)
    {
        if (ja=true) { setVisible(true); } else setVisible(false);
    }
    /**
     * Zeichnet einen Elipsenbogen (Siehe Graphics.drawArc)
     * @param x x-Koordinate des Elipsenmittelpunkts
     * @param y y-Koordinate des Elipsenmittelpunkts
     * @param halbachseX Halbachse der Elipse in x-Richtung
     * @param halbachseY Halbachse der Elipse in y-Richtung
     * @param startWinkel Polarwinkel, an dem der Bogen anfaengt
     * @param winkel Polarwinkel, welchen der Bogen durchlauuft
     */
    public void zeichneBogen(int x, int y, int halbachseX, int halbachseY, int startWinkel, int winkel)
    {
        graphic.drawArc(x-halbachseX,y-halbachseY,2*halbachseX,2*halbachseY,startWinkel,winkel);
        repaint();
    }
    /**
     * Zeichnet einen Kreis (Siehe Graphics.drawOval)
     * @param x x-Koordinate des Mittelpunkts
     * @param y y-Koordinate des Mittelpunkts
     * @param radius Kreisradius
     */
    public void zeichneKreis(int x, int y, int radius)
    {
        graphic.drawOval(x-radius,y-radius,2*radius,2*radius);
        repaint();
    }

    /**
     * Fuellt das Innere eines Kreises mit der angegebenen Farbe. 
     * @param x x-Koordinate des Mittelpunkts
     * @param y y-Koordinate des Mittelpunkts
     * @param radius Kreisradius
     * @param  farbe  Fuellfarbe fuer den Kreis, erlaubt sind "weiss" "schwarz" "rot"
     * "gruen" "blau" "gelb" "magenta" "cyan" "grau"
     */
    public void fuelleKreis(int x, int y, int radius, String farbe)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbe));
        graphic.fillOval(x-radius,y-radius,2*radius,2*radius);
        repaint();
        graphic.setColor(original);
    }
    
    /**
     * Fuellt das Innere eines Kreises mit der angegebenen Farbe. 
     * @param x x-Koordinate des Mittelpunkts
     * @param y y-Koordinate des Mittelpunkts
     * @param radius Kreisradius
     * @param  farbnr  Fuellfarbnummer fuer den Kreis (0 bis 8)
     */
    public void fuelleKreis(int x, int y, int radius, int farbnr)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbnr));
        graphic.fillOval(x-radius,y-radius,2*radius,2*radius);
        repaint();
        graphic.setColor(original);
    }
    
    /**
     * Loescht das Innere eines Kreises 
     * @param x x-Koordinate des Mittelpunkts
     * @param y y-Koordinate des Mittelpunkts
     * @param radius Kreisradius
     */
    public void loescheKreis(int x, int y, int radius)
    {
        Ellipse2D.Double circle = new Ellipse2D.Double(x-radius, y-radius, 2*radius, 2*radius);
        loesche(circle);
    }

    /**
     * Zeichnet den Umriss eines Shape-Objekts.
     * @param  shape  das Shape-Object, welches gezeichnet werden soll
     */
    public void zeichne(Shape shape)
    {
        graphic.draw(shape);
        repaint();
    }
 
    /**
     * Fuellt das Innere eines Shape-Objekts mit der angegebenen Farbe. 
     * @param  shape  das Shape-Objekt, welches gefuellt werden soll 
     * @param  farbe  Fuellfarbe fuer das Shape-Objekt, erlaubt sind "weiss" "schwarz" "rot"
     * "gruen" "blau" "gelb" "magenta" "cyan" "grau"
     */
    public void fuelle(Shape shape, String farbe)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbe));
        graphic.fill(shape);
        repaint();
        graphic.setColor(original);
    }

    /**
     * Fuellt das Innere eines Shape-Objekts mit der angegebenen Farbe. 
     * @param  shape  das Shape-Objekt, welches gefuellt werden soll 
     * @param  farbnr  Fuellfarbnummer fuer das Shape-Objekt (0 bis 8)
     */
    public void fuelle(Shape shape, int farbnr)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbnr));
        graphic.fill(shape);
        repaint();
        graphic.setColor(original);
    }

    /**
     * Loescht das Innere eines Shape-Objekts.
     * @param  shape  das Shape-Object, welches geloescht werden soll 
     */
    public void loesche(Shape shape)
    {
        Color original = graphic.getColor();
        graphic.setColor(HintergrundColor);
        graphic.fill(shape);              // erase by filling background color
        graphic.setColor(original);
        repaint();
    }

    /**
     * Zeichnet den Rand des Rechtecks mit der aktuellen Farbe.
     * @param xPos,yPos Koordinaten der linken oberen Ecke 
     * @param breite, hoehe Breite und Hoehe des Rechtecks
     */
    public void zeichneRechteck(int xPos, int yPos, int breite, int hoehe)
    {
        graphic.drawRect(xPos, yPos, breite, hoehe);
        repaint();
       // fill(new Rectangle(xPos, yPos, breite, hoehe));
    }
    public void zeichneGefuelltesRechteck(int xPos, int yPos, int breite, int hoehe)
    {
        graphic.drawRect(xPos, yPos, breite, hoehe);
        repaint();
        fuelleRechteck(xPos, yPos, breite, hoehe, FarbKonverter.colorZuFarbe(graphic.getColor()));
    }
    /**
     * Fuellt das Innere des Rechtecks mit der angegebenen Farbe.
     * @param xPos,yPos Koordinaten der linken oberen Ecke 
     * @param breite, hoehe Breite und Hoehe des Rechtecks
     * @param  farbe  Fuellfarbe fuer das Rechteck, erlaubt sind "weiss" "schwarz" "rot"
     * "gruen" "blau" "gelb" "magenta" "cyan" "grau"
     */
    public void fuelleRechteck(int xPos, int yPos, int breite, int hoehe, String farbe)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbe));
        graphic.fillRect(xPos, yPos, breite, hoehe);
        repaint();
        graphic.setColor(original);
    }

    /**
     * Fuellt das Innere des Rechtecks mit der angegebenen Farbe.
     * @param xPos,yPos Koordinaten der linken oberen Ecke 
     * @param breite, hoehe Breite und Hoehe des Rechtecks
     * @param  farbnr  Fuellfarbnummer fuer das Rechteck (0 bis 8)
     */
    public void fuelleRechteck(int xPos, int yPos, int breite, int hoehe, int farbnr)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbnr));
        graphic.fillRect(xPos, yPos, breite, hoehe);
        repaint();
        graphic.setColor(original);
    }

    /**
     * Loescht das Innere eines Rechtecks.
     * @param xPos,yPos Koordinaten der linken oberen Ecke 
     * @param breite, hoehe Breite und Hoehe des Rechtecks
     */
    public void loescheRechteck(int xPos, int yPos, int breite, int hoehe)
    {
        loesche(new Rectangle(xPos, yPos, breite, hoehe));
        loescheRand(new Rectangle(xPos, yPos, breite, hoehe));
    }
    public void leereRechteck(int xPos, int yPos, int breite, int hoehe)
    {
        loesche(new Rectangle(xPos, yPos, breite, hoehe));
    }
    public void loescheRandRechteck(int xPos, int yPos, int breite, int hoehe)
    {
        loescheRand(new Rectangle(xPos, yPos, breite, hoehe));
    }
    private Polygon gibDreieck(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        Polygon p=new Polygon();
        p.addPoint(x1,y1);
        p.addPoint(x2,y3);
        p.addPoint(x3,y3);
        return p;
    }
    
    /**
     * Zeichnet den Rand eines Dreiecks mit der aktuellen Farbe.
     * @param x1,y1 Koordinaten des ersten Eckpunkts 
     * @param x2,y2 Koordinaten des zweiten Eckpunkts 
     * @param x3,y3 Koordinaten des dritten Eckpunkts 
     */
    public void zeichneDreieck(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        graphic.drawPolygon(gibDreieck(x1, y1, x2, y2, x3, y3));
        repaint();
    }

    /**
     * Fuellt das Innere eines Dreiecks mit der angegebenen Farbe.
     * @param x1,y1 Koordinaten des ersten Eckpunkts 
     * @param x2,y2 Koordinaten des zweiten Eckpunkts 
     * @param x3,y3 Koordinaten des dritten Eckpunkts 
     * @param  farbe  Fuellfarbe fuer das Dreieck, erlaubt sind "weiss" "schwarz" "rot"
     * "gruen" "blau" "gelb" "magenta" "cyan" "grau"
     */
    public void fuelleDreieck(int x1, int y1, int x2, int y2, int x3, int y3, String farbe)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbe));
        graphic.fillPolygon(gibDreieck(x1, y1, x2, y2, x3, y3));
        repaint();
        graphic.setColor(original);
    }

    /**
     * Fuellt das Innere eines Dreiecks mit der angegebenen Farbe.
     * @param x1,y1 Koordinaten des ersten Eckpunkts 
     * @param x2,y2 Koordinaten des zweiten Eckpunkts 
     * @param x3,y3 Koordinaten des dritten Eckpunkts 
     * @param  farbnr  Fuellfarbnummer fuer das Dreieck (0 bis 8)
     */
    public void fuelleDreieck(int x1, int y1, int x2, int y2, int x3, int y3, int farbnr)
    {
        Color original=graphic.getColor();
        graphic.setColor(FarbKonverter.farbeZuColor(farbnr));
        graphic.fillPolygon(gibDreieck(x1, y1, x2, y2, x3, y3));
        repaint();
        graphic.setColor(original);
    }

    /**
     * Loescht das Innere eines Dreicks
     * @param x1,y1 Koordinaten des ersten Eckpunkts 
     * @param x2,y2 Koordinaten des zweiten Eckpunkts 
     * @param x3,y3 Koordinaten des dritten Eckpunkts 
     */
    public void loescheDreieck(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        loesche(gibDreieck(x1, y1, x2, y2, x3, y3));
    }

    /**
     * Loescht den Inhalt des Zeichenfensters.
     */
    public void loescheAlles()
    {
        Color original = graphic.getColor();
        graphic.setColor(HintergrundColor);
        Dimension size = getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        repaint();
    }

    /**
     * Loescht den Umriss eines Shape-Objekts.
     * @param  shape  das Shape-Object, dessen Umriss geloescht werden soll 
     */
    public void loescheRand(Shape shape)
    {
        Color original = graphic.getColor();
        graphic.setColor(HintergrundColor);
        graphic.draw(shape);  // Loeschen durch uebermalen mit Hintergrundfarbe
        graphic.setColor(original);
        repaint();
    }

    /**
     * Zeichnet ein Bild in das Zeichnenfenster .
     * @param  bild    das anzuzeigende Bild 
     * @param  x       x-Koordinate des linken Bildrands 
     * @param  y       y-Koordinate des oberen Bildrands 
     * @return  gibt eines booleschen Wert zurueck, der angibt, ob das Bild vollstaendig geladen 
     *          werden konnte 
     */
    public boolean zeichneBild(Image bild, int x, int y)
    {
        boolean result = graphic.drawImage(bild, x, y, null);
        repaint();
        return result;
    }

    /**
     * Zeichnet einen Text.
     * @param  text    die anzuzeigende Zeichenkette 
     * @param  x       x-Koordinate des linken Rands 
     * @param  y       y-Koordinate des oberen Rands 
     */
    public void zeichneText(String text, int x, int y)
    {
        graphic.drawString(text, x, y);   
        repaint();
    }

    /**
     * Loescht einen Text vom Zeichenfenster.
     * @param  text    die zu loeschende Zeichenkette 
     * @param  x       x-Koordinate des linken Rands 
     * @param  y       y-Koordinate des oberen Rands 
     */
    public void loescheText(String text, int x, int y)
    {
        Color original = graphic.getColor();
        graphic.setColor(HintergrundColor);
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        repaint();
    }

    /**
     * Zeichnet eine Strecke ins Zeichenfenster.
     * @param  x1   x-Koordinate des Anfangspunkts der Strecke 
     * @param  y1   y-Koordinate des Anfangspunkts der Strecke
     * @param  x2   x-Koordinate des Endpunkts der Strecke 
     * @param  y2   y-Koordinate des Endpunkts der Strecke  
     */
    public void zeichneStrecke(int x1, int y1, int x2, int y2)
    {
        graphic.drawLine(x1, y1, x2, y2);   
        repaint();
    }

    /**
     * Setzt die Vordergrundfarbe des Zeichenfensters.
     * @param  neueFarbe   neue Vordergrundfarbe 
     */
    public void setzeVordergrundFarbe(String neueFarbe)
    {
        graphic.setColor(FarbKonverter.farbeZuColor(neueFarbe));
    }
    private void setzeVordergrundFarbe(Color neueFarbe)
    {
        graphic.setColor(neueFarbe);
    }
    

    /**
     * Gibt die aktuelle Vordergrundfarbe des Zeichenfensters zurueck.
     * @return   die aktuelle Vordergrundfarbe 
     */
    public String gibVordergrundFarbe()
    {
        return FarbKonverter.colorZuFarbe(graphic.getColor());
    }
    public Color gibVordergrundColor()
    {
       return graphic.getColor();
    }

    /**
     * Setzt die Hintergrundfarbe des Zeichenfensters.
     * @param  neueFarbe   neue Hintergrundfarbe 
     */
    public void setzeHintergrundFarbe(String neueFarbe)
    {
        HintergrundColor = FarbKonverter.farbeZuColor(neueFarbe);   
        graphic.setBackground(HintergrundColor);
    }
    private void setzeHintergrundFarbe(Color neueFarbe)
    {
        HintergrundColor = neueFarbe;   
        graphic.setBackground(neueFarbe);
    }

    /**
     * Gibt die aktuelle Hintergrundfarbe des Zeichenfensters zurueck.
     * @return   die aktuelle Hintergrundfarbe 
     */
    public String gibHintergrundFarbe()
    {
        return FarbKonverter.colorZuFarbe(HintergrundColor);
    }
    public Color gibHintergrundColor()
    {
        return HintergrundColor;
    }
    /**
     * Aendert den aktuellen Zeichensatz des Zeichenfensters.
     * @param  neuerZeichensatz   Zeichensatz, der kuenftig fuer Zeichenkettenausgaben verwendet wird
     * Font hat die Struktur: java.awt.Font(String name, int style, int size)  
     * name muss ein gueltiger Fontname sein, sonst wird einfach nichts gezeichnet; 
     * style -> 0 (= Font.PLAIN); 1 (=Font.BOLD); 2 (=Font.ITALIC); 3 (=Font.ITALIC+Font.BOLD)
     * size -> Schriftgroesse
     * Um schnell die Schriftart neu zu setzen->z.B.: setzeZeichensatz(new java.awt.Font("Arial",0,12);
     */
    public void setzeZeichensatz(Font neuerZeichensatz)
    {
        graphic.setFont(neuerZeichensatz);
    }
    /**
     * Gibt den aktuellen Zeichensatz des Zeichenfensters zurueck.
     * @return     den aktuellen Zeichensatz
     **/
    public Font gibZeichensatz()
    {
        return graphic.getFont();
    }
    /**
     * Aendert die aktuelle Schriftgroesse des Zeichensatzes des Zeichenfensters.
     * @param  neueGroesse   neue Schriftgroesse, die kuenftig fuer Zeichenkettenausgaben verwendet wird
     */
    public void setzeSchriftGroesse(int neueGroesse)
    {
       Font font = gibZeichensatz();
       setzeZeichensatz(
         new Font(
            font.getFamily(),
            font.getStyle(),
            neueGroesse
         )
      );
    }
    /**
     * Aendert die aktuelle Schriftart des Zeichensatzes des Zeichenfensters.
     * @param  neueSchriftart   Schriftart, die kuenftig fuer Zeichenkettenausgaben verwendet wird
     */
    public void setzeSchriftArt(String neueSchriftart)
    {
       Font font = gibZeichensatz();
       setzeZeichensatz(
         new Font(
            neueSchriftart,
            font.getStyle(),
            font.getSize()
         )
      );
    }
    /**
     * Aendert den aktuellen Schriftstil des Zeichensatzes des Zeichenfensters.
     * @param  neuerStil   Schriftstil, der kuenftig fuer Zeichenkettenausgaben verwendet wird
     * moeglich sind:  "normal", "fett", "kursiv", "kursiv+fett", alle anderen Eingaben werden als "normal" interpretiert
     */
    public void setzeSchriftStil(String neuerStil)
    {
       int style;
       Font font = gibZeichensatz();
       switch(neuerStil)
       {
                case "fett":
                  style=Font.BOLD;
                  break;
                case "kursiv":
                  style=Font.ITALIC;
                  break;
                case "fett+kursiv":
                  style=Font.ITALIC+Font.BOLD;
                  break;
                default:
                  style=Font.PLAIN;
                  break;
       }
       setzeSchriftStil(style);
    }
    /**
     * Aendert den aktuellen Schriftstil des Zeichensatzes des Zeichenfensters.
     * @param  style   Schriftstil, der kuenftig fuer Zeichenkettenausgaben verwendet wird
     * moeglich sind:  Font.PLAIN, Font.BOLD, Font.ITALIC, Font.Italic+Font.BOLD, 
     */
    public void setzeSchriftStil(int style)
    {
       Font font = gibZeichensatz();
       setzeZeichensatz(
         new Font(
            font.getFamily(),
            style,
            font.getSize()
         )
       );
    }
    public class ZEICHENFLAECHE extends JPanel
    {
        private static final long serialVersionUID = 20130330L;
        
        public void paint(Graphics g)
        {
            g.drawImage(GemaltesAufDerZeichenFlaeche, 0, 0, null);
        }
    }
}