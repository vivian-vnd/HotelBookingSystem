package Assignment3;

public abstract class Account {
    private String ownerName;
    private String accountNumber;
    private double balance;

    private static int totalAccounts = 0; // tracks how many account
    public Account(String name, String accountNumber, double balance) {
        this.ownerName = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        totalAccounts++;
    }

    // Getters
    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    protected void setBalance(double balance) { // child class can modify
        this.balance = balance;
    }

    public abstract boolean withdraw(double amount); // each account will implement its own rule

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [ " + ownerName + " | " + accountNumber + " | €" + balance + " ]";
    }


}
