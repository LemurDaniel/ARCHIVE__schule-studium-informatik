package aufgabe7;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Bandit extends JFrame {
	
	private int countPanels = 0;
	private Color[] colors = {Color.GREEN, Color.yellow, Color.red};
	private JPanel[] panels;
	
	public Bandit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);	
		setTitle("Automat");
		
		Integer length = (Integer) JOptionPane.showInputDialog(null, "Wählen sie die Anzahl der Slots", "Eingabe", JOptionPane.QUESTION_MESSAGE, null,new Integer[]{3, 4, 5, 6, 7, 8, 9}, 0);
		panels = new JPanel[length==null ? 3:length];
		
		for(int i=0; i<panels.length; i++) {
			panels[i] = new panel();
			getContentPane().add(panels[i]);
			countPanels++;
		}
		
		Rectangle bounds = panels[panels.length-1].getBounds();
		bounds.width = bounds.x+bounds.width+25;
		bounds.height = bounds.height+60;
		bounds.x = 0;
		bounds.y = 0;
		setBounds(bounds);
		System.out.println(bounds);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bandit b = new Bandit();
		b.setVisible(true);
	}
	
	

	private class panel extends JPanel implements Runnable{
		
		private JLabel lblOne;
		private JButton btnOne;
		private boolean buttonStateStart;
		private Rectangle bounds = new Rectangle(10, 11, 120, 245);
		private int zahl = 0;
		private Color color;
		
		public panel() {
			bounds.x += (bounds.width+bounds.x)*countPanels;	
			color = colors[countPanels%colors.length];
			//color = colors[(int)(Math.random()*colors.length)];
			setBounds(bounds);
			setLayout(null);
			add(getLblOne());
			add(getBtnOne());
		}
		
		private JLabel getLblOne() {
			if (lblOne == null) {
				lblOne = new JLabel(zahl+"");
				lblOne.setBounds(0, 0, 120, 136);
				lblOne.setHorizontalAlignment(SwingConstants.CENTER);
				lblOne.setOpaque(true);
				lblOne.setBackground(color);
				lblOne.setForeground(Color.LIGHT_GRAY);
				lblOne.setFont(new Font("Dialog", Font.PLAIN, 120));
			}
			return lblOne;
		}
		private JButton getBtnOne() {
			if (btnOne == null) {
				btnOne = new JButton("start");
				buttonStateStart = true;
				btnOne.setBounds(0, 147, 120, 98);
				btnOne.setBackground(color);
				btnOne.setFont(new Font("Dialog", Font.BOLD, 16));
				btnOne.addActionListener(l->{
					if(buttonStateStart) {
						buttonStateStart = false;
						btnOne.setText("Stop");
						new Thread(Bandit.panel.this).start();
					}else {
						buttonStateStart = true;
						btnOne.setText("Start");
					}
						
				});
			}
			return btnOne;
		}
		

		@Override
		public void run() {
			while(true) {
				if(buttonStateStart)
					return;
				try {
					Thread.sleep( (int)(Math.random()*11+20) );
				}catch(Exception e) {
					e.printStackTrace();
				}
				if(++zahl > 9)	zahl = 0;
				lblOne.setText(zahl+"");
			}
		}
	
	}
	
}
