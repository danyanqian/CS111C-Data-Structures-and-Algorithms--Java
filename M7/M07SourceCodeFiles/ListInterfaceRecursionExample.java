
public class ListInterfaceRecursionExample {

	public static void main(String[] args) {
		ListInterface<Integer> list = new AList<Integer>();
		//ListInterface<Integer> list = new LList<Integer>();
		
		/*
		System.out.println(list.contains(1));
		list.add(1);
		System.out.println(list.contains(1));
		System.out.println(list.contains(4));
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(4);
		System.out.println(list.contains(1));
		System.out.println(list.contains(4));
		System.out.println(list.contains(2));
		System.out.println(list.contains(5));
		*/
		
		System.out.println(listContains(list, 1));
		list.add(1);
		System.out.println(listContains(list, 1));
		System.out.println(listContains(list, 4));
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(4);
		System.out.println(listContains(list, 1));
		System.out.println(listContains(list, 4));
		System.out.println(listContains(list, 2));
		System.out.println(listContains(list, 5));
		
	}

	
	public static <T> boolean listContains(ListInterface<T> list, T item) {
		return listContains(list, item, 1, list.getLength()); 
	}
	
	private static <T> boolean listContains(ListInterface<T> list, T item, int start, int end) {
		if(start <= end) { // recursive case
			if(list.getEntry(start).equals(item)) {
				return true;
			} else {
				return listContains(list, item, start+1, end);
			}
		} else { // base case: start > end
			return false;
		}
	}
	
	
	
	
	

}
