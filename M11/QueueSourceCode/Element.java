
public class Element implements Comparable<Element> {
	
	private String data;
	private int priority; // lower integers have higher priority
	
	public Element(String data, int priority) {
		this.data = data;
		this.priority = priority;
	}
	
	public String getData() {
		return data;
	}
	
	public int getPriority() {
		return priority;
	}
	
	@Override
	public String toString() {
		return priority + "-" + data;
	}
	
	@Override
	public int compareTo(Element other) {
		return Integer.compare(this.priority, other.priority);
	}

}
