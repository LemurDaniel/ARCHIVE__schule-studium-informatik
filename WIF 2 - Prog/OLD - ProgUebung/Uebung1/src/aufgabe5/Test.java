package aufgabe5;

public class Test {

	public static void main(String[] args) {
		Robot r = new Robot("Tom", Robot.ALL);
		
		//while(true) 
		//	r.operate();
			
		r.alleEingaben();
		
		
		System.out.println("\n\n *** New Robot *** \n\n");
		
		r = new Robot("Tom2", Robot.ARM, Robot.ARM, Robot.FNG, Robot.FNG);
		r.alleEingaben();
		
	}

}
