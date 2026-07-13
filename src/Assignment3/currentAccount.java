package Assignment3;

public class currentAccount extends Account {

    private static final double overLimit = 200.0;

    public currentAccount(String ownerName, String accountNumber, double balance) {
        super(ownerName, accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        double newBalance = getBalance() - amount;

        // allows to go negative, but not below 200
        if (newBalance < overLimit) {
            return false;
        }
        setBalance(newBalance);
        return true;
    }

}
