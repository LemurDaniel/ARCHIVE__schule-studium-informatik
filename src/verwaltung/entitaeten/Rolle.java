package verwaltung.entitaeten;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

public class Rolle  {
	
	private int id;
	private String rolle;
	
	public Rolle(int id, String rolle) {
		this.id = id;
		this.rolle = rolle;
	}
	public String getRolle() {
		return rolle;
	}
	
	public int getId() {
		return id;
	}
	
	public ObservableValue<Rolle> getObservable(){
		ObservableValue<Rolle> ob = new ObservableValueBase<Rolle>() {
			@Override
			public Rolle getValue() {
				return Rolle.this;
			}
		};
		return ob;
	}
	
	@Override
	public String toString() {
		return rolle;
	}
}
