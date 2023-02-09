package estDatos;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PriorityQueueList<E> extends AbstractQueue<E>{
	private List<E> list;
	private Integer size;
	
	public PriorityQueueList(){
		list = new LinkedList<E>();
		this.size = 0;
	}
	
	public PriorityQueueList(Collection<E> c) {
		this();
		list.addAll(c);
		this.size = c.size();
	}
	
	public PriorityQueueList(Collection<E> c, Comparator<E> comp) {
		this(c);
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			E element = it.next();
			E next = ((Iterador<E>) it).peek();
			if(comp.compare(element, next)>0) {
				list.set(list.indexOf(next), element);
				list.set(list.indexOf(element), next);
			}
		}
		
	}
	
	@Override
	public boolean offer(E e) {
		try {
			list.add(e);
			list.remove(e);
		}
		catch(Exception excp) {
			return false;
		}
		Iterator<E> it = iterator();
		while(it.hasNext()) {
		}
		return true;
	}

	@Override
	public E poll() {
		if(list.isEmpty())
			return null;
		return list.remove(0);
	}

	@Override
	public E peek() {
		Iterator<E> it = iterator();
		return ((Iterador<E>) it).peek();
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> i = list.iterator();
		return new Iterador<E>(i); 
	}

	@Override
	public int size() {
		return this.size;
	}
	
	public class Iterador<E> implements Iterator<E>{
		private Iterator<E> it;
		private E nextValue;
		private boolean hasNextE;
		public Iterador(Iterable<E> t) {
			it = t.iterator();
			nextValue = it.hasNext() ? it.next() : null;
			hasNextE = it.hasNext();
		}

		public Iterador(Iterator<E> t) {
			it = t;
		}
		@Override
		public boolean hasNext() {
			return hasNextE;
		}

		@Override
		public E next() {
			hasNextE = it.hasNext();
			E oldValue = nextValue;
			nextValue = it.hasNext() ? it.next() : null;
			return oldValue;
		}
		
		public E peek() {
			return nextValue;
		}
	}
}
