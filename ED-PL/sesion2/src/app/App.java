package app;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import estDatos.PeekingIterator;
import estDatos.Sucesion;

/**
 * Clase para el programa principal.
 */
public final class App {
	
    private App() {
    }
    
    /**
     * Retorna el término enésimo de la sucesión:
     * {@code 0, 1, 3, 6, 10, 15, 21,…}.
     * @param n el número de término
     * @return el término enésimo de la sucesión:
     * {@code 0, 1, 3, 6, 10, 15, 21,…}.
     */
    private static int nthTerm(final int n) {
    	int term = 0;
    	
    	for (int index = 1; index <= n; index++) {
    		term += index;
    	}
    	
    	return term;
    }

	public static void main(final String[] args) {
		
		// obtener el valor umbral, limit, por teclado
		int limit = -1;
		final Scanner sin = new Scanner(System.in);
		
		do {
			try {
				System.out.print("Umbral (entero no negativo): ");
				limit = sin.nextInt();
			} catch (InputMismatchException e) {
				sin.nextLine();
			}
		} while (limit < 0);

		sin.close();
		
		// muestra la sucesión y la suma de términos mediante
		// la función estática que retorna el término enésimo 
		System.out.println("\nResultados utilizando la función nthTerm");
		
		// primer término
    	int term = nthTerm(0);
    	System.out.print(term);
    	
    	// términos restantes
    	int total = term;
    	int index = 1;
    	term = nthTerm(index);
    	while (term <= limit) {
    		System.out.printf(", %d", term);
    		total += term;
    		index++;
    		term = nthTerm(index);
    	}
    	
		System.out.println();
		System.out.printf("Suma de los términos: %d\n", total);
		
		// hacer lo mismo pero utilizando un iterador PeekingIterator
		System.out.println("\nResultados utilizando el iterador");
		Sucesion s = new Sucesion();
		PeekingIterator<Integer> it = new PeekingIterator<Integer>(s);
		//PeekingIterator<Integer> aux = new PeekingIterator<Integer>(s);
		term = 0;
		while(term < limit && it.hasNext()) {
			term = (int) it.next();
			if((int) it.peek()>limit) 
				System.out.print(term);
			else
				System.out.print(term+", ");
		}
		
	}

}
