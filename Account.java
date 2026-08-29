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
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
            if (this.balance == 0) {
                this.status = "Inactive";
            } else if ("Closed".equals(this.status)) {
                this.status = "Closed";
            } else {
                this.status = "Active";
            }
        }
    }

    public void setAccountType(String accountType) {
        if (accountType != null && !accountType.trim().isEmpty()) {
            this.accountType = accountType.trim();
        }
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status.trim();
        }
    }

    public void closeAccount() {
        this.status = "Closed";
    }

    public void reactivateAccount() {
        if (this.balance > 0) {
            this.status = "Active";
        }
    }

    public void displayAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Status: " + status);
    }

    // Main method for testing
    public static void main(String[] args) {

        Account account = new Account(
                1001,
                "Yatin Annam",
                19,
                5000.0,
                "Savings");

        account.displayAccount();

        System.out.println();

        System.out.println("Deposit 2000: " + account.deposit(2000));
        System.out.println("Balance: " + account.getBalance());

        System.out.println("Withdraw 1000: " + account.withdraw(1000));
        System.out.println("Balance: " + account.getBalance());

        System.out.println("Withdraw 10000: " + account.withdraw(10000));
        System.out.println("Balance: " + account.getBalance());

        account.setAccountType("Current");
        account.setStatus("Active");
        account.displayAccount();
    }
}
