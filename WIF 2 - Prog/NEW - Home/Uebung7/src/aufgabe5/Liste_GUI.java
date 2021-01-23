package aufgabe5;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Liste_GUI extends JFrame {
	private JLabel lblNewLabel;
	private JLabel lblSumme;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnErstellen;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private Liste<Integer> liste = new Liste<>(3);
	
	public Liste_GUI() {
		setTitle("Liste mit Typ Integer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblSumme());
		getContentPane().add(getTextField());
		getContentPane().add(getTextField_1());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getBtnErstellen());
		getContentPane().add(getScrollPane());
		setBounds(500,250,355,215);
	}
	public static void main(String args[]) {
		Liste_GUI t = new Liste_GUI();
		t.setVisible(true);
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Zahl:");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 30, 101, 22);
		}
		return lblNewLabel;
	}
	private JLabel getLblSumme() {
		if (lblSumme == null) {
			lblSumme = new JLabel("Summe:");
			lblSumme.setFont(new Font("Dialog", Font.BOLD, 13));
			lblSumme.setBounds(10, 62, 101, 22);
		}
		return lblSumme;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setFont(new Font("Dialog", Font.BOLD, 13));
			textField.setBounds(121, 62, 112, 22);
			textField.setColumns(10);
		}
		return textField;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Dialog", Font.BOLD, 13));
			textField_1.setColumns(10);
			textField_1.setBounds(121, 30, 112, 22);
		}
		return textField_1;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("erfassen");
			btnNewButton.setFont(new Font("Dialog", Font.BOLD, 13));
			btnNewButton.setBounds(243, 31, 89, 23);
			btnNewButton.addActionListener(l->{
				if(liste.getCount()==0)
					textArea.setText(null);
				
				try {
					liste.add( Integer.parseInt(textField_1.getText()));
					if(textArea.getText().length()!=0)
						textArea.append(", ");				
					textArea.append(textField_1.getText());
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(Liste_GUI.this, "Fehler");
				}
			});
		}
		return btnNewButton;
	}
	private JButton getBtnErstellen() {
		if (btnErstellen == null) {
			btnErstellen = new JButton("erstellen");
			btnErstellen.setFont(new Font("Dialog", Font.BOLD, 13));
			btnErstellen.setBounds(243, 63, 89, 23);
			btnErstellen.addActionListener(l->{
				int summe = 0;
				for(int i=liste.getCount(); i>0; i--) {
					summe += liste.remove();
				}
				textField.setText(summe+"");
			});
		}
		return btnErstellen;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 97, 322, 71);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
}
