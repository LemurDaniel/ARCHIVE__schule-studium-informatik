
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
 
public class RUN
{
	
	 public static void main(String[] args) {
		
		Ampel a1 = new Ampel(10,100,10);
		Ampel3 a2 = new Ampel3 (100, 100,10,true);
		
		JComboBox<String> jbox = GenerateComboBox("aus;rot;gelb;gruen;rot-gelb;fail".split(";"));
		
		JButton off = GenerateButton("OFF", () -> a1.schalte_aus());
		GenerateButton("SWITCH", () -> a1.schalte_auf(jbox.getSelectedItem().toString()));
		
		GenerateButton("OFF", () -> a2.schalte_aus());
		GenerateButton("SWITCH", () -> a2.schalte_auf(jbox.getSelectedItem().toString()));
		
		
		off.doClick();
	 }
	 
	 private static JComboBox<String> GenerateComboBox (String[] options){
		JComboBox<String> box = new JComboBox<String> (options);
		ZEICHENFENSTER.gibFenster().gibSteuerungSued().add(box);
		return box;
	 }
	 
	 private static JButton GenerateButton (String name, simpleMethod sm) {
		JButton btn=new JButton(name);
        ZEICHENFENSTER.gibFenster().gibSteuerungSued().add(btn);
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
	 
	 interface simpleMethodParam {
		void runMethod(String param);
	 }
}

