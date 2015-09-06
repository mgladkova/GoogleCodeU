import java.util.List;
import java.util.ArrayList;

public class Palindrome {
	// I've decided to store all numbers which binary representation is a palindrome in a list
	// for the cases of multiple queries, so no computations are repeated
	private List<Integer>pal;
	
	Palindrome(){
		pal = new ArrayList<Integer>();
	}
	
	/**
	 * Use bit manipulations to find whether a binary representation
	 * of a number is a palindrome
	 * @param x		number to check
	 * @return
	 */
	boolean isPal(int x){
		if (x < 1)
			return false;
		
		int copy = x;
		int rev = 0;
		
		// backwards reading of the number
		// storing the result in rev variable
		// finish when all digits in copy are exhausted
		
		while (copy != 0){
			rev <<= 1;
			rev |= copy & 1;
			copy >>>= 1;
		}
		return (rev == x);
	}
	
	/**
	 * Find kth positive number
	 * Which binary representation is a palindrome
	 * @param k		
	 * @return
	 */
	int kthPal(int k){
		
		if (k < 1)
			return -1;
		
		// case if we've already computed the number
		if (pal.size() >= k && pal.size() != 0){
			return pal.get(k - 1);
		} 
		
		int number = 0;
		
		// get the last number to be computed from
		if (pal.size() != 0)
			number = pal.get(pal.size() - 1);
		
		while(pal.size() < k){
			number++;
			if (isPal(number)){
				pal.add(number);
			}
		}
		
		return number;
	}
	
	public static void main(String[] args){
		Palindrome pal = new Palindrome();
		System.out.println(pal.kthPal(4));
		System.out.println(pal.kthPal(-1));
		System.out.println(pal.kthPal(1));
	}
}
