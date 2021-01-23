import java.util.Random;
import java.awt.*;

public class Test
{
    static private Dimension Masse = ZEICHENFENSTER.gibFenster().gibMasse();
    
    Subdivision Test_sub;
    Koordinaten[] Test_koo;
    
    public Test(int shape){
        
        switch(shape){
            
        case 1: // 8-eck
            Test_koo = new Koordinaten[8];
            
            Test_koo[0] = new Koordinaten(250,250);
             Test_koo[1]=  new Koordinaten(375,200);  
            
            Test_koo[2] = new Koordinaten(500,250);
             Test_koo[3]=  new Koordinaten(550,375);
            
            Test_koo[4] = new Koordinaten(500,500);
             Test_koo[5]=  new Koordinaten(375,550);
            
            Test_koo[6] = new Koordinaten(250,500);
            Test_koo[7]=  new Koordinaten(200,375);             
            break;
            
        case 2: //3-eck
            Test_koo = new Koordinaten[3];
            
            Test_koo[0] = new Koordinaten(50,900);
            Test_koo[1]=  new Koordinaten(550,50);  
            
            Test_koo[2] = new Koordinaten(1150,900);
            break;
            
        case 3:
            Test_koo = new Koordinaten[8];
            
            Test_koo[0] = new Koordinaten(250,150);
             Test_koo[1]=  new Koordinaten(115,220);  
            
            Test_koo[2] = new Koordinaten(568,220);
             Test_koo[3]=  new Koordinaten(550,375);
            
            Test_koo[4] = new Koordinaten(50,500);
             Test_koo[5]=  new Koordinaten(350,550);
            
            Test_koo[6] = new Koordinaten(250,500);
            Test_koo[7]=  new Koordinaten(960,750);    
        
        
        default:
            Test_koo = new Koordinaten[rand(3,20)];
                
            for(int i = 0; i<Test_koo.length; i++){
                Test_koo[i] = new Koordinaten( rand(0,Masse.width) , rand(0,Masse.height));
            }
            
        }

        
        Test_sub = new Subdivision(Test_koo);
        Test_sub.draw_shape(Test_koo, "rot",true,"rot" );
    }
    
     public static int rand(int MinWert, int MaxWert){ // gibt Zufalllzahlen
         
        int Zufallszahl = (int)((Math.random()*(MaxWert+1 -MinWert)+MinWert));
        return Zufallszahl;
    }    
   
   
    public void subdivide(){
        Test_sub.subdivide();
    }
    
    public void subdivide(int times){
        Test_sub.subdivide(times);
    }
    
    public void undivide(){
        Test_sub.undivide();
    }
    
    public Subdivision gib(){
        return Test_sub;
    }

 
}
