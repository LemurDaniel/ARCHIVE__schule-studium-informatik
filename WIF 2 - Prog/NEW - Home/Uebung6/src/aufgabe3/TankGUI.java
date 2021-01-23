package aufgabe3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TankGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel tankanlegen;
	private JPanel tankbedienung;
	private JPanel progress;
	private JLabel lblKap;
	private JLabel lblMenge;
	private JLabel lblInfo;
	private JLabel lblInfo2;
	private JLabel lblFllmenge;
	private JLabel lblFllmenge2;
	private JTextField tFkap;
	private JTextField tFmenge;
	private JButton btnOperate;
	private JButton btnAnlegen;
	private JProgressBar progressBar;
	
	private Tank tank;
	private JButton btnReset;

	public TankGUI() {
		getContentPane().setLayout(null);
		getContentPane().add(getTankanlegen());
		getContentPane().add(getPanel_1_1());
		getContentPane().add(getLblInfo());
		getContentPane().add(getLblInfo2());
		getContentPane().add(getProgress());
		tankbedienung.setVisible(false);
		progress.setVisible(false);
		setTitle("Tankbedienung");
		
		System.out.println(Integer.MAX_VALUE);
	}

	public static void main(String[] args) {
		TankGUI tank = new TankGUI();
		tank.setVisible(true);
		tank.setBounds(500, 250, 600, 320);

	}
	private JPanel getTankanlegen() {
		if (tankanlegen == null) {
			tankanlegen = new JPanel();
			tankanlegen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tank anlegen", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 12), new Color(255, 0, 255)));
			tankanlegen.setBounds(10, 11, 340, 80);
			tankanlegen.setLayout(null);
			tankanlegen.add(getBtnAnlegen());
			tankanlegen.add(getLblKap());
			tankanlegen.add(getTFkap());
			tankanlegen.add(getBtnReset());
		}
		return tankanlegen;
	}

	private JPanel getPanel_1_1() {
		if (tankbedienung == null) {
			tankbedienung = new JPanel();
			tankbedienung.setLayout(null);
			tankbedienung.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tankbedienung", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 12), Color.GREEN));
			tankbedienung.setBounds(10, 170, 380, 80);
			tankbedienung.add(getLblMenge());
			tankbedienung.add(getTFmenge());
			tankbedienung.add(getBtnOperate());
		}
		return tankbedienung;
	}
	
	private JPanel getProgress() {
		if (progress == null) {
			progress = new JPanel();
			progress.setBounds(420, 11, 154, 239);
			progress.setLayout(null);
			progress.add(getLblFllmenge());
			progress.add(getLblFllmenge2());
			progress.add(getProgressBar());
		}
		return progress;
	}
	
	private JLabel getLblKap() {
		if (lblKap == null) {
			lblKap = new JLabel("Kapazit\u00E4t: ");
			lblKap.setFont(new Font("Dialog", Font.BOLD, 12));
			lblKap.setBounds(10, 33, 72, 14);
		}
		return lblKap;
	}
	private JLabel getLblMenge() {
		if (lblMenge == null) {
			lblMenge = new JLabel("Menge: ");
			lblMenge.setFont(new Font("Dialog", Font.BOLD, 12));
			lblMenge.setBounds(10, 33, 72, 14);
		}
		return lblMenge;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setForeground(Color.RED);
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo.setBounds(20, 90, 330, 14);
		}
		return lblInfo;
	}
	private JLabel getLblInfo2() {
		if (lblInfo2 == null) {
			lblInfo2 = new JLabel("");
			lblInfo2.setForeground(Color.RED);
			lblInfo2.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo2.setBounds(20, 105, 330, 14);
		}
		return lblInfo2;
	}
	private JLabel getLblFllmenge() {
		if (lblFllmenge == null) {
			lblFllmenge = new JLabel("F\u00FCllmenge:");
			lblFllmenge.setBounds(41, 11, 72, 14);
			lblFllmenge.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return lblFllmenge;
	}
	private JLabel getLblFllmenge2() {
		if (lblFllmenge2 == null) {
			lblFllmenge2 = new JLabel("0 Liter");
			lblFllmenge2.setBounds(41, 25, 72, 14);
			lblFllmenge2.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return lblFllmenge2;
	}
	
	private JTextField getTFmenge() {
		if (tFmenge == null) {
			tFmenge = new JTextField();
			tFmenge.setFont(new Font("Dialog", Font.BOLD, 12));
			tFmenge.setColumns(10);
			tFmenge.setBounds(92, 31, 95, 20);
			tFmenge.addFocusListener( new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					tFmenge.setForeground(Color.BLACK);
					tFmenge.setText(null);
				}
			});
		}
		return tFmenge;
	}
	private JTextField getTFkap() {
		if (tFkap == null) {
			tFkap = new JTextField();
			tFkap.setFont(new Font("Dialog", Font.BOLD, 12));
			tFkap.setBounds(92, 31, 95, 20);
			tFkap.setColumns(10);
			tFkap.addFocusListener( new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					tFkap.setText(null);
					tFkap.setForeground(Color.black);
				}
			});
		}
		return tFkap;
	}
	
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setBounds(0, 47, 154, 192);
			progressBar.setOrientation(SwingConstants.VERTICAL);
			progressBar.setFont(new Font("Dialog", Font.BOLD, 12));
			progressBar.setString("");
		}
		return progressBar;
	}
	
	private JButton getBtnAnlegen() {
		if (btnAnlegen == null) {
			btnAnlegen = new JButton("anlegen");
			btnAnlegen.setFont(new Font("Dialog", Font.BOLD, 12));
			btnAnlegen.setBounds(220, 25, 110, 30);
			//btnAnlegen.setBounds(20, 50, 110, 30);
			
			btnAnlegen.addActionListener( e -> 
				defaultExceptionHandler( () -> {
//					try {
//						tank = new Tank(tFkap.getText());					
//					}catch(Exception e1) {
//						lblInfo.setText(e1.getClass().getSimpleName());
//						lblInfo2.setText(e1.getMessage());
//						return;
//					}
					
					tank = new Tank(tFkap.getText());	
					
					 //Panel Unssichtbar machen und lbl+TF in Fenster verschieben
//					tankanlegen.setVisible(false);
//					TankGUI.this.getContentPane().add(lblKap);
//					TankGUI.this.getContentPane().add(tFkap);	
//					TankGUI.this.getContentPane().add(getBtnReset());
//					
//					// Anpassen der Relativen Position im Panel an absolute im Fenster
//					int x = tankanlegen.getX();
//					int y = tankanlegen.getY(); 
//					
//					Rectangle r = lblKap.getBounds();
//					lblKap.setBounds( x+r.x, y+r.y,  (int)r.getWidth(), (int)r.getHeight());
//					
//					r = tFkap.getBounds();
//					tFkap.setBounds( x+r.x, y+r.y,  (int)r.getWidth(), (int)r.getHeight());
//					btnReset.setBounds(x+r.x+r.width+5, y+r.y,  (int)r.getWidth(), (int)r.getHeight());			
//					btnReset.setVisible(true);
					
					//
					tankanlegen.setBorder(null);
					progressBar.setMaximum(tank.getMax());
							
					tankbedienung.setVisible(true);
					tFmenge.requestFocus();
					progress.setVisible(true);
					btnAnlegen.setVisible(false);
					btnReset.setVisible(true);
					tFkap.setEditable(false);
					tFkap.setForeground(Color.LIGHT_GRAY);
					lblKap.setForeground(Color.LIGHT_GRAY);
					
					lblInfo.setText(null);
					lblInfo2.setText(null);
				}, 
					// finally Block
				() -> {}));
			
//			btnAnlegen.addActionListener( e -> {
//				defaultExceptionHandler( new Method() {
//					@Override
//					public void apply() throws Exception {
//							Testtt
//					}
//					
//				});
//			});
		}
		return btnAnlegen;
	}
	private JButton getBtnOperate() {
		if (btnOperate == null) {
			btnOperate = new JButton("operate");
			btnOperate.setFont(new Font("Dialog", Font.BOLD, 12));
			btnOperate.setBounds(260, 25, 110, 30);
			btnOperate.addActionListener( e -> 
				defaultExceptionHandler( () -> {
					tank.aenderFuellstand(tFmenge.getText());					
					tFmenge.setForeground(Color.LIGHT_GRAY);				
				},
				//finally-Block
				()-> { 
					progressBar.setValue(tank.getIst());
					lblFllmenge2.setText(tank.getIst()+"");
				}));
		}
		return btnOperate;
	}
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("Reset");
			btnReset.setFont(new Font("Dialog", Font.BOLD, 12));
			btnReset.setVisible(false);
			btnReset.setBounds(197, 30, 95, 20);
			//btnReset.setForeground(Color.LIGHT_GRAY);
			btnReset.addActionListener( l ->{
				btnAnlegen.setVisible(true);
				btnAnlegen.requestFocus();
				btnReset.setVisible(false);
				tankbedienung.setVisible(false);
				progress.setVisible(false);
				
				tank = null;

//				tankanlegen.setVisible(true);
//				tFmenge.setText(null);
//				tFkap.setText(null);	
//				tFkap.setBounds(92, 31, 95, 20);
//				lblKap.setBounds(10, 33, 72, 14);
				
				lblKap.setForeground(Color.BLACK);
				//tFkap.setForeground(Color.BLACK);	
				tFkap.setEditable(true);
				
//				tankanlegen.add(tFkap);
//				tankanlegen.add(lblKap);
//				tankanlegen.requestFocus();
				
				tankanlegen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tankanlegen", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 12), Color.GREEN));
				
			});
		}
		return btnReset;
	}
	
	
	private void defaultExceptionHandler(Method m1, Finally m2) {
		Exception e = null;
		try {
			m1.apply();
		}catch(Exception e1) {
			e = e1;
		}finally {
			m2.apply();
			if(e!=null)	JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.WARNING_MESSAGE);
		}
	}
	
@FunctionalInterface
private interface Method{
		public void apply() throws Exception;
	}

@FunctionalInterface
private interface Finally{
		public void apply();
	}
}


