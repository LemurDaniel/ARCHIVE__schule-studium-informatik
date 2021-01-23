package aufgabe10;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Zaehler extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCounter;
	private JLabel lblCounter;
	private JLabel lblBild;
	private int counter = 0;
	
	public static void main(String[] args) {
		Zaehler z = new Zaehler();
		z.setVisible(true);
		z.setBounds(500, 500, 630, 213);
	}
	
	public Zaehler() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Wollen sie nach " + counter + " Klicks schon aufhören?", "Multi Purpose Counter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
					System.exit(0);
			}
		});
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		getContentPane().setLayout(null);
		getContentPane().add(getBtnCounter());
		getContentPane().add(getLblCounter());
		getContentPane().add(getLblBild());
		
		this.rootPane.setDefaultButton(getBtnCounter());
	}

	private JButton getBtnCounter() {
		if (btnCounter == null) {
			btnCounter = new JButton("Z\u00E4hlerstand erh\u00F6hen");
			btnCounter.setFont(new Font("Dialog", Font.BOLD, 12));
			btnCounter.setBounds(100, 29, 300, 40);
			btnCounter.addActionListener( e -> count() );
		}
		return btnCounter;
	}
	private JLabel getLblCounter() {
		if (lblCounter == null) {
			lblCounter = new JLabel("Z\u00E4hlerstand: 0");
			lblCounter.setFont(new Font("Dialog", Font.BOLD, 12));
			lblCounter.setBounds(110, 80, 100, 22);
		}
		return lblCounter;
	}
	private JLabel getLblBild() {
		if (lblBild == null) {
			lblBild = new JLabel("");
			lblBild.setIcon(new ImageIcon("G:\\Nicht verschl\u00FCsselt\\WIF - Studium\\Studium\\WIF 2\\Prog 2\\weiteres\\duke.gif"));
			lblBild.setBounds(428, 29, 176, 131);
		}
		return lblBild;
	}
	
	private void count() {
		counter++;
		lblCounter.setText("Z\u00E4hlerstand: "+counter);
		String path = "G:\\Nicht verschl\u00FCsselt\\WIF - Studium\\Studium\\WIF 2\\Prog 2\\weiteres\\";
		String image = "";
		
		switch(counter%3) {
		case 0: image = "duke.gif"; break;
		case 1: image = "fight.gif"; break;
		case 2: image = "snooze.gif"; break;
		}
		
		lblBild.setIcon(new ImageIcon(path+image));
	}

}
