public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String name, int age,
            double initialBalance, double overdraftLimit) {
        super(accountNumber, name, age, initialBalance, "Current");
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit >= 0) {
            this.overdraftLimit = overdraftLimit;
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        double availableBalance = getBalance() + overdraftLimit;
        if (amount > availableBalance) {
            return false;
        }

        setBalance(getBalance() - amount);
        return true;
    }

    public void displayCurrentAccount() {
        System.out.println("Current Account Details");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Name: " + getName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}
