package aufgabe3;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import aufgabe1.Artikel;

@SuppressWarnings("serial")
public class Artikelverwaltung extends JFrame {
	
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
	private JButton btnUpdate;
	private JList<Artikel> list;

	
	public Artikelverwaltung() {
		setTitle("Artikeldatenerfassung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getRootPane().setDefaultButton(getBtnSpeichern());
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
		getContentPane().add(getBtnUpdate());
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
			btnSpeichern.addActionListener( al -> {
				try {
					lblStatus.setText(null);
					if(textFieldBez.getText().equals("TestList")) {
						((MyModel<Artikel>)list.getModel()).add(new Artikel.TestArtikelListe().getArtikel());
						return;
					}
					
					((MyModel<Artikel>)list.getModel()).add(new Artikel(textFieldBez.getText(), textFieldPreis.getText(), textFieldMenge.getText()));
					list.setSelectedIndex(list.getModel().getSize()-1);
				}catch(Exception e) {
					lblStatus.setText(e.getMessage());
				}
			});
		}
		return btnSpeichern;
	}

	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 151, 414, 99);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JButton getBtnLoeschen() {
		if (btnLoeschen == null) {
			btnLoeschen = new JButton("l\u00F6schen");
			btnLoeschen.setEnabled(false);
			btnLoeschen.setFont(new Font("Courier New", Font.PLAIN, 12));
			btnLoeschen.setBounds(338, 128, 86, 23);
			btnLoeschen.addActionListener( al->{
				lblStatus.setText(null);
				((MyModel<Artikel>)list.getModel()).remove(list.getSelectedIndex()); 
			});
		}
		return btnLoeschen;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("update");
			btnUpdate.setEnabled(false);
			btnUpdate.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnUpdate.setBounds(302, 98, 122, 23);
			btnUpdate.addActionListener(al->{
				try {
					lblStatus.setText(null);
					Artikel artk = list.getModel().getElementAt(list.getSelectedIndex());
					artk.setBezeichnung(textFieldBez.getText());
					artk.setPreis(textFieldPreis.getText());
					artk.setMenge(textFieldMenge.getText());
				}catch(Exception e) {
					lblStatus.setText(e.getMessage());
				}
			});
		}
		return btnUpdate;
	}
	private JLabel getLblArtikelliste() {
		if (lblArtikelliste == null) {
			lblArtikelliste = new JLabel("Artikelliste:");
			lblArtikelliste.setFont(new Font("Dialog", Font.BOLD, 12));
			lblArtikelliste.setBounds(10, 132, 75, 14);
		}
		return lblArtikelliste;
	}
	private JList<Artikel> getList() {
		if (list == null) {
			list = new JList<>(new MyModel<Artikel>());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setFont(new Font("Dialog", Font.BOLD, 12));
			list.addListSelectionListener( lsl ->{
				if(lsl.getValueIsAdjusting()) return;
				
				btnLoeschen.setEnabled(list.getSelectedIndex()!=-1);
				btnUpdate.setEnabled(list.getSelectedIndex()!=-1);
				
				if(list.getSelectedIndex()==-1) return;
				Artikel artk = list.getModel().getElementAt(list.getSelectedIndex());
				textFieldBez.setText(artk.getBezeichnung());
				textFieldMenge.setText(artk.getMenge()+"");
				textFieldPreis.setText(String.format("%.2f", artk.getPreis()).replaceAll(",", "."));
			});

		}
		return list;
	}
}
	

