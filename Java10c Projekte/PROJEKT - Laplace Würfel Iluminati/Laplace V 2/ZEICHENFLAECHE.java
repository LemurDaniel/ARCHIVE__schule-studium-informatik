import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;


/**
 * ZEICHENFLAECHE ist eine Klasse, die beim Instanzieren ein Fenster
 * mit einer Malfl�che vorbereitet. Auf dieser Malfl�che k�nnen
 * einfache Grafiksymbole (Kreis, Dreieck, Viereck, Sechseck)
 * dargestellt werden.
 * 
 * Die Klasse ZEICHENFLAECHE ist sehr stark vereinfacht und speziell f�r
 * das Einstiegsprojekt "Grafikdokument" gedacht. Die Realisierung dieser
 * Klasse entspricht keineswegs einem "vorbildlichen" und problemgerechten
 * Design. Ziel ist es, dass die vom Sch�ler verwendeten Figuren-Klassen
 * so einfach wie nur m�glich gehalten werden k�nnen.
 * Ein Einsatz dieser Klasse ausserhalb des Projekts "Grafikdokument" ist
 * nicht sinnvoll.
 * 
 * Nach einer Idee von Michael K�lling, David J.Barnes.
 * 
 * @author: U.Freiberger 
 * @version: 1.0
 */


public class ZEICHENFLAECHE {
	
	/*
	 * Von der Klasse ZEICHENFLAECHE gibt es nur ein Objekt
	 * zeichenflaeche.
	 * Alle FigurenObjekte benutzen dieses Objekt zur Ausgabe ihrer
	 * Grafiksymbole. Jede Figur muss sich bei ihrer Instanzierung
	 * bei diesem Objekt anmelden und bei Ver�nderungen dieses Objekt
	 * informieren, damit ggf. eine Neuzeichnung erfolgt.
	 * Dieses Objekt wird indirekt beim ersten Anmelden einer Figur am
	 * Fenster erzeugt. Eine direkte Erzeugung �ber den Konstruktor ist
	 * nicht m�glich.
	 */ 
	
	private static ZEICHENFLAECHE zeichenflaeche;
	
