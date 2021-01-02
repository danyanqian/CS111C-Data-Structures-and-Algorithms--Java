/**
 * A class that implements the ADT binary search tree by extending BinaryTree.
 * Recursive version.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 */
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {
	
	public BinarySearchTree() {
		super();
	} 

	public BinarySearchTree(T rootEntry) {
		super(rootEntry);
	} 

	// Disable setTree (see Segment 26.6)
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	} 

	public T getEntry(T anEntry) {
		return findEntry(getRootNode(), anEntry);
	} 

	private T findEntry(BinaryNode<T> currentRootNode, T anEntry) {
		T result = null;

		if (currentRootNode != null) {
			T rootEntry = currentRootNode.getData();

			if (anEntry.equals(rootEntry)) {
				result = rootEntry;
			} else if (anEntry.compareTo(rootEntry) < 0) {
				result = findEntry(currentRootNode.getLeftChild(), anEntry);
			} else { // anEntry.compareTo(rootEntry) > 0
				result = findEntry(currentRootNode.getRightChild(), anEntry);
			}
		} 
		return result;
	} 

	public boolean contains(T entry) {
		return getEntry(entry) != null;
	} 

	public T add(T newEntry) {
		T result = null;

		if (isEmpty()) {
			setRootNode(new BinaryNode<>(newEntry)); // inherited from BinaryTree
		} else {
			result = addEntry(getRootNode(), newEntry);
		}

		return result;
	} 

	// Adds anEntry to the nonempty subtree rooted at currentRootNode.
	private T addEntry(BinaryNode<T> currentRootNode, T anEntry) {
		// Assertion: currentRootNode != null
		T result = null;
		BinaryNode<T> newNodeToAdd = new BinaryNode<>(anEntry);
		int comparison = anEntry.compareTo(currentRootNode.getData());

		if (comparison == 0) { // anEntry == currentRootNode.data
			result = currentRootNode.getData();
			currentRootNode.setData(anEntry);
		} else if (comparison < 0) { // anEntry < currentRootNode.data
			if (currentRootNode.hasLeftChild()) {
				result = addEntry(currentRootNode.getLeftChild(), anEntry);
			} else {
				currentRootNode.setLeftChild(newNodeToAdd);
			}
		} else { // anEntry > currentRootNode.data
			if (currentRootNode.hasRightChild()) {
				result = addEntry(currentRootNode.getRightChild(), anEntry);
			} else {
				currentRootNode.setRightChild(newNodeToAdd);
			}
		} 

		return result;
	} // end addEntry

	// removes anEntry and returns anEntry (or null, if anEntry was not in the tree)
	public T remove(T anEntry) {
		ReturnObject removedEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), anEntry, removedEntry);
		setRootNode(newRoot);

		return removedEntry.get();
	} 

	// Removes an entry from the tree rooted at a given node.
	// Returns: The root node of the resulting tree; 
	//    if anEntry was in the tree, removedEntry's data is the entry that was removed from the tree; 
	//    otherwise, removedEntry's data is null.
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T anEntry, ReturnObject removedEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = anEntry.compareTo(rootData);

			if (comparison == 0) {// anEntry == rootData: we've found the entry to remove
				removedEntry.set(rootData); // save the data
				BinaryNode<T> newRoot = removeRoot(rootNode); // remove the current root and get the new root
				return newRoot;
			} else if (comparison < 0) { // anEntry < root entry; keep looking for our entry in the left subtree
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> leftSubtreeNewRoot = removeEntry(leftChild, anEntry, removedEntry);
				rootNode.setLeftChild(leftSubtreeNewRoot);
				return rootNode;
			} else {// anEntry > root entry; keep looking for our entry in the right subtree
				BinaryNode<T> rightChild = rootNode.getRightChild();
				BinaryNode<T> righSubtreeNewRoot = removeEntry(rightChild, anEntry, removedEntry);
				rootNode.setRightChild(righSubtreeNewRoot);
				return rootNode;
			} 
		} else { // rootNode is null
			return rootNode;
		}
	} 

	// Removes the root of a subtree
	// Returns the new root node of the subtree
	private BinaryNode<T> removeRoot(BinaryNode<T> rootNode) {
		
		// Case 1: rootNode has two children
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			
			// Find node with largest entry in left subtree (Option B from our notes)
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNodeInLeftSubtree = findLargest(leftSubtreeRoot);
			T largestDataInLeftSubtree = largestNodeInLeftSubtree.getData();

			// Replace entry in root
			rootNode.setData(largestDataInLeftSubtree);

			// Remove node with largest entry in left subtree 
			BinaryNode<T> newLeftSubtreeRoot = removeLargest(leftSubtreeRoot);
			
			// the removal above will change the leftSubtreeRoot, so update that
			rootNode.setLeftChild(newLeftSubtreeRoot);
		} 
		// Case 2: rootNode has one right child
		else if (rootNode.hasRightChild()) {
			rootNode = rootNode.getRightChild();	
		}
		 // Case 3: rootNode has left child 
		else if (rootNode.hasLeftChild()) { 
			rootNode = rootNode.getLeftChild();
		}
		// Case 4: rootNode is a leaf
		else {
			rootNode = null;
		}	

		return rootNode;
	} 
	
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			return findLargest(rootNode.getRightChild());
		} else {
			return rootNode;			
		}
	} 

	// Removes the node containing the largest entry in a given tree
	// Returns the root node of the revised tree (with the largest element removed)
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			BinaryNode<T> newRightChild = removeLargest(rightChild);
			rootNode.setRightChild(newRightChild);
			return rootNode;
		} else {
			return rootNode.getLeftChild();
		}
	} 

	
	
	
	private class ReturnObject {
		private T item;

		private ReturnObject(T entry) {
			item = entry;
		} 

		public T get() {
			return item;
		} 

		public void set(T entry) {
			item = entry;
		} 
	} 
	
}
