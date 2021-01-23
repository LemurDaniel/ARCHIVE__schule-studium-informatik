package aufgabe9;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SicherBeenden extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SicherBeenden(){
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if( JOptionPane.showConfirmDialog(SicherBeenden.this, "Wollen sie das Fenster wirklich schlieﬂen", "Beenden", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION );
					System.exit(0);
			}
		});
	}


}
