package aufgabe7;

public class Motorrad extends Fahrzeug {

	private String fahrzeugtyp = "Motorrad";
	
	@Override
	public void print() {
		super.print();
		System.out.println("Fahrzeugtyp: " + fahrzeugtyp);
	}
}
