package aufgabe5;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class Studentendaten extends AbstractTableModel{
	
	public static final int MAX_SEMESTER = 9;
	public static final int MATRIKELNUMMER_LAENGE = 5;
	public static final int MAX_ECTS = 210;
	private static final int fatalCount = 300;
	private static char STRSEP = '.';
	private static char VARSEP = '|';
	
	
	private List<Student> list;
	
	public Studentendaten() {
		list = new ArrayList<>();
		list.add(new Student(47110, 58, 2, "Nesquick"));
		list.add(new Student(46002, 30, 1, "Susie"));
		list.add(new Student(45112, 122, 4, "Hugo"));
		list.add(new Student(23567, 92, 3, "Karin"));
		list.add(new Student(150523, 30, 2, "Daniel Landau"));
		
//		for(char i='z'; i!='A'+1; i--) {
//			list.add(new Student(i, 30, 2, i+""));
//		}
		
	}
	

	@Override
	public String getColumnName(int col) {
		String name =  Student.class.getDeclaredFields()[col].getName();
		if(name.equals("ects")) return name.toUpperCase();
		else return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return Student.class.getDeclaredFields().length;
	}
	
	public void clear() {
		list.clear();
		fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex<0 || columnIndex>getColumnCount()-1) return null;
		if(rowIndex<0 || rowIndex>list.size()-1) return null;
 		
		Field var = list.get(rowIndex).getClass().getDeclaredFields()[columnIndex];
		String methodName = "get"+Character.toUpperCase(var.getName().charAt(0)) +var.getName().substring(1);
		try {
			return list.get(rowIndex).getClass().getDeclaredMethod(methodName, new Class<?>[0]).invoke( list.get(rowIndex) , new Object[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void checkName(String name) throws Exception {
		for(int i=0; i<name.length(); i++) {
			if(name.charAt(i)==STRSEP)
				throw new Exception("Das Zeichen '"+STRSEP+"' ist nicht erlaubt");
			if(name.charAt(i)==VARSEP)
				throw new Exception("Das Zeichen '"+VARSEP+"' ist nicht erlaubt");
		}
	}
	public void add(Student st) throws Exception{
		if((st.getMartrikelNr()+"").length()!=MATRIKELNUMMER_LAENGE) throw new Exception("Martrikelnummer muss "+MATRIKELNUMMER_LAENGE+"-stellig sein.");
		if(st.getEcts()>MAX_ECTS || st.getEcts()<0) throw new Exception(st.getEcts()+" liegt nicht im Intervall [0, "+MAX_ECTS+"]");
		if(st.getSemester()>MAX_SEMESTER || st.getSemester()<1) throw new Exception(st.getSemester()+" liegt nicht im Intervall [1, "+MAX_SEMESTER+"]");
		checkName(st.getName());
		for(Student student: list) {
			if(student.getMartrikelNr()==st.getMartrikelNr()) throw new Exception("Martrikel Nummer bereits vorhande");
		}
		list.add(st);
		fireTableRowsInserted(list.size(), list.size());
	}
	public void remove(int index) {
		list.remove(index);
		fireTableRowsDeleted(index, index);
	}
	public void remove(int index, int count) {
		for(int i=0; i<count; i++) 
			list.remove(index+i);
		fireTableRowsDeleted(index, index+count-1);
	}
	public void sort() {
		list.sort((s1, s2)->s1.compareTo(s2));
		fireTableDataChanged();
	}
	public void sortName() {
		list.sort(new Comparator<Student>() {

//			@Override
//			public int compare(Student o1, Student o2) {
//				if(o1.getName().equals(o2.getName())) return 0;
//				if(o1.getName().hashCode()>o2.getName().hashCode()) return 1;
//				else return -1;
//			}
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getName().equals(o2.getName())) return 0;
				int length = o1.getName().length()<=o2.getName().length()? o1.getName().length():o2.getName().length();
				for(int i=0; i<length; i++) {
					if(o1.getName().charAt(i) > o2.getName().charAt(i))	return 1;
					else if (o1.getName().charAt(i) < o2.getName().charAt(i)) return -1;
				}
				return 1;
			}
		});
		fireTableDataChanged();
	}
	public double semesterDurchschnitt() {
		return list.stream().map(s->s.getSemester()).mapToInt(i->i).average().getAsDouble();
	}
	public int regulaerStudierende() {
		return (int)list.stream().filter(s-> s.getSemester()<=7 && s.getEcts()>=s.getSemester()*30).count();
	}

	
//	public void saveToFile(File f) throws Exception{
//		try (FileOutputStream fOut = new FileOutputStream(f);
//				ObjectOutputStream out = new ObjectOutputStream(fOut)){
//			
//			out.writeInt(list.size());
//			Iterator<Student> it = list.iterator();
//			while(it.hasNext())	out.writeObject(it.next());
//				
//		}catch(Exception e) {
//			throw e;
//		}
//	}
//	
//	public void loadFile(File f) throws Exception{
//		List<Student> exc = new ArrayList<>();
//		int count = 0;
//		
//		try (FileInputStream fIn = new FileInputStream(f);
//				ObjectInputStream in = new ObjectInputStream(fIn)){
//		for(int i=in.readInt(); i>0; i--) {
//			Student s = (Student)in.readObject();	
//			if(list.stream().filter(s1->s1.getMartrikelNr()==s.getMartrikelNr()).count() == 0) {
//				list.add(s);
//				count++;
//			}else
//				exc.add(s);
//		}
//		}catch(Exception e) {
//			throw e;
//		}finally {
//			fireTableRowsInserted(list.size()-count+1, list.size());
//			System.out.println(exc.size());
//		}
//		if(exc.size()>0) {
//			StringBuilder s = new StringBuilder();
//			s.append("Folgende Studenten konnten nicht geladen werden, da ihre Martrikelnummer bereits vergeben sind: ");
//			list.forEach(s1->s.append("\n"+s1));
//			throw new Exception(s.toString());
//		}
//	}
	
	public void saveToFile(File f) throws Exception{
//		try (FileOutputStream fOut = new FileOutputStream(f);
//				ObjectOutputStream out = new ObjectOutputStream(fOut)){
//			
//			out.writeInt(list.size());
//			Iterator<Student> it = list.iterator();
//			while(it.hasNext())	out.writeObject(it.next());
//				
//		}catch(Exception e) {
//			throw e;
//		}
		
		try (PrintWriter pw =new PrintWriter(f)){
			pw.print(STRSEP);
			pw.print(VARSEP);
			list.forEach(al->{
				String s = String.format("%s%5$s%s%5$s%s%5$s%s%6$s", al.getMartrikelNr(), al.getName(), al.getEcts(),  al.getSemester(), VARSEP, STRSEP);
				pw.print(s);
			});
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void loadFile(File file) throws Exception{
			try( FileInputStream fIn = new FileInputStream(file);
					InputStreamReader in = new InputStreamReader(fIn)){
				load(in);
			}catch(LadeFehler lf) {
				throw lf;
			}catch(Exception e) {
				throw new FatalerLadeFehler("Fataler Lesefehler: " + e.getMessage());
			}
	}
	

	private void load(InputStreamReader in) throws Exception{
		System.out.println("Test");
		int count = 0;
		int fatalCounter = 0;
		List<Student> exc = new ArrayList<>();
		
		int c = in.read();
		if(c==-1)	throw new Exception("Datei nicht lesbar");
		STRSEP = (char) c;
		c = in.read();
		if(c==-1 || c==(int)STRSEP)	throw new Exception("Datei nicht lesbar");
		VARSEP = (char)c;
		
		StringBuilder s = new StringBuilder();
		while(true){
			c = in.read();
			fatalCounter++;
			if(c== -1)
				break;
			if(fatalCounter > fatalCount)	throw new Exception();
			
			if((char)c==STRSEP) {
				fatalCounter = 0;
				String[] sarr = s.toString().split("\\"+VARSEP);
				Student stu = null;
				try {
					stu = new Student(Integer.parseInt(sarr[0]), Integer.parseInt(sarr[2]), Integer.parseInt(sarr[3]), sarr[1]);
					checkName(stu.getName());
					for(int i=0; i<list.size(); i++) {
						if(list.get(i).getMartrikelNr()==stu.getMartrikelNr()) throw new MatriklnummerFehler();
					}
					list.add(stu);
				}catch(MatriklnummerFehler npe) {
					exc.add(stu);
				}catch(Exception e){
					e.printStackTrace();
					count++;
				}
				s = new StringBuilder();
			}else
				s.append((char)c);
		}
		
		String fehler = "";
		if(count>0) fehler += count+ " Zeilen konnten nicht gelesen werden\n";
		if(exc.size()>0) {
			fehler += "Die Matrikelnummer folgender Studenten waren bereits vergeben:";
			for(int i=0; i<list.size(); i++) 
				fehler += "\n"+list.get(i).toString();
		}
		if(fehler.length()>0) throw new LadeFehler(fehler);
	}
	private class MatriklnummerFehler extends Exception{	
	}
	private class FatalerLadeFehler extends LadeFehler{	
		FatalerLadeFehler(String s){
			super(s);
		}
	}
	private class LadeFehler extends Exception{	
		LadeFehler(String s){
			super(s);
		}
	}
}
