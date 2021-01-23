package Test;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Test.Roboter.RoboterLadeFehler;

import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial" )
public class KC extends JFrame {

	private boolean spielereien = false;
	private int count = 0;
	
	private JList<String> list;
	private JLabel lblWinkel;
	private JTextField textField;
	private JRadioButton rdbtnRelativ;
	private JRadioButton rdbtnAbsolut;
	private JButton btnKommando;

	private Roboter r;
	private DefaultListModel<String> dlm = new DefaultListModel<>();	
	private Rectangle jWbounds = new Rectangle(10, 11, 250, 150);
	private Rectangle jFbounds = new Rectangle(500, 250, 385, 300);
	private String titleAppend = "   -   Steuerung";
	
	//private JWindow robotPane;
	// JDialog, da JWindow kein funktionierendes Textfeld bietet.
	private JDialog robotPane;
	private RobotPanel panels[];
	
	
	private JLabel lblInfo;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem mNew;
	private JMenuItem mOpen;
	private JMenuItem mSave;
	private JButton btnHinzu;
	private JButton btnLoesche;
	private JPanel panel;
	private JMenuItem mDelete;
	private boolean active = false;
	private JLabel lblAnzahl;
	private JMenuItem mName;
	
	
	public KC() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				if(robotPane != null) robotPane.setLocation(getBounds().x+getBounds().width, getBounds().y);
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.YES_OPTION;
				if ( active ) i = JOptionPane.showConfirmDialog(KC.this, "Möchten sie das Programm wirklich beenden? \nNicht gespeicherte Änderungen gehen verloren!", "Beenden", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if ( i != JOptionPane.YES_OPTION)
					return;
				else
					System.exit(0);
			}
			@Override
			public void windowIconified(WindowEvent e) {
				if (robotPane != null) robotPane.setVisible(false);
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				if (robotPane != null) robotPane.setVisible(true);
			}
		});	
			
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Robotersteuerung");
		setResizable(false);
		setBounds(jFbounds);
		
		getContentPane().setLayout(null);
		getContentPane().add(getLblInfo());
		getContentPane().add(getPanel());
		setJMenuBar(getMenuBar_2());
		getMenuBar();		
	}
	
	public static void main(String args[]) {
		KC kc = new KC();
		kc.setVisible(true);
	}
	
	
	// Robot Panel
	private JDialog getRobotPane() {
		if(robotPane == null) {
			if( r.length() == 0) 
				return new JDialog();
			
			int row = (int)Math.ceil( Math.sqrt(r.length()) );		
			int zeilen = (int)Math.ceil(r.length()/(double)row);
			int fbreite = row*(int)jWbounds.getWidth()+ (row+1)*(int)jWbounds.getX();
			int fhoehe =  zeilen*(int)jWbounds.getHeight()+ (zeilen+1)*(int)jWbounds.getY();
			Rectangle b = getBounds();
			
			robotPane = new JDialog();
			robotPane.setBounds( b.x+b.width, b.y, fbreite, fhoehe);	
			robotPane.getContentPane().setLayout(null);
			robotPane.setUndecorated(true);
			robotPane.addMouseListener( new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					robotPane.requestFocus();
				}
			});
			
			getPanels(row, zeilen);
			for(int i=0; i<panels.length; i++) {
				robotPane.getContentPane().add(panels[i]);
			}
		}
		return robotPane;
	}
	
	private RobotPanel[] getPanels(int row, int zeilen) {
		panels = new RobotPanel[r.length()];
		Rectangle pos = new Rectangle(jWbounds);
		
		LOOP1:
		for(int i=0, z=0; i<r.length();) {
			
			if( z < zeilen-1 || !spielereien || zeilen < 2) {
				panels[i] = new RobotPanel(i, pos);
				panels[i].setVisible(true);	
				if( ++i%row != 0 ) {			
					pos.x += jWbounds.width+jWbounds.x;
				}else {
					pos.x = jWbounds.x;
					pos.y += jWbounds.height + jWbounds.y;
					z++;
				}	
			}else {
				
				// Spielereien
				int rest = r.length()-i;
				if(  (rest%2 == 0 && row%2 == 0)  ||  (rest%2 != 0 && row%2 != 0)  ) {
					pos.x = jWbounds.x + (jWbounds.width+jWbounds.x)* ( (row-rest) /2);
					for(;i<r.length(); i++) {
						panels[i] = new RobotPanel(i, pos);
						panels[i].setVisible(true);
						pos.x += jWbounds.width+jWbounds.x;
					}
					break LOOP1;
				}
				if( (rest%2 == 0 && row%2 != 0) ) {
					boolean b = true;
					for(; i<r.length(); i++) {
						if( b && r.length()-i == rest/2 ) {
							pos.x += (jWbounds.width+jWbounds.x)*(row-rest);
							b = false;
						}
						panels[i] = new RobotPanel(i, pos);
						panels[i].setVisible(true);
						pos.x += (jWbounds.width+jWbounds.x);
					}
					break LOOP1;
				}
				spielereien = false;
			}
			
		}
		return panels;
	}
	//
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 36, 355, 203);
			panel.setLayout(null);
			panel.add(getLblWinkel());
			panel.add(getRdbtnAbsolut());
			panel.add(getRdbtnRelativ());
			panel.add(getScrollPane());
			panel.add(getBtnHinzu());
			panel.add(getTextField());
			panel.add(getBtnKommando());
			panel.add(getLblAnzahl());
			panel.setVisible(false);
		}
		return panel;
	}
	
	private JLabel getLblAnzahl() {
		if (lblAnzahl == null) {
			lblAnzahl = new JLabel("New label");
			lblAnzahl.setFont(new Font("Dialog", Font.BOLD, 12));
			lblAnzahl.setBounds(190, 141, 143, 14);
		}
		return lblAnzahl;
	}
	
	// KC
	private JList<String> getList() {
		if (list == null) {
			list = new JList<>(dlm);
			list.setSelectedIndex(0);
			list.setFont(new Font("Dialog", Font.BOLD, 12));
			list.setSelectedIndex(0);
			}
		return list;
	}
	private JLabel getLblWinkel() {
		if (lblWinkel == null) {
			lblWinkel = new JLabel("Winkel:");
			lblWinkel.setBounds(190, 17, 60, 14);
			lblWinkel.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return lblWinkel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(247, 14, 86, 20);
			textField.setFont(new Font("Dialog", Font.BOLD, 12));
			textField.setColumns(10);
			textField.addFocusListener( new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					textField.setText(null);
					textField.setForeground(Color.BLACK);
				}
			});
			
		}
		return textField;
	}
	private JRadioButton getRdbtnRelativ() {
		if (rdbtnRelativ == null) {
			rdbtnRelativ = new JRadioButton("relativ");
			rdbtnRelativ.setBounds(190, 70, 143, 23);
			rdbtnRelativ.setFont(new Font("Dialog", Font.BOLD, 12));
			rdbtnRelativ.addActionListener( e -> rdbtnAbsolut.setSelected(!rdbtnRelativ.isSelected()) );
		}
		return rdbtnRelativ;
	}
	private JRadioButton getRdbtnAbsolut() {
		if (rdbtnAbsolut == null) {
			rdbtnAbsolut = new JRadioButton("absolut");
			rdbtnAbsolut.setBounds(190, 46, 143, 23);
			rdbtnAbsolut.setSelected(true);
			rdbtnAbsolut.setFont(new Font("Dialog", Font.BOLD, 12));
			rdbtnAbsolut.addActionListener( e -> rdbtnRelativ.setSelected(!rdbtnAbsolut.isSelected()) );
		}
		return rdbtnAbsolut;
	}

	// KC Button
	private JButton getBtnKommando() {
		if (btnKommando == null) {
			btnKommando = new JButton("Kommando geben");
			btnKommando.setBounds(25, 172, 308, 20);
			btnKommando.addActionListener(e -> {
				try {
					lblInfo.setText(null);
					r.bewege(list.getSelectedIndex(), textField.getText(), rdbtnRelativ.isSelected());
					panels[list.getSelectedIndex()].aktualisiere();
					textField.setForeground(Color.LIGHT_GRAY);
				}catch(Exception e1) {
					lblInfo.setText(e1.getMessage());
				}
			});
			btnKommando.setFont(new Font("Dialog", Font.BOLD, 12));
			
		}
		return btnKommando;
	}
	private JButton getBtnHinzu() {
		if (btnHinzu == null) {
			btnHinzu = new JButton("F\u00FCge Hinzu");
			btnHinzu.setBounds(190, 105, 143, 25);
			btnHinzu.setFont(new Font("Dialog", Font.BOLD, 12));
			btnHinzu.addActionListener( l -> {
				if(r.getName().equals("TestingModeOn")) {
					try {
						count++;
						r.hinzufuegen("Test_"+count+Roboter.getActionSeperator()+"Test_"+count, 180, 0, 90, 45, 15);
						reset();
					}catch(Exception e) {
						lblInfo.setText(e.getMessage());
					}
					return;
				}
				
				try {
					lblInfo.setText(null);
					setVisible(false);
					if(robotPane != null) robotPane.setVisible(false);					
					new RobotInput( KC.this.getBounds(), r, 
										(bez, i) -> {
											try {
												r.hinzufuegen(bez, i[0], i[1], i[2], i[3], i[4]);
											}catch(Exception e) {
												lblInfo.setText(e.getMessage());
											}
										},
										()	 ->	 reset() );
		
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}
			});
		}
		return btnHinzu;
	}
	private JButton getBtnLoesche() {
		if (btnLoesche == null) {
			btnLoesche = new JButton("Loesche");
			btnLoesche.setFont(new Font("Dialog", Font.BOLD, 12));
			btnLoesche.addActionListener( l -> {
				try {
					lblInfo.setText(null);
					if ( !r.getName().equals("TestingModeOn") && JOptionPane.showConfirmDialog(panel, "Wollen sie das Element "+list.getSelectedValue()+ " löschen? ", "Entfernen", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) != JOptionPane.YES_OPTION)
						return;
					
					int i = list.getSelectedIndex();
					r.loesche(i);	
					reset();
					list.setSelectedIndex(i-1);
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}
			});
		}
		return btnLoesche;
	}
	
	
	//
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfo.setForeground(Color.RED);
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo.setBounds(10, 11, 355, 14);
		}
		return lblInfo;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 11, 145, 150);
			scrollPane.setViewportView(getList());
			scrollPane.setColumnHeaderView(getBtnLoesche());
		}
		return scrollPane;
	}
	
	// KC Menu
	private JMenuBar getMenuBar_2() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenu());
		}
		return menuBar;
	}
	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu("File");
			menu.add(getMNew());
			menu.add(getMOpen());
			menu.add(getMSave());
			menu.add(getMDelete());
			menu.add(getMName());
		}
		return menu;
	}
	private JMenuItem getMNew() {
		if (mNew == null) {
			mNew = new JMenuItem("new");
			mNew.addActionListener(l -> {
				try {
					lblInfo.setText(null);
					r = new Roboter( JOptionPane.showInputDialog(KC.this, "Geben sie ihrem Roboter einen Namen", "Roboter erstellen", JOptionPane.INFORMATION_MESSAGE) );
					reset();
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}
			});
		}
		return mNew;
	}
	private JMenuItem getMOpen() {
		if (mOpen == null) {
			mOpen = new JMenuItem("open");
			mOpen.addActionListener( l ->{		
				try {
					lblInfo.setText(null);
					r = Roboter.load();
					reset();
				}catch(RoboterLadeFehler rlf) {
					r = rlf.getRoboter();
					reset();
					lblInfo.setText(rlf.getMessage());
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}			
			});
		}
		return mOpen;
	}
	private JMenuItem getMSave() {
		if (mSave == null) {
			mSave = new JMenuItem("save");
			//mSave.setEnabled(false);
			mSave.setVisible(false);
			mSave.addActionListener( l -> {
				try {
					lblInfo.setText(null);
					r.save(robotPane);
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}
			});
		}
		return mSave;
	}
	
	private JMenuItem getMDelete() {
		if (mDelete == null) {
			mDelete = new JMenuItem("Delete");
			//mDelete.setEnabled(false);
			mDelete.setVisible(false);
			mDelete.addActionListener( l -> {
				if ( JOptionPane.showConfirmDialog(KC.this, "Sind sie sicher?", "Deleting", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) 
					return;
				
				lblInfo.setText(null);
				if(robotPane != null) robotPane.setVisible(false);
				robotPane = null;
				panel.setVisible(false);		
				dlm.clear();
				r = null;
				
//				mSave.setEnabled(false);
//				mDelete.setEnabled(false);
//				mName.setEnabled(false);
				
				mSave.setVisible(false);
				mDelete.setVisible(false);
				mName.setVisible(false);
//				mOpen.setVisible(true);
//				mNew.setVisible(true);
				active = false;
				setTitle("RoboterSteuerung");
			});
		}
		return mDelete;
	}
	
	private JMenuItem getMName() {
		if (mName == null) {
			mName = new JMenuItem("Rename");
			//mName.setEnabled(false);
			mName.setVisible(false);
			mName.addActionListener( l ->{
				try {
					lblInfo.setText(null);
					r.setName( JOptionPane.showInputDialog(KC.this, "Geben sie ihren Roboter einen Namen", "Roboter erstellen", JOptionPane.INFORMATION_MESSAGE) );
					setTitle(r.getName() + titleAppend);
				}catch(Exception e) {
					lblInfo.setText(e.getMessage());
				}
			});
		}
		return mName;
	}
	//

	private void reset() {
		if (robotPane != null) {
			robotPane.setVisible(false);
			robotPane = null;
		}
		
		r.fillDLM(dlm);
		setTitle( r.getName() + titleAppend);
		setVisible(true);
		panel.setVisible(true);
		active = true;
//		mNew.setVisible(false);
//		mOpen.setVisible(false);	
		mName.setVisible(true);
		mDelete.setVisible(true);
		mSave.setVisible(true);
		
		btnHinzu.setEnabled( r.length()<r.getListMax() );	
		
		if (r.length() <= 0) {
			btnLoesche.setEnabled(false);
			btnKommando.setEnabled(false);
			//mSave.setEnabled(false);
			//mDelete.setEnabled(false);
			lblInfo.setText(r.getName()+ " besitzt keine Körperteile");
			lblAnzahl.setText("Keine Körperteile");
		}else {						
			btnKommando.setEnabled(true);
			btnLoesche.setEnabled(true);
			//mSave.setEnabled(true);
			//mDelete.setEnabled(true);
			getRobotPane();
			robotPane.setVisible(true);
			robotPane.requestFocus();			
			if (list.isSelectionEmpty())	list.setSelectedIndex(0);
			lblAnzahl.setText(r.length()+" / "+r.getListMax() +   "   Körperteile" );
		}

	}
	
	

	
	
	
	
	private class RobotPanel extends JPanel{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JLabel lblWinkel;
		private JLabel lblBez;
		private JButton snap;
		
		private JSlider slider;
		private JTextField textField;
		private JTextField tFBez;
		private boolean clicked = false;
		private int id;
		
		
		RobotPanel(int id, Rectangle bounds) {
			this.id = id;
			setBounds(bounds);
			setLayout(null);
			add(getLblBez());
			add(getSlider());
			add(getLblWinkel());
			add(getTextField());
			add(getTFBez());
			add(getBtnSnap());
		}
		
		private JButton getBtnSnap() {
			if (snap == null) {
				snap = new JButton("Snap");
				snap.setBounds(10, 2, 80, 15);
				snap.setForeground(Color.GRAY);
				snap.addActionListener( e -> {
					slider.setSnapToTicks(!slider.getSnapToTicks());
					snap.setText(slider.getSnapToTicks()+"");
				});

				snap.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						snap.setText(slider.getSnapToTicks()+"");
					}
					@Override
					public void mouseExited(MouseEvent e) {
						snap.setText("Snap");
					}
				});
			}
			return snap;
		}
		
		private JTextField getTFBez() {
			if (tFBez == null) {
				tFBez = new JTextField();
				tFBez.setEditable(true);
				tFBez.setVisible(false);
				tFBez.setHorizontalAlignment(SwingConstants.CENTER);
				tFBez.setFont(new Font("Dialog", Font.BOLD, 12));
				tFBez.setBounds(10, 21, 230, 14);
				tFBez.setColumns(10);
				method3 m = () -> {						
					try {
						lblInfo.setText(null);
						if( tFBez.getText().equals(lblBez.getText()) )
							return;
						r.setText(id, tFBez.getText());
						dlm.setElementAt(tFBez.getText(), id);
						lblBez.setText(tFBez.getText());
						tFBez.setForeground(Color.LIGHT_GRAY);
					}catch(Exception e1) {
						lblInfo.setText(e1.getMessage());
					}
				};
				
				tFBez.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						lblBez.setVisible(true);
						tFBez.setVisible(false);
						m.apply();
					}
				});
				tFBez.addFocusListener( new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						tFBez.setText(null);
						tFBez.setForeground(Color.BLACK);
					}
					@Override
					public void focusLost(FocusEvent e) {
						lblBez.setVisible(true);
						tFBez.setVisible(false);
						m.apply();
					}
				});
				tFBez.addActionListener( l -> m.apply());
			}
			return tFBez;
		}
		private JTextField getTextField() {
			if (textField == null) {
				textField = new JTextField(r.getWinkel(id)+"");
				textField.setEditable(false);
				textField.setFont(new Font("Dialog", Font.BOLD, 12));
				textField.setBounds(115, 107, 86, 20);
				textField.setColumns(10);
			}
			return textField;
		}
		
		
		private JLabel getLblBez() {
			if (lblBez == null) {
				lblBez = new JLabel(r.getBez(id));
				lblBez.setHorizontalAlignment(SwingConstants.CENTER);
				lblBez.setForeground(Color.BLACK);
				lblBez.setFont(new Font("Dialog", Font.BOLD, 12));
				lblBez.setBounds(10, 21, 230, 14);
				lblBez.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblBez.setVisible(false);
						tFBez.setVisible(true);
						tFBez.setText(lblBez.getText());
						tFBez.setForeground(Color.LIGHT_GRAY);
					}
				});
			}
			return lblBez;
		}
		
		private JSlider getSlider() {
			if (slider == null) {
				slider = new JSlider();
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				slider.setSnapToTicks(false);
				slider.setEnabled(true);
				slider.setFont(new Font("Dialog", Font.BOLD, 12));
				slider.setMaximum(r.getMax(id));
				slider.setMinimum(r.getMin(id));
				slider.setValue(r.getWinkel(id));
				slider.setBounds(10, 46, 230, 56);
				
				slider.setMajorTickSpacing(r.getMajorT(id));
				slider.setMinorTickSpacing(r.getMinorT(id));

				@SuppressWarnings("unchecked")
				Hashtable<Integer, JLabel> ht = slider.createStandardLabels(r.getMajorT(id));			
				ht.put(r.getMax(id), new JLabel(r.getMax(id)+"") );
				ht.put(r.getMin(id), new JLabel(r.getMin(id)+"") );
				slider.setLabelTable(ht);
				
				slider.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						clicked = true;
					}
					
					@Override
					public void mouseReleased(MouseEvent e) {
						clicked = false;
					}
				});
				
				slider.addChangeListener( l -> {
					try {
						if(clicked) {
							r.bewege(id, slider.getValue(), false);
							textField.setText(slider.getValue()+"");
						}
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				});
			}
			return slider;
		}
		private JLabel getLblWinkel() {
			if (lblWinkel == null) {
				lblWinkel = new JLabel("Akt.-Winkel:");
				lblWinkel.setFont(new Font("Dialog", Font.BOLD, 12));
				lblWinkel.setBounds(30, 110, 70, 14);
			}
			return lblWinkel;
		}
		
		public void aktualisiere() {
			textField.setText(r.getWinkel(id)+"");
			slider.setValue(r.getWinkel(id));
		}
	}
}

