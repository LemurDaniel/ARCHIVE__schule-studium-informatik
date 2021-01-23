import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Arrays;
             
public class Menue extends JFrame{
    
    private String Spielfigur = "keine Spielfigur gew�hlt" ;  // Name der Spielfigur
    private String Spielkarte = "keine Spielkarte gew�hlt" ;  // Name der Spielkarte
    private String SF[]=Hauptfigur.gibSpielfiguren(); // holen des Arrays mit den verf�gbaren Spielfiguren
    private String SK[]=Hauptmap.gibSpielkarten();    // holen des Arrays mit den verf�gbaren Spielkarten

 
  public Menue(){ // Konstruktor von Menue
      
     super("Hauptmen�");
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     add(createMenuPanel());
     pack();
     setLocationRelativeTo(null);
     setVisible(true);
     
  }
             
  private JPanel createMenuPanel() { //erzeugen der 4 Kn�pfe ("Start",Settings","Credits","close") im Hauptmen�
      
     JPanel panel = new JPanel(new GridLayout(0, 1));  //erstellen eines neuen JPanels mit Layout
     JButton start = new JButton("Start");            //erstellen des "Start" Knopfes 
     start.addActionListener(new ActionListener()  // zuweisen eines AL f�r "Start" Knopf
     {
         
        public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Start" Knopfes ausgef�hrt wird

           //Knopfgenerator kn4 = new Knopfgenerator("Walk","unten","Walk");
           //Knopfgenerator kn5 = new Knopfgenerator("Jump","unten","Jump");
           //Knopfgenerator kn6 = new Knopfgenerator("Stop","unten","Stop");
           
           if(!Arrays.asList(SF).contains(Spielfigur)||!Arrays.asList(SK).contains(Spielkarte)){defaultSettings(); aendereSettings();} // pr�fen ob Spiekarte oder Spielfigur einen g�ltigen Wert aufweisen.
      
           JButton SettingsKnopf = new JButton("Settings"); // erstellen des "Settings" Knopfs im Zeichenfenster
           ZEICHENFENSTER.gibFenster().steuerungOst.add(SettingsKnopf);
           SettingsKnopf.addActionListener(new ActionListener(){ // zuweisen eines AL
               public void actionPerformed( ActionEvent e ) { // Methode welche nach klicken des "Settings" Knops ausgef�hrt wird
                   zeigeSettings(); // zeigen der aktuellen Einstellungen
                   }
              });
               
              
           JButton CreditsKnopf = new JButton("Credits"); // erstellen des "Credits" Knopfs im Zeichenfenster
           ZEICHENFENSTER.gibFenster().steuerungOst.add(CreditsKnopf);
           CreditsKnopf.addActionListener(new ActionListener(){ // zuweisen eines AL
               public void actionPerformed( ActionEvent e ) { // Methode welche nach klicken des "Credits" Knops ausgef�hrt wird
                   zeigeCredits(); // zeigen der aktuellen Einstellungen
                   }
              });
               
               
           Knopfgenerator kn3 = new Knopfgenerator("End","rechts","Stop");  // erstellen des "End" Knopfes mit der Klasse Knopfgenerator
           
           Hauptmap Map = new Hauptmap(Spielkarte);  // erstellen der Map mit dem Parameter Spielkarte (Karte-1)
           Map.erstelleKnopf("Start");  // erstellen des "Start" Knopfes im Objekt Map (siehe Klasse Hauptkarte, Methode: erstelleKnopf)
           Map.erstelleKnopf("Stop");  //  erstellen des "Stop" Knopfes im Objekt Map  (siehe Klasse Hauptkarte, Methode: erstelleKnopf)
           
           Hauptfigur Figur = new Hauptfigur(Spielfigur);  // erstellen der Figur mit dem Parameter Spielfigur (Mario, Luigi)
           Figur.erstelleKnopf("rechtsLaufen");        // erstellen des "Laufen" Knopfes im Objekt Figur      (siehe Klasse Hauptfigur, Methode: erstelleKnopf)
           Figur.erstelleKnopf("rechtsLaufenStop");    // erstellen des "LaufenStop" Knopfes im Objekt Figur  (siehe Klasse Hauptfigur, Methode: erstelleKnopf)
           Figur.erstelleKnopf("Springen");      // erstellen des "Springen" Knopfes im Objekt Figur    (siehe Klasse Hauptfigur, Methode: erstelleKnopf)
           Figur.erstelleKnopf("SpringenStop");  // erstellen des "SpringenStop" im Objekt Figur        (siehe Klasse Hauptfigur, Methode: erstelleKnopf) 
           
           Figur.erstelleKnopf("linksLaufen");      // erstellen des "Springen" Knopfes im Objekt Figur    (siehe Klasse Hauptfigur, Methode: erstelleKnopf)
           Figur.erstelleKnopf("linksLaufenStop");  // erstellen des "SpringenStop" im Objekt Figur        (siehe Klasse Hauptfigur, Methode: erstelleKnopf) 
              
           setVisible(false);
            }
            
        });
        
