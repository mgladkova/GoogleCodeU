import java.util.List;
import java.util.ArrayList;

public class UglyNumbers {
	// to store all "ugly numbers", in order to avoid recomputations
	List<Integer> uglies;
	
	UglyNumbers(){
		uglies = new ArrayList<Integer>();
	}
	
	/**
	 * Factorize the number (different prime numbers are only taken)
	 * @param number		number to factorize
	 * @return
	 */
	public List<Integer> factorization(int number){
		if (number < 1)
			return null;
		int copy = number;
		
		List<Integer> list = new ArrayList<Integer>();
		
		// keep iteration not to the number, but to already its divisor
		for (int i = 2; i <= copy/i; i++){
			while (copy % i == 0){
				if (list.lastIndexOf(i) < 0){
					list.add(i);
				}
				copy /= i;
			}
		}
		
		// if not divised fully, but a prime number is left
		if (copy > 1)
			list.add(copy);
		
		return list;
	}
	
	/**
	 * Check whether the number is ugly: its factors are only 2, 3, and 5
	 * @param number		number to check
	 * @return
	 */
	public boolean isUgly(int number){
		if (number < 1)
			return false;
		
		if (number == 1)
			return true;
		
		List<Integer> factors = factorization(number);
		
		for (int i = 0; i < factors.size(); i++){
			int element = factors.get(i);
			if (element != 2 && element != 3 && element != 5)
				return false;
		}
		
		return true;
	}
	
	public boolean isPositive(int k){
		return (k > 0);
	}
	
	/**
	 * Return the kth number, is already pre-computed - take from the array
	 * @param k		order of an ugly number to be found
	 * @return
	 */
	public int kthUgly(int k){
		if (k < 1)
			return -1;
		
		if (uglies.size() >= k && !uglies.isEmpty())
			return uglies.get(k - 1);
		
		int entry = isPositive(uglies.size() - 1) ? uglies.get(uglies.size() - 1) : 0;
		
		while(uglies.size() < k){
			entry++;
			if (isUgly(entry))
				uglies.add(entry);
		}
		
		return uglies.get(k - 1);
			
	}
	
	public static void main(String[] args){
		UglyNumbers test1 = new UglyNumbers();
		
		System.out.println(test1.kthUgly(7));
		
		System.out.println(test1.kthUgly(0));
		
		System.out.println(test1.kthUgly(-1));
	}
}
