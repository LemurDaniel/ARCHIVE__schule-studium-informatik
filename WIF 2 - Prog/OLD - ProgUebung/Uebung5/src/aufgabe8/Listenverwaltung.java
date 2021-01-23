package aufgabe8;

import javax.swing.JFrame;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Listenverwaltung extends JFrame {
	private List list_links;
	private List list_rechts;
	private JButton btn_r;
	private JButton btn_l;
	private JLabel lbl_info;
	
	
	public Listenverwaltung() {
		setResizable(false);
		setTitle("Listenverwaltung");
		getContentPane().setLayout(null);
		getContentPane().add(getList_links());
		getContentPane().add(getList_rechts());
		getContentPane().add(getBtn_r());
		getContentPane().add(getBtn_l());
		getContentPane().add(getLbl_info());
	}

	public static void main(String[] args) {
		Listenverwaltung lw = new Listenverwaltung();
		lw.setBounds(230, 230, 450, 306);
		lw.setVisible(true);
	}
	private List getList_links() {
		if (list_links == null) {
			list_links = new List();
			list_links.addItemListener(e -> btn_l.setEnabled(true));
			list_links.setBounds(27, 35, 122, 190);
			list_links.add("Bier");
			list_links.add("Sekt");
			list_links.add("Schnapps");
		}
		return list_links;
	}
	private List getList_rechts() {
		if (list_rechts == null) {
			list_rechts = new List();
			list_rechts.addItemListener(e -> btn_r.setEnabled(true));
			list_rechts.setBounds(288, 35, 122, 190);
			list_rechts.add("Wein");
		}
		return list_rechts;
	}
	private JButton getBtn_r() {
		if (btn_r == null) {
			btn_r = new JButton("nach Rechts");
			btn_r.setEnabled(false);
			btn_r.setFont(new Font("Dialog", Font.PLAIN, 12));
			btn_r.setBounds(155, 90, 116, 23);
			btn_r.addActionListener( e -> { schiebe(list_links, list_rechts);
											btn_r.setEnabled(false);
											});
		}
		return btn_r;
	}
	private JButton getBtn_l() {
		if (btn_l == null) {
			btn_l = new JButton("nach links");
			btn_l.setEnabled(false);
			btn_l.setFont(new Font("Dialog", Font.PLAIN, 12));
			btn_l.setBounds(166, 124, 116, 23);
			btn_l.addActionListener( e -> { schiebe(list_rechts, list_links);
											btn_l.setEnabled(false);
											});
		}
		return btn_l;
	}
	
	
	protected void schiebe(List liste1, List liste2) {
		//if( liste1.getSelectedItem() != null) {
		if( liste1.getSelectedIndex() == -1) {
			lbl_info.setText("Es wurde nichts ausgewählt");
			return;
		}	
			liste2.add(liste1.getSelectedItem());
			liste1.remove(liste1.getSelectedItem());
			lbl_info.setText(null);
	}
	
	
	private JLabel getLbl_info() {
		if (lbl_info == null) {
			lbl_info = new JLabel("");
			lbl_info.setForeground(Color.RED);
			lbl_info.setFont(new Font("Dialog", Font.BOLD, 12));
			lbl_info.setBounds(27, 236, 383, 30);
		}
		return lbl_info;
	}
}
