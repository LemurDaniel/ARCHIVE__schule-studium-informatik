/**
 * Write a description of class Dialogfenster here.
 * 
 * @author (Daniel Landau) 
 * @version (27.05.2015v1)
 */

import javax.swing.JOptionPane;

public class Dialogfenster
{   
   public void BeispielNachricht(){
       String Titel="Beispiel-Nachricht";  // Titel des Dialogfensters
       String Dialog="Dies ist eine Beispiel Nachricht"; // im Fenster angezeigter Text 
       String arr[]={"Knopf0","Knopf1"}; // Namen und Anzahl der Knöpfe
       String Vorauswahl=arr[1]; // Vorgewählter Knopf bei Aufruf des Dialogfensters
       gibNachrichtmitKnopf(Titel,Dialog,arr,Vorauswahl);
   }
   
   public static String stelleFragemitListe(String Titel, String Dialog, String[] array, String Vorauswahl){ //Methode zum stellen einer Frage mit Antworten als Liste
      return (String)JOptionPane.showInputDialog(null,Dialog,Titel,JOptionPane.QUESTION_MESSAGE,null,array,Vorauswahl); 
   }
    
   public static int stelleFragemitKnopf(String Titel, String Dialog, String[] array, String Vorauswahl){ //Methode zum stellen einer Frage mit mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null,Dialog,Titel, JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, null,array,Vorauswahl);  
   }
  
  public static int gibFehlermeldungmitKnopf(String Titel, String Dialog, String[] array, String Vorauswahl){  //Methode zum ausgeben einer Information mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null, Dialog, Titel,JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,null, array,Vorauswahl);
  }
  
  public static int gibWarnungmitKnopf(String Titel, String Dialog, String[] array, String Vorauswahl){ //Methode zum ausgeben einer Information mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null, Dialog, Titel,JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, array,Vorauswahl);
  }
  
  public static int gibNachrichtmitKnopf(String Titel, String Dialog, String[] array, String Vorauswahl){ //Methode zum ausgeben einer Information mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null, Dialog, Titel,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, array,Vorauswahl);
  }
  
  public static String stelleFragemitListe(String Titel, String Dialog, String[] array){ //Methode zum stellen einer Frage mit Antworten als Liste
      return (String)JOptionPane.showInputDialog(null,Dialog,Titel,JOptionPane.QUESTION_MESSAGE,null,array,array[0]); 
  }
    
  public static int stelleFragemitKnopf(String Titel, String Dialog, String[] array){ //Methode zum stellen einer Frage mit mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null,Dialog,Titel, JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, null, array, array[0]);  
  }
  
  public static int gibFehlermeldungmitKnopf(String Titel, String Dialog, String[] array){  //Methode zum ausgeben einer Information mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null, Dialog, Titel,JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,null, array, array[0]);
  }
  
  public static int gibWarnungmitKnopf(String Titel, String Dialog, String[] array){ //Methode zum ausgeben einer Information mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null, Dialog, Titel,JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, array, array[0]);
  }
  
  public static int gibNachrichtmitKnopf(String Titel, String Dialog, String[] array){ //Methode zum ausgeben einer Information mit selbst beschriebenen Knöpfen
     return JOptionPane.showOptionDialog(null, Dialog, Titel,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, array, array[0]);
  }
     
   public static String gibTexteingabe(String Dialog, String Textfeld){ //Methode um Spieler einen String eingeben zu lassen
     return (String)JOptionPane.showInputDialog(Dialog,Textfeld);
  }
   
  public static int gibFrage(String Dialog, String Titel){ //Methode zum stellen einer Ja/Nein Frage
     return JOptionPane.showConfirmDialog(null,Dialog,Titel,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
  }
  
  public static void gibInformation(String Titel, String Dialog){ //Methode zum ausgeben einer Information
       JOptionPane.showMessageDialog(null,Dialog,Titel,JOptionPane.INFORMATION_MESSAGE);
  }
    
  public static void gibFehlermeldung(String Titel, String Dialog){ //Methode zum ausgeben einer Fehlermeldung
       JOptionPane.showMessageDialog(null,Dialog,Titel,JOptionPane.ERROR_MESSAGE);
  }
   
  public static void gibWarnung(String Titel, String Dialog){ //Methode zum ausgeben einer Warnung
       JOptionPane.showMessageDialog(null,Dialog,Titel,JOptionPane.WARNING_MESSAGE);
  }
   
  public static void gibNachricht(String Titel, String Dialog){ //Methode zum ausgeben einer Nachricht
       JOptionPane.showMessageDialog(null,Titel,Dialog,JOptionPane.PLAIN_MESSAGE);
  }
}
