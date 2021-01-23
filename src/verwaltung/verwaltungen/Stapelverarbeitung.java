package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import gui.FensterManager;
import javafx.scene.paint.Color;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;
import verwaltung.entitaeten.Id;

public abstract class Stapelverarbeitung<T extends Backup > implements Runnable{

	protected Stack<T> delete, add, update, err;
	
//	protected List<Exception>	fehlerlog;
//	protected List<String>	log;
	
	public Stapelverarbeitung () {
		delete = new Stack<>();
		add = new Stack<>();
		update = new  Stack<>();
		err = new Stack<>();
	}
	

	public void clear(){
		delete.clear();
		add.clear();
		update.clear();
	}
	
	public boolean addEntitaet(T entitaet) {
		if(entitaet==null || add.contains(entitaet))	return false;
		if(delete.contains(entitaet))	delete.remove(entitaet);
		else							add.push(entitaet);
		
		
		System.out.println("add----------add");
		add.forEach(a->System.out.println(a));
		System.out.println("delete----------");
		delete.forEach(a->System.out.println(a));
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return true;
	}
	public boolean updateEntitaet(T entitaet) {
		if(entitaet==null  || add.contains(entitaet) || update.contains(entitaet))	return false;
		update.push(entitaet);
		
		System.out.println(this.getClass().getSimpleName());
		
		System.out.println("update----------add");
		update.forEach(a->System.out.println(a));
		System.out.println("delete----------");
		delete.forEach(a->System.out.println(a));
		System.out.println("add----------add");
		add.forEach(a->System.out.println(a));
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return true;
	}
	
	
	public boolean hatAuftraege() {
		return add.size()!=0 || update.size()!=0 || delete.size()!=0;
	}
	
	
	
	public void save(Connection con) throws SQLException, InterruptedException {
		if(!hatAuftraege())		return;
		
		err.clear();
	
		con.setAutoCommit(false);
		try {
			stapelAbarbeiten(add, 		this::onAdd, 	this::onAddSucess, con);
			stapelAbarbeiten(update,	this::onUpdate, this::onUpdateSucess, con);
			stapelAbarbeiten(delete,	this::onDelete, this::onDeleteSucess, con);
		}catch(SQLException e) {
			FensterManager.logErreignis(e);
		}
	}
		
	private void stapelAbarbeiten(Stack<T> stack, methode<T> m, methode2<T> m2, Connection con) throws SQLException, InterruptedException{
		try {
			while(!stack.empty()) {
				if(Thread.interrupted()) throw new InterruptedException();

				T ent = stack.pop();
				try {
					m.ausfuehren(ent, con);
					con.commit();
				}catch (SQLException e1) {
					con.rollback();
					err.push(ent);
					FensterManager.logErreignis(e1);
					continue;
				}catch(Exception e) {
					err.push(ent);
					FensterManager.logErreignis(e);
					continue;
				}
				m2.ausfuehren(ent, con);
			}
		}finally {
			while(!err.empty())	stack.push(err.pop());
		}
	}
	
	protected abstract void onAdd(T ent, Connection con) 	throws Exception;
	protected abstract void onUpdate(T ent, Connection con) throws Exception;
	protected abstract void onDelete(T ent, Connection con) throws Exception;
	
	protected abstract void onAddSucess(T ent, Connection con) 		throws SQLException, InterruptedException;
	protected abstract void onUpdateSucess(T ent, Connection con) 	throws SQLException, InterruptedException;
	protected abstract void onDeleteSucess(T ent, Connection con) 	throws SQLException, InterruptedException;
	
	
	public void reset() {
		if(!hatAuftraege()) {
			FensterManager.logErreignis("\nEs sind keine Änderungen vorhanden");
			return;
		}
		
		update.forEach(	item->	item.backupReset());
		add.clear();
		delete.clear();
		update.clear();
		FensterManager.logErreignis("\nAlle ungespeicherten Änderungen wurden zurückgesetzt");
	}
	
	interface methode<T> {
		public void ausfuehren(T ent, Connection con) throws Exception;
	}
	interface methode2<T> {
		public void ausfuehren(T ent, Connection con) throws SQLException, InterruptedException;
	}
	
	
	@Override
	public void run() {
		if(!hatAuftraege())	{
			FensterManager.logErreignis("\nEs existieren keine Änderungen zum speichern");
			return;
		}
		
		FensterManager.logErreignis("\n  Der Speichervorgang wurde gestartet\n");
		try (Connection con = DB_Manager.getCon()){
			save(con);	
		} catch (SQLException e) {
		}catch(InterruptedException e) {
			FensterManager.logErreignis("\n  Der Speichervorgang wurde abgebrochen", Color.RED);
			return;
		}
		FensterManager.logErreignis("\n  Der Speichervorgang wurde beendet");
	}
	
}