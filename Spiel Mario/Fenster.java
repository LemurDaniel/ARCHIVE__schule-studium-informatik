import javax.swing.*;
import java.awt.GridLayout;

public class Fenster{
    JFrame fenster =new JFrame(); // Fenster wird bereitgestellt
    JLabel label = new JLabel(); // Beschriftungsfeld wird bereitgestellt
    JButton knopf = new JButton(); // Knopf wird bereitgestellt
    
    public Fenster()
    {
      JPanel panel = new JPanel(new GridLayout(2,5));  
      JButton start = new JButton("Start");
      
      knopf.setText("Starten"); 
      knopf.setBounds(650, 150, 650, 350); 
      fenster.getContentPane().add(knopf);
      
      knopf.setText("Settings"); 
      knopf.setBounds(650, 250, 650, 350); 
      fenster.getContentPane().add(knopf);   
      
      knopf.setText("Credits"); 
      knopf.setBounds(650, 350, 650, 350); 
      fenster.getContentPane().add(knopf);   
      
      knopf.setText("Beenden"); 
      knopf.setBounds(650, 450, 650, 350); 
      fenster.getContentPane().add(knopf);   
    }    
    
    void macheSichtbar(){
 
        fenster.setVisible(true); // Methode zum zeigen des Fensters
        fenster.setSize(600,400);
    }
    
    void labelEinfuegen(String txt, int a, int b, int c, int d){ // Methode für Label
        label.setText(txt); 
        label.setBounds(a, b, c, d); 
        fenster.getContentPane().add(label); // 
    } // Aufgabe: Finde heraus, was a,b,c,d genau einstellen und finde sprechendere Namen!
    
    void knopfEinfuegen(String txt, int a, int b, int c, int d){ //Methode für Knopf
        knopf.setText(txt); 
        knopf.setBounds(a, b, c, d); 
        fenster.getContentPane().add(knopf); 
    } // Aufgabe: Finde heraus, was a,b,c,d genau einstellen und finde sprechendere Namen!
 


}


