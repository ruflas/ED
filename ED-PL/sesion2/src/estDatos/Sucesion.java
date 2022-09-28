package estDatos;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generador de la sucesión: {@code 0, 1, 3, 6, 10, 15, 21, ...}
 */
public class Sucesion implements Iterable<Integer> {

	public Sucesion() {	}

	@Override
	public Iterator<Integer> iterator() {
		return new SucesionIterator();
	}

	private final class SucesionIterator implements Iterator<Integer> {
		private int term = 0;
		private int index = 0;

		public boolean hasNext() {
			return true;
		}

		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int temp = this.term;
			this.index++;
			this.term += this.index;
			return temp;
		}
	}

}
