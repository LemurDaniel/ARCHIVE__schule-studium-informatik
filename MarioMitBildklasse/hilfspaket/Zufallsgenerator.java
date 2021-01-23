package hilfspaket;


/**
 * Write a description of class Zufallsgenerator here.
 * 
 * @author (Ritterhoff Christian) 
 * @version (a version number or a date)
 */
public class Zufallsgenerator
{
    public static int Zufallszahlengenerator(int x,int y)
    {
        if (y>x)
        {
            return ((int)((Math.random()*(y-x))+x+0.99999999999999999999999));
        }
        else
        {
            System.out.println("Keine korrekte Eingabe");
            return (-1);
        }
    }
}
