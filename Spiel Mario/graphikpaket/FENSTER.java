package graphikpaket;

import javax.swing.*;
import java.awt.*;

/**
 * Class FENSTER - Eine Klasse, die einfache grafische Zeichnungen 
 * in einem Programmfenster ermoeglicht.
 * 
 * @author Michael Kolling (mik)
 * @author Bruce Quig
 * @author Christian Heidrich
 * @author Kurt Horlebein
 *
 * @version V12_20130330
 */

public abstract class FENSTER  extends JFrame implements I
{
    static int Breite=$StandardBreite;
    static int Hoehe=$StandardHoehe;
    static String HintergrundFarbe=$StandardHintergrundFarbe;
    static String Titel;
    Color HintergrundColor;
    static FENSTER StandardFenster;
    private static final long serialVersionUID = 20130330L;
    /**
     * Ruft den Konsturktor fuer diese Klasse (this => FENSTER) 
     * mit einem String-Parameter auf (-> Parameter ist auf "StandardFenster" gesetzt) 
     */
    public FENSTER()
    {
        this(Titel);
    }
    /**
     * Ruft den Konsturktor fuer diese Klasse (this => FENSTER) 
     * mit einem String-Parameter fuer neuerTitel   
     * und den Instanzvariablen fuer die oben festgelegten Standardmasse Breite - Hoehe - Hintergrundfarbe als Parameter auf
     * @param neuerTitel  Titel des Fensters  - String   
     */
    public FENSTER(String neuerTitel)
    {
        this(neuerTitel, Breite, Hoehe);        
    } 

    /**
     * Ruft den Konsturktor fuer diese Klasse (this => FENSTER) 
     * mit einem String-Parameter fuer neuerTitel, Integer-Parametern fuer neueBreite und neueHoehe  
     * und der Instanzvariable fuer die oben festgelegte Hintergrundfarbe als Parameter auf
     * @param neuerTitel  Titel des Fensters  - String
     * @param neueBreite  Breite des Fensters - int
     * @param neueHoehe  Hoehe des Fensters - int
     */
    public FENSTER(String neuerTitel, int neueBreite, int neueHoehe)
    {
       this(neuerTitel, neueBreite, neueHoehe, HintergrundFarbe);
    }

