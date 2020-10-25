import java.util.*;

public class HomeworkM7Driver {

	public static void main(String[] args) {
		
		System.out.println("\n********************************************TESTING ARRAY REVERSER");
		testArrayReverse(new int[] {1, 2, 3, 4, 5}, "odd length array");
		testArrayReverse(new int[] {1, 2, 3, 4, 5, 6}, "even length array");
		testArrayReverse(new int[] {7, 8}, "array length 2");
		testArrayReverse(new int[] {5}, "array length 1");
		testArrayReverse(new int[] {}, "empty array");

					
		System.out.println("\n********************************************TESTING Q WITHOUT U");
		testQNotFollowedByU("hello", false, "no q; odd length");
		testQNotFollowedByU("cats", false, "no q; even length");
		testQNotFollowedByU("bulb", false, "no q but has a u; odd length");
		testQNotFollowedByU("puts", false, "no q but has a u; even length");
		testQNotFollowedByU("a", false, "single letter");
		testQNotFollowedByU("", false, "empty string");
		testQNotFollowedByU("quite", false, "q-followed-by-u at the beginning of the word");
		testQNotFollowedByU("equal", false, "q-followed-by-u in the middle of a word; odd length");
		testQNotFollowedByU("aqua", false, "q-followed-by-u in the middle of a word; even length");
		testQNotFollowedByU("abcqu", false, "q-followed-by-u q followed by u at the end of the word, odd length");
		testQNotFollowedByU("abcdqu", false, "q-followed-by-u q followed by u at the end of the word, even length");
		testQNotFollowedByU("qu", false, "q-followed-by-u and nothing else");
		testQNotFollowedByU("QUOTE", false, "q-followed-by-u in all-caps");
		
		testQNotFollowedByU("qat", true, "q-not-followed-by-u at the beginning of the word");
		testQNotFollowedByU("cinq", true, "q-not-followed-by-u at the end of the word; even length");
		testQNotFollowedByU("drinq", true, "q-not-followed-by-u at the end of the word; odd length");
		testQNotFollowedByU("abqc", true, "q-not-followed-by-u in the middle of the word, even length");
		testQNotFollowedByU("abqcd", true, "q-not-followed-by-u in the middle of the word, odd length");
		testQNotFollowedByU("squaq", true, "q-not-followed-by-u in a word that also has a \"qu\" before it");
		testQNotFollowedByU("bubqb", true, "q-not-followed-by-u in a word that also has a \"qu\" before it");
		testQNotFollowedByU("qaqu", true, "q-not-followed-by-u in a word that also has a \"qu\" after it");
		testQNotFollowedByU("qiteu", true, "q-not-followed-by-u right away, but with a u later on, odd length");
		testQNotFollowedByU("qeut", true, "q-not-followed-by-u right away, but with a u later on, even length ");
		testQNotFollowedByU("q", true, "q all on its own- single letter");
		testQNotFollowedByU("qq", true, "q all on its own- single letter");
		testQNotFollowedByU("uq", true, "q all on its own- single letter");
		testQNotFollowedByU("QAT", true, "q-not-followed-by-u in caps");

		
		System.out.println("\n********************************************TESTING GET FREQUENCY OF");
		testGetFrequencyOf(new Integer[] {}, 12, 0, "empty bag"); // second parameter is the target value; third parameter is the expected result
		testGetFrequencyOf(new Integer[] {13}, 13, 1, "singleton bag that contains the target");
		testGetFrequencyOf(new Integer[] {13}, 5, 0, "singleton bag that does not contain the target");
		testGetFrequencyOf(new Integer[] {15, 2, 5, 4, 7, 5}, 15, 1, "target is in the bag at the beginning");
		testGetFrequencyOf(new Integer[] {5, 2, 5, 4, 7, 25}, 25, 1, "target is in the bag at the end");
		testGetFrequencyOf(new Integer[] {5, 2, 5, 4, 7, 5}, 4, 1, "target is in the middle");
		testGetFrequencyOf(new Integer[] {5, 2, 5, 4, 7, 5}, 5, 3, "target is in the bag multiple times");
		testGetFrequencyOf(new Integer[] {5, 2, 5, 4, 7, 5}, 8, 0, "target not in the bag, even length");
		testGetFrequencyOf(new Integer[] {5, 2, 5, 4, 7, 5, 2}, 8, 0, "target not in the bag, odd length");
		testGetFrequencyOf(new String[] {"a", "b", "c", "d", "e", "a"}, new String("a"), 2, "test with Strings");


		System.out.println("\n********************************************TESTING EXTRA CREDIT COUNT POSITIVES");	
		testCountPositives(new int[] {2, -1, 3, 5, -2, 4, 1}, 5, "odd number of elements");
		testCountPositives(new int[] {1, -4, -2, 5, 3, -1, 2}, 4, "even number of elements");
		testCountPositives(new int[] {-3, -4, -2, -5, -8}, 0, "no positives");
		testCountPositives(new int[] {6, 4, 3, 2, 1, 7, 9}, 7, "all positives");
		testCountPositives(new int[] {}, 0, "empty bag");
		testCountPositives(new int[] {6}, 1, "singleton bag with positive");
		testCountPositives(new int[] {-6}, 0, "singleton bag without positive");
	}

