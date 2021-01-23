package student;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aufgabe4.DB_Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("serial")
public class Studentendaten extends DB_Manager {
	
	public static final int MAX_SEMESTER = 9;
	public static final int MATRIKELNUMMER_LAENGE = 6;
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
		if(true) return;
			for(int i=200000; i<900001; i++) {
				Student st = new Student( i, (int)(Math.random()*211), ((int)(Math.random()*9))+1, "");
				st.setName("Student Test_"+st.getMartrikelNr().intValue());
				System.out.println(i+"---"+st);
				try {
					this.add(st);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
			}
	}
	
	public void getAll() throws SQLException {
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select * from Studenten");
			list.clear();
			while(rs.next()) {
				list.add(new Student(rs.getInt("martnr"), rs.getInt("ects"), rs.getInt("semester"), rs.getString("name")));
			}
		}
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
	
	public boolean checkMatnr(int nr) throws SQLException {
		try(Connection con = getCon();){
			return con.createStatement().executeQuery("select * from Studenten where martnr="+nr).next();
		}
	}
	
	/** ADD REMOVE 
	 * @throws SQLException */
	public void add(Student st) throws SQLException{
		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("insert into studenten values(?, ?, ?, ?)");
			ps.setInt(1, st.getMartrikelNr().intValue());
			ps.setString(2, st.getName().getValue());
			ps.setInt(3, st.getSemester().intValue());
			ps.setInt(4, st.getEcts().intValue());
			ps.execute();
		}
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
	
}
