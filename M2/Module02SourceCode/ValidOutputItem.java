/* A ValidOutputItem is an object whose text representation contains
 * only letters, digits, or spaces.
 */

public class ValidOutputItem<T> {
	
	private T item;
	
	public ValidOutputItem() {
		this.item = null;
	}
	public ValidOutputItem(T item) {
		this();
		if(isValid(item)) {
			this.item = item;
		} 
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		if(isValid(item)) {
			this.item = item;
		}
	}
	private boolean isValid(T item) {
		String itemString = item.toString();
		boolean valid = true;
		for(int i=0; i<itemString.length() && valid; i++) {
			char currentCharacter = itemString.charAt(i);
			boolean charPassTest = Character.isLetterOrDigit(currentCharacter) || currentCharacter==' '; 
			if(!charPassTest) {
				valid = false;
			}
		}
		return valid;
	}
	
	public boolean isEmpty() {
		return item==null;
	}
	
	@Override
	public String toString() {
		if(isEmpty()) {
			return "<empty>";
		} else {
			return item.toString();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ValidOutputItem<?>) {
			ValidOutputItem<T> otherValidOutputItem = (ValidOutputItem<T>) obj;
			if(this.isEmpty() && otherValidOutputItem.isEmpty()) {
				return true;
			} else if(this.isEmpty() || otherValidOutputItem.isEmpty()) {
				return false;
			} else { // both not empty
				return this.item.equals(otherValidOutputItem.item);
			}
		} else {
			return false;
		}
	}
	
	public void processString() {
		// do something with the valid text representation of the item
	}
	
	
	
	
	
	
	
}
