/**
 * 
 */
package estDatos;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SortedLListSet<E> extends LListSet<E> {
	protected Comparator<? super E> cmp;
	
	public SortedLListSet() {
		super();
	}
	
	public SortedLListSet(Comparator<? super E> comp) {
		this();
		cmp = comp;
	}

	public SortedLListSet(Collection<? extends E> c) {
		super(c);
	}
	
	public SortedLListSet(Collection<? extends E> c, Comparator<? super E> comp) {
		this();
		cmp = comp;
		this.addAll(c);
	}
	
	@SuppressWarnings("unchecked")
	private int compare(Object a, Object b) {
		if (cmp == null && !(a instanceof Comparable<?>) ) {
			throw new ClassCastException();
		}
		
		return cmp == null ? ((Comparable<? super E>) a).compareTo((E) b)
			               : cmp.compare((E) a, (E) b);
	}
	
	@Override
	public boolean add(E e) {
		boolean check = super.add(e);
		sort();
		return check;
	}
	
	private boolean sort() {
		if(!this.data.isEmpty()) {
			List<E> aux = this.data;
			Iterator<E> it = iterator();
			Integer cont = 0;
			while(it.hasNext()) {
				E element = it.next();
				E next = null;
				try{
					next = ((PeekingIterator<E>) it).peek();
				}
				catch(Exception e) {
					return true;
				}
				cont++;
				if(cmp.compare(element,next)>1) {
					this.data.set(cont+1, element);
					this.data.set(cont, next);
				}
			}
			return !this.data.equals(aux);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {

		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		
		return false;
	}
	
}
	
