package app;

import estDatos.Rational;
import estDatos.InmutableRational;

/**
 * Clase para el programa principal sobre racionales.
 */
public final class App {

	private static void comparar(final Rational a, final Rational b) {
		int x = a.compareTo(b);
		System.out.printf("%s.compareTo(%s): %d\n", a, b, x);
		if (x == 0) {
			System.out.printf("%s = %s\n", a, b);
		} else if (x > 0) {
			System.out.printf("%s > %s\n", a, b);
		} else {
			System.out.printf("%s < %s\n", a, b);
		}
	}

	private App() {
	}

	/**
	 * Programa principal.
	 *
	 * @param args argumentos del programa
	 */
	public static void main(final String[] args) {
		final Rational r1 = new InmutableRational(2, -6);
		final Rational r2 = new InmutableRational(3, 4);
		final Rational r3 = new InmutableRational(r1.reduce());

		System.out.printf("Racional r1: %s\n", r1);
		System.out.printf("Racional r2: %s\n", r2);
		System.out.printf("Racional r3: %s\n", r3);
		comparar(r1, r2);
		comparar(r1, r3);
		comparar(r2, r3);
		//r3.setNumerator(3);
		System.out.printf("Racional r3: %s\n", r3);
		final Rational r4 = new InmutableRational(2, 3);
		final Rational r5 = new InmutableRational(4, 6);
		System.out.print(r4.equals(r5)+"\n");
	}
}
