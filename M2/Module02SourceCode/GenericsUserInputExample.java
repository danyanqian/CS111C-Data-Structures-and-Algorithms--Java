import java.util.*;

public class GenericsUserInputExample {

	public static void main(String[] args) {
		
		ArrayList<String> wordList = new ArrayList<String>(); // the right hand side "String" is optional, the <> MUST be included
		wordList.add("hello");
		//wordList.add(Integer.valueOf(4));
		//wordList.add(new Student("Jessica", "Masters", 43));
		wordList.add("bye");
		
		String firstItem = wordList.get(0);
		String secondItem = wordList.get(1);	
		
		readUserNames();
		readUserNumbers();
	}
	
	public static void readUserNames() {

		Scanner scan = new Scanner(System.in);

		ArrayList<String> nameList = new ArrayList<String>();

		System.out.println("Enter names or \"quit\" to quit:");
		String userInputName = scan.nextLine();

		while (!userInputName.equalsIgnoreCase("quit")) {
			nameList.add(userInputName);

			System.out.println("Enter names or \"quit\" to quit:");
			userInputName = scan.nextLine();
		}

		Collections.sort(nameList);
		for (String name : nameList) {
		//for(int i=0; i<nameList.size(); i++) { // equivalent for-loop
		//	String name = nameList.get(i);
			
			System.out.println(name.toUpperCase());
		}
	}

	public static void readUserNumbers() {
		Scanner scan = new Scanner(System.in);

		ArrayList<Integer> numberList = new ArrayList<Integer>();

		System.out.println("How many numbers?");
		int numberNumbers = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < numberNumbers; i++) {
			int userNumber = Integer.parseInt(scan.nextLine());
			numberList.add(userNumber);
		}

		int total = 0;
		//for (Integer num : numberList) { // enhanced for-loop or for-each loop equivalent
		for(int i=0; i<numberList.size(); i++) {
			int num = numberList.get(i);
			total += num;
		}
		double average = ((total)*1.0) / ((double)(numberList.size())); // use a cast (double) or *1.0 to one of the operands
		System.out.println("The average is " + average);

	}

	
	
	
	
}
