package app;
import java.util.*;

import estDatos.LPCHash;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LPCHash<Integer> t=new LPCHash<Integer>();
		System.out.println(t);
		t.add(7); t.add(15);t.add(34);
		t.add(4);t.add(19);t.add(7);
		System.out.println(t);
		//pruebo de la redimension 
		t.add(25);t.add(30);t.add(3);
		System.out.println(t);
		
        //prueba de insercion y redimension con cadenas de caracteres
		LPCHash<String> ts=new LPCHash<String>(7);
		ts.add("pepe"); ts.add("ppee");ts.add("hola");ts.add("adios");ts.add("Jaime");ts.add("Pedro");
		System.out.println(ts);
		
		//prueba del constructor de copia
		LPCHash<String> ts2=new LPCHash<String>(ts);
		System.out.println(ts2);
		
		Integer [] a={10,25,33,40,54,69,70};
		TreeSet<Integer> s=new TreeSet<Integer>();
		s.addAll(Arrays.asList(a));
		LPCHash<Integer> ti=new LPCHash<Integer>(14);
		ti.addAll(s);
		System.out.println(ti);

		ti.resize();
		System.out.println(ti);
			
	}

}
