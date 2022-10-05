package app;

import java.util.Collection;

import estDatos.Bolsa;

/**
 * Clase para el programa principal.
 */
public final class App {

	public static boolean equals(Collection<?> c1, Collection<?> c2) {
		Collection<?> clone = new Bolsa<>(c1);
		
		for(Object obj: c2) {
			if (clone.isEmpty())
				return false;
			clone.remove(obj);
		}
		
		return clone.isEmpty();
	}

    private App() {
    }

	public static void main(final String[] args) {
    	Bolsa<String> b1 = new Bolsa<>();
    	Bolsa<String> b2 = new Bolsa<>();

    	b1.add("ejemplo"); b1.add("de"); b1.add("bolsa");
    	b1.add("con"); b1.add("cadenas"); b1.add("de");
    	b1.add("caracteres");

    	b2.add("de"); b2.add("cadenas"); b2.add("con");
    	b2.add("caracteres"); b2.add("ejemplo"); b2.add("de");
    	b2.add("bolsa");

    	System.out.printf("b1: %s\n", b1);
    	System.out.printf("b2: %s\n", b2);
    	System.out.printf("equals(b1, b2)? %b\n", equals(b1, b2));	// true
    	System.out.printf("b1.equals(b2)? %b ¿por qué?\n", b1.equals(b2));	// false
    	System.out.printf("b1: %s\n", b1);
    	System.out.printf("b2: %s\n", b2);
    	System.out.printf("equals(b1, b2)? %b\n", equals(b1, b2));	// false

	}

}

