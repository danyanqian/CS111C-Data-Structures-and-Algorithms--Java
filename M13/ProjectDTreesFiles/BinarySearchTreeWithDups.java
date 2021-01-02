import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry) {
		T result = newEntry;
		if (isEmpty()) {
			setRootNode(new BinaryNode<T>(newEntry));
		} else {
			addEntryHelperNonRecursive(newEntry);
		}
		return result;
	}
	
	// YOUR CODE HERE! THIS METHOD CANNOT BE RECURSIVE.
	private void addEntryHelperNonRecursive(T newEntry) {
		BinaryNode<T> currentNode = getRootNode();
		boolean found = false;
		while (!found){
			int comparison = newEntry.compareTo(currentNode.getData());
			if (comparison > 0){ // the item is > the node
				if (currentNode.hasRightChild()){
					currentNode = currentNode.getRightChild();
				} else {
					found = true;
					currentNode.setRightChild(new BinaryNode<>(newEntry));
				}
			} else { // the item is <= the node
				if (currentNode.hasLeftChild()){
					currentNode = currentNode.getLeftChild();
				} else {
					found = true;
					currentNode.setLeftChild(new BinaryNode<>(newEntry));
				}
			}
		}
	}


	// YOUR CODE HERE! THIS METHOD CANNOT BE RECURSIVE.
	// MAKE SURE TO TAKE ADVANTAGE OF THE SORTED NATURE OF THE BST!
	public int countEntriesNonRecursive(T target) {
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> currentNode = getRootNode();
		// consider a loop!
		while ( currentNode != null){
			int comparison = target.compareTo(currentNode.getData());
			if (comparison > 0) { // the item is > the node
				currentNode = currentNode.getRightChild();
			} else { // the item is <= the node
				if (comparison == 0) {
					count++;
				}
				currentNode = currentNode.getLeftChild();
			}
		}
		return count;
	}

	// YOUR CODE HERE! MUST BE RECURSIVE! YOU ARE ALLOWED TO CREATE A PRIVATE HELPER.
	// MAKE SURE TO TAKE ADVANTAGE OF THE SORTED NATURE OF THE BST!
	public int countGreaterRecursive(T target) {
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = getRootNode();
		// consider a helper method!
		// loop to found the rootNode
		if (rootNode == null) {
			return 0;
		}

		boolean foundNode = false;
		while (!foundNode){
			T nodeData = rootNode.getData();

			if (target.compareTo(nodeData) == 0){ // target = rootNode
				foundNode = true;
				if (rootNode.hasRightChild()) {
					count += countGreaterRecursiveHelper(rootNode.getRightChild(), 0);
				}
			} else if (target.compareTo(nodeData) < 0) { // target < rootNode
				if (rootNode.hasRightChild()) {
					count += 1+ countGreaterRecursiveHelper(rootNode.getRightChild(), 0);
				} else {
					count++;
				}

				if (rootNode.hasLeftChild()) {
					rootNode = rootNode.getLeftChild();
				} else {
					foundNode = true;
				}

			} else { // target > rootNode
				if (rootNode.hasRightChild()) {
					rootNode = rootNode.getRightChild();
				} else { // at the leaf Node
					foundNode = true;
				}
			}
		}

		return count;
	}

	private int countGreaterRecursiveHelper(BinaryNode<T> rootNode, int count){
		if (rootNode.isLeaf()) {
			return 1;
		} else {
			if (rootNode.hasLeftChild() && rootNode.hasRightChild()){
				count += 1+ countGreaterRecursiveHelper(rootNode.getLeftChild(), count) +
						countGreaterRecursiveHelper(rootNode.getRightChild(), count);
			} else if (rootNode.hasLeftChild()){
				count += 1+ countGreaterRecursiveHelper(rootNode.getLeftChild(), count);
			} else {
				count += 1+ countGreaterRecursiveHelper(rootNode.getRightChild(), count);
			}
		}
		return count;
	}

		
	// YOUR CODE HERE! MUST USE A STACK!! MUST NOT BE RECURSIVE! 
	// MAKE SURE TO TAKE ADVANTAGE OF THE SORTED NATURE OF THE BST!
	public int countGreaterWithStack(T target) {
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = getRootNode();
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(rootNode);

//		int total_iteration = 0;
		// consider a loop based on the stack!
		while (!nodeStack.isEmpty()){
//			total_iteration++;
			BinaryNode<T> node = nodeStack.pop();
			if (target.compareTo(node.getData()) == 0){ // target = node
				if (node.hasRightChild()){
					nodeStack.push(node.getRightChild());
				}
			} else if (target.compareTo(node.getData()) < 0) { // target < node
				count++;
				if (node.hasRightChild()){
					nodeStack.push(node.getRightChild());
				}
				if (node.hasLeftChild()){
					nodeStack.push(node.getLeftChild());
				}
			} else { // target > node
				if (node.hasRightChild()) {
					nodeStack.push(node.getRightChild());
				} else {
					return 0;
				}
			}
		}
//		System.out.println(total_iteration);
		return count;
	}
		
	// YOUR EXTRA CREDIT CODE HERE! THIS METHOD MUST BE O(n). 
	// YOU ARE ALLOWED TO USE A HELPER METHOD. THE METHOD CAN BE ITERATIVE OR RECURSIVE. 
	public int countUniqueValues() {
		BinaryNode<T> rootNode = getRootNode();
		if (rootNode == null) {
			return 0;
		}
		// use inorder traverse to add every node value in a linked list: O(n)
		List<T> lList = new LinkedList<T>();
		lList = recursiveInorderTraverseList(rootNode, lList);

		// use the ordered characteristics of Inorder Traverse of BST to loop
		// through the Inorder Traverse List once: O(n)
		T preItem = lList.get(0);
		int uniqueCount = 1;
		for(int num=0; num<lList.size(); num++) {
			// if the item is different from previous item, uniqueCount+=1
			if (preItem.compareTo(lList.get(num)) != 0) {
				uniqueCount++;
			}
			// update previous item
			preItem = lList.get(num);
		}
		return uniqueCount;
	}

	private List<T> recursiveInorderTraverseList(BinaryNode<T> node, List<T> lList) {
		if (node != null) {
			lList = recursiveInorderTraverseList(node.getLeftChild(), lList);
			lList.add(node.getData());
			lList = recursiveInorderTraverseList(node.getRightChild(), lList);
		}
		return lList;
	}



	public int getLeftHeight() {
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null) {
			return 0;
		} else if(!rootNode.hasLeftChild()) {
			return 0;
		} else {
			return rootNode.getLeftChild().getHeight();
		}
	}

	public int getRightHeight() {
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null) {
			return 0;
		} else if(!rootNode.hasRightChild()) {
			return 0;
		} else {
			return rootNode.getRightChild().getHeight();
		}
	}
	


}