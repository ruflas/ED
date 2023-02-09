package app;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import estDatos.Poly;

public class Polynomial implements Poly {
	
	private TreeMap<Integer,Double> terms;
	
	public Polynomial() {
		terms = new TreeMap<Integer,Double>();
	}
	
	public Polynomial(Integer exp, Double value) {
		this();
		this.put(exp, value);
	}
	
	public Polynomial(Polynomial p) {
		this();
		this.terms.putAll(p.terms);
	}
	
	@Override
	public Double put(Integer exp, Double coef) {
		if(!terms.containsKey(exp)) return terms.put(exp, coef);
		Set<Map.Entry<Integer,Double>> termsMap = terms.entrySet();
		Iterator<Map.Entry<Integer,Double>> it = termsMap.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer,Double> term = it.next();
			if(term.getKey()==exp) return terms.put(exp, coef+term.getValue());
		}
		return 0.0;
	}

	@Override
	public Double remove(Integer exp) {
		return terms.remove(exp);
	}

	@Override
	public Double coefficient(Integer exp) {
		Double coef = 0.0;
		if(!terms.containsKey(exp))
			return coef;
		Set<Map.Entry<Integer,Double>> termsMap = terms.entrySet();
		Iterator<Map.Entry<Integer,Double>> it = termsMap.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer,Double> term = it.next();
			if(term.getKey()==exp) coef = term.getValue();
		}
		return coef;
	}

	@Override
	public Integer degree() {
		return terms.lastKey();
	}

	@Override
	public Double evaluate(Integer x) {
		if(terms.isEmpty()) return 0.0;
		Double result = 0.0;
		Set<Map.Entry<Integer,Double>> termsMap = terms.entrySet();
		Iterator<Map.Entry<Integer,Double>> it = termsMap.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer,Double> term = it.next();
			result += Math.pow(x, term.getKey())*term.getValue();
		}
		return result;
	}
	
	/**
	* Calcula el polinomio suma de los dos especificados.
	* @param p1
	* @param p2
	* @return el polinomio suma de los especificados
	*/
	public static Polynomial sum (Polynomial p1, Polynomial p2) {
		if(p1.terms.isEmpty()) return p2;
		if(p2.terms.isEmpty()) return p1;
		Set<Map.Entry<Integer,Double>> termsMap = p1.terms.entrySet();
		Iterator<Map.Entry<Integer,Double>> it = termsMap.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer,Double> termp1 = it.next();
			p2.put(termp1.getKey(),termp1.getValue());
		}
		return p2;
	}
	
	@Override
	public String toString() {
		String result = "";
		if(terms.isEmpty()) return result;
		Set<Map.Entry<Integer,Double>> termsMap = terms.entrySet();
		Iterator<Map.Entry<Integer,Double>> it = termsMap.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer,Double> term = it.next();
			if(term.getKey()!=0 || term.getValue()!=0) {
				String st = ""+term.getValue()+'x'+term.getKey();
				if(terms.lastKey()!=term.getKey() && term.getValue()>0)
					st='+'+st;
				result = st+" "+result;	
			}
		}
		return result;
	}
}
