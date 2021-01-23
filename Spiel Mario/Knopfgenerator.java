import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Diese Klasse stellt die Möglichkeit zur Verfügung in das Zeichenfenster Buttons aufzunehmen Knopfgenerator.
 * 
 * @author (Horlebein) 
 * @version (20150429 V 0.1)
 */
public class Knopfgenerator
{
        private JButton knopf;  // Instanzvariable fuer einen JButton
        private ZEICHENFENSTER fenster; // Instanzvariable fuer den Zeiger zum aktuellen Zeichenfenster
        public String Befehl; // Instanzvariable zum auswaehlen fuer den Befehl beim Klicken;
        
        Knopfgenerator()
        {
            this("Abbruch");
        }
        
        Knopfgenerator(String Beschriftung)
        {
            this(Beschriftung, "rechts");
        } 
        
        Knopfgenerator(String Beschriftung, String Ort)
        {
            this(Beschriftung, Ort, "abbrechen");
        }
        
        Knopfgenerator(String Beschriftung, String Ort, String Bef)
        {
            fenster= ZEICHENFENSTER.gibFenster();
            knopf = new JButton(Beschriftung);
            Befehl=Bef;
            
            switch(Ort)
            {
                case "steuerungOst":
                    fenster.steuerungOst.add(knopf);
                 break;
                 case "rechts":
                    fenster.steuerungOst.add(knopf);
                 break;    
                 
                 case "steuerungSued":
                    fenster.steuerungSued.add(knopf);
                 break;
                 case "unten":
                    fenster.steuerungSued.add(knopf);
                 break;   
                 
                 case "mitte":
                    fenster.gibFrame().getContentPane().add(knopf);
                 break;
                 
                 default:
                    fenster.steuerungOst.add(knopf);
                 break;
            }
            
            ActionListener al = new ActionListener() // Erzeuge Variable für die Knopfaktivitaetsueberwachung
            {
                public void actionPerformed( ActionEvent e ) 
                {
                   ausfuehren(Befehl);
                }
            };
            knopf.addActionListener( al );
        }
        
        private void ausfuehren(String Befehl)
        {
            switch (Befehl)
            {
                case "Start":
                    Hauptfigur Figur  = new Hauptfigur();
                    Hauptmap1 Map1  = new Hauptmap1();
                    //System.out.println("Hallo");
                break;
                
                case "Settings":
                    System.out.println("........");     
                break;
                
                case "Credits":
                    System.out.println("Developed by Alexander Kiselov, Patric Zimonich, Daniel Landau and Dominic Zimonich in 2015 ");
                    //System.exit (0);
                break;
                
                case "End":                    
                    //System.exit (0);                
                break;
                
                default: System.exit (0);
            }
        }
}
