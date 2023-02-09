package estDatos;

import java.util.Collection;

public class LListBAG<E> extends LListSet<E>{
	public LListBAG() {
		super();
	}
	public LListBAG(Collection<E> c) {
		this();
		this.addAll(c);
	}

	@Override
	public boolean add(E e) {
		return super.data.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return super.data.addAll(c);
	}

}
