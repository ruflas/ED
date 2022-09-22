package estDatos;

import java.util.Objects;

public abstract class AbstractRational implements Rational {
	@Override
	public String toString() {
		if (this.denominator() == 1)
			return String.format("%d", this.numerator());
		else
			return String.format("%d/%d", this.numerator(), this.denominator());
	}
	@Override
	public int hashCode() {
	 final int prime = 31;
	 int result = super.hashCode();
	 result = prime * result + Objects.hash(this.reduce().denominator(), this.reduce().numerator());
	 return result;
	}
	@Override
	public boolean equals(Object obj) {
	 if (this == obj)
	 return true;
	 if (!(obj instanceof Rational))
	 return false;
	 Rational other = (Rational) obj;
	 return this.reduce().denominator() == other.reduce().denominator() &&
	 this.reduce().numerator() == other.reduce().numerator();
	}
}
