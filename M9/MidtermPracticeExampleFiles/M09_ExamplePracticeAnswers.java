@SuppressWarnings({"unchecked", "rawtypes"})
public class M09_ExamplePracticeAnswers {
	// NOTE: In this code, I sometimes used unparameterized (raw) bags and lists. Other times, I use
	// Integers or Strings. Note that the actual correct way to write some of these methods would be as
	// generic methods, but this is beyond the scope of how we use generics in our course. Instead, I
	// want you to focus on the logic of using the bags and lists. 
	
	
	/* CLIENT LEVEL CODE */
	
	public static ListInterface mergeListInterface(ListInterface listA, ListInterface listB) {
		ListInterface mergeList = new AList(); // or LList
		
		int i, j;
		for(i=1, j=1; i<=listA.getLength() && j<=listB.getLength(); i++, j++) {
			mergeList.add(listA.getEntry(i));
			mergeList.add(listB.getEntry(j));
		}
		while(i<=listA.getLength()) {
			mergeList.add(listA.getEntry(i));
			i++;
		}
		while(j<=listB.getLength()) {
			mergeList.add(listB.getEntry(j));
			j++;
		}
		return mergeList;
	}
	
	public static BagInterface<Integer> mergeBagInterfaces(BagInterface<Integer> bagA, BagInterface<Integer> bagB) {
		BagInterface<Integer> mergeBag = new LinkedBag<Integer>(); // or these could be ArrayBag
		BagInterface<Integer> copyBagA = new LinkedBag<Integer>();
		BagInterface<Integer> copyBagB = new LinkedBag<Integer>();
		
		while(!bagA.isEmpty()) {
			Integer element = bagA.remove();
			copyBagA.add(element);
			mergeBag.add(element);
		}
		
		while(!bagB.isEmpty()) {
			Integer element = bagB.remove();
			copyBagB.add(element);
			mergeBag.add(element);
		}
		
		while(!copyBagA.isEmpty()) {
			bagA.add(copyBagA.remove());
		}
		
		while(!copyBagB.isEmpty()) {
			bagB.add(copyBagB.remove());
		}
		
		return mergeBag;
	}
	
	public static boolean containsDuplicates(ListInterface<String> list) {
		for(int i=1; i<=list.getLength(); i++) {
			String current = list.getEntry(i);
			for(int j=i+1; j<=list.getLength(); j++) {
				String comparison = list.getEntry(j);
				if(current.equals(comparison)) {
					return true;
				}
			}
		}
		return false;
	}
	

	public static int getSize(BagInterface bag) {
		int size = 0;
		BagInterface copyBag = new ArrayBag(); // or LinkedBag
		while(!bag.isEmpty()) {
			copyBag.add(bag.remove());
			size++;
		}
		while(!copyBag.isEmpty()) {
			bag.add(copyBag.remove());
		}
		return size;
	}
	
	/* CODING WITH NODES */

	public static boolean allContainChar(Node<String> firstNode, char targetChar) {
		boolean allContain = true;
		Node<String> current = firstNode;
		while(current!=null && allContain) {
			String currentString = current.data;
			if(currentString.indexOf(targetChar)<0) {
				allContain = false;
			}
			current = current.next;
		}
		return allContain;
	}
	

	public static boolean hasNeighborRepeats(Node<Integer> firstNode) {
		if(firstNode==null) {
			return false;
		} else if(firstNode.next==null) {
			return false;
		} else {
			Node previous = firstNode;
			Node current = firstNode.next;
			while(current!=null) {
				if(previous.getData().equals(current.getData())) {
					return true;
				}
				current = current.next;
				previous = previous.next;
			}
			return false;
		}
	}

	
	public static int[] merge(Node<Integer> firstNode, int[] array) {
		int chainSize = 0;
		Node<Integer> current = firstNode;
		while(current!=null) {
			chainSize++;
			current = current.next;
		}
		
		int[] mergeArray = new int[chainSize+array.length];
		int mergePosition = 0;
		
		current = firstNode;
		int arrayPosition = 0;
		while(mergePosition < mergeArray.length) {
			if(current!=null) {
				mergeArray[mergePosition] = current.data;
				mergePosition++;
				current = current.next;
			}
			
			if(arrayPosition<array.length && mergePosition < mergeArray.length) {
				mergeArray[mergePosition] = array[arrayPosition];
				mergePosition++;
				arrayPosition++;
			}
		}
		
		return mergeArray;
	}

	/* RECURSIVE METHODS */
	
	public static int countChars(String word, char ch, int index) {
		int count = 0;
		if(index < word.length()) {
			int currentMatch = 0;
			if(word.charAt(index)==ch) {
				currentMatch++;
			}
			count = currentMatch + countChars(word, ch, index+1);
		}
		return count;
	}
	
	public static int size(Node firstNode) {
		int size = 0;
		if(firstNode!=null) {
			size = 1 + size(firstNode.next);
		}
		return size;
	}
	

	private static int countGreaterThanHelper(int[] array, int target, int index) {
		if(index<array.length) {
			if(array[index]>target) {
				return 1 + countGreaterThanHelper(array, target, index+1);
			} else {
				return 0 + countGreaterThanHelper(array, target, index+1);
			}
		} else {
			return 0;
		}
	}
}
