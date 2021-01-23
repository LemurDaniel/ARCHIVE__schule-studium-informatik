package hilfspaket;
import javax.swing.*;

public class DialogRahmen
{
  public static boolean t;
  public static String stelleFrage()
  {
      return stelleFrage("Eingabe mit Enter abschließen: ");
  }
  public static String stelleFrage(String FrageText)
  {
      return stelleFrage(null, FrageText, false);
  }
  public static String stelleFrage(String FrageText, boolean bestaetigen)
  {
      return stelleFrage(null, FrageText, bestaetigen);
  }
  public static String stelleFrage(JComponent Fenster, String FrageText, boolean bestaetigen)
  {
    String ausgabe = JOptionPane.showInputDialog(FrageText);
    if (bestaetigen) {JOptionPane.showMessageDialog(null, ausgabe , "Antwort", JOptionPane.QUESTION_MESSAGE);}
    return ausgabe;
  }
  public static void gibMeldungAus(String MeldungsText)
  {
      gibMeldungAus(MeldungsText,"Hinweis");
  }  
  public static void gibMeldungAus(String MeldungsText,String RahmenBeschriftung)
  {
       gibMeldungAus(MeldungsText,RahmenBeschriftung,"Information");
  } 
  public static void gibMeldungAus(String MeldungsText, String RahmenBeschriftung, String FensterTyp)
  {
      gibMeldungAus(null, MeldungsText,RahmenBeschriftung,FensterTyp);
  } 
  public static void gibMeldungAus(JComponent Fenster, String MeldungsText,String RahmenBeschriftung,  String FensterTyp)
  {
      switch(FensterTyp)
      {
          case "Alternativfrage":             
            JOptionPane.showMessageDialog(Fenster, MeldungsText, RahmenBeschriftung, JOptionPane.QUESTION_MESSAGE);   
          break;
          case "Information":             
            JOptionPane.showMessageDialog(Fenster, MeldungsText, RahmenBeschriftung, JOptionPane.INFORMATION_MESSAGE);   
          break;
           case "Fehlermeldung":             
            JOptionPane.showMessageDialog(Fenster, MeldungsText, RahmenBeschriftung, JOptionPane.ERROR_MESSAGE);   
          break;
           case "Warnung":             
            JOptionPane.showMessageDialog(Fenster, MeldungsText, RahmenBeschriftung, JOptionPane.WARNING_MESSAGE);   
          break;
        }
  }
  
 
  public static int holeAntwortMitFlexiblemRahmen(JComponent Fenster, String EntscheidungsFrage, String Beschriftung,
                                                  String RueckgabeKnoepfeTyp, String NachrichtenTyp, String[] KnopfTexte,
                                                  String StandardKnopf)
  {
      int NachrichtenTypZahl;
      int RueckGabeKnoepfeTypZahl;
  
      switch(NachrichtenTyp)
      {
          case "Alternativfrage":             
            NachrichtenTypZahl=JOptionPane.QUESTION_MESSAGE;   
          break;
          case "Information":             
            NachrichtenTypZahl=JOptionPane.INFORMATION_MESSAGE;   
          break;
          case "Fehlermeldung":             
            NachrichtenTypZahl= JOptionPane.ERROR_MESSAGE;   
          break;
          case "Warnung":             
            NachrichtenTypZahl=JOptionPane.WARNING_MESSAGE;   
          break;
          default:  NachrichtenTypZahl=JOptionPane.INFORMATION_MESSAGE;  break;
      }
      switch(RueckgabeKnoepfeTyp)
      {
          case "JaNein":             
            RueckGabeKnoepfeTypZahl=JOptionPane.YES_NO_OPTION;   
          break;
          case "JaNeinAbbruch":             
            RueckGabeKnoepfeTypZahl=JOptionPane.YES_NO_CANCEL_OPTION;   
          break;
          case "OkAbbruch":             
            RueckGabeKnoepfeTypZahl= JOptionPane.OK_CANCEL_OPTION;   
          break;
          case "OK":             
            RueckGabeKnoepfeTypZahl=JOptionPane.DEFAULT_OPTION;   
          break;
          default: RueckGabeKnoepfeTypZahl= JOptionPane.OK_CANCEL_OPTION; break;
      }  
      return JOptionPane.showOptionDialog(Fenster,EntscheidungsFrage, Beschriftung,RueckGabeKnoepfeTypZahl, 
                                         NachrichtenTypZahl, null, KnopfTexte, StandardKnopf);
  }
      
  public static int holeJaNeinAntwort(String FrageText)
  {
     
       return holeJaNeinAntwort(null,FrageText);
  }
  public static int holeJaNeinAntwort(JComponent Fenster, String EntscheidungsFrageText)
  {
     return JOptionPane.showConfirmDialog(null, EntscheidungsFrageText);
  }

  public static int test()
  {
  return JOptionPane.showOptionDialog(null, "Dies ist ein Optionsdialog","Optionsdialog",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE, null, 
                new String[]{"A", "B", "C"}, "B");  
  }

}
