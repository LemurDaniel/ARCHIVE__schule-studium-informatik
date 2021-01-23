package hilfspaket;

import java.util.Scanner;
/**
 * KonsolenLeser -> Liest eine Eingabe von der Tastatur ueber die der Konsole.
 * 
 * @author (Ihr K. Horlebein) 
 * @version (V0_20130410)
 */
public class KonsolenLeser
{
    static Scanner Textsammler = new Scanner (System.in);
    /**
     * @param   String AnweisungsText
     * @return  String RückgabeText
     */
    public static String  holeEingabe(String AnweisungsText)
    {
        System.out.println(AnweisungsText);
        return Textsammler.nextLine();
    }
    
}
