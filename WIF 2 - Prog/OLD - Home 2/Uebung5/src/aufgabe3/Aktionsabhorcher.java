package aufgabe3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aktionsabhorcher implements ActionListener {

	Content c;
	
	public Aktionsabhorcher(Content c) {
		this.c = c;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.activate();
	}
	
}
