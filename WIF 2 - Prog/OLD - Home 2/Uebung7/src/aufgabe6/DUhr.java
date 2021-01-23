package aufgabe6;

import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DUhr extends JPanel implements Runnable{

	private DateTimeFormatter  dtf;
	private JLabel text;
	
	public DUhr() {
		add(getText());		
		dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		new Thread(this).start();
	}
	
	private JLabel getText() {
		if(text == null) {
			text = new JLabel();
			text.setBounds(0, 0, 400, 50);
			text.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return text;
	}
	@Override
	public void run() {
		while(true) {
			text.setText(dtf.format(LocalDateTime.now()));
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
