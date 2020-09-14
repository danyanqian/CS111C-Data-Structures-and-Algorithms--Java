import java.util.*;

/*
 * NOTES ON USING THIS TESTER
 * 
 * This tester file automates some tests to help you confirm that your code works and track down errors.
 * Note that some tests cannot be tested automatically, but rely on you reviewing the results. For example,
 * when testing toString output, you must visually confirm the results.
 * 
 * You can ignore the use of generics in the tester methods (after the main method). These methods use
 * generics at a level beyond which we use in our class. I only use them here to make this a robust and
 * useful testing file. You are NOT required to understand the use of generics in this way.
 * 
 */

public class TrioTester {

	private static boolean passGetterSetter = true, passHasDuplicates = true, passCount = true, passEquals = true;
	
	public static void main(String[] args) {

		// ************TESTING GENERIC TYPE************
		Trio<Integer> numberTrio = new Trio<Integer>(3, 4, 5);
		/* un-comment the line of code below and it should cause a compiler error because trio1 should only accept Strings  */
		//numberTrio.setItem2("apple");
		
		Trio<String> wordTrio = new Trio<String>("hello");
		wordTrio.setItem2("goodbye");
		wordTrio.setItem3("farewell");
		/* un-comment the line of code below and it should cause a compiler error because wordTrio should only accept Strings  */
		//wordTrio.setItem2(3);

		
		System.out.println("************TESTING TOSTRING METHOD************");
		System.out.println("Code should print a text representation of the Trio that contains(3, 4, 5):");
		System.out.println(numberTrio);
		System.out.println("Code should print a text representation of the Trio that contains (hello, goodbye, farewell):");
		System.out.println(wordTrio);
		
		
		System.out.println("\n************TESTING GETTERS AND SETTERS METHOD************");
		testGetterSetter(numberTrio, 1, 3); // testing getItem1 method
		testGetterSetter(numberTrio, 2, 4); // testing getItem2 method
		testGetterSetter(numberTrio, 3, 5); // testing getItem3 method
		numberTrio.setItem1(6);
		numberTrio.setItem2(7);
		numberTrio.setItem3(8);
		testGetterSetter(numberTrio, 1, 6); // testing getItem1 method
		testGetterSetter(numberTrio, 2, 7); // testing getItem2 method
		testGetterSetter(numberTrio, 3, 8); // testing getItem3 method
		
		System.out.println("***Getter/Setter tests: " + (passGetterSetter ? "Passed" : "AT LEAST ONE TEST FAILED"));

			
		System.out.println("\n************TESTING HASDUPLICATES METHOD************");		
		numberTrio = new Trio<Integer>(3, 4, 5);
		testHasDuplicates(numberTrio, false);
		numberTrio = new Trio<Integer>(3, 3, 5);
		testHasDuplicates(numberTrio, true);
		numberTrio = new Trio<Integer>(3, 4, 4);
		testHasDuplicates(numberTrio, true);
		numberTrio = new Trio<Integer>(3, 4, 3);
		testHasDuplicates(numberTrio, true);
		numberTrio = new Trio<Integer>(4, 4, 4);
		testHasDuplicates(numberTrio, true);
		wordTrio = new Trio<String>(new String("a"), new String("a"), new String("a"));
		testHasDuplicates(wordTrio, true);

		System.out.println("***hasDuplicates tests: " + (passHasDuplicates ? "Passed" : "AT LEAST ONE TEST FAILED"));

		
		System.out.println("\n************TESTING COUNT METHOD************");
		numberTrio = new Trio<Integer>(3, 4, 5);
		testCount(numberTrio, 7, 0);
		numberTrio = new Trio<Integer>(7, 4, 5);
		testCount(numberTrio, 7, 1);
		numberTrio = new Trio<Integer>(3, 7, 5);
		testCount(numberTrio, 7, 1);
		numberTrio = new Trio<Integer>(3, 4, 7);
		testCount(numberTrio, 7, 1);
		numberTrio = new Trio<Integer>(7, 7, 2);
		testCount(numberTrio, 7, 2);
		numberTrio = new Trio<Integer>(7, 2, 7);
		testCount(numberTrio, 7, 2);
		numberTrio = new Trio<Integer>(2, 7, 7);
		testCount(numberTrio, 7, 2);
		numberTrio = new Trio<Integer>(7, 7, 7);
		testCount(numberTrio, 7, 3);
		wordTrio = new Trio<String>(new String("a"), new String("a"), new String("a"));
		testCount(wordTrio, "a", 3);
		testCount(wordTrio, new String("a"), 3);
		testCount(wordTrio, "A", 0);

		System.out.println("***count tests: " + (passCount ? "Passed" : "AT LEAST ONE TEST FAILED"));
		
		
		System.out.println("\n************TESTING REPLACE ALL METHOD************");
		numberTrio.replaceAll(43);
		System.out.println("Code should print a text representation of the Trio that contains(43, 43, 43):");
		System.out.println(numberTrio);
		testCount(numberTrio, 43, 3);
		testHasDuplicates(numberTrio, true);
			
		
		System.out.println("\n************TESTING EQUALS METHOD************");	
		Trio<String> wordTrio2 = new Trio<String>(new String("aa"), new String("bb"), new String("cc"));
		Trio<String> wordTrio3 = new Trio<String>(new String("aa"), new String("bb"), new String("cc"));
		testEquals(wordTrio2, wordTrio3, true, "no duplicates, same items in same order");

		Trio<Character> charTrio1 = new Trio<Character>('a', 'b', 'c');
		Trio<Character> charTrio2 = new Trio<Character>('b', 'c', 'a');
		testEquals(charTrio1, charTrio2, true, "no duplicates, same items in different order");
		
		Trio<Integer> numberTrio2 = new Trio<Integer>(5, 6, 8);
		Trio<Integer> numberTrio3 = new Trio<Integer>(8, 5, 6);
		testEquals(numberTrio2, numberTrio3, true, "no duplicates, same items in different order");
		
		numberTrio2 = new Trio<Integer>(1, 2, 1);
		numberTrio3 = new Trio<Integer>(1, 1, 2);
		testEquals(numberTrio2, numberTrio3, true, "duplicates, same items in different order");
		
		numberTrio2 = new Trio<Integer>(2, 1, 1);
		numberTrio3 = new Trio<Integer>(1, 1, 2);
		testEquals(numberTrio2, numberTrio3, true, "duplicates, same items in different order");
		
		numberTrio2 = new Trio<Integer>(5, 5, 8);
		numberTrio3 = new Trio<Integer>(8, 5, 6);
		testEquals(numberTrio2, numberTrio3, false, "different items");
		
		numberTrio2 = new Trio<Integer>(1, 1, 2);
		numberTrio3 = new Trio<Integer>(1, 2, 2);
		testEquals(numberTrio2, numberTrio3, false, "same items, but different items are duplicated");
		
		numberTrio2 = new Trio<Integer>(2, 1, 2);
		numberTrio3 = new Trio<Integer>(1, 2, 1);
		testEquals(numberTrio2, numberTrio3, false, "same items, but different items are duplicated");
		
		testEquals(numberTrio2, wordTrio, false, "different items and types");
		
		numberTrio2 = new Trio<Integer>(1, 2, 3);
		numberTrio3 = new Trio<Integer>(3, 2, 1);
		testEquals(numberTrio2, numberTrio3, true, "no duplicates, same items in different order");
		System.out.println("\nCheck of the equals method not modifying the objects");
		System.out.println("Code should print a text representation of the Trio that contains(1, 2, 3) (in that order):");
		System.out.println(numberTrio2);
		System.out.println("Code should print a text representation of the Trio that contains(3, 2, 1) (in that order):");
		System.out.println(numberTrio3);
		
		boolean result = numberTrio.equals(4); 
		if(result==true) {
			passEquals = false;
		}
		System.out.println("\nCheck of the equals method with a non-Trio object passed in: result should be false:" + result);

		List<Trio<Integer>> trioList = new ArrayList<>();
		trioList.add(new Trio<Integer>(4));
		result = trioList.contains(new Trio<Integer>(4));
		if(result==false) {
			passEquals = false;
		}
		System.out.println("\nCheck of the equals method used in methods such as contains: result should be true:" + result);
		
		System.out.println("***equals tests: " + (passEquals ? "Passed" : "AT LEAST ONE TEST FAILED"));

		if(passGetterSetter && passHasDuplicates && passCount && passEquals) {
			System.out.println("\n\n******Summary: All automated tests have passed. Be sure to manually check the output for the results of other tests.");
		} else {
			System.out.println("\n\n******Summary: ERROR: There is failure in at least one automated test. Review the output above for details.");
		}
	}
	
