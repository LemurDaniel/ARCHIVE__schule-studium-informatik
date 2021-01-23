package aufgabe1.a0;

public class Student {
  
  private String name;
  private int matnr;
  private Fach fach;



  public String getName() {
    return this.name;
  }

  
  public void setName(String name) {
    this.name = name;
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
}
  
