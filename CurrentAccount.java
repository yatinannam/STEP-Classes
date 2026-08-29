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

    public void withdrawWithOverdraft(double amount, int pin)
            throws InvalidAmountException,
            InsufficientBalanceException,
            MinimumBalanceViolationException,
            InactiveAccountException,
            InvalidPinException {
        double availableBalance = getBalance() + overdraftLimit;
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }
        if (amount > availableBalance) {
            throw new InsufficientBalanceException("Withdrawal exceeds available balance including overdraft limit");
        }
        withdraw(amount, pin);
    }

    public void displayCurrentAccount() {
        System.out.println("Current Account Details");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Name: " + getName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}
