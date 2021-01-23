
public class Test
{
    
    Koordinatensystem Test_Koo;
    Koordinaten Bezug;
    
    public Test(){
        Bezug = new Koordinaten(80,500);
        Test_Koo = new Koordinatensystem(Bezug);
    }


    public void Change_Bezug(int x, int y){
        Bezug.change(x, y);
        Test_Koo.change_Bezug(Bezug);
    }
    
    public void doStuff(){
        Test_Koo.Berechne_Kurve(50);
        Test_Koo.Berechne_Gitternetz(50);
    }
    
    public void DoIt(){
        Test_Koo.DoIt(100);
    }
    
    public void loesche(){
        Test_Koo.loesche_Alles();
    }
    
    public Koordinatensystem gib(){
        return Test_Koo;
    }
}
