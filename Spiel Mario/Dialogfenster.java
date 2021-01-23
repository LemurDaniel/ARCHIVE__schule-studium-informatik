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
       String Titel = "Beispiel-Nachricht";  // Titel des Dialogfensters
       String Dialog = "Dies ist eine Beispiel Nachricht"; // im Fenster angezeigter Text 
       String array[] = {"Knopf0","Knopf1"}; // Namen und Anzahl der Knöpfe
       String Vorauswahl = array[1]; // Vorgewählter Knopf bei Aufruf des Dialogfensters
       
       gibNachrichtmitKnopf(Titel,Dialog,array,Vorauswahl);
   }
   
   public void BeispielArrayausgabe(){
       String Titel = "Beispiel-Nachricht";  // Titel des Dialogfensters
       String Dialog = "Ungültige Eingabe: \nMögliche Eingaben: \n"; // im Fenster angezeigter Text 
       String array[] = {"Eingabe1","Eingabe2","Eingabe3","Eingabe4","Eingabe5","Eingabe6"}; // Auszugebender Array
       String Nachrichtentyp = "Fehlermeldung"; // Der Nachrichtentyp
       int Zeilenumbruch = 3; //Zeilenumbruch nach 3 bei angezigten Elementen
       
       gibArrayaus(Titel,Dialog,array,Nachrichtentyp,Zeilenumbruch);
    }
   
   public static void gibArrayaus(String Titel, String Dialog, String[] array, String Nachrichtentyp, int Zeilenumbruch){ //Arraausgabe in einem Dialogfenster von einem Array aus Strings   
       int zahl=0; //zählt für Zeilenumbruch mit
       
       for(int i=0;i<array.length;i++){
           if(zahl==Zeilenumbruch){
               Dialog += "\n" + array[i] + "; "; //Zeilenumbruch nach gewünschter Zahl an Elementen
               zahl=0;
            }else{
                Dialog += array[i] + "; "; //addieren aller Elemente des arrays zu einem String mit Komma Leerzeichen dazwischen
                zahl++;
            }
        } 
           
       switch(Nachrichtentyp){
           
           case "Frage":
                gibFrage(Titel, Dialog);
                break;  
                
           case "Information":           
                gibInformation(Titel, Dialog);
                break;
                
           case "Fehlermeldung":
                gibFehlermeldung(Titel, Dialog);
                break;
                
           case "Warnung":
                gibWarnung(Titel, Dialog);
                break;
                
           case "Nachricht":
                gibNachricht(Titel, Dialog);
                break;           
       }
   }
   
   public static void gibArrayaus(String Titel, String Dialog, int[] array, String Nachrichtentyp, int Zeilenumbruch){  //Arraausgabe in einem Dialogfenster von einem Array aus Integers    
       int zahl=0; //zählt für Zeilenumbruch mit
       
       for(int i=0;i<array.length;i++){
           if(zahl==Zeilenumbruch){
               Dialog += "\n" + array[i] + "; "; //Zeilenumbruch nach gewünschter Zahl an Elementen
               zahl=0;
            }else{
                Dialog += array[i] + "; "; //addieren aller Elemente des arrays zu einem String mit Komma Leerzeichen dazwischen
                zahl++;
            }
        } 
           
       switch(Nachrichtentyp){
           
           case "Frage":
                gibFrage(Titel, Dialog);
                break;  
                
           case "Information":           
                gibInformation(Titel, Dialog);
                break;
                
           case "Fehlermeldung":
                gibFehlermeldung(Titel, Dialog);
                break;
                
           case "Warnung":
                gibWarnung(Titel, Dialog);
                break;
                
           case "Nachricht":
                gibNachricht(Titel, Dialog);
                break;           
       }
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
