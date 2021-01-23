
/**
 * Beschreiben Sie hier die Klasse ec.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum farbe 
{
    schwarz, 
    blau, 
    gruen, 
    cyan, 
    rot, 
    magenta, 
    gelb, 
    grau, 
    weiss;

    public int holeFarbzahl(farbe Farbe)
    {
        switch (Farbe)
        {
           case schwarz: return 0;
           
           case blau: return 1;
           
           case gruen: return 2;
           
           case cyan: return 3;
           
           case rot: return 4;
           
           case magenta: return 5;
           
           case gelb: return 6;
           
           case grau: return 7;
           
           case weiss: return 8;
           
           default: return -1;           
        }        
    }
}
