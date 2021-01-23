package student;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("serial")
public class Student implements Comparable<Student>, Serializable{
		
	private ReadOnlyIntegerWrapper martrikelNr;
	private ReadOnlyStringWrapper name;
	private ReadOnlyIntegerWrapper  ects, semester;
	
	
	
	public Student(int martrikelNr, int ects, int semester, String name){
		this.martrikelNr = new ReadOnlyIntegerWrapper();
		this.martrikelNr.set(martrikelNr);
		this.ects =  new ReadOnlyIntegerWrapper();
		this.ects.set(ects);
		this.semester =  new ReadOnlyIntegerWrapper();
		this.semester.set(semester);
		this.name =  new ReadOnlyStringWrapper();
		this.name.set(name);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(o.getClass() != getClass()) return false;
		
		Student other = (Student)o;
		if(other.martrikelNr.get() != martrikelNr.get()) return false;
		if(other.ects.get() != ects.get()) return false;
		if(other.semester.get() != semester.get()) return false;
		
		if(name.get() == null) 
			if(other.name.get() != null) return false;
		else if(!name.get().equals(other.name.get())) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31 + ((this.name==null)? 0:this.name.hashCode());
	}


	@Override
	public int compareTo(Student o) {
		if(martrikelNr.get() > o.getMartrikelNr().get()) return 1;
		else if(martrikelNr.get() < o.getMartrikelNr().get()) return -1;
		else return 0;
	}
	
	
	public ReadOnlyIntegerProperty getEcts() {
		return ects.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getSemester() {
		return semester.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getMartrikelNr() {
		return martrikelNr.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getName() {
		return name.getReadOnlyProperty();
	}
	
	public String toString() {
		return String.format("%7d   %-15s   %d   %d", martrikelNr.get(), name.get(), ects.get(), semester.get());
	}


	public void setName(String string) {
		this.name.set(string);
	}
}
