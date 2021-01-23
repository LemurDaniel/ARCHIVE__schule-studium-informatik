package aufgabe1.weiteres;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import aufgabe1.a1.Fach;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ST_GUI extends JFrame {
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblFach;
	private JTextField tfMatNr;
	private JTextField tfName;
	private JComboBox<Fach> comboBox;
	private JButton btnErfassen;
	private JPanel pane;
	
	private ArrayList<Student> students;
	private JLabel lblFehler;
	private JLabel lblFehler2;
	
	private JComboBox<String> cBstudents;
	private DefaultComboBoxModel<String> dcbmStudents;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mMenu;
	private JMenuItem mOpen;
	private JMenuItem mSave;
	private JMenuItem mNew;

	public ST_GUI() {
		students = new ArrayList<>();
		dcbmStudents = new DefaultComboBoxModel<>();
		dcbmStudents.addElement("Neuen Studenten Hinzufügen");
		setTitle("Studenten Verwaltung");
		
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblName());
		getContentPane().add(getLblFach());
		getContentPane().add(getTfMatNr());
		getContentPane().add(getTfName());
		getContentPane().add(getComboBox());
		getContentPane().add(getBtnErfassen());
		getContentPane().add(getPane());
		getContentPane().add(getLblFehler());
		getContentPane().add(getLblFehler2());
		getContentPane().add(getCBstudents());
		setJMenuBar(getMenuBar_1());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ST_GUI t = new ST_GUI();
		t.setBounds(500, 250, 500, 410);
		t.setVisible(true);
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Matrikelnummer:");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setBounds(37, 79, 133, 23);
		}
		return lblNewLabel;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
			lblName.setFont(new Font("Dialog", Font.BOLD, 13));
			lblName.setBounds(37, 113, 133, 23);
		}
		return lblName;
	}
	private JLabel getLblFach() {
		if (lblFach == null) {
			lblFach = new JLabel("Fach:");
			lblFach.setFont(new Font("Dialog", Font.BOLD, 13));
			lblFach.setBounds(37, 147, 133, 23);
		}
		return lblFach;
	}
	private JTextField getTfMatNr() {
		if (tfMatNr == null) {
			tfMatNr = new JTextField();
			tfMatNr.setFont(new Font("Dialog", Font.BOLD, 13));
			tfMatNr.setBounds(180, 78, 100, 23);
			tfMatNr.setColumns(10);
		}
		return tfMatNr;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setFont(new Font("Dialog", Font.BOLD, 13));
			tfName.setColumns(10);
			tfName.setBounds(180, 112, 244, 23);
		}
		return tfName;
	}
	private JComboBox<Fach> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Fach>();
			comboBox.setFont(new Font("Dialog", Font.BOLD, 13));
			comboBox.setBounds(180, 147, 244, 23);
			comboBox.setModel( new DefaultComboBoxModel<Fach>(Fach.values()) );
			comboBox.setSelectedIndex(0);
		}
		return comboBox;
	}
	private JButton getBtnErfassen() {
		if (btnErfassen == null) {
			btnErfassen = new JButton("erfassen");
			btnErfassen.setFont(new Font("Dialog", Font.BOLD, 13));
			btnErfassen.setBounds(180, 181, 133, 23);
			btnErfassen.addActionListener( e -> {
				
				lblFehler.setText(null);
				lblFehler2.setText(null);
				
				Student student;
				if(cBstudents.getSelectedIndex() == cBstudents.getModel().getSize()-1)
					student = new Student();
				else
					student = students.get(cBstudents.getSelectedIndex());
				
				try {
					
					int matnr = Integer.parseInt(tfMatNr.getText());
					for(Student stud2: students) {
						if(stud2 == student)
							continue;
						if(stud2.getMatnr()== matnr)
							throw new Exception("Martrikelnummer bereits vorhanden");
						if(stud2.getName().equals(tfName.getText()))
							throw new Exception("Name bereits vorhanden");
					}
					student.setMatnr( matnr );
					student.setName(tfName.getText());
					student.setFach( (Fach)comboBox.getSelectedItem() );		
				}catch(Exception e1) {
					lblFehler.setText(e1.getClass().getSimpleName());
					lblFehler2.setText(e1.getMessage());
					return;
				}
				
				if(cBstudents.getSelectedIndex() == cBstudents.getModel().getSize()-1) {
					students.add(student);
					students.sort((s, s2) -> s.compareTo(s2) );
					dcbmStudents.insertElementAt(student.getName(), students.indexOf(student));
					tfName.setText(null);
					tfMatNr.setText(null);
					cBstudents.setSelectedIndex(students.size()-1);
				}else {
					int index = cBstudents.getSelectedIndex();
					dcbmStudents.removeElementAt(index);
					dcbmStudents.insertElementAt(student.getName(), index);
					dcbmStudents.setSelectedItem(student.getName());
				}
				textArea.setText(null);
				textArea.append(student.toString()+"\n");
			});
		}
		return btnErfassen;
	}
	private JPanel getPane() {
		if (pane == null) {
			pane = new JPanel();
			pane.setBounds(10, 264, 464, 75);
			pane.setBorder(new TitledBorder(null, "Studenten", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.DIALOG, Font.BOLD, 13), java.awt.Color.BLACK));
			pane.setLayout(null);
			pane.add(getTextArea());
		}
		return pane;
	}
	private JLabel getLblFehler() {
		if (lblFehler == null) {
			lblFehler = new JLabel("");
			lblFehler.setFont(new Font("Dialog", Font.BOLD, 13));
			lblFehler.setBounds(37, 215, 387, 16);
			lblFehler.setForeground(Color.red);
		}
		return lblFehler;
	}
	private JLabel getLblFehler2() {
		if (lblFehler2 == null) {
			lblFehler2 = new JLabel("");
			lblFehler2.setForeground(Color.RED);
			lblFehler2.setFont(new Font("Dialog", Font.BOLD, 13));
			lblFehler2.setBounds(47, 237, 377, 16);
		}
		return lblFehler2;
	}
	private JComboBox<String> getCBstudents() {
		if (cBstudents == null) {
			cBstudents = new JComboBox<>();
			cBstudents.setFont(new Font("Dialog", Font.BOLD, 13));
			cBstudents.setBounds(82, 32, 281, 23);	
			cBstudents.setModel(dcbmStudents);
			cBstudents.addItemListener( l -> {
				if(l.getStateChange()==ItemEvent.DESELECTED)
					return;
				
				if(cBstudents.getSelectedIndex() == cBstudents.getModel().getSize()-1) {
					textArea.setText(null);
					tfName.setText(null);
					tfMatNr.setText(null);
					comboBox.setSelectedIndex(0);
				}else {
					textArea.setText(null);
					tfName.setText(students.get(cBstudents.getSelectedIndex()).getName());
					tfMatNr.setText(students.get(cBstudents.getSelectedIndex()).getMatnr()+"");
					textArea.append(students.get(cBstudents.getSelectedIndex()).toString()+"\n");
					comboBox.setSelectedItem(students.get(cBstudents.getSelectedIndex()).getFach());
				}
			});
		}
		return cBstudents;
	}
	
	
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
			textArea.setBounds(10, 21, 444, 43);
		}
		return textArea;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMMenu());
		}
		return menuBar;
	}
	private JMenu getMMenu() {
		if (mMenu == null) {
			mMenu = new JMenu("Men\u00FC");
			mMenu.add(getMNew());
			mMenu.add(getMOpen());
			mMenu.add(getMSave());
		}
		return mMenu;
	}
	private JMenuItem getMOpen() {
		if (mOpen == null) {
			mOpen = new JMenuItem("open");
			mOpen.setFont(new Font("Segoe UI", Font.BOLD, 12));
			mOpen.addActionListener(l->{
				JFileChooser fc = new JFileChooser();
				if(JFileChooser.APPROVE_OPTION != fc.showOpenDialog(ST_GUI.this))
					return;
				
				students.clear();
				String last = dcbmStudents.getElementAt(dcbmStudents.getSize()-1);
				dcbmStudents.removeAllElements();
				dcbmStudents.addElement(last);
				
				boolean damaged = false;
				int fatalCounter = 0;
				
				try(FileInputStream Fin = new FileInputStream(fc.getSelectedFile());
						InputStreamReader in = new InputStreamReader(Fin)){
					
					StringBuilder sb = new StringBuilder();
					while(true) {
						int ci = in.read();
						fatalCounter++;
						if(students.size()>50)
							throw new Exception("Zu Viele Studenten");
						if(fatalCounter > 200)
							throw new Exception("Datei kann nicht weiter gelesen werden");
						if(ci==-1)
							break;
						
						char c = (char)ci;
						
						// Lesen der Studenten
						if(c=='.') {
							String[] sArr = sb.toString().split("\\|");
							Student stud = new Student();
							try {
								stud.setName(sArr[0]);
								int matnr = Integer.parseInt(sArr[1]);
								for(Student stud2: students) {
									if(stud2.getMatnr()== matnr || stud2.getName()==stud.getName())
										throw new Exception();
								}
								stud.setMatnr(matnr);
								stud.setFach(Fach.values()[Integer.parseInt(sArr[2])]);							
								students.add(stud);
								dcbmStudents.insertElementAt(stud.getName(), students.size()-1);
							}catch(Exception e1) {
								damaged = true;
							}
							sb = new StringBuilder();
							fatalCounter = 0;
						//
						}else {
							sb.append(c);
						}
					}
					if(damaged)
						throw new Exception("Datei beschädigt");
					
				}catch(Exception e) {
					lblFehler.setText(e.getClass().getSimpleName());
					lblFehler2.setText(e.getMessage());
					e.printStackTrace();
				}
			});
		}
		return mOpen;
	}
	private JMenuItem getMSave() {
		if (mSave == null) {
			mSave = new JMenuItem("save");
			mSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
			mSave.addActionListener(l->{
				JFileChooser fc = new JFileChooser();
				if(JFileChooser.APPROVE_OPTION != fc.showSaveDialog(ST_GUI.this))
					return;
				
				try(PrintWriter pw = new PrintWriter(fc.getSelectedFile())){
					students.forEach( a -> pw.print(a.getName()+"|"+a.getMatnr()+"|"+a.getFach().ordinal()+".") );
				}catch(Exception e) {
					lblFehler.setText(e.getClass().getSimpleName());
					lblFehler2.setText(e.getMessage());
				}
			});
		}
		return mSave;
	}
	private JMenuItem getMNew() {
		if (mNew == null) {
			mNew = new JMenuItem("new");
			mNew.setFont(new Font("Segoe UI", Font.BOLD, 12));
			mNew.addActionListener(l->{
				students.clear();
				String last = dcbmStudents.getElementAt(dcbmStudents.getSize()-1);
				dcbmStudents.removeAllElements();
				dcbmStudents.addElement(last);
				
				Student s;
				for(Fach f:Fach.values()) {
					try {
						s = new Student();
						String[] name = Names.values()[f.ordinal()%Names.values().length].toString().split("_");
						s.setName(name[0]+" "+name[1]);
						s.setMatnr( 10000+f.ordinal() );
						s.setFach(f);
						
						students.add(s);
						dcbmStudents.insertElementAt(s.getName(), students.size()-1);
					}catch(Exception e) {
						e.printStackTrace();
						System.exit(0);
					}
				}
				
			});
		}
		return mNew;
	}
	
	
	
	
	public enum Names {
		Hans_Peter, Paula_Schmidt, Christian_Kracht, Hubert_Hubt, Michaela_Mutschidonat, Jet_Li, Franz_Freibauer, Manuel_Miebert, Kunold_Kranz;
	}
}
