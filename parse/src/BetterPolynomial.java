package lab6;

import java.util.Iterator;
import java.util.LinkedList;

public class BetterPolynomial extends Polynomial {
	
	// DO NOT redefine the LinkedList here!
	// DO NOT change the access of the LinkedList in Polynomial!
	// It is available privately in Polynomial, but
	//   you do not have direct access to it here.
	// You are therefore forced to compute the product
	//   using methods you already have available from
	//   Polynomial, but NOT access to the list contained therein
	
	/**
	 * Java generates the following constructor automatically,
	 * but I'll put it in here in case you worry about it being
	 * missing.  A BetterPolynomial is made by having Polynomial
	 * do its usual work. Nothing extra is needed for the extension.
	 */
	public BetterPolynomial() {
		super();
		
	}
	
	/**
	 * Copies a Polynomial as a BetterPolynomial. The better
	 *   one can do everything a Polynomial can, as well as perform
	 *   the product method.
	 * @param p A Polynomial
	 */
	public BetterPolynomial(Polynomial p) {
		super();
		Iterator<Double> i = p.getIterator();
		while (i.hasNext()) {
			double d = i.next();
			addTerm(d);
		}
	}

	public Polynomial product(Polynomial another) {
	
		Iterator<Double> it = getIterator();
		
		int indexIt = 0;
		int indexThat = 0;
		
		
		while (it.hasNext()){
			Iterator<Double> that = getIterator();
			Polynomial abc = new Polynomial();
			Double xyz = it.next();
			while(that.hasNext()){
		//	if(that.nextIndex()== null){
				
				
			}
			//	abc.addTerm(coeff)
				abc.addTerm(xyz*that.next());
			}
		
		return null;
		
	}
	
	/*
	public Polynomial superMult (Polynomial another ){
		
	
		Iterator<Double> it = getIterator();
		Iterator <Double>that = getIterator();
		Polynomial abc = new Polynomial(); 
		
		
	
	return null;
	}
	
	public Polynomial multiply(Iterator <Double>it, Iterator<Double> that){
		Polynomial abc = new Polynomial(); 
		return abc.addTerm(multHelp(it.next(), that));
		
		
	}
	
	
	
	public double multHelp(Double x, Iterator <Double> that){
		
		if (that.hasNext()!= true){
			return 0;
		}
		else{
			
			return x*that.next();
			
		}
	*/
/**
 * helper method for evaluate 
 * @param x, from the evaluate method
 * @param it, an Iterator for list
 * @return  
 */
	
	
	
	

		
		
		/*
		public Polynomial product(Polynomial another) {
		Iterator <Double> it = getIterator();
		Iterator <Double>that = getIterator();
		while (it.hasNext()==true){
		
		}}
		
		*/
	
	/*
	public Polynomial productHelp(Double it, Iterator that){
	
	
	Polynomial prod = new Polynomial();
	
		prod.addTerm(it.next()* that.next());
	}
	
	
		/**
		 * 
		 * 
		 * 
		 */
	
/*
		for (int i = 0; i <list.size(); i++){
	
			Polynomial prod = new Polynomial();
			for (int j = 0; j<another.list.size(); j++){
				
				for (int k = 0; k<=i; k++){
						prod.addTerm(0);
				}
				prod.addTerm(prod.get(j)+ list.get(i)*another.list.get(j));
			}
			
		}
		
		return new Polynomial(); // FIXME
	}
	*/
	}

