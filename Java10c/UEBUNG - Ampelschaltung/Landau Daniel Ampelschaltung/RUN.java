
/**
 * Write a description of class Run here.
 * 
 * @author (Daniel Landau) 
 * @version (20141105v1)
 */
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class RUN
{
	
	 public static void main(String[] args) {
		Ampelanlage al = new Ampelanlage();
		
		JButton on = GenerateButton("ON", () -> al.schalte_ein());
		GenerateButton("OFF", () -> al.schalte_aus());
		GenerateButton("SWITCH", () -> al.schalte_auf());
		GenerateButton("DEL", () -> al.loesche());
		GenerateButton("DRAW", () -> al.zeichne());
		
		on.doClick();
	 }
	 
	 private static JButton GenerateButton (String name, simpleMethod sm) {
		JButton btn=new JButton(name);
        ZEICHENFENSTER.gibFenster().gibSteuerungSued().add(btn);;
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

