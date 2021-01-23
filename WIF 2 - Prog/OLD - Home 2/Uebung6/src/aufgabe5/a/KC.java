package aufgabe5.a;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;

public class KC extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private JLabel lblWinkel;
	private JTextField textField;
	private JRadioButton rdbtnRelativ;
	private JRadioButton rdbtnAbsolut;
	private JButton btnKommando;

	private RobotGUI rGUI;
	private JLabel lblInfo;
	private JScrollPane scrollPane;
	
	public KC() {
		rGUI = new RobotGUI();
		rGUI.setVisible(true);
		rGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		getContentPane().add(getLblWinkel());
		getContentPane().add(getTextField());
		getContentPane().add(getRdbtnRelativ());
		getContentPane().add(getRdbtnAbsolut());
		getContentPane().add(getBtnKommando());
		getContentPane().add(getLblInfo());
		getContentPane().add(getScrollPane());
	}
	
	
	public static void main(String args[]) {
		KC kc = new KC();
		kc.setBounds(500, 250, 500, 300);
		kc.setVisible(true);
	}
	
	private JList<String> getList() {
		if (list == null) {
			list = new JList<>(rGUI.getDLM());
			list.setSelectedIndex(0);
			list.setFont(new Font("Dialog", Font.BOLD, 12));
			list.setSelectedIndex(0);
			}
		return list;
	}
	private JLabel getLblWinkel() {
		if (lblWinkel == null) {
			lblWinkel = new JLabel("Winkel:");
			lblWinkel.setFont(new Font("Dialog", Font.BOLD, 12));
			lblWinkel.setBounds(200, 43, 60, 14);
		}
		return lblWinkel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Dialog", Font.BOLD, 12));
			textField.setBounds(253, 41, 86, 20);
			textField.setColumns(10);
			
		}
		return textField;
	}
	private JRadioButton getRdbtnRelativ() {
		if (rdbtnRelativ == null) {
			rdbtnRelativ = new JRadioButton("relativ");
			rdbtnRelativ.setFont(new Font("Dialog", Font.BOLD, 12));
			rdbtnRelativ.setBounds(210, 110, 109, 23);
			rdbtnRelativ.addActionListener( e -> rdbtnAbsolut.setSelected(false) );
		}
		return rdbtnRelativ;
	}
	private JRadioButton getRdbtnAbsolut() {
		if (rdbtnAbsolut == null) {
			rdbtnAbsolut = new JRadioButton("absolut");
			rdbtnAbsolut.setSelected(true);
			rdbtnAbsolut.setFont(new Font("Dialog", Font.BOLD, 12));
			rdbtnAbsolut.setBounds(210, 84, 109, 23);
			rdbtnAbsolut.addActionListener( e -> rdbtnRelativ.setSelected(false) );
		}
		return rdbtnAbsolut;
	}
	private JButton getBtnKommando() {
		if (btnKommando == null) {
			btnKommando = new JButton("Kommando geben");
			btnKommando.addActionListener(e -> {
				try {
					lblInfo.setText(null);
					rGUI.command(list.getSelectedIndex(), textField.getText(), rdbtnRelativ.isSelected());
				}catch(Exception e1) {
					lblInfo.setText(e1.getMessage());
				}
			});
			btnKommando.setFont(new Font("Dialog", Font.BOLD, 12));
			btnKommando.setBounds(55, 212, 284, 20);
			
		}
		return btnKommando;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setForeground(Color.RED);
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo.setBounds(44, 11, 380, 14);
		}
		return lblInfo;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 41, 145, 150);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
}
