import java.util.*;

public class HomeworkM6Driver {

	// I recommend changing these to smaller numbers to start with!
	// Then once you think you have a good solution, you can increase them for more robust testing.
	public static final int LIST_SIZE = 100; 
	public static final int SINGLE_DUPLICATE_NUMBERS = 10; // minimum number of numbers on the list that will appear twice (have a "single duplicate"
	public static final int DOUBLE_DUPLICATE_NUMBERS = 5;  // minimum number of numbers on the list that will appear three times (have a "duplicate duplicate")
	
	
	public static void main(String[] args) {
		
		// set up an Integer list that is guaranteed to have some single and double duplicates
		List<Integer> numbers = new ArrayList<>();
		Random generator = new Random();
		int max = 5 * LIST_SIZE;
		int min = -1 * max;
		int randomRange = max - min + 1;
		
		// add the first round of numbers
		int originalNumbersToAdd = LIST_SIZE- SINGLE_DUPLICATE_NUMBERS - DOUBLE_DUPLICATE_NUMBERS;
		numbers.add(min); // adding the min and max number to help test for array out of bounds errors
		numbers.add(min);
		numbers.add(max);
		numbers.add(max);
		numbers.add(0);  // adding 0 as a special test case
		numbers.add(0); 
		originalNumbersToAdd-=6;
		for(int i=0; i<originalNumbersToAdd; i++) {
			numbers.add(generator.nextInt(randomRange) + min);			
		}
		
		// add duplicate numbers
		Collections.shuffle(numbers);
		for(int i=0; i<SINGLE_DUPLICATE_NUMBERS; i++) {
			numbers.add(numbers.get(i));
		}
		for(int i=0; i<DOUBLE_DUPLICATE_NUMBERS; i++) {
			numbers.add(numbers.get(i));
		}
		
		// print the list sorted (might help with testing)
		Collections.sort(numbers);
		System.out.println("The original list:");
		System.out.println(numbers);
		Collections.shuffle(numbers);
		
		// the results should be the same for both methods
		System.out.println("\nThe duplicate lists from both methods- these should match!");
		List<Integer> duplicateList = findDuplicatesBad(numbers);
		Collections.sort(duplicateList);
		System.out.println(duplicateList);
		
		duplicateList = findDuplicatesLinear(numbers);
		Collections.sort(duplicateList);
		System.out.println(duplicateList);


	}
	
	public static List<Integer> findDuplicatesBad(List<Integer> numbers) {
		List<Integer> duplicateList = new ArrayList<Integer>();
		for(int i=0; i<numbers.size(); i++) { // O(n)
			int numberEvaluating = numbers.get(i); // O(1)
			boolean duplicateFound = false;
			
			for(int j=i+1; j<numbers.size() && !duplicateFound; j++) { //O(n)
				int numberChecking = numbers.get(j); // O(1)
				
				if(numberEvaluating==numberChecking && !duplicateList.contains(numberEvaluating)) { // O(n)
					duplicateFound = true; 
					
					for(int k=j; k<numbers.size(); k++) {
						if(numberChecking==Integer.valueOf(numbers.get(k))) {
							duplicateList.add(numberChecking);
						}
					}
				}
			}
		}
		return duplicateList;
	}
	
	
	public static List<Integer> findDuplicatesLinear(List<Integer> numbers) {
		// YOUR CODE HERE
		List<Integer> duplicateList = new ArrayList<Integer>();
		// create a array with all the possible numbers in the list -5n to 5n ,
		// correspond to index from 0 to 10n +1
		int[] numbersArray = new int[10* numbers.size()+1];

		for (int i = 0; i < numbers.size(); i++){
			int numberChecking = numbers.get(i); // O(1)
			// convert the numbers into the index in numbersArray()
			// for example, numbersChecking = -5n correspond to numbersArray[0];
			// numbersChecking = 0 correspond to numbersArray[5n];
			// numbersChecking = 5n correspond to numbersArray[10n];
			int arrayIndex = 5 * numbers.size() + numberChecking;
			if (numbersArray[arrayIndex] < 0) { // already existed before
				duplicateList.add(numberChecking); // O(1)
			} else {
				numbersArray[arrayIndex] = -1; // first occurrence, replace with a negative number
			}

		}
		return duplicateList;
	}
}
