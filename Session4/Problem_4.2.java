public class LongestPalindrome {
	/**
	 * Checking whether the following word (taken as a character array)
	 * is a palindrome
	 * @param input		a word
	 * @param start		first character
	 * @param end		last character
	 * @return
	 */
	private static boolean isPal(char[] input, int start, int end){
		while (start < end){
			if (input[start] != input[end])
				return false;
			start++;
			end--;
		}
		
		return true;
	}
	
	/**
	 * Manacher's algorithm used
	 * We make a new string putting delimiters between all characters,
	 * create an array of integers for corresponding character in the string
	 * Each integer indicates the length of a palindrome centered at this character
	 * We notice that around some of elements integers are symmetrically distributed
	 * Thus we can just knowing data found earlier (mirror element is taken)
	 * Compute the new integer value. Variable "right" keeps track of the rightmost element
	 * In the current palindrome longest sequence 
	 * @param s		string to be found the longest palindrome in
	 * @return
	 */
	private static String ManacherAlgorithm(String s){
		if (s == "")
			return "";
		
		String modString = preparation(s);
		
		int[] c = new int[modString.length()];
		
		int exp = 0;
		// to rightmost index of the range for the last expanded element 
		int right = 0;
		
		for (int i = 1; i < modString.length() - 1; i++){
			int mirror = 2*exp - i;
			
			if (right > i)
				c[i] = Math.min(right - i, c[mirror]);
			
			// count the length of a palindrome centered at this element
			while((modString.charAt(i-(1+c[i])) == modString.charAt(i+(1+c[i]))) && (i- c[i] - 1 > 0) && (i+ c[i] + 2 < modString.length())){
				c[i]++;
			}
			
			// in case of the range wider than previous, adjust the expanded element
			if (right < i + c[i]){
				exp = i;
				right = i+c[i];
			}
		}
		
		return maxLength(s,c);
	}
	
	/**
	 * Find the longest substring index in array and return the substring
	 * @param str		string to be returned from
	 * @param c			array of pre-computed values for longest substring
	 * @return
	 */
	private static String maxLength(String str, int[] c){
		if (c == null)
			return null;
		
		int max = c[0];
		int center = 0;
		
		// find maximum element in array
		for (int i = 1; i < c.length; i++){
			if (c[i] > max){
				max = c[i];
				center = i;
			}
		}
		
		return str.substring((center - max)/2, (center - 1 + max)/2 + 1);
	}
	
	/**
	 * Put hash character between characters, in the beginning and end of a sequence
	 * To simplify work with even-odd sequences
	 * @param s		string to modify
	 * @return
	 */
	private static String preparation(String s){
		char[] arr = new char[2*s.length() + 1];
		for (int i = 0; i < s.length(); i++){
			arr[2*i] = '#';
			arr[2*i+1] = s.charAt(i);
		}
		
		arr[2*s.length()] = '#';
		
		String str = new String(arr);
		
		return str; 
		
	}
	
	/**
	 * Intuitive way to find the longest substring(not efficient)
	 * @param str	string to find the longest substring in
	 * @return
	 */
	private static String LongestSubstringUsual(String str){
		if (str == "")
			return "";
		char[] arr = str.toCharArray();
		
		int palStart = 0;
		int palEnd = 0;
		
		for (int start = 0; start < arr.length; start++)
			for (int end = start + 1; end < arr.length; end++)
				if (isPal(arr, start,end)){
					if ((palEnd - palStart) < (end - start)){
						palEnd = end;
						palStart = start;
					}		
				}
		return str.substring(palStart, palEnd + 1);
	}
	
	public static void main(String[] args){
		System.out.println(ManacherAlgorithm("abbbbbbbba"));
		System.out.println(LongestSubstringUsual("abbbbbbbba"));
		System.out.println(ManacherAlgorithm(""));
		System.out.println(LongestSubstringUsual(""));
	}
}
