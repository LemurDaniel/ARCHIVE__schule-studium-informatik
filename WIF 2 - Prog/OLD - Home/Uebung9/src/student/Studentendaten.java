package student;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("serial")
public class Studentendaten {
	
	public static final int MAX_SEMESTER = 9;
	public static final int MATRIKELNUMMER_LAENGE = 5;
	public static final int MAX_ECTS = 210;
	private static final int fatalCount = 300;
	private static char STRSEP = '.';
	private static char VARSEP = '|';
	
	private static Studentendaten instance;
	public static Studentendaten instance() {
		if(instance==null)
			instance = new Studentendaten();
		return instance;
	}
	
	private ObservableList<Student> list;
	private Studentendaten() {
		list = FXCollections.observableArrayList();
	}
	
	/**Default */
	public void setDefault() {
		list.add(new Student(47110, 58, 2, "Nesquick"));
		list.add(new Student(46002, 30, 1, "Susie"));
		list.add(new Student(45112, 122, 4, "Hugo"));
		list.add(new Student(23567, 92, 3, "Karin"));
		list.add(new Student(150523, 30, 2, "Daniel Landau"));
		
	}
	
	
	/** LIST */
	public ObservableList<Student> getList() {
		return list;
	}
	
	public void checkName(String name) throws Exception {
		if(name.equals("TestingMode")) {
			for(char i='z'; i!='A'+1; i--) {
			list.add(new Student(i, 30, 2, i+""));
			}
		}
		
		for(int i=0; i<name.length(); i++) {
			if(name.charAt(i)==STRSEP)
				throw new Exception("Das Zeichen '"+STRSEP+"' ist nicht erlaubt");
			if(name.charAt(i)==VARSEP)
				throw new Exception("Das Zeichen '"+VARSEP+"' ist nicht erlaubt");
		}
	}
	
	/** ADD REMOVE */
	public void add(Student st){
		list.add(st);
	}
	public void remove(int index) {
		list.remove(index);
	}
	

	/** Statistiken */
	public double semesterDurchschnitt() {
		return list.stream().map(s->s.getSemester()).mapToInt(i->i.get()).average().getAsDouble();
	}
	public int regulaerStudierende() {
		return (int)list.stream().filter(s-> s.getSemester().get()<=7 && s.getEcts().get()>=s.getSemester().get()*30).count();
	}

	
	
	
	/**	LOAD SAVE */
	
	public void saveToFile(File f) throws Exception{	
		try (PrintWriter pw =new PrintWriter(f)){
			pw.print(STRSEP);
			pw.print(VARSEP);
			list.forEach(al->{
				String s = String.format("%s%5$s%s%5$s%s%5$s%s%6$s", al.getMartrikelNr().get(), al.getName().get(), al.getEcts().get(),  al.getSemester().get(), VARSEP, STRSEP);
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
		int count = 0;
		int vorhandenCount = 0;
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
					checkName(stu.getName().toString());
					for(int i=0; i<list.size(); i++) {
						if(list.get(i).getMartrikelNr().get()==stu.getMartrikelNr().get()) {
							if(!list.get(i).equals(stu))
								throw new MatriklnummerFehler();
							else 
								throw new LadeFehler("",false);
						}
					}
					list.add(stu);
				}catch(MatriklnummerFehler npe) {
					exc.add(stu);
				}catch(LadeFehler le) {
					vorhandenCount++;
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
		if(vorhandenCount>0) fehler += vorhandenCount+ " identische Zeilen waren bereits vorhanden";
		if(exc.size()>0) {
			fehler += "Die Matrikelnummer folgender Studenten waren bereits vergeben:";
			for(int i=0; i<list.size(); i++) 
				fehler += "\n"+list.get(i).toString();
		}
		if(fehler.length()>0) throw new LadeFehler(fehler, count>0||exc.size()>0);
	}
	

	private class MatriklnummerFehler extends Exception{	
	}
	private class FatalerLadeFehler extends LadeFehler{	
		FatalerLadeFehler(String s){
			super(s, true);
		}
	}
	public class LadeFehler extends Exception{	
		public boolean error;
		LadeFehler(String s, boolean error){
			super(s);
			this.error = error;
		}
	}
	
}