	public static boolean qNotFollowedByU(String word) {
		// YOUR CODE HERE
		return qNotFollowedByUHelper(word,0);
	}

	private static boolean qNotFollowedByUHelper(String word, int index){
		if (word.length() <= 0){
			return false;
		}
		if (index >= 0 && index <= word.length()-1){
			boolean isQ = (word.charAt(index) == 'q' || word.charAt(index) == 'Q');
			boolean notFollowedU = true;
			if (index <= word.length()-2) {
				notFollowedU = word.charAt(index+1) != 'u' && word.charAt(index+1) != 'U';
			}
			if (isQ && notFollowedU) {
				return true;
			} else {
				return qNotFollowedByUHelper(word,index+1);
			}
		}
		return false;
	}

	public static void arrayReverse(int[] array) {
		// YOUR CODE HERE
		arrayReverseHelper(array,0,array.length -1);
	}

	private static void arrayReverseHelper(int[] array, int indexLeft, int indexRight){
		if (indexLeft<indexRight){
			int valueLeft = array[indexLeft];
			int valueRight = array[indexRight];
			array[indexLeft] = valueRight;
			array[indexRight] = valueLeft;
			arrayReverseHelper(array,indexLeft+1,indexRight-1);
		}
	}

	public static int countPositives(BagInterface<Integer> bag) {
		// YOUR EXTRA CREDIT CODE HERE
		return countPositivesHelper(bag,bag.getCurrentSize());
	}

	private static int countPositivesHelper(BagInterface<Integer> bag, int size){
		if (size <=0) {
			return 0;
		} else {
			int removedBag = bag.remove();
			int count = 0;
			if (removedBag > 0) {
				count =  1+ countPositivesHelper(bag,size-1);
			} else {
				count =  countPositivesHelper(bag,size -1);
			}
			bag.add(removedBag);
			return count;
		}
	}


	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */
	
	public static void testArrayReverse(int[] array, String testDescription) {
		int[] arrayToReverse = Arrays.copyOf(array, array.length);
		int[] originalArray = Arrays.copyOf(array, array.length);
		int[] correctReverseArray = new int[array.length];
		for(int i=array.length-1, j=0; i>=0; i--, j++ ) {
			correctReverseArray[j] = originalArray[i];
		}
		arrayReverse(arrayToReverse);
		System.out.println("Original array = " + Arrays.toString(originalArray) + "\tReversed array = " + Arrays.toString(arrayToReverse));
		if(!Arrays.equals(correctReverseArray, arrayToReverse)) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected result = " + Arrays.toString(correctReverseArray) + "\tActual result = " + Arrays.toString(arrayToReverse));
		}
	}
	
	public static void testQNotFollowedByU(String word, boolean expectedResult, String testDescription) {
		boolean actualResult =  qNotFollowedByU(word);
		System.out.println("Word contains a \"q\" that is not immediately followed by a \"u\"?\t" + word + "\t" + actualResult);
		if(actualResult != expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);
		} 
	}
	
	public static <T> void testGetFrequencyOf(T[] bagContents, T value, int expectedResult, String testDescription) {
		LinkedBag<T> originalBag = new LinkedBag<T>();
		LinkedBag<T> copyBag = new LinkedBag<T>();
		for(T n : bagContents) {
			originalBag.add(n);
			copyBag.add(n);
		}
		int actualResult = originalBag.getFrequencyOf(value);
		System.out.println("Bag contains: " + Arrays.toString(bagContents) + "\tCount of " + value + " is " + actualResult);
		if(actualResult!=expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);
		} 
		Object[] originalBagContentsAfterRemove = originalBag.toArray();
		Arrays.sort(originalBagContentsAfterRemove);
		Object[] copyBagContents = copyBag.toArray();
		Arrays.sort(copyBagContents);
	
		if(!Arrays.equals(originalBagContentsAfterRemove, copyBagContents)) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   The bag has been modified.\tSorted contents after invoking frequency = " + Arrays.toString(originalBagContentsAfterRemove));
		}
	}
	
	public static void testCountPositives(int[] bagContents, int expectedResult, String testDescription) {
		LinkedBag<Integer> originalBag = new LinkedBag<Integer>();
		LinkedBag<Integer> copyBag =  new LinkedBag<Integer>();
		for(int n : bagContents) {
			originalBag.add(n);
			copyBag.add(n);
		}
		int actualResult = countPositives(originalBag);
		Object[] originalBagContentsAfterCount = originalBag.toArray();
		Arrays.sort(originalBagContentsAfterCount);
		Object[] copyBagContents = copyBag.toArray();
		Arrays.sort(copyBagContents);

		System.out.println("Bag contains: " + Arrays.toString(copyBagContents) + "\tNumber of positives=" + actualResult);
		
		if(actualResult!=expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);
		}
		if(!Arrays.equals(originalBagContentsAfterCount, copyBagContents)) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   The bag has been modified.\tSorted contents after invoking frequency = " + Arrays.toString(originalBagContentsAfterCount));
		}
	}
}
