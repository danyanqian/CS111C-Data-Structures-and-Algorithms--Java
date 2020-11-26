import java.util.*;

public class HomeworkM11Driver {
	
	public static void main(String[] args) {

		System.out.println("*****************************************************TESTING CLIENT VIEW SPLICE");
		// first parameter is the contents of Q1 (listed front to back)
		// second parameter is the contents of Q2 (listed front to back)
		// third parameter is the expected result after the splice (listed from to back)
		testClientSplice(new Integer[]{},            new Integer[]{},                 new Integer[]{},                    "splice empty queue to empty queue");
		testClientSplice(new Integer[]{4, 8, 9, 2},  new Integer[]{},                 new Integer[]{4, 8, 9, 2},          "splice empty queue to non-empty queue");
		testClientSplice(new Integer[]{},            new Integer[]{3, 7, 1, 2, 5},    new Integer[]{3, 7, 1, 2, 5},       "splice non-empty queue to empty queue");
		testClientSplice(new Integer[]{},            new Integer[]{1, 3, 7, 6, 3, 1}, new Integer[]{1, 3, 7, 6, 3, 1},    "splice non-empty queue to empty queue");
		testClientSplice(new Integer[]{1, 3, 2, 4},  new Integer[]{6, 9, 3},          new Integer[]{1, 3, 2, 4, 6, 9, 3}, "splice non-empty queue to non-empty queue");
		testClientSplice(new String[]{"a", "b", "c", "d"}, new String[]{"d", "e", "d"},  new String[]{"a", "b", "c", "d", "d", "e", "d"}, "splice Strings"); 
		
		System.out.println("\n\n*****************************************************TESTING ARRAYQUEUE SPLICE");
		testArraySplice(new Integer[]{},            new Integer[]{},                 new Integer[]{},                    "splice empty queue to empty queue");
		testArraySplice(new Integer[]{4, 8, 9, 2},  new Integer[]{},                 new Integer[]{4, 8, 9, 2},          "splice empty queue to non-empty queue");
		testArraySplice(new Integer[]{},            new Integer[]{3, 7, 1, 2, 5},    new Integer[]{3, 7, 1, 2, 5},       "splice non-empty queue to empty queue");
		testArraySplice(new Integer[]{},            new Integer[]{1, 3, 7, 6, 3, 1}, new Integer[]{1, 3, 7, 6, 3, 1},    "splice non-empty queue to empty queue");
		testArraySplice(new Integer[]{1, 3, 2, 4},  new Integer[]{6, 9, 3},          new Integer[]{1, 3, 2, 4, 6, 9, 3}, "splice non-empty queue to non-empty queue");
		testArraySplice(new String[]{"a", "b", "c", "d"}, new String[]{"d", "e", "d"},  new String[]{"a", "b", "c", "d", "d", "e", "d"}, "splice Strings");

		// test the wrap-around nature of ArrayQueue
		// for these tests, extra elements are enqueued and dequeued to force the contents to wrap around the array; the extra element added is 99
		testArraySpliceWrap(new Integer[]{4, 8, 9, 2},  new Integer[]{1, 3, 7, 6},      new Integer[]{4, 8, 9, 2, 1, 3, 7, 6},  "test of wrap around behavior");
		

		System.out.println("\n\n*****************************************************TESTING LINKEDQUEUE SPLICE");
		testLinkedSplice(new Integer[]{},            new Integer[]{},                 new Integer[]{},                    "splice empty queue to empty queue");
		testLinkedSplice(new Integer[]{4, 8, 9, 2},  new Integer[]{},                 new Integer[]{4, 8, 9, 2},          "splice empty queue to non-empty queue");
		testLinkedSplice(new Integer[]{},            new Integer[]{3, 7, 1, 2, 5},    new Integer[]{3, 7, 1, 2, 5},       "splice non-empty queue to empty queue");
		testLinkedSplice(new Integer[]{},            new Integer[]{1, 3, 7, 6, 3, 1}, new Integer[]{1, 3, 7, 6, 3, 1},    "splice non-empty queue to empty queue");
		testLinkedSplice(new Integer[]{1, 3, 2, 4},  new Integer[]{6, 9, 3},          new Integer[]{1, 3, 2, 4, 6, 9, 3}, "splice non-empty queue to non-empty queue");
		testLinkedSplice(new String[]{"a", "b", "c", "d"}, new String[]{"d", "e", "d"},  new String[]{"a", "b", "c", "d", "d", "e", "d"}, "splice Strings");

		
	   	// UN-COMMENT FOR EXTRA CREDIT	    
			// first parameter is the contents that will be enqueued onto the queue- contents are added from start to finish
			//    (so the element at array index 0 will be on the front of the queue)
			// second parameter is the expected result
			// note: if there is no expected result passed in, that means the expected result is returning null or throwing an exception
			// second/third parameter is test description

		System.out.println("\n\n******************************TESTING GETSECOND IN LINKEDQUEUE ******************************");
		testGetSecondLinked(new Integer[]{},                        "empty queue");
		testGetSecondLinked(new Integer[]{1},                       "singleton queue");
		testGetSecondLinked(new Integer[]{1, 2},          2,        "even length queue");
		testGetSecondLinked(new Integer[]{4, 5, 6},       5,        "odd length queue");
		testGetSecondLinked(new Integer[]{3, 8, 7, 2},    8,        "odd length queue");
		testGetSecondLinked(new String[]{"a", "b", "c", "d"},  "b", "test with Strings");
   

		System.out.println("\n\n******************************TESTING GETSECOND IN ARRAYQUEUE ******************************");
		testGetSecondArray(new Integer[]{},                        "empty queue");
		testGetSecondArray(new Integer[]{1},                       "singleton queue");
		testGetSecondArray(new Integer[]{1, 2},          2,        "even length queue");
		testGetSecondArray(new Integer[]{4, 5, 6},       5,        "odd length queue");
		testGetSecondArray(new Integer[]{3, 8, 7, 2},    8,        "odd length queue");
		testGetSecondArray(new String[]{"a", "b", "c", "d"},  "b", "test with Strings");    		    

	}		

