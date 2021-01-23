import javax.swing.Timer;
import java.awt.event.*;
import java.util.Random;

public class PETRUS
{
    private NIEDERSCHLAG niederschlag;
    private Timer uhr;
    
    
    public PETRUS()
    {
      niederschlag = new NIEDERSCHLAG();
	  uhr = new Timer(100, new ActionListener()
            {
             public void actionPerformed(ActionEvent e)
                   {
                       ticke();
                                }
           });
       
    }
    
    private void ticke()
    {
        niederschlag.bewege(2.0);
        niederschlag.zeichne();
    }
    
    public void starte()
    {
        uhr.start();
    }
    
    public void stoppe()
    {
        uhr.stop();
    }
    
    
    
    
    
    

}
