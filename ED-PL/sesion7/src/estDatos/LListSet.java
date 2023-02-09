package estDatos;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LListSet<E> extends AbstractSet<E> implements Set<E>{
	protected List<E> data;
	
	public LListSet() {
		data = new LinkedList<E>();
	}
	
	public LListSet(Collection<? extends E> c) {
		this();
		this.addAll(c);
	}
	
	@Override
	public int size() {
		return data.size();	
	}

	@Override
	public boolean isEmpty() {
		return this.size()==0;
	}

	@Override
	public boolean contains(Object o) {
		return data.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return new PeekingIterator<E>(data);
	}

	@Override
	public Object[] toArray() {
		return data.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return data.toArray(a);
	}

	@Override
	public boolean add(E e) {
		if(data.contains(e)) return false;
		return data.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return data.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(E e: data) {
			if(!data.contains(e))
				return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		List<E> aux = this.data;
		if(c.isEmpty()) return false;
		for(E e: c) {
			this.add(e);
		}
		return !aux.equals(this.data);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return data.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return data.removeAll(c);
	}

	@Override
	public void clear() {
		data.clear();
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
