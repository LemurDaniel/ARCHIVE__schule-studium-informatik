package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import verwaltung.DB_Manager;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Verwaltung;

public class Listenverwaltung extends Verwaltung<Liste>{
	
	public static final DataFormat dfListe = new DataFormat("Liste");
	
	private static Listenverwaltung instance;
	public static Listenverwaltung instance() {
		if(instance==null)instance = new Listenverwaltung();
		return instance;
	}

		
	public void ladeListen(Connection con) throws SQLException {	
		super.clear();
		
		System.out.println("ttt");
		
		String sql = "Select id, name from liste where besitzer=? ";	
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, Nutzer.getNutzer().getId());				
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next())	addObj(new Liste(rs.getInt(1), rs.getString(2)));
			}
		}
		fuelleListen(con);		
	}
		
	private void fuelleListen(Connection con) throws SQLException {
		String	sql = "Select * from film "
				+ "join genre_film on genre_film.fid=film.id "
				+ "join liste_film on liste_film.fid=film.id "
				+ "where lid=? order by film.id ";
		
		for(Liste li:getList()) {
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, li.getId());
				try(ResultSet rs = ps.executeQuery()){
					li.addFilme(rs);
				}
			}
		}
	}
	
	
	
	@Override
	protected void add(Liste ent, Connection con) throws SQLException {
		
		String sql = "Insert liste(name, besitzer) values( ?, ?); Select SCOPE_IDENTITY();";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, ent.getName()				);
			ps.setInt(2, 	Nutzer.getNutzer().getId()	);
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				ent.setId(rs.getInt(1));             // <------ id TODO
			}			
		}
	}	
	@Override
	protected void update(Liste li, Connection con) throws SQLException {
		
		String sql = "Update liste set name=? where lid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, li.getName()	);
			ps.setInt(2, 	li.getId()		);
			ps.executeUpdate();
		}
	}
	@Override
	protected void delete(Liste li, Connection con) throws SQLException {
		
		String sql = "Delete liste_film where lid=?; Delete liste where lid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, li.getId());
			ps.setInt(2, li.getId());
			ps.executeUpdate();
		}
	}
	
	
	public static void kopiereInDragbord(Dragboard db, List<Liste> listen) {
   	 	List<Integer> ids = new ArrayList<>();
   	 	listen.forEach(liste->ids.add(liste.getId()));
   	 	ClipboardContent content = new ClipboardContent();
   	 	content.put(dfListe, ids);
   	 	db.setContent(content);
	}
	public List<Liste> kopiereAusDragbord(Dragboard db) {		
   	 	@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) db.getContent(dfListe);
   	 	List<Liste> listen = new ArrayList<>();
   	 	ids.forEach(id->{
   	 		Liste li = super.getObList().stream().filter(list->list.getId()==id).findFirst().orElse(null);
   	 		if(li!=null)	listen.add(li);
   	 	});
   	 	return listen;
	}
	
	
	
	public static int getMaxName() {
		return DB_Manager.get("ListeNameMax");
	}
	public static int getMinName() {
		return DB_Manager.get("ListeNameMin");
	}
}