import java.util.Arrays;
import java.util.List;

public class Majority {
	
	/**
	 * Return the mean of two numbers
	 * @param left
	 * @param right
	 * @return
	 */
	static int getMid(int left, int right){
		return (left+right)/2;
	}
	
	/**
	 * Swap two elements with given indices
	 * @param list		initial list
	 * @param i			index1
	 * @param j			index2
	 */
	static void swap (List<Integer> list, int i, int j){
		int temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	/**
	 * Take a pivot in the middle of the list
	 * Put elements smaller on the left side and bigger on the right one 
	 * @param list		initial list of ints
	 * @param left		left bound
	 * @param right		right bound
	 * @return
	 */
	static int partition(List<Integer>list, int left, int right){
		if (list.size() == 0)
			return 0;
		
		int i = left;
		int j = right;
		// choosing a pivot
		int pivot = list.get(getMid(left,right));
		
		while (i <= j){
			while (list.get(i)< pivot)
				i++;
			while (list.get(j) > pivot)
				j--;
			if (i <= j){
				swap(list, i, j);
				i++;
				j--;
			}
		}
		
		return i;
	}
	
	/**
	 * Quicksort technique use for sorting a list
	 * @param list	 	initial list for sorting
	 * @param left		left-bound index of list
	 * @param right		right-bound index of list
	 */
	
	static void quicksort(List<Integer>list, int left, int right){
		int part = partition(list, left, right);
		
		if (part != 0){
			if (left < part -1){
				quicksort(list, left, part - 1);
			}
		
			if (right > part){
				quicksort(list, part, right);
			}
		}
		
	}
	
	/**
	 * Return the mode of the list
	 * @param list		initial list
	 * @return			
	 */
	static int hasMajority(List<Integer> list){
		quicksort(list, 0, list.size() - 1);
		
		int maxLength = 0;
		int currentLength = 0;
		
		// case of empty list
		if (list.size() != 0){
			for (int i = 0; i < list.size() - 1; i++){
				if (list.get(i) != list.get(i+1)){
					if (currentLength > maxLength)
						maxLength = currentLength;
					currentLength = 0;
				}
				
				currentLength++;
			}
		}
		
		// if no majority found
		if (maxLength > 1)
			return maxLength;
		else
			return 0;
	}
	
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(1,2,5,4);
		System.out.println(hasMajority(list));
	}
}
