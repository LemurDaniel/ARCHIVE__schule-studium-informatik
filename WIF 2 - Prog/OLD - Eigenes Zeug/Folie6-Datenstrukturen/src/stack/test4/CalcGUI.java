package stack.test4;

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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class CalcGUI extends JFrame {
	private JLabel lblinfo;
	private JButton btnCheck;
	private JTextField tFpassword;
	
	private StackR<?> stack;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextArea textArea2;
	private JButton btnPrintStack;
	private JButton btnClearStack;
	private JButton btnPush;
	private JButton btnPop;
	private JButton btnPeek;
	private JButton btnClear;
	
	private String[] eingaben = new String[6];
	private int pos = 0;
	private JRadioButton rdbtnReversePolishNotation;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton_1;
	private JSplitPane splitPane;
	private JButton btnSwap;
	private boolean swapped = false;
	private JLabel label;
	
	public CalcGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Reverse Polish");
		stack = new StackTest4(a -> getTextArea_1().append(" "+a+"\n"));
		
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
		getContentPane().add(getRdbtnReversePolishNotation());
		getContentPane().add(getRdbtnNewRadioButton_1());
		getContentPane().add(getLabel());
		getTextArea_1();
	}

	public static void main(String[] args) {
		System.out.println("Integer Max: "+Integer.MAX_VALUE);
		System.out.println("Double Max:  "+Double.MAX_VALUE);
		System.out.println("Long Max:    "+Long.MAX_VALUE);
		System.out.println("RPNtest: "+StackR.RPNtest+" = "+StackR.RPNtestErg);
		System.out.println("RPtest: "+StackR.RPtest+" = "+StackR.RPtestErg);
		System.out.println("TestLoop: "+StackR.LoopTest);
		CalcGUI fenster = new CalcGUI();
		fenster.setVisible(true);
		fenster.setBounds(500, 250, 570, 790);
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
					label.setText(null);
					textArea.setText(null);
					if(eingaben[0]==null || !eingaben[0].equals(tFpassword.getText())) {
						for(int i=eingaben.length-1; i>0; i--) eingaben[i] = eingaben[i-1];
						eingaben[0] = tFpassword.getText();
					}
					
					textArea.append("Expression\n -> "+tFpassword.getText()+"\n");
					stack.calcExp(tFpassword.getText());
					tFpassword.setText(stack.pop().toString());
				}catch(Exception e) {
					label.setText(e.getMessage());
					lblinfo.setText(e.getClass().getName());
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
		tFpassword.setText("list");
		return tFpassword;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 224, 487, 513);
			scrollPane.setViewportView(getTextArea());
			scrollPane.setColumnHeaderView(getSplitPane());
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
				textArea.setText(null);
				textArea.append("Stack:\n "+stack.getSize()+" Elements | Bottom of Stack:\n");
				stack.streamStack().forEach(a-> textArea.append("  "+a+" -> "+a.getClass().getSimpleName()+"\n"));
				
				textArea.append("\n");
				textArea.append("SaveStack:\n "+stack.getSizeS()+" Elements | Bottom of Stack:\n");
				stack.streamSaveStack().forEach(a-> textArea.append("  "+a+" -> "+a.getClass().getSimpleName()+"\n"));
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
			//btnPush.setEnabled(false);
			btnPush.setFont(new Font("Dialog", Font.BOLD, 13));
			btnPush.setBounds(29, 11, 118, 24);
			btnPush.addActionListener(arg0->{
				try {
					lblinfo.setText(null);
					label.setText(null);
					if(eingaben[0]==null || !eingaben[0].equals(tFpassword.getText())) {
						for(int i=eingaben.length-1; i>0; i--) eingaben[i] = eingaben[i-1];
						eingaben[0] = tFpassword.getText();
					}
					
					textArea.setText(null);
					stack.calcExp(tFpassword.getText());
					textArea.append("Expression\n -> "+tFpassword.getText()+"\n");
					tFpassword.setText(null);
					btnPrintStack.doClick();
				}catch(Exception e) {
					label.setText(e.getMessage());
					lblinfo.setText(e.getClass().getSimpleName());
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
					lblinfo.setText(null);
					label.setText(null);
					textArea2.append("Pop -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n");
					tFpassword.setText(stack.pop()+"");
				}catch(Exception e) {
					label.setText("Stack ist Leer");
					lblinfo.setText(e.getClass().getSimpleName());
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
					lblinfo.setText(null);
					label.setText(null);
					textArea2.append(" Peek -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n");
				}catch(Exception e) {
					label.setText("Stack ist Leer");
					lblinfo.setText(e.getClass().getSimpleName());
				}
			});
		}
		return btnPeek;
	}
	private JButton getBtnClear() {
		if (btnClear == null) {
			btnClear = new JButton("clear");
			btnClear.setFont(new Font("Dialog", Font.BOLD, 13));
			btnClear.addActionListener(arg0->{
				textArea.setText(null);
				textArea2.setText(null);
			});
		}
		return btnClear;
	}
	private JRadioButton getRdbtnReversePolishNotation() {
		if (rdbtnReversePolishNotation == null) {
			rdbtnReversePolishNotation = new JRadioButton("Reverse Polish Notation");
			rdbtnReversePolishNotation.setSelected(true);
			buttonGroup.add(rdbtnReversePolishNotation);
			rdbtnReversePolishNotation.setFont(new Font("Dialog", Font.BOLD, 13));
			rdbtnReversePolishNotation.setBounds(320, 179, 198, 23);
		}
		return rdbtnReversePolishNotation;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Max Info");
			rdbtnNewRadioButton_1.setSelected(false);
			rdbtnNewRadioButton_1.setFont(new Font("Dialog", Font.BOLD, 13));
			rdbtnNewRadioButton_1.setBounds(39, 179, 198, 23);
			rdbtnNewRadioButton_1.addActionListener(l->{
				if(rdbtnNewRadioButton_1.isSelected())	stack.setInfo(StackR.MaxInfo);
				else stack.setInfo(StackR.MinInfo);
			});
			rdbtnNewRadioButton_1.doClick();
		}
		return rdbtnNewRadioButton_1;
	}
	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setResizeWeight(0.5);
			splitPane.setLeftComponent(getBtnClear());
			splitPane.setRightComponent(getButton_1());
		}
		return splitPane;
	}
	private JButton getButton_1() {
		if (btnSwap == null) {
			btnSwap = new JButton("swap to 2");
			btnSwap.setFont(new Font("Dialog", Font.BOLD, 13));
			btnSwap.addActionListener(l->{
			if(swapped) {
				scrollPane.setViewportView(textArea);
				btnSwap.setText("swap to 2");
			}else {
				scrollPane.setViewportView(textArea2);
				btnSwap.setText("swap to 1");
			}	
				swapped = !swapped;
			});
		}
		return btnSwap;
	}
	private JTextArea getTextArea_1() {
		if (textArea2 == null) {
			textArea2 = new JTextArea();
			textArea2.setFont(new Font("Monospaced", Font.PLAIN, 13));
		}
		return textArea2;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setForeground(Color.RED);
			label.setFont(new Font("Dialog", Font.BOLD, 13));
			label.setBounds(39, 141, 477, 24);
		}
		return label;
	}
}
