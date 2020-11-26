public class CircularLinkedQueue<T> implements QueueInterface<T> {
	
	public Node lastNode; // this should be private! i'm just making it public to run the tester
	
	public CircularLinkedQueue() {
		lastNode = null; // empty queue
	}

	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry, null);
		
		if(isEmpty()) {
			newNode.next = newNode;
			lastNode = newNode;
		} else { // queue is not empty
			Node firstNode = lastNode.next;
			newNode.next = firstNode;
			lastNode.next = newNode;
			lastNode = newNode;			
		}
	}

	public T getFront() {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		return lastNode.next.data;
	}

	public T dequeue() {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		Node firstNode = lastNode.next;
		T front = firstNode.data;
		
		if(firstNode==lastNode) { // dequeueing from a singleton
			lastNode = null;
		} else { // dequeueing from a queue with 2 or more
			lastNode.next = firstNode.next;
		}
		return front;
	}

	public boolean isEmpty() {
		return lastNode==null;
	}

	public void clear() {
		lastNode = null;
	}

	public class Node {
		public T data;
		public Node next;

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}
		private Node(T dataPortion, Node linkPortion) {
			data = dataPortion;
			next = linkPortion;
		}
	}
} 
