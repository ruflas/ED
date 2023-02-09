package estDatos;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Tipo de dato cuyas instancias son listas doblemente enlazadas
 * mutables. Las operaciones en ambos extremos de la lista son de
 * tiempo constante.
 * @param <E> tipo de los elementos de la lista
 */
public class MutableList<E> extends AbstractSequentialList<E> {
	private Node<E> head;	// primer nodo de la lista
	private Node<E> tail;	// Ãºltimo nodo de la lista
	int size;				// nÃºmero de elementos de la lista
	
	List<E> l;
	/**
	 * Clase de los nodos de la lista.
	 * @param <E> tipo de los elementos de la lista
	 */
	private static class Node<E> {
		private E item;				// elemento
		private Node<E> previous;	// nodo previo
		private Node<E> next;		// nodo siguiente
		
		/**
		 * Retorna un nodo que contiene el elemento dado y
		 * tiene como nodo previo y siguiente los especificados.
		 * @param e el elemento
		 * @param previous el nodo previo
		 * @param next el nodo siguiente
		 */
		public Node(E e, Node<E> previous, Node<E> next) {
			this.item = e;
			this.previous = previous;
			this.next = next;
		}
	}
	
	/**
	 * Crea una lista vacÃ­a.
	 */
	public MutableList() {
		l = new ArrayList<E>();
	}
	
	/**
	 * Crea una lista con los elementos de la colecciÃ³n
	 * especificada en el orden proporcionado por su iterador.
	 * @param c la colecciÃ³n dada
	 */
	public MutableList(Collection<? extends E> c) {
		this();
		Iterator<? extends E> it = c.iterator();
		while(it.hasNext()) {
			l.add(it.next());
		}
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return new MutableListIterator(index);
	}

	@Override
	public int size() {
		return this.size();
	}
	
	private class MutableListIterator implements ListIterator<E> {
		private Node<E> previous;		// nodo que contiene el elemento
										// que retornarÃ¡ previous()
		private Node<E> current;		// nodo que contiene el elemento
										// que retornarÃ¡ next()
		private Node<E> lastReturned;	// nodo que contiene el Ãºltimo elemento
										// retornado por next() o previous()
		private int currentIndex;		// el Ã­ndice correspondiente a la
										// posiciÃ³n del nodo current		
		
		private MutableListIterator(int index) {
			
		}

		@Override
		public boolean hasNext() {
			return currentIndex<size() ? true : false;
		}

		@Override
		public E next() {
			lastReturned = current;
			previous = lastReturned;
			currentIndex++;
			return hasNext() ? current.item : null;
		}

		@Override
		public boolean hasPrevious() {
			return currentIndex-1>0 ? true : false;
		}

		@Override
		public E previous() {
			current = previous;
			lastReturned = current;
			currentIndex--;
			//?????previous = previous.previous;
			return currentIndex-1>0 ? lastReturned.item : null;
		}

		@Override
		public int nextIndex() {
			return hasNext() ? currentIndex+1 : null;
		}

		@Override
		public int previousIndex() {
			return currentIndex-1>0 ? currentIndex-1 : null;
		}

		@Override
		public void remove() {
			if(lastReturned.item == null)
				throw new IllegalStateException();
			//lastReturned.item = new E();
			currentIndex--;
			size--;

		}

		@Override
		public void set(E e) {
			current = new Node(e,previous,current);
		}

		@Override
		public void add(E e) {
			
		}
		
	}

}
