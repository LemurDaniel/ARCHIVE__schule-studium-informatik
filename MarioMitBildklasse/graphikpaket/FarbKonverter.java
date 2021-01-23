package graphikpaket;

import java.awt.Color;

/**
 * Beschreiben Sie hier die Klasse xx.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class FarbKonverter
{
    public static Color farbeZuColor(int farbnr)
    {
        switch (farbnr)
        {
            case 0: return Color.black;
            case 1: return Color.blue;
            case 2: return Color.green;
            case 3: return Color.cyan;
            case 4: return Color.red;
            case 5: return Color.magenta;
            case 6: return Color.yellow;
            case 7: return Color.gray;
            case 8: return Color.white;
            default: return Color.black;
        }
    }
    public static int farbeZuFarbNr(String farbe)
    {
        if (farbe=="weiss") return 8;
        if (farbe=="schwarz") return 0;
        if (farbe=="rot") return 4;
        if (farbe=="gruen") return 2;
        if (farbe=="blau") return 1;
        if (farbe=="gelb") return 6;
        if (farbe=="magenta") return 5;
        if (farbe=="cyan") return 3;
        if (farbe=="grau") return 7;
        return 0;
    }
    public static Color farbeZuColor(String farbe)
    {
        if (farbe=="weiss") return Color.white;
        if (farbe=="schwarz") return Color.black;
        if (farbe=="rot") return Color.red;
        if (farbe=="gruen") return Color.green;
        if (farbe=="blau") return Color.blue;
        if (farbe=="gelb") return Color.yellow;
        if (farbe=="magenta") return Color.magenta;
        if (farbe=="cyan") return Color.cyan;
        if (farbe=="grau") return Color.gray;
        return Color.black;
    }
    
    public static String colorZuFarbe(Color color)
    {
        if (color==Color.white) return "weiss";
        if (color==Color.black) return "schwarz";
        if (color==Color.red) return "rot";
        if (color==Color.green) return "gruen";
        if (color==Color.blue) return "blau";
        if (color==Color.yellow) return "gelb";
        if (color==Color.magenta) return "magenta";
        if (color==Color.cyan) return "cyan";
        if (color==Color.gray) return "grau";
        return "";
    }

}
