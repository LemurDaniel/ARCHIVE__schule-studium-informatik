package aufgabe3;

import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Label;

@SuppressWarnings("serial")
public class Content extends Panel {
	private Button button;
	private Label label;
	
	private int count = 0;

	Content(){
		setLayout(null);
		add(getButton());
		add(getLabel());
		
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame t = new JFrame();
		t.setVisible(true);
		t.setBounds(500,350,600,300);
		t.getContentPane().add(new Content());
	}
	private Button getButton() {
		if (button == null) {
			button = new Button("Test");
			button.setBounds(72, 97, 148, 45);
			button.addActionListener( new Aktionsabhorcher(this));
			MouseBla mbla = new MouseBla();
			button.addMouseListener( mbla );
			button.addMouseMotionListener( mbla );
		}
		return button;
	}
	private Label getLabel() {
		if (label == null) {
			label = new Label("Hallo Nr X");
			label.setAlignment(Label.CENTER);
			label.setVisible(false);
			label.setBounds(72, 148, 148, 45);
		}
		return label;
	}

	public void activate() {
		label.setVisible(true);
		label.setText("Hallo Nr. " + count++);
	}
	
	
	private class MouseBla implements MouseListener, MouseMotionListener{

		private boolean entered;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			entered = true;
			label.setVisible(true);
			label.setText(  "   X: " + e.getX() +
							"   Y: " + e.getY());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			label.setVisible(false);
			entered = false;
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println();
			if (!entered)
				return;		
			
			label.setText(  "   X: " + e.getX() +
							"   Y: " + e.getY());
		}
		
	}
}
