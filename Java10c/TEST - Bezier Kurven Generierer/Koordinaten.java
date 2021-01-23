import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;

public class Koordinaten
{
    float Xpos;
    float Ypos;
    
    static private Dimension Masse = ZEICHENFENSTER.gibFenster().gibMasse();
    
    private int radius = 0;
    private int Butradius = 0;
    private boolean MouseListener = false;
    private boolean marked = false;
    private boolean MouseClick = false;
    private String default_color;
    
    private JButton Koo, Zeich;
    
    public Koordinaten(){
        Xpos = 0;
        Ypos = 0;
    }

    public Koordinaten(float x, float y){
        Xpos = x;
        Ypos = y;
        if(MouseListener){change_button_size();}
    }
    
    public void change(float x, float y){
        Xpos = x;
        Ypos = y;
        if(MouseListener){change_button_size();}
    }

    public void change_X(float x){
        Xpos = x;
        if(MouseListener){change_button_size();}
    }
    
    public void change_Y(float y){
        Ypos = y;
        if(MouseListener){change_button_size();}
    }
    
     public void Add_to_X(float n){
        Xpos += n;
        if(MouseListener){change_button_size();}
    }
    
    public void Add_to_Y(float n){
        Ypos += n;
        if(MouseListener){change_button_size();}
    }
    
    public void print(){
        System.out.print( "( " + Xpos + " / " + Ypos + " )" );
    }
    
    public void mark(int rad, String farbe){
        radius = rad;
        marked = true;
        default_color = farbe;
        if(MouseListener){change_button_size();}
        ZEICHENFENSTER.gibFenster().fuelleKreis( (int)Xpos, (int)Ypos, rad, farbe);
    }
    
    public void unmark(){
         marked = false;
        ZEICHENFENSTER.gibFenster().loescheKreis( (int)Xpos, (int)Ypos, radius);
    }
    
    public void add_mouseListener(){
        
        Zeich = new JButton("");
        ZEICHENFENSTER.gibFenster().frame.add(Zeich);
        ZEICHENFENSTER.gibFenster().frame.setLayout(null);       
        Zeich.setLayout(null);
        
        Zeich.setOpaque(false);
        Zeich.setContentAreaFilled(false);
        Zeich.setBorderPainted(false);
        
        Zeich.setBounds(0,0, Masse.width, Masse.height);
        
         Zeich.addMouseListener(new MouseAdapter(){  
             
                @Override
                public void mouseClicked(MouseEvent e){ 
                    if(MouseClick){
                        print();
                        int X = e.getX(); 
                        int Y = e.getY(); 
                        unmark();
                        change(X,Y);
                        mark(radius, default_color);
                        print();
                        MouseClick = false;                   
                    }
                    
                    //if(!MouseClick){MouseClick = true;}
                    change_visible_Zeich(false);
                    change_visible(Zeich, false);
                }  
                
            });
            
        change_visible_Zeich(false);

                              
        
        MouseListener = true;
        Butradius = radius*2;
        
        Koo = new JButton("");
        ZEICHENFENSTER.gibFenster().frame.add(Koo);
        ZEICHENFENSTER.gibFenster().frame.setLayout(null);       
        Koo.setLayout(null);
        
        Koo.setOpaque(false);
        Koo.setContentAreaFilled(false);
        Koo.setBorderPainted(false);
        
        Koo.setBounds((int)Xpos-Butradius/2,(int) Ypos-Butradius/2, Butradius, Butradius);
        
         Koo.addMouseListener(new MouseAdapter(){  

                @Override
                public void mouseClicked(MouseEvent e){ 
                    if(MouseClick){
                        MouseClick = false;                   
                    }
                    
                    if(!MouseClick){
                        MouseClick = true;
                        change_visible_Zeich(true);
                        change_visible(Zeich, false);
                    }
                }               
                
                @Override
                public void mouseExited(MouseEvent e){                                                                    
                   change_visible(Koo, false);
                }
                
                @Override
                public void mouseReleased(MouseEvent e){                                                                    
                   //MouseClick = false;
                }
                
            });
            
    }
     
    private void change_button_size(){
        Butradius = radius*2;
       Koo.setBounds((int)Xpos-Butradius/2,(int) Ypos-Butradius/2, Butradius, Butradius);
    }
    
    private void change_visible(JButton B, boolean Visible){
        B.setOpaque(Visible);
        B.setContentAreaFilled(Visible);
        B.setBorderPainted(Visible);
    }
    
    private void change_visible_Zeich(boolean Visible){
        Zeich.setVisible(Visible);
    }
}
