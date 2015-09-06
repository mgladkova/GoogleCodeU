public class CoinChange {
	
	/**
	 * Return the number of ways change can be represented with numbers from array
	 * Recursive way of computation
	 * @param array		"coins" to give a change with
	 * @param size		number of coins
	 * @param change	the amount to be covered by "coins"
	 * @return
	 */
	public int changePrecomputed(int[]array, int size, int change){
		// over-payed change with certain sequence of coins - add 0
		if (change < 0)
			return 0;
		
		// if  change is completely covered - add 1
		if (change == 0)
			return 1;
		
		// no more possible coins to cover the change - add 0
		if (size <= 0 && change >=1)
			return 0;
		
		return changePrecomputed(array, size - 1, change) + changePrecomputed(array, size, change - array[size - 1]);
	}
	
	public int changeWays(int[]array, int change){
		return changePrecomputed(array, array.length, change);
	}
	
	public static void main(String[] args){
		CoinChange test1 = new CoinChange();
		int[]array = new int[]{2,5,10};
		System.out.println(test1.changeWays(array, 100));
	}
}
