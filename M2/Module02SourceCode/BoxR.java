
public class BoxR {
	
	private Object thing;
	private int thingCount;
	
	public BoxR(Object firstThing) {
		this.thing = firstThing;
		this.thingCount = 1;
	}
	
	public Object getContents() {
		return thing;
	}
	public int getCount() {
		return thingCount;
	}
	
	public void replaceContents(Object newThing) {
		this.thing = newThing;
		thingCount++;
	}
	
	@Override
	public String toString() {
		return thing.toString() + " (item " + thingCount + ")";
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof BoxR) {
			BoxR otherBoxR = (BoxR) other;
			
			boolean sameThing = this.thing.equals(otherBoxR.thing);
			boolean sameCount = this.thingCount==otherBoxR.thingCount;
			
			return sameThing && sameCount;
			
		} else {
			return false;
		}
	}

}
