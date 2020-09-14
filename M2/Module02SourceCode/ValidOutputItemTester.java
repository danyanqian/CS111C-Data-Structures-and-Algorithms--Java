
public class ValidOutputItemTester {

	public static void main(String[] args) {
		
		ValidOutputItem<String> stringItem = new ValidOutputItem<String>("a,b,c");
		System.out.println(stringItem);
		stringItem.setItem("abc123");
		System.out.println(stringItem);
		
		
		Student student = new Student("Sally", "Studier", 123);
		ValidOutputItem<Student> studentItem = new ValidOutputItem<Student>();
		studentItem.setItem(student);
		System.out.println(studentItem);
		
		ValidOutputItem<Integer> integerItem = new ValidOutputItem<Integer>(4);
		System.out.println(integerItem);
		
	}

}
