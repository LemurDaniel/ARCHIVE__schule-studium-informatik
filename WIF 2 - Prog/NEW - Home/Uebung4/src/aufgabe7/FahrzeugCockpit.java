package aufgabe7;

public interface FahrzeugCockpit {

	public void bewegeLenkrad();
	public void gibGas();
	public void bremse();
	
	default public void hatEDrive() {
		System.out.println("Der " + this.getClass().getSimpleName() + " hat keinen EDrive");
	}
	
	default public void metaDaten() {
		String[] name = this.getClass().getName().split("\\.");
		System.out.println( name[name.length-1] + "; " + name[name.length-2] );
	}
}
