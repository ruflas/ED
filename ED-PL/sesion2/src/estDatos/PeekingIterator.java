package estDatos;

import java.util.Iterator;

public class PeekingIterator<E> implements PeekIterator<Object>{
	private Iterator<E> it;
	private Object nextValue;
	private boolean hasNextE;
	public PeekingIterator(Iterable<E> t) {
		it = t.iterator();
		nextValue = it.hasNext() ? it.next() : null;
		hasNextE = it.hasNext();
	}

	public PeekingIterator(Iterator<E> t) {
		it = t;
	}

	@Override
	public boolean hasNext() {
		return hasNextE;
	}

	@Override
	public Object next() {
		hasNextE = it.hasNext();
		Object oldValue = nextValue;
		nextValue = it.hasNext() ? it.next() : null;
		return oldValue;
	}

	@Override
	public Object peek() {
		return nextValue;
	}
}
