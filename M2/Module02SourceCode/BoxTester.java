import java.util.*;

public class BoxTester {

	public static void main(String[] args) {

		BoxR numbersBoxNotReally = new BoxR(Integer.valueOf(4));  //new Integer(4) as used in the video has been deprecated; use Integer.valueOf(4) instead
		numbersBoxNotReally.replaceContents(Integer.valueOf(5));
		
		//numbersBoxNotReally.replaceContents(new String("hello"));
		
		Integer currentContents = (Integer) numbersBoxNotReally.getContents();
		
		Box<Integer> numberBox = new Box<Integer>(Integer.valueOf(1));
		int contents = numberBox.getContents();
		numberBox.replaceContents(4);
		
		Box<String> wordBox1 = new Box<String>("hello");
		String wordContents = wordBox1.getContents();
		wordBox1.replaceContents("bye");
		
		Box<String> wordBox2 = new Box<String>("hi");
		wordBox2.replaceContents("bye");

		ArrayList<Box<Integer>> listOfNumberBoxes = new ArrayList<Box<Integer>>();


		System.out.println("equal boxes? " + wordBox1.equals(wordBox2));
		System.out.println("equal boxes? " + wordBox1.equals(numberBox));
		
	
	}

}
