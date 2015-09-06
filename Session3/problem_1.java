import java.util.List;
import java.util.ArrayList;

public class TrivialDictionary {
	static List <String> dict = new ArrayList<String>();
	static String wordAt(int position){
		if (position < 0 || position > dict.size())
			return "null";
		return dict.get(position);
	}
	
	/**
	 * Find the mean of two numbers
	 * @param left		
	 * @param right		
	 * @return	
	 */
	static int getMid(int left, int right){
		return left + (right - left)/2;
	}
	
	/**
	 * Return true if a word is in dictionary, false otherwise
	 * Dictionary is assumed to have words in alphabetic order
	 * Binary search technique is used
	 * 
	 * @param word	a word we are looking for
	 * @return
	 */
	static boolean isInDictionary(String word){
		int mid; 
		int left = 0;
		int right = TrivialDictionary.dict.size();
		
		while (left <= right){
			mid = getMid(left,right);
			
			//couldn't find a solution how to speed up and store the calls
			String midWord = TrivialDictionary.wordAt(mid);
			
			// if the word is found in the middle
			if (midWord.compareTo(word) == 0){
				return true;
			// if it is alphabetically greater - move to the right sub-part
			} else if (midWord.compareTo(word) < 0){
				left = mid + 1;
			// otherwise move to the left sub-part
			} else 
				right = mid - 1;
		}
		
		return false;
	
	}
	
	public static void main (String[] args){
		TrivialDictionary.dict.add("Anna");
		TrivialDictionary.dict.add("Brad");
		TrivialDictionary.dict.add("Collie");
		TrivialDictionary.dict.add("Daisy");
		TrivialDictionary.dict.add("Ericka");
		TrivialDictionary.dict.add("Fred");
		TrivialDictionary.dict.add("Frog");
		TrivialDictionary.dict.add("Zack");
		
		String name = "Anik";
		
		System.out.println(TrivialDictionary.wordAt(2));
		if (TrivialDictionary.isInDictionary(name)){
			System.out.println(name + " is in our list!");
		} else {
			System.out.println(name + " is not in our list!");
		}
	}
}
