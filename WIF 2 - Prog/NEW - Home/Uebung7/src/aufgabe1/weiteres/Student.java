package aufgabe1.weiteres;

import aufgabe1.a1.Fach;

public class Student implements Comparable<Student>{
  
  private String name;
  private int matnr;
  private Fach fach;



  public String getName() {
	  return this.name;
  }

  
  public void setName(String name)throws NameFormatException {
    this.name = checkName(name);
  }

  public static String checkName(String name) throws NameFormatException{
	  name = name.trim().toLowerCase();
	  if (name.matches(".*\\d+.*"))
		  throw new NameFormatException("Der Name darf keine Zahlen enthalten");
	  if (name.matches(".*[^a-z\\s].*"))
		  throw new NameFormatException("Der Name enthält ungültige Zeichen");
	  if (!name.matches(".*\\s+.*"))
		  throw new NameFormatException("Bitte geben sie einen Vor- und Nachnahmen ein");
	  if (!name.matches("[^\\s]+\\s+[^\\s]+"))
		  throw new NameFormatException("Bitte nur jeweils einen Vor- und Nachnahme");
	  int lastSpace = name.lastIndexOf((int)' ');
	  int firstSpace = name.indexOf((int)' ');
	  return Character.toUpperCase(name.charAt(0)) + name.substring(1, firstSpace) + ' ' + Character.toUpperCase(name.charAt(lastSpace+1)) + name.substring(lastSpace+2);
  }
  
   public int getMatnr() {
    return matnr;
  }

  
  public void setMatnr(int matnr) {
    this.matnr = matnr;
  }

  
  public Fach getFach() {
    return fach;
  }

  
  public void setFach(Fach fach) {
    this.fach = fach;
  }


  /** Gib eine textuelle Beschreibung dieses Studenten aus */
  public String toString() {
    String res = name + " (" + matnr + ")\n";
    switch(fach) {
      case MATHEMATIKSTUDIUM:
        return res + "  ein Mathestudent " +
                     "(oder auch zwei, oder drei).";
      case INFORMATIKSTUDIUM:
        return res + "  ein Informatikstudent.";
      case ARCHITEKTURSTUDIUM:
        return res + "  angehender Architekt.";
      case WIRTSCHAFTLICHESSTUDIUM:
        return res + "  ein Wirtschaftswissenschaftler.";
      case BIOLOGIESTUDIUM:
        return res + "  Biologie ist seine Staerke.";
      case GESCHICHTSSTUDIUM:
        return res + "   sollte Geschichte nicht mit Geschichten " +
                     "verwechseln.";
      case GERMANISTIKSTUDIUM:
        return res + "  wird einmal Germanist gewesen tun sein.";
      case POLITOLOGIESTUDIUM:
        return res + "  kommt bestimmt einmal in den Bundestag.";
      case PHYSIKSTUDIUM:
        return res + "  studiert schon relativ lange Physik.";
      default:
        return res + "  keine Ahnung, was der Mann studiert.";
    }
  }
  
  @SuppressWarnings("serial")
  static class NameFormatException extends Exception{
	  public NameFormatException(String message) {
		  super(message);
	  }
  }

  @Override
  public int compareTo(Student o) {
	  if (matnr == o.matnr)
		  return 0;
	  else if(matnr > o.matnr)
		  return 1;
	  else
		  return -1;
	}
}
  
