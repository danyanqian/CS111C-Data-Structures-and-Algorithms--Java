/**
 * A class of priority queues represented by a chain of linked nodes.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Charles Hoot
 * @version 5.0
 */
public final class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

	private Node firstNode; // Reference to first node of chain and the front
							// of the priority queue, which has the highest priority

	public LinkedPriorityQueue() {
		firstNode = null;
	}

	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		Node nodeBefore = getNodeBefore(newEntry);

		if (isEmpty() || (nodeBefore == null)) { // Add at beginning
			newNode.next = firstNode;
			firstNode = newNode;
		} else { // Add after nodeBefore
			Node nodeAfter = nodeBefore.next;
			newNode.next = nodeAfter; 
			nodeBefore.next = newNode;
		}
	}

	public T remove() {
		T result = null;

		if (!isEmpty()) {
			result = firstNode.data;
			firstNode = firstNode.next;
		}
		return result;
	}

	public T peek() {
		T result = null;

		if (!isEmpty()) {
			result = firstNode.data;
		}
		return result;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	public int getSize() {
		Node current = firstNode;
		int size = 0;
		while (current != null) {
			size++;
			current = current.next;
		}
		return size;
	}

	public void clear() {
		firstNode = null;
	}

	@Override
	public String toString() {
		Node currentNode = firstNode;
		String text = "";
		while (currentNode != null) {
			text += currentNode.data + "  ";
			currentNode = currentNode.next;
		}
		text += "\n";
		return text;
	}

	// Returns either a reference to the node that is before the node
	// that contains or should contain anEntry, or null if
	// no prior node exists (that is, if anEntry belongs at
	// the beginning of the chain)
	private Node getNodeBefore(T anEntry) {
		Node currentNode = firstNode;
		Node nodeBefore = null;

		while (  (currentNode != null) && 
				(anEntry.compareTo(currentNode.data) >= 0)) { 
				// >= instead of > so that the new entry will get
				// put at the *back* of the priority section
			
			nodeBefore = currentNode;
			currentNode = currentNode.next;
		}

		return nodeBefore;
	}

	private class Node {
		private T data; // Entry in priority queue
		private Node next; // Link to next node

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
}
