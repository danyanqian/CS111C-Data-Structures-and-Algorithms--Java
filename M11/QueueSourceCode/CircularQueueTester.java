
public class CircularQueueTester {
	public static void main(String[] args) {
		// *** to use, temporarily make the lastNode in CircularLinkedQueue public
		// (note I also made parts of the Node class public)
		CircularLinkedQueue<String> wordQueue = new CircularLinkedQueue<String>();

		printQueue(wordQueue);
		wordQueue.enqueue("amps");
		printQueue(wordQueue);
		wordQueue.enqueue("bank");
		wordQueue.enqueue("card");
		printQueue(wordQueue);
		wordQueue.dequeue();
		printQueue(wordQueue);
		wordQueue.enqueue("door");
		wordQueue.enqueue("ever");
		printQueue(wordQueue);
		wordQueue.dequeue();
		printQueue(wordQueue);
		wordQueue.enqueue("frog");
		wordQueue.enqueue("golf");
		wordQueue.dequeue();
		printQueue(wordQueue);
		wordQueue.enqueue("hats");
		wordQueue.enqueue("iris");
		printQueue(wordQueue);
		wordQueue.dequeue();
		wordQueue.dequeue();
		wordQueue.dequeue();
		wordQueue.dequeue();
		printQueue(wordQueue);
		wordQueue.dequeue();
		printQueue(wordQueue);
		wordQueue.dequeue();
		printQueue(wordQueue);
	}

	@SuppressWarnings("rawtypes")
	private static void printQueue(CircularLinkedQueue queue) {
		CircularLinkedQueue.Node last = queue.lastNode;
		if(last==null) {
			System.out.println("Front: null \tBack: null");
		} else {
			CircularLinkedQueue.Node first =last.next;
			System.out.print("Front: " + first.data + "\tBack: " + last.data + "\t");
			CircularLinkedQueue.Node current = first;
			while(current!=last) {
				System.out.print(current.data + "->");
				current = current.next;
			}
			System.out.println(current.data);
		}

		
	}

}
