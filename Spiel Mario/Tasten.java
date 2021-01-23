
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*; 

class Tasten extends JFrame implements ActionListener, KeyListener
{
	private boolean oben = false, links = false, rechts = false, unten = false;
	
	Tasten()
	{
        this.addKeyListener(this);
        
	  //Layout-Definition

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
	}

	public void keyPressed(KeyEvent e)
	{
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
        	links = true;
        	func();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
        	rechts = true;
        	func();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
        	oben = true;
        	func();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
        	unten = true;
        	func();
        }
        
    }

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
        	links = false;
			func();
		}

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
        	rechts = false;
        	func();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
        	oben = false;
        	func();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
        	unten = false;
        	func();
        }
        
    }

	public void keyTyped(KeyEvent e){}
		
	public void actionPerformed(ActionEvent e)
    {
    	//auch nicht relevant, da die Knöpfe nicht mehr gebraucht werden
    }
	
	void func()
	{
		if (links && oben)
		{
			System.out.println("links oben");
		}
		else if (oben)
		{
		    System.out.println("oben");
		}
		else if (rechts && oben)
		{
			System.out.println("rechts oben");
		}
		else if (links)
		{
			System.out.println("links");
		}
		else if (rechts)
		{
			System.out.println("rechts");
		}
		else if (links && unten)
		{
			System.out.println("links unten");
		}
		else if (unten)
		{
			System.out.println("unten");
		}
		else if (rechts && unten)
		{
			System.out.println("rechts unten");
		}
	}
	
	public static void main (String[] args)
	{
		new Tasten();
	}
	
	
   
}