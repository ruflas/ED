package estDatos;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class LPCHash<E> extends AbstractSet<E> implements Set<E>{
	private E[] table; //tabla de elementos
	private int[] status; //estados->0:vacío;1:borrado;2:ocupado
	private int tablesize; //tamaño de la tabla
	private double loadFactorLimit; //factor de carga límite
	private int elements; //número de elementos
	private int deleted; //número de posiciones borradas
	
	public LPCHash() {
		this(11,0.5);
	}
	public LPCHash(int initialCapacity) {
		this(initialCapacity, 0.5);
	}
	public LPCHash(int initialCapacity, double theLoadFactor) {
		this.tablesize = initialCapacity;
		this.loadFactorLimit = theLoadFactor;
		table = (E[]) new Object[this.tablesize];
		status = new int[this.tablesize];
		for(int i = 0; i<this.tablesize; i++) {
			this.status[i] = 0;
		}
		this.elements = 0;
		this.deleted = 0;
	}
	public LPCHash(Collection<? extends E> c) {
		this(c.size());
		this.addAll(c);
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean add(E e) {
		this.resize();
		Integer index = hash(e);
		if(this.status[index] == 2) {
			Integer col = 0;
			while(this.status[index]==2) {
				col++;
				index = rehash(e,col);
			}
		}
		this.table[index] = e;
		this.status[index] = 2;
		this.elements++;
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if(c.isEmpty()) return false;
		for(E e: c) {
			this.add(e);
		}
		return true;
	}
	
	@Override
	public boolean contains(Object e) {
		Integer i = 0;
		while(i<this.elements) {
			if(this.status[i] == 2 && this.table[i].equals(e))
				return true;
			i++;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object e) {
		if(!this.contains(e))
			return false;
		Integer index = 0;
		while(index<this.elements) {
			if(this.table[index].equals(e))
				this.status[index] = 1;
		}
		return true;
	}
	
	@Override
	public int size() {
		return this.tablesize;
	}

	public void resize() {
		if(this.elements/this.tablesize>0.5) {
			E[] aux = this.table;
			int[] statusaux = this.status;
			this.tablesize *= 2;
			this.tablesize++;
			table = (E[]) new Object[this.tablesize];
			status = new int[this.tablesize];
			for(int i = 0; i<aux.length; i++) {
				this.table[i] = aux[i];
				this.status[i] = statusaux[i];
			}
		}
		
	}
	
	private int hash(E e){
		return e.hashCode()%tablesize;
	}
	
	private int rehash(E e, int col){
		return (hash(e)+col)%tablesize;
	}
	
	// Recorre la secuencia de posiciones hash/rehash para e y retorna la posición
	// donde está el elemento e o una posición vacía (si e no está en la tabla)
	private int firstEqualEmpty(E e) {
		if(!this.contains(e)) {
			int i = 0;
			while(i < this.size()) {
				if(this.status[i] == 1)
					return i;
				i++;
			}
		}
		else {
			int i = hash(e);
			int col = 0;
			while(i < this.size()) {
				if(this.status[i] == 1)
					return i;
				i = rehash(e,col);
				col++;
			}
		}
		return hash(e);
	}
	// Recorre la secuencia de posiciones hash/rehash para e y retorna la
	// primera posición borrada o vacía que encuentra
	private int firstEraseEmpty(E e){
		int pos=hash(e);
		int colisiones=0;
		while (colisiones<tablesize) {
			if (status[pos]==2) //ocupado
				pos=rehash(e,++colisiones);
			else
				return pos;
		}
		throw new RuntimeException("demasiados intentos");
	}
	
	public String toPrint() {
		StringBuilder result = new StringBuilder();
		for (int i=0; i<tablesize;i++){
		if (status[i]==2) //ocupado
		result.append(i+": "+table[i]+"\n");
		else
		result.append(i+": "+" "+"\n");
		}
		return result.toString();
	}
	
	@Override
	public String toString() {
		return toPrint();
	}
	
}
