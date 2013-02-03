package lab2;
/**
 * Name: Gaurav	Singhal	
 * Lab Section: 501 N A	
 * Date: Jan 31, 2010
 * Email: gsinghal@wustl.edu	
 * Recursion.java
 * CSE 131 Lab 2
 */
//TA Grade:100
//import java.lang.Object;
public class Recursion {

	// Example:
	static int factorial(int k) {
		if (k == 0)
			return 1;
		else
			return k * factorial(k-1);
	}

	// method sumDownBy2

	static int sumDownBy2 (int k ){ 
		if (k <=0)
			return 0;
		else 
			return k + sumDownBy2(k-2);

	}

	static double harmonicSum(int k){

		if (k<=0) {
			return 0.0;
		}

		else { 


			return harmonicSum(k-1)+ 1.0/k;

		}
	}


	static double geometricSum(int k){
//TA Style comment you have too much space.
		if (k<0){
			return 0.0;}

		else{ 


			return 1/Math.pow(2.0,k) + geometricSum(k-1);	
		}

	}


	static int multHelp(int j, int k) {

		if (k==1){
			return j;
		}
		else {

			return  j + multHelp(j, (k-1));

		}
	}

	static int mult(int j, int k){
		// this method is just to take care of the signs
		// of j and k
		
		if ((k==0)||(j==0)){
			return 0;
		}
		else {
			if (k<0 ^ j<0){
				return 0 - (multHelp (Math.abs(j), Math.abs(k)));  
			}
			else if (k<0 && j<0) {
				return multHelp(Math.abs(j), Math.abs(k));
			}
			else{
				return multHelp(j,k);
			}
		}
	}

	static int exptHelp(int n, int k) {

		if (k==1){
			return n;
		}
		else {

			return  n* exptHelp(n, (k-1));

		}
	}

	static int expt(int n, int k){ // this method is just to take care of the signs
		// of n and k
		if (k==0){
			return 1;
		}
		else if (k<0){
			return 0;
		}
		else if ( n<0   &&   ((k%2)== 0) ){
			return (exptHelp (Math.abs(n), k)); 
		}
		else if (n<0  &&   ((k%2)!= 0) ) {
			return 0 - exptHelp(Math.abs(n), k);
		}
		else{
			return exptHelp(n,k);
		}
	}


	static int lcmHelp(int j, int k, int n){

		if ((n%k) == 0) {  // as indicated in the hints, I add j to j and see if the sum becomes completely 
			// divisible by k. 
			//eg, is j%k==0 ? 
			//    is (j+j)%k ==0 ?
			//    is (j+j+j)%k ==0 ?  
			return n;
		}
		else {

			return lcmHelp(j, k, n+j);



		}
	}

	static int lcm (int j, int k){
		if (j<1 || k<1)
		{return 0;}
		else if (j==k){
			return j;
		}
		else if (j>k){
		
			return lcmHelp(j,k,j);

		}
		else 
			return lcmHelp(k,j,k); // just to make it computationally efficient, the bigger number
									// is added to itself to get the lcm in minimum number of steps

	}


	static double loanLength(double p, double rate, double emi){

		double months = 0;  // if i could supply months (as 0) in the argument
  // i could have done without this method, and could have directly called helpLoanLength with 4 args
		return helpLoanLength(p, rate,emi, months);


	}

	static double helpLoanLength(double p, double rate, double years, double time){

		double newP = p*(1+rate/4);    // this is the new principal obtained after 
		//adding the interest and then deducting the emi

		if ((years*4) > time){  // so that even when the principal left is less than emi, 
			//the last payment which is less than emi still remains 

			System.out.println("Term :" + time + " Your Principal =" + p);

			return helpLoanLength (newP, rate, years, time+1);	
		}

		else {

			return newP;

		}

	}






}










