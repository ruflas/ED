package estDatos;

import java.util.Iterator;

public class PeekingIterator<E> implements PeekIterator<Object>{
	private Iterator<E> it;
	Object nextVal;

	public PeekingIterator(Iterable<E> t) {
		it = t.iterator();
		nextVal = it.hasNext() ? it.next() : null;
	}

	public PeekingIterator(Iterator<E> t) {
		it = t;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Object next() {
		Object oldNext = nextVal;
		nextVal = it.hasNext() ? it.next() : null;
		return oldNext;
	}

	@Override
	public Object peek() {
		return nextVal;
	}
}