	// note: don't worry about generics here; i've omitted them and suppressed the
	// warnings so you can focus on the queue logic
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void splice(QueueInterface firstQueue, QueueInterface secondQueue) {
     	// YOUR CODE HERE
		LinkedQueue<Object> otherQueue = new LinkedQueue();
		while (!secondQueue.isEmpty()) {
			Object item = secondQueue.dequeue();
			otherQueue.enqueue(item);
			firstQueue.enqueue(item);
		}
		while (!otherQueue.isEmpty()){
			secondQueue.enqueue(otherQueue.dequeue());
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
	private static enum SpliceType {CLIENT, ARRAY, LINKED };
	
	public static <T> void testClientSplice(T[] contents1, T[] contents2, T[] expectedResultContents, String testDescription) {
		QueueInterface<T> q1 = new LinkedQueue<T>();
		QueueInterface<T> q2 = new ArrayQueue<T>();

		testSplice(contents1, q1, contents2, q2, expectedResultContents, true, testDescription, SpliceType.CLIENT);
	}
	
	public static <T> void testArraySplice(T[] contents1, T[] contents2, T[] expectedResultContents, String testDescription) {
		QueueInterface<T> q1 = new ArrayQueue<T>();
		QueueInterface<T> q2 = new ArrayQueue<T>();

		testSplice(contents1, q1, contents2, q2, expectedResultContents, true, testDescription, SpliceType.ARRAY);
	}
	public static void testArraySpliceWrap(Integer[] contents1, Integer[] contents2, Integer[] expectedResultContents, String testDescription) {
		QueueInterface<Integer> q1 = new ArrayQueue<Integer>(4);
		QueueInterface<Integer> q2 = new ArrayQueue<Integer>(4);
		
		q1.enqueue(99); q1.enqueue(99); q1.enqueue(99);
		q1.enqueue(contents1[0]);
		q1.dequeue(); q1.dequeue(); q1.dequeue(); // forcing the contents to wrap around the array
		q1.enqueue(contents1[1]); 	q1.enqueue(contents1[2]); q1.enqueue(contents1[3]);

		q2.enqueue(99); q2.enqueue(99); q2.enqueue(99);
		q2.enqueue(contents2[0]);
		q2.dequeue(); q2.dequeue(); q2.dequeue(); // forcing the contents to wrap around the array
		q2.enqueue(contents2[1]); 	q2.enqueue(contents2[2]); q2.enqueue(contents2[3]);

		testSplice(contents1, q1, contents2, q2, expectedResultContents, false, testDescription, SpliceType.ARRAY);
	}

	public static <T> void testLinkedSplice(T[] contents1, T[] contents2, T[] expectedResultContents, String testDescription) {
		QueueInterface<T> q1 = new LinkedQueue<T>();
		QueueInterface<T> q2 = new LinkedQueue<T>();

		testSplice(contents1, q1, contents2, q2, expectedResultContents, true, testDescription, SpliceType.LINKED);
	}
	
	public static <T> void testSplice(T[] contents1, QueueInterface<T> q1, T[] contents2, QueueInterface<T> q2, T[] expectedResultContents, boolean build, String testDescription, SpliceType type) {
		
		if(build) {
			for(T element : contents1) {
				q1.enqueue(element);
			}
			for(T element : contents2) {
				q2.enqueue(element);
			}
		}
		if(type==SpliceType.CLIENT) { 	
			splice(q1, q2);
		} else if(type==SpliceType.ARRAY) {
			( (ArrayQueue<T>)q1).splice(( (ArrayQueue<T>)q2));
		} else if(type==SpliceType.LINKED) {
			( (LinkedQueue<T>)q1).splice(( (LinkedQueue<T>)q2));
		}
		T[] actualArray = getArray(q1);
		
		int actualSizeQ1 = getQueueSize(q1);
		int expectedSizeQ1 = expectedResultContents.length;
		
		int actualSizeQ2 = getQueueSize(q2);
		int expectedSizeQ2 = contents2.length;
		
		String extraQTab = "", extraResultTab = "";
		if(contents1.length==0 ) {
			extraQTab = "\t\t";
		}
		if(contents2.length==0 ) {
			extraResultTab = "\t\t";
		} 
		
		if(contents1.length<=4 && contents1.length>0 && contents2.length<=4 && contents2.length>0) {
			extraResultTab += "\t";
		}
		
		System.out.print("Q1: " + Arrays.toString(contents1) + extraQTab + "\tSpliced with Q2: " + Arrays.toString(contents2) + extraResultTab + "\tResult: " + Arrays.toString(actualArray));
		
		if(type==SpliceType.LINKED) {
			
			T expectedFirstQ1 = expectedResultContents.length==0 ? null : expectedResultContents[0];
			T expectedLastQ1 = expectedResultContents.length==0 ? null : expectedResultContents[expectedResultContents.length-1];
			T expectedFirstQ2 = contents2.length==0 ? null : contents2[0];
			T expectedLastQ2 = contents2.length==0 ? null : contents2[contents2.length-1];
			
			T actualFirstQ1 = q1.isEmpty() ? null : ((LinkedQueue<T>)q1).firstNode.data;
			T actualLastQ1 = q1.isEmpty() ? null : ((LinkedQueue<T>)q1).lastNode.data;
			T actualFirstQ2 = q2.isEmpty() ? null : ((LinkedQueue<T>)q2).firstNode.data;
			T actualLastQ2 = q2.isEmpty() ? null : ((LinkedQueue<T>)q2).lastNode.data;
			
			String extraNodeTab = "";
			if(actualSizeQ1<=5) {
				extraNodeTab = "\t";
				if(actualSizeQ1<2) {
					extraNodeTab = "\t";
				}
			}
			System.out.println(extraNodeTab + "\tQ1 firstNode=" + actualFirstQ1 + "\tQ1 lastNode=" + 
					actualLastQ1 + "\tQ2 firstNode=" + actualFirstQ2 + "\tQ2 lastNode=" + actualLastQ2);
			
			boolean q1FirstMatches = dataMatches(expectedFirstQ1, actualFirstQ1);
			boolean q1LastMatches = dataMatches(expectedLastQ1, actualLastQ1);
			boolean q2FirstMatches = dataMatches(expectedFirstQ2, actualFirstQ2);
			boolean q2LastMatches = dataMatches(expectedLastQ2, actualLastQ2);
			
			if(!q1FirstMatches || !q1LastMatches || !q2FirstMatches || !q2LastMatches) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Expected Q1 firstNode=" + expectedFirstQ1 + " lastNode=" + expectedFirstQ2 +
						"\tExpected Q2 firstNode=" + expectedFirstQ2 + " lastNode=" + expectedLastQ2);
			}
		} else {
			System.out.println();
		}

		if(actualSizeQ1!=expectedSizeQ1 || actualSizeQ2!=expectedSizeQ2) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected size of Q1=" + expectedSizeQ1 + " and expected size of Q2=" + expectedSizeQ2);
		}
		boolean correctContents = true;
		for(int i=0; !q1.isEmpty() && correctContents && i<expectedResultContents.length; i++) {
			T qElement = q1.dequeue();
			if(!qElement.equals(expectedResultContents[i])) {
				correctContents = false;
			}
		}
		if(!correctContents || !q1.isEmpty()) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Expected contents of Q1 after splice: " + Arrays.toString(expectedResultContents));
		}
	}
	private static <T> boolean dataMatches(T expected, T actual) {
		if(expected==null || actual==null) {
			if(expected!=actual) {
				return false;
			}
		} else { // neither is null
			if(!expected.equals(actual)) {
				return false;
			}
		}
		return true;
	}
	private static <T> T[] getArray(QueueInterface<T> queue) {
		int queueSize = getQueueSize(queue);
		T[] array = (T[]) new Object[queueSize];
		QueueInterface<T> copyQueue = new LinkedQueue<T>();
		while(!queue.isEmpty()) {
			copyQueue.enqueue(queue.dequeue());
		}
		int index=0;
		for(int i=0; !copyQueue.isEmpty(); i++) {
			T element = copyQueue.dequeue();
			queue.enqueue(element);
			array[i] = element;
		}
		return array;	
	}
	
	private static <T> int getQueueSize(QueueInterface<T> queue) {
		int size = 0;
		QueueInterface<T> copyQueue = new LinkedQueue<T>();
		while(!queue.isEmpty()) {
			size++;
			copyQueue.enqueue(queue.dequeue());
		}
		while(!copyQueue.isEmpty()) {
			queue.enqueue(copyQueue.dequeue());
		}
		return size;
	}	

	public static <T> void testGetSecondLinked(T[] contents, String testDescription) {
		testGetSecondLinked(contents, null, testDescription);
	}
	public static <T> void testGetSecondLinked(T[] contents, T expectedResult, String testDescription) {
		LinkedQueue<T> queue = new LinkedQueue<T>();
		LinkedQueue<T> printQueue = new LinkedQueue<T>();
		for(T element : contents) {
			queue.enqueue(element);
			printQueue.enqueue(element);
		}
		testGetSecond(queue, printQueue, expectedResult, testDescription);
	}
	public static <T> void testGetSecondArray(T[] contents, String testDescription) {
		testGetSecondArray(contents, null, testDescription);
	}
	public static <T> void testGetSecondArray(T[] contents, T expectedResult, String testDescription) {
		ArrayQueue<T> queue = new ArrayQueue<T>();
		ArrayQueue<T> printQueue = new ArrayQueue<T>();
		for(T element : contents) {
			queue.enqueue(element);
			printQueue.enqueue(element);
		}
		testGetSecond(queue, printQueue, expectedResult, testDescription);
	}
	
	private static <T> void testGetSecond(QueueInterface<T> queue, QueueInterface<T> printQueue, T expectedResult, String testDescription) {
	
		List<T> printList = createDisplayListFromQueue(printQueue);
		boolean exceptionThrown = false;
		T actualResult;
		Exception exception = null;
		try {
			actualResult = queue.getSecond();
		} catch(Exception ex) {
			// Normally this is bad practice to catch a general exception object. Don't do this!! 
			// I had to do this here to make this general since you can choose different kinds of exceptions for this question.
			exceptionThrown = true;
			actualResult = null;
			exception = ex;
		}
		
		int actualAfterSize = getQueueSize(queue);
		int expectedAfterSize = printList.size();
		
		if(exceptionThrown) {
			System.out.println("getSecond for Front*" + printList + "*Back \tResult: exception thrown of type " + exception.getClass().getName());
		} else {
			System.out.println("getSecond for Front*" + printList + "*Back \tResult=" + actualResult);

		}
		
		if(expectedResult==null) {
			if(! (actualResult==null || exceptionThrown)) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Expected getSecond contents are either null or an exception thrown.");
			}
		} else { 
			if(!expectedResult.equals(actualResult)) {
				System.out.println("\t***Test failed for: " + testDescription);
				System.out.println("\t   Expected getSecond result=" + expectedResult);
			}
		}
		if(actualAfterSize!=expectedAfterSize) {
			System.out.println("\t***Test failed for: " + testDescription);
			System.out.println("\t   Size of the queue should not change");
			
		}		
	}
	private static <T> List<T> createDisplayListFromQueue(QueueInterface<T> queue) {
		List<T> printList = new LinkedList<T>();
		while(!queue.isEmpty()) {
			printList.add(queue.dequeue());
		}
		return printList;
	}

}