import java.util.Iterator;

public class BinaryTreeEqualsTester {

	public static void main(String[] args) {

		BinaryTree<Integer> numberTree1A = createTree1();
		BinaryTree<Integer> numberTree1B = createTree1();
		BinaryTree<Integer> numberTree2 = createTree2();
		BinaryTree<Integer> numberTree3 = createTree3();

		System.out.println(numberTree1A.equals(numberTree1B));
		

	}

	private static BinaryTree<Integer> createTree1() {
		// leaves
		BinaryTree<Integer> tree6 = new BinaryTree<Integer>(6);
		BinaryTree<Integer> tree11 = new BinaryTree<Integer>(11);
		BinaryTree<Integer> tree14 = new BinaryTree<Integer>(14);
		BinaryTree<Integer> tree5 = new BinaryTree<Integer>(5);
		BinaryTree<Integer> tree12 = new BinaryTree<Integer>(12);

		// next level up from leaves
		BinaryTree<Integer> tree7 = new BinaryTree<Integer>(7, tree6, tree11);
		BinaryTree<Integer> tree9 = new BinaryTree<Integer>(9, tree14, null);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(2, tree5, null);

		// next level up
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(1, tree7, tree9);
		BinaryTree<Integer> tree3 = new BinaryTree<Integer>(3, tree2, tree12);

		// root
		return new BinaryTree<Integer>(4, tree1, tree3);

	}

	private static BinaryTree<Integer> createTree2() {
		// leaves
		BinaryTree<Integer> tree6 = new BinaryTree<Integer>(6);
		BinaryTree<Integer> tree11 = new BinaryTree<Integer>(11);
		BinaryTree<Integer> tree14 = new BinaryTree<Integer>(14);
		BinaryTree<Integer> tree5 = new BinaryTree<Integer>(5);
		BinaryTree<Integer> tree12 = new BinaryTree<Integer>(12);

		// next level up from leaves
		BinaryTree<Integer> tree7 = new BinaryTree<Integer>(7, tree6, tree11);
		BinaryTree<Integer> tree9 = new BinaryTree<Integer>(9, null, tree14); // order switched
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(2, tree5, null);

		// next level up
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(1, tree7, tree9);
		BinaryTree<Integer> tree3 = new BinaryTree<Integer>(3, tree2, tree12);

		// root
		return new BinaryTree<Integer>(4, tree1, tree3);

	}
	
	private static BinaryTree<Integer> createTree3() {
		// leaves
		BinaryTree<Integer> tree6 = new BinaryTree<Integer>(6);
		//BinaryTree<Integer> tree11 = new BinaryTree<Integer>(11);
		BinaryTree<Integer> tree14 = new BinaryTree<Integer>(14);
		BinaryTree<Integer> tree5 = new BinaryTree<Integer>(5);
		BinaryTree<Integer> tree12 = new BinaryTree<Integer>(12);

		// next level up from leaves
		BinaryTree<Integer> tree7 = new BinaryTree<Integer>(7, tree6, null); // instead of tree11
		BinaryTree<Integer> tree9 = new BinaryTree<Integer>(9, tree14, null);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(2, tree5, null);

		// next level up
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(1, tree7, tree9);
		BinaryTree<Integer> tree3 = new BinaryTree<Integer>(3, tree2, tree12);

		// root
		return new BinaryTree<Integer>(4, tree1, tree3);

	}
}
