package aufgabe3;

public class Auffangbecken extends Produkt {

	 private int volumen;

	 public Auffangbecken(String name, int tiefe, int hoehe, int breite, int gewicht, int volumen) {
		 super(name, tiefe, hoehe, breite, gewicht);
		 this.volumen = volumen;
	 }
}
