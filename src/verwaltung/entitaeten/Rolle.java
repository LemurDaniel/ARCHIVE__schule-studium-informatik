package verwaltung.entitaeten;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

public class Rolle extends Entitaet {
	
	private String rolle;
	
	public Rolle(int id, String rolle) {
		super(id);
		this.rolle = rolle;
	}
	public String getRolle() {
		return rolle;
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
}
