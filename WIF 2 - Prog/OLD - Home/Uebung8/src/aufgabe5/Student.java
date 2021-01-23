package aufgabe5;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Comparable<Student>, Serializable{
		
	private int martrikelNr;
	private String name;
	private int  ects, semester;
	
	public Student(int martrikelNr, int ects, int semester, String name){
		this.martrikelNr = martrikelNr;
		this.ects = ects;
		this.semester = semester;
		this.name = name;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(o.getClass() != getClass()) return false;
		
		Student other = (Student)o;
		if(other.martrikelNr != martrikelNr) return false;
		if(other.ects != ects) return false;
		if(other.semester != semester) return false;
		
		if(name == null) 
			if(other.name != null) return false;
		else if(!name.equals(other.name)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31 + ((this.name==null)? 0:this.name.hashCode());
	}


	@Override
	public int compareTo(Student o) {
		if(martrikelNr > o.getMartrikelNr()) return 1;
		else if(martrikelNr < o.getMartrikelNr()) return -1;
		else return 0;
	}
	
	
	public int getEcts() {
		return ects;
	}
	public int getSemester() {
		return semester;
	}
	public int getMartrikelNr() {
		return martrikelNr;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return String.format("%7d   %-15s   %d   %d", martrikelNr, name, ects, semester);
	}
}
