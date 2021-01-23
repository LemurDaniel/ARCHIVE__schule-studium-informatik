package stack.test1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class CalcGUI extends JFrame {
	private JLabel lblinfo;
	private JButton btnCheck;
	private JTextField tFpassword;
	
	private StackR<?> stack = new StackTest3();
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnPrintStack;
	private JButton btnClearStack;
	private JButton btnPush;
	private JButton btnPop;
	private JButton btnPeek;
	private JButton btnClear;
	
	private String[] eingaben = new String[6];
	private int pos = 0;
	private Number lastRes;
	
	public CalcGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Passwort Checker");
		getContentPane().setLayout(null);
		getContentPane().add(getLblinfo());
		getContentPane().add(getBtnCheck());
		getContentPane().add(getTFpassword());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnPrintStack());
		getContentPane().add(getBtnClearStack());
		getContentPane().add(getBtnPush());
		getContentPane().add(getBtnPop());
		getContentPane().add(getBtnPeek());
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Double.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		CalcGUI fenster = new CalcGUI();
		fenster.setVisible(true);
		fenster.setBounds(500, 250, 570, 400);
	}
	private JLabel getLblinfo() {
		if (lblinfo == null) {
			lblinfo = new JLabel("");
			lblinfo.setForeground(Color.RED);
			lblinfo.setFont(new Font("Dialog", Font.BOLD, 13));
			lblinfo.setBounds(39, 117, 477, 24);
		}
		return lblinfo;
	}
	private JButton getBtnCheck() {
		if (btnCheck == null) {
			btnCheck = new JButton("Rechne");
			btnCheck.setFont(new Font("Dialog", Font.BOLD, 13));
			btnCheck.setBounds(398, 82, 118, 24);
			btnCheck.addActionListener(arg0 -> {
				try {
					lblinfo.setText(null);
					textArea.setText(null);
					lastRes = stack.calcExp( tFpassword.getText(),arg1 -> textArea.append(arg1) );
					tFpassword.setText(lastRes+"");
				}catch(Exception e) {
					lblinfo.setText(e.getMessage());
					e.printStackTrace();
				}
			});
		}
		return btnCheck;
	}
	private JTextField getTFpassword() {
		if (tFpassword == null) {
			tFpassword = new JTextField();
			tFpassword.setHorizontalAlignment(SwingConstants.CENTER);
			tFpassword.setForeground(Color.LIGHT_GRAY);
			tFpassword.setFont(new Font("Dialog", Font.BOLD, 13));
			tFpassword.setBounds(29, 37, 487, 33);
			tFpassword.setColumns(10);
			tFpassword.addFocusListener( new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					tFpassword.setForeground(Color.BLACK);
					if(tFpassword.getText().equals("!!Eingabe in 'Reverse Polish Notation'!!")) tFpassword.setText(null);
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(tFpassword.getText()==null)	{
						tFpassword.setForeground(Color.LIGHT_GRAY);
						tFpassword.setText("!!Eingabe in 'Reverse Polish Notation'!!");
					}
				}
			});
			tFpassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==10) {
						btnPush.doClick();
						return;
					}
					
					if(e.getKeyCode() != 40 && e.getKeyCode() != 38)
						return;
					//for(int i=0; i<eingaben.length; i++) System.out.println(i+"--->"+eingaben[i]);
					String text = null;
					for(int i=0; i<eingaben.length; i++) {
						if(e.getKeyCode()==38) 		text = eingaben[pos++];
						else if(e.getKeyCode()==40)	text = eingaben[pos--];
						
						if(pos >= eingaben.length)	pos=0;
						else if(pos < 0 ) pos = eingaben.length-1;
						
						if(text!=null) {
							tFpassword.setText(text);
							break;
						}
					}

				}
			});
		}
		return tFpassword;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 152, 487, 199);
			scrollPane.setViewportView(getTextArea());
			scrollPane.setColumnHeaderView(getBtnClear());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		}
		return textArea;
	}
	private JButton getBtnPrintStack() {
		if (btnPrintStack == null) {
			btnPrintStack = new JButton("Print Stack");
			btnPrintStack.setFont(new Font("Dialog", Font.BOLD, 13));
			btnPrintStack.setBounds(29, 82, 118, 24);
			btnPrintStack.addActionListener(arg0 -> {
				textArea.append(" "+stack.getSize()+" Elements | Bottom of Stack:\n");
				stack.stream().forEach(a-> textArea.append("	"+a+" -> "+a.getClass().getSimpleName()+"\n"));
			});
		}
		return btnPrintStack;
	}
	private JButton getBtnClearStack() {
		if (btnClearStack == null) {
			btnClearStack = new JButton("Clear Stack");
			btnClearStack.setFont(new Font("Dialog", Font.BOLD, 13));
			btnClearStack.setBounds(222, 81, 118, 25);
			btnClearStack.addActionListener(arg0 -> {
				stack.clearStack();
				btnClear.doClick();
				btnPrintStack.doClick();
			});
		}
		return btnClearStack;
	}
	private JButton getBtnPush() {
		if (btnPush == null) {
			btnPush = new JButton("Push");
			btnPush.setFont(new Font("Dialog", Font.BOLD, 13));
			btnPush.setBounds(29, 11, 118, 24);
			btnPush.addActionListener(arg0->{
				try {
					if(eingaben[0]==null || !eingaben[0].equals(tFpassword.getText())) {
						for(int i=eingaben.length-1; i>0; i--) eingaben[i] = eingaben[i-1];
						eingaben[0] = tFpassword.getText();
					}
					
					btnClear.doClick();
					stack.push(tFpassword.getText());
					textArea.append("Push -> "+tFpassword.getText()+"\n");
					tFpassword.setText(null);
					btnPrintStack.doClick();
				}catch(Exception e) {
					lblinfo.setText(e.getMessage());
					e.printStackTrace();
				}
			});
		}
		return btnPush;
	}
	private JButton getBtnPop() {
		if (btnPop == null) {
			btnPop = new JButton("Pop");
			btnPop.setFont(new Font("Dialog", Font.BOLD, 13));
			btnPop.setBounds(398, 11, 118, 24);
			btnPop.addActionListener(arg0-> {
				try {
					textArea.append("Pop -> "+stack.peek()+" "+stack.peek().getClass().getSimpleName()+"\n");
					tFpassword.setText(stack.pop()+"");
				}catch(Exception e) {
					lblinfo.setText("Stack ist Leer");
				}
			});
		}
		return btnPop;
	}
	private JButton getBtnPeek() {
		if (btnPeek == null) {
			btnPeek = new JButton("Peek");
			btnPeek.setFont(new Font("Dialog", Font.BOLD, 13));
			btnPeek.setBounds(222, 11, 118, 24);
			btnPeek.addActionListener(arg0-> {
				try {
					textArea.append("Länge: "+stack.getSize()+" | Peek -> "+stack.peek()+" "+stack.peek().getClass().getSimpleName()+"\n");
				}catch(Exception e) {
					lblinfo.setText("Stack ist Leer");
				}
			});
		}
		return btnPeek;
	}
	private JButton getBtnClear() {
		if (btnClear == null) {
			btnClear = new JButton("clear");
			btnClear.setFont(new Font("Dialog", Font.BOLD, 13));
			btnClear.addActionListener(arg0->textArea.setText(null));
		}
		return btnClear;
	}
}
