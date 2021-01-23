package zvPaket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class J150523 extends JFrame {
	private JLabel lblinfo;
	private JButton btnCheck;
	private JTextField tFpassword;
	
	public J150523() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Passwort Checker");
		getContentPane().setLayout(null);
		getContentPane().add(getLblinfo());
		getContentPane().add(getBtnCheck());
		getContentPane().add(getTFpassword());
	}

	public static void main(String[] args) {
		J150523 fenster = new J150523();
		fenster.setVisible(true);
		fenster.setBounds(500, 250, 430, 220);
	}
	private JLabel getLblinfo() {
		if (lblinfo == null) {
			lblinfo = new JLabel("");
			lblinfo.setForeground(Color.RED);
			lblinfo.setFont(new Font("Dialog", Font.BOLD, 13));
			lblinfo.setBounds(46, 117, 326, 24);
		}
		return lblinfo;
	}
	private JButton getBtnCheck() {
		if (btnCheck == null) {
			btnCheck = new JButton("Passwortcheck");
			btnCheck.setFont(new Font("Dialog", Font.BOLD, 13));
			btnCheck.setBounds(235, 81, 137, 24);
			btnCheck.addActionListener(arg0 -> {
				String password = tFpassword.getText();
				if(password.length() == 0)
					lblinfo.setText("Bitte gültiges Passwort eingeben!");
				else if(password.length() < 8)
					lblinfo.setText("Die Mindestlänge beträgt 8 Zeichen!");
				else if(!Character.isUpperCase(password.charAt(0)))
					lblinfo.setText("Der erste Buchstabe muss Großgeschrieben sein!");
				else
					lblinfo.setText("Das Passwort entspricht den Anforderungen.");
			});
		}
		return btnCheck;
	}
	private JTextField getTFpassword() {
		if (tFpassword == null) {
			tFpassword = new JTextField();
			tFpassword.setFont(new Font("Dialog", Font.BOLD, 13));
			tFpassword.setBounds(46, 37, 326, 33);
			tFpassword.setColumns(10);
		}
		return tFpassword;
	}
}
