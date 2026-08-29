public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(int accountNumber, String name, int age,
            double initialBalance, double interestRate) {
        super(accountNumber, name, age, initialBalance, "Savings");
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate >= 0) {
            this.interestRate = interestRate;
        }
    }

    public void applyInterest() throws InvalidAmountException, InactiveAccountException {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    public void displaySavingsAccount() {
        System.out.println("Savings Account Details");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Name: " + getName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}
