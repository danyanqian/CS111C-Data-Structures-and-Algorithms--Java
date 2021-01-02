import java.util.*;

/**
 * A class that implements the ADT binary tree.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
	private BinaryNode<T> root;

	public BinaryTree() {
		root = null;
	} 

	public BinaryTree(T rootData) {
		root = new BinaryNode<>(rootData);
	} 

	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		initializeTree(rootData, leftTree, rightTree);
	} 

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		initializeTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
	} 

	public void setRootData(T rootData) {
		root.setData(rootData);
	} 

	public T getRootData() {
		if (isEmpty()) {
			throw new EmptyTreeException();
		} else {
			return root.getData();
		}
	} 

	public boolean isEmpty() {
		return root == null;
	} 

	public void clear() {
		root = null;
	} 

	public int getHeight() {
		int height = 0;
		if (root != null) {
			height = root.getHeight(); // uses the method in BinaryNode
		}
		return height;
	} 

	public int getNumberOfNodes() {
		int numberOfNodes = 0;
		if (root != null) {
			numberOfNodes = root.getNumberOfNodes(); // uses the method in BinaryNode
		}
		return numberOfNodes;
	} 

	protected void setRootNode(BinaryNode<T> rootNode) {
		root = rootNode;
	} 

	protected BinaryNode<T> getRootNode() {
		return root;
	} 

	private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<>(rootData);

		if ((leftTree != null) && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}

		if ((rightTree != null) && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		} 

		if ((leftTree != null) && (leftTree != this)) {
			leftTree.clear();
		}

		if ((rightTree != null) && (rightTree != this)) {
			rightTree.clear();
		}
	} 

	public int count(T anEntry) {
		// YOUR CODE HERE
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BinaryTree<?>) {
			BinaryTree<T> otherTree = (BinaryTree<T>) obj;
			//return equalsRecursive(root, otherTree.root);
			return equalsWithStack(root, otherTree.root);
		} else {
			return false;
		}
	}
	
	public boolean containsAsLeaf(T target) {
		return containsAsLeaf(root, target);
	}
	private boolean containsAsLeaf(BinaryNode<T> node, T target) {
		if(node==null) {
			return false;
		} else {
			if(node.getData().equals(target)) {
				return !node.hasLeftChild() && !node.hasRightChild();
			} else {
				return containsAsLeaf(node.getLeftChild(), target) || containsAsLeaf(node.getRightChild(), target);
			}
		}
	}
	private boolean equalsWithStack(BinaryNode<T> node1, BinaryNode<T> node2) {
		Stack<BinaryNode<T>> stack1 = new Stack<>();
		Stack<BinaryNode<T>> stack2 = new Stack<>();
		
		stack1.push(node1);
		stack2.push(node2);
		
		while(!stack1.isEmpty() && !stack2.isEmpty()) {
			BinaryNode<T> current1 = stack1.pop();
			BinaryNode<T> current2 = stack2.pop();
			
			String node1Output = current1==null ? "null" : current1.getData().toString();
			String node2Output = current2==null ? "null" : current2.getData().toString();
			System.out.println("Tree 1: " + node1Output + " Tree 2: " + node2Output);
			
			if(current1==null && current2==null) { // both null
				// proceed!
			} else if(current1==null || current2==null) { // only one null
				return false;
			} else { // both non-null
				if(!current1.getData().equals(current2.getData())) {
					return false;
				} else {
					stack1.push(current1.getLeftChild());
					stack2.push(current2.getLeftChild());
					
					stack1.push(current1.getRightChild());
					stack2.push(current2.getRightChild());
				}
			}
		}

		//return true; // this is enough
		return stack1.isEmpty() && stack2.isEmpty();
	}
	
	private boolean equalsRecursive(BinaryNode<T> node1, BinaryNode<T> node2) {
		String node1Output = node1==null ? "null" : node1.getData().toString();
		String node2Output = node2==null ? "null" : node2.getData().toString();
		System.out.println("Tree 1: " + node1Output + " Tree 2: " + node2Output);
		
		// base case 1
		if(node1==null && node2==null ) { 
			return true;
			
		// base case 2
		} else if(node1==null || node2==null) {
			return false;
			
		// solve part of the problem now
		} else if(!node1.getData().equals(node2.getData())) {
			return false;

		// solve the rest later: the recursive part			
		} else {
			return equalsRecursive(node1.getLeftChild(), node2.getLeftChild()) &&
					equalsRecursive(node1.getRightChild(), node2.getRightChild());
		}
	}



	
	public Iterator<T> getPreorderIterator() {
		return new PreorderIterator();
	} 

	public Iterator<T> getPostorderIterator() {
		return new PostorderIterator();
	} 

	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	} 

	public Iterator<T> getLevelOrderIterator() {
		return new LevelOrderIterator();
	} 

	public void iterativePreorderTraverse() {
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		if (root != null) {
			nodeStack.push(root);
		}
		BinaryNode<T> nextNode;
		while (!nodeStack.isEmpty()) {
			nextNode = nodeStack.pop();
			BinaryNode<T> leftChild = nextNode.getLeftChild();
			BinaryNode<T> rightChild = nextNode.getRightChild();

			// Push into stack in reverse order of recursive calls
			if (rightChild != null) {
				nodeStack.push(rightChild);
			}

			if (leftChild != null) {
				nodeStack.push(leftChild);
			}

			System.out.print(nextNode.getData() + " ");
		} 
	} 

	public void iterativeInorderTraverse() {
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		BinaryNode<T> currentNode = root;

		while (!nodeStack.isEmpty() || (currentNode != null)) {

			// Find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			} 

			// Visit leftmost node, then traverse its right subtree
			if (!nodeStack.isEmpty()) {
				BinaryNode<T> nextNode = nodeStack.pop();
				System.out.print(nextNode.getData() + " ");
				currentNode = nextNode.getRightChild();
			} 
		} 
	}

	private class PreorderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> nodeStack;

		public PreorderIterator() {
			nodeStack = new Stack<>();
			if (root != null) {
				nodeStack.push(root);
			}
		} 

		public boolean hasNext() {
			return !nodeStack.isEmpty();
		} 

		public T next() {
			BinaryNode<T> nextNode;

			if (hasNext()) {
				nextNode = nodeStack.pop();
				BinaryNode<T> leftChild = nextNode.getLeftChild();
				BinaryNode<T> rightChild = nextNode.getRightChild();

				// Push into stack in reverse order of recursive calls
				if (rightChild != null)
					nodeStack.push(rightChild);

				if (leftChild != null)
					nodeStack.push(leftChild);
			} else {
				throw new NoSuchElementException();
			}

			return nextNode.getData();
		} 

		public void remove() {
			throw new UnsupportedOperationException();
		} 
	} 

	private class PostorderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currentNode;

		public PostorderIterator() {
			nodeStack = new Stack<>();
			currentNode = root;
		} 

		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		} 

		public T next() {
			BinaryNode<T> leftChild, nextNode = null;
			
			// Find current node's leftmost leaf
			while (currentNode != null) {
				nodeStack.push(currentNode);
				leftChild = currentNode.getLeftChild();
				if (leftChild == null) {
					currentNode = currentNode.getRightChild();
				} else {
					currentNode = leftChild;
				}
			} 
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop(); // next in the traversal
				BinaryNode<T> parent = null; // find the parent
				if (!nodeStack.isEmpty()) {
					parent = nodeStack.peek();
					if (nextNode == parent.getLeftChild()) { // if the parent's left is done being processed, the parent's right still needs to be looked at
						currentNode = parent.getRightChild();
					} else { // the parent's left and right have already been reviewed
						currentNode = null;
					}
				} else { // node stack is empty
					currentNode = null;
				}
			} else {
				throw new NoSuchElementException();
			} 
			return nextNode.getData();
		} 

		public void remove() {
			throw new UnsupportedOperationException();
		} 
	} 

	private class InorderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currentNode;

		public InorderIterator() {
			nodeStack = new Stack<>();
			currentNode = root;
		} 

		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}

		public T next() {
			BinaryNode<T> nextNode = null;

			// Find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			} 

			// Get leftmost node, then move to its right subtree
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				currentNode = nextNode.getRightChild();
			} else {
				throw new NoSuchElementException();
			}

			return nextNode.getData();
		} 

		public void remove() {
			throw new UnsupportedOperationException();
		} 
	} 

	private class LevelOrderIterator implements Iterator<T> {
		private Queue<BinaryNode<T>> nodeQueue;

		public LevelOrderIterator() {
			nodeQueue = new LinkedList<>();
			if (root != null) {
				nodeQueue.add(root);
			}
		} 

		public boolean hasNext() {
			return !nodeQueue.isEmpty();
		} 

		public T next() {
			BinaryNode<T> nextNode;

			if (hasNext()) {
				nextNode = nodeQueue.remove();
				BinaryNode<T> leftChild = nextNode.getLeftChild();
				BinaryNode<T> rightChild = nextNode.getRightChild();

				// Add to queue in order of recursive calls
				if (leftChild != null) {
					nodeQueue.add(leftChild);
				}

				if (rightChild != null) {
					nodeQueue.add(rightChild);
				}
			} else {
				throw new NoSuchElementException();
			}

			return nextNode.getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		} 
	} 
	
	
	public void recursiveInorderTraverse() {
		recursiveInorderTraverse(root);
		System.out.println();
	} 

	private void recursiveInorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			recursiveInorderTraverse(node.getLeftChild());
			System.out.print(node.getData() + " ");
			recursiveInorderTraverse(node.getRightChild());
		}
	} 
	
	public void recursivePreorderTraverse() {
		recursivePreorderTraverse(root);
		System.out.println();
	} 

	private void recursivePreorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			System.out.print(node.getData()+ " ");
			recursivePreorderTraverse(node.getLeftChild());
			recursivePreorderTraverse(node.getRightChild());
		} 
	} 
	
	public void recursivePostorderTraverse() {
		recursivePostorderTraverse(root);
		System.out.println();
	} 

	private void recursivePostorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			recursivePostorderTraverse(node.getLeftChild());
			recursivePostorderTraverse(node.getRightChild());
			System.out.print(node.getData() + " ");
		} 
	} 
	
	
} 
