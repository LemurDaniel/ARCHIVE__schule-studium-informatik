package aufgabe3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Point;

public class TankeVerwaltung extends JFrame {
	private JPanel panel;
	private JLabel lblKap;
	private JTextField tFkap;
	private JButton btnAnlegen;
	private JPanel panel_1;
	private JLabel lblMenge;
	private JTextField tFmenge;
	private JButton btnOperate;
	private JProgressBar progressBar;
	private JLabel lblStand;
	
	private Tank tank;
	
	public TankeVerwaltung() {
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getPanel_1());
		getContentPane().add(getProgressBar());
		getContentPane().add(getLblStand());
	}

	
	public static void main(String[] args) {
		TankeVerwaltung tk = new TankeVerwaltung();
		tk.setVisible(true);
		tk.setBounds(500, 250, 450, 300);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setFont(new Font("Dialog", Font.BOLD, 12));
			panel.setForeground(Color.WHITE);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tankanlegen", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 12), Color.MAGENTA));
			panel.setBounds(10, 11, 310, 78);
			panel.setLayout(null);
			panel.add(getLblKap());
			panel.add(getTFkap());
			panel.add(getBtnAnlegen());
		}
		return panel;
	}
	private JLabel getLblKap() {
		if (lblKap == null) {
			lblKap = new JLabel("Kapazit\u00E4t:");
			lblKap.setFont(new Font("Dialog", Font.BOLD, 12));
			lblKap.setBounds(10, 28, 76, 22);
		}
		return lblKap;
	}
	private JTextField getTFkap() {
		if (tFkap == null) {
			tFkap = new JTextField();
			tFkap.setFont(new Font("Dialog", Font.BOLD, 12));
			tFkap.setBounds(96, 28, 86, 22);
			tFkap.setColumns(10);
		}
		return tFkap;
	}
	private JButton getBtnAnlegen() {
		if (btnAnlegen == null) {
			btnAnlegen = new JButton("anlegen");
			btnAnlegen.setFont(new Font("Dialog", Font.BOLD, 12));
			btnAnlegen.setBounds(192, 29, 89, 22);
			btnAnlegen.addActionListener( e -> {
		
				try {
					int kap = Integer.parseInt(tFkap.getText());
					progressBar.setMaximum(kap);
					tank = new Tank(kap);
					
					panel_1.setVisible(true);
					progressBar.setVisible(true);
					lblStand.setVisible(true);					
					tFkap.setEnabled(false);
					btnAnlegen.setVisible(false);
					panel.setBorder(null);
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(TankeVerwaltung.this, nfe.getMessage(), "Fehlermeldung", JOptionPane.WARNING_MESSAGE);
				}	
			});
		}
		return btnAnlegen;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setVisible(false);
			panel_1.setLayout(null);
			panel_1.setForeground(Color.WHITE);
			panel_1.setFont(new Font("Dialog", Font.BOLD, 12));
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tankbedienung", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 12), Color.GREEN));
			panel_1.setBounds(10, 172, 310, 78);
			panel_1.add(getLblMenge());
			panel_1.add(getTFmenge());
			panel_1.add(getBtnOperate());
		}
		return panel_1;
	}
	private JLabel getLblMenge() {
		if (lblMenge == null) {
			lblMenge = new JLabel("Menge:");
			lblMenge.setFont(new Font("Dialog", Font.BOLD, 12));
			lblMenge.setBounds(10, 28, 50, 22);
		}
		return lblMenge;
	}
	private JTextField getTFmenge() {
		if (tFmenge == null) {
			tFmenge = new JTextField();
			tFmenge.setFont(new Font("Dialog", Font.BOLD, 12));
			tFmenge.setColumns(10);
			tFmenge.setBounds(70, 28, 86, 22);
		}
		return tFmenge;
	}
	private JButton getBtnOperate() {
		if (btnOperate == null) {
			btnOperate = new JButton("operate");
			btnOperate.setFont(new Font("Dialog", Font.BOLD, 12));
			btnOperate.setBounds(166, 28, 89, 22);
			btnOperate.addActionListener( e-> operate() );
		}
		return btnOperate;
	}
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setVisible(false);
			progressBar.setFont(new Font("Dialog", Font.BOLD, 12));
			progressBar.setOrientation(SwingConstants.VERTICAL);
			progressBar.setBounds(330, 11, 94, 239);
		}
		return progressBar;
	}
	private JLabel getLblStand() {
		if (lblStand == null) {
			lblStand = new JLabel("");
			lblStand.setForeground(Color.RED);
			lblStand.setFont(new Font("Dialog", Font.BOLD, 12));
			lblStand.setBounds(10, 100, 310, 14);
		}
		return lblStand;
	}
	
	
	
	private void operate() {
		int menge;
		
		try {
			menge = Integer.valueOf(tFmenge.getText());
			if(menge>0)	tank.fuellen(menge);
			if(menge<0) tank.leeren(-menge);
				
		}catch(TankException te) {
			JOptionPane.showMessageDialog(this, te.getMessage()+te.getDelta(), "Tankproblem", JOptionPane.WARNING_MESSAGE);
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, nfe.getMessage(), "Eingabefehler", JOptionPane.WARNING_MESSAGE);
		}finally {
			progressBar.setValue(tank.getIst());
			lblStand.setText("Füllstand: "+ tank.getIst());
		}
	}
}
