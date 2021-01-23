package verwaltung.entitaeten;

public interface Backup {
	
	public void backup();
	public boolean hasBackup();
	public void deleteBackup();
	public void backupReset();
}
