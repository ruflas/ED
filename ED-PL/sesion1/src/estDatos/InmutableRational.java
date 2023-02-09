package estDatos;

public class InmutableRational extends AbstractRational {
	private Pair<Integer, Integer> par;

	public InmutableRational(Integer num, Integer denom) {
		//PairImp<Integer, Integer> parImp = (PairImp<Integer, Integer>) par;
		if (denom != 0)
			par = new PairImp<Integer, Integer>(num,denom);
		else
			throw new ArithmeticException();
	}

	public InmutableRational(Rational r) {
		//PairImp<Integer, Integer> parImp = (PairImp<Integer, Integer>) par;
		if(r instanceof InmutableRational) {
			InmutableRational other = (InmutableRational) r;
			this.par = other.par;
		}
		else {
			if (r.denominator() != 0)
				par = new PairImp<Integer, Integer>(r.numerator(),r.denominator());
			else
				throw new ArithmeticException();	
		}
	}

	@Override
	public int numerator() {
		return par.first();
	}

	@Override
	public int denominator() {
		return par.second();
	}

	@Override
	public Rational reduce() {
		Integer num = par.first();
		Integer denom = par.second();
		 for (int i = 2; i <= denom+1; i++) {
	            if(num%i==0 && denom%i==0)
	            {
	                num/=i;
	                denom/=i;
	                i=2;
	            }
	        }
		 return new InmutableRational(num,denom);
	}

	@Override
	public int compareTo(Rational o) {
		InmutableRational other = new InmutableRational(o);
		if(par.second()*other.numerator()>other.denominator()*par.second())
			return 1;
		else if(par.second()*other.numerator()==other.denominator()*par.second())
			return 0;
		else 
			return -1;
	}

}
