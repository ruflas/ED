package estDatos;

import java.util.Iterator;

public class PeekingIterator<E> implements PeekIterator<Object>, Cloneable {
	private Iterator<E> it;
	public PeekingIterator(Iterable<E> t){
		it = t.iterator();
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
		return it.next();
	}

	@Override
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	@Override
	public Object peek() {
		PeekingIterator<E> aux = new PeekingIterator<E>(it); //Es lo mismo que aux = it ???
		return  aux.next();
	}
}
