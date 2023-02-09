package estDatos;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class OpenHash<E> extends AbstractSet<E> implements Set<E> {
  // Private instance variables
  private ArrayList<TreeSet<E>> table;
  private int elements;
  private int tablesize;
  private double loadFactorLimit;

  // Default constructor
  public OpenHash() {
	    table = new ArrayList<TreeSet<E>>();
	    for (int i = 0; i < tablesize; i++) {
	      this.add(null);
	    }
	    elements = 0;
	    tablesize = 11;
	    loadFactorLimit = 0.75;
}

  // Constructor with initial capacity
  public OpenHash(int initialCapacity) {
    this();
    tablesize = initialCapacity;
  }

  // Constructor with initial capacity and load factor
  public OpenHash(int initialCapacity, double theLoadFactor) {
    this(initialCapacity);
    loadFactorLimit = theLoadFactor;
  }

  // Constructor with collection
  public OpenHash(Collection<? extends E> c) {
    this();
    this.addAll(c);
  }
  
  public OpenHash(OpenHash<E> oh) {
	  this();
	  this.table = oh.table;
	  this.elements = oh.elements;
	  this.tablesize = oh.tablesize;
	  this.loadFactorLimit = oh.loadFactorLimit;
	}
  
  private int hash(Object e) {
    return e.hashCode() % tablesize;
  }
  
  // Add an element to the table
  @Override
  public boolean add(E e) {
	  	this.resize();
	    if (table.isEmpty()) {
	      table.add(new TreeSet<E>());
	    }

	    int index = hash(e);

	    if (table.size() <= index) {
	      for (int i = table.size(); i <= index; i++) {
	        table.add(new TreeSet<E>());
	      }
	    }

	    boolean added = table.get(index).add(e);

	    if (added) {
	      elements++;
	    }

	    return added;
	  }
  
  @Override
  public boolean contains(Object e) {
    int index = hash(e);
    if (index < 0) {
      index += tablesize;
    }
    if (table.get(index) != null) {
      return table.get(index).contains(e);
    }
    return false;
  }

  @Override
  public boolean remove(Object e) {
    int index = hash(e);
    if (index < 0) {
      index += tablesize;
    }
    if (table.get(index) != null && table.get(index).remove(e)) {
      elements--;
      return true;
    }
    return false;
  }

  private void resize() {
	    if (this.elements / this.tablesize > loadFactorLimit) {
	      this.tablesize *= 2;
	      // Rehash the elements
	      OpenHash<E> temp = new OpenHash<E>(this.tablesize);
	      temp.addAll(this);
	      this.loadFactorLimit = this.tablesize;
	      table = temp.table;
	      elements = temp.elements;
	    }
  }
  public String printTable() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < table.size(); i++) {
        sb.append(i + ": ");
        if (table.get(i) != null) {
          for (E e : table.get(i)) {
            sb.append(e + " ");
          }
        }
        sb.append("\n");
      }
      return sb.toString();
    }
@Override
public Iterator<E> iterator() {
	return new MyIterator();
}

@Override
public int size() {
	return this.elements;
}

@Override
public boolean addAll(Collection<? extends E> c) {
  boolean modified = false;
  for (E e : c) {
    if (add(e)) {
      modified = true;
    }
  }
  return modified;
}

@Override
public String toString() {
  String str = "[";
  for (int i = 0; i < table.size(); i++) {
    if (table.get(i) != null && !table.get(i).isEmpty()) {
      for (E e : table.get(i)) {
        str += e + ", ";
      }
    }
  }
  if (str.length() > 1) {
    str = str.substring(0, str.length() - 2);
  }
  str += "]";
  return str;
}

private class MyIterator implements Iterator<E> {
	    private int pos; // position of the table being iterated
	    private Iterator<E> itr; // iterator of the collection at table[pos]
	    private E lastReturned; // for deletions
	
	    public MyIterator() {
	      pos = 0;
	      while (pos < table.size() && table.get(pos) == null) {
	        pos++;
	      }
	      if (pos < table.size()) {
	        itr = table.get(pos).iterator();
	      }
	    }
	
	    @Override
	    public boolean hasNext() {
	      if (itr == null) {
	        return false;
	      }
	      if (itr.hasNext()) {
	        return true;
	      }
	      pos++;
	      while (pos < table.size() && table.get(pos) == null) {
	        pos++;
	      }
	      if (pos < table.size()) {
	        itr = table.get(pos).iterator();
	        return itr.hasNext();
	      }
	      return false;
	    }
	
	    @Override
	    public E next() {
	      if (itr == null) {
	        throw new NoSuchElementException();
	      }
	      lastReturned = itr.next();
	      return lastReturned;
	    }
	
	    @Override
	    public void remove() {
	      if (lastReturned == null) {
	        throw new IllegalStateException();
	      }
	      itr.remove();
	      lastReturned = null;
	    }
	}
}
