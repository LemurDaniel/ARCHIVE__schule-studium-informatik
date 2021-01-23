package aufgabe4;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import aufgabe1.Artikel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Wort extends JFrame {
	
	private JLabel lblBezeichnung;
	private JLabel lblStatus;
	private JTextField tf;
	private JScrollPane scrollPane;
	private JButton btnAnfgen;
	private JButton btnDrucken;
	private JPanel panel;

	private Map<String, Integer> map;
	private JTextArea textArea;
	private JButton btnTest;
	
	public Wort() {
		setTitle("Wort Hash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblBezeichnung());
		getContentPane().add(getTf());
		getContentPane().add(getLblStatus());
		getContentPane().add(getBtnAnfgen());
		getContentPane().add(getBtnDrucken());
		getContentPane().add(getPanel());
		getContentPane().add(getBtnTest());
		
		map = new HashMap<String, Integer>() {
			@Override
			public Integer put(String key, Integer value) {
				if(this.containsKey(key)) 
					value = map.get(key)+1;
				super.put(key, value);
				textArea.setText(null);
				return value;
			}
		};
	}

	public static void main(String[] args) {
		Wort a = new Wort();
		a.setVisible(true);
		a.setBounds(500,250, 450, 300);
		Wort2 a2 = new Wort2();
		a2.setVisible(true);
		a2.setBounds(500,250, 450, 300);
		
	}
	private JLabel getLblBezeichnung() {
		if (lblBezeichnung == null) {
			lblBezeichnung = new JLabel("Wort:");
			lblBezeichnung.setFont(new Font("Dialog", Font.BOLD, 13));
			lblBezeichnung.setBounds(10, 11, 58, 21);
		}
		return lblBezeichnung;
	}
	private JTextField getTf() {
		if (tf == null) {
			tf = new JTextField();
			tf.setFont(new Font("Dialog", Font.PLAIN, 13));
			tf.setBounds(75, 11, 122, 20);
			tf.setColumns(10);
		}
		return tf;
	}
	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("");
			lblStatus.setFont(new Font("Dialog", Font.BOLD, 13));
			lblStatus.setForeground(Color.RED);
			lblStatus.setBounds(10, 107, 187, 21);
		}
		return lblStatus;
	}

	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 197, 217);
			scrollPane.setBorder(new TitledBorder(null, "List", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 13), Color.black));
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JButton getBtnAnfgen() {
		if (btnAnfgen == null) {
			btnAnfgen = new JButton("anf\u00FCgen");
			btnAnfgen.setFont(new Font("Dialog", Font.PLAIN, 13));
			btnAnfgen.setBounds(10, 43, 122, 23);
			btnAnfgen.addActionListener(al->{
				lblStatus.setText(tf.getText()+": "+map.put(tf.getText(), 1));
			});
		}
		return btnAnfgen;
	}
	private JButton getBtnDrucken() {
		if (btnDrucken == null) {
			btnDrucken = new JButton("drucken");
			btnDrucken.setFont(new Font("Dialog", Font.PLAIN, 13));
			btnDrucken.setBounds(10, 77, 122, 23);
			btnDrucken.addActionListener(al->map.forEach( (k, v)->textArea.append(k+": "+v+"\n")));
		}
		return btnDrucken;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel.setBounds(207, 11, 217, 239);
			panel.setLayout(null);
			panel.add(getScrollPane());
		}
		return panel;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		}
		return textArea;
	}
	
	
	private void test() {
		new Artikel.TestArtikelListe().forEach(a->map.put(a.getBezeichnung(), a.getMenge()));
	}
	private JButton getBtnTest() {
		if (btnTest == null) {
			btnTest = new JButton("Test");
			btnTest.setFont(new Font("Dialog", Font.PLAIN, 13));
			btnTest.setBounds(10, 139, 122, 23);
			btnTest.addActionListener(al->test());
		}
		return btnTest;
	}
}
	

