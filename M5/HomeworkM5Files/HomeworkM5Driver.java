import java.util.*;

public class HomeworkM5Driver {

	public static void main(String[] args) {

		System.out.println("\n*********************************TESTING QUESTION: PRINT AT INTERVAL");
		Node<String> firstNode = new Node<String>("a", new Node<String>("b", new Node<String>("c", new Node<String>("d"))));
		testPrintAtInterval(firstNode, 2, "a->b->c->d", "a c");
		testPrintAtInterval(firstNode, 3, "a->b->c->d", "a d");
		testPrintAtInterval(firstNode, 4, "a->b->c->d", "a");
		
		firstNode = new Node<String>("c", new Node<String>("d", new Node<String>("e", new Node<String>("f", new Node<String>("g")))));
		testPrintAtInterval(firstNode, 2, "c->d->e->f->g", "c e g");
		
		firstNode = new Node<String>("c", new Node<String>("d", new Node<String>("e", new Node<String>("f", new Node<String>("g")))));
		testPrintAtInterval(firstNode, 3, "c->d->e->f->g", "c f");

		firstNode = new Node<String>("a", new Node<String>("b", new Node<String>("c", new Node<String>("d", new Node<String>("e", new Node<String>("f", new Node<String>("g", new Node<String>("h"))))))));
		testPrintAtInterval(firstNode, 3, "a->b->c->d->e->f->g->h", "a d g");
		
		firstNode = new Node<String>("q", new Node<String>("r", new Node<String>("s", new Node<String>("t"))));
		testPrintAtInterval(firstNode, 5, "q->r->s->t", "q");
		testPrintAtInterval(firstNode, 1, "q->r->s->t", "q r s t");
	
		firstNode = null; 
		testPrintAtInterval(null, 5, "<empty chain>", "nothing (and not crash)");

		firstNode = new Node<String>("m"); 
		testPrintAtInterval(firstNode, 3, "m", "m");
		testPrintAtInterval(firstNode, 2, "m", "m");
	

		System.out.println("\n*********************************TESTING QUESTION: LINKED BAG REMOVE ALL");
		testRemoveAll(new Integer[] {}, 1, "empty bag");
		
		testRemoveAll(new Integer[] {1}, 1, "singleton bag with matching element");
		testRemoveAll(new Integer[] {2}, 1, "singleton bag without matching element");
		
		testRemoveAll(new Integer[] {2, 2, 2}, 2, "odd sized bag with all matches");
		testRemoveAll(new Integer[] {3, 3, 3, 3}, 3, "even sized bag with all matches");
		
		testRemoveAll(new Integer[] {3, 4, 5}, 5, "bag with one match added last");
		testRemoveAll(new Integer[] {2, 3, 4, 5}, 5, "bag with one match added last");
		testRemoveAll(new Integer[] {3, 4, 5}, 3, "bag with one match added first");
		testRemoveAll(new Integer[] {3, 4, 5}, 4, "bag with one match added in the middle");

		testRemoveAll(new Integer[] {1, 1, 2, 4, 1, 5, 1}, 1, "bag with some matches");
		testRemoveAll(new Integer[] {1, 1, 3, 1, 1, 4, 1, 5, 1, 1}, 1, "bag with some matches");
		
		testRemoveAll(new Integer[] {1, 1, 3, 1, 4, 1, 5, 1}, 6, "bag with no matches");
	
		testRemoveAll(new String[] {"a", "b", "c"}, new String("a"), "bag with Strings");
		
	
		System.out.println("\n*********************************TESTING QUESTION: LLIST GET MAX");
		testGetMax(new Integer[]{1, 2, 1, 4, 3}, "max in the middle");
		testGetMax(new Integer[]{1, 2, 1, 4, 3, 1}, "max in the middle");		
		testGetMax(new Integer[]{1, 1, 4, 3, 1, 4}, "duplicate max");
		testGetMax(new Integer[]{1, 4, 3, 1, 4, 6}, "max at the end");
		testGetMax(new Integer[]{9, 1, 2, 1, 4, 3}, "max at the beginning");
		testGetMax(new Integer[]{7}, "singleton");
		testGetMax(new Integer[]{}, "empty list");
		testGetMax(new String[]{"a", "m", "z"}, "list with Strings (earlier letters are considered smaller)");
		
			
		System.out.println("\n*********************************TESTING QUESTION: LLIST ADD ALL");
		System.out.println("\t*******************Note: this tester code does not examine the efficiency of your result!");
		testAddAll(new String[]{}, new String[]{"peach"}, "add a 1-element array to an empty list");	
		testAddAll(new String[]{}, new String[]{"banana", "date", "grape", "eggplant",	"jicama", "grape"}, "add multiple elements to an empty list");	
		testAddAll(new String[]{"apple", "peach"}, new String[]{"banana", "date", "grape", "eggplant",	"jicama", "grape" }, "add an array to a non-empty list");
		testAddAll(new String[]{"peach"}, new String[]{}, "add an empty array to a non-empty list");
		testAddAll(new String[]{}, new String[]{}, "add an empty array to an empty list");
		testAddAll(new Integer[]{1, 2, 3}, new Integer[]{4, 5, 6}, "test with Integers");

		
		System.out.println("\n*********************************TESTING QUESTION EXTRA CREDIT: DOUBLE NODE PALINDROME");
		testPalindrome(new Integer[] {4, 3, 4, 3, 4}, true, "odd length palindrome");
		testPalindrome(new Integer[] {1, 2, 2, 1}, true, "even length palindrome");
		testPalindrome(new Integer[] {5, 6}, false, "even length non-palindrome");
		testPalindrome(new Integer[] {1, 3, 2, 1}, false, "even length non-palindrome");
		testPalindrome(new Integer[] {3, 4, 3, 4}, false, "even length non-palindrome");
		testPalindrome(new Integer[] {4, 3, 3, 2, 4}, false, "odd length non-palindrome");
		testPalindrome(new Integer[] {4, 2, 1, 2, 3}, false, "odd length non-palindrome");
		testPalindrome(new Integer[] {}, true, "empty palindrome");
		testPalindrome(new Integer[] {5}, true, "singleton palindrome");
	}
	

