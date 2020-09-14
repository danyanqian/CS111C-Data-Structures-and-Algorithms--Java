public class DigitalReceipt extends Receipt{
    private String emailAddress;

    public DigitalReceipt (Store store, int numItemPurchased, double totalMoneySpent,String receiptID,String emailAddress){
        super(store, numItemPurchased,totalMoneySpent,receiptID);
        this.emailAddress = emailAddress;
    }

    public String getEmail(){
        return this.emailAddress;
    }

    public static boolean validateEmail(String email){
        // check if it contains the '@' symbol or '.' symbol
        int atIndex = email.indexOf('@');
        int lastPeriodIndex = email.lastIndexOf('.');
        if (atIndex == -1 || lastPeriodIndex == -1) {
            return false;
        }

        // check there is at least one letter character before '@'
        String emailBeforeAt = email.substring(0, atIndex);
        boolean hasLettersBeforeAt = false;
        for (char ch : emailBeforeAt.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasLettersBeforeAt = true;
                break;
            }
        }

        // get che number of characters after the last period
        int numCharBeforeLastPeriod = email.substring(lastPeriodIndex+1).length();

        return hasLettersBeforeAt && numCharBeforeLastPeriod == 3;
    }


}
