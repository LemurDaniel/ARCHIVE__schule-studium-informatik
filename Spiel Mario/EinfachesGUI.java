import javax.swing.*;

public class EinfachesGUI extends JFrame 
{
    public EinfachesGUI()
    { getContentPane().setLayout(null); 
      setSize(300,300);
      JLabel l = new JLabel(); 
      l.setText("Label-Fläche"); 
      l.setBounds(10, 0, 100, 20); 
      getContentPane().add(l); 
      JButton b = new JButton(); 
      b.setText("Button-Fläche"); 
      b.setBounds(10, 50, 120, 20); 
      getContentPane().add(b);
    }
    public static void main (String args [ ] ) 
    { 
        EinfachesGUI oberflaeche = new EinfachesGUI(); oberflaeche.setVisible(true);
    }
}

// Teste das Programm -> Hinweis: Die Main-Methode wird abgearbeitet, wenn man die Klasse nicht mit 
// new EinfachesGUI erzeugt, sondern direkt über den Aufruf der main-Methode 