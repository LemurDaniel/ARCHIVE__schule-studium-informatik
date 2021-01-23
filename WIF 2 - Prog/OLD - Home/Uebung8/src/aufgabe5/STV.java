package aufgabe5;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class STV extends JFrame {
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnStudenten;
	private JMenuItem mNeu;
	private JMenuItem mSort;
	private JMenuItem mStatistik;
	private JTable table;
	
	private Studentendaten data = new Studentendaten();
	private JMenu mMSort;
	private JMenuItem mMatnr;
	private JMenuItem mntmSave;
	private JMenu mnDelete;
	private JMenuItem mntmAll;
	private JMenuItem mntmSelected;
	private JMenu mnLoad;
	private JMenuItem mntmNew;
	private JMenuItem mntmAdd;
	
	public STV() {
		setTitle("Studentenverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		setJMenuBar(getMenuBar_1());
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				STV s = new STV();
				s.setBounds(500, 300, 455, 300);
				s.setVisible(true);
			}
			
		});
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	//Menu
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnStudenten());
		}
		return menuBar;
	}
	private JMenu getMnStudenten() {
		if (mnStudenten == null) {
			mnStudenten = new JMenu("Studenten");
			mnStudenten.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mnStudenten.add(getMnLoad());
			mnStudenten.add(getMntmSave());
			mnStudenten.add(getMNeu());
			mnStudenten.add(getMnDelete());
			mnStudenten.add(getMMSort());
			mnStudenten.add(getMStatistik());
		}
		return mnStudenten;
	}
	private JMenuItem getMNeu() {
		if (mNeu == null) {
			mNeu = new JMenuItem("add new");
			mNeu.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mNeu.addActionListener(al-> {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						STZ.instance(data).setVisible(true);
					}
				});
			});
		}
		return mNeu;
	}
	private JMenuItem getMSort() {
		if (mSort == null) {
			mSort = new JMenuItem("Name");
			mSort.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mSort.addActionListener(al->data.sortName());
		}
		return mSort;
	}
	private JMenuItem getMStatistik() {
		if (mStatistik == null) {
			mStatistik = new JMenuItem("Statistik");
			mStatistik.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mStatistik.addActionListener(al->{
				String text = "Semesterdurchschnitt: "+ String.format("%.4f", data.semesterDurchschnitt());
				text+="\nRegulär Studierende: "+ data.regulaerStudierende();
				JOptionPane.showMessageDialog(STV.this, text, "Statistiken", JOptionPane.INFORMATION_MESSAGE);
			});
		}
		return mStatistik;
	}
	// Menu end
	
	
	
	private JTable getTable() {
		if (table == null) {
			table = new JTable(data) {		
				@Override
			    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
			        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
			        if(Student.class.getDeclaredFields()[arg1].getType().equals(String.class))
			        	render.setHorizontalAlignment(SwingConstants.LEFT);
			        else
			        	render.setHorizontalAlignment(SwingConstants.RIGHT);
			        return render;
			    }
			};
			table.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return table;
	}

	private JMenu getMMSort() {
		if (mMSort == null) {
			mMSort = new JMenu("sort");
			mMSort.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mMSort.add(getMSort());
			mMSort.add(getMMatnr());
		}
		return mMSort;
	}
	private JMenuItem getMMatnr() {
		if (mMatnr == null) {
			mMatnr = new JMenuItem("Matnr");
			mMatnr.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mMatnr.addActionListener(al->data.sort());
		}
		return mMatnr;
	}
	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("save");
			mntmSave.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmSave.addActionListener(al->{
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(STV.this) != JFileChooser.APPROVE_OPTION) 
					return;
				
				try {
					data.saveToFile(fc.getSelectedFile());		
					JOptionPane.showMessageDialog(STV.this, "Speichern erfolgreich", "Speichern", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(STV.this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.WARNING_MESSAGE);
				}
			});
		}
		return mntmSave;
	}
	private JMenu getMnDelete() {
		if (mnDelete == null) {
			mnDelete = new JMenu("delete");
			mnDelete.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mnDelete.add(getMntmAll());
			mnDelete.add(getMntmSelected());
		}
		return mnDelete;
	}
	private JMenuItem getMntmAll() {
		if (mntmAll == null) {
			mntmAll = new JMenuItem("all");
			mntmAll.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmAll.addActionListener(al->data.clear());
		}
		return mntmAll;
	}
	private JMenuItem getMntmSelected() {
		if (mntmSelected == null) {
			mntmSelected = new JMenuItem("selected");
			mntmSelected.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmSelected.addActionListener(al->{
				data.remove(table.getSelectedRow(), table.getSelectedRowCount());
			});
		}
		return mntmSelected;
	}
	private JMenu getMnLoad() {
		if (mnLoad == null) {
			mnLoad = new JMenu("load");
			mnLoad.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mnLoad.add(getMntmNew());
			mnLoad.add(getMntmAdd());
		}
		return mnLoad;
	}
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("new");
			mntmNew.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmNew.addActionListener(al->{
				JFileChooser fc = new JFileChooser();
				if(fc.showOpenDialog(STV.this) != JFileChooser.APPROVE_OPTION)
					return;
				data.clear();
				try {
					data.loadFile(fc.getSelectedFile());
					JOptionPane.showMessageDialog(STV.this, "Laden erfolgreich","Laden von Daten", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(STV.this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.WARNING_MESSAGE);
				}
			});
		}
		return mntmNew;
	}
	private JMenuItem getMntmAdd() {
		if (mntmAdd == null) {
			mntmAdd = new JMenuItem("add");
			mntmAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
			mntmAdd.addActionListener(al->{
				JFileChooser fc = new JFileChooser();
				if(fc.showOpenDialog(STV.this) != JFileChooser.APPROVE_OPTION)
					return;
				try {
					data.loadFile(fc.getSelectedFile());
					JOptionPane.showMessageDialog(STV.this, "Laden erfolgreich","Laden von Daten", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(STV.this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.WARNING_MESSAGE);
				}
			});
		}
		return mntmAdd;
	}
}
