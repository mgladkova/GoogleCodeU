public class OrderStatistics {
	private int[] array;
	
	/**
	 * Parameterized constructor
	 * @param arr	unordered array of ints
	 */
	OrderStatistics(int[] arr){
		array = new int[arr.length];
		int j = 0;
		for (Integer i: arr){
			array[j] = i;
			j++;
		}
			
	}
	
	/**
	 * Return the position of the pivot (its order)
	 * @param array		initial array of ints
	 * @param left		left-bound index
	 * @param right		right-bound index
	 * @return
	 */
	static int partition(int[] array, int left, int right){
		// choose the first element as a pivot
		int pivot = array[left];
		int pivotPos = left;
		
		//start from the next element
		left++;
		
		while(left <= right){
			
			while((left <= right) && (array[left] < pivot)){
				left++;
			}
			
			while((left <= right) && (array[right] >= pivot)){
				right--;
			}
			
			// if the index from left exceeds the index from right
			// we find the place for the pivot element
			// otherwise we exchanging left index element with
			// the right index one - didn't pass the previous check
			// of two while loops
			if (left > right){
				swap(array, pivotPos, right);
			} else {
				swap(array,left,right);
			}
		}
		
		return right;
	}
	
	/**
	 * Swap two elements in array with certain indices
	 * @param arr	initial array
	 * @param i		index 1
	 * @param j		index 2
	 */
	static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Order statistics technique use
	 * if the pivot is on place we need - return this element
	 * if not - choose whether call the routine on left branch
	 * or on the right branch
	 * @param arr		initial array
	 * @param n			position to seek in ordered array
	 * @param left		left-index bound
	 * @param right		right-index bound
	 * @return
	 */
	static int ordStat(int[] arr, int n, int left, int right){
		int pivot = partition(arr,left,right);
		
		// cope with difference in counting the rank in ordered array
		// and indices count
		if (pivot == n - 1){
			return arr[n-1];
		} else if (n-1 < pivot){
			// go to left branch
			return ordStat(arr, n, left, pivot - 1);
		} else {
			// go to right branch
			return ordStat(arr, n, pivot + 1, right);
		}
	}
	
	/**
	 * Return the nth largest element
	 * @param arr	initial array
	 * @param n		rank
	 * @return
	 */
	static int nthLargest(int[] arr, int n){
		// as we return the nth largest we go from the end in ordered array
		return ordStat(arr, arr.length - n + 1, 0, arr.length - 1);
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{5, 3, 9, 4, 3, 3, 12, 3, 3, 10, 11};
		OrderStatistics test1 = new OrderStatistics(arr);
		System.out.println(nthLargest(test1.array, 1));
	}
}
