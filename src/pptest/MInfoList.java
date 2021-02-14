package pptest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
public class MInfoList implements ObservableList<InfoWrapper>{
	private ArrayList <InfoWrapper>iList;
	public MInfoList() {
		 iList= new ArrayList<>();
	}
	public MInfoList(Info[] arr) {
		iList = new ArrayList();
		try {
			for(Info i : arr) {
				InfoWrapper cur = new InfoWrapper(i);
				iList.add(cur);
		}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return iList.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return iList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return iList.contains(o);
	}

	@Override
	public Iterator<InfoWrapper> iterator() {
		// TODO Auto-generated method stub
		return iList.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] res = new Object[this.size()];
		
		for(int i = 0;i<this.size();i++) {
			res[i]= iList.get(i);
		}
		return res;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(InfoWrapper e) {
		// TODO Auto-generated method stub
		return iList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return iList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return iList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends InfoWrapper> c) {
		// TODO Auto-generated method stub
		return iList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends InfoWrapper> c) {
		// TODO Auto-generated method stub
		return iList.addAll(index,c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return iList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return iList.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		iList.clear();
	}

	@Override
	public InfoWrapper get(int index) {
		// TODO Auto-generated method stub
		return iList.get(index);
	}

	@Override
	public InfoWrapper set(int index, InfoWrapper element) {
		// TODO Auto-generated method stub
		return iList.set(index, element);
	}

	@Override
	public void add(int index, InfoWrapper element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfoWrapper remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return iList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return iList.indexOf(o);
	}

	@Override
	public ListIterator<InfoWrapper> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<InfoWrapper> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfoWrapper> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(InfoWrapper... arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addListener(ListChangeListener<? super InfoWrapper> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeAll(InfoWrapper... arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ListChangeListener<? super InfoWrapper> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean retainAll(InfoWrapper... arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAll(InfoWrapper... arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAll(Collection<? extends InfoWrapper> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
