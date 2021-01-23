
/**
 * Klasse, in der die Zustaende fuer die Ampel repraesentiert werden koennen
 * 
 * @author (K. Horlebein) 
 * @version (6.3.15-A00)
 */
public class ZUSTAND
{
    private String Set[] = new String[4];
    
    ZUSTAND()
    {
        this(null);
    }
    
    ZUSTAND(String[] Kombi)
    {
        Set=Kombi;
    }

    public void setze(String[] Kombination, String Name)
    {
        Set=Kombination;
        for(int i=0;i<Set.length;i++){
            System.out.print(Set[i] + " ,");
        }
        System.out.println();
    }
    
    public String hole (int x)
    {
        return Set[x];
    }
}
