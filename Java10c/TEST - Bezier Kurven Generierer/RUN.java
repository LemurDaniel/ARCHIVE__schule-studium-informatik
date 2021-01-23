
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
		

		
		Test t = new Test();
		
		
		JButton off = GenerateButton("DoIt", () -> t.DoIt());
		GenerateButton("DoStuff", () -> t.DoIt());
		
		off.doClick();
		
		int i=0;
		while(true){
			System.out.println("1 --> DoIt | 2 --> DoStuff | 3 --> Loesche: ");
			try{
			String s = System.console().readLine();
				i = Integer.parseInt(s);
			}catch(Exception e){
			}
			if (i==1) t.DoIt();
			else if(i==2) t.doStuff();
			else if(i==3) t.loesche();
		}
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

