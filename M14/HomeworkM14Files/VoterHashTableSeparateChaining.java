
public class VoterHashTableSeparateChaining {

	private Node<Voter>[] hashTable;
	private int tableSize;

	public VoterHashTableSeparateChaining(int size) {
		hashTable = (Node<Voter>[]) new Node[size];
		tableSize = size;
	}
	
	public int getHashTableLocation(int voterID) {
		// YOUR CODE HERE
		return voterID%tableSize;
	}

	public boolean addVoter(Voter voterToAdd) {
		// YOUR CODE HERE
//		int hashCode = getHashTableLocation(voterToAdd.getId());
//		Node<Voter> voterNode = hashTable[hashCode];
//		Node<Voter> newVoterNode = new Node(voterToAdd, voterNode);
//		hashTable[hashCode] = newVoterNode;

		// completing extra credit.
		int hashCode = getHashTableLocation(voterToAdd.getId());
		Node<Voter> voterNode = hashTable[hashCode];
		// if null/empty:  add a single node
		if (voterNode == null){
			Node<Voter> newVoterNode = new Node(voterToAdd, null);
			hashTable[hashCode] = newVoterNode;
			return true;
		}
		// when node is not empty
		// loop through the linked nodes, if the id match, return false
		while (voterNode != null){
			if (voterNode.data.getId() == voterToAdd.getId()) {
				return false;
			}
			if (voterNode.next == null) {
				break;
			}
			voterNode = voterNode.next;
		}
		// when no match found, insert at tail
		Node<Voter> newVoterNode = new Node(voterToAdd, null);
		voterNode.next = newVoterNode;
		return true;
	}

	public Voter getVoter(int voterID) {
		// YOUR CODE HERE
		int hashCode = getHashTableLocation(voterID);
		Node<Voter> voterNode = hashTable[hashCode];
		while (voterNode != null) {
			if (voterNode.data.getId() == voterID) {
				return voterNode.data;
			} else {
				voterNode = voterNode.next;
			}
		}
		return null;
	}

	public void printTable() {
		for (int i = 0; i < tableSize; i++) {
			if (hashTable[i] != null) {
				System.out.print("Location " + i + ": ");
				Node<Voter> current = hashTable[i];
				while (current != null && current.next != null) {
					System.out.print(current.data.getName() + " -> ");
					current = current.next;
				}
				System.out.println(current.data.getName());
			}
		}
	}

}
