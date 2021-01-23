package aufgabe1.a1;

import static aufgabe1.a1.Fach.WIRTSCHAFTLICHESSTUDIUM;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student Peter = new Student();
		Peter.setName("Peter Honig");
		Peter.setMatnr(12345);
		Peter.setFach(WIRTSCHAFTLICHESSTUDIUM);
		System.out.println(Peter);
		System.out.println("Regelstudienzeit für sein Studium: " + Peter.getFach().regelstudienzeit() + " Semester. ");
	}

}
