public class WordPartition {
	private String[] dictionary;
	
	WordPartition(){
		dictionary = new String[]{"app", "lepie", "my", "mom", "cooks",
				"awesome", "food", "apple", "pie", "am", "an", "aman", "!"};
	}
	
	/**
	 * Check whether the word is in the dictionary
	 * @param word		word to be checked
	 * @return
	 */
	public boolean isInDictionary(String word){
		
		for (int i = 0; i < dictionary.length; i++){
			if (word.equals(dictionary[i])){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Print all possible partitions of the sequence of characters according to the dictionary
	 * Take all possible prefixes from original string, take the prefix away
	 * Recursively call the method on the rest of the string for each case   
	 * @param word		original sequence
	 * @param size		its length
	 * @param output	the modified sequence with words from the dictionary
	 */
	public void wordPartHelper(String word, int size, String output){
		
		for (int i = 0; i <= size; i++){
			String part = word.substring(0,i);
			
			if (isInDictionary(part)){
				if (i == size){
					output += part;
					System.out.println(output);
					return;
				}
				// call on the rest of the character sequence 
				wordPartHelper(word.substring(i, size), size - i, output + part + " ");
			}
				
		}
	}
	
	public void wordPart(String word){
		wordPartHelper(word, word.length(), "");
	}
	
	public static void main(String[] args){
		WordPartition test1 = new WordPartition();
		String words = new String("aman");
		test1.wordPart(words);
		
		words = "";
		test1.wordPart(words);
		
		words = "mymomcooksawesomefood!";
		test1.wordPart(words);
	}
}
