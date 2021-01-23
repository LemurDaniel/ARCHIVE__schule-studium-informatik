package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Artikel;

public class Artikel_Manager extends DB_Manager {
	private String findAll = "select * from Artikel";
	private String findById = "select * from Artikel where id = ?";
	private String update = "update Artikel set bezeichnung = ?, menge = ?, preis = ? where id = ?";
	private String insert = "insert into Artikel(id, bezeichnung, menge, preis) values(?, ?, ?, ?)";
	private String delete = "Delete Artikel where id=?";
	
	public List<Artikel> findAll() throws SQLException{
		List<Artikel> alle = new ArrayList<>();
		try(Connection con = getCon(); ){
			PreparedStatement ps = con.prepareStatement(findAll);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				alle.add(getArtikelFromResultSet(rs));
			}
		}
		return alle;
	}
	public Artikel findById(int id) throws SQLException {
		Artikel a = null;
		try(Connection con = getCon(); ){
			PreparedStatement ps = con.prepareStatement(findById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) a = this.getArtikelFromResultSet(rs);
		}
		return a;
	}

	private Artikel getArtikelFromResultSet(ResultSet rs) throws SQLException{
		Artikel a = new Artikel();
		a.setId(rs.getInt("id"));
		a.setBezeichnung(rs.getString("bezeichnung"));
		a.setMenge(rs.getInt("menge"));
		a.setPreis(rs.getFloat("preis"));
		return a;
	}
	
	public void update(Artikel a) throws SQLException{
		if(findById(a.getId())==null) return;
		
		try(Connection con = getCon(); ){
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, a.getBezeichnung());
			ps.setInt(2, a.getMenge());
			ps.setDouble(3, a.getPreis());
			ps.setInt(4, a.getId());
			ps.executeUpdate();
		}
	}
	
	public Artikel insert(Artikel a) throws SQLException{
		try(Connection con = getCon(); ){
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("select max(id) from Artikel");
			ResultSet r = ps.executeQuery();
			int id = 1;
			if(r.next()) id=r.getInt(1) + 1;
			
			ps = con.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, a.getBezeichnung());
			ps.setInt(3, a.getMenge());
			ps.setDouble(4, a.getPreis());
			ps.executeUpdate();
			con.commit();
		}
		return a;
	}
	
	public void delete(Artikel a) throws SQLException{
		if(a==null) return;
		try(Connection con = getCon(); ){
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, a.getId());
			ps.executeUpdate();
		}
	}
}