    /**
     * Ruft den Konsturktor fuer diese Klasse (this => FENSTER) 
     * mit einem String-Parameter fuer neuerTitel, Integer-Parametern fuer neueBreite und neueHoehe 
     * und einem in String umgewandelten Color-Parameter auf
     * @param neuerTitel  Titel des Fensters  - String
     * @param neueBreite  Breite des Fensters - int
     * @param neueHoehe  Hoehe des Fensters - int
     * @param neueHintergrundColor  Hintergrundfarbe des Zeichenfensters - Color (Javainterner Typ fuer Farbe)
     */
    public FENSTER(String neuerTitel, int neueBreite, int neueHoehe, Color neueHintergrundColor)
    {
        this(neuerTitel,neueBreite,neueHoehe,FarbKonverter.colorZuFarbe(neueHintergrundColor)); // Umwandlung des Paramters Color in String
    }
    /**
     * Ruft den Konsturktor fuer diese Klasse (this => FENSTER) 
     * mit einem String-Parameter fuer neuerTitel, Integer-Parametern fuer neueBreite und neueHoehe 
     * und einem String-Parameter fuer die neueHintergrundFarbe
     * erweitert um den boolean-Parameter fuer die Wahl des Standardfensters (->true das Standardfenster wird gewaehlt) auf
     * @param neuerTitel  Titel des Fensters  - String
     * @param neueBreite  Breite des Fensters - int
     * @param neueHoehe  Hoehe des Fensters - int
     * @param neueHintergrundFarbe  Hintergrundfarbe des Zeichenfensters - String
     */
    public FENSTER(String neuerTitel, int neueBreite, int neueHoehe, String neueHintergrundFarbe)
    {
        this(neuerTitel,neueBreite,neueHoehe,neueHintergrundFarbe,true);
    }
    /**
     * Erzeugt ein Objekt der Klasse FENSTER 
     * mit einem String-Parameter fuer neuerTitel, Integer-Parametern fuer neueBreite und neueHoehe 
     * und einem String-Parameter fuer die neueHintergrundFarbe
     * erweitert um den boolean-Parameter fuer die Wahl des Standardfensters (->true das Standardfenster wird gewaehlt)
     * @param neuerTitel  Titel des Fensters  - String
     * @param neueBreite  Breite des Fensters - int
     * @param neueHoehe  Hoehe des Fensters - int
     * @param neueHintergrundFarbe  Hintergrundfarbe des Zeichenfensters - String
     * @param standardfenster  Falls true wird das erzeugte Fensterobjekt an F als Standardfenster uebergeben  - boolean
     */
    public FENSTER(String neuerTitel, int neueBreite, int neueHoehe, String neueHintergrundFarbe,boolean standardfenster)
    {
        Breite=neueBreite; Hoehe=neueHoehe; HintergrundFarbe=neueHintergrundFarbe;
        // besetzt entsprechenden Instanzvariablen  mit den angegebenen Werten
        setzeTitel(holeTitel(neuerTitel)); // setzt den Titel des Fensters
        setSize(new Dimension(Breite,Hoehe));
        ergaenzeFensterElemente();
        pack(); // Das Fenster wird entsprechend den Veraenderungen neu arrangiert
        zeige();
    }
    public abstract FENSTER gibFenster();
    public abstract String holeTitel(String neuerTitel);
    public abstract void ergaenzeFensterElemente();
    /**
     * Beschriftet den Titel des Fensters neu.
     * @param  titelNeu  Text der neuen Fensterueberschrift
     */
    public void setzeTitel(String titelNeu)
    {
        setTitle(titelNeu);
    }
    public void setzeFensterBreite(int neueBreite)
    {
       Breite = neueBreite;
       setzeFensterGroesse(Breite, Hoehe);
    }
    public void setzeFensterHoehe(int neueHoehe)
    {
        Hoehe = neueHoehe;
        setzeFensterGroesse(Breite, Hoehe);
    }
    public void setzeFensterGroesse(int neueBreite, int neueHoehe)
    {
        Dimension groesse = new Dimension(); 
        groesse.width=neueBreite; 
        Breite=neueBreite;
        groesse.height=neueHoehe;
        Hoehe=neueHoehe;
        setSize(groesse);
        zeige();
    }
    /**
     * Macht das Fenster sichtbar bzw. setzt es in den Vordergrund,
     * falls es bereits sichtbar ist.
     */
    public void zeige()
    {
        setVisible(true);
    }
    /**
     * Gibt Information ueber die Sichtbarkeit.
     * @return  true falls das Fenster sichtbar ist.
     */
    public boolean istSichtbar()
    {
        return isVisible();
    }
    public void auffrischenFenster()
    {
        repaint();
    }
    public void macheSichtbar(boolean ja)
    {
        if (ja=true) {setVisible(true); } else setVisible(false);
    }
    /**
     * Gibt die Abmessungen des Fensters zurueck.
     * @return     die aktuellen Abmessungen des Fensters
     */

    public Dimension gibFensterGroesse()
    {
        return getSize();
    }
    public static int gibBreite()
    {
        return Breite;
    }
    public static int gibHoehe()
    {
        return Hoehe;
    }
    /**
     * Wartet eine bestimmte Zeit.
     * Eine kurze Verzoegerung kann z. B. fuer Animationen verwendet werden.
     * Aber Achtung -> Alle Methodenaufrufe die zusammen mit warte in einer Schleifen-, if-, oder switch-Sequenz erfolgen
     * werden waehrend der Wartezeit bereits intern abgearbeitet, die Wirkung ist also so, als ob Warte am Beginn der Sequenz
     * stehen wuerde. Wird z.B. eine Bildschirmausgabe vor warte aufgerufen und danach wieder eine, sieht man nur das, was danach
     * ausgegeben wurde.
     * @param  zeit  Wartezeit in Millisekunden 
     */
    public void warte(int zeit)
    {
        try
        {
            Thread.sleep(zeit);
        } 
        catch (InterruptedException e)
        {
            // ignoring exception at the moment
        }
    }
}