         JButton Settings = new JButton("Settings"); // erstellen des "Settings" Knopfs im Hauptmenue
         Settings.addActionListener(new ActionListener() { // zuweisen eines AL
           
          public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Settings" Knops ausgef�hrt wird
              aendereSettings(); //zeigen der aktuellen Einstellugnen
         }
      
     });
        
        JButton Credits = new JButton("Credits");  // erstellen des "Credits" Knopfs im Hauptmenue
       Credits.addActionListener(new ActionListener() { // zuweisen eines AL
           
          public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Credits" Knops ausgef�hrt wird
           zeigeCredits();
      }
     
     });
        
       JButton close = new JButton("Close");  //  erstellen des "Close" Knopfs im Hauptmenue
       close.addActionListener(new ActionListener() {  // zuweisen eines AL
           
          public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Credits" Knops ausgef�hrt wird
            System.exit(0); 
      }
      
     });
             
     panel.add(start);
     panel.add(Settings);
     panel.add(Credits);
     panel.add(close);
     
     return panel;
  }
   
   private String waehleSpielfigur(){ // Methode zum Abfragen des Spieler nach der zu w�hlenden Spielkarte
        return  (String)JOptionPane.showInputDialog(
                 null,"Mit welcher Spielfigur m�chten sie spielen?\n\n"+"w�hlen sie eine Figur:","Spielfigurwahl", //Titel: "Spielfigurenwahl" Dialog: "Mit welcher Spielfigur m�chten sie spielen?\n"+ "w�hlen sie eine Figur:"
                 JOptionPane.QUESTION_MESSAGE,null,SF,SF[0]); // Nachrichtentyp: Frage // Auswahl: Alle Strings des Arrays SF // Standartauswahl: SF[0]
   }
   
   private String waehleSpielkarte(){ // Methode zum Abfragen des Spieler nach der zu w�hlenden Spielkarte
        return  (String)JOptionPane.showInputDialog(
                 null,"Auf welcher Karte m�chten sie spielen?\n\n"+"w�hlen sie eine Karte:","Spielkartenwahl", //Titel: "Spielkartenwahl" Dialog: "Auf welcher Karte m�chten sie spielen?\n"+ "w�hlen sie eine Karte:"
                 JOptionPane.QUESTION_MESSAGE,null,SK,SK[0]); // Nachrichtentyp: Frage // Auswahl: Alle Strings des Arrays SK // Standartauswahl: SK[0]
   }
   
   private void aendereSettings(){ // Methode zum aendern der Einstellungen (Spielfigur und Spielkarte)
      if(FrageSettingsaendern()==0){          
         String Figur=waehleSpielfigur();
      if(Figur!=null){Spielfigur=Figur;}; //pr�fen auf g�ltigen Wert
      
         String Karte=waehleSpielkarte();
      if(Karte!=null){Spielkarte=Karte;}; //pr�fen auf g�ltigen Wert
      } 
    }
    
    private void defaultSettings(){ // Setzt die Standart Wert f�r Spielkarte oder Spielfigur.
       if(!Arrays.asList(SK).contains(Spielkarte)){Spielkarte=SK[0];} //Setzt Standartwert f�r Spielkarte, wenn Spielkarte keinen g�ltigen Wert besitzt
       if(!Arrays.asList(SF).contains(Spielfigur)){Spielfigur=SF[0];}  //Setzt Standartwert f�r Spielfigur, wenn Spielfigur keinen g�ltigen Wert besitzt
     }
    
   private void zeigeSettings(){ // Methode zum anzeigen der aktuellen Einstellungen (Spielfigur und Spielkarte)
     JOptionPane.showMessageDialog(null,"Spielfigur:  "+Spielfigur +"\n"+"Spielkarte:  "+Spielkarte+"\n\n", "Aktuelle Einstellungen", //Titel: Aktuelle Einstellungen
     JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void zeigeCredits(){ // Methode zum anzeigen der aktuellen Einstellungen (Spielfigur und Spielkarte)
     JOptionPane.showMessageDialog(null,"Developed in 2015: \n\n"+"Alexander Kiselov\n"+"Daniel Landau\n"+"Patric Zimonich\n"+"Dominic Zimonich\n\n", "Credits" , //Titel: Aktuelle Einstellungen
     JOptionPane.INFORMATION_MESSAGE);
    }
 
   private int FrageSettingsaendern(){ // Methode zum anzeigen der aktuellen Einstellungen (Spielfigur und Spielkarte)
     String Auswahl[]={"�ndern","Behalten!!!"};
     return JOptionPane.showOptionDialog(null,"Spielfigur:  "+Spielfigur +"\n"+"Spielkarte:  "+Spielkarte+"\n\n", // Dialog
            "Aktuelle Einstellung", JOptionPane.YES_NO_OPTION, //Ja, Nein option
            JOptionPane.INFORMATION_MESSAGE, null, Auswahl, Auswahl[1]);  // Titel:  "Aktuelle Einstellung" // Nachrichtentyp: Information
   }
}

        
          
