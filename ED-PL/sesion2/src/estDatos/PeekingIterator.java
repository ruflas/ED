package estDatos;

import java.util.Iterator;

public class PeekingIterator<E> implements PeekIterator<E>{
	private Iterator<E> it;
	private E nextValue;
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
	public E next() {
		hasNextE = it.hasNext();
		E oldValue = nextValue;
		nextValue = it.hasNext() ? it.next() : null;
		return oldValue;
	}

	@Override
	public E peek() {
		return nextValue;
	}
}
