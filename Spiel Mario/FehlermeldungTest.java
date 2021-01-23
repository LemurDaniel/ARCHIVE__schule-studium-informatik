
/**
 * Write a description of class FehlermeldungTest here.
 * 
 * @author (Daniel Landau) 
 * @version (26.05.2015v1)
 */
public class FehlermeldungTest
{

    public FehlermeldungTest()
    {
    }
    
    public void FehlermeldungHauptfigur(){
        Hauptfigur TestFigur = new Hauptfigur("Maaarioo");

    }
    
    public void FehlermeldungHauptfigur2(){
        Hauptfigur TestFigur = new Hauptfigur();
        TestFigur.erstelleKnopf("blafl");
    }
    
    public void FehlermeldungHauptfigur3(){
        Hauptfigur TestFigur = new Hauptfigur();
        TestFigur.LaufenStart("blafdal");
    }
    
    public void FehlermeldungHauptmap(){
        Hauptmap TestKarte = new Hauptmap("falscheKarte");
    }
    
    public void FehlermeldungHauptmap2(){
        Hauptmap TestKarte = new Hauptmap();
        TestKarte.erstelleKnopf("bladasfal");
    }
    
    public void FehlermeldungRECHTECK(){
        RECHTECK TestRechteck = new RECHTECK();
        TestRechteck.bewegex(4,"blarg");
   }
   
   public void FehlermeldungRECHTECK2(){
        RECHTECK TestRechteck = new RECHTECK();
        TestRechteck.bewegey(4,"gdgrqf");
   }
   
   public void FehlermeldungKREIS(){
        KREIS TestKreis = new KREIS();
        TestKreis.bewegex(4,"gdafag");
   }
   
   public void FehlermeldungKREIS2(){
        KREIS TestKreis = new KREIS();
        TestKreis.bewegey(4,"gafdg");
   }
   
   public void FehlermeldungDREIECK(){
        DREIECK TestDreieck = new DREIECK(1,1,1,"rot","fasscheECKE",true);
   }
   
   public void FehlermeldungDREIECK2(){
        DREIECK TestDreieck = new DREIECK();
        TestDreieck.bewegex(4,"vncyny");
   }
   
   public void FehlermeldungDREIECK3(){
        DREIECK TestDreieck = new DREIECK();
        TestDreieck.bewegey(4,"gjkflkg");
   }
   
}
