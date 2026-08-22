public class Account {

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;

    // Constructor
    public Account(int accountNumber, String name, int age,
                   double initialBalance, String accountType) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.status = "Active";
    }

    // Deposit
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    // Withdraw
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Main method for testing
    public static void main(String[] args) {

        Account account = new Account(
            1001,
            "Yatin Annam",
            19,
            5000.0,
            "Savings"
        );

        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Name: " + account.getName());
        System.out.println("Age: " + account.getAge());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Status: " + account.getStatus());

        System.out.println();

        System.out.println("Deposit 2000: " + account.deposit(2000));
        System.out.println("Balance: " + account.getBalance());

        System.out.println("Withdraw 1000: " + account.withdraw(1000));
        System.out.println("Balance: " + account.getBalance());

        System.out.println("Withdraw 10000: " + account.withdraw(10000));
        System.out.println("Balance: " + account.getBalance());
    }
}
