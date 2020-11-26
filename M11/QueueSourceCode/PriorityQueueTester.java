
public class PriorityQueueTester {

	public static void main(String[] args) {
		PriorityQueueInterface<Element> priorityQueue = new LinkedPriorityQueue<Element>();
		
		Element[] elements = {
				new Element("apple", 2),
				new Element("dog", 1),
				new Element("banana", 1),
				new Element("friend", 2),
				new Element("elephant", 2),
				new Element("anger", 1),
				new Element("anger", 3),
				new Element("colorful", 2),
				new Element("surprise", 0)
		};
		
		System.out.println("\n****************************ADDING");
		for(Element element : elements) {
			System.out.println("\nAdding " + element);
			priorityQueue.add(element);
			System.out.println(priorityQueue);
			
		}
		
		System.out.println("\n****************************REMOVING");
		while(!priorityQueue.isEmpty()) {
			System.out.println("Removing: " + priorityQueue.remove());
			System.out.println(priorityQueue);
		}

		
	}

}
