package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import verwaltung.Unterverwaltung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;

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
	
	
	private static List<Person> allLoadedPersons;
	public static Person getIfexists(int pid) {
		Optional<Person> opt = allLoadedPersons.stream().filter(per->per.getId()==pid).findFirst();
		if(opt.isPresent()) return opt.get();
		return null;
	}
	public static Person getIfexists(String name, String vorname) {
		Optional<Person> opt = allLoadedPersons.stream().filter(per->{
			if(per.getName().get().equals(name) && per.getVorname().get().equals(vorname))
				return true;
			return false;
		}).findFirst();
		if(opt.isPresent()) return opt.get();
		return null;
	}
	
	
	
	private Map<Integer, List<String>> perRolleMap;
	
	public Personenverwaltung(Film film) {
		super(film);
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
				Person per = getIfexists(rs.getInt(1));
				if(per==null) {
					per = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					allLoadedPersons.a
				}if(!perRolleMap.containsKey(per.getId())) 
					perRolleMap.put(per.getId(), new ArrayList<String>());
				perRolleMap.get(per).add(per.rolle.get());
				list.add(per);
			}
		}
	}
	
	
	public void addOrUpdate(List<Person> personen) throws Exception {
		if(film == null) throw new Exception("No Film");
		
		try(Connection con = getCon();){
			con.setAutoCommit(false);
			
			for(Person person: personen) {
				Integer rolleId = rollen.get(person.getRolle().get());
				Integer pid = null;
			
				Person original = person.getId()>-1 ? getIfexists(person.getId()) : null;
				
				if(original == null)
					original = getIfexists(person.getName().get(), person.getVorname().get());
				
				if(original == null) {
					//Existiert bereits in Datenbank?
					PreparedStatement ps = con.prepareStatement("Select id, vorname, name from personen where name=? and vorname=?");
					ps.setString(1, personen.get(0).getName().get());
					ps.setString(2, personen.get(0).getVorname().get());
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						original = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), "");
						allLoadedPersons.add(original);
					}
				}
			
				if(original == null) {
					//Existiert nicht -> muss angelegt werden
					PreparedStatement ps = con.prepareStatement("insert into personen(vorname, name) values(?, ?);"
															+ "Select id, vorname, name from personen where id=SCOPE_IDENTITY()");
					ps.setString(1, person.getVorname().get());
					ps.setString(2, person.getName().get());
					ResultSet rs = ps.executeQuery();
					rs.next();
					original = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), "");
					allLoadedPersons.add(original);
				}else {
					PreparedStatement ps = con.prepareStatement("update personen set vorname=?,  name=? where id=?");
					ps.setString(1, person.getVorname().get());
					ps.setString(2, person.getName().get());
					ps.setInt(3, pid);
					ps.executeUpdate();
				}
				
				//Kombination schon vorhanden?
				PreparedStatement ps = con.prepareStatement("Select * from filme_personen_rollen where fid=? and pid=? and rid=?");
				ps.setInt(1, film.getId());
				ps.setInt(2, pid);
				ps.setInt(3, rolleId);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()) {
					ps = con.prepareStatement("insert into filme_personen_rollen(fid, pid, rid) values(?, ?, ?)");
					ps.setInt(1, film.getId());
					ps.setInt(2, pid);
					ps.setInt(3, rolleId);
					ps.executeUpdate();
				}
				rs.close();
				con.commit();
				
				if(!perRolleMap.containsKey(original)) 
					perRolleMap.put(original, new ArrayList<String>());
				perRolleMap.get(original).add(person.rolle.get());
				list.add(original); // lokale Liste
				
				original.getName().set( person.getName().get() );
				original.getVorname().set(person.getVorname().get() );
				
			}
		}	
	}
	
	public List<String> perRolleMap(Person per) {
		return perRolleMap(per);
	}
	
}
