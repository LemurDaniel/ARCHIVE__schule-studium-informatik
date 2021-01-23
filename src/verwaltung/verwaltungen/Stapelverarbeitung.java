package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;

public abstract class Stapelverarbeitung<T extends Backup > {

	protected Stack<T> delete, add, update;
	protected List<T> deleteErr, addErr, updateErr;
	
	protected List<Exception>	fehlerlog;
	
	public Stapelverarbeitung () {
		delete = new Stack<>();
		add = new Stack<>();
		update = new  Stack<>();
		
		deleteErr	= new ArrayList<>();
		addErr		= new ArrayList<>();
		updateErr	= new ArrayList<>();
		fehlerlog   = new ArrayList<>();
	}
	

	public List<Exception> getFehlerlog(){
		return fehlerlog;
	}
	
	
	public boolean addEntitaet(T entitaet) {
		if(entitaet==null || add.contains(entitaet))	return false;
		if(delete.contains(entitaet))	delete.remove(entitaet);
		add.push(entitaet);
		System.out.println("add----------add");
		add.forEach(a->System.out.println(a));
		System.out.println("delete----------");
		delete.forEach(a->System.out.println(a));
		return true;
	}

	public boolean removeEntitaet(T entitaet) {
		if(entitaet==null || delete.contains(entitaet) )	return false;
		if(add.contains(entitaet))	add.remove(entitaet);
		else 						delete.push(entitaet);
		System.out.println("delete----------delete");
		delete.forEach(a->System.out.println(a));
		System.out.println("add----------");
		add.forEach(a->System.out.println(a));
		return true;
	}
	public boolean updateEntitaet(T entitaet) {
		if(entitaet==null  || add.contains(entitaet) || update.contains(entitaet))	return false;
		update.push(entitaet);
		return true;
	}
	
	
	public boolean hatAuftraege() {
		return add.size()!=0 || update.size()!=0 || delete.size()!=0;
	}
	
	
	
	public void save(Connection con) throws SQLException {
		if(!hatAuftraege())		return;
		
		fehlerlog.clear();
		
		try {
			con.setAutoCommit(false);
			stapelAbarbeiten(add, 		this::onAdd, 	this::onAddSucess, con);
			stapelAbarbeiten(update,	this::onUpdate, this::onUpdateSucess, con);
			stapelAbarbeiten(delete,	this::onDelete, this::onDeleteSucess, con);
		}finally {
			deleteErr.forEach(delete::push);
			updateErr.forEach(update::push);
			addErr.forEach(add::push);
			
			deleteErr.clear();
			updateErr.clear();
			addErr.clear();
		}
	}
		
	private void stapelAbarbeiten(Stack<T> stack, methode<T> m, methode2<T> m2, Connection con) throws SQLException{
		while(!stack.empty()) {
			T ent = stack.pop();
			try {
				m.ausfuehren(ent, con);
				con.commit();
				m2.ausfuehren(ent);
			}catch (SQLException e1) {
				con.rollback();
				addErr.add(ent);
				fehlerlog.add(e1);
			}catch(Exception e) {
				//e.printStackTrace();
				addErr.add(ent);
				fehlerlog.add(e);
			}
		}
	}
	
	protected abstract void onAdd(T ent, Connection con) 	throws Exception;
	protected abstract void onUpdate(T ent, Connection con) throws Exception;
	protected abstract void onDelete(T ent, Connection con) throws Exception;
	
	protected abstract void onAddSucess(T ent);
	protected abstract void onUpdateSucess(T ent);
	protected abstract void onDeleteSucess(T ent);
	
	
	public void reset() {
		update.forEach(	item->	item.backupReset());
		add.clear();
		delete.clear();
		update.clear();
	}
	
	interface methode<T> {
		public void ausfuehren(T ent, Connection con) throws Exception;
	}
	interface methode2<T> {
		public void ausfuehren(T ent);
	}
}