import java.text.NumberFormat;

public class Receipt {
    private Store store;
    private int numberOfItems;
    private double totalMoneySpent;
    private String receiptID;

    private static final int UNDECLARED_NUMBER_OF_ITEMS_PURCHASED = 1;
    public Receipt(Store store, int numberOfItems, double totalMoneySpent, String receiptID){
        this.store = store;
        this.numberOfItems = numberOfItems;
        this.totalMoneySpent = totalMoneySpent;
        this.receiptID = receiptID;
    }

    public Receipt(Store store, double totalMoneySpent, String receiptID){
        this(store, UNDECLARED_NUMBER_OF_ITEMS_PURCHASED, totalMoneySpent, receiptID);
    }

    public void setNumberOfItems(int numberOfItems) {
        if(numberOfItems<0) {
            System.out.println("Error- number of items cannot be negative.");
        }
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public Store getStore(){
        return this.store;
    }

    public double getTotal(){
        return this.totalMoneySpent;
    }

    public String getReceiptID(){
        return this.receiptID;
    }

    @Override
    public String toString(){
        String storeName = store.toString();
        String moneyString = NumberFormat.getCurrencyInstance().format(totalMoneySpent);
        String itemString;
        if (numberOfItems > 1) {
            itemString = "; Number of Items: ";
        } else {
            itemString = "; Number of Item: ";
        }
        return "Store: " + storeName +
                itemString + numberOfItems +
                "; Total money spent: " + moneyString +
                "; Receipt ID: " + receiptID;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Receipt){
            Receipt otherReceipt = (Receipt) other;
            return this.store.equals(otherReceipt.store) &&
                    this.numberOfItems == otherReceipt.numberOfItems &&
                    this.totalMoneySpent == otherReceipt.totalMoneySpent &&
                    this.receiptID.equalsIgnoreCase(otherReceipt.receiptID);
        } else {
            return false;
        }
    }

    public boolean meetsReceiptCriteria(char firstCharacter, char secondCharacter, int numberTimesSecondAppearsAfterFirst){
        // check the id contains the first character
        int firstCharacterIndex = receiptID.indexOf(firstCharacter);
        if (firstCharacterIndex == -1 ){
            return false;
        }

        // check second character appears exactly the specified number of times after the first character appears
        String stringAfterFirst = receiptID.substring(firstCharacterIndex+1);
        int secondCharCount = 0;
        for (int i = 0; i < stringAfterFirst.length(); i++) {
            if (stringAfterFirst.charAt(i) == secondCharacter) {
                secondCharCount++;
            }
        }
        return secondCharCount == numberTimesSecondAppearsAfterFirst;
    }



}
