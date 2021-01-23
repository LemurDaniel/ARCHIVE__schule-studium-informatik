package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;
import verwaltung.entitaeten.Rolle;

public class Personenverwaltung extends Unterverwaltung<Person>{

	private static Map<Integer, Rolle> rolleMap;
	public static void ladeRollen(Connection con) throws SQLException{
		rolleMap =  new HashMap<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select id, rolle from rolle")){
			while(rs.next()) 
				rolleMap.put(rs.getInt(1), new Rolle(rs.getInt(1), rs.getString(2)));
		}	
	}
	
	public static ObservableList<Rolle> getRollen() {
		return FXCollections.observableArrayList(rolleMap.values());
	}
	
	
	
	/**  **/
	public ObservableList<PersonMitRolle> pmrliste;
	
	public Personenverwaltung(Film film) {
		super(film);
		pmrliste = FXCollections.observableArrayList();
	}
	
	
	
	public ObservableList<PersonMitRolle> getPersonenMitRollen(){
		return pmrliste;
	}
	private void aktualisierePmrliste() {
		pmrliste.clear();
		getList().forEach(per->pmrliste.addAll(per.getPersonenMitRolle()));
	}
	
	@Override
	public boolean addEntitaet(Person per) {
		if(!super.addEntitaet(per)) return false;
		per.getPersonenMitRolle().forEach(item->{
			if(!pmrliste.contains(item)	)	
					pmrliste.add(item);	
		});
		return true;
	}	
	@Override
	public void reset() {
		super.reset();
		aktualisierePmrliste();
		pmrliste.forEach(item->{
			item.getDeleteProperty().set(false);
			item.getUpdateProperty().set(false);
		});
	}
	
	@Override
	public void lade(Connection con) throws SQLException {
		super.lade(con);
		String sql = "Select pid, vorname, name, rid from person "
					+"inner join film_person_rolle on pid = person.id "
					+"where fid="+film.getId()+" order by pid";
		
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)){
			
			Person person = null;
			while(rs.next()) {
				if(person == null || person.getId() != rs.getInt(1)) {
					person = new Person(rs.getInt(1), rs.getString(2), rs.getString(3));
					addObj(person);
				}
				person.addRolle(rolleMap.get(rs.getInt("rid")));
			}
			getList().forEach(p->System.out.println(p+ "  "+p.getId()));
		}
		aktualisierePmrliste();
	}

		
	
	@Override
	protected void onAdd(Person person, Connection con) throws Exception {
		super.onAdd(person, con);
		
		int id = -1;
		Person original = null;
		//Existiert bereits in Datenbank?
		try(PreparedStatement ps = con.prepareStatement("Select id from person where name=? and vorname=?")){
			ps.setString(1, person.getName());
			ps.setString(2, person.getVorname());
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next())	id = rs.getInt(1);							
			}
		}
		
		if(original == null) {
			//Existiert nicht -> muss angelegt werden
			try(PreparedStatement ps = con.prepareStatement("insert into person(vorname, name) values(?, ?); Select SCOPE_IDENTITY()")){
				ps.setString(1, person.getVorname());
				ps.setString(2, person.getName());
				try(ResultSet rs = ps.executeQuery()){
					rs.next();
					id = rs.getInt(1);	
				}
			}
		}
		
		for(PersonMitRolle pmr: person.getPersonenMitRolle()) {
			
			if(pmr.getUpdateProperty().get()==false)	continue;
			
			int rolleid = pmr.getRolle().getId();
			//Kombination schon vorhanden?
			try(PreparedStatement ps1 = con.prepareStatement("Select * from film_person_rolle where fid=? and pid=? and rid=?");
					PreparedStatement ps2 = con.prepareStatement("insert into film_person_rolle(fid, pid, rid) values(?, ?, ?)")){
				ps1.setInt(1, film.getId());
				ps1.setInt(2, id);
				ps1.setInt(3, rolleid);

				try(ResultSet rs = ps1.executeQuery()){
					//Wenn bereits vorhanden dann nicht nochmal hinzufügen
					if(!rs.next()) {
						ps2.setInt(1, film.getId());
						ps2.setInt(2, id);
						ps2.setInt(3, rolleid);
						ps2.executeUpdate();	
					}
				}
			}
			//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			if(person != original) {
//				person.removePMR(pmr);
//				original.addPMR(pmr);
//				if(pmr.getDeleteProperty().get()==true)
//					super.removeEntitaet(pmr.getPerson());
//			}
			
//			pmr.getUpdateProperty().set(false);	
//			System.out.println(pmr+"  "+pmr.getPerson());
//			System.out.println(pmr.getUpdateProperty());
		}
	}
		
	@Override
	protected void onUpdate(Person per, Connection con) throws Exception {
		super.onUpdate(per, con);
		
		for(PersonMitRolle pmr: per.getPersonenMitRolle()) {
			
			int rolleid = pmr.getinitialRolle().getId();
			try(PreparedStatement ps = con.prepareStatement("Delete from film_person_rolle where fid=? and pid=? and rid=?;")){
				ps.setInt(1, film.getId());
				ps.setInt(2, per.getId());
				ps.setInt(3, rolleid);
				ps.executeUpdate();
			}
			pmr.resetInitialRolle();
			pmr.getUpdateProperty().set(true);
		}
		onAdd(per, con);		
	}
	
	@Override
	protected void onDelete(Person per, Connection con) throws Exception {
		super.onDelete(per, con);
		
		for(PersonMitRolle pmr: per.getPersonenMitRolle()) {
			if(pmr.getDeleteProperty().get()==false)	continue;
			int rolleid;
			if(pmr.getinitialRolle()!=pmr.getRolle())	rolleid = pmr.getinitialRolle().getId();
			else										rolleid = pmr.getRolle().getId();
			try(PreparedStatement ps = con.prepareStatement("Delete from film_person_rolle where fid=? and pid=? and rid=?;")){
				ps.setInt(1, film.getId());
				ps.setInt(2, per.getId());
				ps.setInt(3, rolleid);
				ps.executeUpdate();
			}	
		}
	}

	@Override
	public void save(Connection con) throws SQLException, InterruptedException {
		super.save(con);
		lade(con);
	}
	
	public static int getMaxName() {
		return DB_Manager.get("PerNameMax");
	}
	public static int getMaxVorname() {
		return DB_Manager.get("PerVornameMax");
	}
}
	

	
