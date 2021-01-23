package aufgabe5.a;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RobotGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RobotPanel[] panels;
	private Rectangle bounds = new Rectangle(10, 11, 250, 150);
	private static Roboter r = new Roboter();;
	
	public RobotGUI() {
		int zeilen = (int)Math.ceil(r.getWinkel().length/3.0);
		int fbreite = 3*(int)bounds.getWidth()+ 4*(int)bounds.getX() +10;
		int fhoehe =  zeilen*(int)bounds.getHeight()+ (zeilen+1)*(int)bounds.getY()+ 50;
		
		setBounds(700, 300, fbreite, fhoehe);
		
		getContentPane().setLayout(null);
		getPanels();
		for(int i=0; i<panels.length; i++) {
			getContentPane().add(panels[i]);
		}
		
	}
	
	private JPanel[] getPanels() {
		Winkel[] w = r.getWinkel();
		panels = new RobotPanel[w.length];
		Rectangle pos = new Rectangle(bounds);
		
		for(int i=0; i<w.length;) {
			panels[i] = new RobotPanel(pos, w[i]);
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
	
	public void fillDLM(DefaultListModel<String> dlm) {
		dlm.clear();
		for(Winkel w: r.getWinkel()) {
			dlm.addElement(w.getBez());
		}
	}
	
	public DefaultListModel<String> getDLM() {
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for(Winkel w: r.getWinkel()) {
			dlm.addElement(w.getBez());
		}
		return dlm;
	}
	
	public void command(int cnr, String sbetrag, boolean modeR) throws Exception{
		int betrag;
		try {
			betrag = Integer.parseInt(sbetrag);
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException("'"+sbetrag+ "' ist keine gültige Integer-Zahl");
		}
		r.bewege(cnr, betrag, modeR);
		panels[cnr].aktualisiere();
	}
	
	
	
	
private static class RobotPanel extends JPanel{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JLabel lblWinkel;
		private JLabel lblBez;
		private JSlider slider;
		private JTextField textField;
		private boolean clicked = false;
		Winkel w;
		
		RobotPanel(Rectangle bounds, Winkel w) {
			this.w = w;
			setBounds(bounds);
			setLayout(null);
			add(getLblBez());
			add(getSlider());
			add(getLblWinkel());
			add(getTextField());
			
//			JButton b = new JButton();
//			b.setVisible(true);
//			b.setBounds(10, 10, 20, 20);
//			b.addActionListener( e -> {
//				textField.setText(w.getWinkel()+"");
//			});
//			add(b);
		}
		private JLabel getLblBez() {
			if (lblBez == null) {
				lblBez = new JLabel(w.getBez());
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
				slider.setMaximum(w.getMax());
				slider.setMinimum(w.getMin());
				slider.setValue(w.getWinkel());
				slider.setBounds(10, 46, 230, 56);
				
				slider.setMajorTickSpacing(45);
				slider.setMinorTickSpacing(15);
				@SuppressWarnings("unchecked")
				Hashtable<Integer, JLabel> ht = slider.createStandardLabels(45);			
				ht.put(w.getMin(), new JLabel(w.getMin()+"") );
				ht.put(w.getMax(), new JLabel(w.getMax()+"") );
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
							w.aendere(slider.getValue(), false);
							textField.setText(w.getWinkel()+"");
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
				textField = new JTextField(w.getWinkel()+"");
				textField.setEditable(false);
				textField.setFont(new Font("Dialog", Font.BOLD, 12));
				textField.setBounds(115, 107, 86, 20);
				textField.setColumns(10);
				textField.setText(w.getWinkel()+"");
			}
			return textField;
		}
		
		public void aktualisiere() {
			textField.setText(w.getWinkel()+"");
			slider.setValue(w.getWinkel());
		}
	}

}
