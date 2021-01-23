
/**
 * Klasse, in der die Zustaende fuer die Ampel repraesentiert werden koennen
 * 
 * @author (K. Horlebein) 
 * @version (6.3.15-A00)
 */
public class ZUSTAND
{
    static String[] Set = {"","","",""};
    static String[] SetFussAmpel = {"","","","","","","",""};
    
    ZUSTAND()
    {
        this(Set, SetFussAmpel);
    }
    ZUSTAND(String[] Kombi, String[] Kombi2 )
    {
        Set=Kombi;
        SetFussAmpel=Kombi2;
    }

    public void setze(String[] Kombination)
    {
        for (int i=0;i<Kombination.length;i++)
        {
            Set[i]=Kombination[i];
        }
    }
    public String hole (int x)
    {
        return Set[x];
    }
}
