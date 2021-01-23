package Test;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Hashtable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class RobotInput extends JFrame {
	
	public static boolean Testing = false;

	private JPanel panel;
	private JSlider slider;
	private myTF<String> txtName;
	private myTF<Integer> txtMinimum;
	private myTF<Integer> txtMaximum;
	private myTF<Integer> txtWinkel;
	private myTF<Integer> txMinorT;
	private myTF<Integer> txtMajorT;
	
	private JButton btnKreieren;
	private JLabel lblInfo;
	private Roboter r;
	
	private method create;
	private method3 close;

	public RobotInput(Rectangle bounds, Roboter r, method create, method3 close) {
		this.create = create;
		this.close = close;
		this.r = r;
		
		setTitle("Hinzufügen");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {	
				close.apply();
				RobotInput.this.dispose();
			}
		});
		setBounds( bounds.x, bounds.y, 285, 335);
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		setVisible(true);
		panel.requestFocus();

		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					panel.requestFocus();
				}
			});
			panel.setBorder(new TitledBorder(null, "K\u00F6rperteil hinzuf\u00FCgen", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 12), Color.GREEN));
			panel.setBounds(10, 11, 249, 274);
			panel.setLayout(null);
			panel.add(getSlider());
			panel.add(getTxtName());
			panel.add(getTxtMinimum());
			panel.add(getTxtMaximum());
			panel.add(getTxtWinkel());
			panel.add(getBtnKreieren());
			panel.add(getLblInfo());
			
			panel.add(getTxMinorT());
			panel.add(getTxtMajorT());
			
			aktualisiereSlider();
		}
		return panel;
	}
	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setEnabled(false);
			slider.setPaintTicks(false);
			slider.setPaintTicks(false);
			slider.setPaintTrack(true);
			slider.setBounds(10, 60, 230, 56);
		}
		return slider;
	}
	private myTF<String> getTxtName() {
		if (txtName == null) {
			txtName = new myTF<>();
			txtName.setText("Bezeichnung "+Roboter.getActionSeperator()+" Aktion");
			txtName.setDefaultString(txtName.getText());
			txtName.setForeground(Color.LIGHT_GRAY);
			txtName.setHorizontalAlignment(SwingConstants.CENTER);
			txtName.setFont(new Font("Dialog", Font.BOLD, 12));
			txtName.setBounds(10, 33, 230, 16);
			txtName.setColumns(10);
			txtName.addFocusAdapter( () -> {
				r.checkBezeichnung(txtName.getText());	
				txtName.setWert(txtName.getText());
				txtName.setSatisfied(true);
			});
		}
		return txtName;
	}
	private myTF<Integer> getTxtMinimum() {
		if (txtMinimum == null) {
			txtMinimum = new myTF<>();
			txtMinimum.setHorizontalAlignment(SwingConstants.CENTER);
			txtMinimum.setForeground(Color.LIGHT_GRAY);
			txtMinimum.setText("Minimum");
			txtMinimum.setDefaultString(txtMinimum.getText());
			txtMinimum.setFont(new Font("Dialog", Font.BOLD, 12));
			txtMinimum.setColumns(10);
			txtMinimum.setBounds(10, 129, 100, 16);
			txtMinimum.addFocusAdapter( () -> {
					Integer temp = new Integer(txtMinimum.getText());
					if(txtMaximum.getWert() != null)
						if(temp >= txtMaximum.getWert())
							throw new Exception("Minimum größer als Maximum");
					if(txtWinkel.getWert() != null)
						if(temp > txtWinkel.getWert())
							throw new Exception("Minimum größer als Winkel");
					txtMinimum.setWert(temp);
					txtMinimum.setSatisfied(true);
					aktualisiereSlider();
			});
		}
		return txtMinimum;
	}
	private myTF<Integer> getTxtMaximum() {
		if (txtMaximum == null) {
			txtMaximum = new myTF<>();
			txtMaximum.setText("Maximum");
			txtMaximum.setDefaultString(txtMaximum.getText());
			txtMaximum.setHorizontalAlignment(SwingConstants.CENTER);
			txtMaximum.setForeground(Color.LIGHT_GRAY);
			txtMaximum.setFont(new Font("Dialog", Font.BOLD, 12));
			txtMaximum.setColumns(10);
			txtMaximum.setBounds(140, 129, 100, 16);
			txtMaximum.addFocusAdapter( () -> {
				Integer temp = new Integer(txtMaximum.getText());
				if(txtMinimum.getWert() != null)
					if(temp <= txtMinimum.getWert())
						throw new Exception("Maximum kleiner als Minimum");
				if(txtWinkel.getWert() != null)
					if(temp < txtWinkel.getWert())
						throw new Exception("Minimum kleiner als Winkel");
				txtMaximum.setWert(temp);
				txtMaximum.setSatisfied(true);
				aktualisiereSlider();
			});
		}
		return txtMaximum;
	}
	private myTF<Integer> getTxtWinkel() {
		if (txtWinkel == null) {
			txtWinkel = new myTF<>();
			txtWinkel.setText("Winkel");
			txtWinkel.setDefaultString(txtWinkel.getText());
			txtWinkel.setHorizontalAlignment(SwingConstants.CENTER);
			txtWinkel.setForeground(Color.LIGHT_GRAY);
			txtWinkel.setFont(new Font("Dialog", Font.BOLD, 12));
			txtWinkel.setColumns(10);
			txtWinkel.setBounds(60, 156, 127, 16);
			txtWinkel.addFocusAdapter( () -> {
					Integer temp = new Integer(txtWinkel.getText());
					if(txtMinimum.getWert() != null)
						if(temp < txtMinimum.getWert())
							throw new Exception("Winkel kleiner als Minimum");
					if(txtMaximum.getWert() != null)
						if(temp > txtMaximum.getWert())
							throw new Exception("Winkel größer als Maximum");
					txtWinkel.setWert(temp);
					txtWinkel.setSatisfied(true);
					aktualisiereSlider();
			});
		}
		return txtWinkel;
	}
	
	private myTF<Integer> getTxMinorT() {
		if (txMinorT == null) {
			txMinorT = new myTF<>();
			txMinorT.setText("Minor TS");
			txMinorT.setDefaultString(txMinorT.getText());
			txMinorT.setHorizontalAlignment(SwingConstants.CENTER);
			txMinorT.setForeground(Color.LIGHT_GRAY);
			txMinorT.setFont(new Font("Dialog", Font.BOLD, 12));
			txMinorT.setColumns(10);
			txMinorT.setBounds(10, 183, 100, 16);
			txMinorT.addFocusAdapter( () -> {
					Integer temp = new Integer(txMinorT.getText());
					if( temp <= 0)
						throw new Exception("Minor TS muss größer als 0 sein");
						
					txMinorT.setWert(temp);
					txMinorT.setSatisfied(true);
					aktualisiereSlider();
			});
		}
		return txMinorT;
	}
	private myTF<Integer> getTxtMajorT() {
		if (txtMajorT == null) {
			txtMajorT = new myTF<>();
			txtMajorT.setText("Major TS");
			txtMajorT.setDefaultString(txtMajorT.getText());
			txtMajorT.setHorizontalAlignment(SwingConstants.CENTER);
			txtMajorT.setForeground(Color.LIGHT_GRAY);
			txtMajorT.setFont(new Font("Dialog", Font.BOLD, 12));
			txtMajorT.setColumns(10);
			txtMajorT.setBounds(140, 182, 100, 16);
			txtMajorT.addFocusAdapter( () -> {
				Integer temp = new Integer(txtMajorT.getText());
				if( temp <= 0)
					throw new Exception("Major TS muss größer als 0 sein");
					
				txtMajorT.setWert(temp);
				txtMajorT.setSatisfied(true);
				aktualisiereSlider();
			});
		}
		return txtMajorT;
	}
	
	
	private JButton getBtnKreieren() {
		if (btnKreieren == null) {
			btnKreieren = new JButton();
			btnKreieren.setText("Kreiere");
			btnKreieren.setFont(new Font("Dialog", Font.BOLD, 12));
			btnKreieren.setBounds(10, 232, 230, 31);
			btnKreieren.addActionListener( l -> {
				if (txtName.isSatisfied() && txtMaximum.isSatisfied() && txtMinimum.isSatisfied() && txtWinkel.isSatisfied() && txtMajorT.isSatisfied() && txMinorT.isSatisfied() ) {
					create.apply(txtName.getWert(), txtMaximum.getWert(), txtMinimum.getWert(), txtWinkel.getWert(), txtMajorT.getWert(), txMinorT.getWert() );
					close.apply();
					RobotInput.this.dispose();
				}else
					lblInfo.setText("Es fehlen Angaben");
			});
		}
		return btnKreieren;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setForeground(Color.RED);
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo.setBounds(10, 207, 230, 14);
		}
		return lblInfo;
	}
	
	@SuppressWarnings("unchecked")
	private void aktualisiereSlider() {

		int min = 0, max = 100, minorT = 25, majorT = 50;	
		if( txtMaximum.getWert() != null )	{
			max = txtMaximum.getWert();
			if (min >= max)	min = max-100;
		}
		
		if( txtMinimum.getWert() != null ) {
			min = txtMinimum.getWert();	
			if (max <= min)	max = min+100;
		}
		
		int winkel = (max+min)/2;
		if( txtWinkel.getWert() != null ) {
			winkel = txtWinkel.getWert();
			if (max < winkel)	max = winkel+50;
			if( min > winkel) min = winkel-50;
		}
		
		if( txtMajorT.getWert() != null ) majorT = txtMajorT.getWert();
		if( txMinorT.getWert() != null )  minorT = txMinorT.getWert();
		else minorT = majorT;
		
		slider.setMaximum(max);
		slider.setMinimum(min);
		slider.setValue(winkel);
		slider.setMajorTickSpacing(majorT);
		slider.setMinorTickSpacing(minorT);

		
		Hashtable<Integer, JLabel> ht;
		if( txtMajorT.getWert() != null ) {
			ht = slider.createStandardLabels(txtMajorT.getWert());
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
		} else {
			ht = slider.createStandardLabels(45);
			slider.setPaintLabels(false);
			slider.setPaintTicks(false);
		}		
		
		if ( txtMaximum.getWert() != null ) 	ht.put(max, new JLabel(max+"") );
		if ( txtMinimum.getWert() != null )		ht.put(min, new JLabel(min+"") );
		//if ( txtWinkel.getWert() != null ) 		ht.put(winkel, new JLabel(winkel+"") );
		slider.setLabelTable(ht);

	};
	
	
	
	
	
	
	
	
	
	
	
	
	

	private class myTF<T> extends JTextField{
		private String defaultString;
		private T wert;
		private boolean satisfied = false;
		
		public String getDefaultString() {
			return defaultString;
		}
		public void setDefaultString(String defaultString) {
			this.defaultString = defaultString;
		}
		
		public void addFocusAdapter(method2 m) {
			this.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// setze null wenn default Text
					if( getText().equals(getDefaultString()) )
						setText(null);
					setForeground(Color.BLACK);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// Wenn nichts drinnen steht nicht verarbeiten.
					if( getText().length() == 0 ) {
						setText(getDefaultString());
						setForeground(Color.LIGHT_GRAY);
						wert = null;
						setSatisfied(false);
						aktualisiereSlider();
						return;
					}
					

					try {
						lblInfo.setText(null);
						m.apply();
						
					}catch(NumberFormatException nfe) {
						lblInfo.setText("'"+getText() + "' ist keine gültige Integer-Zahl");
						if( wert != null)
							setText(wert+"");
						else {
							setText(defaultString);
							setForeground(Color.LIGHT_GRAY);
						}
					}catch(Exception e1) {
						lblInfo.setText(e1.getMessage());
						if( wert != null)
							setText(wert+"");
						else {
							setText(defaultString);
							setForeground(Color.LIGHT_GRAY);
						}
					}
				}
			});
	}
		public void setWert(T wert) {
			this.wert = wert;
		}
		public T getWert() {
			return wert;
		}
		public boolean isSatisfied() {
			return satisfied;
		}
		public void setSatisfied(boolean satisfied) {
			this.satisfied = satisfied;
		}
	}
}
