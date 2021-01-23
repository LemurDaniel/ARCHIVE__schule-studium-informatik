import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

             
public class Menue extends JFrame{
    
    private String Spielfigur;  // Name der Spielfigur
    private String Spielkarte;  // Name der Spielkarte
                       
  public Menue(){ // Konstruktor von Menue
      
     super("Hauptmenü");
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     add(createMenuPanel());
     pack();
     setLocationRelativeTo(null);
     setVisible(true);
     
  }
             
  private JPanel createMenuPanel() { //erzeugen der 4 Knöpfe ("Start",Settings","Credits","close") im Hauptmenü
      
     JPanel panel = new JPanel(new GridLayout(0, 1));  //erstellen eines neuen JPanels mit Layout
     JButton start = new JButton("Start");            //erstellen des "Start" Knopfes 
     start.addActionListener(new ActionListener()  // zuweisen eines AL für "Start" Knopf
     {
         
        public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Start" Knopfes ausgeführt wird

           //Knopfgenerator kn4 = new Knopfgenerator("Walk","unten","Walk");
           //Knopfgenerator kn5 = new Knopfgenerator("Jump","unten","Jump");
           //Knopfgenerator kn6 = new Knopfgenerator("Stop","unten","Stop");
           
           if(Spielkarte==null || Spielfigur==null){aendereSettings();} // prüfen ob Spiekarte oder Spielfigur einen gültigen Wert aufweisen. Falls kein gültiger Wert: Abfragen des Spielers nach gültigen Werten mit der Methode aendereSettings()
           if(Spielkarte==null || Spielfigur==null){defaultSettings();} // prüfen ob Spiekarte oder Spielfigur einen gültigen Wert aufweisen. Falls kein gültiger Wert: einstellen der Standart-Einstellungen mit der Methode defaultSettings()                                                         
      
           JButton SettingsKnopf = new JButton("Settings"); // erstellen des "Settings" Knopfs im Zeichenfenster
           ZEICHENFENSTER.gibFenster().steuerungOst.add(SettingsKnopf);
           SettingsKnopf.addActionListener(new ActionListener(){ // zuweisen eines AL
               public void actionPerformed( ActionEvent e ) { // Methode welche nach klicken des "Settings" Knops ausgeführt wird
                   zeigeSettings(); // zeigen der aktuellen Einstellungen
                   }
              });
               
           Knopfgenerator kn2 = new Knopfgenerator("Credits","rechts","Credits"); // erstellen des "Credits" Knopfes mit der Klasse Knopfgenerator
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
           
          public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Settings" Knops ausgeführt wird
              aendereSettings(); //aendern der aktuellen Einstellungen
              zeigeSettings();  //zeigen der aktuellen Einstellugnen
         }
      
    });
        
