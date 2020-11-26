import java.util.*;

public class HomeworkM10Driver {

	public static void main(String[] args) {

		System.out.println("\n******************************TESTING PRIORITY PUSH******************************");
		// first parameter is the contents that will be pushed onto the stack- contents are pushed from start to finish
		//    (so the element at array index 0 will be on the bottom of the stack)
		// second parameter is the element to priority push
		// third parameter is the expected return value of the priority push method
		// fourth parameter is the expected contents of the stack after the priority push (listed bottom to top)
		testPriorityPush(new Integer[]{2, 4, 5, 8, 10},    12, false,   new Integer[]{2, 4, 5, 8, 10, 12},     "push an element not in the stack");
		testPriorityPush(new Integer[]{1, 5, 7, 8, 9, 10}, 1, true,     new Integer[]{5, 7, 8, 9, 10, 1},      "push an element that is on the bottom of the stack");
		testPriorityPush(new Integer[]{3, 9, 8, 4, 6},     6, true,     new Integer[]{3, 9, 8, 4, 6},          "push an element that is at the top of the stack");
		testPriorityPush(new Integer[]{7, 8, 3, 2, 1},     3, true,     new Integer[]{7, 8, 2, 1, 3},          "push an element that is in the middle of the stack");
		testPriorityPush(new Integer[]{7, 8, 2, 1},        2, true,     new Integer[]{7, 8, 1, 2},             "push an element that is in the middle of the stack");
		testPriorityPush(new Integer[]{7, 8, 3, 8, 1},     8, true,     new Integer[]{7, 8, 3, 1, 8},          "push an element that is in the middle of the stack and repeated");
		testPriorityPush(new Integer[]{4, 2, 5, 2, 3, 6},  2, true,     new Integer[]{4, 2, 5, 3, 6, 2},       "push an element that is in the middle of the stack and repeated");
		testPriorityPush(new Integer[]{1, 9, 9, 7, 9},     9, true,     new Integer[]{1, 9, 9, 7, 9},          "push an element that is on the top of the stack and repeated");
		testPriorityPush(new String[]{"a", "b", "e", "c"}, new String("b"), true, new String[]{"a", "e", "c", "b"}, "test with Strings");
		testPriorityPush(new Integer[]{},                  2, false,    new Integer[]{2},                      "push on an empty stack");
		testPriorityPush(new Integer[]{4},                 4, true,     new Integer[]{4},                      "push on a singleton stack with a match");
		testPriorityPush(new Integer[]{7},                 5, false,    new Integer[]{7, 5},                   "push on a singleton stack without a match");
	
	  
    	// UN-COMMENT FOR EXTRA CREDIT	    
		// first parameter is the contents that will be pushed onto the stack- contents are pushed from start to finish
		//    (so the element at array index 0 will be on the bottom of the stack)
		// second parameter is the expected result
		// note: if there is no expected result passed in, that means the expected result is returning null or throwing an exception
		// second/third parameter is test description

		System.out.println("\n\n******************************TESTING PEEKNEXT IN LINKEDSTACK******************************");
		testPeekNextLinked(new Integer[]{},                        "empty stack");
		testPeekNextLinked(new Integer[]{1},                       "singleton stack");
		testPeekNextLinked(new Integer[]{1, 2},          1,        "even length stack");
		testPeekNextLinked(new Integer[]{4, 5, 6},       5,        "odd length stack");
		testPeekNextLinked(new Integer[]{3, 8, 7, 2},    7,        "odd length stack");
		testPeekNextLinked(new String[]{"a", "b", "c", "d"},  "c", "test with Strings");
     	
 		System.out.println("\n\n******************************TESTING PEEKNEXT IN ARRAYSTACK******************************");
		testPeekNextArray(new Integer[]{},                        "empty stack");
		testPeekNextArray(new Integer[]{1},                       "singleton stack");
		testPeekNextArray(new Integer[]{1, 2},          1,        "even length stack");
		testPeekNextArray(new Integer[]{4, 5, 6},       5,        "odd length stack");
		testPeekNextArray(new Integer[]{3, 8, 7, 2},    7,        "odd length stack");
		testPeekNextArray(new String[]{"a", "b", "c", "d"},  "c", "test with Strings");    		    
    }      
	
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */
	
