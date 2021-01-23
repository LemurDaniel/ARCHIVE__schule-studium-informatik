package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;
import verwaltung.entitaeten.Rolle;

public class Personenverwaltung extends Unterverwaltung<Person>{

	public static ObservableList<Rolle> getRollen() {
		ObservableList<Rolle> rlist = FXCollections.observableArrayList();
		rolleMap.forEach((k,v)->rlist.add(v));
		return rlist;
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
	public void load(Connection con) throws SQLException {
		super.load(con);
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
	}
	
	public void addOrUpdate(List<PersonMitRolle> pmrlist, Connection con) throws SQLException {
		if(pmrlist.size()==0)	return;
		
//		for(PersonMitRolle pmr: pmrlist)
//			System.out.print(pmr.getRolle().get());
		
		
		con.setAutoCommit(false);
			
		//Working Copy for AddFilm , Actual object
		Person person = null, original = null;
		for(PersonMitRolle pmr: pmrlist) {
				
			if(person != pmr.getPerson()) {
				person = pmr.getPerson();
//				inner:
//				for(Person per: list) {
//					if(	per.getId() == person.getId() ) {
//						original = per;
//						break inner;
//					}
//				}
				
				//Existiert bereits in Datenbank?
				try(PreparedStatement ps = con.prepareStatement("Select id from person where name=? and vorname=?")){
					ps.setString(1, person.getName());
					ps.setString(2, person.getVorname());
					try(ResultSet rs = ps.executeQuery()){
						if(rs.next()) {
							int id = rs.getInt(1);
							original = getList().stream().filter(per->per.getId()==id).findFirst().orElse(null);
							if(original==null) {
								original = new Person(id, person.getVorname(), person.getName());
								addObj(original);
							}
						}
					}
				}
									
				if(original == null) {
					//Existiert nicht -> muss angelegt werden
					try(PreparedStatement ps = con.prepareStatement("insert into person(vorname, name) values(?, ?); Select SCOPE_IDENTITY()")){
						ps.setString(1, person.getVorname());
						ps.setString(2, person.getName());
						try(ResultSet rs = ps.executeQuery()){
							rs.next();
							con.commit();
							original = new Person(rs.getInt(1), person.getVorname(), person.getName());
							addObj(original);	
						}
					}
				}else {
					//Updaten
					try(PreparedStatement ps = con.prepareStatement("update person set vorname=?, name=? where id=?")){
						ps.setString(1, person.getVorname());
						ps.setString(2, person.getName());
						ps.setInt(3, original.getId());
						ps.executeUpdate();
						con.commit();
						original.getNameProperty().set( person.getName() );
						original.getVornameProperty().set( person.getVorname() );
					}
				}				
			}

			int rolleId = pmr.getRolle().getId();
				
			//Kombination schon vorhanden?
			try(PreparedStatement ps1 = con.prepareStatement("Select * from film_person_rolle where fid=? and pid=? and rid=?");
					PreparedStatement ps2 = con.prepareStatement("insert into film_person_rolle(fid, pid, rid) values(?, ?, ?)");){
				ps1.setInt(1, film.getId());
				ps1.setInt(2, original.getId());
				ps1.setInt(3, rolleId);

				try(ResultSet rs = ps1.executeQuery()){
					//Wenn bereits vorhanden dann nicht nochmal hinzufügen
					if(!rs.next()) {
						ps2.setInt(1, film.getId());
						ps2.setInt(2, original.getId());
						ps2.setInt(3, rolleId);
						ps2.executeUpdate();
						con.commit();					
						original.addRolle(pmr.getRolle());
					}
				}
			}
			
		}
			
	}

	
	public void delete(List<PersonMitRolle> pmrlist, Connection con) throws SQLException {
		if(pmrlist.size()==0)	return;
		
		for(PersonMitRolle pmr: pmrlist) {			
			int rolleId = pmr.getRolle().getId();			
			try (PreparedStatement ps = con.prepareStatement("Delete film_person_rolle where fid=? and pid=? and rid=?");){
				ps.setInt(1, film.getId());
				ps.setInt(2, pmr.getPerson().getId());
				ps.setInt(3, rolleId);
				ps.executeUpdate();
				pmr.getPerson().removeRolle(pmr.getRolle());
			}

			if(pmr.getPerson().getRollen().size()==0) {
				removeObj( getList().stream().filter(per->per.getId()==pmr.getPerson().getId()).findFirst().orElse(null) );										
			}		
		}
	}

	
	public List<PersonMitRolle> getPersonenMitRollen(){
		List<PersonMitRolle> pml = new ArrayList<>();
		getList().forEach(per->pml.addAll(per.getCopy().getPersonenMitRolle()));
		return pml;
	}
	
	public static int getMaxName() {
		return maxSize.get("PerNameMax");
	}
	public static int getMaxVorname() {
		return maxSize.get("PerVornameMax");
	}


}
	

	