	public static void printAtInterval(Node<String> firstNode, int interval) {
		// YOUR CODE HERE
		Node current = firstNode;
		if (current == null) {
			System.out.print("");
		} else {
			String currentData = firstNode.data;
			while (current != null){
				System.out.print(current.data + " ");
				for (int i = 0; i<interval-1; i++){
					current = current.next;
					if (current == null){
						return;
					}
				}
				current = current.next;
			}
		}
	}
	

	
	public static boolean isDoublyLinkedPalindromeList(DoubleNode<Integer> firstNode, DoubleNode<Integer> lastNode)  {
		// YOUR EXTRA CREDIT CODE HERE
		DoubleNode leftNode = firstNode;
		DoubleNode rightNode = lastNode;

		while (leftNode != rightNode){
			if (!leftNode.data.equals(rightNode.data)) {
				return false;
			}
			leftNode = leftNode.next;
			rightNode = rightNode.previous;
		}
		return true;
	}

	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */
	public static void testPrintAtInterval(Node<String> firstNode, int interval, String chain, String expectedOutput) {
		System.out.println("Chain: " + chain + " at interval " + interval + " should print:\n" + expectedOutput);
		printAtInterval(firstNode, interval); 
		System.out.println();
	}
	public static <T extends Comparable<? super T>> void testRemoveAll(T[] valuesToAdd, T valueToRemove, String testDescription) {
		LinkedBag<T> originalBag = new LinkedBag<T>();
		LinkedBag<T> resultsBag = new LinkedBag<T>();
		for(T valueToAdd : valuesToAdd) {
			originalBag.add(valueToAdd);
			if(!valueToAdd.equals(valueToRemove)) {
				resultsBag.add(valueToAdd);
			}
		}
		int beforeRemoveSize = originalBag.getCurrentSize();
		int expectedAfterSize = resultsBag.getCurrentSize();
		
		int expectedNumberRemoved = beforeRemoveSize - expectedAfterSize ;	
		int actualNumberRemoved = originalBag.removeAll(valueToRemove);

		Arrays.sort(valuesToAdd);

		T[] actualAfterArray = originalBag.toArray();
		Arrays.sort(actualAfterArray);
		T[] expectedAfterArray = resultsBag.toArray();
		Arrays.sort(expectedAfterArray);
	
		System.out.println("Removing " + valueToRemove + " from " + Arrays.toString(valuesToAdd) + "\tRemaining elements: " + Arrays.toString(actualAfterArray)+ "\tNumber removed=" + actualNumberRemoved );

		if(expectedNumberRemoved!=actualNumberRemoved) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected number removed=" + expectedNumberRemoved + " Actual number removed=" + actualNumberRemoved);
		}	
		if(originalBag.getCurrentSize()!=expectedAfterSize) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected after size=" + expectedAfterSize + " Actual after size=" + originalBag.getCurrentSize());
		}		
		if(!Arrays.equals(actualAfterArray,  expectedAfterArray)) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected after contents=" + Arrays.toString(actualAfterArray) + " Actual after contents=" + Arrays.toString(expectedAfterArray));
		}
	}
	public static <T extends Comparable<? super T>> void testGetMax(T[] valuesToAdd, String testDescription) {
		LList<T> list = new LList<T>();
		ArrayList<T> arrayList = new ArrayList<T>();
		for(T valueToAdd : valuesToAdd) {
			list.add(valueToAdd);
			arrayList.add(valueToAdd);
		}
		if(list.isEmpty()) {
			try {
				T actualMax = list.getMax();
				System.out.println("Max of " + Arrays.toString(valuesToAdd) + " = " + actualMax);
				if(actualMax!=null) {
					System.out.println("\t***Test failed for: " + testDescription);
					System.out.println("\t   Error: Expected max=" + null + " Actual max=" + actualMax);
				}
			} catch(Exception ex) { 
				System.out.println("Max of " + Arrays.toString(valuesToAdd) + " throws an exception of type " + ex.getClass());
				if(ex.getClass().equals(NullPointerException.class)) {
					System.out.println("\t***Test failed for: " + testDescription);
					System.out.println("\t   Error: exception type should not be NullPointerException.");		
				}
			}
		} else {
			T expectedMax = Collections.max(arrayList);
			T actualMax = list.getMax();

			System.out.println("Max of " + Arrays.toString(valuesToAdd) + " = " + actualMax);

			if (!expectedMax.equals(actualMax)) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Error: Expected max=" + expectedMax + " Actual max=" + actualMax);
			}
			if (!Arrays.equals(list.toArray(), arrayList.toArray())) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Error: Expected contents after finding max: " + arrayList
						+ "Actual contents after finding max:" + Arrays.toString(list.toArray()));
			}
		}
	}
	public static <T extends Comparable<? super T>> void testAddAll(T[] initialListContents, T[] arrayToAdd, String testDescription) {
		LList<T> originalList = new LList<T>();
		LList<T> resultList = new LList<T>();
		for(T valueToAdd : initialListContents) {
			originalList.add(valueToAdd);
			resultList.add(valueToAdd);
		}
		for(T valueToAdd : arrayToAdd) {
			resultList.add(valueToAdd);
		}
		originalList.addAll(arrayToAdd);
		
		System.out.println("Adding " + Arrays.toString(arrayToAdd) + " to the end of \t" + Arrays.toString(initialListContents) + " which results in \t" + Arrays.toString(originalList.toArray()));
		
		if(!originalList.equals(resultList)) { 
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected contents after adding: " + Arrays.toString(resultList.toArray())
					+ " Actual contents after adding:" + Arrays.toString(originalList.toArray()));
		}	
	}
	public static void testPalindrome(Integer[] chainContents, boolean expectedResult, String testDescription) {
		ArrayList<DoubleNode<Integer>>nodeList = new ArrayList<DoubleNode<Integer>>();
		for(Integer valueToAdd : chainContents) {
			nodeList.add(new DoubleNode<Integer>(valueToAdd));
		}
		Collections.reverse(nodeList);
		DoubleNode<Integer> firstNode = null;
		DoubleNode<Integer> lastNode = null;
		if(!nodeList.isEmpty()) {
			firstNode = nodeList.get(0);
			lastNode = nodeList.get(nodeList.size()-1);
			if(nodeList.size()>1) {
				for (int i=0; i<nodeList.size(); i++) {
					if (i==0 ) {
						nodeList.get(i).next = nodeList.get(i+1);
					} else if (i==nodeList.size()-1) {
						nodeList.get(i).previous = nodeList.get(i-1);
					} else {
						nodeList.get(i).next = nodeList.get(i+1);
						nodeList.get(i).previous = nodeList.get(i-1);
					}
				}
			}
		}

		boolean actualResult = isDoublyLinkedPalindromeList(firstNode, lastNode);
		System.out.println("Chain containing " + Arrays.toString(chainContents) + " is palindrome? " + actualResult);
		if (actualResult!=expectedResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Error: Expected result=" + expectedResult + " Actual result=" + actualResult);
		}
	}
}
