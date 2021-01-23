import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Beschreiben Sie hier die Klasse Knopfgenerator.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Knopf
{
        private JButton b = new JButton( "Beschriftung" );
        ZEICHENFENSTER fenster;
        
        Knopf()
        {
            
            fenster= ZEICHENFENSTER.gibFenster();
            fenster.steuerungOst.add(b);
        ActionListener al = new ActionListener() 
        
        {
            
            public void actionPerformed( ActionEvent e ) 
            {
                methode_ausfuehren();
            }
        };
        b.addActionListener( al );
        
        }
        
        private void methode_ausfuehren()
        {
             System.out.println("Beispiel fuer methode_ausfuehren");   
        }
}
