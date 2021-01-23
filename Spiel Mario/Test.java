import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;

public class Test
{

    Bild Mario;
    Bild Luigi;
    Bild Mario2;
    Bild Luigi2;
    Bild Mario3;
    Bild Luigi3;
    Bild blauesHintergrundBild;
    int x;
    int y;
    int Zahl;
    Timer Lauftimer;
    Timer Lauftimer2;
    Timer Lauftimer3;
    Timer Lauftimer4;
       
    public Test(){
        this(300,100);
    }
    
    public Test(int x, int y){
        Mario = new Bild("pic/Mario.png",x,y);
        Luigi = new Bild("pic/Luigi.png",100,100);
        Mario2 = new Bild("pic/Mario2.png",300,250);
        Luigi2 = new Bild("pic/Luigi2.png",100,250);
        Mario3 = new Bild("pic/Mario3.png",700,250);
        Luigi3 = new Bild("pic/Luigi3.png",700,100);
        
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
    
    public int AbfrageFenster(){
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


    
    public void bewegeFigurMario(int Geschwindigkeit, int Zeit){
        //Mario.loeschen(Mario.pic,x,y);
       
        uebermalen(x,y);
        x = x + Geschwindigkeit * Zeit; 
        Mario.positioniereBild(Mario.pic,x,y);
        
    }
    
     public void bewegeFigurMario2(int Geschwindigkeit, int Zeit){
        //Mario.loeschen(Mario.pic,x,y);
        
        uebermalen(x,y);
        x = x + Geschwindigkeit * Zeit; 
        Mario2.positioniereBild(Mario2.pic,x,y);
        
    }
    
    public void bewegeFigurMario3(int Geschwindigkeit, int Zeit){
        //Mario.loeschen(Mario.pic,x,y);
        
        uebermalen(x,y);
        x = x - Geschwindigkeit * Zeit; 
        Mario3.positioniereBild(Mario3.pic,x,y);
        
    }
    
    public void bewegeFigurLuigi(int Geschwindigkeit, int Zeit){
        //Mario.loeschen(Mario.pic,x,y);
        
        uebermalen(x,y);
        x = x - Geschwindigkeit * Zeit; 
        Luigi3.positioniereBild(Luigi3.pic,x,y);
        
    }
    
    public void uebermalen(int x, int y){
      blauesHintergrundBild.positioniereBild(blauesHintergrundBild.pic,x,y);
    }
}
