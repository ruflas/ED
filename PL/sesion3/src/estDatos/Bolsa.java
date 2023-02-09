package estDatos;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;


/**
 * Tipo de dato mutable de colecciones de elementos con
 * repetición y de capacidad fija.
 *
 * @param <E> el tipo de los elementos de la colección
 */
public class Bolsa<E> extends AbstractCollection<E> {
	// capacidad de una bolsa por defecto
	final private static int DEFAULT_CAPACITY = 20;
	
	private Object[] bag;
	/**
	 * Crea una bolsa vacía de capacidad por defecto.
	 */
	public Bolsa() {
		//Object[] bag = (E[]) new Object[DEFAULT_CAPACITY]; 
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Crea una bolsa  vacía de la capacidad especificada.
	 * Si ésta no es mayor que cero de capacidad por defecto.
	 * @param capacity la capacidad de esta bolsa
	 */
	@SuppressWarnings("unchecked")
	public Bolsa(int capacity) {
		bag = (E[]) new Object[capacity]; 
	}

	/**
	 * Crea una bolsa de capacidad el tamaño de la
	 * colección especificada incrementado en la capacidad
	 * por defecto y que contiene todos los elementos de ésta.
	 * @param c la colección dada
	 */
	public Bolsa(Collection<? extends E> c) {
		this(c.size()+DEFAULT_CAPACITY);
		int cont = 0;
		for(Object o: c) {
			bag[cont] = o;
			if(cont+1<bag.length) cont++;
		}
	}

	/**
	 * Crea una bolsa de capacidad el tamaño de la
	 * colección especificada incrementado en la capacidad
	 * adicional especificada y que contiene todos los elementos
	 * de ésta. Si la capacidad adicional es negativa se toma en
	 * su lugar la capacidad por defecto.
	 * @param c la colección dada
	 * @param additionalCapacity el incremento de capacidad
	 * respecto al tamaño de la colección {@code c}
	 */
	public Bolsa(Collection<? extends E> c, int additionalCapacity) {
		this(additionalCapacity>0 ? c.size()+additionalCapacity : DEFAULT_CAPACITY);
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	@Override
	public int size() {
		return bag.length;
	}
	
	@Override
	public boolean add(Object o) {
		Integer size = this.size();
		if(!this.isEmpty()) {
			Integer cont = 0;
			while(cont<size) {
				if(bag[cont] == null) {
					bag[cont] = o;
					cont = size;
				}
				cont++;
			}
			return true;
		}
		else
			return false;
	}
	
	@Override
	public String toString() {
		String bagElements ="[";
		for(Object o: bag) {
			if(o == bag[bag.length-1])
				bagElements += o;
			else
				bagElements += o+",";
		}
		bagElements += "]";
		return bagElements;
	}
	
	private class MyIterator implements Iterator<E>{
		private int index;
		//private boolean oNext;
		public MyIterator() { index = 0; }
		@Override
		public boolean hasNext() {
			return index<bag.length;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			return hasNext() ? (E) bag[index+1] : null;
		}
		
	}
}