	private JFrame fenster;
	private MalPanel malPanel;
	private BufferedImage  hintergrundbild;
	private Graphics2D gDC;
	private java.util.List<Grafiksymbol> alleSymbole;
	
	
	/**
	 * Konstruktor der Klasse ZEICHENFLAECHE 
	 * 
	 * Erstellt ein Fenster (JFrame), dessen Innenbereich von einer
	 * wei�en Malfl�che ausgef�llt wird.
	 * Als Malfl�che dient ein Objekt der Unterklasse MalPanel von JPanel.
	 * Das Zeichnen erfolgt nicht direkt auf dieser Malfl�che sondern
	 * auf einem Hintergrundbild, das dann zur Anzeige im Ganzen
	 * auf die Malfl�che kopiert wird.
	 */
	private ZEICHENFLAECHE()
	{
		GraphicsConfiguration gfxConf;

		fenster = new JFrame("Grafikdokument");
		fenster.setLocation(50,50);
		fenster.setResizable(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		JPanel contentPane = (JPanel) fenster.getContentPane();
		
		malPanel = new MalPanel();
		malPanel.setPreferredSize(new Dimension(1600,600));
		contentPane.add(malPanel);
		fenster.pack();
		
		gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		hintergrundbild = gfxConf.createCompatibleImage(malPanel.getWidth(), malPanel.getHeight());
		gDC = hintergrundbild.createGraphics();	
		loeschen();
		
		alleSymbole = new ArrayList<Grafiksymbol>();
		
		fenster.setVisible(true);		
		fenster.toFront();
	}
	
		
	/**
	 * Fabrikmethode. Liefert eine Referenz auf das einzige Objekt der Klasse
	 * ZEICHENFLAECHE zur�ck. Wenn es noch nicht existiert, dann wird es erzeugt.
	 */
	public static ZEICHENFLAECHE ObjektGeben()
	{
		if (zeichenflaeche == null)
			zeichenflaeche = new ZEICHENFLAECHE();

		return zeichenflaeche;
	}

		
	/**
	 * Jede Figur muss sich beim Grafikfenster anmelden,
	 * damit ihr Grafiksymbol in die Liste der zu zeichnenden
	 * Symbole aufgenommen wird.
	 * 
	 * @return eindeutige ID des zugeordneten Grafiksymbols
	 */
	public int anmelden(SymbolArt was)
	{
		alleSymbole.add(new Grafiksymbol(was));
		return alleSymbole.size();
	}

	
	/**
	 * Grafiksymbol vor der Zeichnung anpassen
	 *  
	 * Wenn sich an einer Figur etwas �ndert, dann muss die Figur
	 * diese �nderung seinem Grafiksysmbol mitteilen. Das Grafiksymbol
	 * veranlasst dann, wenn erforderlich, ein neues Zeichnen.
	 * Ein sinngerechterer Name f�r diese Methode w�re �nderungMitteilen().
	 */
	public void zeichnen(int kennung, int aktX, int aktY, int aktBreite, int aktHoehe, String aktFarbe, boolean aktSichtbar)
	{
		if (kennung>=1 && kennung<=alleSymbole.size())
		{
			Grafiksymbol symbol = alleSymbole.get(kennung-1);
			symbol.x = Math.max(aktX,0);
			symbol.y = Math.max(aktY,0);
			symbol.w = Math.max(aktBreite,0);
			symbol.h = Math.max(aktHoehe,0);
			symbol.farbe = aktFarbe;
			symbol.sichtbar = aktSichtbar;
			alleZeichnen();
		}
	}
	
	
	/*
	 * Zeichnet alle Grafiksymbole auf das Hintergrundbild
	 */
	private void alleZeichnen() {
		loeschen();
		for (Grafiksymbol symbol : alleSymbole)
			if (symbol.sichtbar)
				symbol.draw();
		malPanel.repaint();
	}
	

	/*
	 * Das gesamte Hintergrundbild l�schen
	 */
	private void loeschen()
	{
		Color aktuell = gDC.getColor();
		gDC.setColor(Color.WHITE);
		gDC.fillRect(0,0,hintergrundbild.getWidth(), hintergrundbild.getHeight());		
		gDC.setColor(aktuell);
	}
	
	
	/*
	 * Die Zeichenfarbe f�r das Hintergrundbild setzen
	 * 
	 * @param farbName
	 * 			Name der Zeichenfarbe
	 */
	public void zeichenfarbeSetzen(String farbname) {
		if (farbname.equals("rot")) {
			gDC.setColor(Color.red);
		} else if (farbname.equals("gruen")) {
			gDC.setColor(Color.green);
		} else if (farbname.equals("blau")) {
			gDC.setColor(Color.blue);
		} else if (farbname.equals("gelb")) {
			gDC.setColor(Color.yellow);
		} else if (farbname.equals("cyan")) {
			gDC.setColor(Color.cyan);
		} else if (farbname.equals("magenta")) {
			gDC.setColor(Color.magenta);
		} else if (farbname.equals("schwarz")) {
			gDC.setColor(Color.black);
		} else if (farbname.equals("weiss")) {
			gDC.setColor(Color.white);
		} else if (farbname.equals("hellgelb")) {
			gDC.setColor(new Color(255,255,128));
		} else if (farbname.equals("hellgruen")) {
			gDC.setColor(new Color(128,255,128));
		} else if (farbname.equals("orange")) {
			gDC.setColor(new Color(255,128,0));
		} else if (farbname.equals("braun")) {
			gDC.setColor(new Color(128,64,0));
        } else if (farbname.equals("grau")){
            gDC.setColor(new Color(128,128,128));
        } else if (farbname.equals("dunkelrot")){
            gDC.setColor(new Color(150,0,0));
        } else if (farbname.equals("hellgrau")){
            gDC.setColor(new Color(170,170,170));
		} else {
			gDC.setColor(Color.black);
		}
	}
	
	
	/**
	 * Aufz�hltyp f�r die m�glichen Grundfigurarten in der Darstellung
	 */
	public static enum SymbolArt {artKreis, artDreieck, artViereck, artSechseck; }
	
	
	/*
	 * Interne Klasse MalPanel f�r die GUI-Komponente, die im Fenster 
	 * angezeigt wird. Diese Klasse ist eine Unterklasse von JPanel. 
	 * Beim paint-Event wird das ganze Hintergrundbild auf das Panel
	 * gezeichnet.
	 */
	private class MalPanel extends JPanel
	{
		public void paint(Graphics g)
		{
			g.drawImage(hintergrundbild, 0, 0, null);
		}
	}

	
	/*
	 * Interne Klasse Grafiksymbol f�r die Grafikdarstellung der Figuren, die auf der
	 * Malfl�che dargestellt werden.
	 * Zeichnet die Grafiksymbole auf das Hintergrundbild
	 */
	private class Grafiksymbol {

		int x, y, w, h;
		SymbolArt sArt;
		String farbe;
		boolean sichtbar;
		
		// Konstruktor
		Grafiksymbol(SymbolArt art)
		{
			x=0; y=0; w=0; h=0;
			sArt = art;
			farbe = "weiss";
			sichtbar = false;
		}
		
		// zeichnet das Grafiksymbol in das Hintergrundbild
		public void draw() {
			zeichenfarbeSetzen(farbe);
			if (sArt == SymbolArt.artKreis) {
				gDC.fillOval(x, y, w, h);
			} else if (sArt == SymbolArt.artDreieck) {
				int[] punkteX = { x, x + (w / 2), x - (w / 2) };
				int[] punkteY = { y, y + h, y + h };
				gDC.fillPolygon(punkteX, punkteY, 3);		
			} else if (sArt == SymbolArt.artViereck) {
				gDC.fillRect(x, y, w, h);
			} else if (sArt == SymbolArt.artSechseck) {
				System.out.println("ja");
				int[] punkteX = { x + (int) Math.round(0.2113*w), x + (int) Math.round(0.7887*w),
								  x + w, x + (int) Math.round(0.7887*w),
								  x + (int) Math.round(0.2113*w), x };
				int[] punkteY = { y, y, y + (h / 2), y + h, y + h, y + (h / 2) };
				System.out.println(punkteX.toString());
				System.out.println(punkteY.toString());
				gDC.fillPolygon(punkteX, punkteY, 6);		
			}
			
		}
	}
	

}
