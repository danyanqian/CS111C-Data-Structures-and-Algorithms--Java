public class LinkedFrontBackLimitedSizeList<T extends Comparable<? super T>>
		implements FrontBackLimitedSizeListInterface<T>,
		Comparable<LinkedFrontBackLimitedSizeList<T>> {

	private Node head, tail;
	private int numberOfEntries;
	private int maxSize;

	// YOUR CLASS HERE!
	public LinkedFrontBackLimitedSizeList(int size){
		initializeDataFields();
		maxSize = size;
	}

	public boolean addFront(T newEntry){
		// if the list is already full
		if (isFull()){
			return false;
		} else {
			// the list is not full
			Node newNode = new Node(newEntry);
			newNode.next = head;
			head = newNode;
			// if the list is empty
			if (isEmpty()){
				tail = newNode;
			}
			numberOfEntries++;
			return true;
		}
	}


	public boolean addBack(T newEntry){
		if (isFull()){
			return false;
		} else {
			Node newNode = new Node(newEntry);
			if (isEmpty()){
				head = newNode;
			} else {
				tail.next = newNode;
			}
			tail = newNode;
			numberOfEntries++;
			return true;
		}
	}

	public T removeFront(){
		if (isEmpty()) {
			return null;
		} else if (numberOfEntries == 1) {
			T removedData = head.data;
			initializeDataFields();
			return removedData;
		} else {
			T removedData = head.data;
			head = head.next;
			numberOfEntries--;
			return removedData;
		}
	}

	public T removeBack(){
		if (isEmpty()) {
			return null;
		} else if (numberOfEntries == 1) {
			T removedData = head.data;
			initializeDataFields();
			return removedData;
		} else {
			T removedData = tail.data;
			Node desiredNode = getNodeAt(numberOfEntries-1);
			desiredNode.next = null;
			tail = desiredNode;
			numberOfEntries--;
			return removedData;
		}
	}

	public boolean contains(T anEntry){
		boolean found = false;
		Node currentNode = head;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			} else {
				currentNode = currentNode.next;
			}
		}
		return found;
	}

	public int indexOf(T anEntry){
		boolean found = false;
		int entryIndex = -1;
		Node currentNode = head;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			} else {
				currentNode = currentNode.next;
			}
			entryIndex++;
		}
		if (found) {
			return entryIndex;
		}
		else {
			return -1;
		}
	}

	public int lastIndexOf(T anEntry){
		int entryIndex = -1;
		int maxIndex = -1;
		Node currentNode = head;
		while ( currentNode != null) {
			entryIndex++;
			if (anEntry.equals(currentNode.data)) {
				maxIndex = entryIndex;
			}
			currentNode = currentNode.next;
		}
		return maxIndex;
	}

	public T getEntry(int givenPosition){
		if (givenPosition > numberOfEntries || isEmpty() || givenPosition < 0) {
			return null;
		} else {
			Node currentNode = head;
			int counter = 0;
			while (currentNode != null) {
				if (counter == givenPosition){
					return currentNode.data;
				} else {
					counter++;
					currentNode = currentNode.next;
				}
			}
			return null;
		}
	}

	private Node getNodeAt(int givenPosition) {
		Node currentNode = head;
		for (int counter = 1; counter <= givenPosition-1; counter++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}


	public void clear() {
		initializeDataFields();
	}

	public boolean isEmpty(){
		return numberOfEntries <= 0;
	}

	public boolean isFull(){
		return numberOfEntries >= maxSize;
	}

	public int size(){
		return numberOfEntries;
	}

	private void initializeDataFields(){
		head = tail = null;
		numberOfEntries = 0;
	}

	@Override
	public String toString(){

		Node currentNode = head;
		String str = "";
		if (head == null) {
			return "[" + str + "]";
		}

		for (int counter = 0; counter < numberOfEntries; counter++) {
			if (counter == 0) {
				str = str + currentNode.data ;
			} else {
				str = str + ", " + currentNode.data;
			}
			currentNode = currentNode.next;
		}
		return "[" + str + "]" + " head=" + head.data + " tail=" + tail.data;
	}

	@Override
	public int compareTo(LinkedFrontBackLimitedSizeList<T> otherList) {
		Node currentNode1 = head;
		Node currentNode2 = otherList.head;

		while (currentNode1 != null && currentNode2 != null){
			if (currentNode1.data.equals(currentNode2.data) == false){
				return currentNode1.data.compareTo(currentNode2.data);
			} else {
				currentNode1 = currentNode1.next;
				currentNode2 = currentNode2.next;
			}
		}
		if (currentNode1 == null && currentNode2 == null){
			return 0;
		} else if (currentNode1 == null) {
			return -1;
		} else {
			return 1;
		}

	}


	public class Node {
		public T data;
		public Node next;

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
