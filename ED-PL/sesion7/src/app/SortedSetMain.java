package app;


import java.util.Arrays;
import java.util.Iterator;

import estDatos.LListSet;
import estDatos.SortedLListSet;

public class SortedSetMain {	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedLListSet<Integer> s=new SortedLListSet<Integer>();
		s.add(25);
		s.add(30);
		s.add(3);
		s.add(30);
		s.add(5);
		s.add(15);
		System.out.println("s: "+s);	
		
	    System.out.println("s: contiene 66? "+s.contains(66));
		System.out.println("s: contiene 3? "+s.contains(3));
		
		//Redefinir equals y luego descomentar		
/*		OrderedLListSet<Integer> s2=new OrderedLListSet<Integer>(s);
		System.out.println("s: equals(s2)? "+s.equals(s2));
*/	
		
		//Redefinir addAll y luego descomentar
/*		Integer [] a={1,3,15,27,39,41};
		s.addAll(Arrays.asList(a));
		System.out.println("s: "+s);			
*/
	}		
}

/*******************  SALIDA: **************************
s: [3, 5, 15, 25, 30]
s: contiene 66? false
s: contiene 3? true
s: equals(s2)? true
s: [1, 3, 5, 15, 25, 27, 30, 39, 41]
******************************************************/
