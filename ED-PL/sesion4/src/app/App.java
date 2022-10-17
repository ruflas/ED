package app;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import estDatos.MutableList;

/**
 * Clase para el programa principal.
 */
public final class App {
			
    private App() {
    }
    
	public static void main(final String[] args) {
		List<Character> sel = 
				Stream.of('c', 't', 'a', 'g', 'd', 'k', 'v').collect(Collectors.toCollection(MutableList::new));
		List<Character> ml = new MutableList<>(sel);
		
		System.out.printf("Lista sel: %s\n", sel);
		System.out.printf("Lista ml : %s\n", ml);
		System.out.printf("sel.equals(ml)? %b\n", sel.equals(ml));
		System.out.println();
		
		System.out.println("Operaciones en sel: remove('a') y add('z')");
		sel.remove(Character.valueOf('a')); // remove('a') => remove(ASCII('a')) y excepción
		sel.add('z');
		System.out.printf("Lista sel: %s\n", sel);
		System.out.println("Operaciones en ml: add(5, 'z') y remove(2)");
		ml.add(5, 'z');
		ml.remove(2);
		System.out.printf("Lista ml : %s\n", ml);
		System.out.println();
		
		System.out.printf("Lista sel: %s\n", sel);
		System.out.printf("Lista ml : %s\n", ml);
		System.out.printf("sel.equals(ml)? %b\n", sel.equals(ml));
		System.out.printf("sel.indexOf('a'): %d\n", sel.indexOf('a'));
		System.out.printf("ml.indexOf('k'): %d\n", ml.indexOf('k'));
		System.out.printf("sel.containsAll(ml)? %b\n", sel.containsAll(ml));
	}

}

