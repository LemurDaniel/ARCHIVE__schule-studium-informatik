package aufgabe5;

public class Adel extends Einwohner {

	@Override
	public int steuer() {
		return super.steuer()>20 ? super.steuer():20;
	}
}
