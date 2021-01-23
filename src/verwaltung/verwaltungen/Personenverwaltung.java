package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.Unterverwaltung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;

public class Personenverwaltung extends Unterverwaltung<Person>{

	private static Map<String, Integer> rollen;
	public static ObservableList<String> getRollen() {
		if(rollen == null) {
			rollen = new HashMap<>();
			try(Connection con = getCon();){
				ResultSet rs = con.createStatement().executeQuery("Select id, rolle from rollen");
				while(rs.next()) rollen.put(rs.getString(2), rs.getInt(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		ObservableList<String> oblist = FXCollections.observableArrayList();
		rollen.forEach( (k, v)-> oblist.add(k) );
		return oblist;
	}
	
	public Personenverwaltung(Film film) {
		super(film);
		
//		ArrayList<String> r = new ArrayList<>();
//		r.add("aaa");
//		r.add("awwwaa");
//		r.add("aadadaa");
//		r.add("a22aa");
//		list.add(new Person(1, "Tom", "T", r));
//		list.add(new Person(2, "Tosm", "T", r));
	}
	
	@Override
	public void load() throws SQLException {
		super.load();
		try(Connection con = getCon()){
			ResultSet rs = con.createStatement().executeQuery("Select pid, vorname, name, rolle from personen "
															+"inner join filme_personen_rollen on pid = personen.id "
															+"inner join rollen on rid = rollen.id "
															+"where fid="+film.getId());
			while(rs.next()) {
				int pid = rs.getInt(1);
				Person person = list.stream().filter(per->per.getId()==pid).findFirst().orElse(null);
				if(person == null)
					list.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				else
					person.addRolle(rs.getString(4));
			}
			list.forEach(p->System.out.println(p+ "  "+p.getId()));
		}
	}
	
	
	public void addOrUpdate(List<PersonMitRolle> pmrlist) throws Exception {
		if(film == null) throw new Exception("No Film");
		
		try(Connection con = getCon();){
			con.setAutoCommit(false);
			
			//Working Copy for AddFilm , Actual object
			Person person = null, original = null;
			Integer rolleId;
			for(PersonMitRolle pmr: pmrlist) {

				
				if(person != pmr.getPerson()) {
					person = pmr.getPerson();
					for(Person per: list) {
						if(per.getId()==person.getId()) {
							original = per;
							break;
						}
					}
				
					
					if(original == null) {
						//Existiert bereits in Datenbank?
						PreparedStatement ps = con.prepareStatement("Select id, vorname, name from personen where name=? and vorname=?");
						ps.setString(1, person.getName().get());
						ps.setString(2, person.getVorname().get());
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							original = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), person.getRollen());
					}
					
					if(original == null) {
						//Existiert nicht -> muss angelegt werden
						PreparedStatement ps = con.prepareStatement("insert into personen(vorname, name) values(?, ?);"
																+ "Select id, vorname, name from personen where id=SCOPE_IDENTITY()");
						ps.setString(1, person.getVorname().get());
						ps.setString(2, person.getName().get());
						ResultSet rs = ps.executeQuery();
						rs.next();
						original = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), person.getRollen());
					}else {
						//Updaten
						PreparedStatement ps = con.prepareStatement("update personen set vorname=?,  name=? where id=?");
						ps.setString(1, person.getVorname().get());
						ps.setString(2, person.getName().get());
						ps.setInt(3, original.getId());
						ps.executeUpdate();
					}
					con.commit();
					original.getName().set( person.getName().get() );
					original.getVorname().set(person.getVorname().get() );
					if(!list.contains(original)) list.add(original);	
				}

				rolleId = rollen.get(pmr.getRolle().get());
				
				if(rolleId!=null) {
					//Kombination schon vorhanden?
					PreparedStatement ps = con.prepareStatement("Select * from filme_personen_rollen where fid=? and pid=? and rid=?");
					ps.setInt(1, film.getId());
					ps.setInt(2, original.getId());
					ps.setInt(3, rolleId);
					ResultSet rs = ps.executeQuery();
					if(!rs.next()) {
						ps = con.prepareStatement("insert into filme_personen_rollen(fid, pid, rid) values(?, ?, ?)");
						ps.setInt(1, film.getId());
						ps.setInt(2, original.getId());
						ps.setInt(3, rolleId);
						ps.executeUpdate();
					}
					rs.close();
					con.commit();					
					original.addRolle(pmr.getRolle().get());
				}
			}
		}	
	}
	
	public void delete(List<PersonMitRolle> pmrlist) throws SQLException {
		try(Connection con = getCon();){
		
			Integer rolleId;
			for(PersonMitRolle pmr: pmrlist) {			
				rolleId = rollen.get(pmr.getRolle().get());				
				if(rolleId!=null) {
					PreparedStatement ps = con.prepareStatement("Delete filme_personen_rollen where fid=? and pid=? and rid=?");
					ps.setInt(1, film.getId());
					ps.setInt(2, pmr.getPerson().getId());
					ps.setInt(3, rolleId);
					ps.executeUpdate();
					pmr.getPerson().removeRolle(pmr.getRolle().get());
				}				
			}
		}
	}

	
	public List<PersonMitRolle> getPersonenMitRollen(){
		List<PersonMitRolle> pml = new ArrayList<>();
		list.forEach(per->pml.addAll(per.getCopy().getPersonenMitRolle()));
		return pml;
	}

}
	

	
