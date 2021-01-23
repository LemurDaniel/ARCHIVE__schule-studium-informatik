
/**
 * Write a description of class Run here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v1)
 */
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.io.*; 
 
public class RUN
{
	
	 public static void main(String[] args) {
		
		ADVENTSKRANZ ad = new ADVENTSKRANZ();
		while(true){
		try {System.console().readLine();} catch(Exception e) {}
		ad.Lichtanzuenden();
		}
		
		//JButton btn = GenerateButton("Zündeln", () -> ad.Lichtanzuenden());
		//btn.doClick();
	 }
	 
	  private static JButton GenerateButton (String name, simpleMethod sm) {
		JButton btn=new JButton(name);
        ZEICHENFLAECHE.ObjektGeben().steuerungSued.add(btn);
        btn.addActionListener(new ActionListener(){        
                   public void actionPerformed(ActionEvent e){                                                                    
                      sm.runMethod();   
                   }
              }); 
		return btn;
	 }
	 
	 interface simpleMethod {
		void runMethod();
	 }
}