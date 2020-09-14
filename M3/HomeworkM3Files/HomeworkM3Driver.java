import java.util.*;

public class HomeworkM3Driver {

	public static void main(String[] args) {
		
		System.out.println("******************************TESTING QUESTION: COMPARABLE");
		Person p1 = new Person("Anne", "Teak", 123);			Person p2 = new Person("Trina", "Woods", 234);
		Person p3 = new Person("Trina", "Forest", 345);			Person p4 = new Person("Holly", "Wood", 456);
		Person p5 = new Person("Holly", "McRel", 567);			Person p6 = new Person("anne", "teak", 678);
		Person p7 = new Person("Holly", "Wood", 789);			Person[] people =  {p1, p2, p3, p4, p5, p6, p7};
		System.out.println("Before sorting, array order is:");
		System.out.println(Arrays.toString(people));
		Arrays.sort(people);
		System.out.println("\nAfter sorting, the array order should match this:\n[Trina Forest (345), Holly McRel (567), Anne Teak (123), anne teak (678), Holly Wood (456), Holly Wood (789), Trina Woods (234)]");
		System.out.println(Arrays.toString(people));
		
		
		System.out.println("\n\n******************************TESTING QUESTION: TESTING IF BAG IS A SET");
		BagInterface<String> wordBag = new ArrayBag<>();
		testBagIsSet(wordBag, true, "empty bag");
		
		wordBag.add("hi");
		testBagIsSet(wordBag, true, "singleton bag");

		wordBag.add("HI");			wordBag.add("hello");
		testBagIsSet(wordBag, true, "bag with no duplicates");
		
		wordBag.add("hi");
		testBagIsSet(wordBag, false, "bag with duplicates added first and last");
		
		wordBag.clear();
		wordBag.add("a");			wordBag.add("b");		wordBag.add("d");
		wordBag.add("e");			wordBag.add("c");		wordBag.add("c");
		testBagIsSet(wordBag, false, "bag with duplicates both added last");
	
		BagInterface<String> letterBag = new LinkedBag<String>();
		letterBag.add("a");			letterBag.add("c");		letterBag.add("b");
		letterBag.add("d");			letterBag.add("a");
		testBagIsSet(letterBag, false, "bag with duplicates added first and last");
		
		letterBag.clear();		letterBag.add("a");		letterBag.add("b");
		letterBag.add("b");		letterBag.add("c");
		testBagIsSet(letterBag, false, "bag with duplicates added in the middle");
		
		letterBag.clear();
		letterBag.add("a");		letterBag.add("a");		letterBag.add("b");
		letterBag.add("d");		letterBag.add("e");		letterBag.add("f");
		testBagIsSet(letterBag, false, "bag with duplicates added first");
		
		
		System.out.println("\n\n******************************TESTING QUESTION: CREATING LIST OF WORDS THAT CONTAIN CHAR");
		ListInterface<String> wordList = new AList<>();
		ListInterface<String> correctAnswerList = new AList<>();
		testCreateList(wordList, 'a', correctAnswerList, "empty list");
	
		wordList.add("apple"); 	correctAnswerList.add("apple");
		wordList.add("anna"); 	correctAnswerList.add("anna");
		wordList.add("bob"); 
		wordList.add("ANIMAL");
		wordList.add("canal"); 	correctAnswerList.add("canal");
		wordList.add("frog");
		wordList.add("cat"); 	correctAnswerList.add("cat");
		testCreateList(wordList, 'a', correctAnswerList, "list with matches at beginning, middle, and end");


		System.out.println("\n\n******************************TESTING QUESTION: FINDING LAST POSITION");
		List<String> itemList = new ArrayList<>();
		System.out.println("The index should indicate the target is not in the list: " + lastPosition(itemList, "frank"));
		itemList.add("adam");			itemList.add("betty");			itemList.add("sue");
		System.out.println("The index should indicate the target is not in the list: " + lastPosition(itemList, "frank"));
		
		itemList.add("frank");			itemList.add("sam");
		testLastPosition(itemList, "frank", "target on list one time in the middle of the list");
		
		itemList.add("frank");			itemList.add("sue");			itemList.add("terry");
		itemList.add("adam");			itemList.add("frank");
		testLastPosition(itemList, new String("frank"), "target on list multiple times with last time being in last position");
		
		itemList.add("adam");
		testLastPosition(itemList, "frank", "target on list multiple times with last time being NOT in last position");
		
		itemList.add(0, "mark");
		testLastPosition(itemList, "mark", "target on list once in the first position");


		System.out.println("\n\n******************************TESTING QUESTION: LIST COMPARISON");
		ListInterface<Integer> listInterfaceA = new AList<Integer>();
		List<Integer> listB = new ArrayList<Integer>();
		
		testEquivalentLists(listInterfaceA, listB, true, "two empty lists");
 
		listB.add(1); listB.add(3); listB.add(1); listB.add(5); listB.add(7);
		testEquivalentLists(listInterfaceA, listB, false, "A is empty, B is not");

		listInterfaceA.add(1);		
		testEquivalentLists(listInterfaceA, listB, false, "only the first match");

		listInterfaceA.add(3); listInterfaceA.add(1); listInterfaceA.add(5);
		testEquivalentLists(listInterfaceA, listB, false, "A has one fewer elements");
		
		listInterfaceA.add(7);
		testEquivalentLists(listInterfaceA, listB, true, "equivalent lists");
		
		listInterfaceA.add(8);
		testEquivalentLists(listInterfaceA, listB, false, "A has one more element");
		
		listB.add(8); listB.add(10);
		testEquivalentLists(listInterfaceA, listB, false, "B has one more element");
		
		listInterfaceA.clear(); listInterfaceA.add(1); listInterfaceA.add(2); listInterfaceA.add(3); 
		listB.clear(); listB.add(4); listB.add(2); listB.add(3);
		testEquivalentLists(listInterfaceA, listB, false, "A and B have only first elements different");	

		listInterfaceA.clear(); listInterfaceA.add(1); listInterfaceA.add(2); listInterfaceA.add(3); 
		listB.clear(); listB.add(1); listB.add(6); listB.add(7);
		testEquivalentLists(listInterfaceA, listB, false, "A and B have only first elements the same");	
		
		listInterfaceA.clear(); listInterfaceA.add(1); listInterfaceA.add(2); listInterfaceA.add(3); listInterfaceA.add(4);
		listB.clear(); listB.add(1); listB.add(3); listB.add(2); listB.add(4);
		testEquivalentLists(listInterfaceA, listB, false, "A and B have same elements but in a different order");	
 
		listInterfaceA.clear(); listInterfaceA.add(2); listInterfaceA.add(3); listInterfaceA.add(5); listInterfaceA.add(4);
		listB.clear(); listB.add(1); listB.add(6); listB.add(8); listB.add(5);		
		testEquivalentLists(listInterfaceA, listB, false, "A and B have same last elements ");
		
		listB.clear();
		testEquivalentLists(listInterfaceA, listB, false, "B is empty, A is not");

		
		System.out.println("\n\n******************************TESTING EXTRA CREDIT QUESTION: MOVE THE MAXIMUM TO THE FRONT");
		List<Integer> numberList = new ArrayList<Integer>();
		
		testPrioritizeMax(numberList, new Integer[]{}, "empty list");

		numberList.add(4);
		testPrioritizeMax(numberList, new Integer[]{4}, "singleton list");

		numberList.add(3);		numberList.add(2);
		numberList.add(5);		numberList.add(7);
		testPrioritizeMax(numberList, new Integer[]{7, 4, 3, 2, 5}, "maximum in the middle of the list");

		numberList.add(9);
		testPrioritizeMax(numberList, new Integer[]{9, 7, 4, 3, 2, 5}, "maximum at the end of the list");

		numberList.add(0, 12);
		testPrioritizeMax(numberList, new Integer[]{12, 9, 7, 4, 3, 2, 5}, "maximum at the beginning of the list");
	}
	