	public static <T> void testGetterSetter(Trio<T> trio, int position, T expectedValue) {
		T actualResult;
		if(position==1) {
			actualResult = trio.getItem1();
		} else if(position==2) {
			actualResult = trio.getItem2();
		} else { // position==3 
			actualResult = trio.getItem3();
		}
		String failure = "";
		if(!expectedValue.equals(actualResult)) {
			passGetterSetter = false;
			failure = "\tTEST FAILED";
		}
		System.out.println("Getting item at" + position + "\texpected=" + expectedValue + "\tactual=" + actualResult + "  " + failure);	
	}
	
	public static <T> void testHasDuplicates(Trio<T> trio, boolean expectedResult) {
		boolean actualResult = trio.hasDuplicates();
		String failure = "";
		if(expectedResult!=actualResult) {
			passHasDuplicates = false;
			failure = "\tTEST FAILED";
		}
		String trioDescription = "(" + trio.getItem1() + ", " + trio.getItem2() + ", " + trio.getItem3() + ")";
		System.out.println("Trio " + trioDescription + " has duplicates? \texpected=" + expectedResult + "\tactual=" + actualResult + "  " + failure);	
	}
	public static <T> void testCount(Trio<T> trio, T value, int expectedCount) {
		int actualCount = trio.count(value);
		String failure = "";
		if(expectedCount!=actualCount) {
			passCount = false;
			failure = "\tTEST FAILED";
		}
		String trioDescription = "(" + trio.getItem1() + ", " + trio.getItem2() + ", " + trio.getItem3() + ")";
		System.out.println("Trio " + trioDescription + "\tCount of " + value + "\texpected=" + expectedCount + "\tactual=" + actualCount + "  " + failure);	
	}
	
	public static <T, S> void testEquals(Trio<T> trio1, Trio<S> trio2, boolean expectedResult, String message) {
		boolean actualResult = trio1.equals(trio2);
		String failure = "";
		if(expectedResult!=actualResult) {
			passEquals = false;
			failure = "\tTEST FAILED\tTest: " + message;
		}
		String trio1Description = "(" + trio1.getItem1() + ", " + trio1.getItem2() + ", " + trio1.getItem3() + ")";
		String trio2Description = "(" + trio2.getItem1() + ", " + trio2.getItem2() + ", " + trio2.getItem3() + ")";
		System.out.println("Trio 1 " + trio1Description + "\tTrio 2 " + trio2Description + "\tequal?\texpected=" + expectedResult + "\tactual=" + actualResult + "  " + failure);		
	}
	


}