	public static <T> void testPriorityPush(T[] contents, T pushElement, boolean expectedBooleanResult, T[] expectedContents, String testDescription) {
		LinkedStack<T> stack = new LinkedStack<T>();
		LinkedStack<T> printStack = new LinkedStack<T>();
		for(T element : contents) {
			stack.push(element);
			printStack.push(element);
		}
		boolean actualBooleanResult = stack.priorityPush(pushElement);
		printStack.priorityPush(pushElement);
		
		List<T> printList = createDisplayListFromStack(printStack);
		
		boolean contentsMatch = true;
		int index = -1;
		for(index = expectedContents.length-1; index>=0 && !stack.isEmpty() && contentsMatch; index--) {
			T stackValue = stack.pop();
			T expectedValue = expectedContents[index];
			if(!stackValue.equals(expectedValue)) {
				contentsMatch = false;
			}
		}
		if(!stack.isEmpty() || index>=0) {
			contentsMatch = false;
		}
		
		System.out.println("Original stack: Bottom*" + Arrays.toString(contents) + "*Top \tPriority Push " + pushElement 
				+ " \tPrioritized? " + actualBooleanResult + "\tAfter stack: Bottom*" + printList + "*Top");
		if(!contentsMatch) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected stack contents after: *Bottom " + Arrays.toString(expectedContents));
		}
		if(actualBooleanResult != expectedBooleanResult) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected boolean result=" + expectedBooleanResult );
		} 
				
	}
	
	private static <T> List<T> createDisplayListFromStack(StackInterface<T> stack) {
		List<T> printList = new LinkedList<T>();
		while(!stack.isEmpty()) {
			printList.add(stack.pop());
		}
		Collections.reverse(printList);
		return printList;
	}
	
	public static <T> void testPeekNextLinked(T[] contents, String testDescription) {
		testPeekNextLinked(contents, null, testDescription);
	}
	public static <T> void testPeekNextLinked(T[] contents, T expectedResult, String testDescription) {
		LinkedStack<T> stack = new LinkedStack<T>();
		LinkedStack<T> printStack = new LinkedStack<T>();
		for(T element : contents) {
			stack.push(element);
			printStack.push(element);
		}
		testPeekNext(stack, printStack, expectedResult, testDescription);
	}
	public static <T> void testPeekNextArray(T[] contents, String testDescription) {
		testPeekNextArray(contents, null, testDescription);
	}
	public static <T> void testPeekNextArray(T[] contents, T expectedResult, String testDescription) {
		ArrayStack<T> stack = new ArrayStack<T>();
		ArrayStack<T> printStack = new ArrayStack<T>();
		for(T element : contents) {
			stack.push(element);
			printStack.push(element);
		}
		testPeekNext(stack, printStack, expectedResult, testDescription);
	}
	private static <T> int getStackSize(StackInterface<T> stack) {
		int size = 0;
		while(!stack.isEmpty()) {
			stack.pop();
			size++;
		}
		return size;
	}
	private static <T> void testPeekNext(StackInterface<T> stack, StackInterface<T> printStack, T expectedResult, String testDescription) {
	
		List<T> printList = createDisplayListFromStack(printStack);
		boolean exceptionThrown = false;
		T actualResult;
		Exception exception = null;
		try {
			actualResult = stack.peekNext();
		} catch(Exception ex) {
			// Normally this is bad practice to catch a general exception object. Don't do this!! 
			// I had to do this here to make this general since you can choose different kinds of exceptions for this question.
			exceptionThrown = true;
			actualResult = null;
			exception = ex;
		}
		
		int actualAfterSize = getStackSize(stack);
		int expectedAfterSize = printList.size();
		
		if(exceptionThrown) {
			System.out.println("Peek next for Bottom*" + printList + "*Top \tResult: exception thrown of type " + exception.getClass().getName());
		} else {
			System.out.println("Peek next for Bottom*" + printList + "*Top \tResult=" + actualResult);

		}
		
		if(expectedResult==null) {
			if(! (actualResult==null || exceptionThrown)) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Expected peekNext contents are either null or an exception thrown.");
			}
		} else { 
			if(!expectedResult.equals(actualResult)) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Expected peekNext result=" + expectedResult);
			}
		}
		if(actualAfterSize!=expectedAfterSize) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Size of the stack should not change");
			
		}
	
		
	}
	
	
	
	
    
}