package estDatos;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
		
		this.size=0;
	}
	
	/**
	 * Crea una lista con los elementos de la colecciÃ³n
	 * especificada en el orden proporcionado por su iterador.
	 * @param c la colecciÃ³n dada
	 */
	public MutableList(Collection<? extends E> c) {
		this();
		this.addAll(c);
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return new MutableListIterator(index);
	}

	@Override
	public int size() {
		return this.size;
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
			if(!hasNext())
				throw new NoSuchElementException();
			lastReturned = current;
			previous = current;
			current = current.next;
			currentIndex++;
			return lastReturned.item;
		}

		@Override
		public boolean hasPrevious() {
			return currentIndex>0 ? true : false;
		}

		@Override
		public E previous() {
			current = previous;
			lastReturned = current;
			currentIndex--;
			previous = previous.previous;
			return currentIndex>0 ? lastReturned.item : null;
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
			if(previous == lastReturned) {
				//next()
				currentIndex--;
				size--;
				if(hasPrevious())
					previous = previous.previous;
				else
					previous = null;
				current = current.next;
				lastReturned = null;
			}
			else if(current==lastReturned) {
				//previous()
				size--;
				if(hasNext())
					current = current.next;
				else
					current = null;
				lastReturned = null;
			}
			
			if(previous == null)
				head = current;
			else if(current == null)
				tail = previous;
		}

		@Override
		public void set(E e) {
			if(lastReturned == null)
				throw new IllegalStateException();
			if(previous == lastReturned) {
				//next()
				lastReturned.item = e;
			}
			else if(current==lastReturned) {
				//previous()
				lastReturned.item = e;
			}
		}

		@Override
		public void add(E e) {
			Node<E> node = new Node<>(e, previous, current);
			if(previous != null && current !=null) {
				previous.next=node;
				current.previous = node;
			}
			else if(previous == null) {
					head = node;
					previous = node;
					if(size == 0)
						tail = node;
			}
			else if(current == null) {
				tail = node;
				previous = node;
			}
			currentIndex++;
			size++;
			lastReturned = null;
		}
		
	}

}
