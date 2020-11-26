import java.util.Arrays;

public class ArrayQueueTester {

	public static void main(String[] args) {
		// *** to use, temporarily make the instance data variables in ArrayQueue public
		
		ArrayQueue<String> wordQueue = new ArrayQueue<String>(5);
		
		System.out.println("enqueue amps");
		wordQueue.enqueue("amps");
		printQueue(wordQueue);
		
		System.out.println("enqueue bank");
		wordQueue.enqueue("bank");
		printQueue(wordQueue);
		
		System.out.println("enqueue card");
		wordQueue.enqueue("card");
		printQueue(wordQueue);
		
		System.out.println("dequeue");
		wordQueue.dequeue();
		printQueue(wordQueue);
		
		System.out.println("enqueue door");
		wordQueue.enqueue("door");
		printQueue(wordQueue);
		
		System.out.println("enqueue ever");
		wordQueue.enqueue("ever");
		printQueue(wordQueue);
		
		System.out.println("dequeue");
		wordQueue.dequeue();
		printQueue(wordQueue);
		
		System.out.println("enqueue frog");
		wordQueue.enqueue("frog");
		printQueue(wordQueue);
		
		System.out.println("enqueue golf");
		wordQueue.enqueue("golf");
		printQueue(wordQueue);
		
		System.out.println("dequeue");
		wordQueue.dequeue();
		printQueue(wordQueue);
		
		System.out.println("enqueue hats");
		wordQueue.enqueue("hats");
		printQueue(wordQueue);
		
		System.out.println("enqueue iris");
		wordQueue.enqueue("iris");
		printQueue(wordQueue);
	}
	private static void printQueue(ArrayQueue queue) {
		System.out.println(Arrays.toString(queue.queue) + "\tFront=" + queue.getFront() + "\tfrontIndex=" + queue.frontIndex +"\tbackIndex=" + queue.backIndex + "\n");
	}

}
