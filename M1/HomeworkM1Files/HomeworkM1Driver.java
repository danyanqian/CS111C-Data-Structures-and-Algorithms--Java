import java.util.*;

public class HomeworkM1Driver {

	public static void main(String[] args) {
		
		// Store objects to use in testing
		Store groceryStore1 = new Store("Foods R Us", "Smallerville");
		Store groceryStore2 = new Store("Foods R Us", "Unioner City");
		Store electronicStore = new Store("ElectroFun", "Unioner City");
		Store bookStore = new Store("The Bookworm", "Eastchester");
		
		// receipt objects to use in testing
		Receipt receipt1 = new Receipt(groceryStore1, 10, 99.47, "d4x1m4m2");
		Receipt receipt2 = new Receipt(groceryStore1, .99, "vdevx1vv");
		Receipt receipt3 = new Receipt(groceryStore2, 21, 219.42, "d4g1b4gb");
		Receipt receipt4 = new Receipt(electronicStore, 599.99, "adcz3ash");
		DigitalReceipt digitalReceipt1 = new DigitalReceipt(bookStore, 50, 525.25, "brebfp2s", "sir.reads.a.lot@books.com");
		DigitalReceipt digitalReceipt2 = new DigitalReceipt(bookStore, 1, 4.87, "ddc4fqqq", "mark.darcy@gmail.abc");
		DigitalReceipt digitalReceipt3 = new DigitalReceipt(electronicStore, 2, 139.21, "xdc2m4rv", "dr.frankenstein@monster.com");
		DigitalReceipt digitalReceipt4 = new DigitalReceipt(groceryStore1, 18, 78.41, "sbbbf41n", "t@iced.com");
		Receipt[] receipts = {receipt1, receipt2, receipt3, receipt4, digitalReceipt1, digitalReceipt2, digitalReceipt3, digitalReceipt4};	
		
		System.out.println("******************************Testing toString");
		for(Receipt receipt : receipts) {
			System.out.println(receipt);
		}
		
		
		System.out.println("\n\n******************************Testing use of Store object");
		System.out.println("Should show \"Unioner City\":");
		System.out.println("\t"+receipt3);
		System.out.println("\t"+receipt4);
		System.out.println("\t"+digitalReceipt3);
		
		System.out.println("Now should show \"Union City\":");
		groceryStore2.setCity("Union City");
		electronicStore.setCity("Union City");
		System.out.println("\t"+receipt3);
		System.out.println("\t"+receipt4);
		System.out.println("\t"+digitalReceipt3);
		
		
		System.out.println("\n\n******************************Testing equals");
		testEqualsMethod(receipts);

		
		System.out.println("\n\n******************************Testing Receipt Criteria");
		testReceiptCriteria('x', 'm', 2, receipt1, true, "exactly 2 m's (firstChar) after x (secondChar)");
		testReceiptCriteria('e', 'v', 3, receipt2, true, "exactly 3 v's (secondChar) after e (firstChar); also 1 extra v before e- that doesn't affect the criteria");
		testReceiptCriteria('g', 'b', 2, receipt3, true, "exactly 2 b's (secondChar) after the first g (firstChar); also a second g- that doesn't affect the criteria");
		testReceiptCriteria('b', 'z', 0, digitalReceipt1, true, "exactly 0 z's (secondChar) after the first b (firstChar)");

		testReceiptCriteria('z', 'a', 2, receipt4, false, "only 1 a (firstChar) after the z- the other a comes before the z");
		testReceiptCriteria('f', 'q', 2, digitalReceipt2, false, "3 q's (secondChar) after the f (firstChar) instead of 2");
		testReceiptCriteria('k', 'm', 1, digitalReceipt3, false, "no k (firstChar)");
		testReceiptCriteria('b', 'b', 3, digitalReceipt4, false, "the first b is the firstChar; there are then only 2 *other b's* (that are the second char) after that instead of 3");


		System.out.println("\n\n******************************Testing e-Mail Validator (Extra Credit)");
		testEmailValidator(digitalReceipt1.getEmail(), true, "at least one letter before the @ and exactly three characters after the last period");
		testEmailValidator(digitalReceipt2.getEmail(), true, "at least one letter before the @ and exactly three characters after the last period");
		testEmailValidator(digitalReceipt3.getEmail(), true, "at least one letter before the @ and exactly three characters after the last period");
		testEmailValidator(digitalReceipt4.getEmail(), true, "at least one letter before the @ and exactly three characters after the last period");

		testEmailValidator("@abc.xyz", false, "no characters before @");
		testEmailValidator("abc.com", false, "no @");
		testEmailValidator("j@com", false, "no period");
		testEmailValidator("jm@gmail.comm", false, "4 chars after the last period");
		testEmailValidator("jm@gmail.hi", false, "2 chars after the last period");
		testEmailValidator("jm@gmail.z", false, "1 char after period");
		testEmailValidator("abcdef", false, "no @ or period");
		testEmailValidator("!@abc.def", false, "the character before the @ is not a letter");
		

	}
	
	public static void testEqualsMethod(Receipt[] receipts) {
		List<Receipt> receiptList = new ArrayList<Receipt>();
		for(Receipt receipt : receipts) {
			Receipt copyReceipt;
			if(receipt instanceof DigitalReceipt) {
				copyReceipt = new DigitalReceipt(receipt.getStore(), receipt.getNumberOfItems(), receipt.getTotal(), receipt.getReceiptID(), ( (DigitalReceipt) receipt).getEmail());
			} else {
				copyReceipt = new Receipt(receipt.getStore(), receipt.getNumberOfItems(), receipt.getTotal(), receipt.getReceiptID());
			}
			receiptList.add(copyReceipt);
		}
		boolean passContainsTests = true;
		for(Receipt receipt : receipts) {
			boolean contains = receiptList.contains(receipt);
			if(contains!=true) {
				System.out.println("***Test failed: equals method failed when testing equality of recept: " + receipt);
				passContainsTests = false;
			}
		}
		if(passContainsTests) {
			System.out.println("All contains tests passed.");
		}
		
	}
	
	public static void testReceiptCriteria(char firstChar, char secondChar, int timesAfter, Receipt receipt, boolean expectedResult, String testDescription) {
		boolean actualResult = receipt.meetsReceiptCriteria(firstChar,  secondChar,  timesAfter);
		System.out.print("Expected=" + expectedResult + "\tActual=" + actualResult +
				"\tID=" + receipt.getReceiptID() + "  firstChar=" + firstChar + "  secondChar=" + secondChar +
				"  timesAfter=" + timesAfter);
		if(actualResult!=expectedResult) {
			System.out.print("\t***Test failed: " + testDescription);
		}
		System.out.println();
	}
	
	public static void testEmailValidator(String email, boolean expectedResult, String testDescription) {
		boolean actualResult = DigitalReceipt.validateEmail(email);
		System.out.print("Expected=" + expectedResult + "\tActual=" + actualResult +
				"\temail=" + email);
		if(actualResult!=expectedResult) {
			System.out.print("\t\t***Test failed: " + testDescription);
		}
		System.out.println();
	}

}
