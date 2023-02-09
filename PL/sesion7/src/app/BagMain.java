package app;

import java.util.Arrays;

import estDatos.LListBAG;

public class BagMain {

	public static void main(String[] args) {
		Integer [] a={1,5,3,7,11,9};
		
		LListBAG<Integer> s=new LListBAG<Integer>();
		s.add(25);
		s.add(30);
		s.add(3);
		s.add(30);
		System.out.println("s: "+s);
		
		s.addAll(Arrays.asList(a));
		System.out.println("s: "+s);	
		System.out.println("s vacio?: "+s.isEmpty());
		System.out.println("s contiene el 3?: "+s.contains(3));
		
		LListBAG<Integer> s1=new LListBAG<Integer>(s);
		System.out.println("s1: "+s1);
		
		s1.removeAll(s);
		
		System.out.println("s1: "+s1);	
		
		LListBAG<String> t=new LListBAG<String>();
		t.add("Pedro");
		t.add("Luis");
		t.add("Maria");
		t.add("Ana");
		t.add("Ana");
		
		System.out.println("t: "+t);	
		t.remove("Luis");	
		System.out.println("t: "+t);	

	}

}
