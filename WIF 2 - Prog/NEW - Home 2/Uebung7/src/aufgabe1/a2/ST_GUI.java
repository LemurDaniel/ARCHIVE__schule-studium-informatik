package aufgabe1.a2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;

import aufgabe1.a1.Fach;
import aufgabe1.a1.Student;

import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class ST_GUI extends JFrame {
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblFach;
	private JTextField tfMatNr;
	private JTextField tfName;
	private JComboBox<Fach> comboBox;
	private JButton btnErfassen;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	private Student student;
	private JLabel lblFehler;

	public ST_GUI() {
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblName());
		getContentPane().add(getLblFach());
		getContentPane().add(getTfMatNr());
		getContentPane().add(getTfName());
		getContentPane().add(getComboBox());
		getContentPane().add(getBtnErfassen());
		getContentPane().add(getScrollPane_1());
		getContentPane().add(getLblFehler());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ST_GUI t = new ST_GUI();
		t.setBounds(500, 250, 475, 400);
		t.setVisible(true);
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Matrikelnummer:");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setBounds(37, 32, 133, 23);
		}
		return lblNewLabel;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
			lblName.setFont(new Font("Dialog", Font.BOLD, 13));
			lblName.setBounds(37, 66, 133, 23);
		}
		return lblName;
	}
	private JLabel getLblFach() {
		if (lblFach == null) {
			lblFach = new JLabel("Fach:");
			lblFach.setFont(new Font("Dialog", Font.BOLD, 13));
			lblFach.setBounds(37, 100, 133, 23);
		}
		return lblFach;
	}
	private JTextField getTfMatNr() {
		if (tfMatNr == null) {
			tfMatNr = new JTextField();
			tfMatNr.setFont(new Font("Dialog", Font.BOLD, 13));
			tfMatNr.setBounds(180, 31, 100, 23);
			tfMatNr.setColumns(10);
		}
		return tfMatNr;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setFont(new Font("Dialog", Font.BOLD, 13));
			tfName.setColumns(10);
			tfName.setBounds(180, 68, 244, 23);
		}
		return tfName;
	}
	private JComboBox<Fach> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Fach>();
			comboBox.setFont(new Font("Dialog", Font.BOLD, 13));
			comboBox.setBounds(180, 102, 244, 23);
			comboBox.setModel( new DefaultComboBoxModel<Fach>(Fach.values()) );
			comboBox.setSelectedIndex(0);
		}
		return comboBox;
	}
	private JButton getBtnErfassen() {
		if (btnErfassen == null) {
			btnErfassen = new JButton("erfassen");
			btnErfassen.setFont(new Font("Dialog", Font.BOLD, 13));
			btnErfassen.setBounds(180, 136, 133, 23);
			btnErfassen.addActionListener( e -> {
				int matnr;
				String name = tfName.getText();		
				lblFehler.setText(null);
				
				try {
					matnr = Integer.parseInt(tfMatNr.getText());
					
					if(name.length() < 1 || name.matches(".*\\d+.*") ) 
						throw new Exception("'"+name+ "' ist kein gültiger Name");
					if(!name.matches(".*\\s+.*"))
						throw new Exception("Bitte vor und Nachname");
					
				}catch(NumberFormatException nfe) {
					lblFehler.setText("'"+tfMatNr.getText() +"' ist keine gültige Nummer");
					return;
				}catch(Exception e1) {
					lblFehler.setText(e1.getMessage());
					return;
				}
				

				
				student = new Student();
				student.setName(name);
				student.setMatnr(matnr);
				student.setFach( (Fach)comboBox.getSelectedItem() );
				textArea.append(student.toString()+"\n");
				
				tfName.setText(null);
				tfMatNr.setText(null);
				comboBox.setSelectedIndex(0);
			});
		}
		return btnErfassen;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		}
		return textArea;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(37, 197, 387, 153);
			scrollPane.setViewportView(getTextArea());
			scrollPane.setBorder(new TitledBorder(null, "Studenten", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 13), java.awt.Color.BLACK));
		}
		return scrollPane;
	}
	private JLabel getLblFehler() {
		if (lblFehler == null) {
			lblFehler = new JLabel("");
			lblFehler.setFont(new Font("Dialog", Font.BOLD, 13));
			lblFehler.setBounds(37, 170, 399, 16);
			lblFehler.setForeground(Color.red);
		}
		return lblFehler;
	}
}
