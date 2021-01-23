package aufgabe3;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;


@SuppressWarnings("serial")
public class MyModel<E> extends AbstractListModel<E> {

	private List<E> list;
	
	public MyModel() {
		list = new ArrayList<>();
	}
	
	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public E getElementAt(int index) {
		return list.get(index);
	}
	
	public void add(E[] arr) {
		for(E e: arr) {
			list.add(e);
		}
		super.fireIntervalAdded(this, list.size()-arr.length, list.size());
	}
	public void add(E e) {
		list.add(e);
		super.fireIntervalAdded(this, list.size(), list.size());
	}
	public void remove(E e) {
		int i = list.indexOf(e);
		list.remove(e);
		super.fireIntervalRemoved(this, i, i);
	}	
	public void remove(int i) {
		list.remove(i);
		super.fireIntervalRemoved(this, i, i);
	}

}
