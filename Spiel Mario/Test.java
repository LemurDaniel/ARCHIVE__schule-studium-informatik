/**
 * Write a description of class Test here.
 * 
 * @author (Daniel Landau) 
 * @version (23.05.2015v1)
 */

import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;

public class Test
{

    private Bild Mario[] = new Bild[3];
    private Bild Luigi[] = new Bild[3];
    
    private Bild blauesHintergrundBild;
    private int x;
    private int y;
    private int Zahl;
    private Timer Lauftimer;
    private Timer Lauftimer2;
    private Timer Lauftimer3;
    private Timer Lauftimer4;
       
    public Test(){
        this(300,100);
    }
    
    public Test(int x, int y){
        Mario[0] = new Bild("pic/Mario.png",x,y);
        Luigi[0] = new Bild("pic/Luigi.png",100,100);
        Mario[1] = new Bild("pic/Mario2.png",300,250);
        Luigi[1] = new Bild("pic/Luigi2.png",100,250);
        Mario[2] = new Bild("pic/Mario3.png",700,250);
        Luigi[2] = new Bild("pic/Luigi3.png",700,100);
        
        blauesHintergrundBild = new Bild("pic/blauesHintergrundBild.png",-100,-100);
        
       Lauftimer = new Timer(1,new ActionListener()
                {           
                 public void actionPerformed(ActionEvent e){
                     bewegeFigurMario(1, 1);
                     Zahl += 1;
                     if(Zahl>=400){Lauftimer.stop();};
                     System.out.print(Zahl);
                     System.out.print(", ");
                    }
                });
                
       Lauftimer2 = new Timer(1,new ActionListener()
                {           
                 public void actionPerformed(ActionEvent e){
                     bewegeFigurMario2(1, 1);
                     Zahl += 1;
                     if(Zahl>=400){Lauftimer2.stop();};
                     System.out.print(Zahl);
                     System.out.print(", ");
                    }
                });
                
      Lauftimer3 = new Timer(1,new ActionListener()
                {           
                 public void actionPerformed(ActionEvent e){
                     bewegeFigurMario3(1, 1);
                     Zahl += 1;
                     if(Zahl>=400){Lauftimer3.stop();};
                     System.out.print(Zahl);
                     System.out.print(", ");
                    }
                });
                
       Lauftimer4 = new Timer(1,new ActionListener()
                {           
                 public void actionPerformed(ActionEvent e){
                     bewegeFigurLuigi(1, 1);
                     Zahl += 1;
                     if(Zahl>=400){Lauftimer4.stop();};
                     System.out.print(Zahl);
                     System.out.print(", ");
                    }
                });
    }
    
    private  int AbfrageFenster(){
        return JOptionPane.showOptionDialog(null, "Welche Spielfigur?", "Spielfigurwahl",  
               JOptionPane.DEFAULT_OPTION,      
               JOptionPane.QUESTION_MESSAGE, null,    
               new String[]{"Mario", "Mario2", "Mario3", "Luigi"}, "A");
               }
    
    public void LaufenStart(){
        int Figur=AbfrageFenster();
        if(Figur==0){x=300;y=100;Zahl=0;Lauftimer.start();}
        if(Figur==1){x=300;y=250;Zahl=0;Lauftimer2.start();}
        if(Figur==2){x=700;y=250;Zahl=0;Lauftimer3.start();}
        if(Figur==3){x=700;y=100;Zahl=0;Lauftimer4.start();}
    }


    
    private  void bewegeFigurMario(int Geschwindigkeit, int Zeit){
        //Mario.loeschen(Mario.pic,x,y);
       
        uebermalen(x,y);
        x = x + Geschwindigkeit * Zeit; 
        Mario[0].positioniereBild(Mario[0].pic,x,y);
        
    }
    
     private  void bewegeFigurMario2(int Geschwindigkeit, int Zeit){
        //Mario.loeschen(Mario.pic,x,y);
        
        uebermalen(x,y);
        x = x + Geschwindigkeit * Zeit; 
        Mario[1].positioniereBild(Mario[1].pic,x,y);
        
    }
    
    private  void bewegeFigurMario3(int Geschwindigkeit, int Zeit){
        
        uebermalen(x,y);
        x = x - Geschwindigkeit * Zeit; 
        Mario[2].positioniereBild(Mario[2].pic,x,y);
        
    }
    
    private void bewegeFigurLuigi(int Geschwindigkeit, int Zeit){

        uebermalen(x,y);
        x = x - Geschwindigkeit * Zeit; 
        Luigi[2].positioniereBild(Luigi[2].pic,x,y);
        
    }
    
    private void uebermalen(int x, int y){ // Methode zum übermalen mit dem Bild "blauesHintergrundBild" um anderes Bild zu "loeschen"
       blauesHintergrundBild.positioniereBild(blauesHintergrundBild.pic,x,y);
    }
    
}
