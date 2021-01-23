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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Bandit2 extends JFrame {
	
	private int countPanels = 0;
	private Color[] colors = {Color.GREEN, Color.yellow, Color.red};
	private JPanel[] panels;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem2;
	
	public Bandit2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);	
		setTitle("Automat");
		
		Integer length = (Integer) JOptionPane.showInputDialog(null, "Wählen sie die Anzahl der Slots", "Eingabe", JOptionPane.QUESTION_MESSAGE, null,new Integer[]{3, 4, 5, 6, 7, 8, 9, 1000}, 0);
		panels = new JPanel[length==null ? 3:length];
		
		for(int i=0; i<panels.length; i++) {
			panels[i] = new panel();
			getContentPane().add(panels[i]);
			countPanels++;
		}
		
		Rectangle bounds = panels[panels.length-1].getBounds();
		bounds.width = bounds.x+bounds.width+25;
		bounds.height = bounds.height+75;
		bounds.x = 0;
		bounds.y = 0;
		setBounds(bounds);
		setJMenuBar(getMenuBar_1());
		System.out.println(bounds);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bandit2 b = new Bandit2();
		b.setVisible(true);
	}
	
	

	private class panel extends JPanel implements Runnable{
		
		private JLabel lblOne;
		private JButton btnOne;
		private boolean buttonStateStart;
		private Rectangle bounds = new Rectangle(10, 11, 120, 245);
		private int zahl = 0;
		private Color color;
		
		private Thread neuerThread;
		
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
						start();
					}else {
						buttonStateStart = true;
						btnOne.setText("Start");
						neuerThread.interrupt();
						neuerThread = null;
					}
						
				});
			}
			return btnOne;
		}
		

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep( (int)(Math.random()*11+20) );
				}catch(InterruptedException ie) {
					return;
				}catch(Exception e) {
					e.printStackTrace();
				}
				if(++zahl > 9)	zahl = 0;
				lblOne.setText(zahl+"");
			}
		}
		
		public void stop(int i) {
			if(neuerThread == null) return;
			if(buttonStateStart == true) return;
			System.out.println(i + "  Start");
			new Thread(()-> {
				while(true) {
					try {
						Thread.sleep(10);
					}catch(Exception e) {
						e.printStackTrace();
					}
					if(zahl == i%10) {
						stop();
						break;
					}
				}
				System.out.println(i + "  Termianted");
			}).start();
		}
		
		public void stop() {
			if(neuerThread == null) return;
			if(buttonStateStart == true) return;
			neuerThread.interrupt();
			neuerThread = null;
			buttonStateStart = true;
			btnOne.setText("Start");
		}
		
		public void start() {
			if(buttonStateStart == false)return;
			buttonStateStart = false;
			btnOne.setText("Stop");
			neuerThread = new Thread(Bandit2.panel.this);
			neuerThread.start();
		}
	
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
		}
		return menuBar;
	}
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Menu");
			mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mnNewMenu.add(getMntmNewMenuItem());
			mnNewMenu.add(getMntmNewMenuItem2());
		}
		return mnNewMenu;
	}
	private JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("stop");
			mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmNewMenuItem.addActionListener(e->{
				for(int i=0; i<panels.length; i++) {
					((panel)panels[i]).stop(i);
				}
			});
		}
		return mntmNewMenuItem;
	}
	private JMenuItem getMntmNewMenuItem2() {
		if (mntmNewMenuItem2 == null) {
			mntmNewMenuItem2 = new JMenuItem("Start");
			mntmNewMenuItem2.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmNewMenuItem2.addActionListener(e->{
				for(int i=0; i<panels.length; i++) {
					((panel)panels[i]).start();
				}
			});
		}
		return mntmNewMenuItem2;
	}
}
