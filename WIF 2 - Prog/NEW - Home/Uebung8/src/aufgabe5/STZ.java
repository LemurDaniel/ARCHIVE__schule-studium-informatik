package aufgabe5;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class STZ extends JFrame {
	
	private static STZ instance;
	
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblEcts;
	private JLabel lblSemester;
	private JTextField tfMat;
	private JTextField tfName;
	private JTextField tfECTS;
	private JComboBox<Integer> comboBox;
	private JButton btnErstellen;
	private JButton btnAbrechen;
	private JLabel lblInfo;
	
	private Studentendaten std;
	
	public static STZ instance(Studentendaten std) {
		if(instance == null) instance = new STZ(std);
		else instance.setTableModel(std);
		return instance;
	}
	
	public void setTableModel(Studentendaten std) {
		this.std = std;
	}
	
	private STZ(Studentendaten std) {	
		this.std = std;
		
		setTitle("Neuer Student");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 310, 280);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblName());
		getContentPane().add(getLblEcts());
		getContentPane().add(getLblSemester());
		getContentPane().add(getTfMat());
		getContentPane().add(getTfName());
		getContentPane().add(getTfECTS());
		getContentPane().add(getComboBox());
		getContentPane().add(getBtnErstellen());
		getContentPane().add(getBtnAbrechen());
		getContentPane().add(getLblInfo());
		
		
		CaretListener cl = a ->{
			if(tfName.getText().length()!=0 && tfECTS.getText().length()!=0 && tfMat.getText().length()==5)
				btnErstellen.setEnabled(true);
			else
				btnErstellen.setEnabled(false);
		};
		tfECTS.addCaretListener(cl);
		tfMat.addCaretListener(cl);
		tfName.addCaretListener(cl);
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				STZ s = new STZ(new Studentendaten());
				s.setBounds(500, 300, 310, 280);
				s.setVisible(true);
			}
			
		});
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Matnr:");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 11, 80, 25);
		}
		return lblNewLabel;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
			lblName.setFont(new Font("Dialog", Font.BOLD, 13));
			lblName.setBounds(10, 47, 80, 25);
		}
		return lblName;
	}
	private JLabel getLblEcts() {
		if (lblEcts == null) {
			lblEcts = new JLabel("ECTS:");
			lblEcts.setFont(new Font("Dialog", Font.BOLD, 13));
			lblEcts.setBounds(10, 83, 80, 25);
		}
		return lblEcts;
	}
	private JLabel getLblSemester() {
		if (lblSemester == null) {
			lblSemester = new JLabel("Semester:");
			lblSemester.setFont(new Font("Dialog", Font.BOLD, 13));
			lblSemester.setBounds(10, 119, 80, 25);
		}
		return lblSemester;
	}
	private JTextField getTfMat() {
		if (tfMat == null) {
			tfMat = new JTextField();
			tfMat.setBounds(100, 11, 86, 28);
			tfMat.setColumns(10);
			tfMat.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(tfMat.getText().length()>Studentendaten.MATRIKELNUMMER_LAENGE) tfMat.setText(tfMat.getText().substring(0, Studentendaten.MATRIKELNUMMER_LAENGE));
					tfMat.setText(tfMat.getText().replaceAll("\\D*", ""));
				}
	
			});
		}
		return tfMat;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(100, 50, 186, 28);
		}
		return tfName;
	}
	private JTextField getTfECTS() {
		if (tfECTS == null) {
			tfECTS = new JTextField();
			tfECTS.setColumns(10);
			tfECTS.setBounds(100, 86, 86, 28);
		}
		return tfECTS;
	}
	private JComboBox<Integer> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<>();
			comboBox.setBounds(100, 122, 50, 25);
			for(int i=1; i<=Studentendaten.MAX_SEMESTER; i++) {
				comboBox.addItem(i);
			}
		}
		return comboBox;
	}
	
	
	private JButton getBtnErstellen() {
		if (btnErstellen == null) {
			btnErstellen = new JButton("erstellen");
			btnErstellen.setEnabled(false);
			btnErstellen.setFont(new Font("Dialog", Font.BOLD, 13));
			btnErstellen.setBounds(10, 195, 128, 25);
			btnErstellen.addActionListener(a->{
				int mat, ects;
				try {
					lblInfo.setText(null);
					mat = Integer.parseInt(tfMat.getText());
					ects = Integer.parseInt(tfECTS.getText());
					std.add(new Student(mat, ects, (Integer)comboBox.getSelectedItem(), tfName.getText()));
					this.dispose();
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}
			});
		}
		return btnErstellen;
	}
	private JButton getBtnAbrechen() {
		if (btnAbrechen == null) {
			btnAbrechen = new JButton("abrechen");
			btnAbrechen.setFont(new Font("Dialog", Font.BOLD, 13));
			btnAbrechen.setBounds(158, 195, 128, 25);
			btnAbrechen.addActionListener(al-> this.dispose());
		}
		return btnAbrechen;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setForeground(Color.RED);
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 13));
			lblInfo.setBounds(10, 159, 276, 25);
		}
		return lblInfo;
	}
}
