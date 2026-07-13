package Assignment3;

public class savingsAccount extends Account{

    private double dailyWithdrawal = 0; // tracks how much withdrawn
    private static final double DailyLimit = 500.0;

    public savingsAccount(String ownerName, String accountNumber, double balance) {
        super(ownerName, accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        if (dailyWithdrawal + amount > DailyLimit) {
            return false; // exceeds daily limit
        }

        if (amount > getBalance()) {
            return false; // not enough balance
        }

        setBalance(getBalance() - amount);
        dailyWithdrawal += amount;
        return true;
    }
}
