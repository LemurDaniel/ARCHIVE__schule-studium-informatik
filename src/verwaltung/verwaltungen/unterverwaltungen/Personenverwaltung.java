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
import verwaltung.entitaeten.Rolle;

public class Personenverwaltung extends Unterverwaltung<Person>{

	private static Map<Integer, Rolle> rolleMap;
	public static void ladeRollen(Connection con) throws SQLException{
		rolleMap =  new HashMap<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select id, rolle from rolle")){
			while(rs.next()) rolleMap.put(rs.getInt(1), new Rolle(rs.getInt(1), rs.getString(2)));
		}	
	}
	
	public static ObservableList<Rolle> getRollen() {
		return FXCollections.observableArrayList(rolleMap.values());
	}
	
	
	
	/**  **/
	
	public Personenverwaltung(Film film) {
		super(film);
	}
	
	

	@Override
	public void lade(Connection con) throws SQLException {
		super.lade(con);
		String sql = "Select pid, vorname, name, rid from person "
					+"inner join film_person_rolle on pid = person.id "
					+"where fid="+film.getId()+" order by pid";
		
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)){

			while(rs.next()) addObj( new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rolleMap.get(rs.getInt(4))) );

			getList().forEach(p->System.out.println(p+ "  "+p.getId()));
		}
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
		
		if(id==-1) {
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
		
		
		try(PreparedStatement ps = con.prepareStatement("insert into film_person_rolle(fid, pid, rid) values(?, ?, ?)")){
			ps.setInt(1, film.getId());
			ps.setInt(2, id);
			ps.setInt(3, person.getRolle().getId());
			ps.executeUpdate();
		}
		
		person.setTempId(id);
	}
		
	@Override
	protected void onUpdate(Person per, Connection con) throws Exception {
		super.onUpdate(per, con);
		
		if(per.isNameChanged()) {
			onDelete(per.getBackup(), con);
			onAdd(per, con);
			return;
		}
		
		try(PreparedStatement ps = con.prepareStatement("Update film_person_rolle set rid=? where fid=? and pid=? ")){
			ps.setInt(1, per.getRolle().getId());
			ps.setInt(2, film.getId());
			ps.setInt(3, per.getId());
			ps.executeUpdate();
		}
		
	}
	
	@Override
	protected void onDelete(Person per, Connection con) throws Exception {
		super.onDelete(per, con);
				
		try(PreparedStatement ps = con.prepareStatement("Delete film_person_rolle where rid=? and fid=? and pid=? ")){
			ps.setInt(1, per.getRolle().getId());
			ps.setInt(2, film.getId());
			ps.setInt(3, per.getId());
			ps.executeUpdate();
		}
	}
	
	@Override
	protected void onUpdateSucess(Person per, Connection con) 	throws SQLException, InterruptedException{
		super.onUpdateSucess(per, con);
		if(per.isNameChanged()) {
			per.commitId();
			per.setNameChanged(false);
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

	public static int getMinName() {
		return DB_Manager.get("PerNameMin");
	}
	public static int getMinVorname() {
		return DB_Manager.get("PerVornameMin");
	}
}
	

	
