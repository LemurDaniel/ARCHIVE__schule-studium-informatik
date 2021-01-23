package aufgabe5;

public class Liste<T> {
	private T[] data;
	private int count;
	
	@SuppressWarnings("unchecked")
	public Liste(int size) {
		data = (T[])new Object[size];
	}
	
	@SuppressWarnings("unchecked")
	public void add(T x) {
		if(count == data.length) {
			T[] neu = (T[])new Object[data.length*2];
			for(int i=0; i<data.length; i++)
				neu[i] = data[i];
			data = neu;
		}
		data[count] = x;
		count++;
	}
	
	public T remove() {
		if(count > 0) {
			count--;
			return data[count];
		}
		else return null;
	}
	
	public int getCount() {
		return count;
	}
	
	
	/**
	Die Klasse erstellt ein Array der Klasse liste mit einer festen Länge und einer var count, die die Anzahl der Elemente zählt.
	Wird diese beim hinzufügen neuer Elemente überschritten, wird eine neues Array mit doppelte Länge angelegt.
	Alle Objekte des alten sowie das hinzuzufügende Objekt werden in das neue Array eingelesen.
	Beim löschen von Elementen werden nur solange Elemente gelöscht und an den Methodenaufrufer zurückgegeben, bis der counter 0 erreicht. Danach wird null zurückgegeben.
	*/
}