        JButton Credits = new JButton("Credits");  // erstellen des "Credits" Knopfs im Hauptmenue
       Credits.addActionListener(new ActionListener() { // zuweisen eines AL
           
          public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Credits" Knops ausgeführt wird
           System.out.println("Developed by Alexander Kiselov, Patric Zimonich, Daniel Landau and Dominic Zimonich in 2015 ");  
      }
     
     });
        
       JButton close = new JButton("Close");  //  erstellen des "Close" Knopfs im Hauptmenue
       close.addActionListener(new ActionListener() {  // zuweisen eines AL
           
          public void actionPerformed(ActionEvent e) { // Methode welche nach klicken des "Credits" Knops ausgeführt wird
            System.exit(0); 
      }
      
     });
             
     panel.add(start);
     panel.add(Settings);
     panel.add(Credits);
     panel.add(close);
     
     return panel;
  }

   private int waehleSpielfigur(){ // Methode zum Abfragen des Spieler nach der zu wählenden Spielfigur
        return JOptionPane.showOptionDialog(null, "Mit welcher Spielfigur möchten sie spielen?", "Spielfigurwahl",  // Erstellen einer Dialogbox (Titel: "Spielfigurwahl", Dialog: "Mit welcher Spielfigur möchten sie spielen?")
               JOptionPane.DEFAULT_OPTION,             // Einstellungen: Standart
               JOptionPane.QUESTION_MESSAGE, null,     // Nachrichtentyp: Frage
               new String[]{"Mario", "Mario-2", "Mario-3", "Luigi", "Luigi-2", "Luigi-3", "Wario", "Wario-2", "Waluigi", "Waluigi-2", "Mariotest"}, "A");  // AuswahlKnöpfe: "Mario", "Luigi"
    }
    
   private int waehleSpielkarte(){ // Methode zum Abfragen des Spieler nach der zu wählenden Spielkarte
        return  JOptionPane.showOptionDialog(null, "Auf welcher Karte möchten sie spielen?", "Spielkartenwahl",  // Erstellen einer Dialogbox (Titel: "Spielkartenwahl", Dialog: "Auf welcher Karte möchten sie spielen?")
                JOptionPane.DEFAULT_OPTION,          // Einstellungen: Standart
                JOptionPane.QUESTION_MESSAGE, null,  // Nachrichtentyp: Frage
                new String[]{"Karte-1", "Karte-2"}, "A");       // AuswahlKnöpfe: "Karte-1"
   }

  private void aendereSettings(){ // Methode zum aendern der Einstellungen (Spielfigur und Spielkarte)
      int Figur=waehleSpielfigur(); // ausführen der Methode waehleSpielfigur und dem zurückgegebenen Wert dem integer Figur zuweisen
      if(Figur==0){Spielfigur="Mario";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==1){Spielfigur="Mario-2";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==2){Spielfigur="Mario-3";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==3){Spielfigur="Luigi";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==4){Spielfigur="Luigi-2";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==5){Spielfigur="Luigi-3";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==6){Spielfigur="Wario";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==7){Spielfigur="Wario-2";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==8){Spielfigur="Waluigi";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      if(Figur==9){Spielfigur="Waluigi-2";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      
      if(Figur==10){Spielfigur="Mariotest";}  // zuweisen eines Strings an Spielfigur, je nachdem welcher Wert der int Figur hat
      
      int Karte=waehleSpielkarte(); // ausführen der Methode waehleSpielkarte und dem zurückgegebenen Wert dem integer Karte zuweisen
      if(Karte==0){Spielkarte="Karte-1";}  // zuweisen eines Strings an Spielkarte, je nachdem welcher Wert der int Karte hat
      if(Karte==1){Spielkarte="Karte-2";}  // zuweisen eines Strings an Spielkarte, je nachdem welcher Wert der int Karte hat

    }
    
   private void defaultSettings(){ // Setzt die Standart Wert für Spielkarte oder Spielfigur.
       if(Spielkarte==null){Spielkarte="Karte-1";} //Setzt Standartwert für Spielkarte, wenn Spielkarte keinen Wert besitzt
       if(Spielfigur==null){Spielfigur="Mario";}  //Setzt Standartwert für Spielfigur, wenn Spielfigur keinen Wert besitzt
    }
 
  private void zeigeSettings(){ // Methode zum anzeigen der aktuellen Einstellungen (Spielfigur und Spielkarte)
      System.out.println("-----------------------"); 
      System.out.println("       Settings:       "); 
      
      System.out.print("SpielFigur:    ");  
      if(Spielfigur==null){System.out.println("keine Spielfigur ausgewählt");} else {System.out.println(Spielfigur);}; //prüfen ob Spielfigur einen gültigen Wert besitzt und ausgeben eines Textes. 
                                                                                                                   //Falls kein gültiger Wert zugewiesen: Ausgabetext = "keine Spielfigur ausgewählt"
                                                                                                                   //Falls  eingültiger Wert zugewiesen: Ausgabetext = String Spielfigur
      System.out.print("SpielKarte:    ");                                                                        
      if(Spielkarte==null){System.out.println("keine Spielkarte ausgewählt");} else {System.out.println(Spielkarte);}  //prüfen ob Spielkarte gültigen einen Wert besitzt und ausgeben eines Textes. 
                                                                                                                   //Falls kein gültiger Wert zugewiesen: Ausgabetext = "keine Spielfigur ausgewählt"
                                                                                                                   //Falls  ein gültiger Wert zugewiesen: Ausgabetext = String Spielfigur
      System.out.println("-----------------------");                    
   }
}

        
          
