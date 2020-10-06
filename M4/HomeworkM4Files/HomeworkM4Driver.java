import java.util.*;

public class HomeworkM4Driver {

	public static void main(String[] args) {
		
		System.out.println("************************************TESTING ARRAYBAG REMOVE ALL");
		testArrayBagRemoveAll(new Integer[] {}, 1, "removing from empty bag");
		testArrayBagRemoveAll(new Integer[] {1, 7, 2, 1, 3, 1, 1, 7, 1}, 1, "remove value appears multiple times");
		testArrayBagRemoveAll(new Integer[] {7, 2, 4, 3, 7}, 7, "remove value appears multiple times, first and last values added");
		testArrayBagRemoveAll(new Integer[] {2, 4, 3, 1, 5, 6}, 8, "remove value is not in the bag");
		testArrayBagRemoveAll(new Integer[] {3, 3, 3, 3}, 3, "remove value is the only value in the bag");
		testArrayBagRemoveAll(new String[] {"a", "b", "a"}, new String("a"), "test with Strings");

	
		System.out.println("\n\n************************************TESTING RESIZABLEARRAYBAG TRIM TO SIZE (MAKE SURE YOU'VE IMPLEMENTED getBagArrayLength METHOD");
		// NOTE: you need to implement getBagArrayLength() method in ResizableArrayBag in order for these tests to work correctly 		
		ResizableArrayBag<Integer> resizableBag = new ResizableArrayBag<Integer>(20);
		testTrimToSize(resizableBag, 0, 20, "empty bag created with size 20");

		for(int i=0; i<100; i++) {
			resizableBag.add(1);
		}
		testTrimToSize(resizableBag, 100, 160, "bag with 100 elements; initial array length of 20 doubled three times during the additions for a length of 160");
		
		resizableBag.trimToSize();
		testTrimToSize(resizableBag, 100, 100, "bag with 100 elements, trimmed to size");
	
		resizableBag.add(43);
		testTrimToSize(resizableBag, 101, 200, "bag with 101 elements; previous array length of 100 doubled during the addition of one item");
		
		resizableBag.trimToSize();
		testTrimToSize(resizableBag, 101, 101, "bag with 101 elements, trimmed to size");


		System.out.println("\n\n************************************TESTING ALIST REMOVE ALL");	
		testAListRemoveAll(new Integer[] {}, 1, "removing from empty list");
		testAListRemoveAll(new Integer[] {1, 2, 1, 1, 4, 3, 7, 1}, 1, "remove value appears multiple times");
		testAListRemoveAll(new Integer[] {7, 2, 4, 3, 7}, 7, "remove value appears multiple times, first and last values added");
		testAListRemoveAll(new Integer[] {7, 2, 4, 3, 7}, 8, "remove value is not in the list");
		testAListRemoveAll(new Integer[] {3, 3, 3, 3}, 3, "remove value is the only value in the list");
		testAListRemoveAll(new String[] {"a", "b", "a"}, new String("a"), "test with Strings");

	
		System.out.println("\n\n************************************TESTING ALIST EQUALS");		
		testEquivalentLists(new String[] {}, new String[] {}, true, "two empty lists");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {}, false, "empty parameter, non-empty invoking object");
		testEquivalentLists(new String[] {}, new String[] {"a", "b", "c", "d"}, false, "empty invoking object, non-empty parameter");
		testEquivalentLists(new String[] {"a", "b", "c"}, new String[] {"a", "b", "c", "d"}, false, "different lengths");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"a", "b", "c"}, false, "different lengths");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"a", "b", "c"}, false, "different lengths");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"a", "b", "c", "e"}, false, "same lengths, different elements");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"e", "b", "c", "d"}, false, "same lengths, different elements");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"b", "d", "a", "c"}, false, "same lengths, same elements, different order of elements");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"a", "b", "c", "d"}, true, "equivalent lists");
		testEquivalentLists(new String[] {"a", "b", "c", "d", "a"}, new String[] {"a", "b", "c", "d", new String("a")}, true, "equivalent lists");
		testEquivalentLists(new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3}, true, "equivalent lists");

		
		System.out.println("\n\n************************************TESTING EXTRA CREDIT ALIST COMPARABLE");	
		testComparable(new Integer[] {}, new Integer[] {}, ResultRange.ZERO, "List A and B are empty");
		testComparable(new Integer[] {1}, new Integer[] {}, ResultRange.POSITIVE, "List A is bigger (more elements)");
		testComparable(new Integer[] {}, new Integer[] {1}, ResultRange.NEGATIVE, "List A is smaller (fewer elements)");
		testComparable(new Integer[] {1}, new Integer[] {1, 2}, ResultRange.NEGATIVE, "List A is smaller (fewer elements)");
		testComparable(new Integer[] {1, 2}, new Integer[] {1, 2}, ResultRange.ZERO, "List A and List B are the same size and have the same elements");
		testComparable(new Integer[] {1, 2, 3}, new Integer[] {1, 2, 4}, ResultRange.NEGATIVE, "List A is smaller (lists are the same size; for the first non-matching element, the a list element is smaller)");
		testComparable(new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3}, ResultRange.ZERO, "List A and List B are the same size and have the same elements");
		testComparable(new Integer[] {1, 2, 3, 4}, new Integer[] {1, 2, 3, 2}, ResultRange.POSITIVE, "List A is bigger (lists are the same size; for the first non-matching element, the a list element is bigger)");
		testComparable(new String[] {"a", "b", "c"}, new String[] {"a", "d", "a"}, ResultRange.NEGATIVE, "List A is smaller (lists are the same size; for the first non-matching element, the a list element is smaller)");
	}
	
	
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */
	public static <T extends Comparable<? super T>> void testArrayBagRemoveAll(T[] valuesToAdd, T removeValue, String testDescription) {
		ArrayBag<T> bag = new ArrayBag<T>();
		ArrayBag<T> correctBag = new ArrayBag<T>();
		for(T value : valuesToAdd) {
			bag.add(value);
			if(!value.equals(removeValue)) {
				correctBag.add(value);
			}
		}
		int originalSize = bag.getCurrentSize();
		int numRemoved = bag.removeAll(removeValue);
		Object[] bagContentsAfter = bag.toArray();
		Object[] correctBagContentsAfter = correctBag.toArray();

		System.out.println("Size before=" + originalSize + "  Size after=" + bag.getCurrentSize()
			+ "   Number removed=" + numRemoved + "\tValues before: " + Arrays.toString(valuesToAdd) 
			+	"\tValue removed=" + removeValue);
		if(bag.getCurrentSize()!=correctBag.getCurrentSize()) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.print("\t   Expected size=" + correctBag.getCurrentSize());
			System.out.println("\t   Actual size=" + bag.getCurrentSize());
		}
		boolean match = true;
		while(!correctBag.isEmpty()) {
			boolean removed = bag.remove(correctBag.remove());
			if(!removed) {
				match = false;
			}
		}
		if(!bag.isEmpty()) {
			match = false;
		}
		if(!match) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected bag contents after=" + Arrays.toString(correctBagContentsAfter));
			System.out.println("\t   Actual bag contents after=" + Arrays.toString(bagContentsAfter));
		}
	}
	public static void testTrimToSize(ResizableArrayBag<Integer> bag, int expectedBagSize, int expectedArrayLength, String testDescription) {
		int actualBagSize = bag.getCurrentSize();
		int actualArrayLength = bag.getBagArrayLength();
		
		System.out.println("Resizable Bag: Size: expected=" + expectedBagSize + " actual=" + actualBagSize
				+ "        \tArray Length: expected=" + expectedArrayLength + " actual=" + actualArrayLength);
		if(actualBagSize!=expectedBagSize) {
			System.out.println("\t***Test failed with incorrect bag size for: " + testDescription);
		}
		
		if(actualArrayLength!=expectedArrayLength) {
			System.out.println("\t***Test failed with incorrect array length for: " + testDescription);
		}
	}
	public static  <T extends Comparable<? super T>> void testAListRemoveAll(T[] valuesToAdd, T removeValue, String testDescription) {
		AList<T> list = new AList<T>();
		AList<T> correctList = new AList<T>();
		for(T value : valuesToAdd) {
			list.add(value);
			if(!value.equals(removeValue)) {
				correctList.add(value);
			}
		}
		int originalSize = list.getLength();
		int numRemoved = list.removeAll(removeValue);

		System.out.println("Size before=" + originalSize + "  Size after=" + list.getLength()
			+ "   Number removed=" + numRemoved + "\tValues before: " + Arrays.toString(valuesToAdd) 
			+	"\tValue removed=" + removeValue);
		if(list.getLength()!=correctList.getLength()) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.print("\t   Expected size=" + correctList.getLength());
			System.out.println("\t   Actual size=" + list.getLength());
		}
		boolean match = true;
		if(correctList.getLength()!=list.getLength()) {
			match = false;
		} else {
			for(int i=1; i<=list.getLength() && match; i++) {
				if(!list.getEntry(i).equals(correctList.getEntry(i))) {
					match = false;
				}
			}
		}

		if(!match) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected list contents after=" + Arrays.toString(correctList.toArray()));
			System.out.println("\t   Actual list contents after=  " + Arrays.toString(list.toArray()));
		}
	}
	
	public static <T extends Comparable<? super T>> void testEquivalentLists(T[] listAContents, T[] listBContents, boolean expectedResult, String testDescription) {
		AList<T> listA = new AList<T>(100);
		for(T element : listAContents) {
			listA.add(element);
		}
		AList<T> listB = new AList<T>();
		for(T element : listBContents) {
			listB.add(element);
		}
		boolean result = listA.equals(listB);
			
		System.out.println("Equivalent=" + result + " \tListA: " + Arrays.toString(listA.toArray()) + "\tListB: " + Arrays.toString(listB.toArray()));
		
		if(result!=expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected result=" + expectedResult + " Result=" + result);
		}
	}
	public static enum ResultRange {
		POSITIVE("invoking list is greater"), NEGATIVE("invoking list is smaller"), ZERO("the two lists are \'equal\'");
		
		private String text;
		private ResultRange(String text) {
			this.text = text;
		}
		public String toString() {
			return this.text;
		}
	}
	public static <T extends Comparable<? super T>> void testComparable(T[] listAContents, T[] listBContents, ResultRange expectedResultRange, String testDescription) {
		AList<T> listA = new AList<T>(100);
		for(T element : listAContents) {
			listA.add(element);
		}
		AList<T> listB = new AList<T>();
		for(T element : listBContents) {
			listB.add(element);
		}
		int result = listA.compareTo(listB);
		ResultRange resultRange;
		if(result<0) {
			resultRange = ResultRange.NEGATIVE;
		} else if(result>0) {
			resultRange = ResultRange.POSITIVE;
		} else {
			resultRange = ResultRange.ZERO;
		}
			
		System.out.println("Result=" + resultRange + " \tListA: " + Arrays.toString(listA.toArray()) + "\tListB: " + Arrays.toString(listB.toArray()));
		
		if(resultRange!=expectedResultRange) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected result=" + expectedResultRange + " Result=" + resultRange);
		}
	}

}
