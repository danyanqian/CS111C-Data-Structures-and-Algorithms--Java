
public class Person implements Comparable<Person> {

	private String firstName, lastName;
	private int id;
	
	public Person(String firstName, String lastName, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " (" + id + ")";
	}

	@Override
	public int compareTo(Person other){
		int lastNameComparison = this.lastName.compareToIgnoreCase(other.lastName);
		if (lastNameComparison == 0){
			int firstNameComparison = this.firstName.compareToIgnoreCase(other.firstName);
			if (firstNameComparison == 0) { // last and first names equal, compare by ID
				return Integer.compare(this.id, other.id);
			} else {
				return firstNameComparison; // last name equal, different first names
			}
		} else {
			return lastNameComparison;// different last names
		}

	}
	
}
