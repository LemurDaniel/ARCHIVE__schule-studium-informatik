
 import java.awt.*;
 import java.io.IOException;
 import java.net.URL;
 import javax.imageio.ImageIO;

public class Bild
{
    //private static final long serialVersionUID = 1L;
    public Image pic; 
    public int x, y;
    
    /**
     * Konstruktor für Bild
     */
    public Bild (String bildadresse, int xx, int yy) 
    {
        //Bild in eigenem Unterordner. Unterordner befindet sich im Verzeichnis der Class-Dateien
        //Mit URL laden funktioniert immer auch im JAR, etc.
        URL pic_url = this.getClass().getClassLoader().getResource(bildadresse); //kein Slash vor dem Unterordner!
        //Bild laden mit ImageIO
        try {
            pic = ImageIO.read(pic_url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        positioniereBild(pic,xx,yy);
    }
    public void zeichnen()
    {
        positioniereBild(pic, x,y);
    }
    public void positioniereBild(Image pic, int xx, int yy)
    {
        x=xx;
        y=yy;
        ZEICHENFENSTER.gibFenster().zeichneBild(pic, x, y);
        ZEICHENFENSTER.gibFenster().zeige(); // anzeigen
    }
    
}
