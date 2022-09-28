package estDatos;

import java.util.Iterator;

public class PeekingIterator<E> implements PeekIterator<Object> {
	private Iterator<E> it;
	public PeekingIterator(Iterable<E> t){
		it = t.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Object next() {
		return it.next();
	}

	@Override
	public Object peek() {
		Iterator<E> aux = it;
		return aux.next();
	}
	
}
