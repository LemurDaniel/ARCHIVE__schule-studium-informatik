import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;


//pic erbt von Panel - spart eine Klasse
public class PicPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	BufferedImage pic;  //BufferedImage zum Speichern des Bildes

	public static void main(String[] args) {
	   new PicPanel();
	}
	
	public PicPanel(){
		
		//Bild in eigenem Unterordner. Unterordner befindet sich im Verzeichnis der Class-Dateien
		//Mit URL laden funktioniert immer auch im JAR, etc.
		URL pic_url = this.getClass().getClassLoader().getResource("pic/test.jpg"); //kein Slash vor dem Unterordner!
		
		//Bild laden mit ImageIO
		try {
			pic = ImageIO.read(pic_url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Frame anlegen - JPanel(dieses) reinpacken.
		JFrame frame = new JFrame("Bild in Frame die x-te");
		frame.setLocation(100,100);
		frame.setSize(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true); //wichtig - setVisible(true) zuletzt
	}

	//paintComponent überschreiben, Image zeichnen, super-Aufruf nicht vergessen
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(pic,20,20,this);
		
	}

}