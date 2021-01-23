package aufgabe1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Artikelverwaltung extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblBezeichnung;
	private JLabel lblPreis;
	private JLabel lblMenge;
	private JLabel lblStatus;
	private JLabel lblArtikelliste;
	private JTextField textFieldBez;
	private JTextField textFieldPreis;
	private JTextField textFieldMenge;
	private JButton btnSpeichern;
	private JButton btnLoeschen;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	
	public Artikelverwaltung() {
		setTitle("Artikeldatenerfassung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblBezeichnung());
		getContentPane().add(getLblPreis());
		getContentPane().add(getLblMenge());
		getContentPane().add(getTextFieldBez());
		getContentPane().add(getTextFieldPreis());
		getContentPane().add(getTextFieldMenge());
		getContentPane().add(getLblStatus());
		getContentPane().add(getBtnSpeichern());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnLoeschen());
		getContentPane().add(getLblArtikelliste());
	}

	public static void main(String[] args) {
		Artikelverwaltung a = new Artikelverwaltung();
		a.setVisible(true);
		a.setBounds(500,250, 450, 300);
		
	}
	private JLabel getLblBezeichnung() {
		if (lblBezeichnung == null) {
			lblBezeichnung = new JLabel("Bezeichnung:");
			lblBezeichnung.setFont(new Font("Dialog", Font.BOLD, 12));
			lblBezeichnung.setBounds(10, 11, 86, 21);
		}
		return lblBezeichnung;
	}
	private JLabel getLblPreis() {
		if (lblPreis == null) {
			lblPreis = new JLabel("Preis:");
			lblPreis.setFont(new Font("Dialog", Font.BOLD, 12));
			lblPreis.setBounds(10, 43, 86, 21);
		}
		return lblPreis;
	}
	private JLabel getLblMenge() {
		if (lblMenge == null) {
			lblMenge = new JLabel("Menge:");
			lblMenge.setFont(new Font("Dialog", Font.BOLD, 12));
			lblMenge.setBounds(10, 75, 86, 21);
		}
		return lblMenge;
	}
	private JTextField getTextFieldBez() {
		if (textFieldBez == null) {
			textFieldBez = new JTextField();
			textFieldBez.setFont(new Font("Dialog", Font.PLAIN, 12));
			textFieldBez.setBounds(106, 12, 186, 20);
			textFieldBez.setColumns(10);
		}
		return textFieldBez;
	}
	private JTextField getTextFieldPreis() {
		if (textFieldPreis == null) {
			textFieldPreis = new JTextField();
			textFieldPreis.setFont(new Font("Dialog", Font.PLAIN, 12));
			textFieldPreis.setBounds(106, 44, 86, 20);
			textFieldPreis.setColumns(10);
		}
		return textFieldPreis;
	}
	private JTextField getTextFieldMenge() {
		if (textFieldMenge == null) {
			textFieldMenge = new JTextField();
			textFieldMenge.setFont(new Font("Dialog", Font.PLAIN, 12));
			textFieldMenge.setBounds(106, 76, 86, 20);
			textFieldMenge.setColumns(10);
		}
		return textFieldMenge;
	}
	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("");
			lblStatus.setFont(new Font("Dialog", Font.BOLD, 12));
			lblStatus.setForeground(Color.RED);
			lblStatus.setBounds(10, 107, 282, 14);
		}
		return lblStatus;
	}
	private JButton getBtnSpeichern() {
		if (btnSpeichern == null) {
			btnSpeichern = new JButton("speichern");
			btnSpeichern.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnSpeichern.setBounds(302, 75, 122, 23);
			btnSpeichern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						speichern();
					}catch(Exception e){
						lblStatus.setText(e.getMessage());
					}
				}
			});
		}
		return btnSpeichern;
	}
	
	private void speichern() throws Exception{
		String bez = textFieldBez.getText();
		
		if(bez.length() < 3) 
			throw new Exception("Bezeichnung muss mind. 3 Zeichen  enthalten");
		
		
		double preis;
		String sPreis = textFieldPreis.getText();
		try {
			preis = Double.parseDouble(sPreis);
			if(preis <=0) throw new Exception("Preis nicht korrekt: " + preis);
		}catch(NumberFormatException e1) {
			throw new Exception("Preis nicht gültig: ("+ e1.getMessage() +")");
		}
		
		
		int menge;
		String sMenge = textFieldMenge.getText();
		try {
			menge = Integer.parseInt(sMenge);
			if(menge <= 0) throw new Exception("Menge nicht korrekt: " + menge);
		}catch(NumberFormatException e2) {
			throw new Exception("Menge nicht gültig: ("+ e2.getMessage() +")");
		}
		
		Artikel a = new Artikel(bez, preis, menge);
		textArea.append(a+"\n");  // to String wird immer automatisch aufgerufen!
		
		textFieldBez.setText(null);
		textFieldMenge.setText(null);
		textFieldPreis.setText(null);
		lblStatus.setText(null);
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 151, 414, 99);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JButton getBtnLoeschen() {
		if (btnLoeschen == null) {
			btnLoeschen = new JButton("l\u00F6schen");
			btnLoeschen.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnLoeschen.setBounds(338, 128, 86, 23);
			btnLoeschen.addActionListener( e -> { 
				textArea.setText(null);
				Artikel.reset();								
			});
		}
		return btnLoeschen;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JLabel getLblArtikelliste() {
		if (lblArtikelliste == null) {
			lblArtikelliste = new JLabel("Artikelliste:");
			lblArtikelliste.setFont(new Font("Dialog", Font.BOLD, 12));
			lblArtikelliste.setBounds(10, 132, 75, 14);
		}
		return lblArtikelliste;
	}
	
}
	
	
	
	
	
class Artikel{
	private static int lfn;
	private int art;
	private String bezeichnung;
	private double preis;
	private int menge;
		
	public Artikel(String bezeichnung, double preis, int menge) {
		this.art = ++lfn;
		this.bezeichnung = bezeichnung;
		this.preis = preis;
		this.menge = menge;
	}
		
	public String toString() {
		return String.format("anr=%d, bez=%s, menge=%d, preis=%.2f", art, bezeichnung, menge, preis);
	}
	
	public static void reset() {
		lfn = 0;
	}
}