	public static boolean isSet(BagInterface<String> wordBag) {
		// YOUR CODE HERE
		return false;
	}
	
	public static ListInterface<String> createListContainingChar(ListInterface<String> wordList, char key) {
		// YOUR CODE HERE
		return new AList<String>();
	}
	
	public static int lastPosition(List<String> wordList, String targetWord) {
		// YOUR CODE HERE
		return 0;
	}
	
	public static boolean equivalentLists(ListInterface<Integer> numberListInterface, List<Integer> numberList) {
		// YOUR CODE HERE
		return false;
	}
	
	public static void prioritizeMaximumValue(List<Integer> numberList) {
		// YOUR CODE HERE		
	}
	
	
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 */
	public static void testBagIsSet(BagInterface<String> wordBag, boolean expectedResult, String testDescription) {
		Object[] beforeBagArray = wordBag.toArray();
		Arrays.sort(beforeBagArray);
		
		boolean actualResult = isSet(wordBag);
		
		Object[] afterBagArray = wordBag.toArray();
		Arrays.sort(afterBagArray);
		
		System.out.println("Set? " + actualResult + "\t" + Arrays.toString(beforeBagArray));
		
		if(actualResult!=expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Method did not return expected result. Expected result was:" + expectedResult);
			System.out.println("\t   Original bag contents: " + Arrays.toString(wordBag.toArray()));
		}
		if(!Arrays.equals(beforeBagArray, afterBagArray)) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: The bag contents were changed.");
			System.out.println("\t   Bag contents before isSet invoked: " + Arrays.toString(beforeBagArray));
			System.out.println("\t   Bag contents after isSet invoked: " + Arrays.toString(afterBagArray));

		}
	}
	public static void testCreateList(ListInterface<String> wordList, char c, ListInterface<String> answerList, String testDescription) {
		Object[] beforeListArray = wordList.toArray();
		Object[] answerListArray = answerList.toArray();

		ListInterface<String> resultList = createListContainingChar(wordList, c);
		
		Object[] resultListArray = resultList.toArray();
		
		System.out.println("List containing \'" + c + "\' is: " + Arrays.toString(resultListArray));
		
		if(!Arrays.equals(answerListArray,  resultListArray)) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Method did not return correct list.");
			System.out.println("\t   Correct list to create: " + Arrays.toString(answerListArray));
			System.out.println("\t   Returned list created:  "  + Arrays.toString(resultListArray));
		}
		if(!Arrays.equals(beforeListArray, wordList.toArray())) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: The parameter list was changed.");
			System.out.println("\t   List contents before method invoked: " + Arrays.toString(beforeListArray));
			System.out.println("\t   List contents after method invoked: " + Arrays.toString(wordList.toArray()));
		}
	}

	public static void testLastPosition(List<String> wordList, String target, String testDescription) {
		ArrayList<String> list = new ArrayList<>(wordList);
		int resultPosition = lastPosition(wordList, target);
		int correctPosition = list.lastIndexOf(target);
			
		System.out.println("Last position=" + resultPosition + "   word=" + target + " \tlist: " + wordList);
		
		if(resultPosition!=correctPosition) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Incorrect position. Expected position=" + correctPosition + " Returned position=" + resultPosition);
		}
	}
	
	public static void testEquivalentLists(ListInterface<Integer> listInterface, List<Integer> list, boolean expectedResult, String testDescription) {
		boolean result = equivalentLists(listInterface, list);
			
		System.out.println("Equivalent=" + result + " \tList: " + list + "\tListInterface: " + Arrays.toString(listInterface.toArray()));
		
		if(result!=expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected result=" + expectedResult + " Result=" + result);
		}
	}
	public static void testPrioritizeMax(List<Integer> list, Integer[] result, String testDescription) {
		List<Integer> originalList = new ArrayList<Integer>(list);
		prioritizeMaximumValue(list);
		List<Integer> resultList = new ArrayList<Integer>(Arrays.asList(result));
		
		System.out.println("Original list: " + originalList + " \tList with max prioritized: " + list);
		
		if(!resultList.equals(list)){
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected result list= " + resultList);
			System.out.println("\t   Result list=          " + list);

		}
	}

	
}
