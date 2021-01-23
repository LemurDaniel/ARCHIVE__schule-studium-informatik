import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
             
public class Menu extends JFrame{
    
    String Zustand;
    int breite; 
    int x;
    int y;
    private static int boden = 480;
    Hauptfigur Figur;
    Hauptfigur2 Figur2;
             
  public static void main(String[] args){
     new Menu();
  }
             
  //Konstruktor
  public Menu(){
      
     super("Name");
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     add(createMenuPanel());
     pack();
     setLocationRelativeTo(null);
     setVisible(true);
     
  }
             
  private JPanel createMenuPanel() {
      
     JPanel panel = new JPanel(new GridLayout(0, 1));  
     JButton start = new JButton("Start");
     start.addActionListener(new ActionListener() 
     {
         
         
      @Override
        public void actionPerformed(ActionEvent e) {
           Spiel Hauptspiel = new Spiel();
            }
            
        });
        
       JButton close = new JButton("Close");
       close.addActionListener(new ActionListener() {
           
        @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
      }
      
     });
             
     panel.add(start);
     panel.add(close);
     
     return panel;
  }
 
  
  protected static int gibBoden()
  {
      return boden;
    }
  
  public void bewege(double zeit)
  {
      if (x < Hauptfigur.gibBoden())
      {
          super.bewege(zeit);
        }
        else
        {
            meineHauptfigur.entferne(this);
        }
    }
}
  



        
          
