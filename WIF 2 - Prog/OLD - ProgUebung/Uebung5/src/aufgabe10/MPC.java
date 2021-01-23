package aufgabe10;

import javax.swing.*;

import java.awt.event.*;

class MPC extends JFrame {
	private static final long serialVersionUID = 1L;
	private final static String lblPrefix = "Zählerstand: ";
	private final static String titel = "Multi Purpose Counter";
	private final JLabel lblText;
	private final JButton cbCount;
	private int numClicks = 0;
	private final byte maxIcon = 3;
	private final ImageIcon[] icons;
	private final JLabel lblIcon;
	private byte iconInd = 0;

	MPC() {
		// das Rahmenfenster erhält einen Titel.
		setTitle(titel);
		getContentPane().setLayout(null);

		// Icons erzeugen // Liegen im Package bilder
		icons = new ImageIcon[maxIcon];
		icons[0] = new ImageIcon("G:/Nicht verschlüsselt/WIF - Studium/Studium/WIF 2/Prog 2/weiteres/duke.gif");
		icons[1] = new ImageIcon("G:/Nicht verschlüsselt/WIF - Studium/Studium/WIF 2/Prog 2/weiteres/fight.gif");
		icons[2] = new ImageIcon("G:/Nicht verschlüsselt/WIF - Studium/Studium/WIF 2/Prog 2/weiteres/snooze.gif");

		// JLabel mit Icon initialisieren
		lblIcon = new JLabel(icons[0]);
		lblIcon.setBounds(241, 11, 114, 119);
		lblIcon.setToolTipText("Label mit Icon");

		// Das Icon-Label wird auf den JFrame gesetzt.
		getContentPane().add(lblIcon);

		// JButton-Komponente initialisieren
		cbCount = new JButton("Zählerstand erhöhen");
		cbCount.setBounds(10, 28, 208, 25);
		getContentPane().add(cbCount);
		//Tastenkürzel (Mnemonics) setzen (setMnemonic(char))
		//KeyEvent
		cbCount.setMnemonic(KeyEvent.VK_A); // geht mit ALT und a

		// Tatsächliche Klassen von rootPane, contentPane und glassPane
		// ermitteln --> alles JPanel
		// System.out.println(getRootPane().getContentPane().getClass().getName());
		// System.out.println(cont.getClass().getName());
		// System.out.println(getRootPane().getGlassPane().getClass().getName());

		// Der Schalter kann als Default Button auch mit Enter ausgelöst werden.
		getRootPane().setDefaultButton(cbCount); //Top Level Container unterhalb von JFrame und oberhalb von ContentPane

		// Quick-Info zum Befehlsschalter
		cbCount.setToolTipText("Befehlsschalter");

		// JLabel-Komponente mit Text initialisieren
		lblText = new JLabel(lblPrefix + "0");
		lblText.setBounds(10, 51, 153, 22);
		getContentPane().add(lblText);
		lblText.setToolTipText("Label mit Text");

		// Für den JButton wird ein ActionListener registriert.
		// Dazu wird eine anonyme Klasse an Ort und Stelle definiert.
		cbCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numClicks++;
				lblText.setText(lblPrefix + numClicks);
				if (iconInd < maxIcon - 1)
					iconInd++;
				else
					iconInd = 0;
				lblIcon.setIcon(icons[iconInd]);
			}
		});

		// Seit Java 8 lässt sich ein Lambda-Ausdruck verwenden:
		// cbCount.addActionListener(e -> {
		// numClicks++;
		// lblText.setText(lblPrefix + numClicks);
		// if (iconInd < maxIcon-1)
		// iconInd++;
		// else
		// iconInd = 0;
		// lblIcon.setIcon(icons[iconInd]);
		// });

		// Der JFrame erhält einen Event Handler für Fenster-Ereignisse.
		addWindowListener(new WiLi());

		// Diese Methode verhindert ein Schließen unter Missachtung des
		// WindowListeners
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// initiale JFrame-Größe festlegen
		setSize(381, 180);

		// JFrame anzeigen
		setVisible(true);
	}

	public static void main(String[] args) {
		new MPC();
	}

	// Der WindowListener für den JFrame (= Top Level Container) wird als innere
	// Klasse definiert.
	private class WiLi extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			if (JOptionPane.showConfirmDialog(e.getWindow(),
					"Wollen Sie nach " + numClicks + " Klicks wirklich schon aufhören?", titel,
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
}
