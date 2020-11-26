import java.util.EmptyStackException;

public class StackInfinitePush<T> implements StackInterface<T> {
	
	private DequeInterface<T> deque; // front of the deque is the top of the stack
	private int capacity;
	private int size;
	
	
	public StackInfinitePush(int capacity) {
		deque = new ArrayDeque<T>(); // could also be LinkedDeque<T>
		this.capacity = capacity;
		this.size = 0;
	}

	public void push(T newEntry) {
		deque.addToFront(newEntry);
		size++;
		
		if(size>capacity) {
			deque.removeBack(); // dropping from the bottom of the stack (oldest entry)
			size--;
		}
	}

	public T pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		size--;
		return deque.removeFront();
	}

	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return deque.getFront();
	}
	
	public boolean isEmpty() {
		return deque.isEmpty();
	}
	
	public void clear() {
		deque.clear();
		size = 0;
	}

	
	
}
