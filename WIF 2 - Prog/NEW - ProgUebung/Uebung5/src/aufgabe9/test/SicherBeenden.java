package aufgabe9.test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SicherBeenden extends JFrame{
	
	public SicherBeenden() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		WindowAdapter wa = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opt = JOptionPane.showConfirmDialog(SicherBeenden.this, "Möchten sie das Fenster wirklich schließen?", "Beenden",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (opt == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(SicherBeenden.this, "Tschüss ...", "Bye", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
		};
		addWindowListener(wa);
	}

	
	
	// Nur zu Testzwecken
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SicherBeenden b = new SicherBeenden();
//		b.setVisible(true);
//		b.setBounds(500, 200, 800, 600);
	}

}
