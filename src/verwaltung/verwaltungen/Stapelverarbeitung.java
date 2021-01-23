package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;

public abstract class Stapelverarbeitung<T extends Backup > {

	protected Stack<T> delete, add, update, err;
	
	protected List<Exception>	fehlerlog;
	
	public Stapelverarbeitung () {
		delete = new Stack<>();
		add = new Stack<>();
		update = new  Stack<>();
		err = new Stack<>();
		fehlerlog   = new ArrayList<>();
	}
	

	public List<Exception> getFehlerlog(){
		return fehlerlog;
	}
	
	
	public boolean addEntitaet(T entitaet) {
		if(entitaet==null || add.contains(entitaet))	return false;
		if(delete.contains(entitaet))	delete.remove(entitaet);
		else							add.push(entitaet);
		
		
		System.out.println("add----------add");
		add.forEach(a->System.out.println(a));
		System.out.println("delete----------");
		delete.forEach(a->System.out.println(a));
		return true;
	}

	public boolean removeEntitaet(T entitaet) {
		if(entitaet==null || delete.contains(entitaet) )	return false;
		if(add.contains(entitaet))	add.remove(entitaet);
		else						delete.push(entitaet);
		
		System.out.println("delete----------delete");
		delete.forEach(a->System.out.println(a));
		System.out.println("add----------");
		add.forEach(a->System.out.println(a));
		return true;
	}
	public boolean updateEntitaet(T entitaet) {
		if(entitaet==null  || add.contains(entitaet) || update.contains(entitaet))	return false;
		update.push(entitaet);
		
		System.out.println("update----------add");
		update.forEach(a->System.out.println(a));
		System.out.println("delete----------");
		delete.forEach(a->System.out.println(a));
		System.out.println("add----------add");
		add.forEach(a->System.out.println(a));
		return true;
	}
	
	
	public boolean hatAuftraege() {
		return add.size()!=0 || update.size()!=0 || delete.size()!=0;
	}
	
	
	
	public void save(Connection con) throws SQLException {
		if(!hatAuftraege())		return;
		
		fehlerlog.clear();
		err.clear();
		
		con.setAutoCommit(false);
		stapelAbarbeiten(add, 		this::onAdd, 	this::onAddSucess, con);
		stapelAbarbeiten(update,	this::onUpdate, this::onUpdateSucess, con);
		stapelAbarbeiten(delete,	this::onDelete, this::onDeleteSucess, con);
	}
		
	private void stapelAbarbeiten(Stack<T> stack, methode<T> m, methode2<T> m2, Connection con) throws SQLException{
		try {
			while(!stack.empty()) {
				T ent = stack.pop();
				try {
					m.ausfuehren(ent, con);
					con.commit();
					m2.ausfuehren(ent);
				}catch (SQLException e1) {
					con.rollback();
					err.push(ent);
					fehlerlog.add(e1);
				}catch(Exception e) {
					//e.printStackTrace();
					err.push(ent);
					fehlerlog.add(e);
				}
			}
		}finally {
			System.out.println("finally "+err.size());
			while(!err.empty())	stack.push(err.pop());
			System.out.println("finally "+err.size());
			System.out.println(".....");
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