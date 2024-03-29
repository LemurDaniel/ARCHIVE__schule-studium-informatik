package aufgabe5;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;

import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;


import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KC extends JFrame {

	private JList<String> list;
	private JLabel lblWinkel;
	private JTextField textField;
	private JRadioButton rdbtnRelativ;
	private JRadioButton rdbtnAbsolut;
	private JButton btnKommando;

	private Roboter r = new Roboter();
	private DefaultListModel<String> dlm = new DefaultListModel<>();	
	private Rectangle bounds = new Rectangle(10, 11, 250, 150);
	private JWindow robotPane;
	private RobotPanel panels[];
	
	private JLabel lblInfo;
	private JScrollPane scrollPane;
	
	public KC() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(null);
		getContentPane().add(getLblWinkel());
		getContentPane().add(getTextField());
		getContentPane().add(getRdbtnRelativ());
		getContentPane().add(getRdbtnAbsolut());
		getContentPane().add(getBtnKommando());
		getContentPane().add(getLblInfo());
		getContentPane().add(getScrollPane());
		getContentPane().setVisible(true);
		getMenuBar();	
		getRobotPane();
		robotPane.setVisible(true);
	}
	
	public static void main(String args[]) {
		KC kc = new KC();
		kc.setBounds(500, 250, 450, 300);
		kc.setVisible(true);
	}
	
	
	// Robot Panel
	private JWindow getRobotPane() {
		if(robotPane == null) {
			int zeilen = (int)Math.ceil(r.length()/3.0);
			int fbreite = 3*(int)bounds.getWidth()+ 4*(int)bounds.getX();
			int fhoehe =  zeilen*(int)bounds.getHeight()+ (zeilen+1)*(int)bounds.getY();
		
			robotPane = new JWindow();
			robotPane.setBounds(900, 300, fbreite, fhoehe);	
			robotPane.getContentPane().setLayout(null);
			
			robotPane.addMouseMotionListener(new MouseMotionListener() {
			    private int mx, my;

			    @Override
			    public void mouseMoved(MouseEvent e) {
			        mx = e.getXOnScreen();
			        my = e.getYOnScreen();
			    }

			    @Override
			    public void mouseDragged(MouseEvent e) {
			        Point p = robotPane.getLocation();
			        p.x += e.getXOnScreen() - mx;
			        p.y += e.getYOnScreen() - my;
			        mx = e.getXOnScreen();
			        my = e.getYOnScreen();
			        robotPane.setLocation(p);
			    }
			});
			
			getPanels();
			for(int i=0; i<panels.length; i++) {
				robotPane.getContentPane().add(panels[i]);
			}
		}
		return robotPane;
	}
	
	private RobotPanel[] getPanels() {
		panels = new RobotPanel[r.length()];
		Rectangle pos = new Rectangle(bounds);
		
		for(int i=0; i<r.length();) {
			panels[i] = new RobotPanel(i, pos);
			panels[i].setVisible(true);
			
			if( ++i%3 != 0) 
				pos.x += bounds.width+bounds.x;
			else {
				pos.x = bounds.x;
				pos.y += bounds.height + bounds.y;
			}	
		}
		
		return panels;
	}
	//
	
	// KC
	private JList<String> getList() {
		if (list == null) {
			r.fillDLM(dlm);
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

	// KC Button
	private JButton getBtnKommando() {
		if (btnKommando == null) {
			btnKommando = new JButton("Kommando geben");
			btnKommando.addActionListener(e -> {
				try {
					lblInfo.setText(null);
					r.bewege(list.getSelectedIndex(), textField.getText(), rdbtnRelativ.isSelected());
					panels[list.getSelectedIndex()].aktualisiere();
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
	//
	
	
	
	
	
	private class RobotPanel extends JPanel{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JLabel lblWinkel;
		private JLabel lblBez;
		private JSlider slider;
		private JTextField textField;
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
			
			JButton b = new JButton();
			b.setVisible(true);
			b.setBounds(10, 10, 20, 20);
			b.addActionListener( e -> {
				textField.setText(r.getWinkel(id)+"");
			});
			add(b);
		}
		private JLabel getLblBez() {
			if (lblBez == null) {
				lblBez = new JLabel(r.getBez(id));
				lblBez.setHorizontalAlignment(SwingConstants.CENTER);
				lblBez.setForeground(Color.BLACK);
				lblBez.setFont(new Font("Dialog", Font.BOLD, 12));
				lblBez.setBounds(10, 21, 230, 14);
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
				
				slider.setMajorTickSpacing(45);
				slider.setMinorTickSpacing(15);
				@SuppressWarnings("unchecked")
				Hashtable<Integer, JLabel> ht = slider.createStandardLabels(45);			
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
		
		public void aktualisiere() {
			textField.setText(r.getWinkel(id)+"");
			slider.setValue(r.getWinkel(id));
		}
	}

}

