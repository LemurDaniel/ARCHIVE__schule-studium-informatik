package graphikpaket;



public interface I
/**
 * Statische Konstanten -> Sammlung von statischen Variablen, die für alle Klassen gelten
 * In einem Interface kann auch der Rumpf von Methoden definiert werden, wobei in den Klassen
 * die das Interface implementieren mit Anweisungen hinzugefuegt werden muessen ->
 * z.B. im Interface: public void BeispielMethode(int x, String y);
 * -> in der implementierenden Klasse: 
 *   class BspKlasse implements I 
 *   { 
 *       ... 
 *       public void BeispielMethode (int x, String y){ System.out.println(y+x);}
 * 
 * @author (Horlebein) 
 * @version (V3_20130319)
 */

{
   public static int $StandardBreite = 700;
   public static int $StandardHoehe = 500;
   public static String $StandardHintergrundFarbe = "weiss";
   public static String $StandardZeichenFarbe = "schwarz";
//   public static ZEICHENFENSTER $StandardZEICHENFENSTER = new ZEICHENFENSTER();
   public static GUIZEICHENFENSTER $StandardZEICHENFENSTER = new GUIZEICHENFENSTER();
}
