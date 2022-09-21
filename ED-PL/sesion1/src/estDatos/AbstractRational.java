package estDatos;

public abstract class AbstractRational implements Rational {
	@Override
	public String toString() {
		if (this.denominator() == 1)
			return String.format("%d", this.numerator());
		else
			return String.format("%d/%d", this.numerator(), this.denominator());
	}
}
